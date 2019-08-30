package Tests;

import org.junit.Test;

import SUTClasses.Counter;


public class CounterTest {

	private Counter createTestSubject() {
		return new Counter();
	}


	@Test
	public void testCalc() throws Exception {
		Counter testSubject;
		int[] marks = new int[] { 0 };

		// default test
		testSubject = createTestSubject();
		testSubject.calc(marks);
	}
}