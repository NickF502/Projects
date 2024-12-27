package src;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;


public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JLayeredPane layeredMainContainer = new JLayeredPane();
	
	protected HeaderPanel headerPanel = new HeaderPanel();
	protected MainScreenPanel mainScreenPanel = new MainScreenPanel();
	protected AnimationPanel animationPanel;
	protected InventoryPanel inventoryPanel;
	protected ActionButtonsPanel buttonsPanel = new ActionButtonsPanel();
	
	protected static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Get the screen Dimensions
	
	protected ImageIcon backgroundImageIcon;
	protected Image tempImage;
	protected JLabel backgroundLabel = new JLabel();
	
	public Window() {
		
		// Basic information for the Window
		setTitle("Gatcha by NickF502");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Fullscreen
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen the window
		setUndecorated(true); // Remove Max/Min/Close
		setSize(screenSize); // Set the window size to take up the entire screen
				
		// Create and set the Taskbar Icon to Logo
		ImageIcon taskbarIcon = new ImageIcon("Images/Main Images/UI/Logo.png");
		setIconImage(taskbarIcon.getImage());
		
		// Create and add the main pane of the Window
		layeredMainContainer.setLayout(null);
		layeredMainContainer.setBounds(0, 0, getWidth(), getHeight());
		add(layeredMainContainer);
		
		// Initialize Pull Window Panels
		if(GachaMain.gameMode == "Main") {
			setWindowMainView();
		}
		else {
			setWindowPullView(GachaMain.gameMode);			
		}
				
	    setVisible(true);
	}
	
	
	void setWindowMainView() {

		// Clear the container of all elements
		layeredMainContainer.removeAll();
		
		// Call appropriate functions to switch modes
		headerPanel.setMainMode();
		buttonsPanel.setMainButtons();
				
		// Create and add the background image of the window
		backgroundImageIcon = new ImageIcon("Images/Main Images/UI/MainBackground.png");
		tempImage = (backgroundImageIcon.getImage()).getScaledInstance(getWidth(), getHeight(), java.awt.Image.SCALE_SMOOTH);
		backgroundImageIcon = new ImageIcon(tempImage);
		
		backgroundLabel.setIcon(backgroundImageIcon);
	    backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
	    
	   
	    // Set the bounds/location for each panel
	    headerPanel.setBounds(0, 5, getWidth(), getHeight()/5);
	    mainScreenPanel.setBounds((getWidth() - 550) / 2, (getHeight() - 500) / 2, 550, 550);
	    buttonsPanel.setBounds(0, getHeight() - 100, getWidth(), 100);


	    // Add components to mainContainer	    
	    layeredMainContainer.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
	    layeredMainContainer.add(headerPanel, JLayeredPane.PALETTE_LAYER);
	    layeredMainContainer.add(mainScreenPanel, JLayeredPane.PALETTE_LAYER);
	    layeredMainContainer.add(buttonsPanel, JLayeredPane.PALETTE_LAYER);

	    layeredMainContainer.validate();
	    layeredMainContainer.repaint();

	}


	void setWindowPullView(String gameMode) {
		
		// Clear the container of all elements
		layeredMainContainer.removeAll();
		
		// Call appropriate functions to switch modes
		animationPanel = new AnimationPanel();
		headerPanel.setHeaderPanel();
		buttonsPanel.setPullButtons();

		
		// Create and add the background image of the window
		backgroundImageIcon = new ImageIcon("Images/" + gameMode + " Images/UI/PullBackground.png");
		tempImage = (backgroundImageIcon.getImage()).getScaledInstance(getWidth(), getHeight(), java.awt.Image.SCALE_SMOOTH);
		backgroundImageIcon = new ImageIcon(tempImage);
		
		backgroundLabel.setIcon(backgroundImageIcon);
	    backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
	    
	   
	    // Set the bounds/location for each panel
	    headerPanel.setBounds(0, 5, getWidth(), getHeight()/5);
	    animationPanel.setBounds((getWidth() - 1500) / 2, (getHeight() - 500) / 2, 1500, 550);
	    buttonsPanel.setBounds(0, getHeight() - 100, getWidth(), 100);


	    // Add components to mainContainer	    
	    layeredMainContainer.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
	    layeredMainContainer.add(headerPanel, JLayeredPane.PALETTE_LAYER);
	    layeredMainContainer.add(animationPanel, JLayeredPane.PALETTE_LAYER);
	    layeredMainContainer.add(buttonsPanel, JLayeredPane.PALETTE_LAYER);

	    layeredMainContainer.validate();
	    layeredMainContainer.repaint();

	}
	
	void setWindowInventoryView() {

		// Call appropriate functions to switch modes
		headerPanel.setInventoryMode();
		inventoryPanel = new InventoryPanel();
		buttonsPanel.setInventoryButtons();
		
		
		// Create and add the background image of the window
	    backgroundImageIcon = new ImageIcon("Images/" + GachaMain.gameMode + " Images/UI/InventoryBackground.png");
		tempImage = (backgroundImageIcon.getImage()).getScaledInstance(getWidth(), getHeight(), java.awt.Image.SCALE_SMOOTH);
		backgroundImageIcon = new ImageIcon(tempImage);
		
		backgroundLabel.setIcon(backgroundImageIcon);
	    backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
	    
	    // Set the bounds/location for each panel
	    inventoryPanel.setBounds((getWidth() - 1500) / 2, (getHeight() - 500) / 2, 1500, 550);
	    
		// Remove the animation Panel
		layeredMainContainer.remove(animationPanel);
		
		// Update and Add the inventory Panel
		inventoryPanel.updateInfo();
		layeredMainContainer.add(inventoryPanel, JLayeredPane.PALETTE_LAYER);

	    
	    layeredMainContainer.validate();
	    layeredMainContainer.repaint();
	    
	}
	
	
	

	
}
