package pl.najczuk.differential_evolution.population;

import pl.najczuk.differential_evolution.image_processing.ImageChanger;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * User: Adrian
 * Date: 12/31/2014
 * Time: 10:24
 */
public class Population {
    ArrayList<Genotype> genotypes;
    private double CROSSOVER_THRESHOLD = 0.5;
    public double
            C_MIN = 0.7, C_MAX = 1.3,
            B_MIN = -20, B_MAX = 20,
            RGB_MIN = -20, RGB_MAX = 20,
            GAMMA_MIN = 0.9, GAMMA_MAX = 1.1;
    public double mutationControlConstants[]= {0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1};

    public Population() {
        genotypes = new ArrayList<Genotype>();
    }

    public void setParameters(){

    }

    public void setParameters(double CROSSOVER_THRESHOLD, double c_MIN, double c_MAX, double b_MIN, double b_MAX,
                              double RGB_MIN, double RGB_MAX, double GAMMA_MIN, double GAMMA_MAX, double[] mutationControlConstants) {
        this.CROSSOVER_THRESHOLD = CROSSOVER_THRESHOLD;
        C_MIN = c_MIN;
        C_MAX = c_MAX;
        B_MIN = b_MIN;
        B_MAX = b_MAX;
        this.RGB_MIN = RGB_MIN;
        this.RGB_MAX = RGB_MAX;
        this.GAMMA_MIN = GAMMA_MIN;
        this.GAMMA_MAX = GAMMA_MAX;
        this.mutationControlConstants = mutationControlConstants;
        System.out.println("CROSSOVER_THRESHOLD = [" + CROSSOVER_THRESHOLD + "], c_MIN = [" + c_MIN + "], c_MAX = ["
                + c_MAX + "], b_MIN = [" + b_MIN + "], b_MAX = [" + b_MAX + "], RGB_MIN = [" + RGB_MIN + "], RGB_MAX " +
                "= [" + RGB_MAX + "], GAMMA_MIN = [" + GAMMA_MIN + "], GAMMA_MAX = [" + GAMMA_MAX + "], " +
                "mutationControlConstants = [" + Arrays.toString(mutationControlConstants) + "]");
    }

    public void extendPopulation(Genotype genotype){
        genotypes.add(genotype);
    }

    public Genotype evolve() {
        Genotype parent, child;
        parent = getLastGenotype(); //get last genotype as a parent
        child = generateChild(parent);
        return child;
    }

    private Genotype getLastGenotype() {
        Genotype parent;

        if (genotypes.size() < 1)
            parent = new Genotype(this);
        else
            parent = genotypes.get(genotypes.size() - 1); //get last genotype as a parent
        return parent;
    }

    private Genotype generateChild(Genotype parent) {
        Genotype g1, g2, g3, child, mutant;
        if (genotypes.size() < 3) {
            g1 = new Genotype(this);
            g2 = new Genotype(this);
            g3 = new Genotype(this);
            mutant = new Genotype(g1, g2, g3,this);
            child = new Genotype(parent, mutant, CROSSOVER_THRESHOLD,this);
        } else {
            g1 = getRandomGenotype();
            g2 = getRandomGenotype();
            g3 = getRandomGenotype();
            mutant = new Genotype(g1, g2, g3,this);
            child = new Genotype(parent, mutant, CROSSOVER_THRESHOLD,this);
        }
        return child;
    }

    private Genotype getRandomGenotype() {
        Random random = new Random();
        int genotypeIndex = random.nextInt(genotypes.size());
        return genotypes.get(genotypeIndex);

    }

    public boolean getUserDecision() {
        System.out.println("Czy podoba Ci się wynik (T/N)?");
        Scanner in = new Scanner(System.in);
        if (in.next().equals("T"))
            return true;
        else return false;
    }
    public boolean shouldContinue() {
        System.out.println("Czy kontunuować? (T/N)?");
        Scanner in = new Scanner(System.in);
        if (in.next().equals("T"))
            return true;
        else return false;
    }


}
