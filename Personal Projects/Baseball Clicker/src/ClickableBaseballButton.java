package src;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;



/**
 * @author Nicholas Farkash
 * A class for the button with a baseball as the image. This is the main
 * component of the game, as it is what the player interacts with to play the game
 */
public class ClickableBaseballButton extends JButton {
			
	/**
	 * Instance variables for the class
	 * 
	 * @param buttonIcon The image being displayed on the button
	 */
	private ImageIcon buttonIcon;
	
	
	
	/*
	 * Constructor for the ClickableBaseballButton class 
	 */
	public ClickableBaseballButton() {
		
		// Set the Icon for the Baseball
		ImageIcon icon = new ImageIcon("Images/Baseball.png");
        Image image = (icon.getImage()).getScaledInstance(250, 250,  java.awt.Image.SCALE_SMOOTH);
        buttonIcon = new ImageIcon(image);
		setIcon(buttonIcon);
		
		// Characteristics for the button
		setContentAreaFilled(false);				// Makes button background invisible 
		setBorder(new EmptyBorder(0, 0, 0, 0));		// Removes hover border
		setFocusable(false);						// Removes the selection border
		setVisible(true);							// Make the button visible

		// Mouse Listener for the ClickableBaseballButton
		addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				GameMain.numBaseballs += GameMain.ballsPerClick;
        		GameWindow.header.scoreboard.updateScoreDisplay();	
        		e.consume();
			}	
			
			public void mousePressed(MouseEvent e) {
				resetImage();
			}
			
			public void mouseReleased(MouseEvent e) {
				enlargeImage();
			}
						
			public void mouseEntered(MouseEvent e) {
				enlargeImage();
			}
			
			public void mouseExited(MouseEvent e) {
				resetImage();
			}
			
        });
		
    }
	
	
	
	/**
	 * A method to enlarge the button's image; used when hovering over and clicking the button
	 */
	private void enlargeImage() {
		Image enlargedImage = buttonIcon.getImage().getScaledInstance(275, 275,  java.awt.Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(enlargedImage));
	}
	
	
	
	/**
	 * A method to reset the button's image; used when hovering off and releasing the button
	 */
	private void resetImage() {
		Image regularImage = buttonIcon.getImage().getScaledInstance(250, 250,  java.awt.Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(regularImage));
	}
	
	
	
}
