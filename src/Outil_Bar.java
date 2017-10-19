import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import org.geotools.data.DataUtilities;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.Transaction;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geometry.jts.GeometryBuilder;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.swing.JMapPane;
import org.geotools.swing.event.MapMouseEvent;
import org.geotools.swing.tool.CursorTool;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.identity.FeatureId;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.Point;

	public class Outil_Bar extends JPanel  
	{  
		static ArrayList<Integer> place = new ArrayList<Integer>();
		
		static TreeMap<String,ArrayList<String>> pre_tree = new TreeMap<String, ArrayList<String>>();
		
		static TreeMap<String,ArrayList<String>> post_tree = new TreeMap<String, ArrayList<String>>();
		
		static int pre_post=0;
		static int i =0;
		static int j =0;
		static int Transition_name =0;
		static  Point first_point =null;
		static String identifier_P="";
		static String identifier_T="";
		static  Point second_point =null;
		static Point point_ =null;
		static Point point_1 =null;
		static String puissance ="";
		private static final long serialVersionUID = 1L;
		  static String str="[";
		public Outil_Bar() 
		{
			super();
		}

		public Outil_Bar (JMapPane paneauMapPane) 
		{
			JToolBar temp = new JToolBar(1);
			temp.setFloatable(false);
			temp.setRollover(true);
			JButton button = new JButton(new ImageIcon("first.png"));
			
			button.setPreferredSize(new Dimension(85, 80));
			temp.add(button);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					   Frame.paneauMapPane.setCursorTool(new CursorTool() {
					   @Override
					   public void onMouseClicked(MapMouseEvent ev) {
						   java.awt.Point screenPos = ev.getPoint();		
						   GeometryFactory geaomf = new GeometryFactory();
						   point_=  geaomf.createPoint(new Coordinate(ev.getMapPosition().getX(),ev.getMapPosition().getY()));
					       Rectangle screenRect = new Rectangle(screenPos.x-20, screenPos.y-20, 50, 50);
					       Rectangle2D worldRect = getMapPane().getScreenToWorldTransform().createTransformedShape(screenRect).getBounds2D();							       
					       ReferencedEnvelope bbox = new ReferencedEnvelope(
					               worldRect,
					               getMapPane().getMapContent().getCoordinateReferenceSystem());
						  if(j==0){					  
					      first_point = get_geom(bbox, Petri.featureSource1,2);
					      if(first_point!=null)
					      { j++;
					      System.out.println(first_point.getX());
					      point_1=point_;
					      }
					      else{
					    	  System.out.println("none");
					      }
						  }else{						
						      second_point = get_geom(bbox, Petri.featureSource2,3); 
						      if(second_point!=null){
	                          j=0;
	                          System.out.println(second_point.getX());
	                          Coordinate[] coords = {new Coordinate(point_1.getX(),point_1.getY()),
	                        		  new Coordinate(point_.getX(),point_.getY())};
						      try {
						    	  String nom = JOptionPane.showInputDialog(null, "LA puissance SVP !", "puissance",
										   JOptionPane.QUESTION_MESSAGE);
								ajouterfeature(coords,Integer.parseInt(nom));
							} catch (IOException e) {
								
							}
						      }
						      
						  }
						}
					});
							}
			});
            JButton button1 = new JButton(new ImageIcon("second.png"));
			button1.setPreferredSize(new Dimension(85, 80));
			temp.add(button1);
            button1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					   Frame.paneauMapPane.setCursorTool(new CursorTool() {
					   @Override
					   public void onMouseClicked(MapMouseEvent ev) {
						   java.awt.Point screenPos = ev.getPoint();		
						   GeometryFactory geaomf = new GeometryFactory();
						   point_=  geaomf.createPoint(new Coordinate(ev.getMapPosition().getX(),ev.getMapPosition().getY()));
					       Rectangle screenRect = new Rectangle(screenPos.x-20, screenPos.y-20, 50, 50);
					       Rectangle2D worldRect = getMapPane().getScreenToWorldTransform().createTransformedShape(screenRect).getBounds2D();							       
					       ReferencedEnvelope bbox = new ReferencedEnvelope(
					               worldRect,
					               getMapPane().getMapContent().getCoordinateReferenceSystem());
						  if(j==0){					  
					      first_point = get_geom(bbox, Petri.featureSource2,4);
					      if(first_point!=null)
					      { j++;
					      System.out.println(first_point.getX());
					      point_1=point_;
					      }
					      else{
					    	  System.out.println("none");
					      }
						  }else{						
						      second_point = get_geom(bbox, Petri.featureSource1,5); 
						      if(second_point!=null){
	                          j=0;
	                          System.out.println(second_point.getX());
	                          Coordinate[] coords = {new Coordinate(point_1.getX(),point_1.getY()),
	                        		  new Coordinate(point_.getX(),point_.getY())};
						      try {
						    	  String nom = JOptionPane.showInputDialog(null, "donnez la puissance SVP !", "puissance",
										   JOptionPane.QUESTION_MESSAGE);
								ajouterfeature(coords,Integer.parseInt(nom));
							} catch (IOException e) {
								
							}
						      }
						      
						  }
						}
					});
							}
			});
			
			
            JButton button3 = new JButton(new ImageIcon("forth.png"));
            button3.setPreferredSize(new Dimension(85, 60));
			temp.add(button3);
			button3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					   Frame.paneauMapPane.setCursorTool(new CursorTool() {
					   @Override
					   public void onMouseClicked(MapMouseEvent ev) {
						   try {
							   String nom = JOptionPane.showInputDialog(null, "Numero du resources", "PLACES",
									   JOptionPane.QUESTION_MESSAGE);
							ajouterfeature(Petri.featureSource1 ,new Coordinate(ev.getMapPosition().getX(),ev.getMapPosition().getY()),nom);
						    Frame.refrech(0.0000);
						   } catch (IOException e) {
						
						}		
						}
					});
							}
			});
			
			
			JButton button4 = new JButton(new ImageIcon("fifth.png"));
	        button4.setPreferredSize(new Dimension(85, 60));
		    temp.add(button4);
				
		    button4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
						    Frame.paneauMapPane.setCursorTool(new CursorTool() {
					   @Override
					   public void onMouseClicked(MapMouseEvent ev) {
						   try {
							 
							   Ajouter_Transition(Petri.featureSource2 ,new Coordinate(ev.getMapPosition().getX(),ev.getMapPosition().getY()),Transition_name);
							   Transition_name++;
							   Frame.refrech(0.0000);
						   } catch (IOException e) {
							
						}			
						}
					});
							}
			});
		 
			JButton button8 = new JButton(new ImageIcon("Sans titre.png"));
	        button8.setPreferredSize(new Dimension(85, 80));
		    temp.add(button8);
			button8.addActionListener(new  ActionListener() {		
					@Override
					public void actionPerformed(ActionEvent arg0) {
						try {
							      AMG amg = generateAMG();
						for (int i = 0; i <amg.getNodes().size(); i++) {
						
							Affichage_Graph.tab.add(amg.nodeToString(i));
							
							}
						
						Map<Integer,List<Integer[]>> arcs =  amg.getArcs();
						for (Integer nodeIndx : arcs.keySet())
						{
							String parent = amg.nodeToString(nodeIndx);
							List<Integer[]> children = arcs.get(nodeIndx);
							if (children != null)
							{
								ArrayList<String[]> ch = new ArrayList<String[]>();
								for (Integer[] pair : children)
								{
									String transition = pair[0].toString();
									String node = amg.nodeToString(pair[1]);
									ch.add(new String []{transition,node});
									System.out.println("nnode :"+node + " transition : "+transition);
								}
								Affichage_Graph.tree.put(parent,ch);

							}
							
						}
						Affichage_Graph applet = new Affichage_Graph();
		                applet.init();

		                JFrame frame = new JFrame();
		                frame.getContentPane().add(applet);
		                frame.setTitle("JGraphT Adapter to JGraph Demo");
		               
		                frame.pack();
		                frame.setVisible(true);
		                
						} catch (AMGException e) {
							JOptionPane.showInputDialog(null, e.getMessage(), "Erreur",
									   JOptionPane.OK_OPTION);
						}
					}
				});
			
	
		    JButton button6 = new JButton(new ImageIcon("seven.png"));
            button6.setPreferredSize(new Dimension(85, 80));
			temp.add(button6);
			button6.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
						    Frame.paneauMapPane.setCursorTool(new CursorTool() {
					   @Override
					   public void onMouseClicked(MapMouseEvent ev) {
						   try {
								   java.awt.Point screenPos = ev.getPoint();		
							       Rectangle screenRect = new Rectangle(screenPos.x-20, screenPos.y-20, 50, 50);
							       Rectangle2D worldRect = getMapPane().getScreenToWorldTransform().createTransformedShape(screenRect).getBounds2D();							       
							       ReferencedEnvelope bbox = new ReferencedEnvelope(
							               worldRect,
							               getMapPane().getMapContent().getCoordinateReferenceSystem());
							       get_geom(bbox, Petri.featureSource2,1);
							       get_geom(bbox, Petri.featureSource1,1);
						   } catch (Exception e) {
							e.printStackTrace();
						}			
						}
					});
							}
			});
		    
		
			JButton button7 = new JButton(new ImageIcon("eit.png"));
	        button7.setPreferredSize(new Dimension(85, 80));
		    temp.add(button7);
			button7.addActionListener(new  ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						supprimer_tout(Petri.featureSource);
						supprimer_tout(Petri.featureSource1);
						supprimer_tout(Petri.featureSource2);
						i=0;
						Transition_name=0;
						place=new ArrayList<Integer>();
						pre_tree = new TreeMap<String, ArrayList<String>>();
						post_tree = new TreeMap<String, ArrayList<String>>();
					}
				});
		
			
			this.setLayout(new BorderLayout());
			this.add(temp,BorderLayout.CENTER);
		}

		public void ajoutBar(JPanel pan,int pos)
		{

			if (pos==0) 
			{
				this.add(pan,BorderLayout.NORTH);
			} else 
			{
				this.add(pan,BorderLayout.SOUTH);
			}
		}

		public void supprimBar(JMapPane paneauMapPane)
		{
			new Outil_Bar(paneauMapPane);
		}
		
		public static void supprimer_tout(SimpleFeatureSource featureSource1){
			
		     SimpleFeatureStore store1 = (SimpleFeatureStore) featureSource1;
		  	  Filter filter = Filter.INCLUDE;   	 
		  	  Transaction transaction = new DefaultTransaction("removeExample");
		      store1.setTransaction(transaction);
		   try {
		       store1.removeFeatures(filter);
		       transaction.commit();
		   } catch (Exception eek) {
				try {
					transaction.rollback();
				} catch (IOException e) {
					
				}		
		   } 
		   Frame.refrech(0.0000);
   }
		

