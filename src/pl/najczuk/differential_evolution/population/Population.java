package pl.najczuk.differential_evolution.population;

import pl.najczuk.differential_evolution.image_processing.ImageChanger;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * User: Adrian
 * Date: 12/31/2014
 * Time: 10:24
 */
public class Population {
    ArrayList<Genotype> genotypes;
    private static double CROSSOVER_THRESHOLD = 0.5;

    public Population() {
        genotypes = new ArrayList<Genotype>();
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
            parent = new Genotype();
        else
            parent = genotypes.get(genotypes.size() - 1); //get last genotype as a parent
        return parent;
    }

    private Genotype generateChild(Genotype parent) {
        Genotype g1, g2, g3, child, mutant;
        if (genotypes.size() < 3) {
            g1 = new Genotype();
            g2 = new Genotype();
            g3 = new Genotype();
            mutant = new Genotype(g1, g2, g3);
            child = new Genotype(parent, mutant, CROSSOVER_THRESHOLD);
        } else {
            g1 = getRandomGenotype();
            g2 = getRandomGenotype();
            g3 = getRandomGenotype();
            mutant = new Genotype(g1, g2, g3);
            child = new Genotype(parent, mutant, CROSSOVER_THRESHOLD);
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
