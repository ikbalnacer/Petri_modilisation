
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* Abreviation for Accessible Marking Graph */
public class AMG
{
	private List<Integer[]> nodes;
	/* The first arg is an index of a marking element
	 * from the field list "nodes",
	 * the other is a list of pairs (t,index) where:
	 * "t" is a transition number, "index" is
	 * the marking generated from the key marking
	 * using the transition "t" */
	private Map<Integer,List<Integer[]>> arcs;

	public AMG(List<Integer[]> nodes, Map<Integer, List<Integer[]>> arcs)
	{
		this.nodes = nodes;
		this.arcs = arcs;
	}

	public void addNode(Integer[] marking)
	{
		nodes.add(marking);
		arcs.put(this.getNodeIndex(marking),null);
	}

	public void addArc(int sourceNodeIndex, Integer[] newArc)
	{
		List<Integer[]> ancientArcs = arcs.get(sourceNodeIndex);
		if (ancientArcs == null)
		{
			ancientArcs = new ArrayList<>();
		}
		ancientArcs.add(newArc);
		arcs.put(sourceNodeIndex, ancientArcs);
	}

	public int getNodeIndex(Integer[] marking)
	{
		for (int i = 0; i < nodes.size(); i++)
		{
			if (equalsArrays(marking,nodes.get(i)))
			{
				return i;
			}
		}

		return -1;
	}

	public static boolean equalsArrays(Integer[] t1, Integer[] t2)
	{
		if (t1 == null || t2 == null)
		{
			return false;
		}
		if (t1.length != t2.length)
		{
			return false;
		}
		for (int i = 0; i < t1.length; i++)
		{
			if (t1[i].intValue() != t2[i].intValue())
			{
				return false;
			}
		}

		return true;
	}
    public String nodeToString(int nodeIndex)
    throws AMGException
    {
    	if (nodeIndex < 0 || nodeIndex >= nodes.size())
    	{
    		throw new AMGException("Invalid node index");
    		
    	}
    	
    		Integer[] node = nodes.get(nodeIndex);
    		String nodeStr = "[";
    		for (Integer val : node)
    		{
    			nodeStr += val.intValue()+",";
    		}
    		nodeStr.substring(0, nodeStr.lastIndexOf(","));
    		nodeStr += "]";
    		
    		return nodeStr;
    }

	public List<Integer[]> getNodes() {
		return nodes;
	}

	public Map<Integer, List<Integer[]>> getArcs() {
		return arcs;
	}
	
	
}

