package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.IntListVer3;

class IntListVer3TestEvo {

	@BeforeEach
	void setUp() throws Exception {
	}

	 @Test()
	  public void test00()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3(2303);
	      intListVer3_0.add(9128);
	      IntListVer3 intListVer3_1 = new IntListVer3();
	      intListVer3_1.add(2303);
	      boolean boolean0 = intListVer3_0.equals(intListVer3_1);
	      assertEquals(1, intListVer3_1.size());
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test01()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3();
	      IntListVer3 intListVer3_1 = new IntListVer3();
	      intListVer3_1.add(1103);
	      boolean boolean0 = intListVer3_0.equals(intListVer3_1);
	      assertEquals(1, intListVer3_1.size());
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test02()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3();
	      // Undeclared exception!
	      try { 
	        intListVer3_0.remove(0);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Failed precondition remove. pos it out of bounds. Value of pos: 0
	         //
	      }
	  }

	  @Test()
	  public void test03()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3();
	      // Undeclared exception!
	      try { 
	        intListVer3_0.get(408);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Failed precondition get. pos it out of bounds. Value of pos: 408
	         //
	      }
	  }

	  @Test()
	  public void test04()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3(1);
	      intListVer3_0.add((-2965));
	      int int0 = intListVer3_0.size();
	      assertEquals(1, int0);
	  }

	  @Test()
	  public void test05()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3();
	      intListVer3_0.add(2427);
	      int int0 = intListVer3_0.remove(0);
	      assertEquals(0, intListVer3_0.size());
	      assertEquals(2427, int0);
	  }

	  @Test()
	  public void test06()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3();
	      intListVer3_0.insert(0, 0);
	      int int0 = intListVer3_0.get(0);
	      assertEquals(1, intListVer3_0.size());
	      assertEquals(0, int0);
	  }

	  @Test()
	  public void test07()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3();
	      intListVer3_0.add(2427);
	      int int0 = intListVer3_0.get(0);
	      assertEquals(1, intListVer3_0.size());
	      assertEquals(2427, int0);
	  }

	  @Test()
	  public void test08()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3(1);
	      intListVer3_0.ensureCapcity();
	      assertEquals(0, intListVer3_0.size());
	  }

	  @Test()
	  public void test09()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3(1);
	      intListVer3_0.add((-2965));
	      intListVer3_0.ensureCapcity();
	      assertEquals(1, intListVer3_0.size());
	  }

	  @Test()
	  public void test10()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3(1);
	      int int0 = intListVer3_0.size();
	      assertEquals(0, int0);
	  }

	  @Test()
	  public void test11()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3(2303);
	      intListVer3_0.add(1);
	      IntListVer3 intListVer3_1 = new IntListVer3();
	      intListVer3_1.add(2303);
	      boolean boolean0 = intListVer3_0.equals(intListVer3_1);
	      assertEquals(1, intListVer3_1.size());
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test12()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3(2303);
	      Object object0 = new Object();
	      boolean boolean0 = intListVer3_0.equals(object0);
	      assertEquals(0, intListVer3_0.size());
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test13()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3(2303);
	      intListVer3_0.add(1);
	      IntListVer3 intListVer3_1 = new IntListVer3();
	      boolean boolean0 = intListVer3_0.equals(intListVer3_1);
	      assertEquals(1, intListVer3_0.size());
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test14()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3();
	      boolean boolean0 = intListVer3_0.equals((Object) null);
	      assertFalse(boolean0);
	      assertEquals(0, intListVer3_0.size());
	  }

	  @Test()
	  public void test15()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3(2303);
	      boolean boolean0 = intListVer3_0.equals(intListVer3_0);
	      assertTrue(boolean0);
	      assertEquals(0, intListVer3_0.size());
	  }

	  @Test()
	  public void test16()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3(1);
	      intListVer3_0.add((-2965));
	      intListVer3_0.insert(1, 0);
	      intListVer3_0.toStringUsingStringBuffer();
	      assertEquals(2, intListVer3_0.size());
	  }

	  @Test()
	  public void test17()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3(1);
	      String string0 = intListVer3_0.toStringUsingStringBuffer();
	      assertEquals("size: 0, elements: []", string0);
	  }

	  @Test()
	  public void test18()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3(1);
	      intListVer3_0.add((-2965));
	      intListVer3_0.insert(1, 0);
	      intListVer3_0.toString();
	      assertEquals(2, intListVer3_0.size());
	  }

	  @Test()
	  public void test19()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3(1);
	      String string0 = intListVer3_0.toString();
	      assertEquals("size: 0, elements: []", string0);
	  }

	  @Test()
	  public void test20()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3(1);
	      intListVer3_0.add((-2965));
	      intListVer3_0.insert(1, 0);
	      intListVer3_0.insert(0, (-2965));
	      int int0 = intListVer3_0.remove(1);
	      assertEquals(2, intListVer3_0.size());
	      assertEquals((-2965), int0);
	  }

	  @Test()
	  public void test21()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3(1);
	      intListVer3_0.add((-2965));
	      intListVer3_0.insert(1, 0);
	      int int0 = intListVer3_0.remove(1);
	      assertEquals(1, intListVer3_0.size());
	      assertEquals(0, int0);
	  }

	  @Test()
	  public void test22()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3(1);
	      // Undeclared exception!
	      try { 
	        intListVer3_0.remove((-440));
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Failed precondition remove. pos it out of bounds. Value of pos: -440
	         //
	      }
	  }

	  @Test()
	  public void test23()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3(1);
	      // Undeclared exception!
	      try { 
	        intListVer3_0.remove(1);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Failed precondition remove. pos it out of bounds. Value of pos: 1
	         //
	      }
	  }

	  @Test()
	  public void test24()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3();
	      // Undeclared exception!
	      try { 
	        intListVer3_0.insert(2, 2);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Failed precondition insert. pos is invalid. Value of pos: 2
	         //
	      }
	  }

	  @Test()
	  public void test25()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3();
	      // Undeclared exception!
	      try { 
	        intListVer3_0.insert((-1), 0);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Failed precondition insert. pos is invalid. Value of pos: -1
	         //
	      }
	  }

	  @Test()
	  public void test26()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3(1);
	      intListVer3_0.add((-2965));
	      int int0 = intListVer3_0.get(0);
	      assertEquals(1, intListVer3_0.size());
	      assertEquals((-2965), int0);
	  }

	  @Test()
	  public void test27()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3();
	      // Undeclared exception!
	      try { 
	        intListVer3_0.get((-792));
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Failed precondition get. pos it out of bounds. Value of pos: -792
	         //
	      }
	  }

	  @Test()
	  public void test28()  throws Throwable  {
	      IntListVer3 intListVer3_0 = null;
	      try {
	        intListVer3_0 = new IntListVer3((-571));
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Violation of precondition. IntListVer1(int initialCap):initialCap must be greater than 0. Value of initialCap: -571
	         //
	      }
	  }

	  @Test()
	  public void test29()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3(2303);
	      intListVer3_0.resize();
	      assertEquals(0, intListVer3_0.size());
	  }

	  @Test()
	  public void test30()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3();
	      intListVer3_0.add(2303);
	      IntListVer3 intListVer3_1 = new IntListVer3(2303);
	      intListVer3_1.add(2303);
	      boolean boolean0 = intListVer3_0.equals(intListVer3_1);
	      assertEquals(1, intListVer3_0.size());
	      assertTrue(boolean0);
	  }


	  @Test()
	  public void test32()  throws Throwable  {
	      IntListVer3 intListVer3_0 = new IntListVer3();
	      // Undeclared exception!
	      try { 
	        intListVer3_0.get(0);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Failed precondition get. pos it out of bounds. Value of pos: 0
	         //
	      }
	  }

}
