package deVilliers.DE;

import deVilliers.GA.Life;
import deVilliers.GA.Population;
import deVilliers.Randomness;
import functions.ContinuousFunction;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.commons.math3.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class BasicDE {
    ArrayList<Pair<ArrayList<Double>, Double>> population = new ArrayList<>();
    ArrayList<Pair<ArrayList<Double>, Double>> trialpopulation = new ArrayList<>();
    Double Range, crossoverRate, MutationRate, ScaleFactor;
    Integer PopulationSize, Generations, Dimensions;
    ContinuousFunction f;

    public BasicDE( Double range, Double crossoverRate, Double mutationRate, Integer populationSize, Integer generations, Integer dimensions, ContinuousFunction cf, Double ScaleFactor) {
        this.population = new ArrayList<>();
        //this.population.addAll(new ArrayList<>(dimensions));
        Range = range;
        this.crossoverRate = crossoverRate;
        MutationRate = mutationRate;
        PopulationSize = populationSize;
        Generations = generations;
        Dimensions = dimensions;
        this.ScaleFactor = ScaleFactor;
        this.f = cf;
    }

    public void Initialize()
    {
        this.population = new ArrayList<>();
        Randomness r = new Randomness();
        for (int i = 0; i <= PopulationSize - 1; i++)
        {
            ArrayList<Double> cur = new ArrayList<>();
            for (int j = 0; j <= Dimensions - 1; j++)
            {
                cur.add(r.UniformRandomNumber(Range));
            }
            Pair<ArrayList<Double>, Double> curPair = Pair.create(cur, f.evaluate(cur));
            this.population.add(curPair);
        }
    }

    public HashSet<Integer> Select3Vectors(Integer i)
    {
        HashSet<Integer> uniqueVectorIndices = new HashSet<Integer>();
        uniqueVectorIndices.add(i);
        Randomness r = new Randomness();
        while(uniqueVectorIndices.size() <= 3)
        {
            uniqueVectorIndices.add(r.UniformPositiveRandomInteger(PopulationSize *1.0));
        }
        //uniqueVectorIndices.remove(i);
        return uniqueVectorIndices;
    }

    public ArrayList<Double> createMutantVector(HashSet<Integer> inVectorPos)
    {
        ArrayList<Double> mutantVector = new ArrayList<>();
        ArrayList<Integer> posVectors = new ArrayList<>();
        posVectors.addAll(inVectorPos);
        for (int i = 0; i <= Dimensions - 1; i++)
        {
            Double mutantScalar = population.get(posVectors.get(0)).getFirst().get(i) + (ScaleFactor * (population.get(posVectors.get(1)).getFirst().get(i) - population.get(posVectors.get(2)).getFirst().get(i)));
            mutantVector.add(mutantScalar);
        }
        return mutantVector;
    }

    public Pair<ArrayList<Double>, Double> createTrialVector(ArrayList<Double> mutantVector, ArrayList<Double> XVector)
    {
        ArrayList<Double> Uij = new ArrayList<>();
        Randomness rand = new Randomness();
        for (int j = 0; j <= Dimensions - 1; j++)
        {
            Double probability = rand.UniformPositiveRandomNumber(1.0);
            if (probability <= crossoverRate)
            {
                Uij.add(mutantVector.get(j));
            }
            else
            {
                Uij.add(XVector.get(j));
            }
        }
        Pair<ArrayList<Double>, Double> Upair = Pair.create(Uij, f.evaluate(Uij));
        return Upair;
    }

    public Double evaluateFitness(ArrayList<Double> Vector)
    {
        return f.evaluate(Vector);
    }

    public void createTrialPopulation(Integer i)
    {
        trialpopulation = new ArrayList<>();
        for (int k = 0; k <= population.size() - 1; k++) {
            HashSet<Integer> uniqueVectorIndices = new HashSet<Integer>();
            uniqueVectorIndices = Select3Vectors(i);

        }


    }




}
class sortDE implements Comparator<Pair<ArrayList<Double>, Double>>
{
    @Override
    public int compare(Pair<ArrayList<Double>, Double> o1, Pair<ArrayList<Double>, Double> o2) {
        return Double.compare(o1.getSecond(), o2.getSecond());
    }
}