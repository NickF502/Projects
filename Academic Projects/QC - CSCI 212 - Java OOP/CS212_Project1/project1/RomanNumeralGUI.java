package project1;

import java.awt.*;
import javax.swing.*;

public class RomanNumeralGUI extends JFrame {	// RomanNumeralGUI inherits JFrame

	// Initialize method creates and sets up the GUI
	public static RomanNumeralGUI initialize() {	
	    RomanNumeralGUI RNGUI = new RomanNumeralGUI();
	
	    RNGUI.setSize(400, 200);
	    RNGUI.setLocation(100, 100);
	    RNGUI.setTitle("Roman Numeral Conversion");
	    RNGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    RNGUI.setLayout(new GridLayout(1, 2));		// GUI is a grid layout with 1 row and 2 columns
	
	    RNGUI.setVisible(true);
	
	    return RNGUI;

	}
	
	
	
	// Method to display words
	public static void printtoRNGUI(RomanNumeralGUI gui, String[] listRoman, String[] listArabic, int size) {
	    
		Container myContentPane = gui.getContentPane();	// Create a Content Pane
	    TextArea arabic = new TextArea();				// Create two text areas;
	    TextArea roman = new TextArea();				// One for Roman numerals and one for Arabic numerals
	
	    myContentPane.add(roman);				// Add the two text areas to the Content Pane
	    myContentPane.add(arabic);
	
	    for (int i = 0; i < size; i++) {			// Go through the loop and...
	        arabic.append(listArabic[i] + "\n");	// append the Arabic/Roman numerals
	    	roman.append(listRoman[i] + "\n");		// to their respective text areas
	    }

	    gui.pack();		// Ensure that all text will be shown on the screen
  }

}
