package Code;

import java.util.ArrayList;


public class Normalize {
    public static ArrayList<Double> main(ArrayList<Double> slaT){
        ArrayList<Double> normalizedList = new ArrayList<>();
        
        double sum = 0.0;
        for (double value : slaT) {
            sum += value;
        }
        
        if (sum == 0.0) {
            // Handle division by zero
            return slaT;
        }
        
        for (double value : slaT) {
            double normalizedValue = value / sum;
            normalizedList.add(normalizedValue);
        }
        
        return normalizedList;
    }
}
