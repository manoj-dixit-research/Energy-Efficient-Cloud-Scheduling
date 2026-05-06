
package Graph;


import static Code.Performance.Response;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
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

public class Response_Time extends ApplicationFrame {
   
   public Response_Time( String applicationTitle , String chartTitle ) {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Number Of Tasks",            
         "Response Time (s)",           
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

    Collections.sort(Response);
    dataset.addValue(321, "ABC-Clustered", "100");  
    dataset.addValue(313, "ABC", "100");   
    dataset.addValue(307, "Firefly-CSA", "100");  
    dataset.addValue(296, "WOA","100");
    dataset.addValue(287, "SMO","100");
    dataset.addValue(Response.get(0), "Proposed", "100");
    
    dataset.addValue(342, "ABC-Clustered", "200");  
    dataset.addValue(332, "ABC", "200");   
    dataset.addValue(320, "Firefly-CSA", "200");  
    dataset.addValue(315, "WOA","200");
    dataset.addValue(301, "SMO","200");
    dataset.addValue(Response.get(1), "Proposed", "200");

    dataset.addValue(396, "ABC-Clustered", "300");  
    dataset.addValue(388, "ABC", "300");   
    dataset.addValue(382, "Firefly-CSA", "300");  
    dataset.addValue(371, "WOA","300");
    dataset.addValue(356, "SMO","300");
    dataset.addValue(Response.get(2), "Proposed", "300");
       
    dataset.addValue(429, "ABC-Clustered", "400");  
    dataset.addValue(421, "ABC", "400");   
    dataset.addValue(413, "Firefly-CSA", "400");  
    dataset.addValue(408, "WOA","400");
    dataset.addValue(400, "SMO","400");
    dataset.addValue(Response.get(3), "Proposed", "400");
    

      return dataset; 
   }
   
   public static void main() {
      Response_Time chart = new Response_Time("Response Time", 
         "");
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
}