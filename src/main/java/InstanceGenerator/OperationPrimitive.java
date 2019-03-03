package InstanceGenerator;

import java.util.Random;

public class OperationPrimitive {
    private int minTime;
    private int maxTime;
    private int time;

    public OperationPrimitive(int minTime, int maxTime) {
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public OperationPrimitive(int time) {
        this.time = time;
    }

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public OperationPrimitive() {

    }

    public void setRandomTime() {
        Random random = new Random();
        setTime(Math.abs(random.nextInt()) % (getMaxTime() - getMinTime()) + getMinTime());
    }
}
