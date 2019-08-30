package Tests;

import org.junit.Test;

import SUTClasses.PrimeEx;


public class PrimeExTest {

	private PrimeEx createTestSubject() {
		return new PrimeEx();
	}


	@Test
	public void testMain() throws Exception {
		String[] args = new String[] { "" };

		// default test
		PrimeEx.main(args);
	}


	@Test
	public void testGetPrimes() throws Exception {
		int max = 0;
		boolean[] result;

		// default test
		result = PrimeEx.getPrimes(max);
	}


	@Test
	public void testPrintTest() throws Exception {
		int num = 0;
		int expectedFactors = 0;

		// default test
		PrimeEx.printTest(num, expectedFactors);
	}


	@Test
	public void testIsPrime() throws Exception {
		int num = 0;
		boolean result;

		// default test
		result = PrimeEx.isPrime(num);
	}


	@Test
	public void testNumFactors() throws Exception {
		int num = 0;
		int result;

		// default test
		result = PrimeEx.numFactors(num);
	}
}