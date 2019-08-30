package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.Prime;

class PrimeTestEvo {

	@BeforeEach
	void setUp() throws Exception {
	}

	  @Test()
	  public void test0()  throws Throwable  {
	      Prime prime0 = new Prime();
	      int int0 = 967;
	      prime0.check(967);
	      prime0.check(0);
	      String[] stringArray0 = null;
	      Prime.main((String[]) null);
	      prime0.check(967);
	      prime0.check(2687);
	      Prime.main((String[]) null);
	      prime0.check(2687);
	      prime0.check((-363));
	      prime0.check(2687);
	      Prime.main((String[]) null);
	      prime0.check(2687);
	      prime0.check(2687);
	      prime0.check(2687);
	      prime0.check(967);
	      prime0.check(0);
	      // Undeclared exception!
	      prime0.check(2687);
	  }

	  @Test()
	  public void test1()  throws Throwable  {
	      Prime prime0 = new Prime();
	      prime0.check(449);
	  }

	  @Test()
	  public void test2()  throws Throwable  {
	      Prime prime0 = new Prime();
	  }

	  @Test()
	  public void test3()  throws Throwable  {
	      Prime prime0 = new Prime();
	      prime0.check(3815);
	      prime0.check(0);
	  }

	  @Test()
	  public void test4()  throws Throwable  {
	      Prime prime0 = new Prime();
	      prime0.check(0);
	  }

	  @Test()
	  public void test5()  throws Throwable  {
	      String[] stringArray0 = new String[0];
	      Prime.main(stringArray0);
	      assertEquals(0, stringArray0.length);
	  }

	  @Test()
	  public void test6()  throws Throwable  {
	      Prime prime0 = new Prime();
	      Prime.main((String[]) null);
	      prime0.check((-38));
	      prime0.check(3751);
	  }
	


}
