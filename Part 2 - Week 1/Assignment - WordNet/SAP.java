import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;

/**
 * Class to compute the shortest ancestral path for a given {@link Digraph}.
 * 
 * @author Joon Song
 * @since April 22, 2020
 */
public final class SAP {
	
	private final Digraph G;
	private BreadthFirstDirectedPaths paths1, paths2;
	
	/**
	 * Constructor for {@link SAP} object.
	 * 
	 * @param G The {@link Digraph} to determine the shortest ancestral path from.
	 */
	public SAP(Digraph G) {
		this.G = copyDigraph(G);
	}
	
	private Digraph copyDigraph(Digraph G) {
		Digraph shallowCopy = new Digraph(G.V());
		for (int i = 0; i < G.V(); i++) {
			for (int next : G.adj(i)) {
				shallowCopy.addEdge(i, next);
			}
		}
		
		return shallowCopy;
	}
	
	/**
	 * Computes the shortest ancestral path between nodes v and w.
	 * 
	 * @param v The node to compute the shortest ancestral path with w.
	 * @param w The node to compute the shortest ancestral path with v.
	 * @return The shortest ancestral path between nodes v and w.
	 * 
	 * @throws IllegalArgumentException if v or w are out of bounds of the graph.
	 */
	public int length(int v, int w) {
		if (v < 0 || v > G.V()) throw new IllegalArgumentException("Vertex v is out of bounds");
		if (w < 0 || w > G.V()) throw new IllegalArgumentException("Vertex v is out of bounds");
		
		paths1 = new BreadthFirstDirectedPaths(G, v);
		paths2 = new BreadthFirstDirectedPaths(G, w);
		
		int minLength = Integer.MAX_VALUE;
		for (int i = 0; i < G.V(); i++) {
			if (paths1.hasPathTo(i) && paths2.hasPathTo(i)) {
				minLength = Math.min(minLength, paths1.distTo(i) + paths2.distTo(i));
			}
		}
		
		return minLength == Integer.MAX_VALUE ? -1 : minLength;
	}
	
	/**
	 * Computes the ancestor in the shortest ancestral path between nodes v and w.
	 * 
	 * @param v The node to compute the ancestor from.
	 * @param w The node to compute the ancestor from.
	 * @return The ancestor in the shortest ancestral path between nodes v and w.
	 * 
	 * @throws IllegalArgumentException if v or w are out of bounds of the graph.
	 */
	public int ancestor(int v, int w) {
		if (v < 0 || v >= G.V()) throw new IllegalArgumentException("Vertex v is out of bounds");
		if (w < 0 || w >= G.V()) throw new IllegalArgumentException("Vertex v is out of bounds");
		
		paths1 = new BreadthFirstDirectedPaths(G, v);
		paths2 = new BreadthFirstDirectedPaths(G, w);
		
		int index = -1;
		int minLength = Integer.MAX_VALUE;
		for (int i = 0; i < G.V(); i++) {
			if (paths1.hasPathTo(i) && paths2.hasPathTo(i)) {
				int length = paths1.distTo(i) + paths2.distTo(i);
				
				if (length < minLength) {
					minLength = length;
					index = i;
				}
			}
		}
		
		return index;
	}
	
	/**
	 * Computes the shortest ancestral path between a node in v and a node in w.
	 * 
	 * @param v A set of vertices.
	 * @param w A set of vertices.
	 * @return The shortest ancestral path between a node in v and a node in w.
	 * 
	 * @throws IllegalArgumentException if any of the nodes in v or w are out of bounds.
	 */
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null) throw new IllegalArgumentException("V is null.");
		if (w == null) throw new IllegalArgumentException("W is null.");
		
		for (Integer vi : v) {
			if (vi == null || vi < 0 || vi >= G.V()) {
				throw new IllegalArgumentException("A vertex in v is invalid.");
			}
		}
		
		for (Integer wi : w) {
			if (wi == null || wi < 0 || wi >= G.V()) {
				throw new IllegalArgumentException("A vertex in v is invalid.");
			}
		}
		
		paths1 = new BreadthFirstDirectedPaths(G, v);
		paths2 = new BreadthFirstDirectedPaths(G, w);
		
		int minLength = Integer.MAX_VALUE;
		for (int i = 0; i < G.V(); i++) {
			if (paths1.hasPathTo(i) && paths2.hasPathTo(i)) {
				minLength = Math.min(minLength, paths1.distTo(i) + paths2.distTo(i));
			}
		}
		
		return minLength == Integer.MAX_VALUE ? -1 : minLength;
	}
	
	/**
	 * Computes the ancestor in the shortest ancestral path between a node in v and a node in w.
	 * 
	 * @param v A set of nodes to compute the ancestor from.
	 * @param w A set of nodes to compute the ancestor from.
	 * @return The ancestor in the shortest ancestral path between a node in v and a node in w.
	 * 
	 * @throws IllegalArgumentException if any of the nodes in v or w are out of bounds.
	 */
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null) throw new IllegalArgumentException("V is null.");
		if (w == null) throw new IllegalArgumentException("W is null.");
		
		for (int vi : v) {
			if (vi < 0 || vi >= G.V()) throw new IllegalArgumentException("A vertex in v is out of bounds.");
		}
		
		for (int wi : w) {
			if (wi < 0 || wi >= G.V()) throw new IllegalArgumentException("A vertex in v is out of bounds.");
		}
		
		paths1 = new BreadthFirstDirectedPaths(G, v);
		paths2 = new BreadthFirstDirectedPaths(G, w);
		
		int index = -1;
		int minLength = Integer.MAX_VALUE;
		for (int i = 0; i < G.V(); i++) {
			if (paths1.hasPathTo(i) && paths2.hasPathTo(i)) {
				int length = paths1.distTo(i) + paths2.distTo(i);
				
				if (length < minLength) {
					minLength = length;
					index = i;
				}
			}
		}
		
		return index;
	}
	
	/**
	 * Main function/test client for the {@link SAP} class.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		In in = new In(args[0]);
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		
		while (!StdIn.isEmpty()) {
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			int length = sap.length(v, w);
			int ancestor = sap.ancestor(v, w);
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		}
	}
}