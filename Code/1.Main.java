package Code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class Main {
    
    public static ArrayList<ArrayList<String>> Data;
    
    public static void main(String[] args) throws IOException {
       
        ArrayList<ArrayList<Double>> preData;
        TreeMap<Integer, ArrayList<Double>> slaTabel; 
        ArrayList<ArrayList<Integer>> clustgrp = new ArrayList<>();
        Data = ReadData.main();
        Cloudsim.main();
        preData = Preprocessing.main(Data); // Seprating the Unwanted & noisey user request by pre-processing
        slaTabel = SLAcreation.main(preData); //Assigning SLA table for each pre-processed Request
        int populationSize = preData.size(); // size of the population
        clustgrp = GeneticAlgorithm.main(populationSize, preData,slaTabel); // clustering the user request
        CNN.main(clustgrp,preData); // sheduling labels created by using CNN and CuckooSearch 
        VM_Sheduling.main(preData); // Sheduling the user request to vm using labels 
        
    }
    
}
