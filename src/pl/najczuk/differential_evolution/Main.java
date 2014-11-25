package pl.najczuk.differential_evolution;

import pl.najczuk.differential_evolution.image_processing.helpers.ImageChanger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String sourcePath = "D:\\workspace\\pwr\\pwr-photo-differential-evolution\\photos\\podworko2.jpg";
        String dstPath = "D:\\workspace\\pwr\\pwr-photo-differential-evolution\\photos\\podworko2_out.jpg";
        try {

            File inputFile = new File(sourcePath);
            File outputFile = new File(dstPath);
            BufferedImage inImage= ImageIO.read(inputFile);
//            BufferedImage outImage = ImageChanger.changeBrightness(inImage,40);
//            BufferedImage outImage = ImageChanger.changeContrast(inImage,1.7f);
//            BufferedImage outImage = ImageChanger.changeR(inImage,-300f);
//            BufferedImage outImage = ImageChanger.changeG(inImage,-300f);
//            BufferedImage outImage = ImageChanger.changeB(inImage,-30f);
            // gamma ranges [0.7,1.3]
            BufferedImage outImage = ImageChanger.changeGammaR(inImage,1.4);
            ImageIO.write(outImage,"jpg",outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
