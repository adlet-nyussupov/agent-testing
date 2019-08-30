package Tests;

import org.junit.Test;

import SUTClasses.Triangle;


public class TriangleTest {

	private Triangle createTestSubject() {
		return new Triangle();
	}


	@Test
	public void testGetType() throws Exception {
		Triangle testSubject;
		int a = 0;
		int b = 0;
		int c = 0;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getType(a, b, c);
	}
}