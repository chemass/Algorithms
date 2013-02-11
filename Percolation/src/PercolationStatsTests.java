import org.junit.Test;


public class PercolationStatsTests {

	@Test
	public void testPercolationStats() {
		PercolationStats ps = new PercolationStats(20, 10000);
		
	}
/*
	@Test
	public void testMean() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testStddev() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testConfidenceLo() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testConfidenceHi() {
		fail("Not yet implemented"); // TODO
	}*/

	@Test
	public void testMain() {
		PercolationStats.main(new String[]{ "200", "100" });
		PercolationStats.main(new String[]{ "200", "100" });
		PercolationStats.main(new String[]{ "2", "10000" });
		PercolationStats.main(new String[]{ "2", "10000" });
		PercolationStats.main(new String[]{ "500", "100" });
		PercolationStats.main(new String[]{ "500", "100" });
		PercolationStats.main(new String[]{ "75", "10000" });
		PercolationStats.main(new String[]{ "75", "10000" });
		
		
		
	}
}
