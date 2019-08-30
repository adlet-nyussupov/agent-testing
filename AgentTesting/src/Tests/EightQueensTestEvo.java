package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.EightQueens;

class EightQueensTestEvo {

	@BeforeEach
	void setUp() throws Exception {
	}

	  @Test()
	  public void test00()  throws Throwable  {
	      int int0 = 0;
	      EightQueens.solveNQueens(0);
	      char[][] charArrayArray0 = null;
	      // Undeclared exception!
	      try { 
	        EightQueens.queensAreSafe((char[][]) null);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Violation of precondition: queensAreSafe
	         //
	      }
	  }

	  @Test()
	  public void test01()  throws Throwable  {
	      char[][] charArrayArray0 = null;
	      // Undeclared exception!
	      try { 
	        EightQueens.printBoard((char[][]) null);
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	         //verifyException("EightQueens", e);
	      }
	  }

	  @Test()
	  public void test02()  throws Throwable  {
	      char[][] charArray0 = new char[2][8];
	      char[] charArray1 = new char[6];
	      charArray1[0] = 'N';
	      charArray1[1] = 'g';
	      charArray1[2] = 'c';
	      charArray1[3] = '0';
	      charArray1[4] = 'l';
	      charArray1[5] = 'S';
	      charArray0[0] = charArray1;
	      char[] charArray2 = new char[9];
	      charArray2[0] = 'c';
	      charArray2[1] = '0';
	      charArray2[2] = 'l';
	      charArray2[3] = 'S';
	      charArray2[4] = 'S';
	      charArray2[5] = 'S';
	      charArray2[6] = 'N';
	      charArray2[7] = 'S';
	      charArray2[8] = 'N';
	      charArray0[1] = charArray2;
	      EightQueens.inbounds(0, (-3828), charArray0);
	  }

	  @Test()
	  public void test03()  throws Throwable  {
	      char[][] charArray0 = new char[0][6];
	      char[] charArray1 = null;
	      EightQueens.isSquare(charArray0);
	      // Undeclared exception!
	      try { 
	        EightQueens.onlyContains(charArray0, (char[]) null);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Violation of precondition: onlyContains
	         //
	      }
	  }

	  @Test()
	  public void test04()  throws Throwable  {
	      char[][] charArray0 = EightQueens.blankBoard(113);
	      EightQueens.canSolve(charArray0, 113);
	      char[] charArray1 = new char[7];
	      charArray1[0] = '/';
	      charArray1[1] = 'd';
	      charArray1[2] = '7';
	      charArray1[3] = '7';
	      charArray1[4] = '^';
	      charArray1[5] = 'p';
	      charArray1[6] = 'F';
	      EightQueens.contains(charArray1, 'C');
	      EightQueens.onlyContains(charArray0, charArray1);
	      EightQueens.isSquare(charArray0);
	      // Undeclared exception!
	      EightQueens.canSolve(charArray0, 0);
	  }

	  @Test()
	  public void test05()  throws Throwable  {
	      char[] charArray0 = new char[6];
	      charArray0[0] = 'X';
	      charArray0[1] = '3';
	      charArray0[2] = 'd';
	      charArray0[3] = 'y';
	      charArray0[4] = 'V';
	      charArray0[5] = 'C';
	      EightQueens.contains(charArray0, '3');
	      char[][] charArrayArray0 = null;
	      int int0 = 113;
	      ArrayList<char[][]> arrayList0 = new ArrayList<char[][]>();
	      arrayList0.add((char[][]) null);
	      arrayList0.add((char[][]) null);
	      arrayList0.trimToSize();
	      // Undeclared exception!
	      try { 
	        EightQueens.solveAllNQueens((char[][]) null, 113, arrayList0);
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	         //verifyException("EightQueens", e);
	      }
	  }

	  @Test()
	  public void test06()  throws Throwable  {
	      char[] charArray0 = null;
	      char char0 = 'k';
	      // Undeclared exception!
	      try { 
	        EightQueens.contains((char[]) null, 'k');
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Violation of precondition: contains
	         //
	      }
	  }

	  @Test()
	  public void test07()  throws Throwable  {
	      char[] charArray0 = new char[2];
	      charArray0[0] = '&';
	      charArray0[1] = 'd';
	      EightQueens.contains(charArray0, '5');
	      int int0 = (-238);
	      char[][] charArrayArray0 = null;
	      // Undeclared exception!
	      try { 
	        EightQueens.inbounds(4183, (-238), (char[][]) null);
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	         //verifyException("EightQueens", e);
	      }
	  }

