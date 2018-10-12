package deVilliers.PSO;

import java.util.ArrayList;

public class Particles {
    ArrayList<Double> X = new ArrayList<>();//current X coordinates
    ArrayList<Double> V = new ArrayList<>();//current Velocity of Particle
    ArrayList<Double> xY = new ArrayList<>();//Personal Best X coordinates
    ArrayList<Double> R1 = new ArrayList<>();//Random Vector U(0,1)
    ArrayList<Double> R2 = new ArrayList<>();//Random Vector U(0,1)
    Double w;//inertia weight
    Double y;
}
