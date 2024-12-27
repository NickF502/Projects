package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ActionButtonsPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private static Dimension screenSize = Window.screenSize;
	protected final static int sideGap = 25;
	
	protected static Dimension bottomButtonSize = new Dimension(screenSize.width/4 - (sideGap), 75);
	private Border enterBorder = BorderFactory.createLineBorder(Color.WHITE, 3);
	private Border exitBorder = BorderFactory.createEmptyBorder();
	
	
	private Player user = GachaMain.user;
		

	public ActionButtonsPanel() {
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(true);
		setLayout(new FlowLayout(FlowLayout.CENTER, (screenSize.width - (4*bottomButtonSize.width) - (2*sideGap))/3, 0));
		
		setPullButtons();
	}
	
	
	void setMainButtons() {
		// Remove all buttons
		removeAll();
		
		// Add required buttons in order
		add(createQuitButton());

		// Validate and repaint to ensure proper display of buttons
		validate();
		repaint();
				
	}
	
	
	
	protected void setPullButtons() {
		// Remove all buttons
		removeAll();
		
		// Add required buttons in order
		add(createOnePullButton());
		add(createTenPullButton());
		add(createInventoryButton());
		add(createBackToMainButton());

		// Validate and repaint to ensure proper display of buttons
		validate();
		repaint();
		
	}
	
	
	void setInventoryButtons(){
		// Remove all buttons
		removeAll();		
		
		// Add required buttons in order
		add(createBackButton());
		add(createBackToMainInvButton());
		
		// Validate and repaint to ensure proper display of buttons
		validate();
		repaint();
		
	}

	

	Button createQuitButton() {
		Button quitButton = new Button(GachaMain.gameMode, "Quit", bottomButtonSize);

		// Add the Mouse Listener for actions

		quitButton.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				quitButton.setBorder(enterBorder);

			}
			
			public void mouseExited(MouseEvent e) {
				quitButton.setBorder(exitBorder);
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				GachaMain.quitGame();
			}

		});
		
		return quitButton;

	}
	
	Button createOnePullButton() {
		
		Button onePullButton = new Button(GachaMain.gameMode, "Pullx1", bottomButtonSize);
		
		// Add the Mouse Listener for actions
		onePullButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {		
				if(user.getPoints() >= 100){
					user.setPoints(user.getPoints()-100);
		        	GachaMain.pull();
		        }
			}

			public void mouseEntered(MouseEvent e) {
				onePullButton.setBorder(enterBorder);

			}
			
			public void mouseExited(MouseEvent e) {
				onePullButton.setBorder(exitBorder);
				
			}

			
		});
		
		return onePullButton;
	}
	
	Button createTenPullButton() {
		Button tenPullButton = new Button(GachaMain.gameMode, "Pullx10", bottomButtonSize);

		// Add the Mouse Listener for actions
		tenPullButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {		
				if(user.getPoints() >= 1000){
		        	user.setPoints(user.getPoints()-1000);
		        	GachaMain.pullTen();
		        }
			}

			@Override
			public void mouseClicked(MouseEvent e) {				
			}

			@Override
			public void mousePressed(MouseEvent e) {				
			}

			public void mouseEntered(MouseEvent e) {
				tenPullButton.setBorder(enterBorder);

			}
			
			public void mouseExited(MouseEvent e) {
				tenPullButton.setBorder(exitBorder);
				
			}

		});
		
		return tenPullButton;
	}
	
	Button createInventoryButton() {
		Button inventoryButton = new Button(GachaMain.gameMode, "Inventory", bottomButtonSize);

		// Add the Mouse Listener for actions

		inventoryButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
		        GachaMain.gameWindow.setWindowInventoryView();
			}
			
			public void mouseEntered(MouseEvent e) {
				inventoryButton.setBorder(enterBorder);

			}
			
			public void mouseExited(MouseEvent e) {
				inventoryButton.setBorder(exitBorder);
				
			}

		});
		
		return inventoryButton;

	}
	
	Button createBackToMainButton() {
		Button backToMainButton = new Button(GachaMain.gameMode, "BackToMain", bottomButtonSize);

		// Add the Mouse Listener for actions

		backToMainButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				//FIXME Implement
				GachaMain.gameMode = "Main";
				GachaMain.gameWindow.setWindowMainView();
			}
			
			public void mouseEntered(MouseEvent e) {
				backToMainButton.setBorder(enterBorder);

			}
			
			public void mouseExited(MouseEvent e) {
				backToMainButton.setBorder(exitBorder);
				
			}

		});
		
		return backToMainButton;

	}
	
	
	
	Button createBackToMainInvButton() {
		Button backToMainButton = new Button(GachaMain.gameMode, "BackToMainInv", bottomButtonSize);

		// Add the Mouse Listener for actions

		backToMainButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				//FIXME Implement
				GachaMain.gameMode = "Main";
				GachaMain.gameWindow.setWindowMainView();
			}
			
			public void mouseEntered(MouseEvent e) {
				backToMainButton.setBorder(enterBorder);

			}
			
			public void mouseExited(MouseEvent e) {
				backToMainButton.setBorder(exitBorder);
				
			}

		});
		
		return backToMainButton;

	}
	
	
	
	Button createBackButton() {
		Button backButton = new Button(GachaMain.gameMode, "Back", bottomButtonSize);

			
		// Add the Mouse Listener for actions

		backButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
		        GachaMain.gameWindow.setWindowPullView(GachaMain.gameMode);		        
			}
			
			public void mouseEntered(MouseEvent e) {
				backButton.setBorder(enterBorder);

			}
			
			public void mouseExited(MouseEvent e) {
				backButton.setBorder(exitBorder);
				
			}

		});
		
		return backButton;

	}

	
	
}