	  @Test()
	  public void test08()  throws Throwable  {
	      char[][] charArrayArray0 = null;
	      char[] charArray0 = new char[2];
	      charArray0[0] = '<';
	      charArray0[1] = 'y';
	      // Undeclared exception!
	      try { 
	        EightQueens.onlyContains((char[][]) null, charArray0);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Violation of precondition: onlyContains
	         //
	      }
	  }

	  @Test()
	  public void test09()  throws Throwable  {
	      char[] charArray0 = new char[5];
	      charArray0[0] = 'N';
	      charArray0[1] = 'u';
	      charArray0[2] = 'v';
	      charArray0[3] = '&';
	      charArray0[4] = 'F';
	      // Undeclared exception!
	      try { 
	        EightQueens.onlyContains((char[][]) null, charArray0);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Violation of precondition: onlyContains
	         //
	      }
	  }

	  @Test()
	  public void test10()  throws Throwable  {
	      String[] stringArray0 = new String[5];
	      stringArray0[0] = "";
	      stringArray0[1] = "";
	      stringArray0[2] = "38 `-KORLRo{H`vG";
	      stringArray0[3] = "";
	      stringArray0[4] = ".poE^Es+5{p7u";
	      EightQueens.main(stringArray0);
	      char[][] charArray0 = new char[1][9];
	      char[] charArray1 = new char[7];
	      charArray1[0] = 'n';
	      charArray1[1] = 'Q';
	      charArray1[2] = 'r';
	      charArray1[3] = '?';
	      charArray1[4] = '2';
	      charArray1[5] = '(';
	      charArray1[6] = '[';
	      charArray0[0] = charArray1;
	      EightQueens.makeCopy(charArray0);
	      EightQueens eightQueens0 = new EightQueens();
	      // Undeclared exception!
	      try { 
	        EightQueens.canSolve((char[][]) null, 0);
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	         //verifyException("EightQueens", e);
	      }
	  }

	  @Test()
	  public void test11()  throws Throwable  {
	      int int0 = 113;
	      char[][] charArray0 = EightQueens.blankBoard(113);
	      EightQueens eightQueens0 = new EightQueens();
	      EightQueens.canSolve(charArray0, 113);
	      // Undeclared exception!
	      EightQueens.queensAreSafe(charArray0);
	  }

	  @Test()
	  public void test12()  throws Throwable  {
	      char[][] charArray0 = new char[6][4];
	      char[] charArray1 = new char[9];
	      charArray1[0] = 'z';
	      charArray1[1] = '7';
	      charArray1[2] = 'k';
	      charArray1[3] = 'H';
	      charArray1[4] = '!';
	      charArray1[5] = '3';
	      charArray1[6] = 'u';
	      charArray1[7] = 'z';
	      charArray1[8] = '8';
	      charArray0[0] = charArray1;
	      char[] charArray2 = new char[2];
	      charArray2[0] = 'k';
	      charArray2[1] = '!';
	      charArray0[1] = charArray2;
	      char[] charArray3 = new char[4];
	      charArray3[0] = '7';
	      charArray3[1] = '3';
	      charArray3[2] = 'k';
	      charArray3[3] = '*';
	      charArray0[2] = charArray3;
	      char[] charArray4 = new char[5];
	      charArray4[0] = '*';
	      charArray4[1] = 'k';
	      charArray4[2] = '8';
	      charArray4[3] = 'x';
	      charArray4[4] = '\\';
	      charArray0[3] = charArray4;
	      char[] charArray5 = new char[0];
	      charArray0[4] = charArray5;
	      char[] charArray6 = new char[6];
	      charArray6[0] = 'u';
	      charArray6[1] = '\\';
	      charArray6[2] = 'J';
	      charArray6[3] = 'x';
	      charArray6[4] = 'H';
	      charArray6[5] = '!';
	      charArray0[5] = charArray6;
	      EightQueens.inbounds(2, 1452, charArray0);
	  }

	  @Test()
	  public void test13()  throws Throwable  {
	      char[] charArray0 = new char[4];
	      charArray0[0] = '!';
	      charArray0[1] = 'q';
	      charArray0[2] = ']';
	      charArray0[3] = '6';
	      EightQueens.contains(charArray0, ']');
	  }

