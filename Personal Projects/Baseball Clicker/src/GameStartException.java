package src;

import javax.swing.JOptionPane;

/**
 * @author Nicholas Farkash
 * An exception class to throw if an error occurs when the game starts
 */
public class GameStartException extends RuntimeException {

	// Constructor for a GameStartException
	public GameStartException() {
		JOptionPane.showMessageDialog(null, "Error: Unable to start game. A New Game will be started");	// Print an error message
		GameMain.newGame();	// Start a new game
	}
	
}
