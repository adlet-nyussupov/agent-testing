package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.BinaryConverter;

class BinaryConverterTestMTA {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test1() {
		BinaryConverter.all0sAnd1s("s(C9E}[h2eS(T8fE;M9s");
	}
	
	
	@Test
	void test2() {
		BinaryConverter.main(new java.lang.String[]{ "t8Dn+Hp;]$;3<a]z2Ug-"  });
	}
	
	@Test
	void test3() {
		BinaryConverter.toBinary(-1062852885);
	}
	
	@Test
	void test4() {
		BinaryConverter.toBinary(811790454);
	}

}
