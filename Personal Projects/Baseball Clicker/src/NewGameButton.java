package src;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;



/**
 * @author Nicholas Farkash
 * A class for a button that starts a new game when clicked
 */
public class NewGameButton extends JButton{

	/*
	 * Constructor for the NewGameButton class
	 */
	public NewGameButton() {
		setFont(new Font("Helvetica", Font.BOLD, 16));
		setText("New Game");
		setFocusable(false);

		// Add a MouseListener to the class. When the button is clicked, the user is informed that starting a new game will delete all save data.
		// If they confirm this, then the newGame method in GameMain is called.
		addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				
				int result = JOptionPane.showConfirmDialog(GameMain.gameWin, "Are you sure you want to start a new game? All progress will be lost.",
						"New Game?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);				
				
				// If the user confirms, start a new game. Otherwise, do nothing.
				if(result == JOptionPane.YES_OPTION) GameMain.newGame();
				else if(result == JOptionPane.CANCEL_OPTION) return;

			}

			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
        });
	
	}
	
	
	
	/**
	 * A method to change the background color depening on whether the game is in Light Mode or Dark Mode
	 * 
	 * @param isDark A boolean that evaluates to true if the game is currently in dark mode
	 */
	public void setDark(boolean isDark) {
		if(isDark)setBackground(GameMain.medGray);
		else setBackground(GameMain.buttonGray);
	}
	
	
	
}
