package uoa.partII;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import org.moeaframework.analysis.collector.Observation;
import org.moeaframework.analysis.plot.Plot;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Population;

public class Plots {
	private static int plotWidth = 900;
	private static int plotHeight = 600;

	public static Plot generatePopulationPlot(NondominatedPopulation observation, String algorithmName, String plotName) {
		Plot plot = new Plot();
		
		plot.add(algorithmName, observation);

		plot.setXLabel("Total Charge Cost");
		plot.setYLabel("Total Difference");

		plot.setTitle(plotName);

		return plot;
	}

	public static void savePlot(Plot plot, String name) throws IOException {
		// String format = Plot.supportsSVG() ? "svg" : "png"; // it would be nice to save in SVG, but there is a bug within MOEA with conflicting private key override names in the internal graph generation process which throws a IllegalArgumentException
		String format = "png";
		
		Path filepath = Paths.get("results/" + name + "." + format);
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
