package Code;

import java.util.Calendar;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.core.CloudSim;

public class Cloudsim {

    public static void main() {
        cloudsimsetup();
        createCloudlet();
        createDatacenter();
        createHost();
        createVM();
       
//       CloudSim.stopSimulation();
    } 
    private static void cloudsimsetup() {
       
        int num=1;
        Calendar calendar=Calendar.getInstance();
        boolean trace_flag=false;
        Log.printLine("Reading the Matrices..." );
        CloudSim.init(num,calendar,trace_flag);
        Log.printLine("Starting CloudSim version 3.0" );

    }
    private static void createDatacenter() {
        
        DataCenter dc=new DataCenter();
        DataCenter[] datacenter;
        datacenter = new DataCenter[3];
        dc.printdata(3);
    }
    private static void createCloudlet() {
       
        cloudlet c1=new cloudlet();
        c1.setproperties();
       
    }
    private static void createVM() {
        VM vm=new VM();
        vm.setproperties();
        vm.printdata();
    }
    private static void createHost() {
       
        Host ht=new Host();
        ht.setproperties();
//        Host.printdata(Host.HostList);
   
    }
    
}
