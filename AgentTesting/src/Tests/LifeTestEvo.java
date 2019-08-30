package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.Life;

class LifeTestEvo {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test()
	  public void test00()  throws Throwable  {
	      boolean[][] booleanArray0 = Life.gen();
	      assertNotNull(booleanArray0);
	      assertEquals(10, booleanArray0.length);
	      
	      boolean boolean0 = Life.occupiedNext(9, false);
	      assertFalse(boolean0);
	      
	      boolean[][] booleanArray1 = Life.gen();
	      assertNotNull(booleanArray1);
	      assertFalse(booleanArray1.equals((Object)booleanArray0));
	      assertNotSame(booleanArray1, booleanArray0);
	      assertEquals(10, booleanArray1.length);
	      

	      Life.show(booleanArray0);
	      assertFalse(booleanArray0.equals((Object)booleanArray1));
	      assertNotSame(booleanArray0, booleanArray1);
	      assertEquals(10, booleanArray0.length);
	      
	      boolean[][] booleanArray2 = Life.gen();
	      assertNotNull(booleanArray2);
	      assertFalse(booleanArray2.equals((Object)booleanArray1));
	      assertFalse(booleanArray2.equals((Object)booleanArray0));
	      assertNotSame(booleanArray2, booleanArray1);
	      assertNotSame(booleanArray2, booleanArray0);
	      assertEquals(10, booleanArray2.length);
	      
	      Life.show(booleanArray1);
	      assertFalse(booleanArray1.equals((Object)booleanArray2));
	      assertFalse(booleanArray1.equals((Object)booleanArray0));
	      assertNotSame(booleanArray1, booleanArray2);
	      assertNotSame(booleanArray1, booleanArray0);
	      assertEquals(10, booleanArray1.length);
	      
	      int int0 = Life.numNeighbors(booleanArray0, 0, 9);
	      assertFalse(booleanArray0.equals((Object)booleanArray1));
	      assertFalse(booleanArray0.equals((Object)booleanArray2));
	      assertNotSame(booleanArray0, booleanArray1);
	      assertNotSame(booleanArray0, booleanArray2);
	      assertEquals(3, int0);
	      assertEquals(10, booleanArray0.length);
	      
	      Life.show(booleanArray2);
	      assertFalse(booleanArray2.equals((Object)booleanArray1));
	      assertFalse(booleanArray2.equals((Object)booleanArray0));
	      assertNotSame(booleanArray2, booleanArray1);
	      assertNotSame(booleanArray2, booleanArray0);
	      assertEquals(10, booleanArray2.length);
	      
	      int int1 = Life.numNeighbors(booleanArray0, 0, 3);
	      assertFalse(booleanArray0.equals((Object)booleanArray1));
	      assertFalse(booleanArray0.equals((Object)booleanArray2));
	      assertFalse(int1 == int0);
	      assertNotSame(booleanArray0, booleanArray1);
	      assertNotSame(booleanArray0, booleanArray2);
	      assertEquals(0, int1);
	      assertEquals(10, booleanArray0.length);
	      
	      boolean boolean1 = Life.occupiedNext(0, false);
	      assertTrue(boolean1 == boolean0);
	      assertFalse(boolean1);
	      
	      boolean[][] booleanArray3 = Life.nextGen(booleanArray0);
	      assertNotNull(booleanArray3);
	      assertFalse(booleanArray0.equals((Object)booleanArray1));
	      assertFalse(booleanArray0.equals((Object)booleanArray2));
	      assertFalse(booleanArray3.equals((Object)booleanArray2));
	      assertFalse(booleanArray3.equals((Object)booleanArray0));
	      assertFalse(booleanArray3.equals((Object)booleanArray1));
	      assertNotSame(booleanArray0, booleanArray3);
	      assertNotSame(booleanArray0, booleanArray1);
	      assertNotSame(booleanArray0, booleanArray2);
	      assertNotSame(booleanArray3, booleanArray2);
	      assertNotSame(booleanArray3, booleanArray0);
	      assertNotSame(booleanArray3, booleanArray1);
	      assertEquals(10, booleanArray0.length);
	      assertEquals(10, booleanArray3.length);
	      
	      boolean[][] booleanArray4 = Life.gen();
	      assertNotNull(booleanArray4);
	      assertFalse(booleanArray4.equals((Object)booleanArray0));
	      assertFalse(booleanArray4.equals((Object)booleanArray1));
	      assertFalse(booleanArray4.equals((Object)booleanArray2));
	      assertFalse(booleanArray4.equals((Object)booleanArray3));
	      assertNotSame(booleanArray4, booleanArray0);
	      assertNotSame(booleanArray4, booleanArray1);
	      assertNotSame(booleanArray4, booleanArray2);
	      assertNotSame(booleanArray4, booleanArray3);
	      assertEquals(10, booleanArray4.length);
	      

	      boolean boolean2 = Life.occupiedNext(3, false);
	      assertFalse(boolean2 == boolean1);
	      assertFalse(boolean2 == boolean0);
	      assertTrue(boolean2);
	      
	      Life.show(booleanArray3);
	      assertFalse(booleanArray0.equals((Object)booleanArray3));
	      assertFalse(booleanArray0.equals((Object)booleanArray1));
	      assertFalse(booleanArray0.equals((Object)booleanArray2));
	      assertFalse(booleanArray0.equals((Object)booleanArray4));
	      assertFalse(booleanArray3.equals((Object)booleanArray2));
	      assertFalse(booleanArray3.equals((Object)booleanArray4));
	      assertFalse(booleanArray3.equals((Object)booleanArray0));
	      assertFalse(booleanArray3.equals((Object)booleanArray1));
	      assertNotSame(booleanArray0, booleanArray3);
	      assertNotSame(booleanArray0, booleanArray1);
	      assertNotSame(booleanArray0, booleanArray2);
	      assertNotSame(booleanArray0, booleanArray4);
	      assertNotSame(booleanArray3, booleanArray2);
	      assertNotSame(booleanArray3, booleanArray4);
	      assertNotSame(booleanArray3, booleanArray0);
	      assertNotSame(booleanArray3, booleanArray1);
	      assertEquals(10, booleanArray0.length);
	      assertEquals(10, booleanArray3.length);
	      
	      Life.show(booleanArray0);
	      assertFalse(booleanArray0.equals((Object)booleanArray3));
	      assertFalse(booleanArray0.equals((Object)booleanArray1));
	      assertFalse(booleanArray0.equals((Object)booleanArray2));
	      assertFalse(booleanArray0.equals((Object)booleanArray4));
	      assertNotSame(booleanArray0, booleanArray3);
	      assertNotSame(booleanArray0, booleanArray1);
	      assertNotSame(booleanArray0, booleanArray2);
	      assertNotSame(booleanArray0, booleanArray4);
	      assertEquals(10, booleanArray0.length);
	      
	      boolean[][] booleanArray5 = Life.nextGen(booleanArray1);
	      assertNotNull(booleanArray5);
	      assertFalse(booleanArray5.equals((Object)booleanArray3));
	      assertFalse(booleanArray5.equals((Object)booleanArray2));
	      assertFalse(booleanArray5.equals((Object)booleanArray1));
	      assertFalse(booleanArray5.equals((Object)booleanArray0));
	      assertFalse(booleanArray5.equals((Object)booleanArray4));
	      assertFalse(booleanArray1.equals((Object)booleanArray2));
	      assertFalse(booleanArray1.equals((Object)booleanArray0));
	      assertFalse(booleanArray1.equals((Object)booleanArray4));
	      assertFalse(booleanArray1.equals((Object)booleanArray3));
	      assertNotSame(booleanArray5, booleanArray3);
	      assertNotSame(booleanArray5, booleanArray2);
	      assertNotSame(booleanArray5, booleanArray1);
	      assertNotSame(booleanArray5, booleanArray0);
	      assertNotSame(booleanArray5, booleanArray4);
	      assertNotSame(booleanArray1, booleanArray2);
	      assertNotSame(booleanArray1, booleanArray0);
	      assertNotSame(booleanArray1, booleanArray5);
	      assertNotSame(booleanArray1, booleanArray4);
	      assertNotSame(booleanArray1, booleanArray3);
	      assertEquals(10, booleanArray5.length);
	      assertEquals(10, booleanArray1.length);
	      
	      boolean[][] booleanArray6 = Life.nextGen(booleanArray0);
	      assertNotNull(booleanArray6);
	      assertFalse(booleanArray0.equals((Object)booleanArray3));
	      assertFalse(booleanArray0.equals((Object)booleanArray1));
	      assertFalse(booleanArray0.equals((Object)booleanArray2));
	      assertFalse(booleanArray0.equals((Object)booleanArray4));
	      assertFalse(booleanArray0.equals((Object)booleanArray5));
	      assertFalse(booleanArray6.equals((Object)booleanArray0));
	      assertFalse(booleanArray6.equals((Object)booleanArray5));
	      assertFalse(booleanArray6.equals((Object)booleanArray2));
	      assertFalse(booleanArray6.equals((Object)booleanArray1));
	      assertFalse(booleanArray6.equals((Object)booleanArray3));
	      assertFalse(booleanArray6.equals((Object)booleanArray4));
	      assertNotSame(booleanArray0, booleanArray3);
	      assertNotSame(booleanArray0, booleanArray1);
	      assertNotSame(booleanArray0, booleanArray2);
	      assertNotSame(booleanArray0, booleanArray6);
	      assertNotSame(booleanArray0, booleanArray4);
	      assertNotSame(booleanArray0, booleanArray5);
	      assertNotSame(booleanArray6, booleanArray0);
	      assertNotSame(booleanArray6, booleanArray5);
	      assertNotSame(booleanArray6, booleanArray2);
	      assertNotSame(booleanArray6, booleanArray1);
	      assertNotSame(booleanArray6, booleanArray3);
	      assertNotSame(booleanArray6, booleanArray4);
	      assertEquals(10, booleanArray0.length);
	      assertEquals(10, booleanArray6.length);
	      
	      boolean boolean3 = Life.occupiedNext(1460, false);
	      assertTrue(boolean3 == boolean0);
	      assertTrue(boolean3 == boolean1);
	      assertFalse(boolean3 == boolean2);
	      assertFalse(boolean3);
	      
	      boolean[][] booleanArray7 = Life.gen();
	      assertNotNull(booleanArray7);
	      assertFalse(booleanArray7.equals((Object)booleanArray3));
	      assertFalse(booleanArray7.equals((Object)booleanArray4));
	      assertFalse(booleanArray7.equals((Object)booleanArray1));
	      assertFalse(booleanArray7.equals((Object)booleanArray2));
	      assertFalse(booleanArray7.equals((Object)booleanArray6));
	      assertFalse(booleanArray7.equals((Object)booleanArray5));
	      assertFalse(booleanArray7.equals((Object)booleanArray0));
	      assertNotSame(booleanArray7, booleanArray3);
	      assertNotSame(booleanArray7, booleanArray4);
	      assertNotSame(booleanArray7, booleanArray1);
	      assertNotSame(booleanArray7, booleanArray2);
	      assertNotSame(booleanArray7, booleanArray6);
	      assertNotSame(booleanArray7, booleanArray5);
	      assertNotSame(booleanArray7, booleanArray0);
	      assertEquals(10, booleanArray7.length);
	      
	      boolean[][] booleanArray8 = Life.nextGen(booleanArray4);
	      assertNotNull(booleanArray8);
	      assertFalse(booleanArray8.equals((Object)booleanArray2));
	      assertFalse(booleanArray8.equals((Object)booleanArray3));
	      assertFalse(booleanArray8.equals((Object)booleanArray1));
	      assertFalse(booleanArray8.equals((Object)booleanArray0));
	      assertFalse(booleanArray8.equals((Object)booleanArray4));
	      assertFalse(booleanArray8.equals((Object)booleanArray5));
	      assertFalse(booleanArray8.equals((Object)booleanArray6));
	      assertFalse(booleanArray8.equals((Object)booleanArray7));
	      assertFalse(booleanArray4.equals((Object)booleanArray0));
	      assertFalse(booleanArray4.equals((Object)booleanArray1));
	      assertFalse(booleanArray4.equals((Object)booleanArray2));
	      assertFalse(booleanArray4.equals((Object)booleanArray6));
	      assertFalse(booleanArray4.equals((Object)booleanArray3));
	      assertFalse(booleanArray4.equals((Object)booleanArray7));
	      assertFalse(booleanArray4.equals((Object)booleanArray5));
	      assertNotSame(booleanArray8, booleanArray2);
	      assertNotSame(booleanArray8, booleanArray3);
	      assertNotSame(booleanArray8, booleanArray1);
	      assertNotSame(booleanArray8, booleanArray0);
	      assertNotSame(booleanArray8, booleanArray4);
	      assertNotSame(booleanArray8, booleanArray5);
	      assertNotSame(booleanArray8, booleanArray6);
	      assertNotSame(booleanArray8, booleanArray7);
	      assertNotSame(booleanArray4, booleanArray0);
	      assertNotSame(booleanArray4, booleanArray1);
	      assertNotSame(booleanArray4, booleanArray2);
	      assertNotSame(booleanArray4, booleanArray6);
	      assertNotSame(booleanArray4, booleanArray8);
	      assertNotSame(booleanArray4, booleanArray3);
	      assertNotSame(booleanArray4, booleanArray7);
	      assertNotSame(booleanArray4, booleanArray5);
	      assertEquals(10, booleanArray8.length);
	      assertEquals(10, booleanArray4.length);
	      
	      boolean[][] booleanArray9 = Life.nextGen(booleanArray3);
	      assertNotNull(booleanArray9);
	      assertFalse(booleanArray0.equals((Object)booleanArray3));
	      assertFalse(booleanArray0.equals((Object)booleanArray1));
	      assertFalse(booleanArray0.equals((Object)booleanArray2));
	      assertFalse(booleanArray0.equals((Object)booleanArray6));
	      assertFalse(booleanArray0.equals((Object)booleanArray8));
	      assertFalse(booleanArray0.equals((Object)booleanArray4));
	      assertFalse(booleanArray0.equals((Object)booleanArray7));
	      assertFalse(booleanArray0.equals((Object)booleanArray5));
	      assertFalse(booleanArray9.equals((Object)booleanArray4));
	      assertFalse(booleanArray9.equals((Object)booleanArray7));
	      assertFalse(booleanArray9.equals((Object)booleanArray3));
	      assertFalse(booleanArray9.equals((Object)booleanArray1));
	      assertFalse(booleanArray9.equals((Object)booleanArray8));
	      assertFalse(booleanArray9.equals((Object)booleanArray2));
	      assertFalse(booleanArray9.equals((Object)booleanArray6));
	      assertFalse(booleanArray9.equals((Object)booleanArray5));
	      assertFalse(booleanArray9.equals((Object)booleanArray0));
	      assertFalse(booleanArray3.equals((Object)booleanArray2));
	      assertFalse(booleanArray3.equals((Object)booleanArray6));
	      assertFalse(booleanArray3.equals((Object)booleanArray4));
	      assertFalse(booleanArray3.equals((Object)booleanArray0));
	      assertFalse(booleanArray3.equals((Object)booleanArray7));
	      assertFalse(booleanArray3.equals((Object)booleanArray5));
	      assertFalse(booleanArray3.equals((Object)booleanArray1));
	      assertFalse(booleanArray3.equals((Object)booleanArray8));
	      assertNotSame(booleanArray0, booleanArray3);
	      assertNotSame(booleanArray0, booleanArray1);
	      assertNotSame(booleanArray0, booleanArray2);
	      assertNotSame(booleanArray0, booleanArray6);
	      assertNotSame(booleanArray0, booleanArray9);
	      assertNotSame(booleanArray0, booleanArray8);
	      assertNotSame(booleanArray0, booleanArray4);
	      assertNotSame(booleanArray0, booleanArray7);
	      assertNotSame(booleanArray0, booleanArray5);
	      assertNotSame(booleanArray9, booleanArray4);
	      assertNotSame(booleanArray9, booleanArray7);
	      assertNotSame(booleanArray9, booleanArray3);
	      assertNotSame(booleanArray9, booleanArray1);
	      assertNotSame(booleanArray9, booleanArray8);
	      assertNotSame(booleanArray9, booleanArray2);
	      assertNotSame(booleanArray9, booleanArray6);
	      assertNotSame(booleanArray9, booleanArray5);
	      assertNotSame(booleanArray9, booleanArray0);
	      assertNotSame(booleanArray3, booleanArray2);
	      assertNotSame(booleanArray3, booleanArray9);
	      assertNotSame(booleanArray3, booleanArray6);
	      assertNotSame(booleanArray3, booleanArray4);
	      assertNotSame(booleanArray3, booleanArray0);
	      assertNotSame(booleanArray3, booleanArray7);
	      assertNotSame(booleanArray3, booleanArray5);
	      assertNotSame(booleanArray3, booleanArray1);
	      assertNotSame(booleanArray3, booleanArray8);
	      assertEquals(10, booleanArray0.length);
	      assertEquals(10, booleanArray9.length);
	      assertEquals(10, booleanArray3.length);
	      
	      boolean[][] booleanArray10 = Life.gen();
	      assertNotNull(booleanArray10);
	      assertFalse(booleanArray10.equals((Object)booleanArray2));
	      assertFalse(booleanArray10.equals((Object)booleanArray6));
	      assertFalse(booleanArray10.equals((Object)booleanArray9));
	      assertFalse(booleanArray10.equals((Object)booleanArray0));
	      assertFalse(booleanArray10.equals((Object)booleanArray1));
	      assertFalse(booleanArray10.equals((Object)booleanArray7));
	      assertFalse(booleanArray10.equals((Object)booleanArray5));
	      assertFalse(booleanArray10.equals((Object)booleanArray4));
	      assertFalse(booleanArray10.equals((Object)booleanArray8));
	      assertFalse(booleanArray10.equals((Object)booleanArray3));
	      assertNotSame(booleanArray10, booleanArray2);
	      assertNotSame(booleanArray10, booleanArray6);
	      assertNotSame(booleanArray10, booleanArray9);
	      assertNotSame(booleanArray10, booleanArray0);
	      assertNotSame(booleanArray10, booleanArray1);
	      assertNotSame(booleanArray10, booleanArray7);
	      assertNotSame(booleanArray10, booleanArray5);
	      assertNotSame(booleanArray10, booleanArray4);
	      assertNotSame(booleanArray10, booleanArray8);
	      assertNotSame(booleanArray10, booleanArray3);
	      assertEquals(10, booleanArray10.length);
	      

	      Life.show(booleanArray9);
	      assertFalse(booleanArray0.equals((Object)booleanArray3));
	      assertFalse(booleanArray0.equals((Object)booleanArray1));
	      assertFalse(booleanArray0.equals((Object)booleanArray2));
	      assertFalse(booleanArray0.equals((Object)booleanArray6));
	      assertFalse(booleanArray0.equals((Object)booleanArray9));
	      assertFalse(booleanArray0.equals((Object)booleanArray8));
	      assertFalse(booleanArray0.equals((Object)booleanArray10));
	      assertFalse(booleanArray0.equals((Object)booleanArray4));
	      assertFalse(booleanArray0.equals((Object)booleanArray7));
	      assertFalse(booleanArray0.equals((Object)booleanArray5));
	      assertFalse(booleanArray9.equals((Object)booleanArray4));
	      assertFalse(booleanArray9.equals((Object)booleanArray7));
	      assertFalse(booleanArray9.equals((Object)booleanArray3));
	      assertFalse(booleanArray9.equals((Object)booleanArray1));
	      assertFalse(booleanArray9.equals((Object)booleanArray8));
	      assertFalse(booleanArray9.equals((Object)booleanArray2));
	      assertFalse(booleanArray9.equals((Object)booleanArray6));
	      assertFalse(booleanArray9.equals((Object)booleanArray5));
	      assertFalse(booleanArray9.equals((Object)booleanArray0));
	      assertFalse(booleanArray9.equals((Object)booleanArray10));
	      assertFalse(booleanArray3.equals((Object)booleanArray2));
	      assertFalse(booleanArray3.equals((Object)booleanArray9));
	      assertFalse(booleanArray3.equals((Object)booleanArray6));
	      assertFalse(booleanArray3.equals((Object)booleanArray10));
	      assertFalse(booleanArray3.equals((Object)booleanArray4));
	      assertFalse(booleanArray3.equals((Object)booleanArray0));
	      assertFalse(booleanArray3.equals((Object)booleanArray7));
	      assertFalse(booleanArray3.equals((Object)booleanArray5));
	      assertFalse(booleanArray3.equals((Object)booleanArray1));
	      assertFalse(booleanArray3.equals((Object)booleanArray8));
	      assertNotSame(booleanArray0, booleanArray3);
	      assertNotSame(booleanArray0, booleanArray1);
	      assertNotSame(booleanArray0, booleanArray2);
	      assertNotSame(booleanArray0, booleanArray6);
	      assertNotSame(booleanArray0, booleanArray9);
	      assertNotSame(booleanArray0, booleanArray8);
	      assertNotSame(booleanArray0, booleanArray10);
	      assertNotSame(booleanArray0, booleanArray4);
	      assertNotSame(booleanArray0, booleanArray7);
	      assertNotSame(booleanArray0, booleanArray5);
	      assertNotSame(booleanArray9, booleanArray4);
	      assertNotSame(booleanArray9, booleanArray7);
	      assertNotSame(booleanArray9, booleanArray3);
	      assertNotSame(booleanArray9, booleanArray1);
	      assertNotSame(booleanArray9, booleanArray8);
	      assertNotSame(booleanArray9, booleanArray2);
	      assertNotSame(booleanArray9, booleanArray6);
	      assertNotSame(booleanArray9, booleanArray5);
	      assertNotSame(booleanArray9, booleanArray0);
	      assertNotSame(booleanArray9, booleanArray10);
	      assertNotSame(booleanArray3, booleanArray2);
	      assertNotSame(booleanArray3, booleanArray9);
	      assertNotSame(booleanArray3, booleanArray6);
	      assertNotSame(booleanArray3, booleanArray10);
	      assertNotSame(booleanArray3, booleanArray4);
	      assertNotSame(booleanArray3, booleanArray0);
	      assertNotSame(booleanArray3, booleanArray7);
	      assertNotSame(booleanArray3, booleanArray5);
	      assertNotSame(booleanArray3, booleanArray1);
	      assertNotSame(booleanArray3, booleanArray8);
	      assertEquals(10, booleanArray0.length);
	      assertEquals(10, booleanArray9.length);
	      assertEquals(10, booleanArray3.length);
	  }

