package deVilliers;

import deVilliers.GA.Population;
import functions.ContinuousFunction;
import functions.Griewank;
import functions.Rosenbrock;
import org.apache.commons.math3.random.UniformRandomGenerator;
import org.apache.commons.math3.random.Well19937c;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        /*Random r = new Random();
        ContinuousFunction f = new Griewank();
        ArrayList<Double> d = new ArrayList<>();
        for (int i = 0; i <= 30 -1; i++)
        {
            d.add(r.nextDouble());
        }
        System.out.println(f.evaluate(d));*/
        Population p1 = new Population(1000.0, new Griewank(), 1000, 0.8, 0.8, 1000, 30);
        p1.InitializePopulation();
        //p1.Breed(p1.SelectionNormal());
        p1.Evolve();

    }
   // public Population(Double range, ContinuousFunction cf, Integer populationsize, Double cr, Double mr, Integer generations, Integer dimensions) {
    //(Double range, ContinuousFunction cf, Integer populationsize, Double cr, Double mr, Integer generations, Integer dimensions)


}