public static void ajouterfeature(SimpleFeatureSource featureSource1 ,Coordinate c,String nom) throws IOException{
	  
	   SimpleFeatureStore store = (SimpleFeatureStore) featureSource1;
	   System.out.println(store.getName()+" yeah yeah");
	  
	   GeometryBuilder geom = new GeometryBuilder();
       SimpleFeatureType featureType = store.getSchema();
       SimpleFeatureBuilder build = new SimpleFeatureBuilder(featureType);
       List<SimpleFeature> list = new ArrayList<SimpleFeature>();
       list.add(build.buildFeature("fid1", new Object[]{ geom.point(c.x,c.y),"yes","p : "+i +" et R : "+nom}));
       SimpleFeatureCollection collection = new ListFeatureCollection(featureType, list);
       place.add(Integer.parseInt(nom));
       i++;
       Transaction transaction = new DefaultTransaction("Add Example");
         store.setTransaction( transaction );
       try {
            store.addFeatures( collection );
            transaction.commit();
       }
       catch( Exception eek){
            transaction.rollback();
        }
   
}

public static void Ajouter_Transition(SimpleFeatureSource featureSource1 ,Coordinate c,int nom) throws IOException{
	SimpleFeatureStore store = (SimpleFeatureStore) featureSource1;
	System.out.println(store.getName()+" yeah yeah");
	GeometryBuilder geom = new GeometryBuilder();
	
    SimpleFeatureType featureType = store.getSchema();
    
    SimpleFeatureBuilder build = new SimpleFeatureBuilder(featureType);
    List<SimpleFeature> list = new ArrayList<SimpleFeature>();
    
    list.add(build.buildFeature("fid1", new Object[]{ geom.point(c.x,c.y),"yes",nom}));
    SimpleFeatureCollection collection = new ListFeatureCollection(featureType, list);
    
    
    pre_tree.put(Integer.toString(nom), new ArrayList<String>(place.size()));
    post_tree.put(Integer.toString(nom), new ArrayList<String>(place.size()));
    Transaction transaction = new DefaultTransaction("Add Example");
      store.setTransaction( transaction );
    try {
         store.addFeatures( collection );
         transaction.commit(); 
         }
    catch( Exception eek){
         transaction.rollback();
     }

}

