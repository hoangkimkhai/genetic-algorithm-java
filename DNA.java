package stringga;

import java.util.Arrays;
import java.util.Random;

public class DNA implements Common{
    public char[] genes;
    public int genesLength;
    public double fitness;
    public DNA() {
        this.genesLength = target.length;
        genes = new char[genesLength];
        for(int i = 0; i < genesLength;i++){
            char c = rndChar();
            genes[i] = c;
        }
    }
    public DNA(int i) {
        genesLength = target.length;
        genes = new char[target.length];

    }
    private  char rndChar () {
        int rnd = (int) (Math.random() * 52); // or use Random or whatever
        char base = (rnd < 26) ? 'A' : 'a';
        return (char) (base + rnd % 26);
    }
    public void calcFitness(){
        double score = 0;
        for(int i = 0; i < genesLength; i++){
            if(genes[i] == target[i]){
                score++;
            }
        }
        score = score/genesLength;
        this.fitness = score;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < genesLength; i++){
            stringBuilder.append(genes[i]);
        }
        return stringBuilder.toString();
    }

    public DNA crossOver(DNA partnerB) {
        Random r = new Random();
        DNA child = new DNA(1);
        int midpoint = r.nextInt(genesLength);
        for(int i = 0; i < this.genesLength; i++){
            if(i > midpoint) child.genes[i] = this.genes[i];
            else child.genes[i] = partnerB.genes[i];
        }
        return child;
    }

    public void mutate(double mutationrate) {
        Random r = new Random();

        for(int i =0 ; i < this.genesLength; i++){
            if(r.nextDouble() < mutationrate){
                this.genes[i] = rndChar();
            }
        }
    }
}
