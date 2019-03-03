package SolutionRater;

import InstanceGenerator.InstanceOfProblem;
import InstanceGenerator.Maintanance;

import java.util.*;

public class Solution {
    private InstanceOfProblem instance;
    private ArrayList<Integer> taskNumbers;
    private ArrayList<Maintanance> maintanances;

    public Solution(InstanceOfProblem instance, ArrayList<Integer> taskNumbers,  ArrayList<Maintanance> maintanances) {
        this.instance = instance;
        this.taskNumbers = taskNumbers;
        this.maintanances = maintanances;
    }

    public InstanceOfProblem getInstance() {
        return instance;
    }

    public void setInstance(InstanceOfProblem instance) {
        this.instance = instance;
    }

    public List<Integer> getTaskNumbers() {
        return taskNumbers;
    }

    public void setTaskNumbers(ArrayList<Integer> taskNumbers) {
        this.taskNumbers = taskNumbers;
    }

    public List<Maintanance> getMaintanances() {
        return maintanances;
    }

    public void setMaintanances(ArrayList<Maintanance> maintanances) {
        this.maintanances = maintanances;
    }

    public void mutateMaintanance() {
        if(maintanances.size() == 0) {
            maintanances.add(new Maintanance(
                    instance.getAvergeTaskTime() * 3,
                    Math.abs(new Random().nextInt()) % instance.getNumberOfTasks()));
            return;
        }
        int i = Math.abs(new Random().nextInt()) % 3;
        int size = maintanances.size();
        int r = Math.abs(new Random().nextInt()) % size;

        if(i == 0) {
            if(size > 0) {
                maintanances.remove(r);
            }
        }

        if(i == 1) {
            maintanances.add(new Maintanance(
                    instance.getAvergeTaskTime() * 3,
                    Math.abs(new Random().nextInt()) % instance.getNumberOfTasks()));
        }

        if(i == 2) {
            if(Math.abs(new Random().nextInt()) % 2 == 0) {
                maintanances.get(r).setAfterTask(Math.abs(new Random().nextInt()) % instance.getNumberOfTasks());
            } else {
                maintanances.get(r).setRandomTime();
            }
        }
    }

    public void mutateTask() {
        int i = Math.abs(new Random().nextInt()) % instance.getNumberOfTasks();
        int j;

        do {
            j = Math.abs(new Random().nextInt()) % instance.getNumberOfTasks();
        } while(i == j);
        Collections.swap(taskNumbers,i,j);
    }

    public Solution copy() {
        Solution s = new Solution();
        s.setInstance(instance);
        s.setMaintanances(maintanances);
        s.setTaskNumbers(taskNumbers);
        return s;
    }

    public void mutate() {
        int i = (Math.abs(new Random().nextInt()) % 2);
        if(i !=0) {
            mutateMaintanance();
        } else {
            mutateTask();
        }
        Collections.sort(maintanances, Comparator.comparing(m -> m.getAfterTask()));
    }

    public Solution() {
    }
}