	  @Test()
	  public void test01()  throws Throwable  {
	      boolean[][] booleanArray0 = Life.gen();
	      assertNotNull(booleanArray0);
	      assertEquals(10, booleanArray0.length);
	      
	      boolean boolean0 = Life.inbounds(booleanArray0, 3, (-2976));
	      assertFalse(boolean0);
	      assertEquals(10, booleanArray0.length);
	      
	      boolean boolean1 = Life.occupiedNext((-2976), false);
	      assertTrue(boolean1 == boolean0);
	      assertFalse(boolean1);
	      
	      Life life0 = new Life();
	      assertNotNull(life0);
	      
	      Life.show(booleanArray0);
	      assertEquals(10, booleanArray0.length);
	      
	      life0.run();
	      String[] stringArray0 = new String[7];
	      stringArray0[0] = "04+R/@";
	      stringArray0[1] = "5'l;_bT8#v*";
	      stringArray0[2] = "i?d&76qCDzab.D";
	      stringArray0[3] = "";
	      stringArray0[4] = "";
	      stringArray0[5] = "";
	      stringArray0[6] = "";
	      Life.main(stringArray0);
	      assertEquals(7, stringArray0.length);
	  }

	  @Test()
	  public void test02()  throws Throwable  {
	      // Undeclared exception!
	      try { 
	        Life.numNeighbors((boolean[][]) null, 1773, 0);
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	      }
	  }

