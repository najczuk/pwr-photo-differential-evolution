package pl.najczuk.differential_evolution;

import pl.najczuk.differential_evolution.image_processing.helpers.ImageChanger;
import pl.najczuk.differential_evolution.population.Genotype;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
//        String sourcePath = "D:\\workspace\\pwr\\pwr-photo-differential-evolution\\photos\\podworko2.jpg";
        String sourcePath = "D:\\workspace\\pwr\\pwr-photo-differential-evolution\\photos\\podworko2_out.jpg";
        String dstPath = "D:\\workspace\\pwr\\pwr-photo-differential-evolution\\photos\\podworko3_out.jpg";
        try {

            File inputFile = new File(sourcePath);
            File outputFile = new File(dstPath);
            BufferedImage inImage= ImageIO.read(inputFile);

//            Genotype genotype = new Genotype(0.5f,0.5f,0.5f,0.5f,0.5f,0,0,0);
            Genotype genotype = new Genotype();
            System.out.println(genotype);
            BufferedImage outImage = ImageChanger.changeImageGenotype(inImage,genotype);
            ImageIO.write(outImage,"jpg",outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
