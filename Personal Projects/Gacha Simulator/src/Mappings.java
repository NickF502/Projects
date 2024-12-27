package src;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;


public class Mappings {    
    

		// Add an entry for new games here
		static HashMap<String, Integer> gameToID = new HashMap<String, Integer>() {

			private static final long serialVersionUID = 1L;

			{
				put("Minecraft", 0);
				put("Genshin", 1);

	        }
	    };
	    
	    
	    
		// Add an entry for new games here
	    static HashMap<Integer, String> IDToGame = new HashMap<Integer, String>() {

			private static final long serialVersionUID = 1L;

			{
				put(0, "Minecraft");
				put(1, "Genshin");

	        }
	    };
	    
	    
	    
		// Add an entry for new games here
	    static HashMap<String, Color> titleColors = new HashMap<String, Color>() {

			private static final long serialVersionUID = 1L;

			{
				put("MainTitle", Color.BLUE);
				
	            put("MinecraftPull", Color.decode("#010203"));
	            put("MinecraftInventory", Color.decode("#b8b8b8"));
	            
	            put("GenshinPull", Color.decode("#fefefe"));
	            put("GenshinInventory", Color.decode("#fefefe"));

	        }
	    };
	    
	    
	    
		// Add an entry for new games here
	    static HashMap<String, Color> subColors = new HashMap<String, Color>() {

			private static final long serialVersionUID = 1L;

			{
				put("MainTitle", Color.decode("#fea807"));
				
	            put("MinecraftPull", Color.decode("#ffff55"));
	            put("MinecraftInventory", Color.decode("#ffff55"));

	            put("GenshinPull", Color.decode("#fea807"));
	            put("GenshinInventory", Color.decode("#fea807"));


	        }
	    };
	    
	    
	    
		// Add an entry for new games here
	    static HashMap<String, Font> titleFonts = new HashMap<String, Font>() {

			private static final long serialVersionUID = 1L;

			{
				put("Minecraft", Fonts.minecraftTitleFont);
				put("Genshin", Fonts.genshinTitleFont);

	        }
	    };
	    
	    
	    
		// Add an entry for new games here
	    static HashMap<String, Font> subFonts = new HashMap<String, Font>() {

			private static final long serialVersionUID = 1L;

			{
				put("Minecraft", Fonts.minecraftSubFont);
				put("Genshin", Fonts.genshinSubFont);

	        }
	    };
	    
	    
	    
		// Add an entry for new games here
	    static HashMap<String, Vector<ImageIcon>> itemVectors = new HashMap<String, Vector<ImageIcon>>() {

			private static final long serialVersionUID = 1L;

			{
				put("Minecraft", ImageVectors.minecraftItemIV);
				put("Genshin", ImageVectors.GenshinItemIV);
	        }
	    };
	    
	    
	 // Add an entry for new games here
	    static HashMap<String, Vector<ImageIcon>> animationVectors = new HashMap<String, Vector<ImageIcon>>() {
		
			private static final long serialVersionUID = 1L;
		
			{
				put("Minecraft", ImageVectors.minecraftAnimationIV);
				put("Genshin", ImageVectors.GenshinAnimationIV);
		    }
	    };
	    
	    
	    // Add new "ItemToNumMapping" and "NumToItemMapping" for each new game
	    //
	    
	    
	    static HashMap<String, Integer> minecraftItemToNumMapping = new HashMap<String, Integer>() {
	    	
	 		private static final long serialVersionUID = 1L;

	 		{
	             put("Coal", 0);
	             put("Iron", 1);
	             put("Gold", 2);
	             put("Redstone", 3);
	             put("Diamond", 4);
	 		}
	 	};

	 	static HashMap<Integer, String> minecraftNumToItemMapping = new HashMap<Integer, String>() {

	 		private static final long serialVersionUID = 1L;

	 		{
	             put(0, "Coal");
	             put(1, "Iron");
	             put(2, "Gold");
	             put(3, "Redstone");
	             put(4, "Diamond");
	         }
	     };
	     
	     
	     static HashMap<String, Integer> genshinItemToNumMapping = new HashMap<String, Integer>() {
		    	
		 		private static final long serialVersionUID = 1L;

		 		{
		            put("Dull Blade", 0);
		            put("Chevreuse", 1);
		            put("Navia", 2);
		 		}
		 	};

		 	
		 	
	 	static HashMap<Integer, String> genshinNumToItemMapping = new HashMap<Integer, String>() {

	 		private static final long serialVersionUID = 1L;

	 		{
	 			put(0, "Dull Blade");
	            put(1, "Chevreuse");
	            put(2, "Navia");
	         }
	     };
	    
	
	
	    // Add an entry for new games here - Note: These must be at the end
	     static HashMap<String, HashMap<String, Integer>> itemToNumMapping = new HashMap<String, HashMap<String, Integer>>() {
	    	
			private static final long serialVersionUID = 1L;
			
			{
			     put("Minecraft", minecraftItemToNumMapping);
			     put("Genshin", genshinItemToNumMapping);
			}
	    };
	 	
	    
	    
		// Add an entry for new games here
	    static HashMap<String, HashMap<Integer, String>> numToItemMapping = new HashMap<String, HashMap<Integer, String>>() {
	    	
	 		private static final long serialVersionUID = 1L;
	
	 		{
	             put("Minecraft", minecraftNumToItemMapping);
	             put("Genshin", genshinNumToItemMapping);
	             
	            
	 		}
	    };
		 	
		 	
	}

