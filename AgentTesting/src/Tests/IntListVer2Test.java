package Tests;

import org.junit.Assert;
import org.junit.Test;

import SUTClasses.IntListVer2;


public class IntListVer2Test {

	private IntListVer2 createTestSubject() {
		return new IntListVer2();
	}


	@Test
	public void testAdd() throws Exception {
		IntListVer2 testSubject;
		int x = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.add(x);
	}


	@Test
	public void testResize() throws Exception {
		IntListVer2 testSubject;

		// default test
		testSubject = createTestSubject();
		testSubject.resize();
	}


	@Test
	public void testToString() throws Exception {
		IntListVer2 testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.toString();
	}


	@Test
	public void testToStringUsingStringBuffer() throws Exception {
		IntListVer2 testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.toStringUsingStringBuffer();
	}


	@Test
	public void testEquals() throws Exception {
		IntListVer2 testSubject;
		Object other = null;
		boolean result;

		// test 1
		testSubject = createTestSubject();
		other = null;
		result = testSubject.equals(other);
		Assert.assertEquals(false, result);
	}
}