package InstanceGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Task {

    private int numberOfOperations;
    private List<Operation> operations;

    public Task() {
    }

    public Task(int numberOfOperations) {
        this.numberOfOperations = numberOfOperations;
        operations = new ArrayList<>();
        generateOperationTimes();
    }

    public Task(List<Operation> operations) {
        this.operations = operations;
        numberOfOperations = operations.size();
    }

    public void generateOperationTimes() {
        operations.clear();
        for (int i = 0; i < numberOfOperations; i++) {
            operations.add(new Operation());
        }
    }

    @Override
    public String toString() {
        return operations.stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
}
