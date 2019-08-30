package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.MineSweeper;

class MineSweeperTestMTA {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test1() {
		MineSweeper mineSweeper = new MineSweeper();
		mineSweeper.cellPicked(-1310112939, 814026022);
	}
	
	@Test
	void test2() {
		MineSweeper mineSweeper = new MineSweeper();
		mineSweeper.cellPicked(-262616342, -878401490);
	}

}
