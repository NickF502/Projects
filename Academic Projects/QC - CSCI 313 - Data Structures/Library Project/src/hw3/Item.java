package hw3;

public interface Item{
	
	boolean isAvailable = true;
	
	
	/* Mark the item as checked out */
	public void checkout();
	
	/* Mark the item as available */
	public void returnItem();
	
	/* Check if the item is available for checkout */
	public boolean isAvailable();

	
}
