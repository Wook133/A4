package deVilliers.GA;

import deVilliers.GAexp;
import deVilliers.Randomness;
import deVilliers.readCSV;
import functions.*;
import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class popControl {
    ArrayList<Life> population = new ArrayList<>();
    String sfunction;

    Double Range, crossoverRate, MutationRate;
    Integer PopulationSize, Generations, Dimensions;
    ContinuousFunction f;

    public popControl(Double range, ContinuousFunction cf, Integer populationsize, Double cr, Double mr, Integer generations, Integer dimensions) {
        Range = range;
        f = cf;
        PopulationSize = populationsize;
        crossoverRate = cr;
        MutationRate = mr;
        Generations = generations;
        Dimensions = dimensions;


    }
    public popControl(Double range, ContinuousFunction cf, Integer populationsize, Double cr, Double mr, Integer generations, Integer dimensions, String s) {
        Range = range;
        f = cf;
        PopulationSize = populationsize;
        crossoverRate = cr;
        MutationRate = mr;
        Generations = generations;
        Dimensions = dimensions;
        sfunction = s;
    }

    public void InitializePopulation()
    {
        population = new ArrayList<>();
        for (int i = 0; i <= PopulationSize - 1; i++)
        {
            Life cur = new Life(Dimensions, Range);
            cur.y = f.evaluate(cur.X);
            population.add(cur);
        }
    }

    public ArrayList<Pair<Integer, Integer>> ElitistSelection()
    {
        Collections.sort(population, new sortY());
        ArrayList<Pair<Integer, Integer>> breedingPairs = new ArrayList<>();
        Randomness r = new Randomness();
        Integer Half = (population.size()-1)/2;
        Pair<Integer, Integer> cur1 = Pair.create(0, 0);
        breedingPairs.add(cur1);
        for (int i = 1; i <= Half; i++)
        {
            Pair<Integer, Integer> cur = Pair.create(i, r.UniformPositiveRandomInteger(Half*1.0));
            breedingPairs.add(cur);
        }
        return breedingPairs;
    }

    public ArrayList<Life> Breed(ArrayList<Pair<Integer, Integer>> breedingpairs)
    {
        ArrayList<Life> temp = new ArrayList<>();
        Life c = population.get(breedingpairs.get(0).getFirst());
        Life cc = population.get(breedingpairs.get(0).getSecond());
        Life ccc = createClone(c, cc);
        temp.add(ccc);
        for (int i = 1; i <= breedingpairs.size() - 1; i++)
        {
            Life A = population.get(breedingpairs.get(i).getFirst());
            Life B = population.get(breedingpairs.get(i).getSecond());
            Life cur = createLife(A, B);
            cur.y = f.evaluate(cur.X);
            temp.add(cur);
        }
        return temp;
    }

    public void Evolve()
    {
        Collections.sort(population, new sortY());
        int igen =0;
        while (igen <= Generations)
        {
            Life curMin = Collections.min(population, new sortY());
            Life curMax = Collections.max(population, new sortY());
            System.out.println("Generation " + igen);
            System.out.println("Size " + population.size());
            System.out.println("Min : " + curMin.toString());
            System.out.println("Max : " + curMax.toString());
            ArrayList<Pair<Integer, Integer>> breeders = new ArrayList<>();
            breeders = ElitistSelection();
            ArrayList<Life> children = new ArrayList<>();
            ArrayList<Life> children2 = new ArrayList<>();
            children = Breed(breeders);
            children2 = Breed(breeders);
            population = new ArrayList<>();
            population.addAll(children);
            population.addAll(children2);
            //breeders = selectBest10Percent();
            //BreedToPop(breeders);
            igen = igen + 1;
        }
    }
    public void EvolvePrint(String sinput)
    {
        Collections.sort(population, new sortY());
        int igen =0;
        while (igen <= Generations)
        {
            readCSV rcsv = new readCSV();

            Life curMin = Collections.min(population, new sortY());
            Life curMax = Collections.max(population, new sortY());
            System.out.println("Generation " + igen);
            System.out.println("Size " + population.size());
            System.out.println("Min : " + curMin.toString());
            System.out.println("Max : " + curMax.toString());
            ArrayList<Pair<Integer, Integer>> breeders = new ArrayList<>();
            breeders = ElitistSelection();
            ArrayList<Life> children = new ArrayList<>();
            ArrayList<Life> children2 = new ArrayList<>();
            children = Breed(breeders);
            children2 = Breed(breeders);
            population = new ArrayList<>();
            population.addAll(children);
            population.addAll(children2);
            //breeders = selectBest10Percent();
            //BreedToPop(breeders);
            //(Double ybest, Double yworst, String function, String optimizationMethod, Integer popSize, Integer generation, Integer curGeneration, Double cr, Double mr)
            try {
                GAexp curEXP = new GAexp(curMin.y, curMax.y, sfunction, "Genetic Algorithm", population.size(), Generations, igen, crossoverRate, MutationRate);
                rcsv.writeCsvFile(sinput, curEXP.print());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            igen = igen + 1;
        }
    }




    public Life createLife(Life A, Life B)
    {
        ArrayList<Double> temp = new ArrayList<>();
        for (int j = 0; j <= A.X.size()-1; j++)
        {
            temp.add(0.0);
        }
        Randomness r = new Randomness();
        if (A.X.size() == B.X.size()) {
            for (int i = 0; i <= A.X.size() - 1; i++) {
                Double curCR = r.UniformPositiveRandomNumber(1.0);
                if (curCR >= crossoverRate)
                {
                    Double curMR = r.UniformPositiveRandomNumber(1.0);
                    if (curMR >= MutationRate)
                    {

                        Double c = B.X.get(i) + (r.UniformRandomNumber(1.0) * A.X.get(i));
                        temp.set(i, c);
                    }
                    else
                    {
                        temp.set(i, B.X.get(i));
                    }
                }
                else
                {
                    Double curMR = r.UniformPositiveRandomNumber(1.0);
                    if (curMR >= MutationRate)
                    {
                        Double c = A.X.get(i) + (r.UniformRandomNumber(1.0) * A.X.get(i));
                        temp.set(i, c);
                    }
                    else
                    {
                        temp.set(i, A.X.get(i));
                    }
                }
            }
            return new Life(temp, f.evaluate(temp));
        }
        else
        {
            Double d = Collections.max(A.X);
            Life dl = new Life(A.X.size(), d);
            dl.y = f.evaluate(dl.X);
            return dl;
        }
    }
    public Life createClone(Life A, Life B)
    {
        ArrayList<Double> temp = new ArrayList<>();
        for (int j = 0; j <= A.X.size()-1; j++)
        {
            temp.add(0.0);
        }
        if (A.X.size() == B.X.size()) {
            for (int i = 0; i <= A.X.size() - 1; i++) {
                temp.set(i, A.X.get(i));
            }
            return new Life(temp, f.evaluate(temp));
        }
        else
        {
            Double d = Collections.max(A.X);
            Life dl = new Life(A.X.size(), d);
            dl.y = f.evaluate(dl.X);
            return dl;
        }
    }
}
class sortY implements Comparator<Life>
{
    @Override
    public int compare(Life o1, Life o2) {
        return Double.compare(o1.y, o2.y);
    }
}
