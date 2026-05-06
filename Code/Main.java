package Code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class Main {
    public static ArrayList<ArrayList<Double>> Data;

    public static void main(String[] args) {
        try {
            Data = ReadData.main(); // Your existing method
            
            // Placeholder calls - replace with your actual implementations
            Cloudsim.main();
            
            ArrayList<ArrayList<Double>> preData = Preprocessing.main(Data);
            TreeMap<Integer, ArrayList<ArrayList<Double>>> slaTabel = SLAcreation.main(preData);
            
            int populationSize = Math.max(1, preData.size()); // Safe minimum
            ArrayList<ArrayList<Double>> clustgrp = GeneticAlgorithm.main(populationSize, preData, slaTabel);
            
            CNN.main(clustgrp, preData);
            VM_Sheduling.main(preData);
            
            System.out.println("Pipeline completed successfully");
            
        } catch (Exception e) {
            System.err.println("Main pipeline error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
