/**
 * Class to hold Edge object for a {@link Graph} object. 
 * 
 * @author Joon Song
 * @since April 16, 2020
 */
public class Edge {
	
	private int x, y;
	
	/**
	 * Constructor for {@link Edge} object.
	 * 
	 * @param x The vertex that is connected to y by this {@link Edge}.
	 * @param y The vertex that is connected to x by this {@link Edge}.
	 */
	public Edge(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the x vertex of this {@link Edge}.
	 * 
	 * @return The x vertex of this {@link Edge}.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the y vertex of this {@link Edge}.
	 * 
	 * @return The y vertex of this {@link Edge}.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Equals operator for {@link Edge} object
	 * 
	 * @param object The object to check for equivalence to.
	 * 
	 * @return Whether this {@link Edge} and object are equal.
	 */
	@Override
	public boolean equals(Object object) {
		if (object instanceof Edge) {
			boolean reg = ((Edge) object).x == x && ((Edge) object).y == y;
			boolean flip = ((Edge) object).y == x && ((Edge) object).x == y;
			return reg || flip;
		}
		else return false;
	}
}