	  @Test()
	  public void test14()  throws Throwable  {
	      char[][] charArray0 = new char[2][8];
	      char[] charArray1 = new char[5];
	      charArray1[0] = 'g';
	      charArray1[1] = 'P';
	      charArray1[2] = '0';
	      charArray1[3] = '\'';
	      charArray1[4] = '8';
	      charArray0[0] = charArray1;
	      char[] charArray2 = new char[5];
	      charArray2[0] = '~';
	      charArray2[1] = '0';
	      charArray2[2] = 'g';
	      charArray2[3] = '\'';
	      charArray2[4] = 'P';
	      charArray0[1] = charArray2;
	      EightQueens.onlyContains(charArray0, charArray2);
	      LinkedList<char[][]> linkedList0 = new LinkedList<char[][]>();
	      ArrayList<char[][]> arrayList0 = new ArrayList<char[][]>(linkedList0);
	      // Undeclared exception!
	      try { 
	        EightQueens.solveAllNQueens(charArray0, 0, arrayList0);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Violation of precondition: queensAreSafe
	         //
	      }
	  }

	  @Test()
	  public void test15()  throws Throwable  {
	      char[][] charArray0 = new char[5][4];
	      char[] charArray1 = new char[9];
	      charArray1[0] = '_';
	      charArray1[1] = '}';
	      charArray1[2] = 'e';
	      charArray1[3] = ',';
	      charArray1[4] = 'M';
	      charArray1[5] = 'x';
	      charArray1[6] = 'Y';
	      charArray1[7] = '@';
	      charArray1[8] = 'Z';
	      charArray0[0] = charArray1;
	      char[] charArray2 = new char[4];
	      charArray2[0] = 'Y';
	      charArray2[1] = '@';
	      charArray2[2] = '@';
	      charArray2[3] = 'M';
	      charArray0[1] = charArray2;
	      char[] charArray3 = new char[9];
	      charArray3[0] = 'x';
	      charArray3[1] = '}';
	      charArray3[2] = '_';
	      charArray3[3] = ',';
	      charArray3[4] = 'M';
	      charArray3[5] = 'x';
	      charArray3[6] = '1';
	      charArray3[7] = '^';
	      charArray3[8] = '@';
	      charArray0[2] = charArray3;
	      char[] charArray4 = new char[8];
	      charArray4[0] = 'A';
	      charArray4[1] = '}';
	      charArray4[2] = '6';
	      charArray4[3] = ',';
	      charArray4[4] = 'e';
	      charArray4[5] = ',';
	      charArray4[6] = '~';
	      charArray4[7] = '1';
	      charArray0[3] = charArray4;
	      char[] charArray5 = new char[7];
	      charArray5[0] = ':';
	      charArray5[1] = ',';
	      charArray5[2] = '#';
	      charArray5[3] = 'M';
	      charArray5[4] = 'x';
	      charArray5[5] = 'Z';
	      charArray5[6] = '6';
	      charArray0[4] = charArray5;
	      // Undeclared exception!
	      try { 
	        EightQueens.makeCopy(charArray0);
	        fail("Expecting exception: ArrayIndexOutOfBoundsException");
	      
	      } catch(ArrayIndexOutOfBoundsException e) {
	         //
	         // 4
	         //
	         //verifyException("EightQueens", e);
	      }
	  }

	  @Test()
	  public void test16()  throws Throwable  {
	      // Undeclared exception!
	      try { 
	        EightQueens.makeCopy((char[][]) null);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	      }
	  }

	  @Test()
	  public void test17()  throws Throwable  {
	      char[][] charArray0 = EightQueens.blankBoard(0);
	      EightQueens eightQueens0 = new EightQueens();
	      char[] charArray1 = new char[8];
	      charArray1[0] = '8';
	      charArray1[1] = 'a';
	      charArray1[2] = '~';
	      charArray1[3] = '(';
	      charArray1[4] = '[';
	      charArray1[5] = 'A';
	      charArray1[6] = 'g';
	      charArray1[7] = 'j';
	      EightQueens.onlyContains(charArray0, charArray1);
	  }

	  @Test()
	  public void test18()  throws Throwable  {
	      char[][] charArray0 = new char[3][4];
	      char[] charArray1 = new char[3];
	      charArray1[0] = 'G';
	      charArray1[1] = 'X';
	      charArray1[2] = '|';
	      charArray0[0] = charArray1;
	      char[] charArray2 = new char[2];
	      charArray2[0] = '|';
	      charArray2[1] = 'U';
	      charArray0[1] = charArray2;
	      char[] charArray3 = new char[6];
	      charArray3[0] = 'U';
	      charArray3[1] = '#';
	      charArray3[2] = 'X';
	      charArray3[3] = 'U';
	      charArray3[4] = 'G';
	      charArray3[5] = 'U';
	      charArray0[2] = charArray3;
	      EightQueens.isSquare(charArray0);
	  }

