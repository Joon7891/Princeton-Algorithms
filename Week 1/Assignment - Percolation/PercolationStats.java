import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * PercolationStats object to hold various data for {@link Percolation} simulations.
 * 
 * @author Joon Song
 * @since April 8, 2020
 */
public class PercolationStats {
	private final double mean, stdv, confLow, confHigh;
	
	public PercolationStats(int n, int trials) {
		// Setting up array to hold trial data.
		double[] trialData = new double[trials];
		
		// Running trials
		for (int i = 0; i < trials; i++) {
			// Initializing a counter and a Percolation.
			double globalCount = 0;			
			Percolation curPerc = new Percolation(n);
			
			// Opening sites as long as the system does not percolate.
			while (!curPerc.percolates()) {
				globalCount++;
				
				int row = StdRandom.uniform(1, n + 1);
				int col = StdRandom.uniform(1, n + 1);
				if (curPerc.isOpen(row, col)) {
					globalCount--;
					continue;
				}
				
				curPerc.open(row, col);
			}

			// Storing trial data.
			trialData[i] = globalCount / (n * n);
		}
		
		// Calculating trial data.
		mean = StdStats.mean(trialData);
		stdv = StdStats.stddev(trialData);
		confLow = mean - 1.96 * stdv / Math.sqrt(trials);
		confHigh = mean + 1.96 * stdv / Math.sqrt(trials);
	}

	/**
	 * Gets the mean of the percolation threshold.
	 * 
	 * @return The mean of the percolation threshold.
	 */
    public double mean() {
    	return mean;
    }

    // sample standard deviation of percolation threshold
    /**
     * Gets the standard deviation of the percolation threshold.
     * 
     * @return The standard deviation of the percolation threshold.
     */
    public double stddev() {
    	return stdv;
    }

    /**
     * Gets the low end point of the 95% confidence interval.
     * 
     * @return The low end point of the 95% confidence interval.
     */
    public double confidenceLo() {
    	return confLow;
    }
    
    /**
     * Gets the high end point of the 95% confidence interval.
     * 
     * @return The high end point of the 95% confidence interval.
     */
    public double confidenceHi() {
    	return confHigh;
    }

	/**
	 * Main driver method for program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Handling command line arguments.
		int n = Integer.parseInt(args[0]);
		int t = Integer.parseInt(args[1]);
		
		// Throwing an exception if argument is out of bounds.
		if (n <= 0 || t <= 0) throw new IllegalArgumentException();
		
		// Simulating percolation and printing statistics.
		PercolationStats ps = new PercolationStats(n, t);
		System.out.println("mean                    = " + ps.mean());
		System.out.println("stddev                  = " + ps.stddev());
		System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
	}

}
