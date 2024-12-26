package hw3;

public class Book implements Item{

	public boolean isCheckedOut = false;
	public String title;
	public String author;
	public String ISBN;
	
	/* Default Constructor */
	public Book() {
		title = author = ISBN = null;
	}
	
	/* Full Constructor*/
	public Book(String t, String a, String i) {
		title = t;
		author = a;
		ISBN = i;
	}
	
	@Override
	public void checkout() {
		isCheckedOut = true;
	}

	@Override
	public void returnItem() {
		isCheckedOut = false;
	}

	@Override
	public boolean isAvailable() {
		return !isCheckedOut;
	}

}
