package pl.najczuk.differential_evolution;

import pl.najczuk.differential_evolution.image_processing.ImageChanger;
import pl.najczuk.differential_evolution.population.Genotype;
import pl.najczuk.differential_evolution.population.Population;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String sourcePath = "photos\\img.jpg";
        String lastOutputPath = "photos\\img_last.jpg";
        String currentOutputPath = "photos\\img_current.jpg";
        try {
//
            File inputFile = new File(sourcePath);
            File currentOutputFile = new File(currentOutputPath);
            File lastOutputFile = new File(lastOutputPath);

            BufferedImage inImage= ImageIO.read(inputFile);
            BufferedImage outImage = null;

            Population population = new Population();
            Genotype mutant;

            boolean userDecision = false;
            do {
                mutant=population.evolve();
                outImage = ImageChanger.changeImageGenotype(inImage,mutant);
                ImageIO.write(outImage,"jpg",currentOutputFile);
                userDecision = population.getUserDecision();
                if(userDecision){
                    population.extendPopulation(mutant);
                    ImageIO.write(outImage,"jpg",lastOutputFile);
                }
            } while (population.shouldContinue());
//
        } catch (IOException e) {
            e.printStackTrace();
        }





    }
}
