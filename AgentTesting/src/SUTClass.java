import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class SUTClass implements Runnable {
	static List instances = new ArrayList();

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	public void setData(int a, int b) {
		this.a = a;
		this.b = b;
	}

	int a;
	int b;

	public static boolean f(int a, int b) {
		if (a < b) {
			// System.out.println("True");
			return true;
		} else {
			// System.out.println("False");
			return false;
		}
	}

	public static int d(int b, int a) {
		if (a < b) {
			return a + b;
		} else
			return a - b;
	}
}