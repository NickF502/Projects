package src;

import java.awt.GridLayout;

import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;



/**
 * @author Nicholas Farkash
 * A class for the PopUpOptionsPanel that appears when the Options Button is clicked
 */
public class PopUpOptionsPanel extends JPopupMenu {

	/**
	 * Instance variables for the class
	 * 
	 * @param newGameButton An instance of the NewGameButton class, which starts a new game when clicked
	 * @param lightdark An instance of the LightDarkButton class, which toggles the dark mode feature when clicked
	 */
	private NewGameButton newGameButton;
	private LightDarkButton lightdark;
	
	
	
	/*
	 * Constructor for the PopUpOptionsPanel class
	 */
	public PopUpOptionsPanel() {
		
		// Set up the panel
		setLayout(new GridLayout(0, 1));	// Set the layout of the panel to be a GridLayout with one column, so elements are added top to bottom
		setBorder(new EmptyBorder(5, 5, 5, 5));	// Removes hover border and adds 5px padding
		setBackground(GameMain.lightGray);
		setOpaque(true);
		setFocusable(false);
		
		// Create "New Game" Button and add it to the panel
		newGameButton = new NewGameButton();
		add(newGameButton);
		
		// Create "Light Dark Button" Button and add it to the panel
		lightdark = new LightDarkButton();
		add(lightdark);
	
	}
	
	
	
	/**
	 * A method to change the background color depening on whether the game
	 * is in Light Mode or Dark Mode.
	 * 
	 * @param isDark A boolean that evaluates to true if the game is currently in
	 * dark mode
	 */
	public void setDark(boolean isDark) {
		
		if(isDark)setBackground(GameMain.richBlack);
		else setBackground(GameMain.lightGray);
		
		newGameButton.setDark(isDark);	// Change the NewGameButton's color		
		lightdark.setDark(isDark);	// Change the LightDarkButton's color
		
	}
	
	
	
}
