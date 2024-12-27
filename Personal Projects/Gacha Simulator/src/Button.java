package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Button extends JButton{
	

	private static final long serialVersionUID = 1L;

	protected ImageIcon buttonImageIcon;
	protected String gameName;
	protected Dimension buttonSize;
	
	
	public Button(String mode, String buttonName, Dimension butSize) {
		gameName = mode;
		buttonSize = butSize;

		// Create the image of the button
		buttonImageIcon = new ImageIcon("Images/" + gameName + " Images/UI/" + buttonName + ".png");
		Image image = (buttonImageIcon.getImage()).getScaledInstance(buttonSize.width, buttonSize.height, java.awt.Image.SCALE_SMOOTH);
		buttonImageIcon = new ImageIcon(image);
		
		setIcon(buttonImageIcon);
		setFocusable(false);
		setContentAreaFilled(false);
		setPreferredSize(buttonSize);
		setBorder(BorderFactory.createEmptyBorder());
		
		//Testing for button creation
		//setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

	}
	
	
	protected void addMainMenuMouseListener() {
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				GachaMain.gameMode = gameName;
				GachaMain.gameWindow.setWindowPullView(GachaMain.gameMode);

				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				enlargeImage();		
			}

			@Override
			public void mouseExited(MouseEvent e) {
				shrinkImage();		
			}
			
		});
	}
	
	
	public void enlargeImage() {
		Image enlargedImage = buttonImageIcon.getImage().getScaledInstance(128, 128,  java.awt.Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(enlargedImage));	
	}
	
	
	public void shrinkImage() {
		Image shrunkImage = buttonImageIcon.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(shrunkImage));		
	}
}
