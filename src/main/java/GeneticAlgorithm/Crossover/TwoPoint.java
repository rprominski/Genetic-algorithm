package GeneticAlgorithm.Crossover;

import SolutionRater.Solution;

import java.util.ArrayList;
import java.util.Random;

public class TwoPoint implements CrossoverStrategy {

    @Override
    public ArrayList<Solution> doCrossover(Solution s1, Solution s2) {
        ArrayList<Integer> newOrder = new ArrayList<>();
        ArrayList<Integer> newOrder2 = new ArrayList<>();
        ArrayList<Solution> solutions = new ArrayList<>();
        solutions.add(s1.copy());
        solutions.add(s2.copy());
        int i = (Math.abs(new Random().nextInt()) % s1.getTaskNumbers().size());
        int i2 = (Math.abs(new Random().nextInt()) % s1.getTaskNumbers().size());

        if(i > i2) {
            int a = i;
            i = i2;
            i2 = a;
        }
        if(i == i2) {
            i2++;
        }
        System.out.println(i + " " + i2);

        for (int j = i; j < i2; j++) {
            newOrder.add(s1.getTaskNumbers().get(j));
        }
        for (int j = 0; j < i; j++) {
            newOrder2.add(s1.getTaskNumbers().get(j));
        }
        System.out.println(newOrder);
        for (int j = 0; j < s2.getTaskNumbers().size(); j++) {
            if(!newOrder.contains(s2.getTaskNumbers().get(j))) {
                newOrder.add(s2.getTaskNumbers().get(j));
            } else {
                newOrder2.add(s2.getTaskNumbers().get(j));
            }
        }  System.out.println(newOrder);
        for (int j = i2; j < s1.getTaskNumbers().size(); j++) {
            newOrder2.add(s1.getTaskNumbers().get(j));
        }
        solutions.get(0).setTaskNumbers(newOrder);
        solutions.get(1).setTaskNumbers(newOrder2);
        return solutions;
    }
}
