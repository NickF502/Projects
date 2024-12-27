package hw3;

import java.util.ArrayList;

public class User {

	public String name, userID;
	public ArrayList<Item> checkedOutItems;
	
	
	public User(String n, String ID) {
		name = n;
		userID = ID;
		checkedOutItems = new ArrayList<Item>();
	}
	
	public void checkoutItem(Item i) {
		checkedOutItems.add(i);
	}
	
	public void returnItem(Item i) {
		checkedOutItems.remove(i);
	}
	
	public void viewCheckedOutItems() {
		System.out.println(name + " has checked out:");
		for(Item i : checkedOutItems) {
			if(i.getClass() == Book.class)
				System.out.println(((Book)i).title + " by " + ((Book)i).author);
			else
				System.out.println(((Magazine)i).title + " by " + ((Magazine)i).author);
		}
	}
}
