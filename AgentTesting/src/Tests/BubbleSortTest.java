package Tests;

import org.junit.Test;

import SUTClasses.BubbleSort;


public class BubbleSortTest {

	private BubbleSort createTestSubject() {
		return new BubbleSort();
	}


	@Test
	public void testSort() throws Exception {
		BubbleSort testSubject;
		String[] str = new String[] { "" };

		// default test
		testSubject = createTestSubject();
		testSubject.sort(str);
	}

}