package InstanceGenerator;

public class Maintanance extends OperationPrimitive {
    int afterTask;

    public Maintanance(int minTime,int afterTask) {
        setMinTime(minTime);
        setMaxTime(2*minTime);
        setRandomTime();
        setAfterTask(afterTask);
    }

    public int getAfterTask() {
        return afterTask;
    }

    public void setAfterTask(int afterTask) {
        this.afterTask = afterTask;
    }

    @Override
    public String toString() {
        return "Maintanance{" +
                "Time=" + getTime() +
                " After=" + afterTask +
                '}';
    }
}
