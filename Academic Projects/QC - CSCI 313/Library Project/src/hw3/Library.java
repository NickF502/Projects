package hw3;

import java.util.ArrayList;

/* A class which represents a library, which holds a collection of books */
public class Library {

	public ArrayList<Book> bookLib;
	public ArrayList<Magazine> magLib;

	
	/* Default Constructor */
	public Library() {
		 bookLib = new ArrayList<Book>();
		 magLib = new ArrayList<Magazine>();
	}
	
	/* Adds a book to the library */
	public void addItem(Item i) {
		if(i.getClass() == Book.class) {
			Book b = (Book)i;
			bookLib.add(b);
			System.out.println(b.title + " has been added to the book collection!");
		}
		else if(i.getClass() == Magazine.class) {
			Magazine m = (Magazine)i;
			magLib.add(m);
			System.out.println(m.title + " has been added to the magazine collection!");
		}
		else throw new IllegalArgumentException("This isn't a book or magazine!");

	}
	
	/* Checkout a book based on its ISBN */
	public void checkoutItem(String ISBN, User u) {
		try {
			Item i = findItem(ISBN);
			
			if(i.getClass() == Book.class && i.isAvailable()) {
				Book b = (Book)i;
				b.checkout();
				u.checkoutItem(b);
				System.out.println(u.name + " has checked out " + b.title);
			}
			else if(i.getClass() == Magazine.class && i.isAvailable()) {
				Magazine m = (Magazine)i;
				m.checkout();
				u.checkoutItem(m);
				System.out.println(u.name + " has checked out " + m.title);
			}
			else throw new IllegalArgumentException();
		}
		catch(IllegalArgumentException e) {
			System.out.println("This item is not currently in the library!");
		
		}
		
		
	}
	
	/* Return a book to the library */
	public void returnItem(String ISBN, User u) {
		try {
			Item i = findItem(ISBN);
			
			if(i.getClass() == Book.class && !i.isAvailable()) {
				Book b = (Book)i;
				b.returnItem();
				u.returnItem(b);
				System.out.println(u.name + " has returned " + b.title);
			}
			else if(i.getClass() == Magazine.class && !i.isAvailable()) {
				Magazine m = (Magazine)i;
				m.returnItem();
				u.returnItem(m);
				
				System.out.println(u.name + " has returned " + m.title);
			}
			else throw new IllegalArgumentException();
		}
		catch(IllegalArgumentException e) {
			System.out.println("This item is already available in the library!");

		}
	}
	
	/* Returns a Book based on its ISBN */
	public Item findItem(String ISBN) {
		/* Search all books and magazines. Return match, null otherwise */
		for(Book b : bookLib)
			if(b.ISBN.equalsIgnoreCase(ISBN)) return b;
		for(Magazine m : magLib)
			if(m.ISBN.equalsIgnoreCase(ISBN)) return m;
		
		throw new IllegalArgumentException("Item does not exist!");
	}
	
	public void print() {
		System.out.println();
		System.out.println("Books:");
		for(Book b : bookLib)
			System.out.println(b.title + " by " + b.author + " --- ISBN: " + b.ISBN + " --- " + b.isAvailable());

		System.out.println();
		System.out.println("Magazines:");
		for(Magazine m : magLib)
			System.out.println(m.title + " by " + m.author + " --- ISBN: " + m.ISBN + " --- " + m.isAvailable());
	}
	
	
}
