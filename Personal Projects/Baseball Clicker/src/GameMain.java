package src;

import java.awt.Color;

import java.io.FileWriter;
import java.io.IOException;

import java.text.DecimalFormat;

import java.util.HashMap;



/**
 * @author Nicholas Farkash
 * The main class for the Baseball Clicker Game
 */
public class GameMain {

	/**
	 * Instance variables for the class
	 * 
	 * @param numBaseballs The number of baseballs the player currently has
	 * @param ballsPerClick The number of baseballs the player gains each time they click the baseball (BPS)
	 * @param currentBPS The number of baseballs the player gains every second
	 * 
	 * @param equiptmentButtonArrayList An ArrayList containing the EquipmentButton objects used in the game
	 * @param upgradeArrayList An ArrayList containing the UpgradeButton objects used in the game
	 * @param BPSMileStones An ArrayList containing the BPS requirements to unlock the next level of BPC
	 * @param numberSuffixHM A HashMap that takes an integer value (power of 1000) and converts it to a
	 * 							suffix to shorten the number displayed (Thousand is K, Million is M, etc.)
	 *
	 * @param gameWin An instance of the GameWindow class; essentially the game itself
	 */
	
	static double numBaseballs = 0L;
	static double ballsPerClick = 1L;
	static double currentBPS = 0L;
	
	static EquipmentButton[] equiptmentButtonArrayList = new EquipmentButton[7];
	static UpgradeButton[] upgradeArrayList = new UpgradeButton[7];
	static Long[] BPSMilestones = {
			1000L, 10000L, 100000L, 1000000L, 10000000L, 100000000L, 1000000000L, 10000000000L, 100000000000L, 1000000000000L, 10000000000000L, 100000000000000L 
	};
	static int BPSUpgrades = 0;
	
	static HashMap<Integer, String> numberSuffixHM = new HashMap<Integer, String>(){{
		put(0, "");
		put(1, "K");
		put(2, "M");
		put(3, "B");
		put(4, "T");
		put(5, "Qd");
		put(6, "Qt");
		put(7, "Sx");
		put(8, "Sp");
		put(9, "O");
		put(10, "N");
		put(11, "D");
		put(12, "UD");
		put(13, "DD");
		put(14, "Td");
		put(15, "QdD");
		put(16, "QtD");
		put(17, "SxD");
		put(18, "SpD");
		put(19, "OD");
		put(20, "ND");
		put(21, "V");
		put(22, "UV");
		put(23, "DV");
		put(24, "TV");
		put(25, "QdV");
		put(26, "QtV");
		put(27, "SxV");
		put(28, "SpV");
		put(29, "OV");
		put(10, "NV");
		put(11, "???");
	}};

	protected static GameWindow gameWin;

	// Store some commonly used colors
	static Color lightGray = Color.decode("#d8d8d8");
	static Color medGray = Color.decode("#878787");
	static Color darkGray = Color.decode("#1e1e1e");
	static Color richBlack = Color.decode("#010203");
	static Color buttonGray = Color.decode("#f8f8f8");
	
	
	/*
	 * Main Method
	 */
	public static void main(String[] args){
		System.out.println("\u200B");

		// Try to start a new game
		try {
			gameWin = new GameWindow();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new GameStartException();
		}		

		// While loop for game
		while(gameWin.isVisible()) {
				
			// This is a clock that adds the current BPS. It scales depending on what the BPS is.
			// The higher the BPS, the faster this clock runs.
			try {
				if(currentBPS < 10) {
					Thread.sleep(1000);
					numBaseballs += currentBPS;					
				}
				else if(currentBPS < 100) {
					Thread.sleep(100);
					numBaseballs += currentBPS/10;		
				}
				else {
					Thread.sleep(10);
					numBaseballs += currentBPS/100;		
				}
            	
				// Update the scoreboard
	            GameWindow.header.scoreboard.updateScoreDisplay();
	            
	            // Check to see what purchases are available and set those that are purchasable
	    		for(EquipmentButton button : equiptmentButtonArrayList) {
	    			if(button.cost <= numBaseballs) button.setPurchasable(true);
	    			else button.setPurchasable(false);
	    		}
	    		
	    		for(UpgradeButton button : upgradeArrayList) {
	    			if(button.cost <= numBaseballs) button.setPurchasable(true);
	    			else button.setPurchasable(false);
	    		}
	            
            }
			catch (InterruptedException e) {
                e.printStackTrace();
            }
            
		}
		
		// When the window is closed, exit out of the program.
		System.exit(0);
			
	}
	
	
	
	/**
	 * Method to start a new game by resetting everything to their default states
	 */
	public static void newGame() {
		// Reset the save Data
		try {

			// Create a FileWriter to overwrite saveData.txt
			FileWriter toNewFile = new FileWriter("saveData.txt");			
			
			// Rewrite the Data to be all zeros, separated by the Zero-Width space character
			toNewFile.write("4568016​0​0​0​0​0​0​0​0​0​0​0​0​0​0​0");

			// Close the FileWriter
		    toNewFile.close();
		    
		}
		catch (IOException e) {
			e.printStackTrace();
		}
				

		// Reset the game's basic variables
		numBaseballs = 0L;
		ballsPerClick = 1L;
		currentBPS = 0L;
		
		// Update the scoreboard
        GameWindow.header.scoreboard.updateScoreDisplay();

		//	Reset all Equiptment and Upgrade Buttons
		EquipmentButton[] eqPanel = equiptmentButtonArrayList;
		for(EquipmentButton button : eqPanel)
			button.resetButton();
		
		UpgradeButton[] upPanel = upgradeArrayList;
		for(UpgradeButton button : upPanel)
			button.resetButton();
		

	}
        
	
	/*
	 * Method to format a number in a way that adds a "power of thousand" suffix
	 * For example, 1,234 would be written as 1.234 K
	 */
	public static String formatNumber(double number, boolean isBall) {        	
		
		if(number < 1000) return Double.toString(number);
		
		// Calculate the number of digits the number has
    	int powerOfThousand = 0;
    	double ghostNumber = number;
    	
    	while(ghostNumber > 1000) {
    		ghostNumber /= 1000;
    		powerOfThousand++;
    	}
    	
    	// Use the power of 1000 to get the corresponding suffix from the hashmap
        String suffix = numberSuffixHM.get(powerOfThousand);
        
        // Scale the number accordingly to make the formatting fit
        double scaledNumber = (double) number / Math.pow(10, (powerOfThousand)*3);

        // Format the number and return it
        DecimalFormat formatter;
        if(isBall) formatter = new DecimalFormat("#.000");
        else formatter = new DecimalFormat("#.###");
        
        return formatter.format(scaledNumber) + suffix;
	}
	
	
	
	/*
	 * Method to multiply the Balls per Click by 10 when a new milestone is reached
	 */
	public static void updateBPC() {
		ballsPerClick *= 10;
		BPSUpgrades++;
	}
	

	
}
