package project4;

import javax.swing.*;
import java.awt.event.*;

/** Class that handles the events of the Convert Menu */

public class ConvertMenuHandler implements ActionListener {
	JFrame jframe;
   
	
	/** Constructor for the class 
	 * @param jframe the JFrame of the GUI
	 */
	public ConvertMenuHandler (JFrame jf) {
		jframe = jf;
	}
	
	
	/** Method to determine which File event was selected */
	public void actionPerformed(ActionEvent event) {

		String menuName = event.getActionCommand();
		if (menuName.equals("Roman to Arabic")) {
			((RomanNumeralGUI)jframe).clear();
			RomanToArabic();
		}
		
	}
	
	
	/** Method to handle the Roman to Arabic option; reads in a user-input string, converts the numeral to
	 * its Arabic value and displays the Arabic value to the user. It then prints the results to the GUI
	 */
	private void RomanToArabic() {	

		/** Prompt the user for a numeral string*/
		String numeralString = JOptionPane.showInputDialog(null,"Enter a Roman Numeral to convert to its Arabic counterpart:");
		
		/** Determine the value of the numeral. If it is valid, display it. Otherwise, display an error message */
		int arabicValue = RomanNumeral.valueOf(numeralString);
		if(arabicValue != -1) {
			JOptionPane.showMessageDialog(null, "Roman Numeral \"" + numeralString + "\" represents the Arabic value \"" + arabicValue + "\"");
			String aV = Integer.toString(arabicValue);
			((RomanNumeralGUI)jframe).printToGUI(numeralString, aV, aV);
		}
		else {
			JOptionPane.showMessageDialog(null, "Roman Numeral \"" + numeralString + "\" is an invalid Roman Numeral");
		}
		

	}
	
}