package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.Counter;

class CounterTestMTA {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test1() {
		 Counter counter = new Counter();
		 counter.calc(new int[]{ 139060355, 211711078, 1387181350   });
	}
	
	@Test
	void test2() {
		 Counter counter = new Counter();
		 counter.calc(new int[0]);
	}
	

}
