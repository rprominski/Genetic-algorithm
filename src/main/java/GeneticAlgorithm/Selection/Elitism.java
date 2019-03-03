package GeneticAlgorithm.Selection;

import GeneticAlgorithm.Crossover.CrossoverStrategy;
import SolutionRater.Solution;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Elitism implements SelectionStrategy{

    @Override
    public List<Solution> select(List<Pair<Integer, Solution>> solutions, int maxPopulation) {
        Collections.sort(solutions, Comparator.comparing(p -> p.getKey()));
        List<Solution> selected = new ArrayList<>();

        for (int i = 0; i < maxPopulation; i++) {
            selected.add(solutions.get(i).getValue());
        }
        return selected;
    }
}
