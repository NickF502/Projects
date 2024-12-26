package project3;

/**
 * A class that contains the add method for the SortedRomanNumeralList. The sorted linked list requires nodes to
 * be placed in order of increasing integer value of the numeral, so it requires a more complicated method than
 * the unsorted linked list does.
 * 
 *  @author Nicholas Farkash
 */

public class SortedRomanNumeralList extends RomanNumeralList{
	
	/** The add method determines where the new node needs to be inserted into the SortedRomanNumeralList 
	 * such that the linked list remains sorted in terms of increasing Arabic / integer value of the numeral
	 * 
	 * @param nodeToAdd the node that is being inserted into the linked list
	 * */
	public void add(RomanNumeral nta) {
		
	    RomanNumeralListNode nodeToAdd = new RomanNumeralListNode(nta);	// Create a new node whose data is the new Roman Numeral
	    boolean notFirst = false;
	    
	    /** If the list is empty, make nodeToAdd the first element of the linked list after the dummy head node*/
	    if (first.next == null) {
	        first.next = nodeToAdd;
	    }
	    else {
	    	
	    	/** If the list is not empty, place the node such that the order of value is preserved
		     * @param checkNode a node in the linked list whose data is being compared to nodeToAdd.
		     * 			In a sense, it acts as a sort of iterator
		     */
	    	
	        RomanNumeralListNode checkNode = first.next;	// Create a new node to compare values against
	        while (checkNode != null && checkNode.data.compareTo(nodeToAdd.data) < 0) {	// If we haven't reached the end of the list and the new node's value is less than the current checkNode's value...
	            
	        	notFirst = true;
	        	if (checkNode.next == null || checkNode.next.data.compareTo(nodeToAdd.data) > 0) {	// If checkNode is the last in the linked list or the next value is greater than the checkNode...
	                break;	// exit the while loop so the node can be inserted
	            }
	        	
	            checkNode = checkNode.next;	// The new node doesn't belong here. Move checkNode one spot over and loop
	        }
	        
	        //* Insert the new node to preserve order */
	        if(notFirst) {
	        	nodeToAdd.next = checkNode.next;
		        checkNode.next = nodeToAdd;
	        }
	        else {
	        	first.next = nodeToAdd;
	        	nodeToAdd.next = checkNode;
	        }
	    }
	    
	    length++; // Increment the length parameter of the linked list
	}

}
