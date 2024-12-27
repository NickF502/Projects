package project4;

import java.awt.*;
import javax.swing.*;
/**
 * A Roman Numeral GUI class used to display the results of the project. It contains an
 * initialization method to set up the GUI and a printtoRNGUI method that displays the results.
 * 
 *  @author Nicholas Farkash
 */





public class RomanNumeralGUI extends JFrame {
				
	/**
	 * @param myContentPane Content Pane of the GUI
	 * @param numerals TextArea for the numeral strings
	 * @param values TextArea for the unsorted Arabic Values
	 * @param sorted TextArea for the sorted Arabic Values
	 * @param menuBar JMenuBar for the GUI
	 * @param counterGUI Counter for the number of elements displayed in the GUI
	 * 
	 */
	
	//Instance varaibles
	Container myContentPane = this.getContentPane();
	TextArea numerals = new TextArea();
	TextArea values = new TextArea();
	TextArea sorted = new TextArea();
    JMenuBar menuBar  = new JMenuBar();
	int counterGUI = 1;
	
	/** Constructor for the GUI*/
	public RomanNumeralGUI(String title) {	
	    setSize(100, 100);
	    setLocation(0, 0);
	    setTitle(title);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new GridLayout(1,3));
	    

	    /** Add the text areas to the Content Pane in the proper order. Text areas added first occupy the left-most spot of the grid layout */
	    myContentPane.add(numerals);				
	    myContentPane.add(values);
	    myContentPane.add(sorted);
	    
	    numerals.append("Roman Numeral Values:  \n------------------------------\n");			// append the Roman Numeral
		values.append("Unsorted Arabic Values:\n------------------------------\n");				// append the Arabic value 
		sorted.append("Sorted Arabic Values (Open Only):  \n------------------------------\n");	// append the Arabic value 

		/** Add the File and Convert Menus to the Menu Bar*/
	    createFileMenu();
	    createConvertMenu();
	    
    	this.pack();	    
	    setVisible(true);
	}
	
	
	/** Method to create the File Menu
	 * @param fileMenu The actual File Menu
	 * @param item Recycled variable to create elements of the menu
	 * @param fmh FileMenuHandler to handle actions
	 */
	
	private void createFileMenu() {
		JMenu fileMenu = new JMenu("File");
		JMenuItem item;
		FileMenuHandler fmh  = new FileMenuHandler(this);
		
		/**	Create the Open item, link it to the fmh, and add it to the menu, then add a separator */
		item = new JMenuItem("Open");
	    item.addActionListener( fmh );
	    fileMenu.add(item);

	    fileMenu.addSeparator();
	    
		/**	Create the Clear item, link it to the fmh, and add it to the menu, then add a separator */
	    item = new JMenuItem("Clear");
	    item.addActionListener(fmh);
	    fileMenu.add(item);
	    
	    fileMenu.addSeparator();
	    
		/**	Create the Quit item, link it to the fmh, and add it to the menu */
	    item = new JMenuItem("Quit");
	    item.addActionListener(fmh);
	    fileMenu.add(item);

	    /** Link the menuBar to this JFrame and add the fileMenu to the menuBar*/
	    setJMenuBar(menuBar);
	    menuBar.add(fileMenu);
	}
	
	
	/** Method to create the File Menu
	 * @param convertMenu The actual Convert Menu
	 * @param item Recycled variable to create elements of the menu
	 * @param cmh ConvertMenuHandler to handle actions
	 */
	private void createConvertMenu() {
		JMenuItem item;
		JMenu convertMenu = new JMenu("Convert");
		ConvertMenuHandler cmh  = new ConvertMenuHandler(this);
		
		/**	Create the Roman to Arabic item, link it to the fmh, and add it to the menu */
		item = new JMenuItem("Roman to Arabic");
	    item.addActionListener(cmh);
	    convertMenu.add(item);

	    /** Link the menuBar to this JFrame and add the convertMenu to the menuBar*/
	    setJMenuBar(menuBar);
	    menuBar.add(convertMenu);
	}
	
	/** A method to display the results to the GUI
	 * @param roman The Roman Numeral being displayed
	 * @param arabic The Arabic value of that numeral
	 * @param srtd The numeral in the sorted TreeMap being displayed
	 */
	public void printToGUI(String roman, String arabic, String srtd) {
		
		numerals.append(counterGUI + ".) " + roman + "\n");		// append the Roman Numeral
    	values.append(counterGUI + ".) " + arabic + "\n");		// append the Arabic value
    	sorted.append(counterGUI + ".) " + srtd + "\n");		// append the Arabic value 

    	counterGUI++;	//Increment the counterGUI variable for the next item
	  }
	

	
	/** A method to clear the GUI and the console. It sets the text in each area to its
	 * default state and sets the counterGUI variable back to 1. It then prints out 10 empty lines
	 * to the console to clear it
	 */
	public void clear() {
    	this.numerals.setText("Roman Numeral Values:  \n------------------------------\n");
    	this.values.setText("Unsorted Arabic Values:\n------------------------------\n");
    	this.sorted.setText("Sorted Arabic Values (Open Only):  \n------------------------------\n");
    	this.counterGUI = 1;
    	
    	for(int i=0; i<10; i++)
    		System.out.println();
    	
	}

}