package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeaderPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final JPanel topTitle = new JPanel();
	private final JPanel bottomTitle = new JPanel();
	
    private final JLabel pointsLabel = new JLabel();
    private final JLabel subLabel = new JLabel();
			
    private final JLabel titleLabel = new JLabel();

    public HeaderPanel() {
    	setBackground(new Color(0, 0, 0, 0));
        setOpaque(false);
        setLayout(new FlowLayout());

        setHeaderPanel();
        
        add(topTitle);
        add(bottomTitle);
    }
    
    protected void setHeaderPanel() {
    	setTopTitle();
        setBottomTitle();
    }
    
    private void setTopTitle() {
    	topTitle.setOpaque(false);
		topTitle.setLayout(new GridLayout(1,1));

		createTitleLabel();
		topTitle.add(titleLabel);
		
	}


	private void setBottomTitle(){
    	bottomTitle.setOpaque(false);
    	bottomTitle.setLayout(new GridLayout(1,2));
    	bottomTitle.setBounds(0,0,Window.screenSize.width, 50);
    	
    	createPointsLabel();
    	createSplashLabel();

    	bottomTitle.add(pointsLabel);
    	bottomTitle.add(subLabel);
    }
	
	
	private void createTitleLabel() {
    	titleLabel.setHorizontalAlignment(JLabel.CENTER);
    	titleLabel.setVerticalAlignment(JLabel.TOP);
        
    	titleLabel.setFont(Mappings.titleFonts.get(GachaMain.gameMode));
    	titleLabel.setForeground(Mappings.titleColors.get(GachaMain.gameMode + "Pull"));
    	titleLabel.setText(GachaMain.gameMode + " Gacha");
        
    	titleLabel.setPreferredSize(new Dimension(Window.screenSize.width, 100));
    }

    private void createPointsLabel() {
    	
    	pointsLabel.setHorizontalAlignment(JLabel.CENTER);
    	pointsLabel.setVerticalAlignment(JLabel.CENTER);
    	
    	
    	pointsLabel.setFont(Mappings.subFonts.get(GachaMain.gameMode));
    	pointsLabel.setForeground(Mappings.subColors.get(GachaMain.gameMode + "Pull"));
    	pointsLabel.setText("Points: " + GachaMain.user.getFormattedPoints());
        
    	pointsLabel.setPreferredSize(new Dimension(Window.screenSize.width/2, 50));
    }


    private void createSplashLabel() {
    	subLabel.setHorizontalAlignment(JLabel.CENTER);
    	subLabel.setVerticalAlignment(JLabel.CENTER);
    	
    	
    	subLabel.setFont(pointsLabel.getFont());
        subLabel.setForeground(pointsLabel.getForeground());
        subLabel.setText("Welcome Back " + GachaMain.user.getUsername() + "!");
        
        subLabel.setPreferredSize(new Dimension(Window.screenSize.width/2, 50));

    }

    
    
    
    void setTitle(String text, Color color, Font font) {
    	titleLabel.setText(text);
    	titleLabel.setForeground(color);
    	titleLabel.setFont(font);
    	
    }
    
    void setSubLabel(String text, Color color, Font font) {
    	subLabel.setText(text);
    	subLabel.setForeground(color);
    	subLabel.setFont(font);
    	
    }
    
    void setPointsLabel(String text, Color color, Font font) {
    	pointsLabel.setText(text);
    	pointsLabel.setForeground(color);
    	pointsLabel.setFont(font);
    }
    
    
 
    
    public void updatePointsText() {
    	pointsLabel.setText("Points: " + GachaMain.user.getFormattedPoints());
    }

    // FIXME ???
    void setPullMode() {
    	setTitle(GachaMain.gameMode + "Gacha", Mappings.titleColors.get(GachaMain.gameMode), Mappings.titleFonts.get(GachaMain.gameMode));
    	setSubLabel(subLabel.getText(), Mappings.subColors.get(GachaMain.gameMode), Mappings.subFonts.get(GachaMain.gameMode));
    	setPointsLabel("Points: " + GachaMain.user.getFormattedPoints(), Mappings.subColors.get(GachaMain.gameMode), Mappings.subFonts.get(GachaMain.gameMode));
    	
    	//titleLabel.setForeground(Mappings.titleColors.get(GachaMain.gameMode + "Pull"));
    	//subLabel.setForeground(Mappings.subColors.get(GachaMain.gameMode + "Pull"));
    	//pointsLabel.setForeground(subLabel.getForeground());
    	validate();
    	repaint();
    }


	 void setInventoryMode() {
		titleLabel.setForeground(Mappings.titleColors.get(GachaMain.gameMode + "Inventory"));
		subLabel.setForeground(Mappings.subColors.get(GachaMain.gameMode + "Inventory"));
		pointsLabel.setForeground(subLabel.getForeground());
		pointsLabel.setText("Total Items: " + GachaMain.user.inventory.getGameSpecificInventory().getInventoryCount());

	}
	 
	 void setMainMode() {
    	setTitle("GACHA SIMULATOR", Color.BLUE, Fonts.mainTitleFont);
		setSubLabel("By NickF502", Color.ORANGE, Fonts.mainSubFont);
		setPointsLabel("", Color.BLACK, Fonts.baseFont);
	 }
	
	 
}
