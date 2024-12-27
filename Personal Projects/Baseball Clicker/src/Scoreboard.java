package src;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


/**
 * @author Nicholas Farkash
 * A class for the scoreboard that displays the game's information
 */
public class Scoreboard extends JPanel{

	/**
	 * Instance variables for the class
	 * 
	 * @param numBaseballLabel JLabel that displays the number of baseballs
	 * @param currentBPSLabel JLabel that displays the current BPS
	 */
	private JLabel numBaseballLabel = new JLabel();
	private JLabel currentBPSLabel = new JLabel();
	
	
	
	/*
	 * Constructor for the Scoreboard class
	 */
	public Scoreboard() {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(5, 5, 5, 5));	// Removes hover border and adds 5px padding
		setBackground(GameMain.lightGray);
		setOpaque(true);
		setVisible(true);
		
		// Set the text on the two labels and add them to the scoreboard
		numBaseballLabel.setFont(new Font("Dialog", Font.PLAIN, 36));
		numBaseballLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(numBaseballLabel, BorderLayout.NORTH);

		currentBPSLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
		currentBPSLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(currentBPSLabel, BorderLayout.SOUTH);

		// Update the scoreboard's display
		updateScoreDisplay();
		
	}
	
	
	
	public void updateScoreDisplay() {
		
		// Obtain strings of all required numbers in their proper format
		String numB = GameMain.formatNumber(GameMain.numBaseballs, true);
		String curBPS = GameMain.formatNumber(GameMain.currentBPS, false);
		String curBPC = GameMain.formatNumber(GameMain.ballsPerClick, false);

		// Set the text of the labels using the formatted strings
		numBaseballLabel.setText("Baseballs: \n" + numB);
		currentBPSLabel.setText("BPS: " + curBPS + "   BPC: " + curBPC);
		
	}
	
	
	
	/**
	 * A method to change the background color depening on whether the game is in Light Mode or Dark Mode
	 * 
	 * @param isDark A boolean that evaluates to true if the game is currently in dark mode
	 */
	public void setDark(boolean isDark) {
		
		if(isDark) {
			setBackground(GameMain.richBlack);
			numBaseballLabel.setForeground(GameMain.medGray);
			currentBPSLabel.setForeground(GameMain.medGray);
		}
		else{
			setBackground(GameMain.lightGray);
			numBaseballLabel.setForeground(GameMain.darkGray);
			currentBPSLabel.setForeground(GameMain.darkGray);
		}
		
	}
	

	
}
