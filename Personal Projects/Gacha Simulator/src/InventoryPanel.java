package src;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class InventoryPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	protected Inventory inv = GachaMain.user.inventory;
	protected Vector<JButton> buttonsVector = new Vector<JButton>();
	
	public InventoryPanel() {
		setBackground(new Color(0, 0, 0, 0));
        setOpaque(false);
        
		insertStats();
		
	}

	protected void insertStats() {
		
		JButton itemStat;
		
		// Create the buttons and add them to a vector
		for(int i=0; i<Mappings.itemVectors.get(GachaMain.gameMode).size(); i++) {
			itemStat = new JButton();
			
			// Characteristics for the button
			itemStat.setIcon(resizeIcon(Mappings.itemVectors.get(GachaMain.gameMode).get(i)));
			itemStat.setHorizontalAlignment(SwingConstants.LEFT);			// Align the button's content to the left side

			itemStat.setFont(Mappings.subFonts.get(GachaMain.gameMode));	// Set the font of the button
			
			itemStat.setForeground(Color.WHITE);
			itemStat.setText(Mappings.numToItemMapping.get(GachaMain.gameMode).get(i) + "  x" + inv.getGameSpecificInventory().get(i) + "(" + inv.getPercentage(i) + ")");
			
			itemStat.setContentAreaFilled(false);				// Makes button transparent
			itemStat.setFocusable(false);						// Removes the selection border
			itemStat.setBorder(new EmptyBorder(0, 0, 0, 0));		// Removes hover border
			itemStat.setOpaque(false);
			
			buttonsVector.add(itemStat);
		}

		//FIXME need to globalize
		// Add the buttons in a specific order
		addButtonsMapped();

		
					
	}

	private void addButtonsMapped() {
		int numItems = inv.getGameSpecificInventory().size();

		switch(numItems) {
		
		case 3:
			setLayout(new GridLayout(0,1));
			for(int i=0; i<numItems; i++) {
				add(buttonsVector.get(i));
			}
			return;
		
		case 5:
			setLayout(new GridLayout(0,2));
			for(int i=0; i<numItems; i++) {
				add(buttonsVector.get((i*3%5)));
			}
			return;
		
		default:
			for(int i=0; i<numItems; i++) {
				add(buttonsVector.get((i*3%5)));
			}
			return;
			
		}
		
		
			
	}

	private Icon resizeIcon(ImageIcon imageIcon) {

		Image image = (imageIcon.getImage()).getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(image);

	}

	public void updateInfo() {
		for(int i=0; i<inv.getGameSpecificInventory().size(); i++) {
			buttonsVector.get(i).setText(Mappings.numToItemMapping.get(GachaMain.gameMode).get(i) + "  x" + inv.getGameSpecificInventory().get(i) + " (" + inv.getPercentage(i) + "%)");

		}
		
	}
		
		
}
	

