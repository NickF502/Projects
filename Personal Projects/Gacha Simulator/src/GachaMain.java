package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;


public class GachaMain {

	public static Mappings Mapping;
	static Window gameWindow;
	public static Player user;
	public static String gameMode = "Main";
	
	public static void main(String[] args) {

		// Run the garbage collector
		System.gc();
		
		
	    user = new Player("NickF502", "Password1");
	    
	    // Create the game window
	    gameWindow = new Window();
	    
	    // Load the data for the game
	    loadGame();
	    	    
	}

	private static String getRandomItem(){
		int random = new Random().nextInt(100)+1;
		
		switch(gameMode) {

		case "Minecraft":
			if(random < 50) return "Coal";
			else if(random < 80) return "Iron";
			else if(random < 90) return "Gold";
			else if(random < 98) return "Redstone";
			else return "Diamond";
			
		case "Genshin":
			if(random < 90) return "Dull Blade";
			else if(random < 98) return "Chevreuse";
			else return "Navia";
				
		
		}
		
		return "";
	}
	
	
	public static void pull(){
		gameWindow.headerPanel.updatePointsText();
		
		String item = getRandomItem();
		gameWindow.animationPanel.pullAnimation(item);
		user.addItem(item);
	}
	

	public static void pullTen(){
		gameWindow.headerPanel.updatePointsText();

		Vector<String> tenItems = new Vector<String>();
		for(int i=0; i<10; i++) {
			String item = getRandomItem();
			tenItems.add(item);
			user.addItem(item);
		}
		
		gameWindow.animationPanel.pullTenAnimation(tenItems, 0);
		
		tenItems = null;
	
	}
	
	
	public static void quitGame(){

		// Save the Inventory
		try {
			
			// Create the FileWriter, linked to Documents/InventorySaveData.txt
			FileWriter toFile = new FileWriter("Documents/InventorySaveData.txt");
			
			// Write each GameSpecificInventory's vector to saveData.txt
			for(GameSpecificInventory gsi : user.inventory.playerInventory) {
				toFile.write(gsi.toString());
			}
			
			// Close the FileWriter
			toFile.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		
		
		// Save the Points
		try {
			
			// Create the FileWriter, linked to Documents/InventorySaveData.txt
			FileWriter toFile = new FileWriter("Documents/PointsSaveData.txt");
			
			// Write each GameSpecificInventory's vector to saveData.txt
			toFile.write(user.getGamePoints());
			
			// Close the FileWriter
			toFile.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
				
				
				
		// Exit the application
		System.exit(0);
	}

	
	private static void loadGame() {
		
		// Load the Inventory
			
			// Create the FileWriter, linked to Documents/InventorySaveData.txt
			try {
				
				Scanner fromFile = new Scanner(new File("Documents/InventorySaveData.txt"));
				

				// If there is no save data, stop the load game process
				if(!fromFile.hasNext()) {
					fromFile.close();
					return;
				}

				while(fromFile.hasNext()) {
					
					// Write the lines to the GameSpecificInventories in order
					for(GameSpecificInventory gsi : user.inventory.playerInventory) {
						String line = fromFile.next();
						gsi.setInventory(line);
					}
					
				}
				
				// Close the FileWriter
				fromFile.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			
			
			// Load the Points

			try {
				
				Scanner fromFile = new Scanner(new File("Documents/PointsSaveData.txt"));
				

				// If there is no save data, stop the load game process
				if(!fromFile.hasNext()) {
					fromFile.close();
					return;
				}

				while(fromFile.hasNext()) {
					
					// Write the lines to the GameSpecificInventories in order
					for(int i=0; i<Mappings.gameToID.size(); i++) {
						user.loadGamePoints(i, Long.parseLong(fromFile.next()));
					}
					
				}
				
				// Close the FileWriter
				fromFile.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
				
	}
	
	
	
}


