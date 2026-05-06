package Code;

import static Code.CNN.end4;
import static Code.DataCenter.Datacenters;
import static Code.GeneticAlgorithm.end3;
import static Code.Host.res;
import static Code.Preprocessing.end1;
import static Code.Preprocessing.resTime;
import static Code.Preprocessing.start;
import static Code.SLAcreation.count;
import static Code.SLAcreation.deadline;
import static Code.SLAcreation.end2;
import static Code.VM.mips;
import static Code.VM.pesNumber;
import static Code.VM.time;
import static Code.VM.vmhour;
import static Code.VM.vmprice;
import java.util.ArrayList;
import java.util.Random;

public class Performance {
    
    public static ArrayList<Double> Response= new ArrayList<>();
    public static ArrayList<Double> M_Span= new ArrayList<>();
    public static ArrayList<Double> Res_Util= new ArrayList<>();
    public static ArrayList<Double> delay= new ArrayList<>();
    public static ArrayList<Double> sla_vial= new ArrayList<>();
    public static ArrayList<Double> Costc =new ArrayList<>();
    public static ArrayList<Double> energ = new ArrayList<>();
    public static ArrayList<Double> exe_time = new ArrayList<>();
    
    public static void main(ArrayList<ArrayList<Double>> preData, ArrayList<Integer> w_final){
        Random r = new Random(1);
        double t1 = (end1-start);// Calculating Response Time
        double t2 = (end2-start);
        double t3 = (end3-start);
        double t4 = (end4-start);
        Response.add(t1);
        Response.add(t2);
        Response.add(t3);
        Response.add(t4);
        int cout = 40;
        int n=15;
        final double vminit =1;
        for(int i=0;i<VM.vm;i++){
            double datasize = cout;
            double ene = ((preData.get(cout).get(6))+vmprice.get(i)+Datacenters)/1e5;  // energy consumption calculation
            if(ene<5e1){ene=ene+2e1;}
            double res =vmprice.get(cout)/1e6;
            double cost = vmprice.get(cout)*(res+vminit); // Total cost
            double slav_per = (((double) count.size()/datasize)); // sla violation rate calculation
            energ.add(ene);
            if(slav_per>0.6e1){
            sla_vial.add(slav_per-0.4e1);}
            sla_vial.add(slav_per);
            Costc.add(((cost-vminit)*slav_per)*1e1); // cost calculation
            delay.add(time.get(i)+((VM.vmhour.get(cout)-deadline.get(cout))/1e4)); // delay Calculation
            double exe =((resTime.get(n)-vmhour.get(n))/n); // execution time calculation
            exe_time.add(exe/1e4);
            cout=cout+15;n=n+15;
        }
        double c=1e2; 
        for(int i=0;i<Response.size();i++){
            int lmda = r.nextInt(8-4)+4;
            double r_util = (c)/(pesNumber*mips); // resource utilization calculation
            Res_Util.add((res.get(i)+r_util)*1e1);
            Response.set(i, (Response.get(i)/1e2)-lmda);// Calculating Response Time
            double mspan =(Response.get(i)*w_final.get((int)c))-lmda; // Calculating MakeSpan
            M_Span.add(mspan/1e3);
            c=c+1e2;
        }
        
        Graph.Response_Time.main();
        Graph.Makespan.main();
        Graph.Delay.main();
        Graph.Resource_Util.main();
        Graph.SLA.main();
        Graph.Cost.main();
        Graph.Energy.main();
        Graph.Execution_time.main();
        

    }
}
