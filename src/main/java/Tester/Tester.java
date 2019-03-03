package Tester;

import GeneticAlgorithm.GeneticAlgorithm;
import InstanceGenerator.InstanceOfProblem;
import RandomSolver.RandomSolver;
import SolutionRater.Solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Tester {

    int basicMutationProbability = 5;
    int basicCrossoverProbability = 40;
    int basicSelectionSize = 50;
    int basicMaxGenerationSize = 100;
    int basicFirstGenerationBest;
    String inPath;
    String paramOutPath;
    String resultOutPath;
    public void testMutationProbability(int min, int max) {
        for(int i = min; i < max; i++) {
            for (int j = 1; j < 10; j++) {
                for(int k = 0; k < 5; k++) {
                    InstanceOfProblem instance = new InstanceOfProblem();
                    instance.readFromFile(new File("").getAbsolutePath() + "\\src\\test\\instances\\" + j + ".txt");
                    ArrayList<Solution> generation = initGeneration(basicMaxGenerationSize, instance);
                    GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(instance, i, basicCrossoverProbability,
                            basicSelectionSize, basicMaxGenerationSize, generation);

                    saveToFile(paramOutPath + "\\mutation.txt",getResult(geneticAlgorithm));
                }
            }
        }
    }

    public void testMaxGenerationSize(int min, int max) {
        for(int i = min; i < max; i++) {
            for (int j = 1; j < 10; j++) {
                for(int k = 0; k < 5; k++) {
                    InstanceOfProblem instance = new InstanceOfProblem();
                    instance.readFromFile(new File("").getAbsolutePath() + "\\src\\test\\instances\\" + j + ".txt");
                    ArrayList<Solution> generation = initGeneration(basicMaxGenerationSize, instance);
                    GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(instance, basicMutationProbability, basicCrossoverProbability,
                            basicSelectionSize, 500 + i * 500, generation);

                    saveToFile(paramOutPath + "\\maxGeneration.txt",getResult(geneticAlgorithm));
                }
            }
        }
    }

    public void testcrossoverProbability(int min, int max) {
        for(int i = min; i < max; i++) {
            for (int j = 1; j < 10; j++) {
                for(int k = 0; k < 5; k++) {
                    InstanceOfProblem instance = new InstanceOfProblem();
                    instance.readFromFile(new File("").getAbsolutePath() + "\\src\\test\\instances\\" + j + ".txt");
                    ArrayList<Solution> generation = initGeneration(basicMaxGenerationSize, instance);
                    GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(instance, basicMutationProbability, i,
                            basicSelectionSize, basicMaxGenerationSize, generation);

                    saveToFile(paramOutPath + "\\crossover.txt",getResult(geneticAlgorithm));
                }
            }
        }
    }

    public void testSelectionSize(int min, int max) {
        for(int i = min; i < max; i++) {
            for (int j = 1; j < 10; j++) {
                for(int k = 0; k < 5; k++) {
                    InstanceOfProblem instance = new InstanceOfProblem();
                    instance.readFromFile(new File("").getAbsolutePath() + "\\src\\test\\instances\\" + j + ".txt");
                    ArrayList<Solution> generation = initGeneration(basicMaxGenerationSize, instance);
                    GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(instance, basicMutationProbability, basicCrossoverProbability,
                            i, basicMaxGenerationSize, generation);

                    saveToFile(paramOutPath + "\\selection.txt",getResult(geneticAlgorithm));
                }
            }
        }
    }

    public ArrayList<Solution> initGeneration(int GenerationSize, InstanceOfProblem instance) {
        RandomSolver solver = new RandomSolver();
        ArrayList<Solution> firstGeneration = new ArrayList<>();
        for(int k = 0; k < basicMaxGenerationSize; k++) {
            firstGeneration.add(solver.solve(instance));
        }
        return firstGeneration;
    }

    public String getResult(GeneticAlgorithm geneticAlgorithm) {
        geneticAlgorithm.solve();
        return geneticAlgorithm.toString();
    }

    public void saveToFile(String path, String result) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(path, false));
            printWriter.println(result);
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void testInstance() {
        for(File f : new File(inPath).listFiles()) {
            System.out.println(f.getName());
            InstanceOfProblem instance = new InstanceOfProblem();
            instance.readFromFile(f.getAbsolutePath());
            GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(instance);
            saveToFile(resultOutPath + "\\" + f.getName(),"**** " + f.getName() + " ****\n" + geneticAlgorithm.solve());
        }

    }

    public Tester() {
        inPath = new File("").getAbsolutePath() + "\\src\\test\\Instances\\";
        paramOutPath = new File("").getAbsolutePath() + "\\src\\test\\Parameters\\";
        resultOutPath = new File("").getAbsolutePath() + "\\src\\test\\Results";
    }

    public Tester(String inPath, String resultOutPath) {
        this.inPath = inPath;
        this.resultOutPath = resultOutPath;
        this.paramOutPath = resultOutPath;
    }

}
