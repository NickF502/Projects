package project3;

/**
 * A RomanNumeral class featuring a variety of methods. A Roman Numeral is
 * an object that contains a string of Roman Numerals, such as XLIV, and its
 * corresponding Arabic / integer value, 44
 * 
 *  @author Nicholas Farkash
 */

public class RomanNumeral {

	/**
	 * Private instance variables for the class
	 * 
	 * @param romanNumeralString the string of Roman Numerals, as found in input.txt
	 * @param arabicValue the corresponding integer value of the Roman Numeral
	 */
	private String romanNumeralString;
	private int arabicValue;
	
	/** Constructor that creates a new NomanNumeral */
	public RomanNumeral(String romanNum) {
		romanNumeralString = romanNum;
		
		/** Check that the numeral's value is valid before assigning arabicValue */
		int val = Project3.valueOf(romanNumeralString);
		if (val == -1)
			throw new IllegalRomanNumeralException("Not a Valid Roman Numeral: ");
		else
			arabicValue = val;
	}
	
	/** get and set methods for romanNumeralString 
	 * @return romanNumeralString
	 */
	public String getRomanNumeral() {
		return romanNumeralString;
	}
	
	public void setRomanNumeral(String s) {
		romanNumeralString = s;
	}
	
	/** get method for arabicValue 
	 * @return arabicValue
	 */
	public String getArabicValue() {
		return Integer.toString(arabicValue);
	}
	
	/** equals method to determine if an object is the same as a particular Roman Numeral
	 * @return a boolean that says whether the object equals the RomanNumeral
	 */
	public boolean equals(Object other) {
		if(this == other) return true;
		if(other != null && this.getClass().equals(other.getClass())) {
			RomanNumeral romNumOther = (RomanNumeral)other;
			if(compareTo(romNumOther) == 0) return true;
			else return false;
		}
		return false;
	}
	
	/** toString method that converts a RomanNumeral's arabicValue into a string
	 * @return A string that contains the arabicValue of the RomanNumeral
	 */
	public String toString() {
		return Integer.toString(arabicValue);
	}
	
	/** compareTo method to determine if a RomanNumeral is less than, greater than, or equal to another
	 * @return a positive/negative integer depending on whether the RomanNumeral's arabicValue is greater/less than another's.
	 * 			Returns 0 if they are equal

	 */
	public int compareTo(RomanNumeral other) {
		return(this.arabicValue - other.arabicValue);
	}

	
}



