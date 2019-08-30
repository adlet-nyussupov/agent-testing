package SUTClasses;

public class LeapYear implements Runnable {
	public static void main(String[] args) {

	}

	public void check(int year) {
		boolean isLeap = false;

		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0)
					isLeap = true;
				else
					isLeap = false;
			} else
				isLeap = true;
		} else {
			isLeap = false;
		}

		if (isLeap == true)
			System.out.println(year + " is a Leap Year.");
		else
			System.out.println(year + " is not a Leap Year.");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}