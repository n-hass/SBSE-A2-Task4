package uoa.partI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import org.moeaframework.analysis.plot.Plot;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Population;

public class PopulationPlots {
	
	private static int plotWidth = 900;
	private static int plotHeight = 600;
	public static void setPlotWidth(int width) {
		plotWidth = width;
	}
	public static void setPlotHeight(int height) {
		plotHeight = height;
	}

	public static Plot generateParetoPlot(HashMap<String, List<NondominatedPopulation>> resultMap, String problem, int populationSize) {
		Plot plot = new Plot();
		
		for (String algorithm : resultMap.keySet()) {
			List<NondominatedPopulation> results = resultMap.get(algorithm);
			Population combined = new Population();
			for (int i = 0; i < results.size(); i++) {
				combined.addAll(results.get(i));
			}
			plot.add(algorithm, combined);
		}

		return plot;
	}

	public static void savePopulationPlot(Plot plot, String problem, int populationSize) throws IOException {
		// String format = Plot.supportsSVG() ? "svg" : "png"; // it would be nice to save in SVG, but there is a bug within MOEA with conflicting private key override names in the internal graph generation process which throws a IllegalArgumentException
		String format = "png";
		
		Path filepath = Paths.get("results/populationPlots/" + problem + "_" + populationSize + "." + format);
		Path parentPath = filepath.getParent();
		
		try {
			Files.createDirectories(parentPath);
		} catch (IOException e) {
			System.err.println("Error creating directory " + parentPath + ": " + e.getMessage());
			e.printStackTrace();
		}
		
		File file = new File(filepath.toString());
		
		try {
			plot.save(file, format, plotWidth, plotHeight);
		} catch (IOException | IllegalArgumentException e) {
			System.err.println("Error saving plot to " + filepath + ": " + e.getMessage());
			throw e;
		}
	}
}
