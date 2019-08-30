package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.Prime;

class PrimeTestMTA {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test1() {
		Prime prime = new Prime();
		prime.check(-2031945896);
	}
	
	@Test
	void test2() {
		Prime prime = new Prime();
		prime.check(934200227);
	}
	
	@Test
	void test3() {
		Prime prime = new Prime();
		prime.main(new java.lang.String[]{ "<Hv(h3n4!_tDL-b *ao<", "%*>eJ]VE[!JH%($@!fQE" });
	}
	
	@Test
	void test4() {
		Prime prime = new Prime();
		prime.check(1180961191);
	}

}