	  @Test()
	  public void test03()  throws Throwable  {
	      boolean boolean0 = Life.occupiedNext(3, true);
	      assertTrue(boolean0);
	      
	      String[] stringArray0 = new String[1];
	      stringArray0[0] = "";
	      Life.main(stringArray0);
	      assertEquals(1, stringArray0.length);
	      
	      boolean[][] booleanArray0 = Life.gen();
	      assertNotNull(booleanArray0);
	      assertEquals(10, booleanArray0.length);
	      
	      boolean[][] booleanArray1 = Life.gen();
	      assertNotNull(booleanArray1);
	      assertFalse(booleanArray1.equals((Object)booleanArray0));
	      assertNotSame(booleanArray1, booleanArray0);
	      assertEquals(10, booleanArray1.length);
	      
	      Life.main(stringArray0);
	      assertEquals(1, stringArray0.length);
	      
	      Life life0 = new Life();
	      assertNotNull(life0);
	      
	      Life.show(booleanArray1);
	      assertFalse(booleanArray1.equals((Object)booleanArray0));
	      assertNotSame(booleanArray1, booleanArray0);
	      assertEquals(10, booleanArray1.length);
	      
	      life0.run();
	      life0.run();
	      life0.run();
	  }

	  @Test()
	  public void test04()  throws Throwable  {
	      boolean[][] booleanArray0 = new boolean[0][0];
	      boolean boolean0 = Life.inbounds(booleanArray0, 1655, (-1));
	      assertFalse(boolean0);
	      assertEquals(0, booleanArray0.length);
	      
	      boolean boolean1 = Life.occupiedNext(2, true);
	      assertFalse(boolean1 == boolean0);
	      assertTrue(boolean1);
	      
	      Life life0 = new Life();
	      assertNotNull(life0);
	      
	      boolean[][] booleanArray1 = Life.gen();
	      assertNotNull(booleanArray1);
	      assertFalse(booleanArray1.equals((Object)booleanArray0));
	      assertNotSame(booleanArray1, booleanArray0);
	      assertEquals(10, booleanArray1.length);
	      
	      life0.run();
	      boolean boolean2 = Life.occupiedNext((-1), true);
	      assertFalse(boolean2 == boolean1);
	      assertTrue(boolean2 == boolean0);
	      assertFalse(boolean2);
	  }

