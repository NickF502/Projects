package src;

import java.util.Vector;

public class HeapSort {
	
	
	/* Method to compare two jobs
	 * parameterBeingCompared is int value used to determine weight
	 * 0 = arrival time
	 * 1 = cpu burst
	 * 2 = external priority
	 * 3 = remaining time
	 */
    public void sort(Vector<Job> array, int measure) {
    	
        int heapSize = array.size();

		// Build the heap
		for (int i = heapSize/2 - 1; i >= 0; i--) {
            heapify(array, heapSize, i, measure);
		}
		
		// Extract the sorted elements
        for (int i = heapSize - 1; i > 0; i--) {
        	
	        // Swap the current root to the last leaf of the heap
            swap(array, 0, i);

	        // Heapify to reorder the elements with the smaller heap
            heapify(array, i, 0, measure);
        }
    }

    
	/* Method to build/maintain a heap */
    void heapify(Vector<Job> array, int heapSize, int rootIndex, int measure) {
    	int largestIndex = rootIndex;
		int leftChildIndex = 2*rootIndex + 1;	// index of left child
		int rightChildIndex = 2*rootIndex + 2;	// index of right child


		// If a child is greater than the root, set it to be the largest
		// Check for bounds as well
        if (leftChildIndex < heapSize && array.elementAt(leftChildIndex).compareTo(array.elementAt(largestIndex), measure))
        	largestIndex = leftChildIndex;

        if (rightChildIndex < heapSize && array.elementAt(rightChildIndex).compareTo(array.elementAt(largestIndex), measure))
        	largestIndex = rightChildIndex;

		// If the largest element is not the root, a swap needs to be made
        if (largestIndex != rootIndex) {
			swap(array, rootIndex, largestIndex);

            // Recursively heapify the affected sub-tree
            heapify(array, heapSize, largestIndex, measure);
        }
    }


	
    
    
    
    /* Method for swapping elements in an array */
	private static void swap(Vector<Job> array, int i, int j) {
	    Job temp = array.elementAt(i);
	    array.set(i,array.elementAt(j)); 
	    array.set(j, temp); 
	}
    
    
}
