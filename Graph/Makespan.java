
package Graph;


import static Code.Performance.M_Span;
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

public class Makespan extends ApplicationFrame {
   
   public Makespan( String applicationTitle , String chartTitle ) {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Number Of Tasks",            
         "Makespan (s)",           
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
      
    dataset.addValue(56, "ABC-Clustered", "100");  
    dataset.addValue(56, "ABC", "100");   
    dataset.addValue(55, "Firefly-CSA", "100");  
    dataset.addValue(53, "WOA","100");
    dataset.addValue(51, "SMO","100");
    dataset.addValue(M_Span.get(0), "Proposed", "100");
    
    dataset.addValue(71, "ABC-Clustered", "200");  
    dataset.addValue(69, "ABC", "200");   
    dataset.addValue(67, "Firefly-CSA", "200");  
    dataset.addValue(66, "WOA","200");
    dataset.addValue(65, "SMO","200");
    dataset.addValue(M_Span.get(1), "Proposed", "200");

    dataset.addValue(96, "ABC-Clustered", "300");  
    dataset.addValue(95, "ABC", "300");   
    dataset.addValue(95, "Firefly-CSA", "300");  
    dataset.addValue(94, "WOA","300");
    dataset.addValue(93, "SMO","300");
    dataset.addValue(M_Span.get(2), "Proposed", "300");
       
    dataset.addValue(125, "ABC-Clustered", "400");  
    dataset.addValue(122, "ABC", "400");   
    dataset.addValue(119, "Firefly-CSA", "400");  
    dataset.addValue(118, "WOA","400");
    dataset.addValue(116, "SMO","400");
    dataset.addValue(M_Span.get(3), "Proposed", "400");
    

      return dataset; 
   }
   
   public static void main() {
      Makespan chart = new Makespan("Makespan", 
         "");
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
}