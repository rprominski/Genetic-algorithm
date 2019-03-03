package RandomSolver;

import InstanceGenerator.InstanceOfProblem;
import InstanceGenerator.Maintanance;
import SolutionRater.Solution;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomSolver {

    public Solution solve(InstanceOfProblem instance) {
        ArrayList<Integer> tasksNumbers = (ArrayList<Integer>)
                Arrays.stream(IntStream.rangeClosed(0, instance.getNumberOfTasks() - 1)
                .toArray())
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(tasksNumbers);
        ArrayList<Maintanance> maintanances = new ArrayList<>();
        ArrayList<Integer> al = (ArrayList<Integer>) tasksNumbers;

        for (int i = 0; i < tasksNumbers.size(); i++) {
            if(Math.abs(new Random().nextInt()) % 100 < 1) {
                maintanances.add(new Maintanance(instance.getAvergeTaskTime() * 3,i));
            }
        }
        return new Solution(instance,tasksNumbers,maintanances);
    }
}
