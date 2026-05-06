
package Graph;


import static Code.Performance.delay;
import java.awt.Color;
import java.awt.Font;
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

public class Delay extends ApplicationFrame {
   
   public Delay( String applicationTitle , String chartTitle ) {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Number Of Tasks",            
         "Delay (sec)",           
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
      
    dataset.addValue(1.20, "Existing", "40");  
    dataset.addValue(1.38, "ICA-K_Means", "40");   
    dataset.addValue(1.72, "SCOOTER", "40");  
    dataset.addValue(1.77, "BULLET","40");
    dataset.addValue(delay.get(0), "Proposed", "40");
    
    dataset.addValue(3.08, "Existing", "55");  
    dataset.addValue(3.29, "ICA-K_Means", "55");   
    dataset.addValue(3.67, "SCOOTER", "55");  
    dataset.addValue(3.90, "BULLET","55");
    dataset.addValue(delay.get(1), "Proposed", "55");

    dataset.addValue(4.08, "Existing", "70");  
    dataset.addValue(4.37, "ICA-K_Means", "70");   
    dataset.addValue(4.71, "SCOOTER", "70");  
    dataset.addValue(4.88, "BULLET","70");
    dataset.addValue(delay.get(2), "Proposed", "70");
       
    dataset.addValue(5.80, "Existing", "85");  
    dataset.addValue(6.06, "ICA-K_Means", "85");   
    dataset.addValue(6.39, "SCOOTER", "85");  
    dataset.addValue(6.78, "BULLET","85");
    dataset.addValue(delay.get(3), "Proposed", "85");
    
    dataset.addValue(7.08, "Existing", "100");  
    dataset.addValue(7.41, "ICA-K_Means", "100");   
    dataset.addValue(7.60, "SCOOTER", "100");  
    dataset.addValue(8.10, "BULLET","100");
    dataset.addValue(delay.get(4), "Proposed", "100");
    
    dataset.addValue(8.21, "Existing", "115");  
    dataset.addValue(8.58, "ICA-K_Means", "115");   
    dataset.addValue(8.89, "SCOOTER", "115");  
    dataset.addValue(9.19, "BULLET","115");
    dataset.addValue(delay.get(5), "Proposed", "115");
      return dataset; 
   }
   
   public static void main() {
      Delay chart = new Delay("Delay", 
         "");
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
}