	  @Test()
	  public void test19()  throws Throwable  {
	      EightQueens.solveNQueens(0);
	      char[][] charArray0 = new char[0][1];
	      EightQueens.canSolve(charArray0, 0);
	      char[] charArray1 = new char[4];
	      charArray1[0] = ' ';
	      charArray1[1] = '%';
	      charArray1[2] = 'U';
	      charArray1[3] = 'O';
	      EightQueens.onlyContains(charArray0, charArray1);
	  }

	  @Test()
	  public void test20()  throws Throwable  {
	      char[][] charArray0 = new char[3][7];
	      char[] charArray1 = new char[2];
	      charArray1[0] = '|';
	      charArray1[1] = 'c';
	      charArray0[0] = charArray1;
	      char[] charArray2 = new char[1];
	      charArray2[0] = '|';
	      charArray0[1] = charArray2;
	      char[] charArray3 = new char[3];
	      charArray3[0] = 'c';
	      charArray3[1] = '|';
	      charArray3[2] = 'c';
	      charArray0[2] = charArray3;
	      EightQueens.onlyContains(charArray0, charArray1);
	  }

	  @Test()
	  public void test21()  throws Throwable  {
	      EightQueens eightQueens0 = new EightQueens();
	      int int0 = (-675);
	      // Undeclared exception!
	      try { 
	        EightQueens.getAllNQueens((-675));
	        fail("Expecting exception: NegativeArraySizeException");
	      
	      } catch(NegativeArraySizeException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	         //verifyException("EightQueens", e);
	      }
	  }

	  @Test()
	  public void test22()  throws Throwable  {
	      // Undeclared exception!
	      try { 
	        EightQueens.solveNQueens((-1441));
	        fail("Expecting exception: NegativeArraySizeException");
	      
	      } catch(NegativeArraySizeException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	         //verifyException("EightQueens", e);
	      }
	  }

	  @Test()
	  public void test23()  throws Throwable  {
	      EightQueens eightQueens0 = new EightQueens();
	      char[][] charArray0 = new char[0][4];
	      // Undeclared exception!
	      try { 
	        EightQueens.queensAreSafe(charArray0);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Violation of precondition: queensAreSafe
	         //
	      }
	  }

	  @Test()
	  public void test24()  throws Throwable  {
	      char[][] charArray0 = new char[0][4];
	      int int0 = 973;
	      EightQueens.canSolve(charArray0, 973);
	      char[][] charArray1 = EightQueens.blankBoard(973);
	      // Undeclared exception!
	      EightQueens.printBoard(charArray1);
	  }

	/*
	 * @Test() public void test25() throws Throwable { char[][] charArray0 = new
	 * char[0][9]; int int0 = 0; int int1 = 2166; // Undeclared exception!
	 * EightQueens.getAllNQueens(2166); }
	 */

	/*
	 * @Test() public void test26() throws Throwable { int int0 = 3156; //
	 * Undeclared exception! EightQueens.solveNQueens(3156); }
	 */

	  @Test()
	  public void test27()  throws Throwable  {
	      char[][] charArray0 = new char[1][9];
	      char[] charArray1 = new char[2];
	      charArray1[0] = ')';
	      charArray1[1] = '7';
	      charArray0[0] = charArray1;
	      EightQueens.printBoard(charArray0);
	      // Undeclared exception!
	      try { 
	        EightQueens.canSolve(charArray0, (-2525));
	        fail("Expecting exception: ArrayIndexOutOfBoundsException");
	      
	      } catch(ArrayIndexOutOfBoundsException e) {
	         //
	         // -2525
	         //
	         //verifyException("EightQueens", e);
	      }
	  }

	  @Test()
	  public void test28()  throws Throwable  {
	      char[][] charArray0 = new char[1][4];
	      char[] charArray1 = new char[4];
	      charArray1[0] = 'A';
	      charArray1[1] = ']';
	      charArray1[2] = '2';
	      charArray1[3] = '';
	      charArray0[0] = charArray1;
	      EightQueens.makeCopy(charArray0);
	  }

