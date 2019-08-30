package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.MineSweeper;

class MineSweeperTestEvo {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test()
	  public void test0()  throws Throwable  {
	      MineSweeper mineSweeper0 = new MineSweeper();
	      // Undeclared exception!
	      try { 
	        mineSweeper0.inBounds(1, 1);
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	       //  verifyException("MineSweeper", e);
	      }
	  }

	  @Test()
	  public void test1()  throws Throwable  {
	      MineSweeper mineSweeper0 = new MineSweeper();
	      boolean boolean0 = mineSweeper0.inBounds((-747), (-747));
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test2()  throws Throwable  {
	      MineSweeper mineSweeper0 = new MineSweeper();
	      // Undeclared exception!
	      try { 
	        mineSweeper0.inBounds(0, (-642));
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	       //  verifyException("MineSweeper", e);
	      }
	  }

	  @Test()
	  public void test3()  throws Throwable  {
	      MineSweeper mineSweeper0 = new MineSweeper();
	      // Undeclared exception!
	      try { 
	        mineSweeper0.cellPicked(0, 0);
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	         //verifyException("MineSweeper", e);
	      }
	  }

	  @Test()
	  public void test4()  throws Throwable  {
	      MineSweeper mineSweeper0 = new MineSweeper();
	      mineSweeper0.cellPicked((-4019), (-4019));
	  }



}
