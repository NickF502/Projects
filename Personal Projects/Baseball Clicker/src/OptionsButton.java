package src;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;



/**
 * @author Nicholas Farkash
 * A class for a button that displays a popup options menu when clicked
 */
public class OptionsButton extends JButton{

	private JButton t = this;
	private PopUpOptionsPanel popupMenu = new PopUpOptionsPanel();
	
	/*
	 * Constructor for the OptionsButton class
	 */
	public OptionsButton() {
		setFont(new Font("Helvetica", Font.BOLD, 24));
		setText("Options");
		setFocusable(false);
		
		// Add an ActionListener to the class. When the button is clicked, the popup menu is displayed
		addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!popupMenu.isVisible()) popupMenu.show(t, -2, t.getHeight());
				else popupMenu.setEnabled(false);
			}
			
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

		popupMenu.setDark(isDark);	// Change the popupMenu's color	
		
	}

	
	
}