	  @Test()
	  public void test29()  throws Throwable  {
	      EightQueens eightQueens0 = new EightQueens();
	      char[][] charArray0 = new char[3][4];
	      char[] charArray1 = new char[4];
	      charArray1[0] = '\'';
	      charArray1[1] = '';
	      charArray1[2] = '>';
	      charArray1[3] = 'u';
	      charArray0[0] = charArray1;
	      char[] charArray2 = new char[8];
	      charArray2[0] = '\'';
	      charArray2[1] = '\'';
	      charArray2[2] = '';
	      charArray2[3] = 'u';
	      charArray2[4] = 'u';
	      charArray2[5] = 'e';
	      charArray2[6] = '>';
	      charArray2[7] = 'd';
	      charArray0[1] = charArray2;
	      char[] charArray3 = new char[8];
	      charArray3[0] = 'f';
	      charArray3[1] = 'd';
	      charArray3[2] = '\'';
	      charArray3[3] = '';
	      charArray3[4] = '';
	      charArray3[5] = 'u';
	      charArray3[6] = '0';
	      charArray3[7] = 'e';
	      charArray0[2] = charArray3;
	      // Undeclared exception!
	      try { 
	        EightQueens.canSolve(charArray0, (-4));
	        fail("Expecting exception: ArrayIndexOutOfBoundsException");
	      
	      } catch(ArrayIndexOutOfBoundsException e) {
	         //
	         // -4
	         //
	         //verifyException("EightQueens", e);
	      }
	  }

	  @Test()
	  public void test30()  throws Throwable  {
	      char[][] charArray0 = new char[6][7];
	      char[] charArray1 = new char[6];
	      charArray1[0] = 'o';
	      charArray1[1] = 't';
	      charArray1[2] = '!';
	      charArray1[3] = 'Y';
	      charArray1[4] = '#';
	      charArray1[5] = ' ';
	      charArray0[0] = charArray1;
	      char[] charArray2 = new char[5];
	      charArray2[0] = 'o';
	      charArray2[1] = 'W';
	      charArray2[2] = 'o';
	      charArray2[3] = '#';
	      charArray2[4] = '!';
	      charArray0[1] = charArray2;
	      char[] charArray3 = new char[9];
	      charArray3[0] = '#';
	      charArray3[1] = 'Y';
	      charArray3[2] = 'Y';
	      charArray3[3] = 'N';
	      charArray3[4] = 'Y';
	      charArray3[5] = '#';
	      charArray3[6] = '!';
	      charArray3[7] = '#';
	      charArray3[8] = '#';
	      charArray0[2] = charArray3;
	      char[] charArray4 = new char[8];
	      charArray4[0] = 'W';
	      charArray4[1] = '#';
	      charArray4[2] = 'N';
	      charArray4[3] = '>';
	      charArray4[4] = 't';
	      charArray4[5] = 'Y';
	      charArray4[6] = '#';
	      charArray4[7] = 'Y';
	      charArray0[3] = charArray4;
	      char[] charArray5 = new char[1];
	      charArray5[0] = 't';
	      charArray0[4] = charArray5;
	      char[] charArray6 = new char[0];
	      charArray0[5] = charArray6;
	      ArrayList<char[][]> arrayList0 = new ArrayList<char[][]>();
	      // Undeclared exception!
	      try { 
	        EightQueens.solveAllNQueens(charArray0, 0, arrayList0);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Violation of precondition: queensAreSafe
	         //
	      }
	  }

	  @Test()
	  public void test31()  throws Throwable  {
	      char[][] charArray0 = new char[8][4];
	      char[] charArray1 = new char[5];
	      charArray1[0] = 'p';
	      charArray1[1] = 'T';
	      charArray1[2] = 'Y';
	      charArray1[3] = 'G';
	      charArray1[4] = '\"';
	      charArray0[0] = charArray1;
	      char[] charArray2 = new char[5];
	      charArray2[0] = 'T';
	      charArray2[1] = '\"';
	      charArray2[2] = 'T';
	      charArray2[3] = 'p';
	      charArray2[4] = '|';
	      charArray0[1] = charArray2;
	      char[] charArray3 = new char[2];
	      charArray3[0] = '#';
	      charArray3[1] = 'Y';
	      charArray0[2] = charArray3;
	      char[] charArray4 = new char[1];
	      charArray4[0] = 'T';
	      charArray0[3] = charArray4;
	      char[] charArray5 = new char[8];
	      charArray5[0] = '|';
	      charArray5[1] = '|';
	      charArray5[2] = 'Y';
	      charArray5[3] = '|';
	      charArray5[4] = 'D';
	      charArray5[5] = 'G';
	      charArray5[6] = '#';
	      charArray5[7] = 'T';
	      charArray0[4] = charArray5;
	      char[] charArray6 = new char[0];
	      charArray0[5] = charArray6;
	      char[] charArray7 = new char[4];
	      charArray7[0] = 'p';
	      charArray7[1] = '#';
	      charArray7[2] = 'D';
	      charArray7[3] = '|';
	      charArray0[6] = charArray7;
	      char[] charArray8 = new char[1];
	      charArray8[0] = '\"';
	      charArray0[7] = charArray8;
	      // Undeclared exception!
	      try { 
	        EightQueens.solveAllNQueens(charArray0, 46, (ArrayList<char[][]>) null);
	        fail("Expecting exception: ArrayIndexOutOfBoundsException");
	      
	      } catch(ArrayIndexOutOfBoundsException e) {
	         //
	         // 46
	         //
	         //verifyException("EightQueens", e);
	      }
	  }

