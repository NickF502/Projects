package src;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



/**
 * @author Nicholas Farkash
 * Class for the header panel of the GameWindow
 */
public class HeaderPanel extends JPanel {

	/**
	 * Instance variables for the class
	 * 
	 * @param op An instance of the OptionsPanel class, contains some options for the user
	 * @param scoreboard An instance of the Scoreboard class, contains vital game information
	 * @param empty An empty JPanel that is used for spacing purposes
	 */
	private OptionsPanel op;
	Scoreboard scoreboard;
	private JPanel empty;
	
	
	
	/**
	 * Constructor for the HeaderPanel class
	 */
	public HeaderPanel(){
		
		// Set up the panel
		setBorder(new EmptyBorder(0, 0, 2, 0));	// Removes hover border
		setLayout(new GridLayout(1, 0));	// Set the layout of the panel to be a GridLayout with one row, so elements are added left to right
		setBackground(Color.black);
		setOpaque(true);
		
		// Create the empty panel on the left
		empty = new JPanel();
		empty.setVisible(false);
		
		// Create the scoreboard in the center
		scoreboard = new Scoreboard();
		
		// Create the Options Panel on the right
		op = new OptionsPanel();
		
		// Add the panels to HeaderPanel in proper order
		add(empty);
		add(scoreboard);
		add(op);
		
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

		op.setDark(isDark);
		scoreboard.setDark(isDark);
			
	}
	
}
