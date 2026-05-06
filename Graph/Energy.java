
package Graph;


import static Code.Performance.energ;
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

public class Energy extends ApplicationFrame {

    private static Number get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
   public Energy( String applicationTitle , String chartTitle ) {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Number Of Tasks",            
         "Energy Consumption (kWh)",           
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
      Collections.sort(energ);
    dataset.addValue(60.72, "Existing", "40");  
    dataset.addValue(62.95, "ICA-K_Means", "40");   
    dataset.addValue(69.08, "SCOOTER", "40");  
    dataset.addValue(71.87, "BULLET","40");
    dataset.addValue(energ.get(0), "Proposed", "40");
    
    dataset.addValue(66.08, "Existing", "55");  
    dataset.addValue(69.99, "ICA-K_Means", "55");   
    dataset.addValue(74.63, "SCOOTER", "55");  
    dataset.addValue(76.86, "BULLET","55");
    dataset.addValue(energ.get(1), "Proposed", "55");

    dataset.addValue(70.70, "Existing", "70");  
    dataset.addValue(73.86, "ICA-K_Means", "70");   
    dataset.addValue(78.88, "SCOOTER", "70");  
    dataset.addValue(80.74, "BULLET","70");
    dataset.addValue(energ.get(2), "Proposed", "70");
       
    dataset.addValue(75.88, "Existing", "85");  
    dataset.addValue(78.48, "ICA-K_Means", "85");   
    dataset.addValue(82.75, "SCOOTER", "85");  
    dataset.addValue(84.79, "BULLET","85");
    dataset.addValue(energ.get(3), "Proposed", "85");
    
    dataset.addValue(80.49, "Existing", "100");  
    dataset.addValue(82.54, "ICA-K_Means", "100");   
    dataset.addValue(89.23, "SCOOTER", "100");  
    dataset.addValue(91.83, "BULLET","100");
    dataset.addValue(energ.get(4), "Proposed", "100");
    
    dataset.addValue(85.48, "Existing", "115");  
    dataset.addValue(87.53, "ICA-K_Means", "115");   
    dataset.addValue(92.55, "SCOOTER", "115");  
    dataset.addValue(95.52, "BULLET","115");
    dataset.addValue(energ.get(5), "Proposed", "115");
      return dataset; 
   }
   
   public static void main() {
      Energy chart = new Energy("Energy Consumption", 
         "");
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
}