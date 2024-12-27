package src;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;



/**
 * @author Nicholas Farkash
 * A class for a button that changes the color scheme of the program.
 */
public class LightDarkButton extends JButton{

	/*
	 * Constructor for the LightDarkButton Class
	 */
	public LightDarkButton() {
		setFont(new Font("Helvetica", Font.BOLD, 16));
		setText("Light Mode");
		setFocusable(false);

		// Add a MouseListener to the class. When the button is clicked, the changeColorMode method in GameWindow is called
		addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				GameWindow.changeColorMode();
			}

			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}		
			
        });
		
	}
	
	
	
	/**
	 * A method to change the background color depening on whether the game is in Light Mode or Dark Mode. Also changes the text displayed on the button
	 * 
	 * @param isDark A boolean that evaluates to true if the game is currently in dark mode
	 */
	public void setDark(boolean isDark) {
		if(isDark) {
			setText("Light Mode");
			setBackground(GameMain.medGray);
		}
		else{
			setText("Dark Mode");
			setBackground(GameMain.buttonGray);
		}
		
	}
	
		
		
}
