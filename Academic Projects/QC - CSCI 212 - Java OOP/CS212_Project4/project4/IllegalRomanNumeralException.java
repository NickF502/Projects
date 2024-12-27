package project4;

public class IllegalRomanNumeralException extends IllegalArgumentException {
	
	/**
	 * An exception that extends IllegalArgumentException to be thrown when it is determined that
	 * the Roman Numeral string is not a valid representation of a Roman Numeral
	 * 
	 * @param message The message to display when the exception is thrown
	 */
	public IllegalRomanNumeralException(String message) {
		System.out.println(message);
	}
	
}
