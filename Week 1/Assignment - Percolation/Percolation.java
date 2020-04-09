import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Percolation class to simulate and hold data for a percolation.
 * 
 * @author Joon Song
 * @since April 8, 2020
 */
public class Percolation {
	
	// Data pertaining to the open/blocked states of the sites.
	private int n;
	private boolean[][] open;
	private int openCount = 0;
	
	// Union data to determine Percolation.
	private WeightedQuickUnionUF unionSet;
	private int topIndex;
	private int bottomIndex;
	
	/**
	 * Creates an n-by-n Percolation system, with all sites initially blocked.
	 * 
	 * @param n The horizontal and vertical size of the Percolation system.
	 */
	public Percolation(int n) {
		// Throws exception if size is invalid.
		if (n <= 0) throw new IllegalArgumentException();
		
		// Initializing 2D array to hold open/blocked site data.
		this.n = n;
		open = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				open[i][j] = false;
			}
		}
		
		// Setting up union-find and indexers for top and bottom.
		topIndex = n * n;
		bottomIndex = n * n + 1;
		unionSet = new WeightedQuickUnionUF(n * n + 2);
	}	
    
	/**
	 * Opens the site (row, col) if it is not open already.
	 * 
	 * @param row The row (y) coordinate.
	 * @param col The column (x) coordinate.
	 */
	public void open(int row, int col) {
		// Throwing exception of argument is out of bounds.
		if (row > n || row < 1 || col > n || col < 1) {
			throw new IllegalArgumentException();
		}
						
		// Opening site as necessary.
		int adjustedIndex = (row - 1) * n + col - 1;
		if (!open[row - 1][col - 1]) {
			open[row - 1][col - 1] = true;
			openCount++;
			
			// Unionizing (row, col) with the top or bottom as necessary.
			if (row == 1) unionSet.union(adjustedIndex, topIndex);
			if (row == n) unionSet.union(adjustedIndex, bottomIndex);

			// Unionizing adjacent open sites with (row, col).
			if (row - 1 >= 1 && open[row - 2][col - 1]) {
				unionSet.union(adjustedIndex, (row - 2) * n + col - 1);
			}
			
			if (row + 1 <= n && open[row][col - 1]) {
				unionSet.union(adjustedIndex, row * n + col - 1);
			}
			
			if (col + 1 <= n && open[row - 1][col]) {
				unionSet.union(adjustedIndex, (row - 1) * n + col);
			}
			
			if (col - 1 >= 1 && open[row - 1][col - 2]) {
				unionSet.union(adjustedIndex, (row - 1) * n + col - 2);
			}
		}
	}

	/**
	 * Gets whether the site (row, col) is open.
	 * 
	 * @param row The row (y) coordinate.
	 * @param col The column (x) coordinate.
	 * @return Whether the site (row, col) is open
	 */
    public boolean isOpen(int row, int col) {
    	if (row > n || row < 1 || col > n || col < 1) {
			throw new IllegalArgumentException();
    	}
    	
    	return open[row][col];
    }

    /**
     * Determines whether the site (row, col) is full.
     * 
	 * @param row The row (y) coordinate.
	 * @param col The column (x) coordinate.
	 * @return Whether the site (row, col) is full.
     */
    public boolean isFull(int row, int col) {
    	if (row > n || row < 1 || col > n || col < 1) {
			throw new IllegalArgumentException();
    	}
    	
    	int adjustedIndex = (row - 1) * n + col - 1;
    	return unionSet.find(adjustedIndex) == unionSet.find(topIndex);
    }

    /**
     * Determines the number of open sites in this Percolation.
     * 
     * @return The number of open sites in this Percolation.
     */
    public int numberOfOpenSites() {
    	return openCount;
    }


    /**
     * Gets whether the system percolates.
     * 
     * @return Whether the system percolates.
     */
    public boolean percolates() {
    	return unionSet.find(topIndex) == unionSet.find(bottomIndex);
    }
}
