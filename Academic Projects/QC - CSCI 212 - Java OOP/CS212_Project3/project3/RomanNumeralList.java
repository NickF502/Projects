package project3;

/**
 * A RomanNumeralList class that is a type of linked list. It is modeled after the linked list with dummy nodes
 * that was taught in the class lecture
 * 
 *  @author Nicholas Farkash
 */

public class RomanNumeralList {

	/** Protected instance variables for the class
	 * 
	 * @param first the first node of the linked list, which is an empty dummy node
	 * @param last the last node of the linked list, which is also an empty dummy node
	 * @param length the number of nodes in the linked list not including the empty dummy nodes
	 */
	
	protected RomanNumeralListNode first;
	protected RomanNumeralListNode last;
	protected int length;
	
	/** A constructor for the RomanNumeralList. It sets first and last to an empty node and the length to 0 */
	public RomanNumeralList() {
		RomanNumeralListNode rln = new RomanNumeralListNode();
		first = rln;
		last = rln;
		length = 0;
	}
	
}
