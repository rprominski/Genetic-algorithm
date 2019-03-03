package SolutionRater;

import InstanceGenerator.Maintanance;
import InstanceGenerator.Operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SolutionRater {

    private String lastSolution = new String();

    public String getLastSolution() {
        return lastSolution;
    }

    public int rate(Solution solution) {
        Collections.sort(solution.getMaintanances(), Comparator.comparing(m -> m.getAfterTask()));
        int currMaintanance = 0;
        double cumulation = 10;
        int currentBeginning = 0;
        List <ExecutedOperation> machine1 = new ArrayList<>();
        List <ExecutedOperation> machine2 = new ArrayList<>();
        for (int i = 0; i < solution.getTaskNumbers().size(); i++) {
            Operation o = solution.getInstance().getTasks().get(solution.getTaskNumbers().get(i)).getOperations().get(0);
            ExecutedOperation eo;
            if(machine1.size() == 0) {
                eo = new ExecutedOperation(0,o.getTime(),solution.getTaskNumbers().get(i));
            } else {
                eo = new ExecutedOperation(
                        currentBeginning,
                        (int) Math.ceil(o.getTime() * cumulation / 10),
                        solution.getTaskNumbers().get(i));
            }
            machine1.add(eo);
            currentBeginning = machine1.get(machine1.size() -1).getBegining() + machine1.get(machine1.size() -1).getDuration();
            cumulation += 1;
            while(solution.getMaintanances().size() > currMaintanance && solution.getMaintanances().get(currMaintanance).getAfterTask() == i) {
                Maintanance m = solution.getMaintanances().get(currMaintanance);
                machine1.add(new ExecutedOperation(currentBeginning,m.getTime(),-1));
                currMaintanance++;
                cumulation = 10;
                currentBeginning += m.getTime();
            }
        }

        int last = 0;
        for (int i = 0; i < machine1.size(); i++) {
            ExecutedOperation o = machine1.get(i);
            if(machine1.get(i).id != -1) {
                ExecutedOperation eo;
                if(last < o.getBegining() + o.getDuration()) {
                    eo = new ExecutedOperation(
                            o.getBegining() + o.getDuration(),
                            solution.getInstance().getTasks().get(o.getId()).getOperations().get(1).getTime(),
                            o.getId());
                } else {
                    eo = new ExecutedOperation(
                            last,
                            solution.getInstance().getTasks().get(o.getId()).getOperations().get(1).getTime(),
                            o.getId());
                }
                last = eo.getBegining() + eo.getDuration();
                machine2.add(eo);
            }
        }
        return machine2.get(machine2.size() -1).getDuration() + machine2.get(machine2.size() -1).getBegining();
    }

    public int rateWithOutputString(Solution solution) {
        Collections.sort(solution.getMaintanances(), Comparator.comparing(m -> m.getAfterTask()));
        String formatedSolution = new String();
        int currMaintanance = 0;
        double cumulation = 10;
        int currentBeginning = 0;
        List <ExecutedOperation> machine1 = new ArrayList<>();
        List <ExecutedOperation> machine2 = new ArrayList<>();
        for (int i = 0; i < solution.getTaskNumbers().size(); i++) {
            Operation o = solution.getInstance().getTasks().get(solution.getTaskNumbers().get(i)).getOperations().get(0);
            ExecutedOperation eo;
            if(machine1.size() == 0) {
                eo = new ExecutedOperation(0,o.getTime(),solution.getTaskNumbers().get(i));
            } else {
                eo = new ExecutedOperation(
                        currentBeginning,
                        (int) Math.ceil(o.getTime() * cumulation / 10),
                        solution.getTaskNumbers().get(i));
            }
            machine1.add(eo);
            currentBeginning = machine1.get(machine1.size() -1).getBegining() + machine1.get(machine1.size() -1).getDuration();
            cumulation += 1;
            while(solution.getMaintanances().size() > currMaintanance && solution.getMaintanances().get(currMaintanance).getAfterTask() == i) {
                Maintanance m = solution.getMaintanances().get(currMaintanance);
                machine1.add(new ExecutedOperation(currentBeginning,m.getTime(),-1));
                currMaintanance++;
                cumulation = 10;
                currentBeginning += m.getTime();
            }
        }

        int last = 0;
        for (int i = 0; i < machine1.size(); i++) {
            ExecutedOperation o = machine1.get(i);
            if(machine1.get(i).id != -1) {
                ExecutedOperation eo;
                if(last < o.getBegining() + o.getDuration()) {
                    eo = new ExecutedOperation(
                            o.getBegining() + o.getDuration(),
                            solution.getInstance().getTasks().get(o.getId()).getOperations().get(1).getTime(),
                            o.getId());
                } else {
                    eo = new ExecutedOperation(
                            last,
                            solution.getInstance().getTasks().get(o.getId()).getOperations().get(1).getTime(),
                            o.getId());
                }
                last = eo.getBegining() + eo.getDuration();
                machine2.add(eo);
            }
        }
        formatedSolution = formatedSolution + "M1: ";
        int number = 1;
        int mDuration = 0, iDuration = 0;
        for (int i = 0; i < machine1.size(); i++) {
            if(machine1.get(i).getId() != -1) {
                formatedSolution += String.format("op1_%d %d %d %d ", machine1.get(i).getId(), machine1.get(i).getBegining(),
                        solution.getInstance().getTasks().get(machine1.get(i).getId()).getOperations().get(0).getTime(),
                        machine1.get(i).getDuration());
            } else {
                formatedSolution += String.format("maint%d_M1 %d %d ",number,machine1.get(i).getBegining(),
                        machine1.get(i).getDuration());
                mDuration += machine1.get(i).getDuration();
                number++;
            }
        }
        number = 1;
        int currTime = 0;
        formatedSolution += "\nM2: ";
        for (int i = 0; i < machine2.size(); i++) {
            if(currTime < machine2.get(i).getBegining()) {
                formatedSolution += String.format("idle%d_M2 %d %d ",number, currTime, machine2.get(i).getBegining() - currTime);
                iDuration += machine2.get(i).getBegining() - currTime;
                number++;
            }
            formatedSolution += String.format("op1_%d %d %d ", machine2.get(i).getId(),
                    machine2.get(i).getBegining(), machine2.get(i).getDuration());
            currTime = machine2.get(i).getBegining() + machine2.get(i).getDuration();
        }
        formatedSolution += String.format("\n%d\n%d\n%d\n%d\n",mDuration,0,0,iDuration);
        lastSolution = formatedSolution;
        return machine2.get(machine2.size() -1).getDuration() + machine2.get(machine2.size() -1).getBegining();
    }
}
