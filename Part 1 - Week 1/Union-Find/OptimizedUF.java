import java.util.HashSet;

/**
 * Implementation of an optimized UF interface.
 * 
 * @author Joon Song
 * @since April 8, 2020
 */
public class OptimizedUF implements UF {
	private int[] id;
	private int[] size;
	
	/**
	 * Constructs a OptimizedUF object - O(N).
	 * 
	 * @param N The number of objects in the OptimizedUF object.
	 */
	public OptimizedUF(int N) {
		id = new int[N];
		size = new int[N];
		
		for (int i = 0; i < N; i++) {
			id[i] = i;
			size[i] = 1;
		}
	}

	/**
	 * Determines the root of object i - O(log N).
	 * 
	 * @param i The object to determine the root of.
	 * @return The root of object i.
	 */
	private int root(int i) {
		if (id[i] == i) return i;
		return id[i] = root(id[i]);
	}
	
	/**
	 * Adds a connection between objects p and q - O(log N).
	 * 
	 * @param p The object to be connected with q.
	 * @param q The object to be connected with p.
	 */
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		
		if (i == j) return;
		
		if (size[i] < size[j]) {
			size[j] += size[i];
			id[i] = j;
		}
		else {
			size[i] += size[j];
			id[j] = i;
		}
	}

	/**
	 * Gets whether objects p and q are connected - O(log N).
	 * 
	 * @param p The object to check if it is connected with q.
	 * @param q The object to check if it is connected with p.
	 * @return Whether the objects p and q are connected.
	 */
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	/**
	 * Gets the component identifier for p - O(log N).
	 * 
	 * @param p The object to get the component identifier for.
	 * @return The component identifier for p.
	 */
	public int find(int p) {
		return root(p);
	}

	/**
	 * Gets the number of components in this UF object - O(N log N).
	 * 
	 * @return The number of components in this UF object.
	 */
	public int count() {
		HashSet<Integer> idSet = new HashSet<Integer>();
		
		for (int i = 0; i < id.length; i++) idSet.add(root(i));
		return idSet.size();
	}
	
	
}
