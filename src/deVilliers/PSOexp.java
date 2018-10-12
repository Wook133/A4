package deVilliers;

public class PSOexp extends Experiment {
    Double inertia, c1, c2;
    public PSOexp(Double ybest, Double yworst, String function, String optimizationMethod, Integer popSize, Integer generation, Integer curGeneration, Double inertia, Double c1, Double c2) {
        super(ybest, yworst, function, optimizationMethod, popSize, generation, curGeneration);
        this.inertia = inertia;
        this.c1 = c1;
        this.c2 = c2;
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
                        inertia + ", " +
                        c1 + ", " +
                        c2 + ", " +
                        curGeneration;
    }
}
