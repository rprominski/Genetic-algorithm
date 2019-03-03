package InstanceGenerator;

import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InstanceOfProblem {

    private List<Task> tasks;

    void addTask(Task task) {
        tasks.add(task);
    }

    public InstanceOfProblem() {
        tasks = new ArrayList<>();
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void saveToFile(String path) {
        try {
            PrintWriter printWriter = new PrintWriter(path);
            printWriter.println(tasks.size());
            for (int i = 0; i < tasks.size(); i++) {
                printWriter.println(tasks.get(i));
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(String path) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            int a,b;
            a = scanner.nextInt();
            b = scanner.nextInt();
            addTask(new Task(new ArrayList<Operation>(Arrays.asList(new Operation(a),new Operation(b)))));
        }

    }

    public int getAvergeTaskTime() {
        int s = 0;
        for (int i = 0; i < tasks.size(); i++) {
            s += (tasks.get(i).getOperations().get(0).getTime() + tasks.get(i).getOperations().get(1).getTime());
        }
        return s / (tasks.size() * 2);
    }
}
