import java.util.HashSet;

/**
 * Implementation of a UF interface with Quick Find.
 * 
 * @author Joon Song
 * @since April 8, 2020
 */
public class QuickFindUF implements UF {
	
	private int[] id;
	
	/**
	 * Constructs a QuickFindUF object - O(N).
	 * 
	 * @param N The number of objects in the QuickFindUF object.
	 */
	public QuickFindUF(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++) id[i] = i;
	}
	
	/**
	 * Adds a connection between objects p and q - O(N).
	 * 
	 * @param p The object to be connected with q.
	 * @param q The object to be connected with p.
	 */
	public void union(int p, int q) {
		int pID = id[p];
		int qID = id[q];
		
		for (int i = 0; i < id.length; i++) {
			if (id[i] == pID) id[i] = qID;
		}
	}
	
	/**
	 * Gets whether objects p and q are connected - O(1).
	 * 
	 * @param p The object to check if it is connected with q.
	 * @param q The object to check if it is connected with p.
	 * @return Whether the objects p and q are connected.
	 */
	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}
	
	/**
	 * Gets the component identifier for p - O(1).
	 * 
	 * @param p The object to get the component identifier for.
	 * @return The component identifier for p.
	 */
	public int find(int p) {
		return id[p];
	}
	
	/**
	 * Gets the number of components in this QuickFindUF object - O(N).
	 * 
	 * @return The number of components in this QuickFindUF object.
	 */
	public int count() {
		HashSet<Integer> idSet = new HashSet<Integer>();
		
		for (int i = 0; i < id.length; i++) idSet.add(id[i]);
		
		return idSet.size();
	}
	
}
