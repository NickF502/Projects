package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



/**
 * @author Nicholas Farkash
 * A class for the panel that contains the equipment buttons
 */
public class EquipmentButtonPanel extends JPanel{
	
	/**
	 * Instance variables for the class
	 * 
	 * @param equiptmentButtonArrayList ArrayList that stores all of the buttons that will be placed on the panel
	 */
	static EquipmentButton[] equiptmentButtonArrayList = GameMain.equiptmentButtonArrayList;

	
	
	/**
	 * Constructor for the EquiptmentButtonPanel class
	 */
	public EquipmentButtonPanel() {
		
		// Set up the panel
		setBorder(new EmptyBorder(0, 0, 0, 0));		// Removes hover border
		setLayout(new GridLayout(0,1));				// Establish a GridLayout for the panel with one column so buttons are added top to bottom
		setPreferredSize(new Dimension(380, 600));	// Set the preferred size of the panel
		
		// Create Buttons for each item and add them to equiptmentButtonArrayList
		EquipmentButton bubblegum = new EquipmentButton("Bubble Gum", 100L, 1L);
		equiptmentButtonArrayList[0] = bubblegum;

		EquipmentButton sportsdrink = new EquipmentButton("Sports Drink", 1100L, 10L);
		equiptmentButtonArrayList[1] = sportsdrink;

		EquipmentButton shinguard = new EquipmentButton("Shin Guard", 12000L, 50L);
		equiptmentButtonArrayList[2] = shinguard;

		EquipmentButton battinggloves = new EquipmentButton("Batting Gloves", 130000L, 275L);
		equiptmentButtonArrayList[3] = battinggloves;

		EquipmentButton helmet = new EquipmentButton("Helmet", 1500000L, 1250L);
		equiptmentButtonArrayList[4] = helmet;

		EquipmentButton bat = new EquipmentButton("Bat", 20000000L, 8000L);
		equiptmentButtonArrayList[5] = bat;
		
		EquipmentButton batweight = new EquipmentButton("Bat Weight", 750000000L, 45000L);
		equiptmentButtonArrayList[6] = batweight;
		
		// Add each EquiptmentButton to the panel
		for(EquipmentButton b : equiptmentButtonArrayList)
			add(b);
			
	}

	
	
	/**
	 * Method to update all buttons on the panel
	 */
	public void updateInfo() {
		for(EquipmentButton b : equiptmentButtonArrayList)
			b.formatButton();
	}
	
	
	
	/**
	 * Retrieve a specific button from the panel
	 * 
	 * @param name The name of the EquipmentButton being searched for
	 * @return The corresponding EquipmentButton, or null if it is not found
	 */
	public EquipmentButton getButton(String name) {

		for(EquipmentButton b : equiptmentButtonArrayList)
			if(b.name.equals(name)) return b;
		
		return null;
		
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
		
		for(EquipmentButton b : equiptmentButtonArrayList)
			b.setDark(isDark);
		
	}

	
	
}
