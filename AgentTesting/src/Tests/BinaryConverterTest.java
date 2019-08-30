package Tests;

import javax.annotation.Generated;

import org.junit.Test;

import SUTClasses.BinaryConverter;

@Generated(value = "org.junit-tools-1.0.6")
public class BinaryConverterTest {

	private BinaryConverter createTestSubject() {
		return new BinaryConverter();
	}


	@Test
	public void testAll0sAnd1s() throws Exception {
		String val = "";
		boolean result;

		// default test
		result = BinaryConverter.all0sAnd1s(val);
	}


	@Test
	public void testMain() throws Exception {
		String[] args = new String[] { "" };

		// default test
		BinaryConverter.main(args);
	}



	@Test
	public void testToBinary() throws Exception {
		int base10Num = 0;
		String result;

		// default test
		result = BinaryConverter.toBinary(base10Num);
	}
}