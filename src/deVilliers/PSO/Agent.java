package deVilliers.PSO;

import deVilliers.Randomness;
import functions.ContinuousFunction;

import java.util.ArrayList;

public class Agent {
    ArrayList<Double> pbX = new ArrayList<>();
    ArrayList<Double> Velocity = new ArrayList<>();
    ArrayList<Double> GlobalBestX = new ArrayList<>();
    ArrayList<Double> curX = new ArrayList<>();
    Double y;
    Double pbY;
    ContinuousFunction CF;

    ArrayList<Double> R1 = new ArrayList<>();
    ArrayList<Double> R2 = new ArrayList<>();

    Double w;
    Double c1;
    Double c2;

    Double Range;
    Integer Dimension;

    public Agent(Integer Dimension, Double Range, ContinuousFunction cf, Double w, Double c1, Double c2) {
        this.w = w;
        this.c1 = c1;
        this.c2 = c2;
        CF = cf;
        curX = Initialize(Dimension, Range);
        GlobalBestX.addAll(curX);
        pbX.addAll(curX);
        Velocity = Initialize(Dimension, 1.0);
        R1 = Initialize(Dimension, 1.0);
        R2 = Initialize(Dimension, 1.0);
        y = CF.evaluate(curX);
        pbY = CF.evaluate(curX);
        this.Range = Range;
        this.Dimension = Dimension;
    }

    public void Update(ArrayList<Double> globalBest)
    {
        Double gbY = CF.evaluate(globalBest);
        ArrayList<Double> newVelocity = new ArrayList<>();
        ArrayList<Double> updatedX = new ArrayList<>();
        R1 = Initialize(Dimension, 1.0);
        R2 = Initialize(Dimension, 1.0);
        for (int i = 0; i <= Velocity.size() -1; i++)
        {
            Double Vij = (w * Velocity.get(i)) + (c1*R1.get(i)*(pbX.get(i)-curX.get(i)))+(c2*R2.get(i)*(globalBest.get(i)-curX.get(i)));
            newVelocity.add(Vij);
            Double Xij = curX.get(i)+Vij;
            updatedX.add(Xij);
        }
        curX.clear();
        Velocity.clear();
        curX.addAll(updatedX);
        Velocity.addAll(newVelocity);

        Double curY = CF.evaluate(curX);
        if (curY <= CF.evaluate(pbX))
        {
            pbX.clear();
            pbX.addAll(curX);
            pbY = curY;
        }
    }

    public void Update(Agent A)
    {
        Double gbY = CF.evaluate(A.pbX);
        ArrayList<Double> newVelocity = new ArrayList<>();
        ArrayList<Double> updatedX = new ArrayList<>();
        R1 = Initialize(Dimension, 1.0);
        R2 = Initialize(Dimension, 1.0);
        for (int i = 0; i <= Velocity.size() -1; i++)
        {
            Double Vij = (w * Velocity.get(i)) + (c1*R1.get(i)*(pbX.get(i)-curX.get(i)))+(c2*R2.get(i)*(A.pbX.get(i)-curX.get(i)));
            if (Vij > 1000.0)
            {
                Randomness r = new Randomness();
                Vij = r.UniformRandomNumber(1.0);
            }
            newVelocity.add(Vij);

            Double Xij = curX.get(i)+Vij;
            updatedX.add(Xij);
        }
        curX.clear();
        Velocity.clear();
        curX.addAll(updatedX);
        Velocity.addAll(newVelocity);

        Double curY = CF.evaluate(curX);
        if (curY <= CF.evaluate(pbX))
        {
            pbX.clear();
            pbX.addAll(curX);
            pbY = curY;
        }
    }

    public ArrayList<Double> Initialize(Integer Dimesion, Double Range)
    {
        ArrayList<Double> listOut = new ArrayList<>();
        Randomness r = new Randomness();
        for (int i = 0; i <= Dimesion - 1; i++)
        {
            listOut.add(r.UniformRandomNumber(Range));
        }
        return listOut;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "pbY=" + pbY +
                ", pbX=" + pbX +
                '}';
    }
    public String curString() {
        return "Agent{" +
                "Y=" + y +
                ", X=" + curX +
                '}';
    }

    public String veloc() {
        return "Agent{" +
                ", Velocity=" + Velocity +
                '}';
    }
}
