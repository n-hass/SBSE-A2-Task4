package uoa.partII;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.moeaframework.Executor;
import org.moeaframework.Instrumenter;
import org.moeaframework.algorithm.NSGAII;
import org.moeaframework.analysis.collector.AttachPoint;
import org.moeaframework.analysis.collector.Collector;
import org.moeaframework.analysis.collector.PopulationCollector;
import org.moeaframework.core.EvolutionaryAlgorithm;
import org.moeaframework.core.Problem;
import org.moeaframework.core.Solution;

public class Main {
	public static void main(String[] args) throws IOException {
        // InitialSetup.setupFirstImage();
        runFirstGen();
        runFull();
	}

    public static void runFirstGen() throws IOException {
        Problem problem = new ChargeProblem();

        Executor executor = new Executor()
            .withProblem(problem) 
            .withAlgorithm("NSGAII") 
            .withProperty("populationSize", 20)
            .withMaxEvaluations(80);

        var result = executor.run();

        PrintStream runFiles = new PrintStream("results/files.txt");
        for (Solution solution : result) {
            String filename = (String) (solution.getAttribute("imageFileName"));
            runFiles.println(filename);
        }
        runFiles.close();

        var plot = Plots.generatePopulationPlot(result, "NSGA-II", "Initial Population");
        Plots.savePlot(plot, "InitialPopulation");
    }

    public static void runFull() throws IOException {
        Problem problem = new ChargeProblem();

        Executor executor = new Executor()
            .withProblem(problem) 
            .withAlgorithm("NSGAII") 
            .withProperty("populationSize", 20)
            .withMaxEvaluations(10000);

        var result = executor.run();

        PrintStream runFiles = new PrintStream("results/full_files.txt");
        for (Solution solution : result) {
            String filename = (String) (solution.getAttribute("imageFileName"));
            runFiles.println(filename);
        }
        runFiles.close();

        var plot = Plots.generatePopulationPlot(result, "NSGA-II", "Final Population");
        Plots.savePlot(plot, "FinalPopulation");
    }
}
