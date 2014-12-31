package pl.najczuk.differential_evolution.image_processing.helpers;

import pl.najczuk.differential_evolution.population.Genotype;

import java.awt.*;
import java.awt.image.*;

public class ImageChanger {

    public static BufferedImage changeImageGenotype(BufferedImage inImage, Genotype genotype) {

        float contrast = denormalize(genotype.getContrast(), Genotype.C_MIN, Genotype.C_MAX);
        float brightness = denormalize(genotype.getContrast(), Genotype.B_MIN, Genotype.B_MAX);
        float r = denormalize(genotype.getR(), Genotype.RGB_MIN, Genotype.RGB_MAX);
        float g = denormalize(genotype.getG(), Genotype.RGB_MIN, Genotype.RGB_MAX);
        float b = denormalize(genotype.getB(), Genotype.RGB_MIN, Genotype.RGB_MAX);
        double gR = denormalize(genotype.getGammaR(), Genotype.GAMMA_MIN, Genotype.GAMMA_MAX);
        double gG = denormalize(genotype.getGammaG(), Genotype.GAMMA_MIN, Genotype.GAMMA_MAX);
        double gB = denormalize(genotype.getGammaB(), Genotype.GAMMA_MIN, Genotype.GAMMA_MAX);

        BufferedImage imageToProcess = deepCopy(inImage);
        return changeImage(imageToProcess,contrast,brightness,r,g,b,gR,gG,gB);
    }

    private static float denormalize(double value, double min, double max) {
        float denormalizedValue = (float) (value * (max - min) + min);
        return denormalizedValue;
    }

    private static BufferedImage changeImage(BufferedImage inImage,float contrast, float brightness, float r, float g, float b, double gR,
                                             double gG, double gB) {
        System.out.println("contrast = [" + contrast + "], brightness = [" + brightness + "], r = [" + r + "], g = [" + g + "], b = [" + b + "], gR = [" + gR + "], gG = [" + gG + "], gB = [" + gB + "]");

        float[] factors = {contrast, contrast, contrast};
        float[] offsets = {brightness + r, brightness + g, brightness + b};
        RescaleOp CBRGBFilter = new RescaleOp(factors, offsets, null);


        BufferedImage gammaImage = changeGammaRGB(inImage,gR,gG,gB);
        CBRGBFilter.filter(inImage,inImage);
        return inImage;
    }

    private static BufferedImage changeGammaRGB(BufferedImage inImage, double gammaR, double gammaG, double gammaB) {
        System.out.println("gammaR = [" + gammaR + "], gammaG = [" + gammaG + "], gammaB = [" + gammaB + "]");
        int w = inImage.getWidth();
        int h = inImage.getHeight();
        int r, g, b;
        Color color;

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {

                color = new Color(inImage.getRGB(i, j));


                r = (int) Math.pow((double) color.getRed(), gammaR);
                g = (int) Math.pow((double) color.getGreen(), gammaG);
                b = (int) Math.pow((double) color.getBlue(), gammaB);


                //r,g,b values which are out of the range 0 to 255 should set to 0 or 255
                if (r >= 256) {
                    r = 255;
                } else if (r < 0) {
                    r = 0;
                }

                if (g >= 256) {
                    g = 255;
                } else if (g < 0) {
                    g = 0;
                }

                if (b >= 256) {
                    b = 255;
                } else if (b < 0) {
                    b = 0;
                }

                inImage.setRGB(i, j, new Color(r, g, b).getRGB());
            }
        }

        return inImage;
    }

    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}