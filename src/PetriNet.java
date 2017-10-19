

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PetriNet
{

	private Integer[][] pre;
	private Integer[][] post;

	public PetriNet(Integer[][] pre, Integer[][] post)
	{
		this.pre = pre;
		this.post = post;
	}
//franhissement
	public static boolean isPositive(Integer[][] matrix)
	{
		for (Integer[] row : matrix)
		{
			for (Integer sqr : row)
			{
				if (sqr.intValue() < 0)
				{
					return false;
				}
			}
		}

		return true;
	}

	public Integer[][] incidenceMatrix()
	{
		Integer[][] incidence = new Integer[pre.length][pre[0].length];
		for (int row = 0; row < pre.length; row++)
		{
			for (int col = 0; col < pre[0].length; col++)
			{
				incidence[row][col] = post[row][col] - pre[row][col];
			}
		}

		return incidence;
	}

	/* return: tableau d'erreurs. null sinon */
	public static String areValid(Integer[][] pre, Integer[][] post)
	{
		String errs = "";
		if (pre == null || pre.length < 1 || pre[0].length < 1 || post == null)
		{
			errs += "-The Petri Net must have at least one place and one transition\n";
		}
		if (pre != null && post != null && (pre.length != post.length || pre[0].length != post[0].length))
		{
			errs += "-The matrices PRE and POST must have the same dimension\n";
		}
		if (pre != null && post != null && (!isPositive(pre)|| !isPositive(post)))
		{
			errs += "-Neither the matrix PRE nor POST should have a negative values\n";
		}

		if (!errs.isEmpty())
		{
			return errs;
		}

		return null;
	}
//franhissable or not
	public boolean isTransitionExecutableBy(int transition, Integer[] marking)
	throws AMGException
	{
		if (transition < 0 || transition >= pre[0].length)
		{
			throw new AMGException("Invalid transition number");
		}
		if (marking == null || marking.length != pre.length)
		{
			throw new AMGException("The marking matrix must have the same size as the place number");
		}

		for (int row = 0; row < pre.length; row++)
		{  System.out.println("MARKING : "+marking[row]+"  MARkage :"+pre[row][transition]);
			if (marking[row] < pre[row][transition])
			{   
				return false;
			}
		}

		return true;
	}

	/* return: tableau contenant tous les numeros des transitions executables */
	public Integer[] getTransitionsExecutableBy(Integer[] marking)
	throws AMGException
	{
		List<Integer> exe = new ArrayList<>();
		for (int transition = 0; transition < pre[0].length; transition++)
		{
			if (isTransitionExecutableBy(transition, marking))
			{
				exe.add(new Integer(transition));
			}
		}

		return exe.toArray(new Integer[exe.size()]);
	}

	public Integer[] getNextMarkingFrom(int transition, Integer[] marking)
	throws AMGException
	{
		if (!isTransitionExecutableBy(transition, marking))
		{
			throw new AMGException("This marking cannot execute this transition");
		}

		Integer[][] incidence = incidenceMatrix();

		Integer[] next = new Integer[marking.length];

		for (int row = 0; row < marking.length; row++)
		{
			next[row] = marking[row] + incidence[row][transition];
		}

		return next;
	}

	/* Map first param: transition number, the second array
	 * is the resulting marking from the key transition */
	public Map<Integer,Integer[]> getAllMarkingsFrom(Integer[] marking)
	throws AMGException
	{
		Map<Integer,Integer[]> allMarkings = new TreeMap<>();

		Integer[] exeTransitions = getTransitionsExecutableBy(marking);

		for (Integer transition : exeTransitions)
		{
			allMarkings.put(transition, getNextMarkingFrom(transition, marking));
		}

		return allMarkings;
	}
}
