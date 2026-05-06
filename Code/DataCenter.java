/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;

class DataCenter {
      public static int brokerId; 
      public static int Datacenters;
      public static int count=0;
      public static ArrayList<Integer> Dataid= new ArrayList<>();
       public static ArrayList<ArrayList<Integer>> DataC=new ArrayList<ArrayList<Integer>>();

    static int checkdatafun(int h1) {
          int d=0;
        for (int dl=0;dl<DataC.size();dl++) {
            {
                if(DataC.get(dl).contains(h1))
                {
                    d=dl;
                }
            }
        }
                    return d;
  }
    DataCenter(){
			brokerId = 0;
                        Datacenters =3;
    }
    public static org.cloudbus.cloudsim.Datacenter createdatacenter()
    {
        
        List<Pe> peList1 = new ArrayList<Pe>();
        List<org.cloudbus.cloudsim.Host> hostList = new ArrayList<org.cloudbus.cloudsim.Host>();
 
		String arch = "x86";      // system architecture
		String os = "Linux";          // operating system
		String vmm = "Xen";     // VMM
		double time_zone = 10.0;         // time zone this resource located
		double cost = 3.0;              // the cost of using processing in this resource
		double costPerMem = 0.05;		// the cost of using memory in this resource
		double costPerStorage = 0.001;	// the cost of using storage in this resource
		double costPerBw = 0.1;			// the cost of using bw in this resource
		LinkedList<Storage> storageList = new LinkedList<Storage>();	//we are not adding SAN devices by now
                String name="datacenter";
		DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                arch, os, vmm, hostList, time_zone, cost, costPerMem, costPerStorage, costPerBw);

		
		org.cloudbus.cloudsim.Datacenter datacenter = null;
		try {
			datacenter = new org.cloudbus.cloudsim.Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
                		return datacenter;

    }
    private static DatacenterBroker createBroker() {
		DatacenterBroker broker = null;
		try {
			broker = new DatacenterBroker("Broker");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return broker;
	}
    public static void main()
{
    org.cloudbus.cloudsim.Datacenter datacenter0 = createdatacenter();
        DatacenterBroker broker = createBroker();
      brokerId = broker.getId();
}
    void printdata(int i) {
        int n = Datacenters;
        for(int k = 0; k< n;k++){
            Dataid.add(k);
            Log.printLine("Datacenter_"+k+" is starting..."); 
        }
        Log.printLine("Broker_0 is starting...");  
        Log.printLine("Entities started.\n" ); 
        Log.printLine("0.0: Broker_0: Cloud Resource List received with "+Datacenters+" resource(s)");
    }
}
