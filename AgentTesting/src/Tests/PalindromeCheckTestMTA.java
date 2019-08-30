package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.PalindromeCheck;

class PalindromeCheckTestMTA {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test1() {
		 PalindromeCheck.main(new java.lang.String[0]);
	}
	
	@Test
	void test2() {
		 PalindromeCheck.isPal("cJ{i.u#S+n{CV#0?-&70");
	}

}
