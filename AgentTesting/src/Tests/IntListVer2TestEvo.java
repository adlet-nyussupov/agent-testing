package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.IntListVer2;

class IntListVer2TestEvo {

	@BeforeEach
	void setUp() throws Exception {
	}
	@Test()
	  public void test00()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2();
	      intListVer2_0.add(0);
	      IntListVer2 intListVer2_1 = new IntListVer2(10);
	      intListVer2_1.add(10);
	      boolean boolean0 = intListVer2_0.equals(intListVer2_1);
	      assertEquals(1, intListVer2_1.iSize);
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test01()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2();
	      assertEquals(0, intListVer2_0.iSize);
	      
	      intListVer2_0.iSize = (-76);
	      IntListVer2 intListVer2_1 = new IntListVer2();
	      boolean boolean0 = intListVer2_0.equals(intListVer2_1);
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test02()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2(790);
	      assertEquals(0, intListVer2_0.iSize);
	      
	      intListVer2_0.iSize = (-1);
	      String string0 = intListVer2_0.toStringUsingStringBuffer();
	      assertEquals("size: -1, elements: []", string0);
	  }

	  @Test()
	  public void test03()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2(790);
	      assertEquals(0, intListVer2_0.iSize);
	      
	      intListVer2_0.iSize = (-1);
	      String string0 = intListVer2_0.toString();
	      assertEquals("size: -1, elements: []", string0);
	  }

	  @Test()
	  public void test04()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2();
	      intListVer2_0.iSize = 2137;
	      // Undeclared exception!
	      try { 
	        intListVer2_0.add(2);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	      }
	  }

	  @Test()
	  public void test05()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2(3862);
	      intListVer2_0.iSize = 3501;
	      intListVer2_0.toStringUsingStringBuffer();
	      intListVer2_0.toStringUsingStringBuffer();
	      // Undeclared exception!
	      intListVer2_0.toStringUsingStringBuffer();
	  }

	  @Test()
	  public void test06()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2(2941);
	      intListVer2_0.add(0);
	      intListVer2_0.iValues = null;
	      // Undeclared exception!
	      try { 
	        intListVer2_0.toStringUsingStringBuffer();
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	      }
	  }

	  @Test()
	  public void test07()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2();
	      intListVer2_0.iSize = 3909;
	      // Undeclared exception!
	      try { 
	        intListVer2_0.toStringUsingStringBuffer();
	        fail("Expecting exception: ArrayIndexOutOfBoundsException");
	      
	      } catch(ArrayIndexOutOfBoundsException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	      }
	  }

	  @Test()
	  public void test08()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2(2694);
	      intListVer2_0.iSize = 2694;
	      intListVer2_0.toString();
	      intListVer2_0.toString();
	      intListVer2_0.toString();
	      // Undeclared exception!
	      intListVer2_0.toString();
	  }

	  @Test()
	  public void test09()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2();
	      intListVer2_0.add(0);
	      intListVer2_0.iValues = null;
	      // Undeclared exception!
	      try { 
	        intListVer2_0.toString();
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	      }
	  }

	  @Test()
	  public void test10()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2();
	      intListVer2_0.iSize = 3633;
	      // Undeclared exception!
	      try { 
	        intListVer2_0.toString();
	        fail("Expecting exception: ArrayIndexOutOfBoundsException");
	      
	      } catch(ArrayIndexOutOfBoundsException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	      }
	  }

	  @Test()
	  public void test11()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2();
	      intListVer2_0.iValues = null;
	      // Undeclared exception!
	      try { 
	        intListVer2_0.resize();
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	        // verifyException("IntListVer2", e);
	      }
	  }

	  @Test()
	  public void test12()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2();
	      intListVer2_0.iValues = null;
	      // Undeclared exception!
	      try { 
	        intListVer2_0.add((-5900));
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	       //  verifyException("IntListVer2", e);
	      }
	  }

	  @Test()
	  public void test13()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2();
	      intListVer2_0.add(1);
	      IntListVer2 intListVer2_1 = new IntListVer2(10);
	      intListVer2_1.iValues = intListVer2_0.iValues;
	      intListVer2_1.add(10);
	      boolean boolean0 = intListVer2_0.equals(intListVer2_1);
	      assertEquals(1, intListVer2_1.iSize);
	      assertTrue(boolean0);
	  }

	  @Test()
	  public void test14()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2();
	      IntListVer2 intListVer2_1 = new IntListVer2(10);
	      intListVer2_0.add(0);
	      intListVer2_1.add(10);
	      boolean boolean0 = intListVer2_1.equals(intListVer2_0);
	      assertEquals(1, intListVer2_1.iSize);
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test15()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2();
	      IntListVer2 intListVer2_1 = new IntListVer2();
	      intListVer2_1.add(10);
	      boolean boolean0 = intListVer2_1.equals(intListVer2_0);
	      assertEquals(1, intListVer2_1.iSize);
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test16()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2();
	      boolean boolean0 = intListVer2_0.equals(intListVer2_0);
	      assertTrue(boolean0);
	      assertEquals(0, intListVer2_0.iSize);
	  }

	  @Test()
	  public void test17()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2();
	      boolean boolean0 = intListVer2_0.equals((Object) null);
	      assertEquals(0, intListVer2_0.iSize);
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test18()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2();
	      boolean boolean0 = intListVer2_0.equals("size: 1, elements: [0]");
	      assertFalse(boolean0);
	      assertEquals(0, intListVer2_0.iSize);
	  }

	  @Test()
	  public void test19()  throws Throwable  {
	      IntListVer2 intListVer2_0 = null;
	      try {
	        intListVer2_0 = new IntListVer2((-22));
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Violation of precondition. IntListVer1(int initialCap):initialCap must be greater than 0. Value of initialCap: -22
	         //
	      }
	  }

	  @Test()
	  public void test20()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2();
	      String string0 = intListVer2_0.toStringUsingStringBuffer();
	      assertEquals("size: 0, elements: []", string0);
	  }

	  @Test()
	  public void test21()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2();
	      String string0 = intListVer2_0.toString();
	      assertEquals("size: 0, elements: []", string0);
	  }

	  @Test()
	  public void test22()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2();
	      intListVer2_0.iSize = (-1291);
	      // Undeclared exception!
	      try { 
	        intListVer2_0.add(0);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	      }
	  }

	  @Test()
	  public void test23()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2();
	      intListVer2_0.iValues = intListVer2_0.iValues;
	      int[] intArray0 = new int[0];
	      intListVer2_0.iValues = intArray0;
	      // Undeclared exception!
	      try { 
	        intListVer2_0.add(10);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	      }
	  }


	  @Test()
	  public void test25()  throws Throwable  {
	      IntListVer2 intListVer2_0 = new IntListVer2();
	      intListVer2_0.resize();
	      assertEquals(0, intListVer2_0.iSize);
	  }
	

}
