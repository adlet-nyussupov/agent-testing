package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.CreateASet;

class CreateASetTestEvo {

	@BeforeEach
	void setUp() throws Exception {
	}

	 @Test()
	  public void test0()  throws Throwable  {
	      Object[] objectArray0 = new Object[0];
	      Object[] objectArray1 = CreateASet.makeSet(objectArray0);
	      assertFalse(objectArray1.equals((Object)objectArray0));
	  }

	  @Test()
	  public void test1()  throws Throwable  {
	      CreateASet createASet0 = new CreateASet();
	      Object[] objectArray0 = new Object[9];
	      objectArray0[0] = (Object) createASet0;
	      objectArray0[1] = (Object) createASet0;
	      objectArray0[2] = (Object) createASet0;
	      objectArray0[3] = (Object) createASet0;
	      objectArray0[4] = objectArray0[0];
	      objectArray0[5] = (Object) createASet0;
	      objectArray0[6] = (Object) createASet0;
	      objectArray0[7] = objectArray0[2];
	      objectArray0[8] = (Object) createASet0;
	      boolean boolean0 = CreateASet.noNulls(objectArray0);
	      assertTrue(boolean0);
	  }

	  @Test()
	  public void test2()  throws Throwable  {
	      Object[] objectArray0 = new Object[9];
	      boolean boolean0 = CreateASet.noNulls(objectArray0);
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test3()  throws Throwable  {
	      // Undeclared exception!
	      try { 
	        CreateASet.noNulls((Object[]) null);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Failed precondition makeSet. parameter cannot be null
	         //
	      }
	  }

	  @Test()
	  public void test4()  throws Throwable  {
	      CreateASet createASet0 = new CreateASet();
	      Object[] objectArray0 = new Object[3];
	      objectArray0[0] = (Object) createASet0;
	      objectArray0[1] = (Object) createASet0;
	      objectArray0[2] = (Object) createASet0;
	      Object[] objectArray1 = CreateASet.makeSet(objectArray0);
	      assertEquals(1, objectArray1.length);
	  }

	  @Test()
	  public void test5()  throws Throwable  {
	      // Undeclared exception!
	      try { 
	        CreateASet.makeSet((Object[]) null);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Failed precondition makeSet. parameter cannot be null
	         //
	      }
	  }

	  @Test()
	  public void test6()  throws Throwable  {
	      Object[] objectArray0 = new Object[3];
	      // Undeclared exception!
	      try { 
	        CreateASet.makeSet(objectArray0);
	        fail("Expecting exception: AssertionError");
	      
	      } catch(AssertionError e) {
	         //
	         // Failed precondition makeSet. no elements of parameter can be null
	         //
	      }
	  }

	  @Test()
	  public void test7()  throws Throwable  {
	      CreateASet.main((String[]) null);
	  }


}
