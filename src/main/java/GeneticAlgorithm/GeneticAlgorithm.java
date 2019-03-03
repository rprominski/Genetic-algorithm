package GeneticAlgorithm;

import GeneticAlgorithm.Crossover.CrossoverStrategy;
import GeneticAlgorithm.Crossover.SinglePoint;
import GeneticAlgorithm.Selection.Elitism;
import GeneticAlgorithm.Selection.SelectionStrategy;
import GeneticAlgorithm.Selection.TournamentSelection;
import InstanceGenerator.InstanceOfProblem;
import InstanceGenerator.Maintanance;
import RandomSolver.RandomSolver;
import SolutionRater.Solution;
import SolutionRater.SolutionRater;
import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class GeneticAlgorithm {
    InstanceOfProblem instance;
    int mutationProbability = 13;
    int crossoverProbability = 86;
    int selectionSize = 33;
    int maxGenerationSize = 630;
    int firstGenerationBest;
    int bestResult;
    Solution bestSolution = new Solution();
    SelectionStrategy selectionStrategy;
    CrossoverStrategy crossoverStrategy;
    List<Solution> generation;

    public GeneticAlgorithm(InstanceOfProblem instance, int mutationProbability, int crossoverProbability,
                            int selectionSize, int maxGenerationSize, ArrayList<Solution> generation) {
        this.generation = generation;
        this.mutationProbability = mutationProbability;
        this.crossoverProbability = crossoverProbability;
        this.instance = instance;
        this.selectionSize = selectionSize * maxGenerationSize / 100;
        this.maxGenerationSize = maxGenerationSize;
    }

    public String solve() {
        init();
        int bestResultGeneration = -1;
        int i = 0;
        long endTime = System.nanoTime() + TimeUnit.SECONDS.toNanos(10);
        while(endTime > System.nanoTime()){
            if(i - bestResultGeneration == 1000) {
                break;
            }
            for (Solution s : generation) {
                int x = new SolutionRater().rate(s);
                if(bestResult > x) {
                    bestResult = x;
                    bestResultGeneration = i;
                    bestSolution = s;
                }
            }
            if(i == 0)  {
                firstGenerationBest = bestResult;
            }
            crossover();
            survivorSelection();
            mutateAll();
            i++;
        }
        SolutionRater rater = new SolutionRater();
        rater.rateWithOutputString(bestSolution);
        return Integer.toString(bestResult) + " " + Integer.toString(firstGenerationBest) + "\n" + rater.getLastSolution();
    }

    private void init() {
        bestResult = Integer.MAX_VALUE;
        generation = new ArrayList<>();
        selectionStrategy = new TournamentSelection();
        crossoverStrategy = new SinglePoint();
        RandomSolver randomSolver = new RandomSolver();

        while(generation.size() < maxGenerationSize) {
            generation.add(randomSolver.solve(instance));
        }
    }

    private List<Pair<Integer,Solution>> gateRateOfAllSolutions() {
        List<Pair<Integer,Solution>> rates = new ArrayList<>();
        for(Solution s : generation) {
            SolutionRater solutionRater = new SolutionRater();
            rates.add(new Pair<Integer, Solution>(solutionRater.rate(s),s));
        }
        return rates;
    }

    private void crossover() {
        List<Solution> selected = selectionStrategy.select(gateRateOfAllSolutions(), selectionSize);
        List<Solution> offSpring = new ArrayList<>();
        for (int i = 0; i < selected.size(); i++) {
            if(Math.abs(new Random().nextInt()) % 100 < crossoverProbability) {
                int j;
                do {
                    j = Math.abs(new Random().nextInt()) % selected.size();
                } while(i == j);
                List<Solution> s = crossoverStrategy.doCrossover(selected.get(i),selected.get(j));
                offSpring.add(s.get(0));
                offSpring.add(s.get(1));
            }
        }
        generation.addAll(offSpring);
    }

    private void mutateAll() {
        for(Solution s : generation) {
            if(Math.abs(new Random().nextInt()) % 100 < mutationProbability) {
                s.mutate();
            }
        }
    }

    private void survivorSelection() {
        SelectionStrategy strategy = new Elitism();
        generation = strategy.select(gateRateOfAllSolutions(),maxGenerationSize);
    }

    @Override
    public String toString() {
        return mutationProbability + " " + crossoverProbability + " " + selectionSize + " " + maxGenerationSize + " " +
                " " + firstGenerationBest + " " + bestResult;

    }

    public GeneticAlgorithm(InstanceOfProblem instance) {
        this.instance = instance;
    }
}
