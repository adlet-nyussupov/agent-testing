package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SUTClasses.Triangle;

class TriangleTestEvo {

	@BeforeEach
	void setUp() throws Exception {
	}

	 @Test()
	  public void test00()  throws Throwable  {
	      Triangle triangle0 = new Triangle();
	      String string0 = triangle0.getType(Integer.MAX_VALUE, 688, 1036);
	      assertEquals("Scalene", string0);
	  }

	  @Test()
	  public void test01()  throws Throwable  {
	      Triangle triangle0 = new Triangle();
	      String string0 = triangle0.getType(1417, 1786, 1786);
	      assertEquals("Isosceles", string0);
	  }

	  @Test()
	  public void test02()  throws Throwable  {
	      Triangle triangle0 = new Triangle();
	      String string0 = triangle0.getType(966, (-3943), (-2579));
	      assertEquals("Error", string0);
	  }

	  @Test()
	  public void test03()  throws Throwable  {
	      Triangle triangle0 = new Triangle();
	      String string0 = triangle0.getType(2955, 2955, 0);
	      assertEquals("Error", string0);
	  }

	  @Test()
	  public void test04()  throws Throwable  {
	      Triangle triangle0 = new Triangle();
	      String string0 = triangle0.getType(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
	      assertEquals("Equilateral", string0);
	  }

	  @Test()
	  public void test05()  throws Throwable  {
	      Triangle triangle0 = new Triangle();
	      String string0 = triangle0.getType((-2335), (-2335), 0);
	      assertEquals("Error", string0);
	  }

	  @Test()
	  public void test06()  throws Throwable  {
	      Triangle triangle0 = new Triangle();
	      String string0 = triangle0.getType(1474, Integer.MAX_VALUE, 1);
	      assertEquals("Scalene", string0);
	  }

	  @Test()
	  public void test07()  throws Throwable  {
	      Triangle triangle0 = new Triangle();
	      String string0 = triangle0.getType(1, 2009, 1);
	      assertEquals("Isosceles", string0);
	  }

	  @Test()
	  public void test08()  throws Throwable  {
	      Triangle triangle0 = new Triangle();
	      String string0 = triangle0.getType(1, 1474, (-1629));
	      assertEquals("Error", string0);
	  }

	  @Test()
	  public void test09()  throws Throwable  {
	      Triangle triangle0 = new Triangle();
	      String string0 = triangle0.getType(Integer.MAX_VALUE, 0, 0);
	      assertEquals("Error", string0);
	  }

	  @Test()
	  public void test10()  throws Throwable  {
	      Triangle triangle0 = new Triangle();
	      String string0 = triangle0.getType(1, 1, Integer.MAX_VALUE);
	      assertEquals("Isosceles", string0);
	  }

	  @Test()
	  public void test11()  throws Throwable  {
	      Triangle triangle0 = new Triangle();
	      String string0 = triangle0.getType(1, 1, 1);
	      assertEquals("Equilateral", string0);
	  }

	  @Test()
	  public void test12()  throws Throwable  {
	      Triangle triangle0 = new Triangle();
	      String string0 = triangle0.getType(0, 0, 0);
	      assertEquals("Error", string0);
	  }


}
