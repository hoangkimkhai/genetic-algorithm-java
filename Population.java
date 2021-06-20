package stringga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population implements Common{
    private DNA[] pop;
    private List<DNA> matingPool = new ArrayList<>();
    double maxFitness;
    private int maxFitnessIndex = 0;
    public void initializePop(){
        pop = new DNA[popsize];
        for(int i = 0; i  < popsize;i++){
            pop[i] = new DNA();
        }
    }
    public void calcFitness(){
        for(int i = 0; i < popsize;i++){
            pop[i].calcFitness();
            if(pop[i].fitness >= maxFitness){
                maxFitness = pop[i].fitness;
                maxFitnessIndex = i;
            }
        }
    }
    public void naturalSelection(){
        for(int i = 0; i < popsize; i++){
            DNA current = pop[i];
            double proportion = current.fitness/maxFitness;
            int n = (int) Math.floor(proportion);
            for(int k = 0; k < n; k++){
                matingPool.add(current);
            }
        }

    }
    public void generateNextGeneration(){
        int low = 0;
        int high = matingPool.size();
        for(int i = 0; i < popsize; i++){
            Random r = new Random();
            int a = r.nextInt(high-low) + low;
            int b = r.nextInt(high-low) + low;
            DNA partnerA = matingPool.get(a);
            DNA partnerB = matingPool.get(b);
            DNA child = partnerA.crossOver(partnerB);
            child.mutate(mutationrate);
            this.pop[i] = child;
        }
    }


    public boolean isFinished(){
        return this.maxFitness >= 1;

    }
    public void display(){
        System.out.println(pop[maxFitnessIndex]);
    }
}
