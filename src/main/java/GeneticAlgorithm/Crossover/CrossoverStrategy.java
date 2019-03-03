package GeneticAlgorithm.Crossover;

import SolutionRater.Solution;

import java.util.ArrayList;

public interface CrossoverStrategy {

    public ArrayList<Solution> doCrossover(Solution s1, Solution s2);
}
