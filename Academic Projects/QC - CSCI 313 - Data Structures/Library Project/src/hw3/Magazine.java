package hw3;

public class Magazine implements Item{

	public boolean isCheckedOut = false;
	public String title;
	public String author;
	public String ISBN;
	
	/* Default Constructor */
	public Magazine() {
		title = author = ISBN = null;
	}
	
	/* Full Constructor*/
	public Magazine(String t, String a, String i) {
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
