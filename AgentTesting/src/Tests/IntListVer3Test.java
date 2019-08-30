package Tests;

import org.junit.Assert;
import org.junit.Test;

import SUTClasses.IntListVer3;


public class IntListVer3Test {

	private IntListVer3 createTestSubject() {
		return new IntListVer3();
	}


	@Test
	public void testAdd() throws Exception {
		IntListVer3 testSubject;
		int x = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.add(x);
	}


	@Test
	public void testGet() throws Exception {
		IntListVer3 testSubject;
		int pos = 0;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.get(pos);
	}


	@Test
	public void testInsert() throws Exception {
		IntListVer3 testSubject;
		int pos = 0;
		int x = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.insert(pos, x);
	}


	@Test
	public void testRemove() throws Exception {
		IntListVer3 testSubject;
		int pos = 0;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.remove(pos);
	}


	@Test
	public void testEnsureCapcity() throws Exception {
		IntListVer3 testSubject;

		// default test
		testSubject = createTestSubject();
		testSubject.ensureCapcity();
	}


	@Test
	public void testSize() throws Exception {
		IntListVer3 testSubject;
		int result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.size();
	}


	@Test
	public void testResize() throws Exception {
		IntListVer3 testSubject;

		// default test
		testSubject = createTestSubject();
		testSubject.resize();
	}


	@Test
	public void testToString() throws Exception {
		IntListVer3 testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.toString();
	}


	@Test
	public void testToStringUsingStringBuffer() throws Exception {
		IntListVer3 testSubject;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.toStringUsingStringBuffer();
	}


	@Test
	public void testEquals() throws Exception {
		IntListVer3 testSubject;
		Object other = null;
		boolean result;

		// test 1
		testSubject = createTestSubject();
		other = null;
		result = testSubject.equals(other);
		Assert.assertEquals(false, result);
	}
}