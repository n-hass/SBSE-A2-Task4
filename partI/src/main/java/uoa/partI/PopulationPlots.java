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

import org.jzy3d.chart.EmulGLSkin;
import org.jzy3d.chart.factories.EmulGLChartFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.Scatter;
import org.jzy3d.plot3d.rendering.canvas.Quality;
import org.jzy3d.plot3d.rendering.legends.colorbars.*;

public class PopulationPlots {
	
	private static int plotWidth = 900;
	private static int plotHeight = 600;
	public static void setPlotWidth(int width) {
		plotWidth = width;
	}
	public static void setPlotHeight(int height) {
		plotHeight = height;
	}

	public static Plot generate2dPlot(HashMap<String, List<NondominatedPopulation>> resultMap, String problem, int populationSize) {
		Plot plot = new Plot();
		
		for (String algorithm : resultMap.keySet()) {
			List<NondominatedPopulation> results = resultMap.get(algorithm);
			Population combined = new Population();
			for (NondominatedPopulation result : results) {
				combined.addAll(result);
			}
			plot.add(algorithm, combined);
		}

		plot.setTitle("Population of " + problem + " Solutions, with µ = " + populationSize);

		return plot;
	}

	public static void save2dPlot(Plot plot, String problem, int populationSize) throws IOException {
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

	public static void generate3dPlot(HashMap<String, List<NondominatedPopulation>> resultMap, String problem, int populationSize) throws IOException {
		int size = 10000;
		Coord3d[] points = new Coord3d[size];
		int pointsCnt = 0;
		Color[]   colors = new Color[size];

		double minZ = Double.MAX_VALUE;
		double maxZ = -Double.MAX_VALUE;

		for (String algorithm : resultMap.keySet()) {

			List<NondominatedPopulation> results = resultMap.get(algorithm);
			for (NondominatedPopulation result : results) {
				for (int i = 0; i < result.size(); i++) {
					double[] original = result.get(i).getObjectives();
					var point = new Coord3d(original[0], original[1], original[2]);
					
					// Update the Z range
					minZ = Math.min(minZ, point.z);
					maxZ = Math.max(maxZ, point.z);

					points[pointsCnt] = point;
					if (algorithm.equals("NSGAII")) {
						colors[pointsCnt] = new Color(0.0f, 0.0f, 1.0f);
					} else {
						colors[pointsCnt] = new Color(1.0f, 0.0f, 0.0f);
					}
					
					pointsCnt++;
				}
			}

		}

		// Second pass: Adjust colors based on Z value for depth perception
		double zRange = maxZ - minZ;
		for (int i = 0; i < pointsCnt; i++) {
			double normalizedZ = (points[i].z - minZ) / zRange;
			float alpha = (float) (0.5 + 0.5 * normalizedZ); // Scale alpha between 0.5 and 1.0
			colors[i].a = alpha;
		}

		Coord3d[] newPoints = new Coord3d[pointsCnt];
		Color[] newColors = new Color[pointsCnt];
		int j = 0;
		for (int i = 0; i < pointsCnt; i++) {
			newPoints[j] = points[i];
			newColors[j] = colors[i];
			j++;
		}

		Scatter scatter = new Scatter(newPoints, newColors);
		scatter.setWidth(5);
		Quality q = Quality.Advanced();
		var chart = new EmulGLChartFactory().newChart(q);
		
		chart.getScene().add(scatter);
		chart.getAxisLayout().setXAxisLabel("Objective 1");
		chart.getAxisLayout().setYAxisLabel("Objective 2");
		chart.getAxisLayout().setZAxisLabel("Objective 3");
		// chart.getLegends().add(legend);
		

		File file = new File("results/populationPlots/" + problem + "_" + populationSize + " (3d).png");
		chart.screenshot(file);

		// EmulGLSkin skin = EmulGLSkin.on(chart);
		// skin.getCanvas().setProfileDisplayMethod(true);
		chart.dispose();
	}
}
