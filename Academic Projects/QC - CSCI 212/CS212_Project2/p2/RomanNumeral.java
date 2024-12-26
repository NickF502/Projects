package p2;

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
		arabicValue = valueOf(romanNumeralString);
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
	public int getArabicValue() {
		return arabicValue;
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
	
	/** valueOf function which converts a Roman Numeral string into its
	 * Arabic value
	 * @param numString will store a substring of the Roman Numeral to determine if 
	 * 			it is a subtraction numeral, such as IV
	 * @return romNum the integer value of the Roman Numeral
	 */
	public static int valueOf(String numString) {
		  
	    int romNum = 0;		// Value of Roman numeral string
	    numString += " ";	// Empty string that will hold the value of a substring
	    
	    int len = numString.length();	
	
	    
	    /** Loop over all elements of the Roman Numeral string being evaluated. */
	    for (int i = 0; i < len - 1; i++) { 
                                
	    	/** If i is not the last letter, subtraction numerals like IV need to be checked for.
	    	 * This can be accomplished using a substring of two characters and checking if that substring
	    	 * contains a subtraction numeral. If it does, the proper value is added to romNum and the next
	    	 * substring evaluated is the one which begins after the subtraction numeral ends
	    	 */
	    	if (i < len - 1) { 	

	    		String sub = numString.substring(i, i + 2);		// Create a substring of 2 characters to check for subtraction numerals

	    		/**
	    		 * If statements to check if the substring contains a subtraction numeral. If it does, the proper value is added,
	    		 * i is incremented to account for the fact that subtraction numerals utilize two characters, and a continue is used
	    		 * to return to the top of the loop
	    		 */
		        if (sub.equals("IV")) {
		          romNum += 4;
		          i++;
		          continue;
		        }
		        if (sub.equals("IX")) {
		          romNum += 9;
		          i++;
		          continue;
		        }
		        if (sub.equals("XL")) {
		          romNum += 40;
		          i++;
		          continue;
		        }
		        if (sub.equals("XC")) {
		          romNum += 90;
		          i++;
		          continue;
		        }
		        if (sub.equals("CD")) {
		          romNum += 400;
		          i++;
		          continue;
		        }
		        if (sub.equals("CM")) {
		          romNum += 900;
		          i++;
		          continue;
		        }
	
	    	}

    	/** If no subtraction numerals are found, then this means that only the i-th numeral needs to be counted.
    	 * This block evaluates all single Roman numeral values and increases romNum accordingly
    	 * 
    	 */
	    if (numString.charAt(i) == 'I') romNum += 1;
    	else if (numString.charAt(i) == 'V') romNum += 5;
    	else if (numString.charAt(i) == 'X') romNum += 10;
    	else if (numString.charAt(i) == 'L') romNum += 50;
    	else if (numString.charAt(i) == 'C') romNum += 100;
    	else if (numString.charAt(i) == 'D') romNum += 500;
    	else if (numString.charAt(i) == 'M') romNum += 1000;
	    }

	    return romNum;	// Return the integer value of the string
    
    }
	

}



