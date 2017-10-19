import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.geotools.swing.JMapPane;
import org.opengis.geometry.Envelope;

public class Frame 
{  protected static JMapPane paneauMapPane;
   private static JFrame frame = new JFrame();
   private static JPanel pan = new JPanel();
   private static Outil_Bar outil ;
   public static int id_h=0;
   public static boolean admin = false;

	public Frame(Interface map) 
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
   
  		paneauMapPane.setBackground(Color.WHITE);
  		pan.add(paneauMapPane,BorderLayout.CENTER);
  		pan.add(outil, BorderLayout.EAST);
  	 
  		frame.add(pan);
  		frame.revalidate();
     }
	
	public static  void refrech(double a){
		Envelope env = paneauMapPane.getDisplayArea();
	    double delta = a;
	    ((com.vividsolutions.jts.geom.Envelope) env).expandBy(delta);
	    paneauMapPane.setDisplayArea(env);    
	    paneauMapPane.repaint();
	    
	    }
   
}