public static void ajouterfeature(Coordinate[] coords,int puissance) throws IOException{
	
	try {
		  final SimpleFeatureType TYPE = DataUtilities.createType("Location",
		            "the_geom:MultiLineString:srid=4326," + // <- the geometry attribute: Polyline type
		                    "numero:Integer," + "objectid:Integer"// <- a String attribute
		                    ); 
		  GeometryFactory geaomf = new GeometryFactory();
	      SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
	  //    Coordinate[] coords  =new Coordinate[] {c,c1 };

	      LineString line = geaomf.createLineString(coords);
	      LineString[] linet = new LineString[1];
	      linet[0]=line;
	      MultiLineString route = geaomf.createMultiLineString(linet);
	      
		  featureBuilder.add(route);
			
		  featureBuilder.add(puissance);
		  featureBuilder.add(puissance);
		
	      SimpleFeature feature = featureBuilder.buildFeature(null);
	      SimpleFeatureStore store1 = (SimpleFeatureStore) Petri.featureSource;

	      SimpleFeatureType featureType = store1.getSchema();
	   	   List<SimpleFeature> list = new ArrayList<SimpleFeature>();
	       list.add(feature);
	       SimpleFeatureCollection collection = new ListFeatureCollection(featureType, list);
	   	  if(pre_post==0){
	   	  pre_tree.get(identifier_T).add(identifier_P.substring(4, 5));
	   	  pre_tree.get(identifier_T).add(Integer.toString(puissance));
	   	  }
	   	  if(pre_post==1){
	   	  post_tree.get(identifier_T).add(identifier_P.substring(4, 5));
	      post_tree.get(identifier_T).add(Integer.toString(puissance));
	   	  System.out.println(identifier_T);
	   	  }
	       Transaction transaction = new DefaultTransaction("Add Example");
		   store1.setTransaction( transaction );
		   
		         try {
	              store1.addFeatures(collection);
	              transaction.commit();
	              Frame.refrech(0.0000);
	         }
	         catch( Exception eek){
	              transaction.rollback();
	          }
	} catch (SchemaException e) {
	
	} 
	
}




