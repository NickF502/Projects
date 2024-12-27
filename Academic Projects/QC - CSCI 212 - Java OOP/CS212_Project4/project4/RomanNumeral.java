package project4;

import java.util.HashMap;

/**
 * A RomanNumeral class featuring a variety of methods. A Roman Numeral is
 * an object that contains a string of Roman Numerals, such as XLIV, and its
 * corresponding Arabic / integer value, 44
 * 
 *  @author Nicholas Farkash
 */

public class RomanNumeral implements Comparable {

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
		int val = valueOf(romanNumeralString);
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
	
	/** compareTo method to determine if a RomanNumeral is less than, greater than, or equal to another. Required in
	 * order for the TreeMap to properly sort the values
	 * @return a positive/negative integer depending on whether the RomanNumeral's arabicValue is greater/less than another's.
	 * 			Returns 0 if they are equal

	 */
	public int compareTo(Object other) {
		if(other instanceof RomanNumeral) {
			RomanNumeral o = (RomanNumeral)other;
			return(this.arabicValue - o.arabicValue);
		}
		else{
			throw new IllegalRomanNumeralException("Error");
		}
	}

	
	
	
	/** valueOf function which converts a Roman Numeral string into its Arabic value
	 * 
	 * @param numString will store a substring of the Roman Numeral to determine if it is a subtraction numeral, such as IV
	 * @param substringEvaluated Boolean to determine if a subtraction numeral was evaluated
	 * @param prevAdded Value of numeral previously added, 0 by default
	 * @param wantToAdd Value of numeral currently being evaluated, 0 by default
	 *  
	 * @return romNum the integer value of the Roman Numeral
	 */
	public static int valueOf(String numString) {
	    int len = numString.length();

	    if(numString == null || len == 0) return -1;
	    
		/** Check for invalid characters within the numeral string
    	 * @param c character being evaluated 
    	 */
    	for(int i=0; i < len; i++) {
	    	char c = numString.charAt(i);
	    	if (c != 'I' && c != 'V' && c != 'X' && c != 'L' && c != 'C' && c != 'D' && c != 'M')
	            throw new IllegalRomanNumeralException("\"" + numString + "\" contains an invalid character");
    	}
    	
    	/**	Check for invalid strings of two or more numerals */
		String[] invalidNumeralStrings = {"IIV", "IIX", "IL", "IC", "ID", "IM", "VV", "VX", "VL", "VC", "VD", "VM", "XD", "XM", "LL", "LC", "LD", "LM", "DD", "DM", "IIII", "XXXX", "CCCC", "MMMM"};
    	for(String i:invalidNumeralStrings){
    		if(numString.contains(i))
	    		throw new IllegalRomanNumeralException("\"" + numString + "\" contains the invalid numeral string " + i);
    	}
    	
		
		
		
		/** A HashMap that links numeral values to arabic values */
		HashMap<Character, Integer> RomNumMap = new HashMap<Character, Integer>();
		RomNumMap.put('I', 1);
		RomNumMap.put('V', 5);
		RomNumMap.put('X', 10);
		RomNumMap.put('L', 50);
		RomNumMap.put('C', 100);
		RomNumMap.put('D', 500);
		RomNumMap.put('M', 1000);
		
		
		int romNum = RomNumMap.get(numString.charAt(len-1));		// Value of Roman numeral string		    
	    

	    try { 

	    	for(int i=len-2; i >= 0; i--) {
	    		if(RomNumMap.get(numString.charAt(i)) >= RomNumMap.get(numString.charAt(i+1)))
	    			romNum += RomNumMap.get(numString.charAt(i));
	    		else
	    			romNum -= RomNumMap.get(numString.charAt(i));
	    	}
	    	
		    return romNum;	// Return the integer value of the string
		    
	    }
	    /** Catch the IllegalRomanNumeralException if thrown and return -1 for purposes in other classes */
	    catch(IllegalRomanNumeralException irne) {
	    	return -1;
	    }
		

    }

	
	
}



