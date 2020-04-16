import java.util.ArrayList;

/**
 * Object to hold outgoing paths from a given vertex in a {@link Graph}.
 * 
 * @author Joon Song
 * @since April 16, 2020
 */
public class Paths {
	protected boolean[] marked;
	protected int[] edgeTo;
	protected int s; 
	
	/**
	 * Creates a {@link Paths} object with {@link Graph} G and source node s.
	 * 
	 * @param G The {@link Graph} to determine paths from.
	 * @param s The source node to determine paths from.
	 */
	public Paths(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		
		for (int i = 0; i < G.V(); i++) marked[i] = false;		
	}
	
	/**
	 * Determines whether there is a path from s to v.
	 * 
	 * @param v The node to check if there is a path to from s.
	 * @return Whether there is a path from s to v.
	 */
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	/**
	 * Determines the path from s to v.
	 * 
	 * @param v The node to determine the path from s.
	 * @return The path from s to v.
	 */
	public ArrayList<Integer> pathTo(int v){
		if (!hasPathTo(v)) return null;
		
		ArrayList<Integer> path = new ArrayList<Integer>();
		for (int x = v; x != s; x = edgeTo[x]) {
			path.add(0, x);
		}
		
		path.add(s, 0);
		return path;
	}
}
