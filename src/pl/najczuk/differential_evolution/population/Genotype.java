package pl.najczuk.differential_evolution.population;

import java.util.Random;

/**
 * User: Adrian
 * Date: 12/31/2014
 * Time: 10:24
 */
public class Genotype {
    private double brightness, contrast, r, g, b;
    private double gammaR, gammaG, gammaB;
    public static final int DIFFERENTIAL_CROSSOVER = 0, ADDITIVE_CROSSOVER = 1;
    public Population genotypePopulation;


    /**
     * Constructor for given genotype parameters
     *
     * @param brightness
     * @param contrast
     * @param r
     * @param g
     * @param b
     * @param gammaR
     * @param gammaG
     * @param gammaB
     */
    public Genotype(float brightness, float contrast, float r, float g, float b, double gammaR, double gammaG, double
            gammaB,Population genotypePopulation) {

        this.genotypePopulation = genotypePopulation;
        this.brightness = brightness;
        this.contrast = contrast;
        this.r = r;
        this.g = g;
        this.b = b;
        this.gammaR = gammaR;
        this.gammaG = gammaG;
        this.gammaB = gammaB;
    }

    /**
     * Constructor for random genotype using gaussian distribution
     */
    public Genotype(Population genotypePopulation) {

        this.genotypePopulation = genotypePopulation;
        this.brightness = getRandomGaussianValue();
        this.contrast = getRandomGaussianValue();
        this.r = getRandomGaussianValue();
        this.g = getRandomGaussianValue();
        this.b = getRandomGaussianValue();
        this.gammaR = getRandomGaussianValue();
        this.gammaG = getRandomGaussianValue();
        this.gammaB = getRandomGaussianValue();

    }

    /**
     * @param g1   first genotype used for creation from crossover
     * @param g2   second genotype used for creation from crossover
     * @param flag type of crossover to be used DIFFERENTIAL_CROSSOVER or ADDITIVE_CROSSOVER
     */
    public Genotype(Genotype g1, Genotype g2, int flag,Population genotypePopulation) {
        this.genotypePopulation = genotypePopulation;
        this.brightness = flag == ADDITIVE_CROSSOVER ? addGenomes(g1.getBrightness(), g2.getBrightness())
                : diffGenomes(g1.getBrightness(), g2.getBrightness(),0);

        this.contrast = flag == ADDITIVE_CROSSOVER ? addGenomes(g1.getContrast(), g2.getContrast())
                : diffGenomes(g1.getContrast(), g2.getContrast(),1);

        this.r = flag == ADDITIVE_CROSSOVER ? addGenomes(g1.getR(), g2.getR())
                : diffGenomes(g1.getR(), g2.getR(),2);

        this.g = flag == ADDITIVE_CROSSOVER ? addGenomes(g1.getG(), g2.getG())
                : diffGenomes(g1.getG(), g2.getG(),3);

        this.b = flag == ADDITIVE_CROSSOVER ? addGenomes(g1.getB(), g2.getB())
                : diffGenomes(g1.getB(), g2.getB(),4);

        this.gammaR = flag == ADDITIVE_CROSSOVER ? addGenomes(g1.getGammaR(), g2.getGammaR())
                : diffGenomes(g1.getGammaR(), g2.getGammaR(),5);

        this.gammaG = flag == ADDITIVE_CROSSOVER ? addGenomes(g1.getGammaG(), g2.getGammaG())
                : diffGenomes(g1.getGammaG(), g2.getGammaG(),6);

        this.gammaB = flag == ADDITIVE_CROSSOVER ? addGenomes(g1.getGammaB(), g2.getGammaB())
                : diffGenomes(g1.getGammaB(), g2.getGammaB(),7);
    }

    public Genotype(Genotype g1, Genotype g2, double crossoverThreshold, Population genotypePopulation) {

        this.genotypePopulation = genotypePopulation;
        this.brightness = isGenomeCrossing(crossoverThreshold) ? g2.getBrightness() : g1.getBrightness();
        this.contrast = isGenomeCrossing(crossoverThreshold) ? g2.getContrast() : g1.getContrast();
        this.r = isGenomeCrossing(crossoverThreshold) ? g2.getR() : g1.getR();
        this.g = isGenomeCrossing(crossoverThreshold) ? g2.getG() : g1.getG();
        this.b = isGenomeCrossing(crossoverThreshold) ? g2.getB() : g1.getB();
        this.gammaR = isGenomeCrossing(crossoverThreshold) ? g2.getGammaR() : g1.getGammaR();
        this.gammaG = isGenomeCrossing(crossoverThreshold) ? g2.getGammaG() : g1.getGammaG();
        this.gammaB = isGenomeCrossing(crossoverThreshold) ? g2.getGammaB() : g1.getGammaB();
    }

    public Genotype(Genotype g1,Genotype g2,Genotype g3,Population genotypePopulation){

        this.genotypePopulation = genotypePopulation;
        Genotype diffGenotype = new Genotype(g2,g3,DIFFERENTIAL_CROSSOVER,genotypePopulation);
        Genotype mutantGenotype = new Genotype(g1,diffGenotype,ADDITIVE_CROSSOVER,genotypePopulation);

        this.brightness = mutantGenotype.getBrightness();
        this.contrast = mutantGenotype.getContrast();
        this.r = mutantGenotype.getR();
        this.g = mutantGenotype.getG();
        this.b = mutantGenotype.getB();
        this.gammaR = mutantGenotype.getGammaR();
        this.gammaG = mutantGenotype.getGammaG();
        this.gammaB = mutantGenotype.getGammaB();
    }

    private boolean isGenomeCrossing(double threshold) {
        Random random = new Random();
        return random.nextDouble() > threshold;
    }

    private double getRandomGaussianValue() {
        Random random = new Random();
        double value = -1;
        while (value < 0 || value > 1) {
            value = random.nextGaussian() * 0.5 + 0.5;
        }
        return value;
    }

    private double addGenomes(double g1, double g2) {
        double sum = g1 + g2 > 1 ? 1 : g1 + g2;
        return sum;
    }

    private double diffGenomes(double g1, double g2,int genomeIndex) {
        double diff = (g1 - g2)* genotypePopulation.mutationControlConstants[genomeIndex] < 0 ? 0 : (g1 - g2)*
                genotypePopulation.mutationControlConstants[genomeIndex];
        return diff;
    }

    @Override
    public String toString() {
        return "Genotype{" +
                "brightness=" + brightness +
                ", contrast=" + contrast +
                ", r=" + r +
                ", g=" + g +
                ", b=" + b +
                ", gammaR=" + gammaR +
                ", gammaG=" + gammaG +
                ", gammaB=" + gammaB +
                '}';
    }

    public double getBrightness() {
        return brightness;
    }

    public double getContrast() {
        return contrast;
    }

    public double getR() {
        return r;
    }

    public double getG() {
        return g;
    }

    public double getB() {
        return b;
    }

    public double getGammaR() {
        return gammaR;
    }

    public double getGammaG() {
        return gammaG;
    }

    public double getGammaB() {
        return gammaB;
    }

    public Population getGenotypePopulation() {
        return genotypePopulation;
    }
}
