package deVilliers.PSO;

import deVilliers.DEexp;
import deVilliers.PSOexp;
import deVilliers.readCSV;
import functions.ContinuousFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AgentPopulation {
    ArrayList<Agent> population = new ArrayList<>();
    Double Range;
    Integer Dimensions, Generations;
    ContinuousFunction F;
    Double InertiaW;
    Double c1;
    Double c2;
    String sfunction;
    public AgentPopulation(Double range, Integer dimensions, Integer populationSize, Integer epochs, ContinuousFunction f, Double inertiaW, Double c1, Double c2) {
        Range = range;
        Dimensions = dimensions;
        F = f;
        InertiaW = inertiaW;
        Generations = epochs;
        this.c1 = c1;
        this.c2 = c2;
        for (int i =0; i <= populationSize - 1; i++)
        {
            Agent curA = new Agent(Dimensions, range, f, inertiaW, c1, c2);
            population.add(curA);
        }
    }
    public AgentPopulation(Double range, Integer dimensions, Integer populationSize, Integer epochs, ContinuousFunction f, Double inertiaW, Double c1, Double c2, String sfunc) {
        Range = range;
        Dimensions = dimensions;
        F = f;
        InertiaW = inertiaW;
        Generations = epochs;
        this.c1 = c1;
        this.c2 = c2;
        sfunction = sfunc;
        for (int i =0; i <= populationSize - 1; i++)
        {
            Agent curA = new Agent(Dimensions, range, f, inertiaW, c1, c2);
            population.add(curA);
        }
    }

    public void Swarm()
    {
        for (int i = 1; i <= Generations; i++)
        {
            System.out.println("Generation " + i);
            Agent globalBest = Collections.min(population, new sortAgentPB());
            System.out.println("Current Best");
            System.out.println(globalBest.toString());
            ArrayList<Agent> nextPop = new ArrayList<>();
            for (int j = 0; j <= population.size() - 1; j++)
            {
                Agent cur = population.get(j);
                cur.Update(globalBest);
                nextPop.add(cur);
            }
            population.clear();
            population.addAll(nextPop);
            Agent curBest = Collections.min(population, new sortCurBest());
            System.out.println("Generation's Best");
            System.out.println(curBest.curString());
            System.out.println(curBest.veloc());
           /* Agent curWorst = Collections.max(population, new sortCurBest());
            System.out.println("Generation's Worst");
            System.out.println(curWorst.curString());
            System.out.println(curWorst.veloc());*/
            System.out.println("__________________________________");
        }
    }
    public void SwarmPrint(String sinput)
    {
        readCSV rcsv = new readCSV();
        for (int i = 1; i <= Generations; i++)
        {
            System.out.println("Generation " + i);
            Agent globalBest = Collections.min(population, new sortAgentPB());
            System.out.println("Current Best");
            System.out.println(globalBest.toStringPBY());
            ArrayList<Agent> nextPop = new ArrayList<>();
            for (int j = 0; j <= population.size() - 1; j++)
            {
                Agent cur = population.get(j);
                cur.Update(globalBest);
                nextPop.add(cur);
            }
            population.clear();
            population.addAll(nextPop);
            Agent curBest = Collections.min(population, new sortAgentPB());
            System.out.println("Generation's Best");
            System.out.println(curBest.toStringPBY());
          //  System.out.println(curBest.veloc());

            Agent curWorst = Collections.max(population, new sortAgentPB());
            System.out.println("Generation's Worst");
            System.out.println(curWorst.curYString());
           /* System.out.println(curWorst.veloc());*/
            try {
                ////public PSOexp(Double ybest, Double yworst, String function, String optimizationMethod, Integer popSize, Integer generation, Integer curGeneration, Double inertia, Double c1, Double c2)
                PSOexp curEXP = new PSOexp(globalBest.pbY, curWorst.pbY, sfunction, "Particle Swarm Optimization", population.size(), Generations, i, InertiaW, c1, c2, Range);
                rcsv.writeCsvFile(sinput, curEXP.print());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            System.out.println("__________________________________");
        }
    }

}

class sortAgentPB implements Comparator<Agent>
{
    @Override
    public int compare(Agent o1, Agent o2) {
        return Double.compare(o1.pbY, o2.pbY);
    }
}
class sortCurBest implements Comparator<Agent>
{
    @Override
    public int compare(Agent o1, Agent o2) {
        return Double.compare(o1.y, o2.y);
    }
}
