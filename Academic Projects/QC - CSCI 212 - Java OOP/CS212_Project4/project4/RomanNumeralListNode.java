package project4;

/**
 * A RomanNumeralListNode class that contains constructors for creating the nodes of the linked list.
 * The node contains two parts, data and next, as well as three constructors to be utilized, depending
 * on what information is available at the time
 * 
 *  @author Nicholas Farkash
 */

public class RomanNumeralListNode {
	
	/**
	 * Protected instance variables for the class
	 * 
	 * @param data the content of the node, which is a RomanNumeral
	 * @param next the node that follows this node in the linked list
	 */
	protected RomanNumeral data;
	protected RomanNumeralListNode next;
	
	/** Two parameter constructor that sets both data and next to whatever the user passes through */
	public RomanNumeralListNode(RomanNumeral d, RomanNumeralListNode n) {
		this.data = d;
		this.next = n;
	}
	
	/** Zero parameter constructor that sets both data and next to null */
	public RomanNumeralListNode() {
		this.data = null;
		this.next = null;
	}
	
	/** One parameter constructor that sets the data field; next is set to null */
	public RomanNumeralListNode(RomanNumeral d) {
		this.data = d;
		this.next = null;
	}
	
}