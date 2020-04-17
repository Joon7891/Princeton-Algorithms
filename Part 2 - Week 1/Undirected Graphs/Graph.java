import java.util.ArrayList;

/**
 * Implementation of an undirected graph with basic functionality.
 * 
 * @author Joon Song
 * @since April 16, 2020
 */
public class Graph {
	
	private ArrayList<Integer>[] adj;
	private final int V;
	private int E;
	
	/**
	 * Creates an empty {@link Graph} with V vertices.
	 * 
	 * @param V The number of vertices in the {@link Graph}.
	 */
	@SuppressWarnings("unchecked")
	public Graph(int V) {
		this.V = V;
		E = 0;
		
		adj = new ArrayList[V];		
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<Integer>();
		}
	}
	
	/**
	 * Creates a {@link Graph} from an input stream.
	 * 
	 * @param in The input stream to create a {@link Graph} from.
	 */
	@SuppressWarnings("unchecked")
	public Graph(In in) {
		V = in.readInt();
		E = in.readInt();
		
		adj = new ArrayList[V];		
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < E; i++) {
			int u = in.readInt();
			int v = in.readInt();
			
			adj[u].add(v);
			adj[v].add(u);
		}
	}
	
	/**
	 * Adds an edge between vertices v and w.
	 * 
	 * @param v The vertex to add an edge to w.
	 * @param w The vertex to add an edge to v.
	 */
	public void addEdge(int v, int w) {
		E++;
		
		adj[v].add(w);
		adj[w].add(v);
	}
	
	/**
	 * Gets an ArrayList containing the vertices adjacent to v.
	 * 
	 * @param v The vertex to get the adjacent vertices from.
	 * @return An ArrayList containing the vertices adjacent to v.
	 */
	public ArrayList<Integer> adj(int v){
		return adj[v];
	}
	
	/**
	 * Gets the number of vertices in this {@link Graph}.
	 * 
	 * @return The number of vertices in this {@link Graph}.
	 */
	public int V() {
		return V;
	}
	
	/**
	 * Gets the number of edges in this {@link Graph}.
	 * 
	 * @return The number of edges in this {@link Graph}.
	 */
	public int E() {
		return E;
	}
}