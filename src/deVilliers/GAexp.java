package deVilliers;

import java.util.ArrayList;

public class GAexp extends Experiment
{
    Double MR;
    Double CR;
    public GAexp(Double ybest, Double yworst, String function, String optimizationMethod, Integer popSize, Integer generation, Integer curGeneration, Double cr, Double mr) {
        super(ybest, yworst, function, optimizationMethod, popSize, generation, curGeneration);
        MR = mr;
        CR = cr;
    }

    @Override
    public String print()
    {
        return
                ybest + ", " +
                        yworst + ", " +
                        Function + ", " +
                        OptimizationMethod + ", " +
                        PopSize + ", " +
                        Generation + ", " +
                        CR + ", " +
                        MR + ", " +
                        curGeneration;
    }



}
