package Code;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Preprocessing {
    
    public static ArrayList<Double> resTime = new ArrayList<>();
    public static ArrayList<Double> st = new ArrayList<>();
    public static ArrayList<Integer> idx = new ArrayList<>();
    public static double start,end1;
    public static ArrayList<ArrayList<Double>> main(ArrayList<ArrayList<String>> Data){
        start = System.currentTimeMillis();
        ArrayList<Integer> preidx = new ArrayList<>(); 
        ArrayList<ArrayList<Double>> preData = new ArrayList<>();
        final double contain = 0.0; // Identifying the unwanted requests
        double start = System.nanoTime(); // Time calculation
        for(int i=0;i<Data.size();i++){
            
            for(int j=0;j<Data.get(i).size();j++){
                double checkzero = Double.parseDouble(Data.get(i).get(j));
                if(checkzero == contain){ 
                    preidx.add(i); // adding the index of the unwanted request
                }
            }
        }
        Set<Integer> set = new LinkedHashSet<>();
        set.addAll(preidx);
        preidx.clear();
        preidx.addAll(set);
        
        for(int i=0;i<Data.size();i++){
            if(!preidx.contains(i)){
                double star =System.currentTimeMillis();
                st.add(star);
                idx.add(i); // Index of the Selected request from the Data array
                ArrayList<Double> temp = new ArrayList<>(); // temporary array
                for(int k=0;k<Data.get(i).size();k++){
                    temp.add(Double.parseDouble(Data.get(i).get(k)));
                }
                preData.add(temp); // Selected Data by pre-processing
                double end = System.nanoTime();
                resTime.add(end-start); // time calculation
            }
        }
        end1=System.currentTimeMillis();
        return preData;
    }
}
