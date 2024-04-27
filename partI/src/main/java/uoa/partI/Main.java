package uoa.partI;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.moeaframework.Analyzer;
import org.moeaframework.Executor;
import org.moeaframework.analysis.plot.Plot;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Population;
import org.moeaframework.core.Solution;

public class Main {
	public static void main(String[] args) throws IOException {
		String[] problems = { "ZDT1", "ZDT2", "DTLZ3_3", "DTLZ4_3" };
		String[] algorithms = { "NSGAII", "MOEAD" };
		int[] populations = { 20, 100 };
		int maxEvaluations = 10000;
		int numSeeds = 10;

		for (int populationSize : populations) {
			for (String problem : problems) {
				System.out.println("Problem: " + problem + ", Population Size: " + populationSize);

				Analyzer analyzer = new Analyzer()
					.withProblem(problem)
					.includeHypervolume()
					.showStatisticalSignificance();
				
				Executor executor = new Executor()
					.withProblem(problem)
					.withMaxEvaluations(maxEvaluations)
					.withProperty("populationSize", populationSize)
					.distributeOnAllCores();

				for (String algorithm : algorithms) {
					List<NondominatedPopulation> results = executor.withAlgorithm(algorithm).runSeeds(numSeeds);
					Plot populationPlot = generateParetoPlot(results, algorithm, problem, populationSize);
					savePopulationPlot(populationPlot, algorithm, problem, populationSize);
					analyzer.addAll(algorithm, results);
				}
				
				analyzer.display();
				System.out.println("---");
			}
		}
	}

	public static Plot generateParetoPlot(List<NondominatedPopulation> results, String algorithm, String problem, int populationSize) {
		Plot plot = new Plot();
		
		Population combined = new Population();
		for (int i = 0; i < results.size(); i++) {
			combined.addAll(results.get(i));
		}
		plot.add(algorithm, combined);

		return plot;
	}

	public static void savePopulationPlot(Plot plot, String algorithm, String problem, int populationSize) {
		String filename = "plots/partI/" + algorithm + "_" + problem + "_" + populationSize + ".png";
		// plot.save(filename);
	}
}

