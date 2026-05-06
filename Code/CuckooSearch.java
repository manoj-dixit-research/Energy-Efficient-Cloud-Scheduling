package Code;

import java.util.Random;

public class CuckooSearch {
    private static int POPULATION_SIZE;// = 100; // Number of nests
    private static int DIMENSION;// = 2; // Number of dimensions in search space
    private static final double PA = 0.25; // Probability of a cuckoo egg being discovered
    private static final double ALPHA = 1.5; // Lévy flight scaling factor
    private static final double BETA = 0.5; // Lévy flight exponent
    private static final int MAX_ITERATIONS = 100; // Maximum number of iterations
    private static final double EPSILON = 1e-6; // Stopping criterion
    
    private static Random random = new Random();
    
    public static double[][] main(float[][] weights) {
        // Initialize population
        POPULATION_SIZE = weights.length;
        DIMENSION = weights[0].length;
        double[][] population = new double[POPULATION_SIZE][DIMENSION];
        for (int i = 0; i < POPULATION_SIZE; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                population[i][j] = random.nextDouble();
            }
        }
        
        // Main loop
        int iteration = 0;
        while (iteration < MAX_ITERATIONS) {
            // Sort population by fitness (ascending order)
            sortPopulation(population);
            
            // Generate new solutions (Lévy flights)
            double[][] newPopulation = new double[POPULATION_SIZE][DIMENSION];
            for (int i = 0; i < POPULATION_SIZE; i++) {
                double[] nest = population[i];
                double[] newNest = new double[DIMENSION];
                
                // Generate Lévy flight step
                double sigma = Math.pow(random.nextDouble(), 1.0 / BETA);
                double[] levyStep = generateLevyStep();
                
                // Update position using Lévy flight step
                for (int j = 0; j < DIMENSION; j++) {
                    double x = nest[j] + ALPHA * sigma * levyStep[j];
                    newNest[j] = Math.max(0.0, Math.min(1.0, x)); // Clamp to search space boundaries
                }
                
                // Apply random walk
                if (random.nextDouble() < PA) {
                    for (int j = 0; j < DIMENSION; j++) {
                        newNest[j] += random.nextGaussian() * EPSILON;
                        newNest[j] = Math.max(0.0, Math.min(1.0, newNest[j])); // Clamp to search space boundaries
                    }
                }
                
                newPopulation[i] = newNest;
            }
            
            // Replace worst solutions with new solutions
            for (int i = (int)(POPULATION_SIZE * (1.0 - PA)); i < POPULATION_SIZE; i++) {
                population[i] = newPopulation[i];
            }
            
            // Increment iteration counter
            iteration++;
        }
        
        // Print best solution
        double bestpop[][] = sortPopulation(population);
        return bestpop;
    }
    
    private static double[] generateLevyStep() {
        // Generate Lévy flight step using method proposed by Mantegna (1994)
        double[] step = new double[DIMENSION];
        double[] phi = new double[DIMENSION];
        
        for (int i = 0; i < DIMENSION; i++) {
            phi[i] = random.nextGaussian();
        }
        
        double s = 0.0;
        for (int i =    0; i < DIMENSION; i++) {
        double u = random.nextGaussian();
        double v = random.nextGaussian();
        double w = random.nextGaussian();
        double a = Math.sqrt(1.0 / (2.0 * Math.PI)) * (u / Math.abs(phi[i]));
        double b = Math.exp(-0.5 * Math.pow((ALPHA * phi[i] * v), 2.0));
        double c = Math.sin(ALPHA * phi[i] * w) / Math.pow(Math.abs(ALPHA * phi[i] * w), BETA);
        step[i] = a * b * c;
        s += Math.pow(step[i], 2.0);
    }
    
    double norm = Math.sqrt(s);
    for (int i = 0; i < DIMENSION; i++) {
        step[i] /= norm;
    }
    
    return step;
}

private static double fitness(double[] solution) {
    // The objective function to be minimized
    double x = solution[0];
    double y = solution[1];
    return Math.sin(3.0 * Math.PI * x) * Math.sin(3.0 * Math.PI * y) / (x * x + y * y);
}

private static double[][] sortPopulation(double[][] population) {
    // Sort population by fitness (ascending order)
    for (int i = 0; i < POPULATION_SIZE; i++) {
        for (int j = i + 1; j < POPULATION_SIZE; j++) {
            if (fitness(population[j]) < fitness(population[i])) {
                double[] temp = population[i];
                population[i] = population[j];
                population[j] = temp;
            }
        }
    }
    return population;
}
}

