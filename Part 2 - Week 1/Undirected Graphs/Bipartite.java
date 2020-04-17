/**
 * Wrapper class to hold functions to determine if a {@link Graph} is Bipartite.
 * 
 * @author Joon Song
 * @since April 16, 2020
 */
public class Bipartite {
	private boolean isBipartite = true;
	private boolean[] marked;
	private int[] id;
	
	public Bipartite(Graph G) {
		id = new int[G.V()];
		marked = new boolean[G.V()];
		
		for (int i = 0; i < G.V(); i++) {
			if (!marked[i]) dfs(G, i, 1);
		}
	}
	
	public int id(int v) {
		return id[v];
	}
	
	public boolean isBipartite() {
		return isBipartite;
	}
	
	private void dfs(Graph G, int v, int colour) {
		id[v] = colour;
		marked[v] = true;
		
		for (int next : G.adj(v)) {
			if (id[next] == id[v]) isBipartite = false;
			
			if (!marked[next]) {
				dfs(G, next, -colour);
			}
		}
	}
}
