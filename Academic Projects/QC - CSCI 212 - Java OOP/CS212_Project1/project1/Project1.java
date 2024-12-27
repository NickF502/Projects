package project1;

import java.util.*;

public class Project1 {

    public static void main(String[] args) {
    	TextFileInput in = new TextFileInput("input.txt"); // Read in input.txt
    	String allRomanNums = "";	// Create an empty string that will eventually store all numerals

    	String line = in.readLine();	// Read the first line of the text file
    	StringTokenizer myTokens;		// Create a tokenizer

    	while (line != null) {			// While loop to read lines until the last line has been read

    		myTokens = new StringTokenizer(line, ",");	// Tokenize the line

    		while(myTokens.countTokens() > 0) {
    			String str = myTokens.nextToken() + ",";
    			allRomanNums+=str;		// Append all Tokens into the empty string, separated with a comma
    		}

    		line = in.readLine();			// Read the next like

    	}

    
	    StringTokenizer allTokens = new StringTokenizer(allRomanNums, ",");		// Tokenize the string with all numerals
	    
	    int lenList = allTokens.countTokens();
	    String[] romanList = new String[lenList];	// Create a string array with length equal to number of tokens
	
	    for (int i = 0; i < lenList; i++) {
	    	romanList[i] = allTokens.nextToken();		// Store all numerals in String array
	    }
	
	    // Run all numerals through valueOf function and store the number result in in arabicList
	    String[] arabicList = new String[lenList];
	
	    for (int i = 0; i < lenList; i++) {
	    	arabicList[i] = valueOf(romanList[i]);
	    }
	
	    // Initialize GUI and then call GUI function to display results
	    RomanNumeralGUI rngui = RomanNumeralGUI.initialize();
	    RomanNumeralGUI.printtoRNGUI(rngui, romanList, arabicList, lenList);
    
    }

  
    
    // Function that takes in a string representing a Roman numeral and returns its number value
    public static String valueOf(String numString) {
	  
	    int romNum = 0;		// Value of Roman numeral string
	    numString += " ";	// Empty string that will hold the value of a substring
	    
	    int len = numString.length();	
	
	    for (int i = 0; i < len - 1; i++) { 
                                        
	    	if (i < len - 1) { 	// If i is not the last letter, we need to check for subtraction numerals like IX

	    		String sub = numString.substring(i, i + 2);		// Create a substring of 2 characters to check for subtraction numerals

	    		// if statements to determine the value of the numeral to add
		        if (sub.equals("IV")) {	// If substring equals a particular value
		          romNum += 4;			// Add the value of that subtraction numeral
		          i++;					// Increment i to account for the 2 numerals being evaluated
		          continue;				// Move to the next numeral after the pair
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

    	// If block to evaluate single Roman numeral values
	    if (numString.charAt(i) == 'I') romNum += 1;
    	else if (numString.charAt(i) == 'V') romNum += 5;
    	else if (numString.charAt(i) == 'X') romNum += 10;
    	else if (numString.charAt(i) == 'L') romNum += 50;
    	else if (numString.charAt(i) == 'C') romNum += 100;
    	else if (numString.charAt(i) == 'D') romNum += 500;
    	else if (numString.charAt(i) == 'M') romNum += 1000;

	    }

	    return Integer.toString(romNum);	// Turn the integer value into a string and return it
    
    }

}
