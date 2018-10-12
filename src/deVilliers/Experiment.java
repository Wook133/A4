package deVilliers;

import deVilliers.PSO.Agent;

import java.util.ArrayList;

public class Experiment {
    ArrayList<Double> Xbest;
    Double ybest;

    ArrayList<Double> XWorst;
    Double yworst;

    String Function;
    String OptimizationMethod;

    Integer PopSize;
    Integer Generation;
    Integer curGeneration;

    public Experiment(ArrayList<Double> xbest, Double ybest, ArrayList<Double> XWorst, Double yworst, String function, String optimizationMethod, Integer popSize, Integer generation, Integer curGeneration) {
        Xbest = xbest;
        this.ybest = ybest;
        this.XWorst = XWorst;
        this.yworst = yworst;
        Function = function;
        OptimizationMethod = optimizationMethod;
        PopSize = popSize;
        Generation = generation;
        this.curGeneration = curGeneration;
    }

    public String print()
    {
        return "["+Xbest + "], " +
                ybest + ", " +
                "["+XWorst + "], " +
                yworst + ", " +
                Function + ", " +
                OptimizationMethod + ", " +
                PopSize + ", " +
                Generation + ", " +
                curGeneration;
    }


}