	  @Test()
	  public void test32()  throws Throwable  {
	      char[][] charArray0 = new char[0][6];
	      EightQueens.printBoard(charArray0);
	      EightQueens.canSolve(charArray0, 2);
	      EightQueens.isSquare(charArray0);
	  }

	  @Test()
	  public void test33()  throws Throwable  {
	      char[][] charArray0 = new char[5][2];
	      char[] charArray1 = new char[6];
	      charArray1[0] = '?';
	      charArray1[1] = 'B';
	      charArray1[2] = 'o';
	      charArray1[3] = '\'';
	      charArray1[4] = '=';
	      charArray1[5] = ':';
	      charArray0[0] = charArray1;
	      char[] charArray2 = new char[1];
	      charArray2[0] = '?';
	      charArray0[1] = charArray2;
	      char[] charArray3 = new char[3];
	      charArray3[0] = '=';
	      charArray3[1] = 'o';
	      charArray3[2] = ':';
	      charArray0[2] = charArray3;
	      char[] charArray4 = new char[4];
	      charArray4[0] = '@';
	      charArray4[1] = '5';
	      charArray4[2] = 'P';
	      charArray4[3] = '=';
	      charArray0[3] = charArray4;
	      char[] charArray5 = new char[1];
	      charArray5[0] = '@';
	      charArray0[4] = charArray5;
	      EightQueens.inbounds(0, 0, charArray0);
	  }

	  @Test()
	  public void test34()  throws Throwable  {
	      char[][] charArray0 = new char[2][2];
	      char[] charArray1 = new char[7];
	      charArray1[0] = ' ';
	      charArray1[1] = 'W';
	      charArray1[2] = '6';
	      charArray1[3] = 'J';
	      charArray1[4] = '8';
	      charArray1[5] = 'Y';
	      charArray1[6] = 'U';
	      charArray0[0] = charArray1;
	      char[] charArray2 = new char[1];
	      charArray2[0] = 'U';
	      charArray0[1] = charArray2;
	      EightQueens.inbounds(2, (-2123), charArray0);
	  }

	  @Test()
	  public void test35()  throws Throwable  {
	      char[][] charArray0 = new char[6][3];
	      char[] charArray1 = new char[5];
	      charArray1[0] = '8';
	      charArray1[1] = 'B';
	      charArray1[2] = '{';
	      charArray1[3] = 'I';
	      charArray1[4] = 'X';
	      charArray0[0] = charArray1;
	      char[] charArray2 = new char[2];
	      charArray2[0] = 'B';
	      charArray2[1] = '{';
	      charArray0[1] = charArray2;
	      char[] charArray3 = new char[1];
	      charArray3[0] = 'X';
	      charArray0[2] = charArray3;
	      char[] charArray4 = new char[7];
	      charArray4[0] = 'B';
	      charArray4[1] = 'X';
	      charArray4[2] = 'X';
	      charArray4[3] = 'I';
	      charArray4[4] = 'X';
	      charArray4[5] = 'X';
	      charArray4[6] = 'X';
	      charArray0[3] = charArray4;
	      char[] charArray5 = new char[7];
	      charArray5[0] = 'B';
	      charArray5[1] = 'I';
	      charArray5[2] = 'X';
	      charArray5[3] = 'I';
	      charArray5[4] = '8';
	      charArray5[5] = 'I';
	      charArray5[6] = 'X';
	      charArray0[4] = charArray5;
	      char[] charArray6 = new char[5];
	      charArray6[0] = 'I';
	      charArray6[1] = '8';
	      charArray6[2] = '{';
	      charArray6[3] = '{';
	      charArray6[4] = 'B';
	      charArray0[5] = charArray6;
	      // Undeclared exception!
	      try { 
	        EightQueens.queensAreSafe(charArray0);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Violation of precondition: queensAreSafe
	         //
	      }
	  }

