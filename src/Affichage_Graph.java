
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JApplet;
import javax.swing.JFrame;

import org.jgrapht.ListenableGraph;
import org.jgrapht.demo.JGraphAdapterDemo;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;


/**
 * A demo applet that shows how to use JGraphX to visualize JGraphT graphs.
 * Applet based on JGraphAdapterDemo.
 *
 * @since July 9, 2013
 */
public class Affichage_Graph
    extends JApplet
{
    private static final long serialVersionUID = 2202072534703043194L;
    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

    private JGraphXAdapter<String, DefaultEdge> jgxAdapter;

    public static ArrayList<String> tab = new ArrayList<String>();
    String[] tran_places = new String [2]; 
    public static TreeMap<String,ArrayList<String[]>> tree = new TreeMap<String, ArrayList<String[]>>();
    
  

    /**
     * {@inheritDoc}
     */
    public void init()
    {
        ListenableGraph<String, DefaultEdge> g =
        		new ListenableDirectedGraph<String, DefaultEdge>(
                DefaultEdge.class);

        jgxAdapter = new JGraphXAdapter<>(g);

        getContentPane().add(new mxGraphComponent(jgxAdapter));
        resize(DEFAULT_SIZE);

      

        for (int i = 0; i < tab.size(); i++) {
         g.addVertex(tab.get(i));
		}
       
       for (Map.Entry<String,ArrayList<String []>> entree:tree.entrySet()) {
        	for (int i = 0; i < entree.getValue().size(); i++) {
        		g.addEdge(entree.getKey(),entree.getValue().get(i)[1],new TRANSITION_NAME<String>(entree.getKey(),entree.getValue().get(i)[1], "t"+entree.getValue().get(i)[0]));
			}
        	
		}

        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());

    }
    
    public static class TRANSITION_NAME<V> extends DefaultEdge {
        private V v1;
        private V v2;
        private String label;

        public TRANSITION_NAME(V v1, V v2, String label) {
            this.v1 = v1;
            this.v2 = v2;
            this.label = label;
        }

        public V getV1() {
            return v1;
        }

        public V getV2() {
            return v2;
        }

        public String toString() {
            return label;
        }}
}