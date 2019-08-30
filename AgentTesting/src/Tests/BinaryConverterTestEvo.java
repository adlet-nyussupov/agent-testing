package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.BinaryConverter;

class BinaryConverterTestEvo {

	@BeforeEach
	void setUp() throws Exception {
	}

	 @Test()
	  public void test0()  throws Throwable  {
	      boolean boolean0 = BinaryConverter.all0sAnd1s("TJp$8}|_%B)BXY");
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test1()  throws Throwable  {
	      String string0 = BinaryConverter.toBinary(48);
	      assertEquals("110000", string0);
	  }

	  @Test()
	  public void test2()  throws Throwable  {
	      boolean boolean0 = BinaryConverter.all0sAnd1s("110000");
	      assertTrue(boolean0);
	  }

	  @Test()
	  public void test3()  throws Throwable  {
	      boolean boolean0 = BinaryConverter.all0sAnd1s("-1111101011");
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test4()  throws Throwable  {
	      // Undeclared exception!
	      try { 
	        BinaryConverter.all0sAnd1s((String) null);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Failed precondition all0sAnd1s. parameter cannot be null
	         //
	      }
	  }

	  @Test()
	  public void test5()  throws Throwable  {
	      String string0 = BinaryConverter.toBinary((-1003));
	      assertEquals("-1111101011", string0);
	  }

	  @Test()
	  public void test6()  throws Throwable  {
	      String string0 = BinaryConverter.toBinary(0);
	      assertEquals("0", string0);
	  }

	  @Test()
	  public void test7()  throws Throwable  {
	      String[] stringArray0 = new String[5];
	      BinaryConverter.main(stringArray0);
	      assertEquals(5, stringArray0.length);
	  }

}
