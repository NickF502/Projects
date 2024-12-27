package p2;

import java.awt.*;
import javax.swing.*;

/**
 * A Roman Numeral GUI class used to display the results of the project. It contains an
 * initialization method to set up the GUI and a printtoRNGUI method that displays the results.
 * 
 *  @author Nicholas Farkash
 */


public class RomanNumeralGUI extends JFrame {	// RomanNumeralGUI inherits JFrame

	/** Initialize method which creates and formats the GUI. Most notable is the layout
	 * of the GUI, which is a grid layout of 1 row and 3 columns.
	 * 
	 * @param RNGUI the GUI that displays results
	 * @returns RNGUI
	 */
	
	public static RomanNumeralGUI initialize() {	
	    RomanNumeralGUI RNGUI = new RomanNumeralGUI();
	
	    RNGUI.setSize(400, 200);
	    RNGUI.setLocation(0, 0);
	    RNGUI.setTitle("Roman Numeral Linked Lists");
	    RNGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    RNGUI.setLayout(new GridLayout(1, 3));		// GUI is a grid layout with 1 row and 3 columns
	    RNGUI.setVisible(true);
	
	    return RNGUI;

	}
	
	/** A method to display the results of the project. It takes in 3 parameters, which are the 3 lists that are to be displayed
	 * 
	 * @param myContentPane the content pane upon which the text areas lie
	 * @param numeralStrings the text area that contains the Roman Numerals
	 * @param intUnsorted the text area that contains the integer values of the Roman Numeral shown on the same row
	 * @param intSorted the text area that contains the integer values of Roman Numerals in increasing order
	*/
	public void printtoRNGUI(String[] numeralStringList, UnsortedRomanNumeralList u, SortedRomanNumeralList s) {
	    
		Container myContentPane = this.getContentPane();	// Create a Content Pane
		TextArea numeralStrings = new TextArea();			// Create three text areas; one for the string numerals
		TextArea intUnsorted = new TextArea();				// One for the unsorted Arabic values
	    TextArea intSorted = new TextArea();				// And one for the sorted Arabic values
	
	    /** Add the three text areas to the Content Pane in the proper order. Text areas added first occupy the left-most spot of the grid layout */
	    myContentPane.add(numeralStrings);				
	    myContentPane.add(intUnsorted);
	    myContentPane.add(intSorted);
	    
	    /** Create "current nodes" for each of the linked lists. These nodes are the first node after the dummy
	     * head node; the first nodes which contain data. Doing this ensures that the print function will not 
	     * print a null node. After appending to all text areas, the "current nodes" are updated to the
	     * next node in the linked list, allowing for the loop to print all data
	     * 
	     * @param unsortNode node in the UnsortedRomanNumeralList that is being printed to the GUI
	     * @param sortNode node in the SortedRomanNumeralList that is being printed to the GUI
	     */
    	RomanNumeralListNode unsortNode = u.first.next;		// Set the current node of each linked list
    	RomanNumeralListNode sortNode = s.first.next;		// to be the first node that contains data

    	for (int i = 0; i < numeralStringList.length; i++) {		// For each element in each list...
	    	numeralStrings.append(numeralStringList[i] + "\n");		// append the Roman Numeral strings
	    	
	    	
	    	intUnsorted.append(unsortNode.data.toString() + "\n");	// append the unsorted Arabic values
	    	intSorted.append(sortNode.data.toString()+ "\n");		// append the sorted Arabic values
	    	
	    	unsortNode = unsortNode.next;							// update to the next node in
	    	sortNode = sortNode.next;								// both linked lists
	    }

	  }
	
}