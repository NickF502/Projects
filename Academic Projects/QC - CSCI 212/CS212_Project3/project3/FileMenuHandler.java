package project3;

import javax.swing.*;
import java.awt.event.*;
import java.util.StringTokenizer;


/** Class that handles the events of the File Menu */
public class FileMenuHandler implements ActionListener {
	JFrame jframe;
   
	
	/** Constructor for the class 
	 * @param jframe the JFrame of the GUI
	 */
	public FileMenuHandler (JFrame jf) {
		jframe = jf;
	}
	
	
	/** Method to determine which File event was selected */
	public void actionPerformed(ActionEvent event) {
		String menuName = event.getActionCommand();
		if (menuName.equals("Open")){
			((RomanNumeralGUI)jframe).clear();
			openFile();
		}	
		else if (menuName.equals("Quit"))
			System.exit(1);
		else if(menuName.equals("Clear"))
			((RomanNumeralGUI)jframe).clear();
	}
	
	
	/** Method to handle the Open option; reads in numerals from a file and displays the numeral strings,
	 *  the unsorted arabic values, and the sorted arabic values
	 */
	private void openFile() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		String filePath = chooser.getSelectedFile().getAbsolutePath();
		
		/**
		 * Reads input from a file named input.txt located within the project file. The file contains
		 * some number of Roman Numerals per line, separated by commas. Each line gets tokenized, and
		 * each token is concatenated to the end of a string such that all tokens are separated by
		 * commas. This allows for a single tokenization that can be managed within a single for loop.
		 * 
		 * @param allRomanNums the string that will eventually contain all Numerals separated by commas
		 * @param in the input stream that allows contents of the file input.txt to be read
		 */
    	TextFileInput in = new TextFileInput(filePath); // Read in selected file
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
	    int[] arabicList = new int[lenList];	 // Create an int array with length equal to number of tokens
	    
	    
	    /**
	     * Loop through all numerals, find the arabic value of each, and append those vaues into the
	     * integer array. If an invalid numeral is found for the first time, display a message saying so
	     */
    	boolean invalid = false;	// boolean to allow the error message to print only once
	    for (int i = 0; i < lenList; i++) {
	    	String next = allTokens.nextToken();
	    	romanNumeralList[i] = next;
	    	arabicList[i] = Project3.valueOf(next);
	    	
	    	if(!invalid && arabicList[i] == -1) {
				JOptionPane.showMessageDialog(null, "Invalid Roman Numerals have been filtered. Please see the console.");
				invalid = true;
			}
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
			
	    	if(Integer.parseInt(nextNumeral.getArabicValue()) != -1) {
		    	unsortedRomanNumeralList.append(nextNumeral);	// append the value to the list, preserving natural order
				sortedRomanNumeralList.add(nextNumeral);		// add the value to the list, preserving increasing order
	    	}
	    }
	    
	    
	    
	    /** Display the results to the GUI by looping through the elements of the String array and the two linked lists,
	     * the latter of which is done by updating the nodes on each loop
	     */
	    
	    RomanNumeralListNode unsortNode = unsortedRomanNumeralList.first.next;		// Set the current node of each linked list
    	RomanNumeralListNode sortNode = sortedRomanNumeralList.first.next;		// to be the first node that contains data
    	
	    for(int i=0; i<lenList; i++) {
	    	if(arabicList[i] != -1) {
	    		((RomanNumeralGUI)jframe).printToGUI(romanNumeralList[i],unsortNode.data.getArabicValue(), sortNode.data.getArabicValue());
	    	 	unsortNode = unsortNode.next;	// Update the two nodes
	        	sortNode = sortNode.next;
	    	}

	    }
	
	}
}