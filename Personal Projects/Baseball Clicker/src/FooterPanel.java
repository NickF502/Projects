package src;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



/**
 * @author Nicholas Farkash
 * A class that displays the information at the bottom of the window on a panel
 */
public class FooterPanel extends JPanel {

	/**
	 * Instance variables for the class
	 * 
	 * @param titleTA JLabel that displays the name of the game
	 * @param websiteTA Jlabel that displays my website URL
	 * @param websiteLink String that contains my website URL
	 */
	private static JLabel titleTA;
	private static JLabel websiteTA;
	private final String websiteLink = "https://nickf502.github.io/";

	
	
	/**
	 * Constructor for the FooterPanel class
	 */
	public FooterPanel(){
		
		// Set up the panel
		setLayout(new BorderLayout());			// Establish a BorderLayout for the panel
		setBorder(new EmptyBorder(5, 5, 5, 5));	// Removes hover border and add 5px padding
		
		// Create the labels
		titleTA = new JLabel();
		websiteTA = new JLabel();
		
		// Set the font for the labels
		titleTA.setFont(new Font("Dialog", Font.PLAIN, 36));
		websiteTA.setFont(new Font("Dialog", Font.PLAIN, 24));
		
		// Set the text for the labels
		titleTA.setText("Baseball Clicker by Nicholas Farkash");
		websiteTA.setText("Visit my website at " + websiteLink);
		
		// Add the labels
		add(titleTA, BorderLayout.WEST);
		add(websiteTA, BorderLayout.EAST);
		
	}
	
	
	
	/**
	 * A method to change the background color depening on whether the game
	 * is in Light Mode or Dark Mode
	 * 
	 * @param isDark A boolean that evaluates to true if the game is currently in
	 * dark mode
	 */
	public void setDark(boolean isDark) {
		
		if(isDark) {
			setBackground(GameMain.richBlack);
			titleTA.setForeground(GameMain.medGray);
			websiteTA.setForeground(GameMain.medGray);
		}
		else{
			setBackground(GameMain.lightGray);
			titleTA.setForeground(GameMain.darkGray);
			websiteTA.setForeground(GameMain.darkGray);
		}
		
	}
	
}
