package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.PrimeEx;

class PrimeExTestMTA {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test1() {
		 PrimeEx.getPrimes(219067862);
	}
	
	@Test
	void test2() {
		 PrimeEx.isPrime(903008887);
	}
	
	@Test
	void test3() {
		 PrimeEx.printTest(1184441554, 1184441554);
	}
	
	@Test
	void test4() {
		 PrimeEx.isPrime(-507454284);
	}
	
	@Test
	void test5() {
		 PrimeEx.main(new java.lang.String[0]);
	}
	
	@Test
	void test6() {
		 PrimeEx.numFactors(-1290943427);
	}

}