public static Point get_geom(ReferencedEnvelope bbox ,SimpleFeatureSource featureSource,int i){
	 Point point =null;
	 FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();          	 
	 SimpleFeatureType schema = featureSource.getSchema();

     String geometryAttributeName = schema.getGeometryDescriptor().getLocalName();
     Filter filter = ff.bbox(ff.property(geometryAttributeName), bbox);
  	 SimpleFeatureCollection selectedFeatures1;
	try {
		selectedFeatures1 = featureSource.getFeatures(filter);
  	 SimpleFeatureIterator iter1 = selectedFeatures1.features();
  	 Set<FeatureId> IDs = new HashSet<FeatureId>();     
     try {
        while (iter1.hasNext()  ) {       
           SimpleFeature feature = iter1.next();

           IDs.add(feature.getIdentifier());
           point=(com.vividsolutions.jts.geom.Point) feature.getAttribute("the_geom");
            if(i==2)
            	identifier_P= feature.getAttribute("jeton").toString();              
            if(i==3){
            	identifier_T= feature.getAttribute("Name").toString();;            
            	pre_post=0;
            }
            if(i==4)
            {
            	identifier_T= feature.getAttribute("Name").toString();;          	
            }
            if(i==5)
            {
            	identifier_P= feature.getAttribute("jeton").toString();
            	 pre_post=1;
            }
            
            if(!IDs.isEmpty()&&i==1){     
           supprimerfeature(featureSource,filter);
            }else{
            	return point;	
            }
        }
     } finally {
        iter1.close();
     }      
	} catch (IOException e1) {
	}	
	return null;
      } 



