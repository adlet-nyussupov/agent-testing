package Tests;

import org.junit.Test;

import SUTClasses.LeapYear;


public class LeapYearTest {

	private LeapYear createTestSubject() {
		return new LeapYear();
	}

	
	@Test
	public void testCheck() throws Exception {
		LeapYear testSubject;
		int year = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.check(year);
	}


	@Test
	public void testMain() throws Exception {
		String[] args = new String[] { "" };

		// default test
		LeapYear.main(args);
	}
}