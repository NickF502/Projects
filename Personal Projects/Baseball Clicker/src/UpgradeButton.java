package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;



/**
 * @author Nicholas Farkash
 * A class for a button that allows the user to upgrade equipment items
 */
public class UpgradeButton extends JButton {

	/**
	 * Instance variables for the class
	 * 
	 * @param baseCost The price to purchase the upgrade for the first time
	 * @param name The name of the equipment this button is linked to
	 * @param cost The current cost of this upgrade
	 * @param effect A string that displays what the upgrade does
	 * @param isUnlocked A boolean that states whether the upgrade is able to be purchased or not
	 * @param timesUpgraded The number of times the equipment has been upgraded
	 * 
	 * @param upgradeRequirements An ArrayList that stores the integer number of equipments needed to unlock the next level of upgrade
	 */
	final Long baseCost;
	final String name;
	Long cost;
	final String effect = "x2 BPS";
	boolean isUnlocked = false;
	int timesUpgraded = 0;
	
	Integer[] upgradeRequirements = {1, 5, 25, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, -1};
	
	
	
	/**
	 * Constructor for the EquipmentButton class
	 * 
	 * @param item The name of the equipment the upgrade button is linked to
	 * @param price The initial cost of the item
	 */
	public UpgradeButton(String item, Long price) {

    	// Set the instance variables using the constructor's parameters
		baseCost = cost = price;
    	name = item;
    	
        // Set Button Characteristics
    	setBackground(GameMain.buttonGray);	// Set the color of the button
    	setHorizontalAlignment(SwingConstants.LEFT);			// Align the button's content to the left side
        setFocusable(false);									// Remove the selection border
        setFont(new Font("Courier New Bold", Font.BOLD, 20));	// Set the font of the button
        formatButtonText(); 									// Update the Button		

		//Mouse Listener for the EquiptmentButton
		addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            	
            	// If the player has enough baseballs to purchase the upgrade, there is another upgrade to unlock, and the next upgrade is available
            	 if (GameMain.numBaseballs >= cost && upgradeRequirements[timesUpgraded] != -1 && isUnlocked) {
                     
            		 // Subtract the cost of the upgrade
            		 GameMain.numBaseballs -= cost;
                    
                     // Do the upgrade
            		 GameWindow.equipButtonPanel.getButton(name).doubleBPS();		// Double that item's BPS
                     GameMain.currentBPS += (GameWindow.equipButtonPanel.getButton(name).amount
                    		 * GameWindow.equipButtonPanel.getButton(name).bps); 	// Update the game BPS
                     GameWindow.equipButtonPanel.getButton(name).formatButton();	// Update that button's text
                     GameWindow.header.scoreboard.updateScoreDisplay(); 			// Update the Scoreboard text

                     // Update the upgrade button's information
                     timesUpgraded++;				// Increment number of times upgraded
                     cost *= 101;					// Increase the cost of the next upgrade
                     updateButton();				// Update the button
                     
                     // Update panels
                     GameWindow.equipButtonPanel.updateInfo();
                     GameWindow.upgradeButtonPanel.updateInfo();
                     GameWindow.header.scoreboard.updateScoreDisplay();
                     
                     // If a new BPS milestone has been reached, update the current BPC
                     if(GameMain.currentBPS >= GameMain.BPSMilestones[timesUpgraded])
                    	 GameMain.updateBPC();
                     
                 }

            }

            public void mouseReleased(MouseEvent e) {}

            public void mousePressed(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {}

            public void mouseExited(MouseEvent e) {}
            
        });
		
    }
    

	
	/**
     * Method to update the button's text 
     */
    private void formatButtonText() {
    	
    	if(isUnlocked) {
	        String textCost = GameMain.formatNumber(cost, false);
	        setText("<html>Upgrade " + name + "<br /> Cost: " + textCost + "<br /> Effect: " + effect + "<html>");
    	}
    	else {
	        setText("<html>Upgrade " + name + "<br /> Cost: ???<br /> Effect: ???<html>");

    	}
    	
    }
    
    
    
    /*
     * Method to check if a new upgrade should be made available
     */
    void updateButton() {
    	
    	// If a milestone amount of equipment has been reached, enable the button
    	if(GameWindow.equipButtonPanel.getButton(name).amount >= upgradeRequirements[timesUpgraded]) isUnlocked = true;
    	else isUnlocked = false;
    	
    	formatButtonText();
    	
    }

    
    
    /*	
     * Method to reset the Button when a new game is started
     */
    public void resetButton() {
    	
    	// Reset the upgradeRequirements ArrayList, upgrade counter, and lock the upgrade
    	timesUpgraded = 0;
    	isUnlocked = false;
    	
    	updateButton();
    }

    
    
    /**
     * Method to set the button up on load
     * @param numUpgrades Number of times the equipment was upgraded
     */
    public void setButton(long numUpgrades) {

    	timesUpgraded = (int)numUpgrades;	// Set the timesUpgraded variable after loading in data
    	
    	// Upgrade the equipment the appropriate number of times 
    	for(int i=0; i<numUpgrades; i++) {

    		timesUpgraded++;
    		
			GameMain.currentBPS += (GameWindow.equipButtonPanel.getButton(name).amount * GameWindow.equipButtonPanel.getButton(name).bps); // Update the game BPS
			GameWindow.equipButtonPanel.getButton(name).doubleBPS();	// Double that item's BPS
			GameWindow.equipButtonPanel.getButton(name).formatButton();	// Update that button's text
			GameWindow.header.scoreboard.updateScoreDisplay(); // Update the Scoreboard text

    	}
    	
		updateButton();

    }
    
    
    
    /**
	 * A method to change the background color depening on whether the game
	 * is in Light Mode or Dark Mode
	 * 
	 * @param isDark A boolean that evaluates to true if the game is currently in
	 * dark mode
	 */
    public void setDark(boolean isDark) {
    	if(isDark) setBackground(GameMain.medGray);
		else setBackground(GameMain.buttonGray);
    }
    
    
    
    /**
     * Changes the color of the button depending on if purchase can be made
     * 
     * @param isPurchasable Evaluates to true if the button is Purchasable
     */
    public void setPurchasable(boolean a) {
    	if(a && isUnlocked) setBackground(Color.yellow);
    	else setDark(GameWindow.isDark);
    }
    
    
    
}
