package Tests;

import org.junit.Test;

import SUTClasses.MineSweeper;


public class MineSweeperTest {

	private MineSweeper createTestSubject() {
		return new MineSweeper();
	}


	@Test
	public void testCellPicked() throws Exception {
		MineSweeper testSubject;
		int row = 0;
		int col = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.cellPicked(row, col);
	}

	@Test
	public void testInBounds() throws Exception {
		MineSweeper testSubject;
		int row = 0;
		int col = 0;
		boolean result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.inBounds(row, col);
	}
}