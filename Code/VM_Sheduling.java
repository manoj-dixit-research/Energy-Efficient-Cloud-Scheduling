package Code;

import static Code.CNN.labels;
import java.util.ArrayList;

public class VM_Sheduling {
    
    public static void main(ArrayList<ArrayList<Double>> preData){
        
        ArrayList<Integer> W_final = new ArrayList<>();
        for(int i=0;i<preData.size();i++){ // Sheduling the workloads to the VM using the generated labels 
            switch (labels.get(i)) {
                case 0:
                    VM.VM.get(0).add(i);
                    break;
                case 1:
                    VM.VM.get(1).add(i);
                    break;
                case 2:
                    VM.VM.get(2).add(i);
                    break;
                case 3:
                    VM.VM.get(3).add(i);
                    break;
                case 4:
                    VM.VM.get(4).add(i);
                    break;
                case 5:
                    VM.VM.get(5).add(i);
                    break;
                default:
                    break;
            }
            W_final.add(i);
        }
        Performance.main(preData, W_final);
    }
}
