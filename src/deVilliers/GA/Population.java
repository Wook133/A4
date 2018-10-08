package deVilliers.GA;

import deVilliers.Randomness;
import functions.ContinuousFunction;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

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
       // Collections.reverse(population);
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

        for (int i = 5; i <= population.size() - 1; i++)
        {
            //System.out.println(i);
            Integer randomPos = Randomness.normalRandomInteger(population.size()*1.0);
            breeders.add(population.get(randomPos));
        }

        return breeders;
    }
    public ArrayList<Organism> SelectionUniform()
    {
        ArrayList<Organism> breeders = new ArrayList<>();
        Collections.sort(population, new sorterY());
       // Collections.reverse(population);
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

        for (int i = 5; i <= population.size() - 1; i++)
        {
            Random r = new Random();
           // Integer randomPos = r.nextInt(population.size() - 1);
            Integer randomPos = Randomness.UniformPositiveRandomInteger(population.size()*1.0);
            breeders.add(population.get(randomPos));
        }

        return breeders;
    }
    public ArrayList<Organism> CompleteUniform()
    {
        ArrayList<Organism> breeders = new ArrayList<>();

        /**
         * Uniform random Selection
         */

        for (int i = 0; i <= population.size() - 1; i++)
        {
            Integer randomPos = Randomness.UniformPositiveRandomInteger(population.size()*1.0);
            breeders.add(population.get(randomPos));
        }

        return breeders;
    }

    public void Breed(ArrayList<Organism> breeders)
    {
        ArrayList<Organism> temp = new ArrayList<>();
        ArrayList<Organism> bestFive = new ArrayList<>();
        for (int i = 0; i <= 4; i++)
        {
            Organism cur = new Organism(population.get(i).getX(), Range, f);
            bestFive.add(cur);
            population.add(0, cur);
        }
        for (int i = 5; i <= population.size() - 6; i++)
        {
            Organism cur = population.get(i);
            cur.Breed(breeders.get(i), MutationRate, crossoverRate);
            temp.add(cur);
        }
        population.clear();
        population.addAll(temp);
        population.addAll(bestFive);
    }

    public ArrayList<Organism> selectBest10Percent()
    {
        Long d = Math.round(population.size() *0.1);
        ArrayList<Organism> breeders = new ArrayList<>();
        Collections.sort(population, new sorterY());
        Collections.reverse(population);
        for (int i = 0; i <= d; i++)
        {
            breeders.add(population.get(i));
        }
        return breeders;
    }
    public void BreedToPop(ArrayList<Organism> breeders)
    {
        population.clear();
        int icounter = 0;
        while (icounter < PopulationSize)
        {
            Integer ipa = Randomness.normalRandomInteger((breeders.size() - 1)*1.0);
            Integer ipb = Randomness.normalRandomInteger((breeders.size() - 1)*1.0);
            Organism A = breeders.get(ipa);
            Organism B = breeders.get(ipb);
            A.Breed(B, MutationRate, crossoverRate);
            population.add(A);
            icounter = icounter + 1;
        }
    }



    public void display()
    {
        Collections.sort(population, new sorterY());
        for (Organism o : population)
        {
            System.out.println(o.toString());
        }
    }

    public void Evolve()
    {
        Collections.sort(population, new sorterY());
        int igen =0;
        while (igen <= Generations)
        {
            Organism curMin = Collections.min(population, new sorterY());
            Organism curMax = Collections.max(population, new sorterY());
            System.out.println("Generation " + igen);
            System.out.println("Size " + population.size());
            System.out.println("Min : " + curMin.toString());
            System.out.println("Max : " + curMax.toString());
            ArrayList<Organism> breeders = new ArrayList<>();
            breeders = SelectionNormal();
            Breed(breeders);
            //breeders = selectBest10Percent();
            //BreedToPop(breeders);
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
