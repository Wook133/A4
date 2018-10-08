package deVilliers.GA;

import deVilliers.Randomness;
import functions.ContinuousFunction;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Population
{
    ArrayList<Organism> population = new ArrayList<>();
    Double Range, crossoverRate, MutationRate;
    Integer PopulationSize, Generations, Dimensions;
    ContinuousFunction f;

    public Population(Double range, ContinuousFunction cf, Integer populationsize, Double cr, Double mr, Integer generations, Integer dimensions) {
        Range = range;
        f = cf;
        PopulationSize = populationsize;
        crossoverRate = cr;
        MutationRate = mr;
        Generations = generations;
        Dimensions = dimensions;
    }

    public void InitializePopulation()
    {
        for (int i = 0; i <= PopulationSize; i++)
        {
            population.add(new Organism(Dimensions, Range, f));
        }
    }

    public ArrayList<Organism> SelectionNormal()
    {
        ArrayList<Organism> breeders = new ArrayList<>();
        Collections.sort(population, new sorterY());
        Collections.reverse(population);
        /**
         * get 5 best
         */
        for (int i = 0; i <= 4; i++)
        {
            //System.out.println(i);
            breeders.add(population.get(i));
        }
        /**
         * Uniform random Selection
         */

        for (int i = 5; i <= population.size() - 6; i++)
        {
            //System.out.println(i);
            Integer randomPos = Randomness.normalRandomInteger(population.size()*1.0);
            breeders.add(population.get(randomPos));
        }
        /**
         * get 5 worst
         */
        Collections.reverse(population);
        for (int i = 0; i <= 4; i++)
        {
            breeders.add(population.get(i));
        }
        return breeders;
    }
    public ArrayList<Organism> SelectionUniform()
    {
        ArrayList<Organism> breeders = new ArrayList<>();
        Collections.sort(population, new sorterY());
        Collections.reverse(population);
        /**
         * get 5 best
         */
        for (int i = 0; i <= 4; i++)
        {
            breeders.add(population.get(i));
        }
        /**
         * Uniform random Selection
         */

        for (int i = 5; i <= population.size() - 6; i++)
        {
            Integer randomPos = Randomness.UniformPositiveRandomInteger(population.size()*1.0);
            breeders.add(population.get(randomPos));
        }
        /**
         * get 5 worst
         */
        Collections.reverse(population);
        for (int i = 0; i <= 4; i++)
        {
            breeders.add(population.get(i));
        }
        return breeders;
    }

    public void Breed(ArrayList<Organism> breeders)
    {
        for (int i = 0; i <= 4; i++)
        {
            Organism cur = population.get(i);
            cur.perfectClone();
            population.set(i,cur);
        }
        for (int i =5; i <= population.size() - 1; i++)
        {        //Breed(Organism O, Double MutationRate, Double CrossoverRate)
            Organism cur = population.get(i);
            cur.Breed(breeders.get(i), MutationRate, crossoverRate);
            population.set(i,cur);
        }
    }

    public void Evolve()
    {
        population.sort(new sorterY());
        int igen =0;
        while (igen <= Generations)
        {
            Organism curMin = Collections.min(population, new sorterY());
            Organism curMax = Collections.max(population, new sorterY());
            System.out.println("Generation " + igen);
            System.out.println("Min : " + curMin.toString());
            System.out.println("Max : " + curMax.toString());
            ArrayList<Organism> breeders = new ArrayList<>();
            breeders = SelectionUniform();
            Breed(breeders);
            igen = igen + 1;
        }
    }

}

class sorterY implements Comparator<Organism>
{

    @Override
    public int compare(Organism o1, Organism o2) {
        return Double.compare(o1.getY(), o2.getY());
    }
}
