package SUTClasses;

public class Counter implements Runnable {

	public void calc(int marks[]) {
		int i;
		float total = 0, avg;
		for (i = 0; i < marks.length; i++) {
			total = total + marks[i];
		}
		avg = total / 6;
		System.out.print("The student Grade is: ");
		if (avg >= 80) {
			System.out.print("A");
		} else if (avg >= 60 && avg < 80) {
			System.out.print("B");
		} else if (avg >= 40 && avg < 60) {
			System.out.print("C");
		} else {
			System.out.print("D");
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}