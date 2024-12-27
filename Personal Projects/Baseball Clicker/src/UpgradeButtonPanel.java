package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;


import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



/**
 * @author Nicholas Farkash
 * A class for the panel that contains the upgrade buttons
 */
public class UpgradeButtonPanel extends JPanel{
	
	/**
	 * Instance variables for the class
	 * 
	 * @param upgradeButtonArrayList ArrayList that stores all of the buttons that will be placed on the panel
	 */
	static UpgradeButton[] upgradeButtonArrayList = GameMain.upgradeArrayList;
	
	
	
	/**
	 * Constructor for the UpgradeButtonPanel class
	 */
	public UpgradeButtonPanel() {
		
		// Set up the panel
		setBorder(new EmptyBorder(0, 0, 0, 0));		// Removes hover border
		setLayout(new GridLayout(0,1));				// Establish a GridLayout for the panel with one column so buttons are added top to bottom
		setPreferredSize(new Dimension(380, 600));	// Set the preferred size of the panel
				
		// Create Buttons for each item and add them to upgradeButtonArrayList
		UpgradeButton bubblegum = new UpgradeButton("Bubble Gum", 1000L);
		upgradeButtonArrayList[0] = bubblegum;

		UpgradeButton sportsdrink = new UpgradeButton("Sports Drink", 11000L);
		upgradeButtonArrayList[1] = sportsdrink;

		UpgradeButton shinguard = new UpgradeButton("Shin Guard", 120000L);
		upgradeButtonArrayList[2] = shinguard;

		UpgradeButton battinggloves = new UpgradeButton("Batting Gloves", 1300000L);
		upgradeButtonArrayList[3] = battinggloves;

		UpgradeButton helmet = new UpgradeButton("Helmet", 14000000L);
		upgradeButtonArrayList[4] = helmet;

		UpgradeButton bat = new UpgradeButton("Bat", 200000000L);
		upgradeButtonArrayList[5] = bat;

		UpgradeButton batweight = new UpgradeButton("Bat Weight", 3300000000L);
		upgradeButtonArrayList[6] = batweight;

		// Add each UpgradeButton to the panel
		for(UpgradeButton b : upgradeButtonArrayList)
			add(b);
	
	}

	
	
	/**
	 * Method to update all buttons on the panel
	 */
	public void updateInfo() {
		for(UpgradeButton b : upgradeButtonArrayList)
			b.updateButton();
	}
	
	
	
	/**
	 * A method to change the background color depening on whether the game
	 * is in Light Mode or Dark Mode
	 * 
	 * @param isDark A boolean that evaluates to true if the game is currently in
	 * dark mode
	 */
	public void setDark(boolean isDark) {
		if(isDark) setBackground(GameMain.richBlack);
		else setBackground(Color.WHITE);
		
		for(UpgradeButton b : upgradeButtonArrayList)
			b.setDark(isDark);
		
	}

	

}
