import java.util.ArrayList;

/**
 * Implementation of an directed graph with basic functionality.
 * 
 * @author Joon Song
 * @since April 16, 2020
 */
public class Digraph {
	
	private ArrayList<Integer>[] adj;
	private final int V;
	private int E;
	
	/**
	 * Creates an empty {@link Digraph} with V vertices.
	 * 
	 * @param V The number of vertices in this {@link Digraph}.
	 */
	@SuppressWarnings("unchecked")
	public Digraph(int V) {
		this.V = V;
		E = 0;
		
		adj = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<Integer>();
		}
	}
	
	/**
	 * Creates a {@link Digraph} from an input stream.
	 * 
	 * @param in The input stream to create a {@link Digraph} from.
	 */
	@SuppressWarnings("unchecked")
	public Digraph(In in) {
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
		}
	}
	
	/**
	 * Adds a directed edge v -> w to this {@link Digraph}.
	 * 
	 * @param v The vertex the edge is coming out of.
	 * @param w The vertex the edge is going into.
	 */
	public void addEdge(int v, int w) {
		E++;
		adj[v].add(v);
	}
	
	/**
	 * Gets an ArrayList containing the vertices pointing from v.
	 * 
	 * @param v The vertex from which to get the directed edges from.
	 * @return An ArrayList containing the vertices pointing from v.
	 */
	public ArrayList<Integer> adj(int v){
		return adj[v];
	}
	
	/**
	 * Gets the number of vertices in this {@link Digraph}.
	 * 
	 * @return The number of vertices in this {@link Digraph}.
	 */
	public int V() {
		return V;
	}
	
	/**
	 * Gets the number of edges in this {@link Digraph}.
	 * 
	 * @return The number of edges in this {@link Digraph}.
	 */
	public int E() {
		return E;
	}
}