	  @Test()
	  public void test05()  throws Throwable  {
	      String[] stringArray0 = new String[0];
	      Life.main(stringArray0);
	      assertEquals(0, stringArray0.length);
	      
	      boolean[][] booleanArrayArray0 = null;
	      // Undeclared exception!
	      try { 
	        Life.show((boolean[][]) null);
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	   
	      }
	  }

	  @Test()
	  public void test06()  throws Throwable  {
	      boolean[][] booleanArray0 = Life.gen();
	      assertNotNull(booleanArray0);
	      assertEquals(10, booleanArray0.length);
	      
	      boolean boolean0 = Life.inbounds(booleanArray0, 1914, 3);
	      assertFalse(boolean0);
	      assertEquals(10, booleanArray0.length);
	      
	      boolean boolean1 = Life.inbounds(booleanArray0, 3, 2717);
	      assertTrue(boolean1 == boolean0);
	      assertFalse(boolean1);
	      assertEquals(10, booleanArray0.length);
	      
	      int int0 = Life.numNeighbors(booleanArray0, 3, 3);
	      assertEquals(0, int0);
	      assertEquals(10, booleanArray0.length);
	      
	      boolean[][] booleanArray1 = Life.gen();
	      assertNotNull(booleanArray1);
	      assertFalse(booleanArray1.equals((Object)booleanArray0));
	      assertNotSame(booleanArray1, booleanArray0);
	      assertEquals(10, booleanArray1.length);
	      
	      // Undeclared exception!
	      try { 
	        Life.numNeighbors(booleanArray0, 1, 2717);
	        fail("Expecting exception: ArrayIndexOutOfBoundsException");
	      
	      } catch(ArrayIndexOutOfBoundsException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	      }
	  }

