package stringga;

import javafx.scene.paint.PhongMaterial;
import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        int geCounts = 0;

        Population pop = new Population();
        pop.initializePop();
        pop.calcFitness();
        double maxFit = pop.maxFitness;
        System.out.println("Generation " + geCounts + " maxFitness: " + pop.maxFitness);
        while (!pop.isFinished()) {
            pop.naturalSelection();

            pop.generateNextGeneration();

            pop.calcFitness();
            if (pop.maxFitness > maxFit) {
                maxFit = pop.maxFitness;
                System.out.println("Generation " + geCounts + " maxFitness: " + pop.maxFitness);
                pop.display();
            }
            geCounts++;
        }
        pop.display();
    }
}
