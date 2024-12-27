package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 * @author Nicholas Farkash
 * A class containing the game window for the game and all of its features
 */
public class GameWindow extends JFrame{
	
	/**
	 * Instance variables for the class
	 * 
	 * @param saveGameData String ArrayList containing the game's save data
	 * @param isDark Boolean that determines whether the window is in Dark Mode or not
	 * @param mainContainer A container that spans the entire window. Everything is added to this
	 * 
	 * @param header The header panel of the window (North)
	 * @param upgradeButtonPanel The panel containing the upgrade buttons (West)
	 * @param ballPanel The panel containing the baseball (Center)
	 * @param equipButtonPanel The panel containing the equipment buttons (East)
	 * @param footer The footer panel of the window (South)
	 */
	static ArrayList<String> saveGameData = new ArrayList<String>();
	static boolean isDark = false;
	static JPanel mainContainer = new JPanel();
	
	static HeaderPanel header = new HeaderPanel();
	static UpgradeButtonPanel upgradeButtonPanel = new UpgradeButtonPanel();
	static ClickableBaseballPanel ballPanel = new ClickableBaseballPanel();
	static EquipmentButtonPanel equipButtonPanel = new EquipmentButtonPanel();
	static FooterPanel footer = new FooterPanel();
	

	
	/* Constructor for GameWindow */
	public GameWindow() {
		
		// Set up the window
		setTitle("Baseball Clicker by NickF502");
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen the window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		changeColorMode();
		setVisible(true);	
		
		// Set up the layout of the window
		mainContainer.setLayout(new BorderLayout());
		mainContainer.setBackground(Color.decode("#d8d8d8"));
		
		mainContainer.add(header, BorderLayout.NORTH);
		mainContainer.add(upgradeButtonPanel, BorderLayout.WEST);
		mainContainer.add(ballPanel, BorderLayout.CENTER);
		mainContainer.add(equipButtonPanel, BorderLayout.EAST);
		mainContainer.add(footer, BorderLayout.SOUTH);
		add(mainContainer);

		// Load the game's saved data
		loadGame();

		// Override the window closing event to allow the game to be saved prior to closing
		addWindowListener(new WindowAdapter() {
            
			@Override
            public void windowClosing(WindowEvent e) {
                saveGame(); // Call your saveGame() function when the window is closed
            }
			
        });
		
	}
	
	

	/*
	 * A method to save the game. A very basic form of encryption is utilized in doing so. The original Cookie Clicker game was easily cheated by changing
	 * the numbers in the save data. I wanted to avoid this. The encryption is not complex, so someone could still cheat the game, but it would require some
	 * effot. A random key is generated and multiplied to each element in the ArrayList, and the key is stored in the same text document as the code for 
	 * decryption
	 */
	public static void saveGame() {

		DecimalFormat decimalFormat = new DecimalFormat("#");
		String delimiter = "\u200B";  // Zero Width Space character

		// Generate two numbers and multiply them together
		Long randomKey1 = (long) Math.floor(Math.random() * 10000);
		Long randomKey2 = (long) Math.floor(Math.random() * 10000);
		Long randomKey = randomKey1*randomKey2;
		
		// Add the key to the ArrayList (Index 0)
		saveGameData.add(decimalFormat.format(randomKey));
		
		// Add the number of baseballs to the ArrayList	(Index 1)
		saveGameData.add(decimalFormat.format(GameMain.numBaseballs * randomKey));
		
		// Acquire the number of each equiptment item and its current cost, then add them to the ArrayList
		EquipmentButton[] eqPanel = GameMain.equiptmentButtonArrayList;
		for(EquipmentButton button : eqPanel)
			saveGameData.add(decimalFormat.format(button.amount * randomKey));
		
		// Add the upgrade level of each piece of equiptment
		UpgradeButton[] upPanel = GameMain.upgradeArrayList;
		for(UpgradeButton button : upPanel)
			saveGameData.add(decimalFormat.format(button.timesUpgraded * randomKey));
		
		// For every element in the saveGameData ArrayList, write it to saveData.txt
		try {
			
			// Create a FileWriter to write to saveData.txt
			FileWriter toNewFile = new FileWriter("saveData.txt");			

			// Write each element in the ArrayList to saveData.txt, separated by the delimiter
			for(String a : saveGameData)
				toNewFile.write(a + delimiter);

			// Close the FileWriter and inform the user of success
		    toNewFile.close();
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
	/*
	 * A method to take saved data and load it into the game
	 */
	private void loadGame() {
		
		// Read in the string of characters stored in saveDate.txt
		String totalInput = "";

	    try {
	    	Scanner myInput = new Scanner(new File("saveData.txt"));
		   
	    	while (myInput.hasNextLine()) {
		         totalInput += myInput.nextLine();
		    }
	       
		    myInput.close();
	       
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }

	    // Split the totalInput string around the zero-width space character that was used to delimit it, and store each string in an index of an ArrayList
		String[] ld = totalInput.split("\u200B");		
		ArrayList<Long> loadedData = new ArrayList<Long>();
		
	    for(String s : ld)
	    	loadedData.add(Long.parseLong(s));

	    // Use the key stored in the first spot of the loadedData ArrayList to decode the other spots
	    Long key = loadedData.get(0);

	    for(int i = 0; i<loadedData.size(); i++) {
	    	Long realNum = loadedData.get(i) / key; 
	    	loadedData.set(i, realNum);	    	
	    }

	    // All decoded information is now stored in loadedData. Set the respective variables to the correct amounts
	    int spotInArrayList = 1;
	    GameMain.numBaseballs = loadedData.get(spotInArrayList++);	// Set the number of baseballs
	    
	    for(EquipmentButton b : EquipmentButtonPanel.equiptmentButtonArrayList)
	    	GameMain.currentBPS += b.loadButton(loadedData.get(spotInArrayList++));	// Set up the equipment buttons and set the Baseballs per Second

	    for(UpgradeButton b : UpgradeButtonPanel.upgradeButtonArrayList)
	    	b.setButton(loadedData.get(spotInArrayList++));	// Set up the upgrade buttons

	}
	    
	
	
	/*
	 * Method that controlls all other setDark methods in other classes. It flips the value of the boolean and then calls the other functions
	 */
    public static void changeColorMode() {
		
		// Flip the boolean value of isDark
		isDark = !isDark;
		
		// Call the other setDark functions
		GameWindow.header.setDark(isDark);
		GameWindow.upgradeButtonPanel.setDark(isDark);
		GameWindow.ballPanel.setDark(isDark);
		GameWindow.equipButtonPanel.setDark(isDark);
		GameWindow.footer.setDark(isDark);

    }

    
	
}
