/**
 * Wrapper class to hold strongly connected components functions for a {@link Digraph}.
 * 
 * @author Joon Song
 * @since April 17, 2020
 */
public class KosarajuSharirSCC {
	
	private boolean marked[];
	public int id[];
	private int count;
	
	/**
	 * Creates a {@link KosarajuSharirSCC} object.
	 * 
	 * @param G The {@link Digraph} to determine strong connectivity on.
	 */
	public KosarajuSharirSCC(Digraph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		
		DepthFirstOrder dfs = new DepthFirstOrder(G.reverse());
		for (int v : dfs.reversePost()) {
			if (!marked[v]) {
				dfs(G, v);
				count++;
			}
		}
	}
	
	/**
	 * Performs a DFS search on {@link Graph} G from vertex v.
	 * 
	 * @param G The {@link Graph} to perform the DFS from.
	 * @param v The starting node to perform the DFS from.
	 */
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		id[v] = count;
		for (int w : G.adj(v)) {
			if (!marked[w]) dfs(G, w);
		}
	}
	
	/**
	 * Determines whether vertices v and w are strongly connected.
	 * 
	 * @param v The vertex to check for strong connectivity with w.
	 * @param w The vertex to check for strong connectivity with v.
	 * @return Whether vertices v and w are strongly connected.
	 */
	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}
}
