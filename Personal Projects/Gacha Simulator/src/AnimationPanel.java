package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;


public class AnimationPanel extends JLabel {
	

	private static final long serialVersionUID = 1L;
	
	static JLabel animationBox = new JLabel();
	private final ImageIcon nullIcon= new ImageIcon();
	
    private ImageIcon gif = getTenPullAnimation();
    private int gifDuration= getGIFLength();


		
	public AnimationPanel() {
		setBackground(new Color(0, 0, 0, 0));
        setLayout(new BorderLayout());
                
        setSprite(Mappings.animationVectors.get(GachaMain.gameMode).get(0));
        
        // Set the horizontal and vertical alignments to center
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);

    }


	
	
	void setSprite(ImageIcon icon) {
		setIcon(icon);
	}
	
	void setSprite(String item) {
		int indexItemPulled = Mappings.itemToNumMapping.get(GachaMain.gameMode).get(item);
		setIcon(Mappings.itemVectors.get(GachaMain.gameMode).get(indexItemPulled));
	}
	
	void resetSprite(){
        setSprite(Mappings.animationVectors.get(GachaMain.gameMode).get(0));
	}
	
	
	public void pullAnimation(String item) {
		
		// Disable the button during the sequence
		GachaMain.gameWindow.buttonsPanel.setVisible(false);

	    // Display the gif in the animationBox for a set time
	    playGif(gif, gifDuration, () -> {
	    	
	        // After time is up, display the item that was pulled and wait
	        setSprite(item);sleep(1000, () -> {
	        	
	            // Once the item has been displayed, reset the image and activate the buttons
	            resetSprite();
	    	    GachaMain.gameWindow.buttonsPanel.setVisible(true);
	            
	        });
	        
	    });
	}

	
	public void pullTenAnimation(Vector<String> tenItems, int currentIndex) {
		
	    // Disable the button during the sequence
	    GachaMain.gameWindow.buttonsPanel.setVisible(false);

	    if(currentIndex == 0) {

		    // Display the gif in the animationBox for a set time
		    playGif(gif, gifDuration, () -> {
		    	
		        // After the GIF is displayed, display the item at the current index for 1 second
		        setSprite(tenItems.get(currentIndex));sleep(1000, () -> {
		        
			        // Then clear the sprite for 1/10th of a second
			        setSprite(nullIcon);sleep(100, () -> {
			        
				        // Then call the function again with currentIndex incremented
				        pullTenAnimation(tenItems, 1);
			        });
		        });
		    });
	    }
	    
	    else {
	    	
	    	// Get the index of the next item
	    	int nextIndex = currentIndex + 1;
	    	
	    	 // Display the item at the current index for 1 second
	        setSprite(tenItems.get(currentIndex));sleep(1000, () -> {
	        	
	        	// If there are no more items to display
		        if (nextIndex >= 10) {
		        	// Reset the window
		            resetSprite();
		            GachaMain.gameWindow.buttonsPanel.setVisible(true);
		        } 
		        // Otherwise
		        else {
		        	
		        	// Clear the sprite for 1/10th of a second
			        setSprite(nullIcon);sleep(100, () -> 
			        
			        // Then call the function again with nextIndex
			        pullTenAnimation(tenItems, nextIndex));
			        
		        }// else

	        }); // sleep
	    }
	}




	private void playGif(ImageIcon img, int duration, Runnable callback) {
        setIcon(img);

        Timer timer = new Timer(duration, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setIcon(nullIcon); // Clear the animationBox
                callback.run();
            }
        });

        timer.setRepeats(false);
        timer.start();
    }
	
	
	
	private void sleep(int delay, Runnable action) {
		Timer timer = new Timer(delay, new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				action.run();
			}
		});
		
		timer.setRepeats(false);
		timer.start();
	}




	private int getGIFLength() {
		String gameMode = GachaMain.gameMode;
		switch(gameMode) {
		
			case "Minecraft":
				return 2500;
				
			case "Genshin":
				return 6960;
		
			default:
				return 0;
		}
	}
	
	private ImageIcon getTenPullAnimation() {
		String gameMode = GachaMain.gameMode;
		switch(gameMode) {
		
			case "Minecraft":
				return Mappings.animationVectors.get(GachaMain.gameMode).get(1);

				
			case "Genshin": //FIXME
				return Mappings.animationVectors.get(GachaMain.gameMode).get(1);
		
			default:
				return null;
		}
	}
	
	
}
