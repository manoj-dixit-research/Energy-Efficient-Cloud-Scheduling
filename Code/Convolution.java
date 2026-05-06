package Code;

import UTIL.Mat;

public class Convolution {
    //

    /**
     * caches the input data (the data) for use in the back-propagation phase.
     */
        public float[][] input; // shape --> [28] X [28]
    //

    /**
     * caches filters that were used in the convolution phase for use in the 
     * back-propagation phase.
     */
        public float[][][] filters; // shape --> [3] X [8] X [8]
    /**
     * Convolves the data with respect to a 3X3 filter
     * @param data the data matrix with shape [28] X [28]
     * @param filter a 3X3 filter used in the convolution process.
     * @return a 2D matrix with shape [26] X [26].
     */
    public float[][] convolve3x3(float[][] data, float[][] filter) {
        input=data;
        float[][] result = new float[data.length - 2][data[0].length - 2];
        //loop through
        for (int i = 1; i < data.length - 2; i++) {
            for (int j = 1; j < data[0].length - 2; j++) {
                float[][] conv_region = Mat.m_sub(data, i - 1, i + 1, j - 1, j + 1);
                result[i][j] = Mat.mm_elsum(conv_region, filter);
            }
        }
        return result;
    }

    /**
     * the forward convolution pass that convolves the data w.r.t. each filter
     * in the filter array. No padding has been used in this case, so output matrix
     * shape decreases by 2 w.r.t row width and column height.
     * @param data the input data matrix. [28] X [28]
     * @param filter a 3D matrix containing an array of 3X3 filters ([8]X[3]X[3])
     * @return a 3D array containing an array of the convolved datas w.r.t.
     * each filter. [8] X [26] X [26]
     */
    public float[][][] forward(float[][] data, float[][][] filter) {
        filters=filter; // 8 X 3 X 3
        float[][][] result = new float[8][11][11];
        for (int k = 0; k < filters.length; k++) {
            float[][] res = convolve3x3(data, filters[k]);
            result[k] = res;
        }
        return result;
    }
    
    /**
     * 
     * @param d_L_d_out the input gradient matrix retrieved from the back-propagation
     *  phase of the maximum pooling stage. shape = [8] X [26] X [26]
     * @param learning_rate the learning rate factor used in the neural network.
     */
    public void backprop(float[][][] d_L_d_out,float learning_rate){
        //the output gradient which is dL/dfilter= (dL/dout)*(dout/dfilter)
        float[][][] d_L_d_filters= new float[filters.length][filters[0].length][filters[0][0].length];
        //reverses the convolution phase by creating a 3X3 gradient filter 
        //and assigning its elements with the input gradient values scaled by
        //the corresponding pixels of the data.
        for(int i=1;i<input.length-2;i++){
            for(int j=1;j<input[0].length-2;j++){
                for(int k=0;k<filters.length;k++){
                    //get a 3X3 region of the matrix
                    float[][] region=Mat.m_sub(input,  i - 1, i + 1, j - 1, j + 1);
                    //for each 3X3 region in the input data i,j
                    // d_L_d_filter(kth filter) = d_L_d_filter(kth filter)+ d_L_d_out(k,i,j)* sub_data(3,3)i,j
                    //       [3] X [3]          =       [3] X [3]         +     gradient    *      [3] X [3]
                    //see article as to how this gradient is computed.
                    d_L_d_filters[k]=Mat.mm_add(d_L_d_filters[k], Mat.m_scale(region,d_L_d_out[k][i-1][j-1]));
                }
            }
        }
        
        //update the filter matrix with the gradient matrix obtained above.
        for(int m=0;m<filters.length;m++){
          // [3] X [3]  =   [3] X [3] + -lr * [3] X [3]   
            filters[m]= Mat.mm_add(filters[m], Mat.m_scale(d_L_d_filters[m],-learning_rate));
        }  
    }
}
