package pl.najczuk.differential_evolution.gui;

import pl.najczuk.differential_evolution.image_processing.ImageChanger;
import pl.najczuk.differential_evolution.population.Genotype;
import pl.najczuk.differential_evolution.population.Population;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * User: Adrian
 * Date: 1/10/2015
 * Time: 23:19
 */
public class GUI {
    private JButton yesButton;
    private JButton noButton;
    private JTextField cMin;
    private JTextField cMax;
    private JTextField bMin;
    private JTextField bMax;
    private JTextField rgbMin;
    private JTextField rgbMax;
    private JTextField gammaMin;
    private JTextField gammaMax;
    private JButton endButton;
    private JTextField brigScale;
    private JTextField gbScale;
    private JTextField ggScale;
    private JTextField grScale;
    private JTextField bscale;
    private JTextField gScale;
    private JTextField rScale;
    private JTextField cScale;
    private JTextField mutThreshold;
    private JPanel parametersPane;
    private JPanel acceptPane;
    private JPanel maxMinPane;
    private JPanel scalePane;
    private JPanel mainPane;
    private JPanel pircturesPane;
    private JScrollPane leftPicScrollPane;
    private JScrollPane rightPicScrollPane;
    private JButton readFile;
    private JFileChooser fc;

    BufferedImage originalImage,currentImage,tmpImage;
    Population population; Genotype mutant;

    //confFields
    public double
            C_MIN = 0.7, C_MAX = 1.3,
            B_MIN = -20, B_MAX = 20,
            RGB_MIN = -20, RGB_MAX = 20,
            GAMMA_MIN = 0.9, GAMMA_MAX = 1.1,
            CROSSOVER_THRESHOLD = 0.5;
    public double mutationControlConstants[] = {0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.7, 0.7};

    public GUI() {
        this.population = new Population();
        this.mutant = population.evolve();
        fc = new JFileChooser();
        fc.setCurrentDirectory(new File("photos"));
        cMin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                C_MIN = Double.valueOf(cMin.getText());
            }
        });
        cMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                C_MAX = Double.valueOf(cMax.getText());
            }
        });

        bMin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                B_MIN = Double.valueOf(bMin.getText());
            }
        });
        bMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                B_MAX = Double.valueOf(bMax.getText());
            }
        });
        rgbMin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RGB_MIN = Double.valueOf(rgbMin.getText());
            }
        });
        rgbMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RGB_MAX = Double.valueOf(rgbMax.getText());
            }
        });
        gammaMin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GAMMA_MIN = Double.valueOf(gammaMin.getText());
            }
        });
        gammaMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GAMMA_MAX = Double.valueOf(gammaMax.getText());
            }
        });
        mutThreshold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CROSSOVER_THRESHOLD = Double.valueOf(mutThreshold.getText());
            }
        });
        brigScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mutationControlConstants[0] = Double.valueOf(brigScale.getText());
            }
        });
        cScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mutationControlConstants[1] = Double.valueOf(cScale.getText());
            }
        });
        rScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mutationControlConstants[2] = Double.valueOf(rScale.getText());
            }
        });
        gScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mutationControlConstants[3] = Double.valueOf(gScale.getText());
            }
        });
        bscale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mutationControlConstants[4] = Double.valueOf(bscale.getText());
            }
        });
        grScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mutationControlConstants[5] = Double.valueOf(grScale.getText());
            }
        });
        ggScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mutationControlConstants[6] = Double.valueOf(ggScale.getText());
            }
        });
        gbScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mutationControlConstants[7] = Double.valueOf(gbScale.getText());
            }
        });
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mutant = population.evolve();
                currentImage = tmpImage;
                drawImage(currentImage, leftPicScrollPane);
                tmpImage = ImageChanger.changeImageGenotype(currentImage,mutant);
                drawImage(tmpImage,rightPicScrollPane);
                population.extendPopulation(mutant);

            }
        });
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mutant = population.evolve();
                tmpImage = ImageChanger.changeImageGenotype(currentImage,mutant);
                drawImage(tmpImage,rightPicScrollPane);
            }
        });
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        readFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(mainPane);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                        originalImage = ImageIO.read(file);
                        currentImage = ImageIO.read(file);
                        drawImage(currentImage, leftPicScrollPane);
                        tmpImage = ImageChanger.changeImageGenotype(originalImage,mutant);
                        drawImage(tmpImage,rightPicScrollPane);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                }
            }
        });
    }

    private void mutate(){

    }

    private void drawImage(BufferedImage img, JScrollPane pane){
        pane.getGraphics().drawImage(img,0,0,null);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().mainPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


//        Population population = new Population();
//        Genotype mutant
    }
}
