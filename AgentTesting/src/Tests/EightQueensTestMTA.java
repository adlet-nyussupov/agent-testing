package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.EightQueens;

class EightQueensTestMTA {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test1() {
		EightQueens.onlyContains(new char[][]{ new char[0], new char[]{ '?', 'q' } }, new char[]{ 'E', 'i' });
	}
	
	@Test
	void test2() {
		EightQueens.makeCopy(new char[][]{ new char[]{ 'f', 'e' }, new char[]{ 'a', '3', 'w' } });
	}
	
	@Test
	void test3() {
		EightQueens.solveAllNQueens(new char[0][], 475108952, new java.util.ArrayList());
	}
	
	@Test
	void test4() {
		EightQueens.isSquare(new char[][]{ new char[]{ 'Q' } });
	}
	
	@Test
	void test5() {
		EightQueens.canSolve(new char[0][], -1119342944);
	}
	
	@Test
	void test6() {
		EightQueens.onlyContains(new char[0][], new char[]{ 'h', '-', ']' });
	}
	
	@Test
	void test7() {
		EightQueens.printBoard(new char[][]{ new char[]{ 'A', '>', '!' }, new char[]{ 'v', 'F' } });
	}
	
	@Test
	void test8() {
		EightQueens.queensAreSafe(new char[][]{ new char[]{ 'r', 's', 'g' }, new char[]{ '4', '@', 'N' }, new char[]{ 'W', 'V' } });
	}
	
	@Test
	void test9() {
		EightQueens.onlyContains(new char[][]{ new char[]{ 'O' } }, new char[]{ 'x', 'V' });
	}
	
	@Test
	void test10() {
		EightQueens.contains(new char[]{ 'w', 'u' }, 'i');
	}
	
	@Test
	void test11() {
		EightQueens.inbounds(160202630, 1829402076, new char[][]{ new char[]{ '%' } });
	}
	
	@Test
	void test12() {
		EightQueens.main(new java.lang.String[]{ "NzmR|wsC!^@R7j;*LtO2", "vQD%(/J%er+$qufDY*i_" });
	}
	
	@Test
	void test13() {
		EightQueens.contains(new char[0], 'Z');
	}
	
	@Test
	void test14() {
		EightQueens.isSquare(new char[][]{ new char[0], new char[]{ 'h', 'Q' }, new char[]{ 'N', 'r', '[' } });
	}
	

}
