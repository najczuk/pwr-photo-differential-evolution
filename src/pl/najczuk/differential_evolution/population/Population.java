package pl.najczuk.differential_evolution.population;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * User: Adrian
 * Date: 12/31/2014
 * Time: 10:24
 */
public class Population {
    ArrayList<Genotype> genotypes;
    private double fVector[];

    public Population() {
    }

    public BufferedImage generateMutant(){
        return  null; //TODO
    }

    private Genotype crossover(){
        return  null; //TODO
    }

    private Genotype generateDifferentialGenotype(){
        return null; //TODO
    }

    private Genotype generateAdditiveGenotype(Genotype differentialGenotype){
        return null;
    }


}
