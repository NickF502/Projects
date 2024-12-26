package p2;

import java.util.StringTokenizer;

/**
 * Project 2 for CSCI 212 at Queens College
 * Lecturer: Dr. Kenneth Lord
 * Lab Instructor: Nikola Baci
 * 
 * @author Nicholas Farkash
 */

public class Project2 {
	
	public static void main(String[] args) {
		
		/**
		 * Reads input from a file named input.txt located within the project file. The file contains
		 * some number of Roman Numerals per line, separated by commas. Each line gets tokenized, and
		 * each token is concatenated to the end of a string such that all tokens are separated by
		 * commas. This allows for a single tokenization that can be managed within a single for loop.
		 * 
		 * @param allRomanNums the string that will eventually contain all Numerals separated by commas
		 * @param in the input stream that allows contents of the file input.txt to be read
		 */
		TextFileInput in = new TextFileInput("input.txt"); // Read in input.txt
		String allRomanNums = "";	// Create an empty string that will eventually store all numerals
		StringTokenizer myTokens;		// Create a StringTokenizer
	
		String line = in.readLine();	// Read the first line of the text file
	
		while (line != null) {			// While loop to read lines until the last line has been read
	
			myTokens = new StringTokenizer(line, ",");	// Tokenize the line
	
			while(myTokens.countTokens() > 0) {		// Append all Tokens into the empty string, separated with a comma
				String str = myTokens.nextToken() + ",";
				allRomanNums+=str;		
			}
	
			line = in.readLine();			// Read the next line
	
		}
		
	    in.close();	// Close the file that was being read from
	
	    
	    
		/**
		 * All Roman Numeral strings that were contained in input.txt are now stored in allRomanNums, separated by commas.
		 * Now, allRomanNums will be tokenized and the tokens will be looped over. Each token will be placed in two lists.
		 * The first is a String list that will contain the Roman Numerals, as found in input.txt. The second is an integer
		 * list that contains the Arabic value for the Roman Numerals. They are added in a way the the i-th element of the
		 * string list corresponds to the i-th element of the integer list.
		 * 
		 *  @param romanNumeralList a string array to hold Roman Numerals
		 *  @param arabicValueList an int array to store integer values of corresponding Roman Numeral strings
		 */
	    StringTokenizer allTokens = new StringTokenizer(allRomanNums, ",");		// Tokenize the string with all numerals
	    
	    int lenList = allTokens.countTokens();
	    String[] romanNumeralList = new String[lenList];	// Create a string array with length equal to number of tokens
	    int[] arabicValueList = new int[lenList];	// Create a string array with length equal to number of tokens

	    // Separate the tokens and put them into their respective arrays
	    for (int i = 0; i < lenList; i++) {
	    	String next = allTokens.nextToken();
	    	romanNumeralList[i] = next;		// Store all numerals in String array romanNumeralList
	    	arabicValueList[i] =  RomanNumeral.valueOf(next);	// Store all values in integer array arabicValueList   
	    }
	

	    
	    /**
	     * We now have two lists, one with Roman Numeral Strings and one with Arabic integer values. When the results
	     * are displayed on the GUI, the string values will be displayed in the order they appear in input.txt, so no
	     * additional work is required. The integer values must be appended into the unsorted list such that they
	     * appear in the same order as its respective Roman Numeral. The integer values must also be added into the
	     * sorted list, so as to preserve increasing order
	     * 
	     * @param unsortedRomanNumeralList a linked list of integers whose order corresponds to that of the Roman Numerals
	     * found in input.txt
	     * @param sortedRomanNumeralList a linked list of integers whose order is increasing. Each node points to the node
	     * that contains the next largest integer value
	     */
	    
    	UnsortedRomanNumeralList unsortedRomanNumeralList = new UnsortedRomanNumeralList();	// Integer linked list of natural order
    	SortedRomanNumeralList sortedRomanNumeralList = new SortedRomanNumeralList();	// Integer linked list of increasing order

	    for(int i = 0; i < lenList; i++) {
	    	RomanNumeral nextNumeral = new RomanNumeral(romanNumeralList[i]);	// create a node that contains the next numeral's value
			unsortedRomanNumeralList.append(nextNumeral);	// append the value to the list, preserving natural order
			sortedRomanNumeralList.add(nextNumeral);	// add the value to the list, preserving increasing order
	    }
	    
	    
	    
	    /**
	     * The results are displayed on the GUI
	     * 
	     * @param gui the GUI on which the Roman Numerals, the corresponding Arabic values, and the ordered values are displayed on
	     */
	    RomanNumeralGUI gui = RomanNumeralGUI.initialize();	    // Create the GUI
	    gui.printtoRNGUI(romanNumeralList, unsortedRomanNumeralList, sortedRomanNumeralList); // Print to the GUI
	    gui.pack();	// Ensure that all text will be shown on the screen

	}
}
