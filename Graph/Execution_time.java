
package Graph;


import static Code.Performance.exe_time;
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

public class Execution_time extends ApplicationFrame {
   
   public Execution_time( String applicationTitle , String chartTitle ) {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Number Of Tasks",            
         "Execution Time (s)",           
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
      Collections.sort(exe_time);
    dataset.addValue(229.32, "BULLET", "15");  
    dataset.addValue(255.37, "PSO-HPC", "15");   
    dataset.addValue(297.07, "PSO-SW", "15");  
    dataset.addValue(359.61, "PSO-DVFS","15");
    dataset.addValue(exe_time.get(0), "Proposed", "15");
    
    dataset.addValue(375.24, "BULLET", "30");  
    dataset.addValue(411.73, "PSO-HPC", "30");   
    dataset.addValue(453.42, "PSO-SW", "30");  
    dataset.addValue(510.75, "PSO-DVFS","30");
    dataset.addValue(exe_time.get(1), "Proposed", "30");

    dataset.addValue(526.38, "BULLET", "45");  
    dataset.addValue(594.14, "PSO-HPC", "45");   
    dataset.addValue(635.83, "PSO-SW", "45");  
    dataset.addValue(726.64, "PSO-DVFS","45");
    dataset.addValue(exe_time.get(2), "Proposed", "45");
       
    dataset.addValue(771.34, "BULLET", "60");  
    dataset.addValue(807.82, "PSO-HPC", "60");   
    dataset.addValue(854.72, "PSO-SW", "60");  
    dataset.addValue(938.11, "PSO-DVFS","60");
    dataset.addValue(exe_time.get(3), "Proposed", "60");
    
    dataset.addValue(927.69, "BULLET", "75");  
    dataset.addValue(953.75, "PSO-HPC", "75");   
    dataset.addValue(1000.65, "PSO-SW", "75");  
    dataset.addValue(1094.46, "PSO-DVFS","75");
    dataset.addValue(exe_time.get(4), "Proposed", "75");
    
    dataset.addValue(1198.70, "BULLET", "90");  
    dataset.addValue(1240.39, "PSO-HPC", "90");   
    dataset.addValue(1375.90, "PSO-SW", "90");  
    dataset.addValue(1506.19, "PSO-DVFS","90");
    dataset.addValue(exe_time.get(5), "Proposed", "90");


      return dataset; 
   }
   
   public static void main() {
      Execution_time chart = new Execution_time("Execution Time", 
         "");
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
}