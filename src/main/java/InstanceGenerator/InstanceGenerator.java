package InstanceGenerator;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Random;

public class InstanceGenerator {
    private int numberOfMachines;
    private int numberOfTasks;

    public InstanceGenerator(int numberOfMachines, int numberOfTasks) {
        this.numberOfMachines = numberOfMachines;
        this.numberOfTasks = numberOfTasks;
    }

    public InstanceOfProblem generateInstance() {
        InstanceOfProblem instance = new InstanceOfProblem();

        for(int i = 0; i < numberOfTasks; i++) {
            instance.addTask(new Task(numberOfMachines));
        }
        return instance;
    }
}

