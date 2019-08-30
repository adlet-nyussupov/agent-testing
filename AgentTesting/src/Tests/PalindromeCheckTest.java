package Tests;

import org.junit.Test;

import SUTClasses.PalindromeCheck;


public class PalindromeCheckTest {

	private PalindromeCheck createTestSubject() {
		return new PalindromeCheck();
	}


	@Test
	public void testIsPal() throws Exception {
		String s = "";
		boolean result;

		// default test
		result = PalindromeCheck.isPal(s);
	}


	@Test
	public void testMain() throws Exception {
		String[] args = new String[] { "" };

		// default test
		PalindromeCheck.main(args);
	}
}