package deVilliers.DE;

import functions.*;

import java.util.Random;

public class bdeRunner {
    public static void main(String[] args) {

        Random r = new Random();
        ContinuousFunction f = new Griewank();
        //popControl ga1 = new popControl(600.0, new Whitley(), 1000, 0.5, 0.8, 100000, 30);
        BasicDE bde = new BasicDE(1000.0, new Whitley(), 1000, 0.7, 0.1, 1000, 30, 0.3);
        bde.Initialize();
        bde.differentialEvolve();
    }
}
