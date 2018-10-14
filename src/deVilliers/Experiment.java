package deVilliers;

import deVilliers.PSO.Agent;
import functions.*;

import java.util.ArrayList;

public class Experiment {

   // ArrayList<Double> Xbest;
    Double ybest;

   // ArrayList<Double> XWorst;
    Double yworst;

    String Function;
    String OptimizationMethod;

    Integer PopSize;
    Integer Generation;
    Integer curGeneration;

    Double Range;

    public Experiment(/*ArrayList<Double> xbest,*/ Double ybest,/* ArrayList<Double> XWorst,*/ Double yworst, String function, String optimizationMethod, Integer popSize, Integer generation, Integer curGeneration, Double range) {
       // Xbest = xbest;
        Range = range;
        this.ybest = ybest;
        //this.XWorst = XWorst;
        this.yworst = yworst;
        Function = function;
        OptimizationMethod = optimizationMethod;
        PopSize = popSize;
        Generation = generation;
        this.curGeneration = curGeneration;
    }

    public String print()
    {
        return //"["+Xbest + "], " +
                ybest + ", " +
               // "["+XWorst + "], " +
                yworst + ", " +
                Function + ", " +
                OptimizationMethod + ", " +
                PopSize + ", " +
                Generation + ", " +
                Range + ", " +
                curGeneration;
    }



}
