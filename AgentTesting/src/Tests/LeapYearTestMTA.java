package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.LeapYear;

class LeapYearTestMTA {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test1() {
		  LeapYear leapYear = new LeapYear();
		  leapYear.check(-523170532);
	}
	
	@Test
	void test2() {
		  LeapYear leapYear = new LeapYear();
		  leapYear.check(415989518);
	}
	
	@Test
	void test3() {
		  LeapYear leapYear = new LeapYear();
		  leapYear.main(new java.lang.String[]{ "N[_>z87+#)H5/xOn1@>P" });
	}

}
