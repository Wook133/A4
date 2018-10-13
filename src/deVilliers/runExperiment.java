package deVilliers;

import deVilliers.DE.BasicDE;
import deVilliers.GA.popControl;
import functions.*;

import java.util.ArrayList;
import java.util.HashSet;

public class runExperiment {



    public static void main(String[] args) {
        //RunGAExperiment("GAexperiment.csv");
        RunDEExperiment("DEexperiment.csv");
    }





    public static void RunGAExperiment(String sInput)
    {
        ArrayList<ContinuousFunction> listFunctions = new ArrayList<>();
        listFunctions.add(new Ackley());
        listFunctions.add(new Griewank());
        listFunctions.add(new Rosenbrock());
        listFunctions.add(new Spherical());
        listFunctions.add(new Whitley());
        ArrayList<String> functions = new ArrayList<>();
        functions.add("Ackley");
        functions.add("Griewank");
        functions.add("Rosenbrock");
        functions.add("Spherical");
        functions.add("Whitley");
        readCSV rcsv = new readCSV();


        ArrayList<Double> ranges = new ArrayList<>();
        ranges.add(1.0);
        ranges.add(10.0);
        ranges.add(100.0);
        ranges.add(1000.0);
        for (int i = 0; i <= listFunctions.size() - 1; i++)
        {
            for (int j = 0; j <= ranges.size() - 1; j++) {
                popControl ga1 = new popControl(ranges.get(j), listFunctions.get(i), 1000, 0.5, 0.8, 500, 30, functions.get(i));
                ga1.InitializePopulation();
                ga1.EvolvePrint(sInput);
            }
        }
    }

    public static void RunDEExperiment(String sInput)
    {
        ArrayList<ContinuousFunction> listFunctions = new ArrayList<>();
        listFunctions.add(new Ackley());
        listFunctions.add(new Griewank());
        listFunctions.add(new Rosenbrock());
        listFunctions.add(new Spherical());
        listFunctions.add(new Whitley());
        ArrayList<String> functions = new ArrayList<>();
        functions.add("Ackley");
        functions.add("Griewank");
        functions.add("Rosenbrock");
        functions.add("Spherical");
        functions.add("Whitley");
        readCSV rcsv = new readCSV();


        ArrayList<Double> ranges = new ArrayList<>();
        ranges.add(1.0);
        ranges.add(10.0);
        ranges.add(100.0);
        ranges.add(1000.0);
        for (int i = 0; i <= listFunctions.size() - 1; i++)
        {
            for (int j = 0; j <= ranges.size() - 1; j++) {
                BasicDE bde = new BasicDE(ranges.get(j), listFunctions.get(i), 1000, 0.5, 0.3, 500, 30, 0.3, functions.get(i));
                bde.Initialize();
                bde.differentialEvolvePrint(sInput);
            }
        }
    }
}
