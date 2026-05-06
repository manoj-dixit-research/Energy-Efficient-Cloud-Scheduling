
package Graph;


import static Code.Performance.Res_Util;
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

public class Resource_Util extends ApplicationFrame {
   
   public Resource_Util( String applicationTitle , String chartTitle ) {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Number Of Tasks",            
         "Resource Utility (%)",           
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
      Collections.sort(Res_Util);
    dataset.addValue(68, "ABC-Clustered", "100");  
    dataset.addValue(69, "ABC", "100");   
    dataset.addValue(72, "Firefly-CSA", "100");  
    dataset.addValue(73, "WOA","100");
    dataset.addValue(76, "SMO","100");
    dataset.addValue(Res_Util.get(0), "Proposed", "100");
    
    dataset.addValue(68, "ABC-Clustered", "200");  
    dataset.addValue(71, "ABC", "200");   
    dataset.addValue(73, "Firefly-CSA", "200");  
    dataset.addValue(74, "WOA","200");
    dataset.addValue(77, "SMO","200");
    dataset.addValue(Res_Util.get(1), "Proposed", "200");

    dataset.addValue(70, "ABC-Clustered", "300");  
    dataset.addValue(70, "ABC", "300");   
    dataset.addValue(70, "Firefly-CSA", "300");  
    dataset.addValue(72, "WOA","300");
    dataset.addValue(75, "SMO","300");
    dataset.addValue(Res_Util.get(2), "Proposed", "300");
       
    dataset.addValue(70, "ABC-Clustered", "400");  
    dataset.addValue(73, "ABC", "400");   
    dataset.addValue(75, "Firefly-CSA", "400");  
    dataset.addValue(76, "WOA","400");
    dataset.addValue(79, "SMO","400");
    dataset.addValue(Res_Util.get(3), "Proposed", "400");
    

      return dataset; 
   }
   
   public static void main() {
      Resource_Util chart = new Resource_Util("Resource Utility", 
         "");
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
}