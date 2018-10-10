package deVilliers;

import deVilliers.GA.Population;
import deVilliers.GA.popControl;
import functions.*;
import org.apache.commons.math3.random.UniformRandomGenerator;
import org.apache.commons.math3.random.Well19937c;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
       /* HashSet<Integer> uniqueVectorIndices = new HashSet<Integer>();
        Randomness r = new Randomness();
        while(uniqueVectorIndices.size() <= 2)
        {
            Integer pos = r.UniformPositiveRandomInteger(4 *1.0);
            System.out.println(pos);
            uniqueVectorIndices.add(pos);
        }
        System.out.println("_____________________");
        System.out.println(uniqueVectorIndices);*/


      /*  Random r = new Random();
        ContinuousFunction f = new Griewank();
        //popControl ga1 = new popControl(600.0, new Whitley(), 1000, 0.5, 0.8, 100000, 30);
        popControl ga1 = new popControl(10000.0000010, new Rosenbrock(), 1000, 0.5, 0.8, 100000, 30);
        ga1.InitializePopulation();
        ga1.Evolve();*/

        /*ArrayList<Double> d = new ArrayList<>();
        for (int i = 0; i <= 30 -1; i++)
        {
           // d.add(r.nextDouble());
            d.add(0.01);
        }
        System.out.println(f.evaluate(d));*/
        /**Population p1 = new Population(600.0, new Rosenbrock(), 99, 0.8, 0.5, 100000, 30);
        p1.InitializePopulation();
        //p1.display();
        p1.Evolve();**/

       /* for (int i = 0; i <= 1000; i++)
        {
            Double curM = Randomness.NormalRandomNumber(100.0);
            System.out.println(curM);
        }*/

    }
   // public Population(Double range, ContinuousFunction cf, Integer populationsize, Double cr, Double mr, Integer generations, Integer dimensions) {
    //(Double range, ContinuousFunction cf, Integer populationsize, Double cr, Double mr, Integer generations, Integer dimensions)


}
