package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.BubbleSort;



class BubbleSortTestMTA {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test1()  {
		BubbleSort bubbleSort = new BubbleSort();
		bubbleSort.sort(new java.lang.String[]{ "]#>Y2e3!9XuBax3&^$q-", "8Rz}AJ?fVMw7$`657BD8", "ZSds|g1U 60mkRZ|+aE{" });
	}
	

	

}
