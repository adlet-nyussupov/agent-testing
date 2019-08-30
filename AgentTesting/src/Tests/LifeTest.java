package Tests;

import org.junit.Assert;
import org.junit.Test;

import SUTClasses.Life;

public class LifeTest {

	private Life createTestSubject() {
		return new Life();
	}

	@Test
	public void testGen() throws Exception {
		boolean[][] result;

		// default test
		result = Life.gen();
	}

	@Test
	public void testMain() throws Exception {
		String[] args = new String[] { "" };

		// default test
		Life.main(args);
	}

	@Test
	public void testOccupiedNext() throws Exception {
		int numNeighbors = 0;
		boolean occupied = false;
		boolean result;

		// test 1
		numNeighbors = 2;
		result = Life.occupiedNext(numNeighbors, occupied);
		Assert.assertEquals(false, result);

		// test 2
		numNeighbors = 0;
		result = Life.occupiedNext(numNeighbors, occupied);
		Assert.assertEquals(false, result);

		// test 3
		numNeighbors = 3;
		result = Life.occupiedNext(numNeighbors, occupied);
		Assert.assertEquals(false, result);

		// test 4
		numNeighbors = 0;
		result = Life.occupiedNext(numNeighbors, occupied);
		Assert.assertEquals(false, result);

		// test 5
		numNeighbors = 3;
		result = Life.occupiedNext(numNeighbors, occupied);
		Assert.assertEquals(false, result);

		// test 6
		numNeighbors = 0;
		result = Life.occupiedNext(numNeighbors, occupied);
		Assert.assertEquals(false, result);
	}

}