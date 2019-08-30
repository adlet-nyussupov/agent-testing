package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.IntListVer2;

class IntListVer2TestMTA {

	@BeforeEach
	void setUp() throws Exception {
	}

	
	@Test
	void test1() {
		IntListVer2 intListVer = new IntListVer2();
		intListVer.resize();
	}
	
	@Test
	void test2() {
		IntListVer2 intListVer = new IntListVer2();
		intListVer.toStringUsingStringBuffer();
	}
	
	@Test
	void test3() {
		IntListVer2 intListVer = new IntListVer2();
		intListVer.toString();
	}
	
	@Test
	void test4() {
		IntListVer2 intListVer = new IntListVer2();
		intListVer.add(-1815316129);
	}
	
	
	@Test
	void test5() {
		IntListVer2 intListVer = new IntListVer2();
		intListVer.equals(new java.lang.Object());
	}

}
