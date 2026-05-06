
package Code;

import static Code.DataCenter.Dataid;
import static Code.Host.cdim;
import static Code.Main.Data;
import static Code.cloudlet.cloud;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeMap;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Vm;

public class VM {
    public static int vm;
    public static long ram;
    public static double mips;
    public static long storage;
    public static double bw;   
    public static int pesNumber;
    public static String vmm;
    public static int vmi;
    public static double cost;
    public static ArrayList<Integer> Vm =new ArrayList<>();
    public static TreeMap<Integer,Integer> Ram=new TreeMap<Integer,Integer>();
    public static ArrayList<Double> MIPS = new ArrayList<>();
    public static ArrayList<Long> RAM = new ArrayList<>();
    public static ArrayList<Long> STORAGE = new ArrayList<>();
    public static ArrayList<Long> layer = new ArrayList<>();
    public static ArrayList<Double> time = new ArrayList<>();
    public static ArrayList<Integer> vmid =new ArrayList<>();
    public static TreeMap<Integer,Double> Mips=new TreeMap<Integer,Double>();
    public static TreeMap<Integer,Double> Bw=new TreeMap<Integer,Double>();
    public static ArrayList<ArrayList<Integer>> VM = new ArrayList<ArrayList<Integer>>();
    public static ArrayList<ArrayList<Long>> Storage = new ArrayList<ArrayList<Long>>();
    public static ArrayList<Double> vmhour = new ArrayList<>();
    public static ArrayList<Double> vmprice = new ArrayList<>();
    public static Random r = new Random(1); 
    
    VM(){
        //VM Parameters
		vm=6; // total vm
		ram =(long) 1.9472526882546675E9;//vm memory (MB) MHZ
		mips = 3000; // MIPS
                storage =(long) 7.563317545502404E12;// kb
		bw = 1000; // 1GB/s  
		pesNumber = 1; //number of cpus
		vmm = "Xen"; //VMM name
                cost = 3.0;

        //vm creation
        int start = 0;
        int end = Data.size()/(vm);
        double s = System.nanoTime();
        for(int i=0; i <vm;i++){
            
             
            Vm.add(i);
            VM.add(new ArrayList<>(i));
            for(int j=start ;j<end;j++){
                vmprice.add(cost);
                double e = System.nanoTime();
                double t = e-s;
                vmhour.add(t);
            }start=end;end=start+Data.size()/(vm);
            if(i == (vm-2)){
                end = Data.size();
            }
        }
    }
    public static ArrayList<Integer> main(double d){
        
        ArrayList<Integer> storage = new ArrayList<>();
        ArrayList<Double> randTime =new ArrayList<>();
        Random rand = new Random();
        for(int i=0;i<vm;i++){
            randTime.add((double) i);
            randTime.set(i,rand.nextDouble()*15); //assuming cloudlet already running in the vm using random time
        }
        for(int i=0;i<randTime.size();i++){
            
                if(d>=randTime.get(i)){
                    storage.add(i);
            }
        
        }
        System.out.println("Time : "+randTime);
        return storage;
    }
    void setproperties() {
       for (int Vmid=0;Vmid<vm;Vmid++){
            vmid.add(Vmid);
            Bw.put(Vmid,bw);
            RAM.add(ram);
            MIPS.add(mips);
            STORAGE.add(storage);
        }
        ArrayList<Integer> Con=new ArrayList<>();
        for (int i=0; i<vm;i++)
        {
        Con.add(i);
        }
       Collections.shuffle(Con);
        int start=0;
        int end=(vm)/Host.host;
        for (int i=0;i<Host.host;i++){
           ArrayList<Integer> h=new ArrayList<>();
           for (int j=start;j<end;j++){
               h.add(Con.get(j));
               
           }Host.HostList.add(h); start=end;end=start+((vm)/Host.host);
        }layer.addAll(STORAGE);
   }
    void vmlist(){

	Vm Vm = new Vm(vmi, 
                             DataCenter.brokerId, 
                                    mips, 
                                    pesNumber, (int) ram, (long)bw, 
                                    storage,
                                    vmm, new CloudletSchedulerTimeShared());
    }

    void printdata() {
        ArrayList<Integer> arr =new ArrayList<Integer>();
    int c=vm/DataCenter.Datacenters;
        for (int k=0;k<c;k++)
        {
            for (int k1=0;k1<DataCenter.Datacenters;k1++)
            {
                arr.add(k1);
            }
        }
        int c1=vm-arr.size();
        for (int k=0;k<c1;k++)
        {
            arr.add(k);
        }
        time.add(0.0);
        for(int i=0;i<vm;i++){
            cdim = 3;
            if(i<=cloud){
            Log.printLine("0.0: Broker_0: Trying to Create VM #"+i+" in datacenter_"+Dataid.get(0));
            }else if(i<=cdim){
                Log.printLine("0.0: Broker_0: Trying to Create VM #"+i+" in datacenter_"+Dataid.get(1));
            }else if(i>cdim){
                Log.printLine("0.0: Broker_0: Trying to Create VM #"+i+" in datacenter_"+Dataid.get(2));
            }
            
            double w = 0.5+(vm-0.5)*r.nextDouble();
            time.add(w);
        }
        
        Collections.sort(time);
         Log.printLine();
         Host.printdata(arr);
     }
    public static void print(){
        
    }
}
    

