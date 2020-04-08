import java.util.HashSet;

/**
 * Implementation of a UF interface with Quick Union.
 * 
 * @author Joon Song
 * @since April 8, 2020
 */
public class QuickUnionUF implements UF {

	private int[] id;
	
	/**
	 * Constructs a QuickUnionUF object - O(N).
	 * 
	 * @param N The number of objects in the QuickUnionUF object.
	 */
	public QuickUnionUF(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++) id[i] = i;
	}
	
	/**
	 * Determines the root of object i - O(N).
	 * 
	 * @param i The object to determine the root of.
	 * @return The root of object i.
	 */
	private int root(int i) {
		while (id[i] != i) i = id[i];
		return i;
	}
	
	/**
	 * Adds a connection between objects p and q - O(N).
	 * 
	 * @param p The object to be connected with q.
	 * @param q The object to be connected with p.
	 */
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}

	/**
	 * Gets whether objects p and q are connected - O(N).
	 * 
	 * @param p The object to check if it is connected with q.
	 * @param q The object to check if it is connected with p.
	 * @return Whether the objects p and q are connected.
	 */
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	/**
	 * Gets the component identifier for p - O(N).
	 * 
	 * @param p The object to get the component identifier for.
	 * @return The component identifier for p.
	 */
	public int find(int p) {
		return root(p);
	}

	/**
	 * Gets the number of components in this QuickUnionUF object - O(N^2).
	 * 
	 * @return The number of components in this QuickUnionUF object.
	 */
	public int count() {
		HashSet<Integer> idSet = new HashSet<Integer>();
		
		for (int i = 0; i < id.length; i++) idSet.add(root(i));
		
		return idSet.size();
	}

}
