package src;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



/**
 * @author Nicholas Farkash
 * A class for the panel that contains the OptionsButton 
 */
public class OptionsPanel extends JPanel{

	/**
	 * Instance variables for the class
	 * 
	 * @param ob An instance of the OptionsButton class
	 */
	private OptionsButton ob;
	
	
	
	/*
	 * Constructor for the OptionsPanel class
	 */
	public OptionsPanel() {
		
		// Set up the main panel
		setBorder(new EmptyBorder(0, 0, 0, 0));	// Removes hover border
		setLayout(new BorderLayout());	// Establish a BorderLayout for the panel
		setBackground(Color.black);
		setOpaque(true);
		
		ob = new OptionsButton();
		add(ob, BorderLayout.WEST);
		
	}
	

	
	/**
	 * A method to change the background color depening on whether the game
	 * is in Light Mode or Dark Mode.
	 * 
	 * @param isDark A boolean that evaluates to true if the game is currently in
	 * dark mode
	 */
	public void setDark(boolean isDark) {
		
		if(isDark) setBackground(GameMain.richBlack);
		else setBackground(GameMain.lightGray);

		ob.setDark(isDark);
		
	}
	
	
	
}
