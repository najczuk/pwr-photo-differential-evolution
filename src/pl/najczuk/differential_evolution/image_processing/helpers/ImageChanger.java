package pl.najczuk.differential_evolution.image_processing.helpers;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class ImageChanger {



    public static BufferedImage changeContrast(BufferedImage inImage, float scaleFactor) {

        int w = inImage.getWidth();
        int h = inImage.getHeight();

        BufferedImage outImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        RescaleOp op = new RescaleOp(scaleFactor,0.0f,null);
        outImage=op.filter(inImage,null);

        return outImage;
    }
    public static BufferedImage changeBrightness(BufferedImage inImage, float offset) {

        int w = inImage.getWidth();
        int h = inImage.getHeight();

        BufferedImage outImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        RescaleOp op = new RescaleOp(1.0f,offset,null);
        outImage=op.filter(inImage,null);

        return outImage;
    }
    public static BufferedImage changeR(BufferedImage inImage, float offset) {

        int w = inImage.getWidth();
        int h = inImage.getHeight();
        float[]factors = {1.0f,1.0f,1.0f};
        float[]offsets = {offset,1.0f,1.0f};

        BufferedImage outImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        RescaleOp op = new RescaleOp(factors,offsets,null);
        outImage=op.filter(inImage,null);

        return outImage;
    }
    public static BufferedImage changeG(BufferedImage inImage, float offset) {

        int w = inImage.getWidth();
        int h = inImage.getHeight();
        float[]factors = {1.0f,1.0f,1.0f};
        float[]offsets = {1.0f,offset,1.0f};

        BufferedImage outImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        RescaleOp op = new RescaleOp(factors,offsets,null);
        outImage=op.filter(inImage,null);

        return outImage;
    }
    public static BufferedImage changeB(BufferedImage inImage, float offset) {

        int w = inImage.getWidth();
        int h = inImage.getHeight();
        float[]factors = {1.0f,1.0f,1.0f};
        float[]offsets = {1.0f,1.0f,offset};

        BufferedImage outImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        RescaleOp op = new RescaleOp(factors,offsets,null);
        outImage=op.filter(inImage,null);

        return outImage;
    }


    public static BufferedImage changeGammaR(BufferedImage inImage, double gamma) {

        int w = inImage.getWidth();
        int h = inImage.getHeight();

        BufferedImage outImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {

                Color color = new Color(inImage.getRGB(i, j));

                int r, g, b;

                r = (int)Math.pow((double)color.getRed(),gamma);
                g = color.getGreen();
                b = color.getBlue();


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

                outImage.setRGB(i, j, new Color(r, g, b).getRGB());

            }
        }

        return outImage;
    }
    public static BufferedImage changeGammaG(BufferedImage inImage, double gamma) {

        int w = inImage.getWidth();
        int h = inImage.getHeight();

        BufferedImage outImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {

                Color color = new Color(inImage.getRGB(i, j));

                int r, g, b;

                r = color.getRed();
                g = (int)Math.pow((double)color.getGreen(),gamma);
                b = color.getBlue();


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

                outImage.setRGB(i, j, new Color(r, g, b).getRGB());

            }
        }

        return outImage;
    }
    public static BufferedImage changeGammaB(BufferedImage inImage, double gamma) {

        int w = inImage.getWidth();
        int h = inImage.getHeight();

        BufferedImage outImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {

                Color color = new Color(inImage.getRGB(i, j));

                int r, g, b;

                r = color.getRed();
                g = color.getGreen();
                b =(int)Math.pow((double)color.getBlue(),gamma);


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

                outImage.setRGB(i, j, new Color(r, g, b).getRGB());

            }
        }

        return outImage;
    }
}