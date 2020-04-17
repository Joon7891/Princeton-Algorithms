import java.util.Queue;
import java.util.LinkedList;

/**
 * Object to hold outgoing paths from a given vertex in a {@link Graph}, computed in a BFS manner.
 * 
 * @author Joon Song
 * @since April 16, 2020
 */
public class BreadthFirstPaths extends Paths{

	/**
	 * Creates a {@link BreadthFirstPaths} object extending {@link Paths}.
	 * 
	 * @param G The {@link Graph} to determine paths from.
	 * @param s The source node to determine paths from.
	 */
	public BreadthFirstPaths(Graph G, int s) {
		super(G, s);
		bfs(G, s);
	}
	
	/**
	 * Performs a BFS search on {@link Graph} G from vertex v.
	 * 
	 * @param G The {@link Graph} to perform the BFS from.
	 * @param v The starting node to perform the BFS from.
	 */
	private void bfs(Graph G, int s) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(s);
		marked[s] = true;
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int next : G.adj(cur)) {
				if (!marked[next]) {
					q.add(next);
					edgeTo[next] = cur;
					marked[next] = true;
				}
			}
		}
	}
}