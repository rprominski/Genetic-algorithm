package GeneticAlgorithm.Crossover;

import InstanceGenerator.Maintanance;
import SolutionRater.Solution;

import java.util.*;

public class SinglePoint implements CrossoverStrategy {
    @Override
    public ArrayList<Solution> doCrossover(Solution s1, Solution s2) {
        ArrayList<Integer> newOrder = new ArrayList<>();
        ArrayList<Integer> newOrder2 = new ArrayList<>();
        ArrayList<Maintanance> newMaintanances = new ArrayList<>();
        ArrayList<Maintanance> newMaintanances2 = new ArrayList<>();
        ArrayList<Solution> solutions = new ArrayList<>();
        solutions.add(s1.copy());
        solutions.add(s2.copy());
        int i = (Math.abs(new Random().nextInt()) % s1.getTaskNumbers().size());
        if(i == 0) {
            i++;
        }

        for (int j = 0; j < i; j++) {
            newOrder.add(s1.getTaskNumbers().get(j));
            for (int k = 0; k < s1.getMaintanances().size(); k++) {
                if(s1.getMaintanances().get(k).getAfterTask() == s1.getTaskNumbers().get(j)) {
                    newMaintanances.add(s1.getMaintanances().get(k));
                }
            }
        }

        for (int j = 0; j < s2.getTaskNumbers().size(); j++) {
            if(!newOrder.contains(s2.getTaskNumbers().get(j))) {
                newOrder.add(s2.getTaskNumbers().get(j));
                for (int k = 0; k < s2.getMaintanances().size(); k++) {
                    if (s2.getMaintanances().get(k).getAfterTask() == s2.getTaskNumbers().get(j)) {
                        newMaintanances.add(s2.getMaintanances().get(k));
                    }
                }
            } else {
                newOrder2.add(s2.getTaskNumbers().get(j));
                for (int k = 0; k < s1.getMaintanances().size(); k++) {
                    if(s1.getMaintanances().get(k).getAfterTask() == s1.getTaskNumbers().get(j)) {
                        newMaintanances2.add(s1.getMaintanances().get(k));
                    }
                }
            }
        }
        for (int j = i; j < s1.getTaskNumbers().size(); j++) {
            newOrder2.add(s1.getTaskNumbers().get(j));
            for (int k = 0; k < s2.getMaintanances().size(); k++) {
                if(s2.getMaintanances().get(k).getAfterTask() == s2.getTaskNumbers().get(j)) {
                    newMaintanances2.add(s2.getMaintanances().get(k));
                }
            }
        }
       // Collections.sort(newMaintanances,co);
        Collections.sort(newMaintanances, Comparator.comparing(m -> m.getAfterTask()));

        Collections.sort(newMaintanances2, Comparator.comparing(m -> m.getAfterTask()));
        solutions.get(0).setTaskNumbers(newOrder);
        solutions.get(0).setMaintanances(newMaintanances);
        solutions.get(1).setTaskNumbers(newOrder2);
        solutions.get(1).setMaintanances(newMaintanances2);
        //System.out.println(solutions.get(1).getMaintanances());
        return solutions;
    }
}
