package deVilliers;

public class DEexp extends GAexp {
    Double scalefactor;

    public DEexp(Double ybest, Double yworst, String function, String optimizationMethod, Integer popSize, Integer generation, Integer curGeneration, Double cr, Double mr, Double sf) {
        super(ybest, yworst, function, optimizationMethod, popSize, generation, curGeneration, cr, mr);
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
                        scalefactor + ", " +
                        curGeneration;
    }
}
