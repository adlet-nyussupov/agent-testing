package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.Triangle;

class TriangleTestMTA {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test1() {
		 Triangle triangle = new Triangle();
		 triangle.getType(-1743621871, 1770573038, 1755403130);
	}
	
	@Test
	void test2() {
		 Triangle triangle = new Triangle();
		 triangle.getType(1527873259, 1895746491, 1710760105);
	}
	
	@Test
	void test3() {
		 Triangle triangle = new Triangle();
		 triangle.getType(392586146, 392586146, 392586146);
	}
	

}
