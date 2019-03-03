package GeneticAlgorithm.Selection;

import SolutionRater.Solution;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TournamentSelection implements SelectionStrategy {
    @Override
    public List<Solution> select(List<Pair<Integer, Solution>> solutions, int maxPopulation) {
        while(maxPopulation < solutions.size()) {
            int i,j;
            i = Math.abs(new Random().nextInt()) % solutions.size();
            do {
                j = Math.abs(new Random().nextInt()) % solutions.size();
            } while(i == j);
            if(solutions.get(i).getKey() < solutions.get(j).getKey()) {
                solutions.remove(j);
            } else {
                solutions.remove(i);
            }
        }
        ArrayList <Solution> selected = new ArrayList<>();
        for (Pair<Integer,Solution> p : solutions) {
            selected.add(p.getValue());
        }
        return selected;
    }
}