/**
 * Wrapper class to hold connected components functions for a {@link Graph}.
 * 
 * @author Joon Song
 * @since April 16, 2020
 */
public class CC {
	private boolean[] marked;
	private int[] id;
	private int count = 0;
	
	/**
	 * Creates a new {@link CC} object. Preprocesses connected components.
	 * 
	 * @param G The {@link Graph} to determine connected components from.
	 */
	public CC(Graph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		for (int i = 0; i < G.V(); i++) {
			if (!marked[i]) {
				dfs(G, i);
				count++;
			}
		}
	}
	
	/**
	 * Gets the number of distinct connected components in the {@link Graph}.
	 * 
	 * @return The number of distinct connected components.
	 */
	public int count() {
		return count;
	}
	
	/**
	 * Gets the identifier for vertex v.
	 * 
	 * @param v The vertex to get the identifier for.
	 * @return The identifier for vertex v.
	 */
	public int id(int v) {
		return id[v];
	}
	
	/**
	 * Determines whether vertex v and w are connected.
	 * 
	 * @param v The vertex to determine connectivity to w from.
	 * @param w The vertex to determine connectivity to v from.
	 * @return Whether vertex v and w are connected.
	 */
	public boolean connected(int v, int w) {
		return id[v] == id[w];
	}
	
	/**
	 * Performs a DFS traversal at vertex v to determined connected components.
	 * 
	 * @param G The {@link Graph} to determine connectivity from.
	 * @param v The vertex to traverse DFS from.
	 */
	private void dfs(Graph G, int v) {
		id[v] = count;
		marked[v] = true;
		
		for (int next : G.adj(v)) {
			if (!marked[next]) dfs(G, next);
		}
	}
}