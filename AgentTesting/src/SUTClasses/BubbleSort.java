package SUTClasses;

public class BubbleSort implements Runnable {

	public void sort(String str[]) {
		String temp;
		System.out.println("Strings in sorted order:");
		for (int j = 0; j < str.length; j++) {
			for (int i = j + 1; i < str.length; i++) {
				// comparing adjacent strings
				if (str[i].compareTo(str[j]) < 0) {
					temp = str[j];
					str[j] = str[i];
					str[i] = temp;
				}
			}
			System.out.println(str[j]);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
