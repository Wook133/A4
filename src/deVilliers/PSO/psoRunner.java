package deVilliers.PSO;

import functions.ContinuousFunction;
import functions.Griewank;
import functions.Whitley;

import java.util.Random;

public class psoRunner {
    public static void main(String[] args) {
        /*psoPopulation pso = new psoPopulation(1.0, new Whitley(), 1000, 0.6, 0.4, 1000, 30, 0.3);
        pso.InitializePopulation();
        pso.Swarm();*/
        //AgentPopulation(Double range, Integer dimensions, Integer populationSize, Integer epochs, ContinuousFunction f, Double inertiaW, Double c1, Double c2)
        AgentPopulation aPSO = new AgentPopulation(100.0, 30, 1000, 10, new Whitley(), 0.3, 0.4, 0.6);
        aPSO.Swarm();
    }
}
