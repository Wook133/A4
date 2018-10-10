package deVilliers.DE;

import deVilliers.GA.Population;
import deVilliers.Randomness;
import functions.ContinuousFunction;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BasicDE {
    ArrayList<ArrayList<Double>> population = new ArrayList<>();
    Double Range, crossoverRate, MutationRate;
    Integer PopulationSize, Generations, Dimensions;
    ContinuousFunction f;

    public BasicDE( Double range, Double crossoverRate, Double mutationRate, Integer populationSize, Integer generations, Integer dimensions, ContinuousFunction cf) {
        this.population = new ArrayList<>();
        //this.population.addAll(new ArrayList<>(dimensions));
        Range = range;
        this.crossoverRate = crossoverRate;
        MutationRate = mutationRate;
        PopulationSize = populationSize;
        Generations = generations;
        Dimensions = dimensions;
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
            this.population.add(cur);
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



        return mutantVector;
    }



}