	  @Test()
	  public void test07()  throws Throwable  {
	      boolean[][] booleanArray0 = Life.gen();
	      assertNotNull(booleanArray0);
	      assertEquals(10, booleanArray0.length);
	      
	      boolean[][] booleanArray1 = Life.gen();
	      assertNotNull(booleanArray1);
	      assertFalse(booleanArray1.equals((Object)booleanArray0));
	      assertNotSame(booleanArray1, booleanArray0);
	      assertEquals(10, booleanArray1.length);
	      
	      boolean[][] booleanArray2 = Life.nextGen(booleanArray0);
	      assertNotNull(booleanArray2);
	      assertFalse(booleanArray0.equals((Object)booleanArray1));
	      assertFalse(booleanArray2.equals((Object)booleanArray1));
	      assertFalse(booleanArray2.equals((Object)booleanArray0));
	      assertNotSame(booleanArray0, booleanArray2);
	      assertNotSame(booleanArray0, booleanArray1);
	      assertNotSame(booleanArray2, booleanArray1);
	      assertNotSame(booleanArray2, booleanArray0);
	      assertEquals(10, booleanArray0.length);
	      assertEquals(10, booleanArray2.length);
	      
	      boolean boolean0 = Life.occupiedNext((-2037), false);
	      assertFalse(boolean0);
	      
	      boolean boolean1 = Life.occupiedNext(2718, true);
	      assertTrue(boolean1 == boolean0);
	      assertFalse(boolean1);
	      
	      Life life0 = new Life();
	      assertNotNull(life0);
	      
	      Life.show(booleanArray0);
	      assertFalse(booleanArray0.equals((Object)booleanArray2));
	      assertFalse(booleanArray0.equals((Object)booleanArray1));
	      assertNotSame(booleanArray0, booleanArray2);
	      assertNotSame(booleanArray0, booleanArray1);
	      assertEquals(10, booleanArray0.length);
	      
	      boolean[][] booleanArray3 = Life.gen();
	      assertNotNull(booleanArray3);
	      assertFalse(booleanArray3.equals((Object)booleanArray1));
	      assertFalse(booleanArray3.equals((Object)booleanArray2));
	      assertFalse(booleanArray3.equals((Object)booleanArray0));
	      assertNotSame(booleanArray3, booleanArray1);
	      assertNotSame(booleanArray3, booleanArray2);
	      assertNotSame(booleanArray3, booleanArray0);
	      assertEquals(10, booleanArray3.length);
	      
	      Life.show(booleanArray2);
	      assertFalse(booleanArray0.equals((Object)booleanArray3));
	      assertFalse(booleanArray0.equals((Object)booleanArray2));
	      assertFalse(booleanArray0.equals((Object)booleanArray1));
	      assertFalse(booleanArray2.equals((Object)booleanArray3));
	      assertFalse(booleanArray2.equals((Object)booleanArray1));
	      assertFalse(booleanArray2.equals((Object)booleanArray0));
	      assertNotSame(booleanArray0, booleanArray3);
	      assertNotSame(booleanArray0, booleanArray2);
	      assertNotSame(booleanArray0, booleanArray1);
	      assertNotSame(booleanArray2, booleanArray3);
	      assertNotSame(booleanArray2, booleanArray1);
	      assertNotSame(booleanArray2, booleanArray0);
	      assertEquals(10, booleanArray0.length);
	      assertEquals(10, booleanArray2.length);
	      
	      boolean boolean2 = Life.inbounds(booleanArray2, (-2037), 3);
	      assertFalse(booleanArray0.equals((Object)booleanArray3));
	      assertFalse(booleanArray0.equals((Object)booleanArray2));
	      assertFalse(booleanArray0.equals((Object)booleanArray1));
	      assertFalse(booleanArray2.equals((Object)booleanArray3));
	      assertFalse(booleanArray2.equals((Object)booleanArray1));
	      assertFalse(booleanArray2.equals((Object)booleanArray0));
	      assertTrue(boolean2 == boolean0);
	      assertTrue(boolean2 == boolean1);
	      assertNotSame(booleanArray0, booleanArray3);
	      assertNotSame(booleanArray0, booleanArray2);
	      assertNotSame(booleanArray0, booleanArray1);
	      assertNotSame(booleanArray2, booleanArray3);
	      assertNotSame(booleanArray2, booleanArray1);
	      assertNotSame(booleanArray2, booleanArray0);
	      assertFalse(boolean2);
	      assertEquals(10, booleanArray0.length);
	      assertEquals(10, booleanArray2.length);
	  }

