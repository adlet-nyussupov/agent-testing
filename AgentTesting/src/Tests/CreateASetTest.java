package Tests;

import org.junit.Test;

import SUTClasses.CreateASet;


public class CreateASetTest {

	private CreateASet createTestSubject() {
		return new CreateASet();
	}


	@Test
	public void testMain() throws Exception {
		String[] args = new String[] { "" };

		// default test
		CreateASet.main(args);
	}


	@Test
	public void testMakeSet() throws Exception {
		Object[] data = new Object[] { null };
		Object[] result;

		// default test
		result = CreateASet.makeSet(data);
	}


	@Test
	public void testNoNulls() throws Exception {
		Object[] data = new Object[] { null };
		boolean result;

		// default test
		result = CreateASet.noNulls(data);
	}
}