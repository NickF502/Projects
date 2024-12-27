package src;

import java.util.HashMap;
import java.util.Vector;


public class Inventory{
		
	// First vector represents gameID, second vector stores that game's inventory
	/*
	 * Each gamemode may have a different number of items to pull. Minecraft has 5, Genshin may have more.
	 * As such, the inventory needs to store multiple mini-inventories. This will be accomplished by created a 2D vector.
	 * Vector<Vector<Integer>> The best way to accomplish this would be to create a function/class to create the Vector<Integer>.
	 * The outer Vector will store the mini-inventories
	 * */
	Vector<GameSpecificInventory> playerInventory;
	GameSpecificInventory currentGSI;
	
	public Inventory() {

		// Create the overarching inventory vector
		playerInventory = new Vector<GameSpecificInventory>();
		
		// Create a gameSpecificInventory for each game in gameID and add it to the overarching inventory vector			
		for(String gameName : Mappings.IDToGame.values()) {
			
			GameSpecificInventory gsi = new GameSpecificInventory(gameName, Mappings.numToItemMapping.get(gameName).size());
			
			// Once the gameSpecificInventory has been initialized to the proper length, add it to the playerInventory
			playerInventory.add(gsi);

		}


	}

	
	void addItemtoInventory(String item) {
		// Get the appropriate gameSpecificInventory
		currentGSI = getGameSpecificInventory();
		
	    // Find which index of the gameSpecificInventory needs to be incremented
	    HashMap<String, Integer> map = Mappings.itemToNumMapping.get(GachaMain.gameMode);
		int indexToIncrement = map.get(item);
		
	    // Increment that index of the gameSpecificInventory
		currentGSI.set(indexToIncrement, currentGSI.get(indexToIncrement)+1);

	}
	
	
	
	String getPercentage(int i) {
		// Get the appropriate gameSpecificInventory
		currentGSI = playerInventory.get(Mappings.gameToID.get(GachaMain.gameMode));
		
	    // If no items are in the gameSpecificInventory, handle the div by 0 error
	    
		if(currentGSI.getInventoryCount() == 0) return NumberFormatter.formatDecimal(0);
		
	    float percentage = (float)currentGSI.get(i) * 100 / currentGSI.getInventoryCount();
		return NumberFormatter.formatDecimal(percentage);
	}
	
	GameSpecificInventory getGameSpecificInventory() {
		// Get the appropriate gameSpecificInventory
	    return playerInventory.get(Mappings.gameToID.get(GachaMain.gameMode));
		
	}
	
	
	
}