	  @Test()
	  public void test08()  throws Throwable  {
	      boolean[][] booleanArrayArray0 = null;
	      int int0 = 2284;
	      int int1 = (-669);
	      // Undeclared exception!
	      try { 
	        Life.inbounds((boolean[][]) null, 2284, (-669));
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	      }
	  }

	  @Test()
	  public void test09()  throws Throwable  {
	      boolean boolean0 = Life.occupiedNext(1, true);
	      assertFalse(boolean0);
	      
	      Life life0 = new Life();
	      assertNotNull(life0);
	      
	      life0.run();
	      boolean[][] booleanArray0 = new boolean[1][9];
	      boolean[] booleanArray1 = new boolean[5];
	      booleanArray1[0] = true;
	      booleanArray1[1] = false;
	      booleanArray1[2] = true;
	      booleanArray1[3] = true;
	      booleanArray1[4] = true;
	      booleanArray0[0] = booleanArray1;
	      int int0 = Life.numNeighbors(booleanArray0, 0, 0);
	      assertEquals(0, int0);
	      assertEquals(1, booleanArray0.length);
	  }

	  @Test()
	  public void test10()  throws Throwable  {
	      Life life0 = new Life();
	      assertNotNull(life0);
	      
	      boolean[][] booleanArray0 = Life.gen();
	      assertNotNull(booleanArray0);
	      assertEquals(10, booleanArray0.length);
	      
	      boolean[][] booleanArray1 = Life.gen();
	      assertNotNull(booleanArray1);
	      assertFalse(booleanArray1.equals((Object)booleanArray0));
	      assertNotSame(booleanArray1, booleanArray0);
	      assertEquals(10, booleanArray1.length);
	      
	      life0.run();
	      boolean boolean0 = Life.occupiedNext(3143, true);
	      assertFalse(boolean0);
	      
	      Life.show(booleanArray1);
	      assertFalse(booleanArray1.equals((Object)booleanArray0));
	      assertNotSame(booleanArray1, booleanArray0);
	      assertEquals(10, booleanArray1.length);
	      
	      // Undeclared exception!
	      try { 
	        Life.numNeighbors(booleanArray1, 3143, (-1));
	        fail("Expecting exception: ArrayIndexOutOfBoundsException");
	      
	      } catch(ArrayIndexOutOfBoundsException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	      }
	  }

