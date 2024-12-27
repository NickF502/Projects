package project3;

/**
 * Project 3 for CSCI 212 at Queens College
 * Lecturer: Dr. Kenneth Lord
 * Lab Instructor: Nikola Baci
 * 
 * @author Nicholas Farkash
 */

public class Project3 {

	
	/** Main program that creates the GUI used in the project */
	public static void main(String[] args) {
		RomanNumeralGUI myGUI = new RomanNumeralGUI("Roman Numeral Conversion");
		myGUI.pack();
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
		int romNum = 0;		// Value of Roman numeral string		    
	    int len = numString.length();
	    
	    boolean substringEvaluated = false;
	    int prevAdded = 0;
	    int wantToAdd = 0;
	    
	    try { 

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
	    	
	    	
		    /** Loop over all elements of the Roman Numeral string being evaluated. */
		    for (int i = 0; i < len; i++) { 
		    	
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
			        	wantToAdd = 4;
			        	i++;
			        	substringEvaluated = true;
			        }
			        if (sub.equals("IX")) {
			        	wantToAdd = 9;
			        	i++;
			        	substringEvaluated = true;
			        }
			        if (sub.equals("XL")) {
			        	wantToAdd = 40;
			        	i++;
			        	substringEvaluated = true;
			        }
			        if (sub.equals("XC")) {
			        	wantToAdd = 90;
			        	i++;
			        	substringEvaluated = true;
			        }
			        if (sub.equals("CD")) {
			        	wantToAdd = 400;
			        	i++;
			        	substringEvaluated = true;
			        }
			        if (sub.equals("CM")) {
			        	wantToAdd = 900;
			        	i++;
			        	substringEvaluated = true;
			        }
		    	}

		    	if(substringEvaluated == false){
		    		
		    		/** If no subtraction numerals are found, then this means that only the i-th numeral needs to be counted.
			    	 * This block evaluates all single Roman numeral values and increases romNum accordingly
			    	 */
				    if (numString.charAt(i) == 'I') wantToAdd = 1;
			    	else if (numString.charAt(i) == 'V') wantToAdd = 5;
			    	else if (numString.charAt(i) == 'X') wantToAdd = 10;
			    	else if (numString.charAt(i) == 'L') wantToAdd = 50;
			    	else if (numString.charAt(i) == 'C') wantToAdd = 100;
			    	else if (numString.charAt(i) == 'D') wantToAdd = 500;
			    	else if (numString.charAt(i) == 'M') wantToAdd = 1000;
			        else throw new IllegalRomanNumeralException("\"" + numString + "\" is not a valid Roman Numeral");
			    }
		    	
		   
		    	/**
		    	 * Some error checking to see if the numeral is constructed properly. You can only add
		    	 * smaller numerals to the running total, so this block checks if the numeral being added is larger
		    	 * than the one previously added and throws an exception if it is
		    	 * 
		    	 * @param
		    	 */
		    	if((prevAdded != 0) && ((wantToAdd > prevAdded)))
		    		throw new IllegalRomanNumeralException("\"" + numString + "\" has improperly ordered numerals");
		    	else {
		    		romNum+=wantToAdd;
		    		prevAdded = wantToAdd;
		    		wantToAdd = 0;
		    		substringEvaluated = false;
		    	}    	
	    		

		    }
		    
		    return romNum;	// Return the integer value of the string
		    
	    }
	    /** Catch the IllegalRomanNumeralException if thrown and return -1 for purposes in other classes */
	    catch(IllegalRomanNumeralException irne) {
	    	return -1;
	    }
		

    }
	
}

	