	  @Test()
	  public void test36()  throws Throwable  {
	      int int0 = (-2603);
	      // Undeclared exception!
	      try { 
	        EightQueens.blankBoard((-2603));
	        fail("Expecting exception: NegativeArraySizeException");
	      
	      } catch(NegativeArraySizeException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	         //verifyException("EightQueens", e);
	      }
	  }

	  @Test()
	  public void test37()  throws Throwable  {
	      int int0 = (-1684);
	      char[][] charArrayArray0 = null;
	      EightQueens.inbounds((-1684), (-1684), (char[][]) null);
	      EightQueens eightQueens0 = new EightQueens();
	      ArrayList<char[][]> arrayList0 = new ArrayList<char[][]>();
	      arrayList0.add((char[][]) null);
	      // Undeclared exception!
	      try { 
	        EightQueens.solveAllNQueens((char[][]) null, 0, arrayList0);
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	         //verifyException("EightQueens", e);
	      }
	  }

	  @Test()
	  public void test38()  throws Throwable  {
	      char[][] charArray0 = new char[3][1];
	      char[] charArray1 = new char[7];
	      charArray1[0] = '(';
	      charArray1[1] = 'l';
	      charArray1[2] = 'X';
	      charArray1[3] = 'x';
	      charArray1[4] = 'd';
	      charArray1[5] = 'g';
	      charArray1[6] = 'C';
	      charArray0[0] = charArray1;
	      char[] charArray2 = new char[3];
	      charArray2[0] = '(';
	      charArray2[1] = 'x';
	      charArray2[2] = 'g';
	      charArray0[1] = charArray2;
	      char[] charArray3 = new char[4];
	      charArray3[0] = 'x';
	      charArray3[1] = 'g';
	      charArray3[2] = 'l';
	      charArray3[3] = 'C';
	      charArray0[2] = charArray3;
	      EightQueens.inbounds(0, 0, charArray0);
	  }

	  @Test()
	  public void test39()  throws Throwable  {
	      char[][] charArray0 = new char[0][5];
	      EightQueens.inbounds(113, 113, charArray0);
	      EightQueens eightQueens0 = new EightQueens();
	      // Undeclared exception!
	      try { 
	        EightQueens.solveNQueens((-2707));
	        fail("Expecting exception: NegativeArraySizeException");
	      
	      } catch(NegativeArraySizeException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	         //verifyException("EightQueens", e);
	      }
	  }

	/*
	 * @Test() public void test40() throws Throwable { char[] charArray0 = new
	 * char[1]; charArray0[0] = '\\'; EightQueens.contains(charArray0, '\\'); int
	 * int0 = 335; // Undeclared exception! EightQueens.getAllNQueens(335); }
	 */

	  @Test()
	  public void test41()  throws Throwable  {
	      char[][] charArrayArray0 = null;
	      // Undeclared exception!
	      try { 
	        EightQueens.isSquare((char[][]) null);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Violation of precondition: isSquare
	         //
	      }
	  }

	/*
	 * @Test() public void test42() throws Throwable { int int0 = 1802; //
	 * Undeclared exception! EightQueens.solveNQueens(1802); }
	 */

	  @Test()
	  public void test43()  throws Throwable  {
	      char[][] charArray0 = new char[9][3];
	      char[] charArray1 = new char[0];
	      charArray0[0] = charArray1;
	      char[] charArray2 = new char[8];
	      charArray2[0] = 'k';
	      charArray2[1] = 'x';
	      charArray2[2] = 'Y';
	      charArray2[3] = '-';
	      charArray2[4] = 'q';
	      charArray2[5] = '(';
	      charArray2[6] = '.';
	      charArray2[7] = 'J';
	      charArray0[1] = charArray2;
	      char[] charArray3 = new char[3];
	      charArray3[0] = 'k';
	      charArray3[1] = 'k';
	      charArray3[2] = 'x';
	      charArray0[2] = charArray3;
	      char[] charArray4 = new char[6];
	      charArray4[0] = '.';
	      charArray4[1] = 'q';
	      charArray4[2] = 'J';
	      charArray4[3] = 'k';
	      charArray4[4] = 'J';
	      charArray4[5] = 'q';
	      charArray0[3] = charArray4;
	      char[] charArray5 = new char[7];
	      charArray5[0] = 'x';
	      charArray5[1] = 'k';
	      charArray5[2] = 'k';
	      charArray5[3] = 'Y';
	      charArray5[4] = 'k';
	      charArray5[5] = '(';
	      charArray5[6] = 'J';
	      charArray0[4] = charArray5;
	      char[] charArray6 = new char[7];
	      charArray6[0] = '.';
	      charArray6[1] = '(';
	      charArray6[2] = 'x';
	      charArray6[3] = '.';
	      charArray6[4] = '-';
	      charArray6[5] = '.';
	      charArray6[6] = 'x';
	      charArray0[5] = charArray6;
	      char[] charArray7 = new char[0];
	      charArray0[6] = charArray7;
	      char[] charArray8 = new char[3];
	      charArray8[0] = ')';
	      charArray8[1] = 'J';
	      charArray8[2] = '(';
	      charArray0[7] = charArray8;
	      char[] charArray9 = new char[3];
	      charArray9[0] = 'k';
	      charArray9[1] = 'Y';
	      charArray9[2] = '(';
	      charArray0[8] = charArray9;
	      // Undeclared exception!
	      try { 
	        EightQueens.queensAreSafe(charArray0);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Violation of precondition: queensAreSafe
	         //
	      }
	  }

