package Code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class ReadData {
    
    public static ArrayList<ArrayList<String>> main(){
        
        final int vm = 6;
        ArrayList<String> read;
        ArrayList<Integer> remover = new ArrayList<>();
        ArrayList<ArrayList<String>> Data =  new ArrayList<>();
        int count = 0;
        remover.add(count);
        for(int i=1;i<=vm;i++){
            
            String path= "E:\\Boaner\\Java Works\\Mr. Manoj Kumar Dixit(435986)\\Dataset\\fastStorage\\2013-8\\"+i+".csv";
            try{

                BufferedReader br = new BufferedReader( new FileReader(path)); // Read the csv file
                String line;
                while ((line = br.readLine())!= null){

                    String[] tempArr = line.split(";"); // Setting a delimiter
                    read = new ArrayList<>(Arrays.asList(tempArr)); //Appending the read data into an arraylist 
                    Data.add(read);
                    count = count+1; // count
                }  
                remover.add(count); // adding the index of the title text array to remove from the dataset
            }catch(IOException e){
//                e.printStackTrace();
                System.out.println(e);
            }
        }
        int cout = 0; // count
        for(int l=0;l<remover.size()-1;l++){
            Data.remove(remover.get(l)-cout); // removing the titles from the dataset 
            cout = cout+1; // count
        }
        return Data;
    }
}
