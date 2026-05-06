package Code;

import static Code.VM.layer;
import UTIL.Mat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class CNN {
        
public static double end4;
    
public static Random r =new Random();

    /**
     * converts a BufferedImage into a pixel array and normalizes it. 
     * @return 2D array with normalized pixel values between 0.0 and 1.0
     */
    public static float[][] data_to_mat( ) {
//        int w = imageToPixelate.getWidth(), h = imageToPixelate.getHeight();
//        int[] pixels = imageToPixelate.getRGB(0, 0, w, h, null, 0, w);
        ArrayList<Float> d=new ArrayList<>();
       for (int i=0;i<11;i++){
           d.add(r.nextFloat());
       }
        int w =28;
        int h=28;
        int st=0;
         float[][] dta = new float[w][h];
        //        for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel++) {
        //            dta[row][col] = (((int) pixels[pixel] >> 16 & 0xff)) / 255.0f;
         for(int row =0;row<w; row++){

             for (int col=0;col<h;col++) {
                dta[row][col]=d.get(st);
                   st++;
                if (st == d.size()) {
                        st = 0;
                    }
             }
        //            col++;
        //            
                }
                return dta;
            }

    /**
     * creates 3X3 convolution filters with random initial weights.
     * @param size number of 3X3 filters to be randomly initialized
     * @return a [size] X [3] X [3] 3d array with size filters
     */
    public static float[][][] init_filters(int size) {
        float[][][] result = new float[size][3][3];
        for (int k = 0; k < size; k++) {
            result[k] = Mat.m_random(3, 3);
        }
        return result;
    }
    /**
     * performs both the forward and back-propagation passes of the CNN.
     * @param Test
     * @param pre
     * @throws IOException if image cannot be found.
     */
    public static ArrayList<Integer> labels = new ArrayList<>();
    public static void test(ArrayList<Integer> Test, ArrayList<ArrayList<Double>> pre) throws IOException {
        ArrayList<Integer> TrainData =  new ArrayList<>();
        TrainData.addAll(Test);
        int training_size = TrainData.size();
        float[][][] filters = init_filters(8);
        int label_counter = 0;
        float ce_loss=0;
        int accuracy=0;
        float acc_sum=0.0f;
        float learn_rate=0.005f;
        int label = 6;
        
        //initialize layers
        Convolution conv=new Convolution();
        MaxPool pool=new MaxPool();
        SoftMax softmax=new SoftMax(13*13*8,label);
        
        float[][] out_l = new float[1][6];
        double preMu =  pre.get(0).get(6);
            layer.set(0, (long) (layer.get(0)-preMu));
            labels.add(0);
            for(int j=1;j<pre.size();j++){
                double mu =  pre.get(j).get(6);
                int cout =1;
                for(int k=0;k<layer.size();k++){
                   if(layer.get(k)>layer.get(cout)){
                    layer.set(k, (long) (layer.get(k)-mu));
                    labels.add(k);
                    break;
                }
                cout++;
                if(cout==layer.size()){
                    cout=0;
                } 
                }
            }
        for (int i = 0; i < training_size; i++) {
            int correct_label = label_counter;
           if(label_counter==5){
                label_counter=0;
            }else{
                label_counter++;
            } 
            
            //FORWARD PROPAGATION
            
            //convert to pixel array
            float[][] pxl = data_to_mat( );
            // perform convolution 28*28 --> 8x26x26
            float[][][] out = conv.forward(pxl, filters);

            // perform maximum pooling  8x26x26 --> 8x13x13
            out = pool.forward(out);
            
            // perform softmax operation  8*13*13 --> 6
            out_l = softmax.forward(out); 
            
            // compute cross-entropy loss
            ce_loss += (float) -Math.log(out_l[0][correct_label]);
            accuracy += correct_label == Mat.v_argmax(out_l) ? 1 : 0;
            
            //BACKWARD PROPAGATION --- STOCHASTIC GRADIENT DESCENT
            //gradient of the cross entropy loss
            float[][] gradient=Mat.v_zeros(6);
            gradient[0][correct_label]=-1/out_l[0][correct_label];
            float[][][] sm_gradient=softmax.backprop(gradient,learn_rate);
            float[][][] mp_gradient=pool.backprop(sm_gradient);
            conv.backprop(mp_gradient, learn_rate);
            if(i % 60 == 99){
                ce_loss=0;
                acc_sum+=accuracy;
                accuracy=0;
            }
        }
    }
   
    /**
     * performs both the forward and back-propagation passes of the CNN.
     * @param Train
     * @param pre
     * @throws IOException if image cannot be found.
     */
    public static void train(ArrayList<Integer> Train, ArrayList<ArrayList<Double>> pre) throws IOException {
        ArrayList<Integer> TrainData =  new ArrayList<>();
        Random r = new Random();
        TrainData.addAll(Train);
        int training_size = TrainData.size();
        float[][][] filters = init_filters(8);
        int label_counter = 0;
        float ce_loss=0;
        int accuracy=0;
        float acc_sum=0.0f;
        float learn_rate=0.005f;
        int labels = 6;
        
        //initialize layers
        Convolution conv=new Convolution();
        MaxPool pool=new MaxPool();
        end4=System.currentTimeMillis();
        SoftMax softmax=new SoftMax(13*13*8,labels);

        float[][] out_l = new float[1][6];
        for (int i = 0; i < training_size; i++) {
            int correct_label = label_counter;
           if(label_counter==5){
                label_counter=0;
            }else{
                label_counter++;
            } 
            
            //FORWARD PROPAGATION
            
            //convert to pixel array
            float[][] pxl = data_to_mat( );
            // perform convolution 28*28 --> 8x26x26
            float[][][] out = conv.forward(pxl, filters);

            // perform maximum pooling  8x26x26 --> 8x13x13
            out = pool.forward(out);
            
            // perform softmax operation  8*13*13 --> 6
            out_l = softmax.forward(out); 
            
            // compute cross-entropy loss
            ce_loss += (float) -Math.log(out_l[0][correct_label]);
            accuracy += correct_label == Mat.v_argmax(out_l) ? 1 : 0;
            
            //BACKWARD PROPAGATION --- STOCHASTIC GRADIENT DESCENT
            //gradient of the cross entropy loss
            float[][] gradient=Mat.v_zeros(6);
            gradient[0][correct_label]=-1/out_l[0][correct_label];
            float[][][] sm_gradient=softmax.backprop(gradient,learn_rate);
            float[][][] mp_gradient=pool.backprop(sm_gradient);
            conv.backprop(mp_gradient, learn_rate);
            if(i % 60 == 99){
                ce_loss=0;
                acc_sum+=accuracy;
                accuracy=0;
            }
        }
    }

    
      
    /**
     * Test method.
     * @param clustgrp
     * @param pre
     * @throws IOException
     */
    public static void main(ArrayList<ArrayList<Integer>> clustgrp,ArrayList<ArrayList<Double>> pre) throws IOException {   
        
        ArrayList<Integer> Train = new ArrayList<>();
        ArrayList<Integer> Test = new ArrayList<>();
        Train.addAll(clustgrp.get(0));
        Test.addAll(clustgrp.get(1));
           
                train(Train,pre);
                test(Train,pre);
                labels.clear();
          
                test(Test,pre);
    }
}
    


