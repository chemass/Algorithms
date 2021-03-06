/**
 * class to perform a series of computational experiments.
 * 
 * @author Che Coxshall
 * @login ccoxshall@gmail.com
 * @date 11th February 2013
 */
public class PercolationStats {

	/**
	 * Number of tests to be run.
	 */
	private int testCount;
	/**
	 * Array containing the test results.
	 */
	private double[] results;

	/**
	 * Perform T number of experiments on a N by N grid.
	 * 
	 * @param gridSize
	 *            size of the grid.
	 * @param count
	 *            number of passes.
	 */
	public PercolationStats(int gridSize, int count) {

		if (gridSize < 1) {
			throw new java.lang.IllegalArgumentException(
			        "gridSize cannot be less than 1");
		}
		if (count < 1) {
			throw new java.lang.IllegalArgumentException(
			        "count cannot be less than 1");
		}

		testCount = count;
		results = new double[count];

		for (int i = 0; i < count; i++) {

			Percolation p = new Percolation(gridSize);
			int openSiteCount = 0;
			int row = 0;
			int column = 0;

			do {

				row = StdRandom.uniform(gridSize) + 1;
				column = StdRandom.uniform(gridSize) + 1;

				if (!p.isOpen(row, column)) {
					p.open(row, column);
					openSiteCount++;
				}

			} while (!p.percolates());

			p = null;

			results[i] = (double) openSiteCount
			        / (double) (gridSize * gridSize);
		}
	}

	/**
	 * gets the mean average of the results.
	 * 
	 * @return the mean average of the results.
	 */
	public double mean() {
		return StdStats.mean(results);
	}

	/**
	 * gets the standard deviation of the results.
	 * 
	 * @return the standard deviation of the results.
	 */
	public double stddev() {
		return this.testCount == 1 ? Double.NaN : StdStats.stddev(results);
	}

	/**
	 * gets the lower bound of the 95% confidence interval.
	 * 
	 * @return the lower bound of the 95% confidence interval.
	 */
	public double confidenceLo() {
		return mean() - (stddev() / Math.sqrt(testCount));
	}

	/**
	 * gets the upper bound of the 95% confidence interval.
	 * 
	 * @return the upper bound of the 95% confidence interval.
	 */
	public double confidenceHi() {
		return mean() + (stddev() / Math.sqrt(testCount));
	}

	/**
	 * test client to perform the experiments.
	 * 
	 * @param args
	 *            provided arguments in the format N T
	 */
	public static void main(String[] args) {

		if (args.length != 2) {
			throw new java.lang.IllegalArgumentException(
			        "Wrong number of arguments");
		}

		int n = Integer.parseInt(args[0]);
		int t = Integer.parseInt(args[1]);

		PercolationStats ps = new PercolationStats(n, t);

		StdOut.println("mean\t\t\t= " + ps.mean());
		StdOut.println("stddev\t\t\t= " + ps.stddev());
		StdOut.println("95% confidence interval\t= " + ps.confidenceLo() + ", "
		        + ps.confidenceHi());
	}
}