	  @Test()
	  public void test11()  throws Throwable  {
	      // Undeclared exception!
	      try { 
	        Life.nextGen((boolean[][]) null);
	        fail("Expecting exception: NullPointerException");
	      
	      } catch(NullPointerException e) {
	         //
	         // no message in exception (getMessage() returned null)

	      }
	  }

	  @Test()
	  public void test12()  throws Throwable  {
	      boolean[][] booleanArray0 = new boolean[0][5];
	      boolean boolean0 = Life.inbounds(booleanArray0, 0, 1);
	      assertFalse(boolean0);
	      assertEquals(0, booleanArray0.length);
	      
	      boolean[][] booleanArray1 = Life.gen();
	      assertNotNull(booleanArray1);
	      assertFalse(booleanArray1.equals((Object)booleanArray0));
	      assertNotSame(booleanArray1, booleanArray0);
	      assertEquals(10, booleanArray1.length);
	      
	      boolean boolean1 = Life.occupiedNext(0, false);
	      assertTrue(boolean1 == boolean0);
	      assertFalse(boolean1);
	  }

	  @Test()
	  public void test13()  throws Throwable  {
	      boolean[][] booleanArray0 = new boolean[2][4];
	      boolean[] booleanArray1 = new boolean[3];
	      booleanArray1[0] = true;
	      booleanArray1[1] = true;
	      booleanArray1[2] = false;
	      booleanArray0[0] = booleanArray1;
	      boolean[] booleanArray2 = new boolean[4];
	      assertFalse(booleanArray2.equals((Object)booleanArray1));
	      
	      booleanArray2[0] = true;
	      booleanArray2[1] = true;
	      booleanArray2[2] = false;
	      booleanArray2[3] = true;
	      booleanArray0[1] = booleanArray2;
	      int int0 = Life.numNeighbors(booleanArray0, 0, 2);
	      assertEquals(2, int0);
	      assertEquals(2, booleanArray0.length);
	  }

	  @Test()
	  public void test14()  throws Throwable  {
	      boolean[][] booleanArray0 = new boolean[0][9];
	      boolean boolean0 = Life.inbounds(booleanArray0, 1197, 1197);
	      assertFalse(boolean0);
	      assertEquals(0, booleanArray0.length);
	  }

	  @Test()
	  public void test15()  throws Throwable  {
	      String[] stringArray0 = new String[0];
	      Life.main(stringArray0);
	      assertEquals(0, stringArray0.length);
	      
	      boolean[][] booleanArray0 = new boolean[6][4];
	      boolean[] booleanArray1 = new boolean[8];
	      booleanArray1[0] = true;
	      booleanArray1[1] = true;
	      booleanArray1[2] = false;
	      booleanArray1[3] = true;
	      booleanArray1[4] = false;
	      booleanArray1[5] = false;
	      booleanArray1[6] = true;
	      booleanArray1[7] = true;
	      booleanArray0[0] = booleanArray1;
	      boolean[] booleanArray2 = new boolean[3];
	      assertFalse(booleanArray2.equals((Object)booleanArray1));
	      
	      booleanArray2[0] = false;
	      booleanArray2[1] = true;
	      booleanArray2[2] = true;
	      booleanArray0[1] = booleanArray2;
	      boolean[] booleanArray3 = new boolean[0];
	      assertFalse(booleanArray3.equals((Object)booleanArray1));
	      assertFalse(booleanArray3.equals((Object)booleanArray2));
	      
	      booleanArray0[2] = booleanArray3;
	      boolean[] booleanArray4 = new boolean[7];
	      assertFalse(booleanArray4.equals((Object)booleanArray3));
	      assertFalse(booleanArray4.equals((Object)booleanArray1));
	      assertFalse(booleanArray4.equals((Object)booleanArray2));
	      
	      booleanArray4[0] = false;
	      booleanArray4[1] = true;
	      booleanArray4[2] = true;
	      booleanArray4[3] = true;
	      booleanArray4[4] = true;
	      booleanArray4[5] = true;
	      booleanArray4[6] = true;
	      booleanArray0[3] = booleanArray4;
	      boolean[] booleanArray5 = new boolean[2];
	      assertFalse(booleanArray5.equals((Object)booleanArray1));
	      assertFalse(booleanArray5.equals((Object)booleanArray3));
	      assertFalse(booleanArray5.equals((Object)booleanArray4));
	      assertFalse(booleanArray5.equals((Object)booleanArray2));
	      
	      booleanArray5[0] = true;
	      booleanArray5[1] = true;
	      booleanArray0[4] = booleanArray5;
	      boolean[] booleanArray6 = new boolean[1];
	      assertFalse(booleanArray6.equals((Object)booleanArray1));
	      assertFalse(booleanArray6.equals((Object)booleanArray4));
	      assertFalse(booleanArray6.equals((Object)booleanArray5));
	      assertFalse(booleanArray6.equals((Object)booleanArray3));
	      assertFalse(booleanArray6.equals((Object)booleanArray2));
	      
	      booleanArray6[0] = false;
	      booleanArray0[5] = booleanArray6;
	      // Undeclared exception!
	      try { 
	        Life.nextGen(booleanArray0);
	        fail("Expecting exception: ArrayIndexOutOfBoundsException");
	      
	      } catch(ArrayIndexOutOfBoundsException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	      }
	  }

