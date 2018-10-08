package deVilliers.GA;

import deVilliers.Randomness;
import functions.ContinuousFunction;

import java.util.ArrayList;
import java.util.Collections;

public class Life
{
    public ArrayList<Double> X = new ArrayList<>();
    public Double y;

    public Life(ArrayList<Double> x, Double y) {
        X = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Life{" +
                "y=" + y +
                ", X=" + X +
                '}';
    }

    public Life(int iSize, double range)
    {
        Randomness r = new Randomness();
        X = new ArrayList<>(iSize);

        for (int i = 0; i<= iSize - 1; i++)
        {
            X.add(r.UniformRandomNumber(range));
        }
    }







}

