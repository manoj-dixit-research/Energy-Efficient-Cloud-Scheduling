
package Graph;


import static Code.Performance.Costc;
import java.awt.Color;
import java.awt.Font;
import java.util.Collections;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities; 

public class Cost extends ApplicationFrame {

    private static Number get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
   public Cost( String applicationTitle , String chartTitle ) {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Number Of Tasks",            
         "Cost (C$)",           
         createDataset(),          
         PlotOrientation.VERTICAL,           
         false, false, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 750 , 350 ) );        
      setContentPane( chartPanel ); 
       barChart.setBackgroundPaint(Color.WHITE);
      CategoryPlot plot = barChart.getCategoryPlot();
      Font font3 = new Font("Times New Roman", Font.BOLD, 18); 
      Font font2 = new Font("Times New Roman", Font.BOLD, 14);
      
      plot.getDomainAxis().setLabelFont(font3);
      plot.getRangeAxis().setLabelFont(font3);
      plot.getRenderer().setSeriesPaint(3, new Color(255, 128, 0));
     plot.getRenderer().setSeriesPaint(4, new Color(255, 255, 0));
 plot.getRenderer().setSeriesPaint(5, new Color(255, 0, 0));
plot.getRenderer().setSeriesPaint(6, new Color(124, 0, 123));
      Font nwfont=new Font("Times New Roman",Font.BOLD,14);
      NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
      rangeAxis.setTickLabelFont(nwfont);
      CategoryAxis domainAxis = plot.getDomainAxis();
      domainAxis.setTickLabelFont(nwfont);
      
      BarRenderer br = (BarRenderer) plot.getRenderer();
      br.setMaximumBarWidth(.050);

      LegendTitle legend = new LegendTitle(plot.getRenderer()); 
      legend.setItemFont(font2); 
      legend.setPosition(RectangleEdge.BOTTOM); 
      barChart.addLegend(legend);
   }
   
   private CategoryDataset createDataset( ) {
    
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  
      Collections.sort(Costc);
    dataset.addValue(153.31, "Existing", "40");  
    dataset.addValue(164.92, "ICA-K_Means", "40");   
    dataset.addValue(179.83, "SCOOTER", "40");  
    dataset.addValue(184.81, "BULLET","40");
    dataset.addValue(Costc.get(0), "Proposed", "40");
    
    dataset.addValue(179.01, "Existing", "55");  
    dataset.addValue(192.27, "ICA-K_Means", "55");   
    dataset.addValue(203.87, "SCOOTER", "55");  
    dataset.addValue(215.47, "BULLET","55");
    dataset.addValue(Costc.get(1), "Proposed", "55");

    dataset.addValue(230.39, "Existing", "70");  
    dataset.addValue(244.48, "ICA-K_Means", "70");   
    dataset.addValue(254.42, "SCOOTER", "70");  
    dataset.addValue(265.19, "BULLET","70");
    dataset.addValue(Costc.get(2), "Proposed", "70");
       
    dataset.addValue(256.08, "Existing", "85");  
    dataset.addValue(276.80, "ICA-K_Means", "85");   
    dataset.addValue(299.17, "SCOOTER", "85");  
    dataset.addValue(314.09, "BULLET","85");
    dataset.addValue(Costc.get(3), "Proposed", "85");
    
    dataset.addValue(302.49, "Existing", "100");  
    dataset.addValue(330.66, "ICA-K_Means", "100");   
    dataset.addValue(345.58, "SCOOTER", "100");  
    dataset.addValue(362.15, "BULLET","100");
    dataset.addValue(Costc.get(4), "Proposed", "100");
    
    dataset.addValue(351.38, "Existing", "115");  
    dataset.addValue(386.19, "ICA-K_Means", "115");   
    dataset.addValue(412.71, "SCOOTER", "115");  
    dataset.addValue(429.28, "BULLET","115");
    dataset.addValue(Costc.get(5), "Proposed", "115");
      return dataset; 
   }
   
   public static void main() {
      Cost chart = new Cost("Cost", 
         "");
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
}