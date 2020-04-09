/**
 * Interface for a Union-Find data structure.
 * 
 * @author Joon Song
 * @since April 8, 2020
 */
public interface UF {
	
	/**
	 * Adds a connection between objects p and q.
	 * 
	 * @param p The object to be connected with q.
	 * @param q The object to be connected with p.
	 */
	public void union(int p, int q);
	
	/**
	 * Gets whether objects p and q are connected.
	 * 
	 * @param p The object to check if it is connected with q.
	 * @param q The object to check if it is connected with p.
	 * @return Whether the objects p and q are connected.
	 */
	public boolean connected(int p, int q);
	
	/**
	 * Gets the component identifier for p. 
	 * 
	 * @param p The object to get the component identifier for.
	 * @return The component identifier for p.
	 */
	public int find(int p);
	
	/**
	 * Gets the number of components in this UF object.
	 * 
	 * @return The number of components in this UF object.
	 */
	public int count();
}
