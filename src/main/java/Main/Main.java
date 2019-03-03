package Main;

import GeneticAlgorithm.GeneticAlgorithm;
import InstanceGenerator.InstanceGenerator;
import InstanceGenerator.InstanceOfProblem;
import Tester.Tester;

public class Main {
    public static void main(String args[]) {
        Tester tester;
        if(args.length == 2) {
            tester = new Tester(args[0],args[1]);
        } else {
            tester = new Tester();
        }
        tester.testInstance();
    }
}
