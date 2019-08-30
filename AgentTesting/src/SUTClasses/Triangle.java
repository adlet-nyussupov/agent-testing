package SUTClasses;


public class Triangle implements Runnable{
	

	public String getType(int a, int b, int c) {
		try {
			if (a == b && a > 0 && a <= Integer.MAX_VALUE && a == c && b > 0 && b <= Integer.MAX_VALUE && b == c
					&& c > 0 && c <= Integer.MAX_VALUE) {
				return "Equilateral";
			} else if (a > 0 && a <= Integer.MAX_VALUE && b > 0 && b <= Integer.MAX_VALUE && c > 0
					&& c <= Integer.MAX_VALUE) {
				if (a == b || a == c || b == c) {
					return "Isosceles";
				} else
					return "Scalene";
			}
			return "Error";
		} catch (Exception ex) {

		}
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


}