public static void supprimerfeature(SimpleFeatureSource featureSource1,Filter filter) throws IOException{
	 SimpleFeatureStore store = (SimpleFeatureStore) featureSource1;
 	 Transaction transaction = new DefaultTransaction("removeExample");
     store.setTransaction(transaction);
  try {
     store.removeFeatures(filter);
     transaction.commit();
     } catch (Exception eek) {
		try {
	 transaction.rollback();
		} catch (IOException e) {
		
		}
		
  } 
  Frame.refrech(0.0000);

}

public static Integer[][] getMatrixFromMap(Map<String,ArrayList<String>> p)
{
	Set<String> transitions = p.keySet();
	Integer[][] matrix = new Integer[place.size()][transitions.size()];
	for (int i = 0; i <matrix.length; i++) {
		for (int j = 0; j < matrix[0].length; j++) {
			matrix [i][j]=0;
		}
	}
	for (String transition : transitions)
	{
		int t = Integer.parseInt(transition);
		List<String> markedPlaces = p.get(transition);
		if (markedPlaces != null)
		for (int i = 0; i < markedPlaces.size(); i += 2)
		{
			matrix[Integer.parseInt(markedPlaces.get(i))][t] = Integer.parseInt(markedPlaces.get(i+1));
		}
	}

	return matrix;
}

public static AMG generateAMG()
throws AMGException
{
	AMG graph = new AMG(new ArrayList<Integer[]>(),new TreeMap<Integer, List<Integer[]>>());
	Integer[][] pre = getMatrixFromMap(pre_tree);
	Integer[][] post = getMatrixFromMap(post_tree);
/*	String errs = PetriNet.areValid(pre, post);
	if (errs != null)
	{
		throw new AMGException(errs);
	}*/

	PetriNet pn = new PetriNet(pre,post);
	List<Integer[]> stack = new ArrayList<>();
	Integer[] m0 = place.toArray(new Integer[place.size()]);
	graph.addNode(m0);
	stack.add(m0);
	Integer[] mark = null;

	while (stack.size() != 0)
	{
		mark = stack.get(0);
		Map<Integer, Integer[]> markingsFrom = pn.getAllMarkingsFrom(mark);
		Set<Integer> transitions = markingsFrom.keySet();
		for (Integer t : transitions)
		{
			Integer[] m = markingsFrom.get(t);
			if (graph.getNodeIndex(m) == -1)
			{
				graph.addNode(m);
				stack.add(m);
			}
			graph.addArc(graph.getNodeIndex(mark), new Integer[]{t,graph.getNodeIndex(m)});
		}

		stack.remove(0);
	}

	return graph;
}


	}

