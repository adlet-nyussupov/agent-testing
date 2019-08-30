package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.Counter;

class CounterTestEvo {

	@BeforeEach
	void setUp() throws Exception {
	}

	  @Test()
	  public void test0()  throws Throwable  {
	      Counter counter0 = new Counter();
	      int[] intArray0 = new int[24];
	      intArray0[2] = 29;
	      intArray0[3] = 585;
	      intArray0[4] = (-1231);
	      intArray0[5] = (-1);
	      intArray0[6] = 858;
	      counter0.calc(intArray0);
	      assertEquals(24, intArray0.length);
	  }

	  @Test()
	  public void test1()  throws Throwable  {
	      Counter counter0 = new Counter();
	      int[] intArray0 = new int[8];
	      intArray0[1] = (-515);
	      intArray0[2] = (-515);
	      intArray0[3] = 1906;
	      intArray0[6] = (-1);
	      intArray0[7] = (-515);
	      counter0.calc(intArray0);
	      assertArrayEquals(new int[] {0, (-515), (-515), 1906, 0, 0, (-1), (-515)}, intArray0);
	  }

	  @Test()
	  public void test2()  throws Throwable  {
	      Counter counter0 = new Counter();
	      int[] intArray0 = new int[6];
	      intArray0[2] = 1948;
	      intArray0[3] = (-65);
	      intArray0[4] = (-1403);
	      counter0.calc(intArray0);
	      assertArrayEquals(new int[] {0, 0, 1948, (-65), (-1403), 0}, intArray0);
	  }

	  @Test()
	  public void test3()  throws Throwable  {
	      Counter counter0 = new Counter();
	      // Undeclared exception!
	      try { 
	        counter0.calc((int[]) null);
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	       
	      }
	  }

	  @Test()
	  public void test4()  throws Throwable  {
	      Counter counter0 = new Counter();
	      int[] intArray0 = new int[8];
	      intArray0[1] = (-515);
	      intArray0[2] = (-515);
	      intArray0[5] = 1906;
	      intArray0[6] = (-3);
	      intArray0[7] = (-515);
	      counter0.calc(intArray0);
	      assertEquals(8, intArray0.length);
	  }

	  @Test()
	  public void test5()  throws Throwable  {
	      Counter counter0 = new Counter();
	      int[] intArray0 = new int[7];
	      intArray0[0] = (-1452);
	      intArray0[1] = 1906;
	      counter0.calc(intArray0);
	      assertArrayEquals(new int[] {(-1452), 1906, 0, 0, 0, 0, 0}, intArray0);
	  }

	  @Test()
	  public void test6()  throws Throwable  {
	      Counter counter0 = new Counter();
	      int[] intArray0 = new int[5];
	      intArray0[0] = 2304;
	      counter0.calc(intArray0);
	      assertEquals(5, intArray0.length);
	  }

	  @Test()
	  public void test7()  throws Throwable  {
	      Counter counter0 = new Counter();
	      int[] intArray0 = new int[5];
	      counter0.calc(intArray0);
	      assertEquals(5, intArray0.length);
	  }

}
