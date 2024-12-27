package src;


import java.util.Vector;

import javax.swing.ImageIcon;

public class ImageVectors extends Vector<ImageIcon> {


	private static final long serialVersionUID = 1L;

	public static ImageIcon empty = new ImageIcon();
	
	
	
	
	protected static Vector<ImageIcon> minecraftAnimationIV = new Vector<ImageIcon>() {

		private static final long serialVersionUID = 1L;

		{
			add(new ImageIcon("Images/Minecraft Images/Animation/Chest.png"));
			add(new ImageIcon("Images/Minecraft Images/Animation/ChestOpening.gif"));
		}
	};
	
	
	protected static Vector<ImageIcon> minecraftItemIV= new Vector<ImageIcon>() {

		private static final long serialVersionUID = 1L;

		{
			add(new ImageIcon("Images/Minecraft Images/Items/CoalOre.png"));
			add(new ImageIcon("Images/Minecraft Images/Items/IronOre.png"));
			add(new ImageIcon("Images/Minecraft Images/Items/GoldOre.png"));
			add(new ImageIcon("Images/Minecraft Images/Items/RedstoneOre.png"));
			add(new ImageIcon("Images/Minecraft Images/Items/DiamondOre.png"));
		}
	};
	
	
	
	protected static Vector<ImageIcon> GenshinAnimationIV = new Vector<ImageIcon>() {

		private static final long serialVersionUID = 1L;

		{
			add(new ImageIcon("Images/Genshin Images/Animation/Intertwined Fate.png"));
			add(new ImageIcon("Images/Genshin Images/Animation/Wish.gif"));
		}
	};
	
	
	protected static Vector<ImageIcon> GenshinItemIV= new Vector<ImageIcon>() {

		private static final long serialVersionUID = 1L;

		{
			add(new ImageIcon("Images/Genshin Images/Items/Dull Blade.png"));
			add(new ImageIcon("Images/Genshin Images/Items/Chevreuse.png"));
			add(new ImageIcon("Images/Genshin Images/Items/Navia.png"));
			
		}
	};
	
	
	
	
}
