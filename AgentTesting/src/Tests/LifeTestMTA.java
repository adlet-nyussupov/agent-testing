package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.Life;

class LifeTestMTA {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test1() {

		Life.show(new boolean[0][]);

	}

	@Test
	void test2() {

		Life.gen();

	}

	@Test
	void test3() {

		Life.occupiedNext(-205228475, false);

	}

	@Test
	void test4() {

		Life.main(new java.lang.String[] { "73zx)P2(CCZTk3z*%9jy", " AdUYS=Pmt(5{u.6+S9=", "H|DvL=_5b(&L%671Vr$0" });

	}

	@Test
	void test5() {

		Life.show(new boolean[][] { new boolean[] { false, true, false } });

	}

	@Test
	void test6() {

		Life.nextGen(new boolean[][] { new boolean[] { true, false, true } });

	}

	@Test
	void test7() {
		
		
		Life.inbounds(new boolean[][]{ new boolean[]{ true, true } }, -1915110672, 819126917);

}

}
