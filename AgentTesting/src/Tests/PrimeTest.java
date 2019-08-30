package Tests;

import org.junit.Test;

import SUTClasses.Prime;


public class PrimeTest {

	private Prime createTestSubject() {
		return new Prime();
	}


	@Test
	public void testMain() throws Exception {
		String[] args = new String[] { "" };

		// default test
		Prime.main(args);
	}


	@Test
	public void testCheck() throws Exception {
		Prime testSubject;
		int num = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.check(num);
	}
}