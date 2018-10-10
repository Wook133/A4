package deVilliers.DE;

import functions.ContinuousFunction;
import functions.Griewank;
import functions.Rosenbrock;

import java.util.Random;

public class bdeRunner {
    public static void main(String[] args) {

        Random r = new Random();
        ContinuousFunction f = new Griewank();
        //popControl ga1 = new popControl(600.0, new Whitley(), 1000, 0.5, 0.8, 100000, 30);
        BasicDE bde = new BasicDE(100.0, new Rosenbrock(), 1000, 0.5, 0.8, 100000, 30, 0.3);
        bde.Initialize();
        bde.differentialEvolve();
    }
}
