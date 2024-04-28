package uoa.partI;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;

import org.moeaframework.Analyzer;
import org.moeaframework.Executor;
import org.moeaframework.analysis.plot.Plot;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Problem;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Mean;

public class Main {
	public static void main(String[] args) throws IOException {
		String[] algorithms = { "NSGAII", "MOEAD" };
		int[] populations = { 20, 100 };
		int maxEvaluations = 10000;
		int numSeeds = 10;
		// String[] problems = { "ZDT1", "ZDT2", "DTLZ3_3", "DTLZ4_3" };
		// initialise problems
		Problem[] problems = new Problem[] {
			new org.moeaframework.problem.ZDT.ZDT1(),
			new org.moeaframework.problem.ZDT.ZDT2(),
			new org.moeaframework.problem.DTLZ.DTLZ3(3),
			new org.moeaframework.problem.DTLZ.DTLZ4(3)
		};

		PrintStream allAnalysisResults = new PrintStream("results/analysisResults.txt");

		for (int populationSize : populations) {
			for (Problem problem : problems) {
				String problemName = problem.getClass().getSimpleName();
				
				allAnalysisResults.println("Problem: " + problemName + ", Population Size: " + populationSize);
				System.out.println("Problem: " + problemName + ", Population Size: " + populationSize);

				Analyzer analyzer = new Analyzer()
					.withProblem(problem)
					.includeHypervolume()
					.showStatistic(new StandardDeviation())
					.showStatistic(new Mean());
				
				Executor executor = new Executor()
					.withProblem(problem)
					.withMaxEvaluations(maxEvaluations)
					.withProperty("populationSize", populationSize);
				
				var resultMap = new HashMap<String, List<NondominatedPopulation>>();

				for (String algorithm : algorithms) {
					List<NondominatedPopulation> results = executor.withAlgorithm(algorithm).runSeeds(numSeeds);
					resultMap.put(algorithm, results);
					analyzer.addAll(algorithm, results);
				}
				
				if (problemName.startsWith("DTLZ")) {
					// PopulationPlots.generate3dPlot(resultMap, problemName, populationSize);
					Plot populationPlot = PopulationPlots.generate2dPlot(resultMap, problemName, populationSize);
					PopulationPlots.save2dPlot(populationPlot, problemName, populationSize);
				} else {
					Plot populationPlot = PopulationPlots.generate2dPlot(resultMap, problemName, populationSize);
					PopulationPlots.save2dPlot(populationPlot, problemName, populationSize);
				}
				
				analyzer.getAnalysis().display(allAnalysisResults);
				
				allAnalysisResults.println("---");
			}
		}

		allAnalysisResults.close();
	}
}

