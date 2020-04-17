/**
 * Object to hold outgoing paths from a given vertex in a {@link Graph}, computed in a DFS manner.
 * 
 * @author Joon Song
 * @since April 16, 2020
 */
public class DepthFirstPaths extends Paths {
	
	/**
	 * Creates a {@link DepthFirstPaths} object extending {@link Paths}.
	 * 
	 * @param G The {@link Graph} to determine paths from.
	 * @param s The source node to determine paths from.
	 */
	public DepthFirstPaths(Graph G, int s) {
		super(G, s);
		dfs(G, s);
	}
	
	/**
	 * Performs a DFS search on {@link Graph} G from vertex v.
	 * 
	 * @param G The {@link Graph} to perform the DFS from.
	 * @param v The starting node to perform the DFS from.
	 */
	private void dfs(Graph G, int v) {
		marked[v] = true;
		
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
				edgeTo[w] = v;
			}
		}
	}
}