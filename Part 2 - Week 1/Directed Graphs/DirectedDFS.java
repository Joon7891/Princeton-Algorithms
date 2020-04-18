/**
 * Object to hold reachable nodes from a given vertex in a {@link Digraph}, computed in a DFS manner.
 * 
 * @author Joon Song
 * @since April 17, 2020
 */
public class DirectedDFS {
	private boolean[] marked;
	
	/**
	 * Creates a {@link DirectedDFS} object with {@link Digraph} G and source node s.
	 * 
	 * @param G The {@link Digraph} to traverse through.
	 * @param s The source node.
	 */
	public DirectedDFS(Digraph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}
	
	/**
	 * Performs a DFS search on {@link Digraph} G from vertex v.
	 * 
	 * @param G The {@link Digraph} to perform the DFS from.
	 * @param v The starting node to perform the DFS from.
	 */
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		for (int next : G.adj(v)) {
			if (!marked[next]) dfs(G, next);
		}
	}
	
	/**
	 * Determines whether vertex v has been visited.
	 * 
	 * @param v The vertex to check if it has been visited.
	 * @return Whether vertex has been visited.
	 */
	public boolean visited(int v) {
		return marked[v];
	}
}
