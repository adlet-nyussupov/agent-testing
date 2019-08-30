package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.CreateASet;

class CreateASetTestMTA {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test1() {
		CreateASet createASet = new CreateASet();
		createASet.makeSet(new java.lang.Object[]{ new java.lang.Object(), new java.lang.Object() });
	}
	
	@Test
	void test2() {
		CreateASet createASet = new CreateASet();
		createASet.main( new java.lang.String[]{ "bOU^6`Z#(S+nx}wdX6R(", "@]im&OMF6H,8|^K&hjkC" });
	}
	
	@Test
	void test3() {
		CreateASet createASet = new CreateASet();
		createASet.noNulls( new java.lang.Object[]{ new java.lang.Object() });
	}
	
	
	
	@Test
	void test5() {
		CreateASet createASet = new CreateASet();
		createASet.makeSet(new java.lang.Object[]{ new java.lang.Object[0]});
	}
	

}
