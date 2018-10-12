package deVilliers.PSO;

import deVilliers.Randomness;
import functions.ContinuousFunction;

import java.util.ArrayList;

public class Particle {
    Double Dimension, Range;
    ArrayList<Double> X = new ArrayList<>();//current X coordinates
    ArrayList<Double> V = new ArrayList<>();//current Velocity of Particle
    ArrayList<Double> xY = new ArrayList<>();//Personal Best X coordinates
    ArrayList<Double> globalBest = new ArrayList<>();
    ArrayList<Double> R1 = new ArrayList<>();//Random Vector Uni(0,1)
    ArrayList<Double> R2 = new ArrayList<>();//Random Vector Uni(0,1)
    Double w;//inertia weight
    Double y;
    Double C1, C2;//Positive Acceleration Constants
    ContinuousFunction CF;

    /**
     * Initialize
     * @param iSize
     * @param range
     */
    public Particle(int iSize, double range, Double wI, Double c1, Double c2, ContinuousFunction cf)
    {
        Randomness r = new Randomness();
        X = new ArrayList<>();
        CF = cf;
        Dimension = iSize*1.0;
        Range = range;
        for (int i = 0; i<= iSize - 1; i++)
        {
            X.add(r.UniformRandomNumber(range));
        }
        for (int i = 0; i<= iSize - 1; i++)
        {
            V.add(r.UniformRandomNumber(1.0));
        }
        xY.addAll(X);
        y = cf.evaluate(X);
        w = wI;
        C1 = c1;
        C2 = c2;
        globalBest.addAll(X);
    }

    public void update(ArrayList<Double> Y)
    {
        setVelocity(Y);
        for (int i = 0; i <= X.size() - 1; i++)
        {
            Double cur = X.get(i);
            cur = cur + V.get(i);
            X.set(i, cur);
        }
        y = CF.evaluate(X);
        Double curPersonalBest = CF.evaluate(Y);
        if (y < curPersonalBest)
        {
            xY.clear();
            xY.addAll(X);
        }
        Double gb = CF.evaluate(Y);
        if (gb <= CF.evaluate(globalBest))
        {
            globalBest.clear();
            globalBest.addAll(Y);
        }
    }

    public Double getBest()
    {
        return CF.evaluate(globalBest);
    }

    public void randomizeR()
    {
        Randomness r = new Randomness();
        R1 = new ArrayList<>();
        R2 = new ArrayList<>();
        for (int i =0; i<= Dimension-1; i++)
        {
            R1.add(r.UniformPositiveRandomNumber(1.0));
            R2.add(r.UniformPositiveRandomNumber(1.0));
        }
    }


    /**
     * @param Y GlobalBest
     */
    public void setVelocity(ArrayList<Double> Y)
    {
        randomizeR();
        ArrayList<Double> newVelocity = new ArrayList<>();
        /*System.out.println("Velocity= "+V.size());
        System.out.println("Global X= "+Y.size());
        System.out.println("Particle X= "+X.size());*/
        for (int i = 0; i <= Y.size() - 1; i++)
        {
            Double cur = V.get(i) + (C1 * R1.get(i) * xY.get(i) - X.get(i)) + (C2 * R2.get(i) * Y.get(i) - X.get(i));
            newVelocity.add(cur);
        }
        V.clear();
        V = new ArrayList<>();
        V.addAll(newVelocity);
    }

    @Override
    public String toString() {
        return "Particle's PB{ PB = " + CF.evaluate(xY) +
                " xY=" + xY +
                '}';
    }
    public String toXString()
    {
        return "Particle's { y = " + CF.evaluate(X) +
                " X=" + X +
                '}';
    }
    public String toGBString()
    {
        return "Particle's { y = " + CF.evaluate(globalBest) +
                " X=" + X +
                '}';
    }

}
