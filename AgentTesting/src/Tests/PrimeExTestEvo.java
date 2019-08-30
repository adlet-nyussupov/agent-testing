package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.PrimeEx;

class PrimeExTestEvo {

	@BeforeEach
	void setUp() throws Exception {
	}

	 @Test()
	  public void test00()  throws Throwable  {
	      boolean boolean0 = PrimeEx.isPrime(361);
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test01()  throws Throwable  {
	      boolean[] booleanArray0 = PrimeEx.getPrimes(4);
	      assertTrue(Arrays.equals(new boolean[] {false, false, true, true, false}, booleanArray0));
	  }

	  @Test()
	  public void test02()  throws Throwable  {
	      boolean[] booleanArray0 = PrimeEx.getPrimes((-1));
	      assertEquals(0, booleanArray0.length);
	  }

	  @Test()
	  public void test03()  throws Throwable  {
	      // Undeclared exception!
	      try { 
	        PrimeEx.printTest(0, 3);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // failed precondition. num must be >= 2. num: 0
	         //
	      }
	  }

	  @Test()
	  public void test04()  throws Throwable  {
	      // Undeclared exception!
	      PrimeEx.numFactors(1431655765);
	  }

	  @Test()
	  public void test05()  throws Throwable  {
	      // Undeclared exception!
	      try { 
	        PrimeEx.getPrimes((-2210));
	        fail("Expecting exception: NegativeArraySizeException");
	      
	      } catch(NegativeArraySizeException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	       //  verifyException("PrimeEx", e);
	      }
	  }

	  @Test()
	  public void test06()  throws Throwable  {
	      int int0 = PrimeEx.numFactors(4);
	      assertEquals(3, int0);
	  }

	  @Test()
	  public void test07()  throws Throwable  {
	      int int0 = PrimeEx.numFactors(2565);
	      assertEquals(16, int0);
	  }

	  @Test()
	  public void test08()  throws Throwable  {
	      PrimeEx.printTest(16, 2);
	  }

	  @Test()
	  public void test09()  throws Throwable  {
	      // Undeclared exception!
	      try { 
	        PrimeEx.numFactors(0);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // failed precondition. num must be >= 2. num: 0
	         //
	      }
	  }

	  @Test()
	  public void test10()  throws Throwable  {
	      boolean boolean0 = PrimeEx.isPrime(1291);
	      assertTrue(boolean0);
	  }

	  @Test()
	  public void test11()  throws Throwable  {
	      boolean boolean0 = PrimeEx.isPrime(2565);
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test12()  throws Throwable  {
	      boolean boolean0 = PrimeEx.isPrime(2);
	      assertTrue(boolean0);
	  }

	  @Test()
	  public void test13()  throws Throwable  {
	      // Undeclared exception!
	      try { 
	        PrimeEx.isPrime((-6935));
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // failed precondition. num must be >= 2. num: -6935
	         //
	      }
	  }

	  @Test()
	  public void test14()  throws Throwable  {
	      boolean boolean0 = PrimeEx.isPrime(4);
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test15()  throws Throwable  {
	      PrimeEx.printTest(2, 2);
	  }

	  @Test()
	  public void test16()  throws Throwable  {
	      PrimeEx.printTest(638, 638);
	  }

	  @Test()
	  public void test17()  throws Throwable  {
	      PrimeEx.getPrimes(1788);
	      // Undeclared exception!
	      PrimeEx.getPrimes(4254);
	  }

	  @Test()
	  public void test18()  throws Throwable  {
	      String[] stringArray0 = new String[8];
	      PrimeEx.main(stringArray0);
	      assertEquals(8, stringArray0.length);
	  }


	


}
