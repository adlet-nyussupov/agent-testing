package SUTClasses;
import java.math.BigInteger;
import java.util.Random;




public class PrimeEx implements Runnable {


	public static void main(String[] args) {

	}

	public static boolean[] getPrimes(int max) {
		boolean[] result = new boolean[max + 1];
		for(int i = 2; i < result.length; i++)
			result[i] = true;
		final double LIMIT = Math.sqrt(max);
		for(int i = 2; i <= LIMIT; i++) {
			if(result[i]) {
				// cross out all multiples;
				int index = 2 * i;
				while(index < result.length){
					result[index] = false;
					 index += i;
				}
			}
		}
		return result;
	}


	public static void printTest(int num, int expectedFactors) {


		int actualFactors = numFactors(num);

		System.out.println("Testing " + num + " expect " + expectedFactors + ", " +
				"actual " + actualFactors);
		if(actualFactors == expectedFactors)
			System.out.println("PASSED");
		else
			System.out.println("FAILED");

	}


	public static boolean isPrime(int num) {
		assert num >= 2 : "failed precondition. num must be >= 2. num: " + num;
		final double LIMIT = Math.sqrt(num);
		boolean isPrime = (num == 2) ? true : num % 2 != 0;
		int div = 3;
		while(div <= LIMIT && isPrime) {
			isPrime = num % div != 0;
			div += 2;
		}
		return isPrime;
	}

	// pre: num >= 2
	public static int numFactors(int num) {
		assert num >= 2 : "failed precondition. num must be >= 2. num: " + num;
		int result = 0;
		final double SQRT = Math.sqrt(num);
		for(int i = 1; i < SQRT; i++) {
			if(num % i == 0) {
				result += 2;
			}
		}
		if(num % SQRT == 0)
			result++;
		return result;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
