package InstanceGenerator;

public class Operation extends OperationPrimitive {

    public Operation(int minTime, int maxTime) {
        super(minTime,maxTime);
        setRandomTime();
    }

    public Operation() {
        setMinTime(5);
        setMaxTime(20);
        setRandomTime();
    }

    public Operation(int x) {
        setMinTime(5);
        setMaxTime(20);
        setTime(x);
    }
    @Override
    public String toString() {
        return Integer.toString(getTime());
    }
}
