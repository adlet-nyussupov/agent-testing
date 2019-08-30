package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.IntListVer3;

class IntListVer3TestMTA {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test1() {
		IntListVer3 intListVer = new IntListVer3();
		intListVer.size();
	}
	
	@Test
	void test2() {
		IntListVer3 intListVer = new IntListVer3();
		intListVer.toStringUsingStringBuffer();
	}
	
	@Test
	void test3() {
		IntListVer3 intListVer = new IntListVer3();
		intListVer.resize();
	}
	
	@Test
	void test4() {
		IntListVer3 intListVer = new IntListVer3();
		intListVer.ensureCapcity();
	}
	
	@Test
	void test5() {
		IntListVer3 intListVer = new IntListVer3();
		intListVer.toString();
	}
	
	@Test
	void test6() {
		IntListVer3 intListVer = new IntListVer3();
		intListVer.equals(new java.lang.Object());
	}
	
	@Test
	void test7() {
		IntListVer3 intListVer = new IntListVer3();
		intListVer.add(2020365476);
	}

}
