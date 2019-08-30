import java.util.ArrayList;

public class SUTClass implements Runnable {

	@Override
	public void run() {

	}

	public void method1(ArrayList<String> arrayList) {

	}

	public static boolean method2(int a, int b) {
		if (a < b) {
			return true;
		} else if (a > b) {
			return false;
		} else {
			return false;
		}
	}

	public static int method3(int b, int a) {
		if (a < b) {
			return a + b;
		} else if (a > b) {
			return b;
		} else
			return a - b;
	}

	public static String method4(String st) {
		return "1";
	}

	public static String method5(Double db) {

		return "2";
	}

	public static String method6(Float db) {

		return "3";
	}

	public static String method7(Byte bt, Byte dt) {

		return "4";
	}
	
	public static String method8(Character ch) {

		return "5";
	}
	
	public static String method9(Long lg) {

		return "6";
	}
	
	public static String method8(Short lg, Short dt) {

		return "7";
	}
	
	public static String method11(Boolean lg) {

		return "8";
	}

}