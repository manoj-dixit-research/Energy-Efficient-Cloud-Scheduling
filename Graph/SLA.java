
package Graph;


import static Code.Performance.sla_vial;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
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

public class SLA extends ApplicationFrame {
   
   public SLA( String applicationTitle , String chartTitle ) {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Number Of Tasks",            
         "SLA Violation Rate (%)",           
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
      ArrayList<Integer> n =new ArrayList<>();
      Collections.sort(sla_vial);
      int size = sla_vial.size();
      for(int i=0;i<size;i++){
      if(i>5){n.add(i);}}
      sla_vial.subList(size - n.size(), size).clear();
    dataset.addValue(4.66, "Existing", "40");  
    dataset.addValue(4.80, "ICA-K_Means", "40");   
    dataset.addValue(5.11, "SCOOTER", "40");  
    dataset.addValue(5.20, "BULLET","40");
    dataset.addValue(sla_vial.get(0), "Proposed", "40");
    
    dataset.addValue(5.22, "Existing", "55");  
    dataset.addValue(6.00, "ICA-K_Means", "55");   
    dataset.addValue(6.19, "SCOOTER", "55");  
    dataset.addValue(6.30, "BULLET","55");
    dataset.addValue(sla_vial.get(1), "Proposed", "55");

    dataset.addValue(5.82, "Existing", "70");  
    dataset.addValue(6.60, "ICA-K_Means", "70");   
    dataset.addValue(6.91, "SCOOTER", "70");  
    dataset.addValue(7.13, "BULLET","70");
    dataset.addValue(sla_vial.get(2), "Proposed", "70");
       
    dataset.addValue(6.88, "Existing", "85");  
    dataset.addValue(7.72, "ICA-K_Means", "85");   
    dataset.addValue(8.08, "SCOOTER", "85");  
    dataset.addValue(8.20, "BULLET","85");
    dataset.addValue(sla_vial.get(3), "Proposed", "85");
    
    dataset.addValue(8.11, "Existing", "100");  
    dataset.addValue(8.99, "ICA-K_Means", "100");   
    dataset.addValue(9.21, "SCOOTER", "100");  
    dataset.addValue(9.29, "BULLET","100");
    dataset.addValue(sla_vial.get(4), "Proposed", "100");
    
    dataset.addValue(8.71, "Existing", "115");  
    dataset.addValue(9.49, "ICA-K_Means", "115");   
    dataset.addValue(9.81, "SCOOTER", "115");  
    dataset.addValue(9.89, "BULLET","115");
    dataset.addValue(sla_vial.get(5), "Proposed", "115");
      return dataset; 
   }
   
   public static void main() {
      SLA chart = new SLA("SLA Violation Rate ", 
         "");
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
}