	  @Test()
	  public void test44()  throws Throwable  {
	      String[] stringArray0 = new String[3];
	      stringArray0[0] = "";
	      stringArray0[1] = "";
	      stringArray0[2] = "Violation of precondition: contains";
	      EightQueens.main(stringArray0);
	      EightQueens.solveNQueens(1);
	      char[][] charArray0 = new char[2][6];
	      char[] charArray1 = new char[2];
	      charArray1[0] = 'A';
	      charArray1[1] = '}';
	      charArray0[0] = charArray1;
	      char[] charArray2 = new char[9];
	      charArray2[0] = '}';
	      charArray2[1] = 'c';
	      charArray2[2] = '}';
	      charArray2[3] = 'A';
	      charArray2[4] = 'R';
	      charArray2[5] = 'A';
	      charArray2[6] = '}';
	      charArray2[7] = '}';
	      charArray2[8] = 'A';
	      charArray0[1] = charArray2;
	      // Undeclared exception!
	      try { 
	        EightQueens.canSolve(charArray0, 1);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Violation of precondition: queensAreSafe
	         //
	      }
	  }

	  @Test()
	  public void test45()  throws Throwable  {
	      // Undeclared exception!
	      try { 
	        EightQueens.getAllNQueens(0);
	        fail("Expecting exception: ArrayIndexOutOfBoundsException");
	      
	      } catch(ArrayIndexOutOfBoundsException e) {
	         //
	         // 0
	         //
	         //verifyException("EightQueens", e);
	      }
	  }

	/*
	 * @Test() public void test46() throws Throwable { char[][] charArray0 = new
	 * char[5][8]; char[] charArray1 = new char[3]; char char0 = '6'; charArray1[0]
	 * = '6'; char char1 = 'F'; charArray1[1] = 'F'; char char2 = 't'; charArray1[2]
	 * = 't'; charArray0[0] = charArray1; char[] charArray2 = new char[2];
	 * charArray2[0] = 't'; charArray2[1] = 't'; charArray0[1] = charArray2; char[]
	 * charArray3 = new char[3]; charArray3[0] = 't'; charArray3[1] = 'F';
	 * charArray3[2] = '6'; charArray0[2] = charArray3; char[] charArray4 = new
	 * char[0]; charArray0[3] = charArray4; char[] charArray5 = new char[0];
	 * charArray0[4] = charArray5; int int0 = 55; // Undeclared exception!
	 * EightQueens.getAllNQueens(55); }
	 */

	  @Test()
	  public void test47()  throws Throwable  {
	      char[][] charArray0 = new char[1][8];
	      char[] charArray1 = new char[0];
	      charArray0[0] = charArray1;
	      EightQueens.isSquare(charArray0);
	      EightQueens eightQueens0 = new EightQueens();
	      char char0 = '/';
	      char[][] charArray2 = new char[7][0];
	      char[] charArray3 = new char[6];
	      EightQueens.contains(charArray1, '/');
	      charArray3[0] = '/';
	      charArray3[1] = '/';
	      charArray3[2] = '/';
	      charArray3[3] = '/';
	      charArray3[4] = '/';
	      charArray3[5] = '/';
	      charArray2[0] = charArray3;
	      charArray2[1] = charArray1;
	      EightQueens.getAllNQueens(1);
	      charArray2[2] = charArray1;
	      charArray2[3] = charArray1;
	      charArray2[4] = charArray1;
	      charArray2[5] = charArray1;
	      charArray2[6] = charArray1;
	      int int0 = 0;
	      // Undeclared exception!
	      try { 
	        EightQueens.canSolve(charArray2, 0);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Violation of precondition: queensAreSafe
	         //
	      }
	  }

}
