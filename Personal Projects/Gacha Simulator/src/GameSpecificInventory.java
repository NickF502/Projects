package src;

import java.util.Vector;

public class GameSpecificInventory extends Vector<Integer>{

	int size;
	
	
	public GameSpecificInventory(String gameName, int numItemsInGame){
		size = numItemsInGame;

		
		// Initialize the gameSpecificInventory to hold the same number of items as its game
		for(int i=0; i < size; i++) {
			add(0);
		}
					
	}
	
	public int getInventoryCount() {
		int sum = 0;
		for(int i=0; i<size; i++) {
			sum += this.get(i);
		}
		return sum;
	}
	

	public String toString() {
		
		String line = "";
		for(int i=0; i<size; i++) {
			line += this.get(i) + ",";
		}
		line += "\n";
		
		return line;
	}


	public void setInventory(String line) {
		
		// Break the line into a string array
		String[] numOfEachItem= line.split(",");
				
		// Set each spot of the vector with the value stored in the string array
		for(int i=0; i<size; i++) {		
			set(i, Integer.parseInt(numOfEachItem[i]));
		}
		
		
	}
	
}
