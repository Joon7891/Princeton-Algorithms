import java.util.ArrayList;
/**
 * Wrapper class to hold functions to determine an Euler Tour of a {@link Graph}.
 * 
 * @author Joon Song
 * @since April 16, 2020
 */
public class EulerTour {
	
	private ArrayList<Edge> visitedEdges;
	private ArrayList<Integer> tour;
	
	/**
	 * Constructor for {@link EulerTour} object.
	 * 
	 * @param G The {@link Graph} to perform an Euler Tour on.
	 */
	public EulerTour(Graph G) {
		tour = new ArrayList<Integer>();
		visitedEdges = new ArrayList<Edge>();
		
		boolean possible = true;
		for (int i = 0; i < G.V(); i++) {
			if (GraphProcessor.degree(G, i) % 2 == 1) possible = false;
		}
		
		if (!possible) tour = null;
		else visit(G, 0);
	}
	
	/**
	 * Function to return a Euler Tour of the given {@link Graph}.
	 * 
	 * @return An {@link ArrayList} describing an Euler Tour of the given {@link Graph}.
	 */
	public ArrayList<Integer> getTour(){
		return tour;
	}
	
	/**
	 * Method to determine whether an edge has already been visited.
	 * 
	 * @param edge The {@link Edge} to check if it has already been visited.
	 * @return Whether the {@link Edge} has already been visited.
	 */
	private boolean isVisited(Edge edge) {
		for (Edge e : visitedEdges) {
			if (edge.equals(e)) return true;
		}
		
		return false;
	}
	
	/**
	 * Visits the vertex v in the {@link Graph} G.
	 * 
	 * @param G The {@link Graph} to traverse through.
	 * @param v The vertex to visit in the {@link Graph} G.
	 */
	private void visit(Graph G, int v) {
		for (int next : G.adj(v)) {
			Edge curEdge = new Edge(next, v);
			if (!isVisited(curEdge)) {
				visitedEdges.add(curEdge);
				visit(G, next);
			}
		}
		
		tour.add(v);
	}
}
