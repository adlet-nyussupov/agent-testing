package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.PalindromeCheck;

class PalindromeCheckTestEvo {

	@BeforeEach
	void setUp() throws Exception {
	}

	 @Test()
	  public void test0()  throws Throwable  {
	      // Undeclared exception!
	      try { 
	        PalindromeCheck.isPal((String) null);
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	        // verifyException("PalindromeCheck", e);
	      }
	  }

	  @Test()
	  public void test1()  throws Throwable  {
	      boolean boolean0 = PalindromeCheck.isPal("tYI]`K^At");
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test2()  throws Throwable  {
	      boolean boolean0 = PalindromeCheck.isPal("F");
	      assertTrue(boolean0);
	  }

	  @Test()
	  public void test3()  throws Throwable  {
	      boolean boolean0 = PalindromeCheck.isPal("fqh");
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test4()  throws Throwable  {
	      boolean boolean0 = PalindromeCheck.isPal("");
	      assertTrue(boolean0);
	  }

	  @Test()
	  public void test5()  throws Throwable  {
	      String[] stringArray0 = new String[6];
	      PalindromeCheck.main(stringArray0);
	      assertEquals(6, stringArray0.length);
	  }

	

}
