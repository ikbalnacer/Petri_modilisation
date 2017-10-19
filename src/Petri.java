import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLDParser;
import org.geotools.styling.Style;
import org.geotools.styling.StyleFactory;
import org.geotools.swing.JMapPane;


public class Petri {
	 public static MapContent map = new MapContent();
	 protected static JMapPane paneauMapPane;
	 private static JFrame frame = new JFrame();
	 private static JPanel pan = new JPanel();
	 private static Outil_Bar outil ;	 
	 static  File file = new File("les_lines_.shp");
     static  File file1 = new File("Place.shp");
     static  File file2 = new File("Trasition.shp");
     static SimpleFeatureSource featureSource ;
     static SimpleFeatureSource featureSource1;
     static SimpleFeatureSource featureSource2 ;
  
       
	public Petri(Interface map) 
	{	paneauMapPane =   map.getMapPane(); 	
 		init();		
		frame.setSize(800, 650);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(3);		
	}
	 public static void init(){
   	  System.out.println("yeah");
   		outil = new Outil_Bar(paneauMapPane);
   		  System.out.println("yeah");
    		do_the_rest();
     }
	   public static void do_the_rest(){
	    	pan.setLayout(new BorderLayout());
	    	
	   		paneauMapPane.setBackground(Color.LIGHT_GRAY);
	   		pan.add(paneauMapPane,BorderLayout.CENTER);
	   		pan.add(outil, BorderLayout.EAST);
	   	 
	   		frame.add(pan);
	   		frame.revalidate();
	      }
	   

		public static void init_sir(MapContent map ) throws Exception 
		{ try{
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}
				catch (InstantiationException e) {}
				catch (ClassNotFoundException e) {}
				catch (UnsupportedLookAndFeelException e) {}
				catch (IllegalAccessException e) {}
			try
			{   
				init();
				new Petri(new Interface(map));
			}catch(Exception e){}

		   } 
	    catch (Throwable t) {
	   }
		}
	   
	   public static void main(String [] str)throws Exception{
		     FileDataStore store = FileDataStoreFinder.getDataStore(file);
		     FileDataStore store1 = FileDataStoreFinder.getDataStore(file1);
		     FileDataStore store2 = FileDataStoreFinder.getDataStore(file2);
		     featureSource = store.getFeatureSource();
	         featureSource1 = store1.getFeatureSource();
	         featureSource2 = store2.getFeatureSource();
	         BufferedReader sld1 = new BufferedReader(new InputStreamReader(new FileInputStream("linesld.sld")));

	 		 StyleFactory stylef1 = CommonFactoryFinder.getStyleFactory();
	 		 SLDParser stylereader1 = new SLDParser(stylef1, sld1);
	 		
	 		 Style styles1[] = stylereader1.readXML();
	         Layer layer = new FeatureLayer(featureSource, styles1[0]);

	         BufferedReader  sld12 = new BufferedReader(new InputStreamReader(new FileInputStream("closest_line.sld")));

	         StyleFactory stylef2 = CommonFactoryFinder.getStyleFactory();
	         SLDParser stylereader2 = new SLDParser(stylef2, sld12);
	 		
	         Style[] styles2 = stylereader2.readXML();
	         Layer layer1 = new FeatureLayer(featureSource1, styles2[0]);
	         
	         BufferedReader  sld = new BufferedReader(new InputStreamReader(new FileInputStream("point.sld")));

	         StyleFactory stylef = CommonFactoryFinder.getStyleFactory();
	         SLDParser stylereader = new SLDParser(stylef, sld);
	 		
	         Style[] styles = stylereader.readXML();
	         Layer layer2 = new FeatureLayer(featureSource2, styles[0]);
	         
	         map = new MapContent();
	         map.setTitle("Reseau de PETRI");
	         map.addLayer(layer);
	         map.addLayer(layer1);
	         map.addLayer(layer2);
	        
	         new Frame(new Interface(map));
	         Outil_Bar.supprimer_tout(Petri.featureSource);
	         Outil_Bar.supprimer_tout(Petri.featureSource1);
	         Outil_Bar.supprimer_tout(Petri.featureSource2);
	   }
	  
}
