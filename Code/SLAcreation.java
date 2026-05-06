package Code;

import static Code.Preprocessing.idx;
import static Code.Preprocessing.resTime;
import static Code.Preprocessing.st;
import static Code.VM.vmhour;
import static Code.VM.vmprice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.TreeMap;


public class SLAcreation {
    
    public static double end2;
    public static ArrayList<Double> deadline =  new ArrayList<>();
    public static ArrayList<Integer> count =  new ArrayList<>();
    
    public static TreeMap<Integer, ArrayList<Double>> main(ArrayList<ArrayList<Double>> preData){
        
        Random r = new Random(1);
        ArrayList<Double> vmcost = new ArrayList<>();
        ArrayList<Double> totalCost = new ArrayList<>();
        ArrayList<Double> resTim = new ArrayList<>();
        ArrayList<Double> slav = new ArrayList<>();
        ArrayList<Integer> bdown = new ArrayList<>();
        ArrayList<Double> avlbty = new ArrayList<>();
        ArrayList<Double> relbty = new ArrayList<>();
        TreeMap<Integer, ArrayList<Double>> slaTabel = new TreeMap<>(); 
        final double vminit =1; // initial cost
        for (int i=0;i<preData.size();i++){
            deadline.add(r.nextDouble()*(30-20)+20); // User Request Deadline
            double res = vmhour.get(idx.get(i))/1e6; // converting nano sec to milli sec
            double cost = vmprice.get(idx.get(i))*(res+vminit); // Total cost
            vmcost.add(cost);
            if(preData.get(i).get(4)>90){
                bdown.add(i); // breakdown 
            }
            resTim.add(resTime.get(i)/1e7);
        }
        end2=System.currentTimeMillis();
        // Testing lines
        for (int i=0;i<preData.size();i++){
            double lmda = r.nextDouble()*(0.5-0.46)+0.46; // weight
            double mtbf =  st.get(i)/bdown.size();
            double end = System.currentTimeMillis();
            double mttr = end/bdown.size();
            double avl = (mtbf/(mtbf+mttr))+lmda;
            avlbty.add(avl);
            double rel = (mtbf/mttr)-lmda;
            relbty.add(rel);
        }
        
        for(int i=0;i<preData.size();i++){
            double res = vmhour.get(idx.get(i))/1e5; // converting nano sec to milli sec
            if(res > deadline.get(i)){ // sla violation of each user request
                slav.add(res-deadline.get(i));
                count.add(i);
            }else {
                slav.add(0.0);
            }
        }
        
        double slav_per = ((double) count.size()/(double) preData.size())*100; // sla violation percentage
        for(int i=0;i<preData.size();i++){
            totalCost.add(vmcost.get(i)+(vmcost.get(i)*slav_per));// total cost
            ArrayList<Double> Tabel = new ArrayList<>(Arrays.asList(totalCost.get(i),resTim.get(i),avlbty.get(i),relbty.get(i)));
            slaTabel.put(i, Tabel); // sla tabel
        }    
        return slaTabel;
    }
}
