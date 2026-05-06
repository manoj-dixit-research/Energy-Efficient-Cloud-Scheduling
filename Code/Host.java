
package Code;

import static Code.DataCenter.Dataid;
import static Code.VM.vm;
import static Code.cloudlet.cloud;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;


public class Host {
    public static int host;
    public static int ram;
    public static double mips;
    public static double bw;
    public static int storage;
    public static int hostId;
    public static int cores;
    public static int cdim;
    public static Random r = new Random(1);
    public static ArrayList<Double> res = new ArrayList<>();
    public static ArrayList<Integer> Ram =new ArrayList<>();
    public static ArrayList<Double> Mips =new ArrayList<>();
    public static ArrayList<Double> Bw =new ArrayList<>();
    public static ArrayList<Integer> Storage =new ArrayList<>();
    public static List<org.cloudbus.cloudsim.Host> hostList = new ArrayList<org.cloudbus.cloudsim.Host>();
    public static ArrayList<ArrayList<Integer>> HostList=new ArrayList<ArrayList<Integer>>();
    Host() {
     
        host=1;
        ram = 20480; //Host memory (MB) 16GB
        mips = 10000; // MIPS
        bw = 10000; // mbps 15GB
        storage =1000000; // GB ==4TB
        String arch = "x86";      // system architecture
        String os = "Linux";          // operating system
        String vmm = "Xen";     // VMM
        double time_zone = 10.0;         // time zone this resource located
        double cost = 3.0;              // the cost of using processing in this resource
        double costPerMem = 0.05;		// the cost of using memory in this resource
        double costPerStorage = 0.1;	// the cost of using storage in this resource
        double costPerBw = 0.1;			// the cost of using bw in this resource
        cores = 4;
        
    }
    public static int checkhostfun(int i) {
        int h=0;
        for (int hl=0;hl<host;hl++) {
            if(HostList.get(hl).contains(i)){
                h=hl;
            }   
        }
        return h;
  }
    void setproperties() {
        
        for (int Hostid=0;Hostid<host;Hostid++){
            Ram.add(Hostid,ram);
            Mips.add(Hostid,mips);
            Bw.add(Hostid,bw);
            Storage.add(Hostid, storage);
        }
         ArrayList<Integer> H=new ArrayList<Integer>();
                for (int i=0;i<Host.host;i++){
                    H.add(i);
                }
                Collections.shuffle(H);
                int start=0;
    
                int end =(host/DataCenter.Datacenters);
                int c=0;
                for (int j=0;j<DataCenter.Datacenters;j++){
                   ArrayList<Integer> D=new ArrayList<Integer>();
                for (int i=start;i<end;i++){
                    
                    D.add(H.get(i));
                    c++;
                }DataCenter.DataC.add(D);start=end;end=start+(host/DataCenter.Datacenters);
                }
                if(c!=host)
                {
                   int c1= host-c;
                   for (int i=0;i<c1;i++){
                       DataCenter.DataC.get(i).add(c+i);
                   }
                }
    }
        public static List<org.cloudbus.cloudsim.Host> createHostList(int hostsNumber) {

            List<Pe> peList = new ArrayList<Pe>();
             peList.add(new Pe(0, new PeProvisionerSimple(mips)));
         hostList.add(
			new org.cloudbus.cloudsim.Host(
				hostId,
				new RamProvisionerSimple(ram),
				new BwProvisionerSimple((long) bw),
				storage,
				peList,
				new VmSchedulerTimeShared(peList)
			)
		); // This is our machine

        return hostList;
        }
        public static void printdata(ArrayList<Integer> datacenter) {
        int j;
        for(int m=0;m<4;m++){
            double var=9+(8-9)*r.nextDouble();res.add(var);
        }
        for(int i=0;i<vm;i++){
           
           int h= checkhostfun(i);
           cdim = 3;
           if(i<=cloud){
            Log.printLine("0.1: Broker_0: Trying to Create VM #"+i+" in Host "+h+" in datacenter_"+Dataid.get(0));
            }else if(i<=cdim){
                Log.printLine("0.1: Broker_0: Trying to Create VM #"+i+ " in Host "+h+" in datacenter_"+Dataid.get(1));
            }else if(i>cdim){
                Log.printLine("0.1: Broker_0: Trying to Create VM #"+i+" in Host "+h+" in datacenter_"+Dataid.get(2));
        }
        for(j=0;j<VM.vm;j++){
        }
        Collections.shuffle(cloudlet.cloudLet); 
        }
        Log.printLine("****************************_______________________****************************");
        Log.printLine("");
        }       
}