	  @Test()
	  public void test16()  throws Throwable  {
	      boolean[][] booleanArray0 = Life.gen();
	      assertNotNull(booleanArray0);
	      assertEquals(10, booleanArray0.length);
	      

	      int int0 = (-933);
	      boolean boolean0 = Life.occupiedNext((-933), false);
	      assertFalse(boolean0);
	      
	      // Undeclared exception!
	      try { 
	        Life.numNeighbors(booleanArray0, 853, (-933));
	        fail("Expecting exception: ArrayIndexOutOfBoundsException");
	      
	      } catch(ArrayIndexOutOfBoundsException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	      }
	  }

	  @Test()
	  public void test17()  throws Throwable  {
	      boolean boolean0 = Life.occupiedNext((-1054), true);
	      assertFalse(boolean0);
	  }

	  @Test()
	  public void test18()  throws Throwable  {
	      String[] stringArray0 = new String[0];
	      Life.main(stringArray0);
	      assertEquals(0, stringArray0.length);
	      
	      boolean[][] booleanArray0 = Life.gen();
	      assertNotNull(booleanArray0);
	      assertEquals(10, booleanArray0.length);
	      
	      boolean[][] booleanArray1 = Life.gen();
	      assertNotNull(booleanArray1);
	      assertFalse(booleanArray1.equals((Object)booleanArray0));
	      assertNotSame(booleanArray1, booleanArray0);
	      assertEquals(10, booleanArray1.length);
	      
	      Life life0 = new Life();
	      assertNotNull(life0);
	      
	      boolean[][] booleanArray2 = Life.gen();
	      assertNotNull(booleanArray2);
	      assertFalse(booleanArray2.equals((Object)booleanArray1));
	      assertFalse(booleanArray2.equals((Object)booleanArray0));
	      assertNotSame(booleanArray2, booleanArray1);
	      assertNotSame(booleanArray2, booleanArray0);
	      assertEquals(10, booleanArray2.length);
	      
	      boolean[][] booleanArray3 = Life.gen();
	      assertNotNull(booleanArray3);
	      assertFalse(booleanArray3.equals((Object)booleanArray2));
	      assertFalse(booleanArray3.equals((Object)booleanArray0));
	      assertFalse(booleanArray3.equals((Object)booleanArray1));
	      assertNotSame(booleanArray3, booleanArray2);
	      assertNotSame(booleanArray3, booleanArray0);
	      assertNotSame(booleanArray3, booleanArray1);
	      assertEquals(10, booleanArray3.length);
	      
	      Life.show(booleanArray1);
	      assertFalse(booleanArray1.equals((Object)booleanArray0));
	      assertFalse(booleanArray1.equals((Object)booleanArray2));
	      assertFalse(booleanArray1.equals((Object)booleanArray3));
	      assertNotSame(booleanArray1, booleanArray0);
	      assertNotSame(booleanArray1, booleanArray2);
	      assertNotSame(booleanArray1, booleanArray3);
	      assertEquals(10, booleanArray1.length);
	      
	      life0.run();
	      boolean[][] booleanArray4 = Life.nextGen(booleanArray0);
	      assertNotNull(booleanArray4);
	      assertFalse(booleanArray0.equals((Object)booleanArray1));
	      assertFalse(booleanArray0.equals((Object)booleanArray3));
	      assertFalse(booleanArray0.equals((Object)booleanArray2));
	      assertFalse(booleanArray4.equals((Object)booleanArray3));
	      assertFalse(booleanArray4.equals((Object)booleanArray2));
	      assertFalse(booleanArray4.equals((Object)booleanArray0));
	      assertFalse(booleanArray4.equals((Object)booleanArray1));
	      assertNotSame(booleanArray0, booleanArray1);
	      assertNotSame(booleanArray0, booleanArray4);
	      assertNotSame(booleanArray0, booleanArray3);
	      assertNotSame(booleanArray0, booleanArray2);
	      assertNotSame(booleanArray4, booleanArray3);
	      assertNotSame(booleanArray4, booleanArray2);
	      assertNotSame(booleanArray4, booleanArray0);
	      assertNotSame(booleanArray4, booleanArray1);
	      assertEquals(10, booleanArray0.length);
	      assertEquals(10, booleanArray4.length);
	      
	      // Undeclared exception!
	      try { 
	        Life.numNeighbors(booleanArray1, 0, (-3583));
	        fail("Expecting exception: ArrayIndexOutOfBoundsException");
	      
	      } catch(ArrayIndexOutOfBoundsException e) {
	         //
	         // no message in exception (getMessage() returned null)
	         //
	      }
	  }

	  @Test()
	  public void test19()  throws Throwable  {
	      boolean[][] booleanArray0 = Life.gen();
	      boolean[][] booleanArray1 = Life.nextGen(booleanArray0);
	      Life.inbounds(booleanArray1, 0, 0);
	      String[] stringArray0 = new String[0];
	      Life.main(stringArray0);
	      boolean boolean0 = Life.inbounds(booleanArray0, 0, 0);
	      Life.occupiedNext(0, true);
	      boolean[][] booleanArray2 = Life.gen();
	      Life.main(stringArray0);
	      boolean boolean1 = Life.occupiedNext(0, true);
	      assertFalse(boolean1 == boolean0);
	      
	      Life life0 = new Life();
	      Life.show(booleanArray0);
	      life0.run();
	      Life.nextGen(booleanArray1);
	      int int0 = Life.numNeighbors(booleanArray0, 0, 0);
	      assertEquals(0, int0);
	      
	      Life.show(booleanArray2);
	      assertEquals(10, booleanArray2.length);
	  }
	


}
