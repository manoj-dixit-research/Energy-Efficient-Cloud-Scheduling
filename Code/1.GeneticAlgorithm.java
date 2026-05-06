package Code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeMap;

public class GeneticAlgorithm {

    // Define the size of the population
    private static int POPULATION_SIZE;
    public static double end3;
    public static Random r = new Random();
    // Define the maximum number of generations
    private static final int MAX_GENERATIONS = 10;

    // Define the mutation rate
    private static final double MUTATION_RATE = 0.1;

    // Define the fitness function
    private static double fitness(String chromosome,TreeMap<Integer, ArrayList<Double>> slaTabel, ArrayList<ArrayList<Double>> preData) {
        // Here, the fitness function is simply the sum of the binary digits in the chromosome
        ArrayList<Double> OF =  new ArrayList<>();
        for(int i=0;i<slaTabel.size();i++){
            ArrayList<Double> slaT = new ArrayList<>(slaTabel.get(i));
            double req =0;
            for(int j=0;j<preData.get(i).size();j++){
                req += preData.get(i).get(j);
            }
            ArrayList<Double> NSLO = Normalize.main(slaT);
            double nslo =0;
            for(int k=0;k<NSLO.size();k++){
                nslo+=NSLO.get(k);
            }
            double of = Math.sqrt((req*nslo)-(Double.parseDouble(chromosome)*nslo));
            OF.add(of/1e6);
        }
        double minof = Collections.min(OF);
        double fitness = 0;
        for (int i = 0; i < chromosome.length(); i++) {
            if (chromosome.charAt(i) == '1') {
                fitness++;
            }
        }
        fitness = minof;
        return fitness;
    }

    // Define the crossover function
    
    private static String crossover(ArrayList<Integer> parent1, ArrayList<ArrayList<Integer>> parent2) {
        // One-point crossover: pick a random point in the chromosome, and swap the tails of the parents
        
        int crossoverPoint1 = r.nextInt(parent1.size());
        int crossoverPoint2 = r.nextInt(parent2.size());
        return Integer.toString(parent1.get(crossoverPoint1) + parent2.get(crossoverPoint1).get(crossoverPoint2));
    }

    // Define the mutation function
    private static String mutation(String chromosome) {
        // Flip one bit in the chromosome with probability MUTATION_RATE
        Random random = new Random();
        StringBuilder mutatedChromosome = new StringBuilder(chromosome);
        for (int i = 0; i < mutatedChromosome.length(); i++) {
            if (random.nextDouble() < MUTATION_RATE) {
                mutatedChromosome.setCharAt(i, (mutatedChromosome.charAt(i) == '0') ? '1' : '0');
            }
        }
        return mutatedChromosome.toString();
    }
    public static ArrayList<ArrayList<Integer>> main(int popsize, ArrayList<ArrayList<Double>> preData, TreeMap<Integer, ArrayList<Double>> slaTabel) {
        
        ArrayList<ArrayList<Integer>> ClustGrp = new ArrayList<>();
        POPULATION_SIZE =  popsize;
        // Initialize the population
        String[] population = new String[POPULATION_SIZE];
        for (int i = 0; i < POPULATION_SIZE; i++) {
            StringBuilder chromosome = new StringBuilder();
            for (int j = 0; j < preData.get(i).size(); j++) {
                chromosome.append((Math.random() < 0.5) ? "0" : "1");
            }
            population[i] = chromosome.toString();
        }
        Random r = new Random();
        
        String[] newPopulation = new String[POPULATION_SIZE];
        // Evolve the population
        for (int generation = 1; generation <= MAX_GENERATIONS; generation++) {
            // Compute the fitness of each individual in the population
            double[] fitnessValues = new double[POPULATION_SIZE];
            for (int i = 0; i < POPULATION_SIZE; i++) {
                fitnessValues[i] = fitness(population[i],slaTabel,preData);
            }

            // Select the parents for the next generation
                int clustno = 2; 
                ArrayList<ArrayList<Double>> popdata =  new ArrayList<>();
                for(int i=0;i<population.length;i++){
                    ArrayList<Double> temp =  new ArrayList<>();double v=0;
                    for(int j=0;j<population[i].length();j++){
                        v=v+(double) Character.getNumericValue(population[i].charAt(j));
                    }
                    temp.add(v);
                    popdata.add(temp);
                }
                KMeans KM =  new KMeans(popdata, clustno);
                KM.fit();
                ArrayList<Integer> clusters = KM.getClusters();
                ArrayList<ArrayList<Integer>> clustgrp = new ArrayList<>();
                for(int cl=0;cl<clustno;cl++){
                    clustgrp.add(new ArrayList<>());
                }// creating the cluster groups
                for(int i=0;i<clusters.size();i++){
                    if(clusters.get(i)==0 && i<popdata.size()){
                        clustgrp.get(0).add(i);
                    }else if(clusters.get(i)==1 && i<popdata.size()){
                        clustgrp.get(1).add(i);
                    }
                }
                
                int chIdx =0;// Finding the cluster head
                ArrayList<Integer> CHidx = new ArrayList<>();
                for(int i=0;i<clustgrp.size();i++){ 
                    double minval =  preData.get(i).get(3);
                    for(int j=0;j<clustgrp.get(i).size();j++){ 
                            int idx =  clustgrp.get(i).get(j);
                            double val =  preData.get(idx).get(3);
                            if(val < minval){
                                minval = val;
                                chIdx = idx;
                            }
                    }
                    CHidx.add(chIdx);
               }
                int best_idx=0;
                double minval =  Double.POSITIVE_INFINITY;
                for(int i=0;i<preData.size();i++){
                    double val =  preData.get(i).get(4);
                    if(val < minval){
                                minval = val;
                                best_idx = i;
                            }
                
                ArrayList<Integer> parent1 = new ArrayList<>();
                parent1.addAll(CHidx);
                ArrayList<ArrayList<Integer>> parent2 = new ArrayList<>();
                parent2.addAll(clustgrp);
//                String parent2 = selection(population);
                String offspring = crossover(parent1, parent2);
                offspring = mutation(offspring);
                newPopulation[i] = offspring;
                
                }

            // Update the population
            population = newPopulation;

            // Print the best individual in the current generation
            double bestFitness = Double.MIN_VALUE;
            String bestIndividual = "";
            end3 = System.currentTimeMillis();
            for (int i = 0; i < POPULATION_SIZE; i++) {
                if (fitnessValues[i] > bestFitness) {
                    bestFitness = fitnessValues[i];
                    bestIndividual = population[i];
                }
            }
            if(generation==MAX_GENERATIONS){
                ClustGrp.addAll(clustgrp);
            }
        }
        return ClustGrp;
    }
}
