/**
 * 
 * @author Che Coxshall
 * @login ccoxshall@gmail.com
 * @date 11th February 2013
 */
public class Percolation {

	/**
	 * Size of the grid.
	 */
	private int size;
	/**
	 * open/closed sites.
	 */
	private boolean[] sites;
	/**
	 * QuickUnion algorithm for isConnected.
	 */
	private WeightedQuickUnionUF uf1;
	/**
	 * QuickUnion algorithm for isFull.
	 */
	private WeightedQuickUnionUF uf2;

	/**
	 * Creates a new percolation grid N by N. All sites are initialised full.
	 * 
	 * @param gridSize
	 *            Desired size of the grid.
	 */
	public Percolation(int gridSize) {

		this.size = gridSize;
		// 0 will be top vSite, no virtual bottom site
		int siteCount = (gridSize * gridSize) + 2;
		// +1 for ease - can use the same uf index as the uf alg.
		this.sites = new boolean[siteCount];
		this.sites[0] = true;
		this.sites[siteCount - 1] = true;

		this.uf1 = new WeightedQuickUnionUF(siteCount);
		this.uf2 = new WeightedQuickUnionUF(siteCount - 1);
	}

	/**
	 * Opens the site at the specified position if it is not already open.
	 * 
	 * @param i
	 *            1 based x position.
	 * @param j
	 *            1 based y position.
	 */
	public void open(int i, int j) {
		if (!validateIndex(i)) {
			throw new java.lang.IndexOutOfBoundsException("i is out of bounds");
		}
		if (!validateIndex(j)) {
			throw new java.lang.IndexOutOfBoundsException("j is out of bounds");
		}

		// get site index
		int csId = coordsToUF(i, j);

		// skip extra method call and value checking
		if (!sites[csId]) {
			sites[csId] = true;

			int ufId;

			// connect left?
			if (j > 1) {
				ufId = coordsToUF(i, j - 1);
				if (sites[ufId]) {
					this.uf1.union(ufId, csId);
					this.uf2.union(ufId, csId);
				}
			}

			// connect right?
			if (j < size) {
				ufId = coordsToUF(i, j + 1);
				if (sites[ufId]) {
					this.uf1.union(ufId, csId);
					this.uf2.union(ufId, csId);
				}
			}

			// connect top?
			if (i > 1) {
				ufId = coordsToUF(i - 1, j);
				if (sites[ufId]) {
					this.uf1.union(ufId, csId);
					this.uf2.union(ufId, csId);
				}
			} else {
				// connect virtual site
				this.uf1.union(csId, 0);
				this.uf2.union(csId, 0);
			}

			// connect bottom?
			if (i < this.size) {
				ufId = coordsToUF(i + 1, j);
				if (sites[ufId]) {
					this.uf1.union(ufId, csId);
					this.uf2.union(ufId, csId);
				}
			} else {
				// connect virtual site
				this.uf1.union(csId, (this.size * this.size) + 1);
			}
		}
	}

	/**
	 * Indicates if the site at the specified position is open.
	 * 
	 * @param i
	 *            1 based x position.
	 * @param j
	 *            1 based y position.
	 * @return true if site is open.
	 */
	public boolean isOpen(int i, int j) {
		if (!validateIndex(i)) {
			throw new java.lang.IndexOutOfBoundsException("i is out of bounds");
		}
		if (!validateIndex(j)) {
			throw new java.lang.IndexOutOfBoundsException("j is out of bounds");
		}

		return sites[coordsToUF(i, j)];
	}

	/**
	 * Indicates if the site at the specified position is full.
	 * 
	 * @param i
	 *            1 based x position.
	 * @param j
	 *            1 based y position.
	 * @return true if site is full.
	 */
	public boolean isFull(int i, int j) {
		if (!validateIndex(i)) {
			throw new java.lang.IndexOutOfBoundsException("i is out of bounds");
		}
		if (!validateIndex(j)) {
			throw new java.lang.IndexOutOfBoundsException("j is out of bounds");
		}

		return this.uf2.connected(coordsToUF(i, j), 0);
	}

	/**
	 * Indicates if the grid percolates.
	 * 
	 * @return true if there is a connected path from top to bottom.
	 */
	public boolean percolates() {
		return this.uf1.connected(0, (this.size * this.size) + 1);
	}

	/**
	 * Checks to ensure the provided index is within bounds.
	 * 
	 * @param index
	 *            index to be checked.
	 */
	private boolean validateIndex(final int index) {
		return index > 0 && index <= this.size;
	}

	/**
	 * Transforms x, y coordinates to the grid id.
	 * 
	 * @param x
	 *            1 based x coordinate.
	 * @param y
	 *            1 based y coordinate.
	 * @return grid id
	 */
	private int coordsToUF(final int x, final int y) {
		int row = x - 1;
		int col = y - 1;
		int id = (((row) * size) + (col)) + 1;
		return id;
	}
}
