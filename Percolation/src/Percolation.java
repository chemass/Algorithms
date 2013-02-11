/**
 *
 * @author  Che Coxshall
 * @login   ccoxshall@gmail.com
 * @date    11th February 2013
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
     * QuickUnion algorithm.
     */
    private WeightedQuickUnionUF uf;

    /**
     * Creates a new percolation grid N by N.
     * All sites are initialised full.
     * @param gridSize Desired size of the grid.
     */
    public Percolation(final int gridSize) {

        this.size = gridSize;
        //0 will be top vSite, (N * N) + 2 will be the bottom vSite
        int siteCount = (gridSize * gridSize) + 2;
        // +1 for ease - can use the same uf index as the uf alg.
        this.sites = new boolean[siteCount];
        this.sites[0] = true;
        this.sites[siteCount - 1] = true;
        
        this.uf = new WeightedQuickUnionUF(siteCount);
    }

    /**
     * Opens the site at the specified position if it is not already open.
     * @param i 1 based x position.
     * @param j 1 based y position.
     */
    public final void open(final int i, final int j) {
        // open site (row i, column j) if it is not already
        checkIndex(i);
        checkIndex(j);

        int sI = i - 1;
        int sJ = j - 1;
        int csId = coordsToUF(sI, sJ);

        //avoids extra method call and extra value checking
        if (!sites[csId]) {
            sites[csId] = true;

            int ufId;

            //connect left?
            if (sJ > 0) {
            	ufId = coordsToUF(sI, sJ - 1);
            	if (sites[ufId]) {
            		this.uf.union(csId, ufId);
            	}
            }

            //connect right?
            if (j < size) {
            	ufId = coordsToUF(sI, sJ + 1);
            	if (sites[ufId]) {
            		this.uf.union(csId, ufId);
            	}
            }

            //connect top?
        	ufId = coordsToUF(sI - 1, sJ);
        	if (sites[ufId]) {
        		this.uf.union(csId, ufId);
        	}

            //connect bottom?
        	ufId = coordsToUF(sI + 1, sJ);
        	if (sites[ufId]) {
        		this.uf.union(csId, ufId);
        	}
        }
    }

    /**
     * Indicates if the site at the specified position is open.
     * @param i 1 based x position.
     * @param j 1 based y position.
     * @return true if site is open.
     */
    public final boolean isOpen(final int i, final int j) {
        // is site (row i, column j) open?
        checkIndex(i);
        checkIndex(j);

        return sites[coordsToUF(i - 1, j - 1)];
    }

    /**
     * Indicates if the site at the specified position is full.
     * @param i 1 based x position.
     * @param j 1 based y position.
     * @return true if site is full.
     */
    public final boolean isFull(final int i, final int j) {
        checkIndex(i);
        checkIndex(j);

        return this.uf.connected(0, coordsToUF(i - 1, j - 1));
    }

    /**
     * Indicates if the grid percolates.
     * @return true if there is a connected path from top to bottom.
     */
    public final boolean percolates() {
        // does the system percolate?
        return this.uf.connected(0, (this.size * this.size) + 1);
    }

	/**
	 * Checks to ensure the provided index is within bounds.
	 * @param index index to be checked.
	 */
	private void checkIndex(final int index) {
		if (index < 1) {
            throw new java.lang.IllegalArgumentException("index cannot be less than 1");
        }
        if (index > size + 1) {
            throw new java.lang.IllegalArgumentException("index cannot be greater than " + (this.size + 1));
        }
	}

    /**
     * Transforms x, y coordinates to the grid id.
     * @param x 0 based x coordinate.
     * @param y 0 based y coordinate.
     * @return grid id
     */
    private int coordsToUF(final int x, final int y) {
    	if (x == -1) {
    		return 0;
    	} 

    	if (x == this.size) {
    		return this.size + 1;
    	}
    		
        return ((x * size) + y) + 1;
    }
}
