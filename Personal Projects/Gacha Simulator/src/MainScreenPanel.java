package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class MainScreenPanel extends JPanel{

	static Dimension gameIconSize = new Dimension(100, 100);
	
	
	
	
	public MainScreenPanel() {
		setLayout(new GridLayout(4,4));
		setBackground(new Color(0, 0, 0, 0));
        setOpaque(false);
        
        insertGameModes();
	}
	
	
	
	void insertGameModes() {
		Button gameButton;
		for(String gameName:Mappings.gameToID.keySet()) {
			gameButton = new Button(gameName, "Logo", gameIconSize);
			gameButton.setBorder(new EmptyBorder(0, 0, 0, 0));
			gameButton.addMainMenuMouseListener();
						
			add(gameButton);
		}
		
	}
	
	
}
