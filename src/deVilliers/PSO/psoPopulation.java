package deVilliers.PSO;

import deVilliers.GA.Life;
import functions.ContinuousFunction;
import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class psoPopulation {
    ArrayList<Particle> population = new ArrayList<>();
    Particle globalBest;
    Double Range, C1, C2, w;
    Integer PopulationSize, Generations, Dimensions;
    ContinuousFunction f;

    public psoPopulation(Double range, ContinuousFunction cf, Integer populationsize, Double c1, Double c2, Integer generations, Integer dimensions, Double W) {
        Range = range;
        f = cf;
        PopulationSize = populationsize;
        C1 = c1;
        C2 = c2;
        w = W;
        Generations = generations;
        Dimensions = dimensions;
    }

    public void InitializePopulation()
    {
        population = new ArrayList<>();
        for (int i = 0; i <= PopulationSize - 1; i++)
        {
            Particle cur = new Particle(Dimensions, Range, w, C1, C2, f);
            population.add(cur);
        }
        globalBest = Collections.min(population, new sortYPSO());
    }

    public void Swarm()
    {
        int igen =0;
        while (igen <= Generations) {
            Particle curMin = Collections.min(population, new sortYPSO());
            Particle curMax = Collections.max(population, new sortYPSO());
            System.out.println("Generation " + igen);
            System.out.println("Size " + population.size());
            System.out.println("Min : " + curMin.toString());
            System.out.println("Max : " + curMax.toXString());
            sortYPSO comparer = new sortYPSO();
            if (curMin.y <= globalBest.y)
            {
               /* System.out.println("Best");
                System.out.println("CUR = " + curMin.toString());
                System.out.println("Global = " +globalBest.toString());*/
                globalBest = curMin;
            }
           /* if (comparer.compare(curMin, globalBest) <= 0)
            {
                System.out.println("Best");
                System.out.println("CUR = " + curMin.toString());
                System.out.println("Global = " +globalBest.toString());
                globalBest = curMin;
            }*/
            for (int i = 0; i <= population.size() - 1; i++)
            {
                Particle cur = population.get(i);
                cur.update(globalBest.X);
                population.set(i, cur);
            }
            Particle curGlobalMin = Collections.min(population, new sortGByPSO());
            System.out.println("Global Min : " + curGlobalMin.toGBString());
            igen = igen + 1;
        }
    }




}
class sortYPSO implements Comparator<Particle>
{
    @Override
    public int compare(Particle o1, Particle o2) {
        return Double.compare(o1.y, o2.y);
    }
}
class sortGByPSO implements Comparator<Particle>
{
    @Override
    public int compare(Particle o1, Particle o2) {
        return Double.compare(o1.y, o2.y);
    }
}
