package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.BubbleSort;

class BubbleSortTestEvo {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test()
	  public void test0()  throws Throwable  {
	      BubbleSort bubbleSort0 = new BubbleSort();
	      String[] stringArray0 = new String[8];
	      // Undeclared exception!
	      try { 
	        bubbleSort0.sort(stringArray0);
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	
	      }
	  }

	  @Test()
	  public void test1()  throws Throwable  {
	      BubbleSort bubbleSort0 = new BubbleSort();
	      String[] stringArray0 = new String[3];
	      stringArray0[0] = "";
	      stringArray0[1] = "+o:RLOmeF)Zy#y";
	      stringArray0[2] = "";
	      bubbleSort0.sort(stringArray0);
	      assertEquals(3, stringArray0.length);
	  }


}
