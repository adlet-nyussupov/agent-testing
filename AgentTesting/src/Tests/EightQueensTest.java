package Tests;

import java.util.ArrayList;

import org.junit.Test;

import SUTClasses.EightQueens;


public class EightQueensTest {

	private EightQueens createTestSubject() {
		return new EightQueens();
	}

	
	@Test
	public void testMain() throws Exception {
		String[] args = new String[] { "" };

		// default test
		EightQueens.main(args);
	}


	/*
	 * @Test public void testQueensAreSafe() throws Exception { char[][] board = new
	 * char[][] { ' ' }; boolean result;
	 * 
	 * // default test result = EightQueens.queensAreSafe(board); }
	 */


	@Test
	public void testGetAllNQueens() throws Exception {
		int size = 0;
		ArrayList<char[][]> result;

		// default test
		result = EightQueens.getAllNQueens(size);
	}

	/*
	 * @Test public void testSolveAllNQueens() throws Exception { char[][] board =
	 * new char[][] { ' ' }; int col = 0; ArrayList<char[][]> solutions = null;
	 * 
	 * // default test EightQueens.solveAllNQueens(board, col, solutions); }
	 */

	/*
	 * @Test public void testMakeCopy() throws Exception { char[][] mat = new
	 * char[][] { ' ' }; char[][] result;
	 * 
	 * // default test result = EightQueens.makeCopy(mat); }
	 */

	
	/*
	 * @Test public void testPrintBoard() throws Exception { char[][] board = new
	 * char[][] { ' ' };
	 * 
	 * // default test EightQueens.printBoard(board); }
	 */

	/*
	 * @MethodRef(name = "solveNQueens", signature = "(I)V")
	 * 
	 * @Test public void testSolveNQueens() throws Exception { int n = 0;
	 * 
	 * // default test EightQueens.solveNQueens(n); }
	 */

	/*
	 * @Test public void testCanSolve() throws Exception { char[][] board = new
	 * char[][] { ' ' }; int col = 0; boolean result;
	 * 
	 * // default test result = EightQueens.canSolve(board, col); }
	 */


	@Test
	public void testBlankBoard() throws Exception {
		int size = 0;
		char[][] result;

		// default test
		result = EightQueens.blankBoard(size);
	}


	/*
	 * @Test public void testInbounds() throws Exception { int row = 0; int col = 0;
	 * char[][] mat = new char[][] { ' ' }; boolean result;
	 * 
	 * // default test result = EightQueens.inbounds(row, col, mat); }
	 */


	/*
	 * @Test public void testIsSquare() throws Exception { char[][] mat = new
	 * char[][] { ' ' }; boolean result;
	 * 
	 * // default test result = EightQueens.isSquare(mat); }
	 */

	/*
	 * @Test public void testOnlyContains() throws Exception { char[][] mat = new
	 * char[][] { ' ' }; char[] valid = new char[] { ' ' }; boolean result;
	 * 
	 * // default test result = EightQueens.onlyContains(mat, valid); }
	 */


	@Test
	public void testContains() throws Exception {
		char[] list = new char[] { ' ' };
		char c = ' ';
		boolean result;

		// default test
		result = EightQueens.contains(list, c);
	}
}