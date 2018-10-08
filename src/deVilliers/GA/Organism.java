package deVilliers.GA;

import deVilliers.Randomness;
import functions.ContinuousFunction;
import org.apache.commons.math3.random.UniformRandomGenerator;
import org.apache.commons.math3.random.Well19937c;

import java.util.ArrayList;
import java.util.Random;

public class Organism {
    private ArrayList<Double> X;
    private Double y;
    private Double range;
    private ContinuousFunction f;

    public Double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Organism{" +
                ", y=" + y +
                "X=" + X +
                ", range=" + range +
                '}';
    }

    /**
     * Uniform Initialization
     * @param isize Dimension
     * @param Range [-range, range]
     */
    public Organism(int isize, Double Range, ContinuousFunction cf)
    {
        X = new ArrayList<>();
        for (int i = 0; i <= isize - 1; i++)
        {
            X.add(Randomness.UniformRandomNumber(Range));
        }
        y = cf.evaluate(X);
        this.range = Range;
        this.f = cf;
    }

    public Organism(ArrayList<Double> x) {
        X = x;
        y = f.evaluate(X);
    }

    public Integer getDimension()
    {
        return X.size();
    }

    public ArrayList<Double> getX() {
        return X;
    }

    public void replace(ArrayList<Double> x)
    {
        X.clear();
        X.addAll(x);
        y = f.evaluate(X);
    }

    public void perfectClone()
    {
        ArrayList<Double> temp = new ArrayList<>();
        temp.addAll(getX());
        replace(temp);
    }

    public void Breed(Organism O, Double MutationRate, Double CrossoverRate)
    {
        if (O.getDimension() == this.getDimension())
        {
            ArrayList<Double> temp = new ArrayList<>();
            Integer j = X.size() - 1;
            for (int i = 0; i <= j; i++)
            {
                Double curCR = Randomness.UniformPositiveRandomNumber(1.0);
                if (curCR > CrossoverRate)
                {
                    Double curMR = Randomness.UniformPositiveRandomNumber(1.0);
                    if (curMR > MutationRate)
                    {
                        Double curM = Randomness.NormalRandomNumber(range);
                        temp.add(O.X.get(i) + curM);
                    }
                    else
                    {
                        temp.add(O.X.get(i));
                    }
                }
                else
                {
                    Double curMR = Randomness.UniformPositiveRandomNumber(1.0);
                    if (curMR > MutationRate)
                    {
                        Double curM = Randomness.NormalRandomNumber(range);
                        temp.add(this.X.get(i) + curM);
                    }
                    else
                    {
                        temp.add(this.X.get(i));
                    }
                }
            }
            replace(temp);
        }
        else
        {
            Integer j = X.size() - 1;
            X.clear();
            X = new ArrayList<>();
            for (int i = 0; i <= j; i++)
            {
                X.add(Randomness.UniformRandomNumber(range));
            }
            y = f.evaluate(X);
        }
    }





}
