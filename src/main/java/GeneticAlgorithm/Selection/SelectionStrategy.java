package GeneticAlgorithm.Selection;

import SolutionRater.Solution;
import javafx.util.Pair;

import java.util.List;

public interface SelectionStrategy {

    public List<Solution> select(List<Pair<Integer,Solution>> solutions, int maxPopulation);

}
