package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.LeapYear;

class LeapYearTestEvo {

	@BeforeEach
	void setUp() throws Exception {
	}

	 @Test()
	  public void test0()  throws Throwable  {
	      LeapYear leapYear0 = new LeapYear();
	      leapYear0.check(100);
	  }

	  @Test()
	  public void test1()  throws Throwable  {
	      LeapYear leapYear0 = new LeapYear();
	      leapYear0.check(424);
	  }

	  @Test()
	  public void test2()  throws Throwable  {
	      LeapYear leapYear0 = new LeapYear();
	      leapYear0.check(1578);
	  }

	  @Test()
	  public void test3()  throws Throwable  {
	      LeapYear leapYear0 = new LeapYear();
	      leapYear0.check((-200));
	  }

	  @Test()
	  public void test4()  throws Throwable  {
	      LeapYear leapYear0 = new LeapYear();
	      leapYear0.check(0);
	  }

	  @Test()
	  public void test5()  throws Throwable  {
	      LeapYear leapYear0 = new LeapYear();
	      leapYear0.check((-624));
	  }

	  @Test()
	  public void test6()  throws Throwable  {
	      LeapYear leapYear0 = new LeapYear();
	      leapYear0.check((-1));
	  }


	  @Test()
	  public void test8()  throws Throwable  {
	      String[] stringArray0 = new String[7];
	      LeapYear.main(stringArray0);
	      assertEquals(7, stringArray0.length);
	  }
	


}
