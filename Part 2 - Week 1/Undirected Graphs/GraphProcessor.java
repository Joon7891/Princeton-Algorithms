/**
 * Class containing a number of {@link Graph} processing algorithms.
 * 
 * @author Joon Song
 * @since April 16, 2020
 */
public class GraphProcessor {
	
	/**
	 * Determines the degree of vertex v in {@link Graph} G.
	 * 
	 * @param G The graph to compute the degree of v from.
	 * @param v The vertex to obtain the degree of from {@link Graph} G.
	 * @return The degree of vertex v in {@link Graph} G.
	 */
	public static int degree(Graph G, int v) {
		return G.adj(v).size();
	}
	
	/**
	 * Determines the maximum degree of the vertices in {@link Graph} G.
	 * 
	 * @param G The {@link Graph} to compute the maximum degree from.
	 * @return The maximum degree of the vertices in {@link Graph} G.
	 */
	public static int maxDegree(Graph G) {
		int maxDegree = 0;
		for (int i = 0; i < G.V(); i++) {
			maxDegree = Math.max(maxDegree, G.adj(i).size());
		}
		
		return maxDegree;
	}
	
	/**
	 * Determines the average degree of the vertices in {@link Graph} G.
	 * 
	 * @param G The {@link Graph} to compute the average degree from.
	 * @return The average degree of the vertices in {@link Graph} G.
	 */
	public static double averageDegree(Graph G) {
		return 2.0 * G.E() / G.V();
	}
	
	/**
	 * Determines the number of self-loops in {@link Graph} G.
	 * 
	 * @param G The {@link Graph} to compute the number of self-loops from.
	 * @return The number of self-loops in {@link Graph} G.
	 */
	public static int numberOfSelfLoops(Graph G) {
		int count = 0;
		for (int u = 0; u < G.V(); u++) {
			for (int v : G.adj(u)) {
				if (u == v) count++;
			}
		}
		
		return count / 2;
	}
}