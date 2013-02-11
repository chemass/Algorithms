import static org.junit.Assert.*;

import org.junit.Test;


public class PercolationTests {

	@Test
	public void testPercolation() {
		Percolation p = new Percolation(5);
		for (int i = 1; i < 6; i++) {
			for (int j = 1; j < 6; j++) {
				if(p.isOpen(i, j))
					fail("site at " + i + ", " + j + " is open.");
			}
		}
		/*if(!p.getUf().connected(0, 1))
			fail("0, 1 not connected");
		if(!p.getUf().connected(0, 2))
			fail("0, 2 not connected");
		if(!p.getUf().connected(0, 3))
			fail("0, 3 not connected");
		if(!p.getUf().connected(0, 4))
			fail("0, 4 not connected");
		if(!p.getUf().connected(0, 5))
			fail("0, 5 not connected");
		

		if(!p.getUf().connected(26, 25))
			fail("26, 25 not connected");
		if(!p.getUf().connected(26, 24))
			fail("26, 24 not connected");
		if(!p.getUf().connected(26, 23))
			fail("26, 23 not connected");
		if(!p.getUf().connected(26, 22))
			fail("26, 22 not connected");
		if(!p.getUf().connected(26, 21))
			fail("26, 21 not connected");*/
	}

	@Test
	public void testOpen() {
		Percolation p = new Percolation(5);
		try{
			p.open(1, 1);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testIsOpen() {
		Percolation p = new Percolation(5);
		
		if(p.isOpen(1, 1))
			fail("site is already open");
		
		p.open(1, 1);
		
		if(!p.isOpen(1, 1))
			fail("site is not open");
	}

	@Test
	public void testIsFull() {
		/*Percolation p = new Percolation(5);
		
		if(!p.isFull(1, 1))
			fail("site is not full");
		
		p.open(1, 1);
		
		if(p.isFull(1, 1))
			fail("site is still full");*/
	}

	@Test
	public void testPercolates() {
		Percolation p = new Percolation(5);
		
		if(p.percolates())
			fail("percolates after creation");
		
		for (int i = 1; i < 6; i++) {
			p.open(i, 1);
			
			if(i < 5){
			if(p.percolates())
				fail("percolates early");
			} else {
				if(!p.percolates())
					fail("fails to percolate.");
			}
		}
		
		p = null;
		p = new Percolation(5);
		
		if(p.percolates())
			fail("percolates after creation");
		
		for (int i = 1; i < 6; i++) {
			p.open(i, 3);
			
			if(i < 5){
			if(p.percolates())
				fail("percolates early");
			} else {
				if(!p.percolates())
					fail("fails to percolate.");
			}
		}
		

		p = null;
		p = new Percolation(5);
		
		if(p.percolates())
			fail("percolates after creation");
		
		for (int i = 1; i < 6; i++) {
			p.open(i, 5);
			
			if(i < 5){
			if(p.percolates())
				fail("percolates early");
			} else {
				if(!p.percolates())
					fail("fails to percolate.");
			}
		}
		
		p = null;
		p = new Percolation(5);
		
		if(p.percolates())
			fail("percolates after creation");
		
		p.open(1, 1);
		p.open(1, 2);
		p.open(2, 2);
		p.open(2, 3);
		p.open(3, 3);
		p.open(3, 4);
		p.open(4, 4);
		p.open(4, 5);
		p.open(5, 5);
		
		if(!p.percolates())
			fail("tl-br diagonal percolation failed.");
		
		p = null;
		p = new Percolation(500);
		
		if(p.percolates())
			fail("percolates after creation");
		
		for (int i = 1; i < 501; i++) {
			p.open(i, 255);
			
			if(i < 500){
			if(p.percolates())
				fail("percolates early");
			} else {
				if(!p.percolates())
					fail("fails to percolate.");
			}
		}
	}

}
