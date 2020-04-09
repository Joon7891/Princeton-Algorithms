import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.util.ArrayList;

/**
 * PercolationStats object to hold various data for {@link Percolation} simulations.
 * 
 * @author Joon Song
 * @since April 8, 2020
 */
public class PercolationStats {
	private double[] trialData;
	private double mean, stdv, confLow, confHigh;
	
	public PercolationStats(int n, int trials) {
		// Setting up array to hold trial data.
		trialData = new double[trials];
		
		// Running trials
		for (int i = 0; i < trials; i++) {
			// Initializing a counter, list of unused sites, and a Percolation.
			double globalCount = 0;
			ArrayList<Integer> toUse = new ArrayList<Integer>();
			Percolation curPerc = new Percolation(n);
			for (int j = 0; j < n * n; j++) toUse.add(j);
			
			// Opening sites as long as the system does not percolate.
			while (!curPerc.percolates()) {
				globalCount++;
				int newIndex = StdRandom.uniform(0, toUse.size());
				int location = toUse.get(newIndex); toUse.remove(newIndex);
				int row = location / n + 1;
				int col = location % n + 1;
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
    public double stddev() {
    	return stdv;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
    	return confLow;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
    	return confHigh;
    }

	
	public static void main(String[] args) {
		// Handling command line arguments
		int n = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		
		PercolationStats ps = new PercolationStats(n, T);
		System.out.println("mean                    = " + ps.mean());
		System.out.println("stddev                  = " + ps.stddev());
		System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
	}

}
