package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



/**
 * @author Nicholas Farkash
 * A class for the panel that contains an instance of the ClickableBaseballButton class
 */
public class ClickableBaseballPanel extends JPanel {

	/*
	 * Constructor for the ClickableBaseballPanel class 
	 */
	public ClickableBaseballPanel() {
		
		// Set up the panel
		setBorder(new EmptyBorder(5, 5, 5, 5));					// Removes hover border and add 5px padding
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));		// Center the layout of the panel
		setBackground(Color.black);								// Set the background color of the panel to be black by default
				
		// Add an empty panel for spacing purposes
		JPanel empty = new JPanel();
		empty.setBorder(new EmptyBorder(0, 0, 0, 0));	// Removes hover border
		empty.setPreferredSize(new Dimension(1, 550));	// Set the size of this "spacer" panel
		empty.setOpaque(false);							// Make the panel transparent
		
		// Create an instance of a ClickableBaseballButton
		ClickableBaseballButton ballButton = new ClickableBaseballButton();
		
		// Add the empty panel and ballButton to the panel
		add(empty);
		add(ballButton);
		
	}
	
	
	
	/**
	 * A method to change the background color depening on whether the game
	 * is in Light Mode or Dark Mode
	 * 
	 * @param isDark A boolean that evaluates to true if the game is currently in
	 * dark mode
	 */
	public void setDark(boolean isDark) {
		if(isDark) setBackground(GameMain.darkGray);
		else setBackground(Color.WHITE);
	}
	
	

}
