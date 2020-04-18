import java.util.ArrayList;
/**
 * Object to compute a topological sort of an acyclical {@link Digraph}, computed in a DFS manner.
 * 
 * @author Joon Song
 * @since April 16, 2020
 */
public class DepthFirstOrder {
	
	private boolean[] marked;
	private ArrayList<Integer> order;
	
	/**
	 * Creates a {@link DepthFirstOrder} object with {@link Digraph} G.
	 * 
	 * @param G The {@link Digraph} to perform a topological sort on.
	 */
	public DepthFirstOrder(Digraph G) {
		marked = new boolean[G.V()];
		order = new ArrayList<Integer>();
		
		for (int i = 0; i < G.V(); i++) {
			if (!marked[i]) dfs(G, i);
		}
	}
	
	/**
	 * Performs a DFS on {@link Digraph} G from vertex v.
	 * 
	 * @param G The {@link Digraph} to perform the DFS on.
	 * @param v The vertex to perform the DFS from.
	 */
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		
		for (int next : G.adj(v)) {
			if (!marked[next]) dfs(G, next);
		}
		
		order.add(0, v);
	}
	
	/**
	 * Gets the topologically sorted order for the DAG in reverse post order.
	 * 
	 * @return The topological sorted order for the DAG in reverse post order.
	 */
	public ArrayList<Integer> reversePost(){
		return order;
	}
}
