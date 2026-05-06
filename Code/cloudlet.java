
package Code;

import static Code.Main.Data;
import java.util.ArrayList;
import java.util.Random;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;


public class cloudlet {
    public static int Cloudlet;
    public static double ram;
    public static double mips;
    public static int storage;
    public static double bw;
    public static int DataSize;
    public static int cloud;
    public static  int Cloudletid;
    public static double DTcost;
    public static ArrayList<Integer> Taskid = new ArrayList<>();
    public static ArrayList<Double> Ram = new ArrayList<>();
    public static ArrayList<Double> Mips = new ArrayList<>();
    public static ArrayList<Double> Bw = new ArrayList<>();
    public static ArrayList<Double> Storage = new ArrayList<>();
    public static ArrayList<Integer> cloudLet = new ArrayList<>();
    public static Random r=new Random();
    
    cloudlet(){
    Cloudlet=Data.size();  
    cloud = 1;
    ram = 512; //Host memory (MB)
    mips = 50; // MIPS
    storage = 100; // GB          
    bw = 50; // mbps
    DataSize=1200;//kb
    DTcost=2.0;//data transfer cast
    for(int i=0; i <Cloudlet;i++){
            cloudLet.add(i);
            
        }
     
    }
    void setproperties() {
        
        for (int id=0;id<Cloudlet;id++)
        {
              Taskid.add(id);
            ram=Double.parseDouble(Data.get(id).get(2));
            Ram.add(id,ram);
            mips=r.nextInt((50 - 25) + 1) + 20;
            Mips.add(id,mips);
            bw=r.nextInt((50 - 25) + 1)+50 ;
            Bw.add(id,bw);
            double s= Double.parseDouble(Data.get(id).get(5));
            Storage.add(id,s);
      
        }       
    }
    void cloudletList(){
        UtilizationModel utilizationModel = new UtilizationModelFull();

			Cloudlet cloudlet = new Cloudlet(Cloudletid, (long)storage, VM.pesNumber, (long)bw, (long)ram, utilizationModel, utilizationModel, utilizationModel);
			cloudlet.setUserId(DataCenter.brokerId);
			cloudlet.setVmId(VM.vmi);
    }
}
