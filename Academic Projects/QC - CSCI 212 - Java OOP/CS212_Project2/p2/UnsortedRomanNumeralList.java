package p2;

/**
 * A class that contains the append method for the UnsortedRomanNumeralList. The unsorted linked list requires nodes to
 * be placed in the same order as the numerals appear in input.txt
 * 
 *  @author Nicholas Farkash
 */

public class UnsortedRomanNumeralList extends RomanNumeralList {

	/** Append the new node to the end of the linked list 
	 * 
	 * @param nodeToAdd the node that is being inserted into the linked list
	*/
	public void append(RomanNumeral nodeToAdd) {
		RomanNumeralListNode n = new RomanNumeralListNode(nodeToAdd);
		
		last.next = n;	// make the current last node point to the new node
		last = n;		// give the new node the label of last
		length++;		// increment the length variable
	} 
	
	
}
