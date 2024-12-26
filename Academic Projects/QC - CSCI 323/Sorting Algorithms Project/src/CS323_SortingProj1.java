package src;

import java.util.Random;

public class CS323_SortingProj1 {

	// Initialize some variables and arrays
	static Random rand = new Random();

	static int[] mainArray = new int[10000];
	static int[] scaledArray;
	static int[] arr;

	static int compCounter;
	static int swapCounter;
	static Long startTime;
	static Long stopTime;
	
	static int avgSwap;
	static int avgComp;
	static int avgTime;
	
	
	// Create arrays to store the runtimes and comparisons for the 100 trials for each algorithm
	static int[] heapSwaps = new int[100];
	static int[] heapComps = new int[100];
	static int[] heapTimes = new int[100];

	static int[] modHeapSwaps = new int[100];
	static int[] modHeapComps = new int[100];
	static int[] modHeapTimes = new int[100];
	
	static int[] quickSwaps = new int[100];
	static int[] quickComps = new int[100];
	static int[] quickTimes = new int[100];
	
	static int[] modQuickSwaps = new int[100];
	static int[] modQuickComps = new int[100];
	static int[] modQuickTimes = new int[100];
	
	static int[] insertSwaps = new int[100];
	static int[] insertComps = new int[100];
	static int[] insertTimes = new int[100];
	
	
	public static void main(String[] args) {
		
		/* Part 1 */
		// Run experiment 100 times
		for(int i=0; i<1; i++) {
			
			populate(mainArray, 1000000);	// Populate mainArray
					
			/* Run all 4 algorithms on the same array */

			//Heap Sort
			resetVariables();
			arr = mainArray.clone();

			printArray(arr);
			heapSort(arr, true);

			// Store respective data in their arrays
			heapSwaps[i] = swapCounter;
			heapComps[i] = compCounter;			
			heapTimes[i] = (int)(stopTime - startTime);
			
			
			

			
			//Mod-Heap Sort
			resetVariables();
			arr = mainArray.clone();
			
			printArray(arr);
			heapSort(arr, true);

			// Store respective data in their arrays
			modHeapSwaps[i] = swapCounter;
			modHeapComps[i] = compCounter;			
			modHeapTimes[i] = (int)(stopTime - startTime);			
			
			
			//Quick Sort
			resetVariables();
			arr = mainArray.clone();

			startTime = System.nanoTime();
			quickSort(arr, 0, arr.length-1);
			stopTime = System.nanoTime();

			// Store respective data in their arrays
			quickSwaps[i] = swapCounter;
			quickComps[i] = compCounter;
			quickTimes[i] = (int)(stopTime - startTime);
			

			//Mod-Quick Sort with random pivot
			resetVariables();
			arr = mainArray.clone();
			
			startTime = System.nanoTime();
			modQuickSort(arr, 0, arr.length-1);
			stopTime = System.nanoTime();
	
			// Store respective data in their arrays
			modQuickSwaps[i] = swapCounter;
			modQuickComps[i] = compCounter;			
			modQuickTimes[i] = (int)(stopTime - startTime);

		}

		
	
		// Calculate and display averages for Heap Sort
		avgSwap = getArrayAverage(heapSwaps);
		avgComp = getArrayAverage(heapComps);
		avgTime = getArrayAverage(heapTimes);

		System.out.println("Heap Sort:\t Avg. Swaps = " + avgSwap + "\t   "
				+ "Avg. Comparisons = " + avgComp + "\t   Avg. Duration = " + avgTime + "ns");


		// Calculate and display averages for ModHeap Sort
		avgSwap = getArrayAverage(modHeapSwaps);
		avgComp = getArrayAverage(modHeapComps);
		avgTime = getArrayAverage(modHeapTimes);

		System.out.println("Mod-Heap Sort:\t Avg. Swaps = " + avgSwap + "\t   "
				+ "Avg. Comparisons = " + avgComp + "\t   Avg. Duration = " + avgTime + "ns");


		// Calculate and display averages for Quick Sort
		avgSwap = getArrayAverage(quickSwaps);
		avgComp = getArrayAverage(quickComps);
		avgTime = getArrayAverage(quickTimes);

		System.out.println("Quick Sort:\t Avg. Swaps = " + avgSwap + "\t   "
				+ "Avg. Comparisons = " + avgComp + "\t   Avg. Duration = " + avgTime + "ns");


		// Calculate and display averages for ModQuick Sort
		avgSwap = getArrayAverage(modQuickSwaps);
		avgComp = getArrayAverage(modQuickComps);
		avgTime = getArrayAverage(modQuickTimes);

		System.out.println("Mod-Quick Sort:\t Avg. Swaps = " + avgSwap + "\t   "
				+ "Avg. Comparisons = " + avgComp + "\t   Avg. Duration = " + avgTime + "ns");
				
		
		
		System.out.println("\n\n\n\n");
		
		
		
		
		
		/* Part 2 */

		// Run experiment for arrays of size 2^1 --> 2^7
		for(int arraySize = 2; arraySize <= 256; arraySize *= 2) {
			
			// Dynamically scale the array
			scaledArray = new int[arraySize];
			
			// Run 100 trials
			for(int i=0; i<100; i++) {
				
				populate(scaledArray, 1000);	// Populate scaledArray
						
				// Run all 3 algorithms on the same array

				//Insertion Sort
				resetVariables();
				arr = scaledArray.clone();

				startTime = System.nanoTime();
				insertionSort(arr);
				stopTime = System.nanoTime();

				// Store respective data in their arrays
				insertSwaps[i] = swapCounter;
				insertComps[i] = compCounter;			
				insertTimes[i] = (int)(stopTime - startTime);
				
				
				//Heap Sort
				resetVariables();
				arr = scaledArray.clone();

				startTime = System.nanoTime();
				heapSort(arr, false);
				stopTime = System.nanoTime();

				// Store respective data in their arrays
				heapSwaps[i] = swapCounter;
				heapComps[i] = compCounter;			
				heapTimes[i] = (int)(stopTime - startTime);
				
				
				
				//Quick Sort
				resetVariables();
				arr = scaledArray.clone();

				startTime = System.nanoTime();
				quickSort(arr, 0, arr.length-1);
				stopTime = System.nanoTime();

				// Store respective data in their arrays
				quickSwaps[i] = swapCounter;
				quickComps[i] = compCounter;
				quickTimes[i] = (int)(stopTime - startTime);
				
			}
			
			
			// Display the results
			System.out.println("For n = " + arraySize + ":");
			
			// Calculate and display averages for Insertion Sort
			avgSwap = getArrayAverage(insertSwaps);
			avgComp = getArrayAverage(insertComps);
			avgTime = getArrayAverage(insertTimes);

			System.out.println("Insertion Sort:\t Avg. Swaps = " + avgSwap + "  \t   "
					+ "Avg. Comparisons = " + avgComp + "  \t   Avg. Duration = " + avgTime + "ns");
			
						
			// Calculate and display averages for Heap Sort
			avgSwap = getArrayAverage(heapSwaps);
			avgComp = getArrayAverage(heapComps);
			avgTime = getArrayAverage(heapTimes);

			System.out.println("Heap Sort:\t Avg. Swaps = " + avgSwap + "  \t   "
					+ "Avg. Comparisons = " + avgComp + "  \t   Avg. Duration = " + avgTime + "ns");


			// Calculate and display averages for Quick Sort
			avgSwap = getArrayAverage(quickSwaps);
			avgComp = getArrayAverage(quickComps);
			avgTime = getArrayAverage(quickTimes);

			System.out.println("Quick Sort:\t Avg. Swaps = " + avgSwap + "  \t   "
					+ "Avg. Comparisons = " + avgComp + "  \t   Avg. Duration = " + avgTime + "ns\n\n");

		
		}


	}

	
	private static void heapSort(int[] array, boolean isModded) {
		startTime = System.nanoTime();
		
		int heapSize = array.length;
		
		// Build the heap
		for (int i = heapSize/2 - 1; i >= 0; i--) {
			
			// Determine which heapSort to use
			if(isModded == false) {
				heapify(array, heapSize, i);
			}
			else {
				modHeapify(array, heapSize, i);
			}
			
	    }
		
		// Extract the sorted elements
		for (int i = heapSize-1; i > 0; i--) {
			
	        // Swap the current root to the last leaf of the heap
			swap(array, 0, i);
			swapCounter++;

	        // Heapify to reorder the elements with the smaller heap
			if(isModded == false) {
				heapify(array, i, 0);
			}
			else {
				modHeapify(array, i, 0);
			}
	    }
		
		stopTime = System.nanoTime();
		
	}
	
	
	
	private static void modHeapify(int[] array, int heapSize, int rootIndex) {
		int largestIndex = rootIndex;
		int LLChildIndex = 2*rootIndex + 1;	// index of left-left grandchild
		int LRChildIndex = 2*rootIndex + 2;	// index of left-right grandchild
		int RLChildIndex = 2*rootIndex + 3;	// index of right-left grandchild
		int RRChildIndex = 2*rootIndex + 4;	// index of right-right grandchild

		
		// If a grandchild is greater than the root, set it to be the largest
		// Check for bounds as well
		if(LLChildIndex < heapSize && array[LLChildIndex] > array[largestIndex]) {
			compCounter += 2;
			largestIndex = LLChildIndex;
		}
		
		if(LRChildIndex < heapSize && array[LRChildIndex] > array[largestIndex]) {
			compCounter += 2;
			largestIndex = LRChildIndex;
		}
		if(RLChildIndex < heapSize && array[RLChildIndex] > array[largestIndex]) {
			compCounter += 2;
			largestIndex = RLChildIndex;
		}
		
		if(RRChildIndex < heapSize && array[RRChildIndex] > array[largestIndex]) {
			compCounter += 2;
			largestIndex = RRChildIndex;
		}

		// If the largest element is no longer the root, a swap needs to be made
		if(largestIndex != rootIndex) {
			compCounter++;
			swap(array, rootIndex, largestIndex);
			swapCounter++;
			heapify(array, heapSize, largestIndex);
		}
		
		// If no swap has occurred, run regular heapify to check the children
		heapify(array, heapSize, rootIndex);
		
		
	}


	/* Method to build/maintain a heap */
	private static void heapify(int[] array, int heapSize, int rootIndex) {
		int largestIndex = rootIndex;
		int leftChildIndex = 2*rootIndex + 1;	// index of left child
		int rightChildIndex = 2*rootIndex + 2;	// index of right child

		
		// If a child is greater than the root, set it to be the largest
		// Check for bounds as well
		if(leftChildIndex < heapSize && array[leftChildIndex] > array[largestIndex]) {
			compCounter += 2;
			largestIndex = leftChildIndex;
		}
		
		if(rightChildIndex < heapSize && array[rightChildIndex] > array[largestIndex]) {
			compCounter += 2;
			largestIndex = rightChildIndex;
		}

		// If the largest element is not the root, a swap needs to be made
		if(largestIndex != rootIndex) {
			compCounter++;
			swap(array, rootIndex, largestIndex);
			swapCounter++;
			heapify(array, heapSize, largestIndex);
		}
		
		
	}


	
	
	/* quickSort method */
	private static void quickSort(int[] array, int first, int last) {
		if(first < last) {
			compCounter++;
			int piv = pivot(array, first, last);	// returns index of pivot element
			quickSort(array, first, piv-1);	// sort first "half"
			quickSort(array, piv+1, last);	// sort second "half"
		}
		
	}

	
	
	/* pivot method for quickSort Method */
	private static int pivot(int[] array, int first, int last) {
		int pivot = array[first];
		int left = first+1;
		int right = last;
		
		// while the pointers haven't crossed
		while(left <= right) {
			compCounter++;
			
			// move left as long as its lower than the pivot
			while(left <= right && array[left] <= pivot) {
				compCounter += 2;
				left++;
			}
			
			// move right as long as its higher than the pivot
			while(left <= right && array[right] > pivot) {
				compCounter += 2;
				right--;
			}
			
			// if the pointers cross, swap
			if(left < right) {
				compCounter++;
				swap(array, left, right);
				swapCounter++;
			}

		}
		
		// swap to put the partition in place
		swap(array, first, right);
		swapCounter++;
		return right;
	}

	

	/* modQuickSort method - picks a random pivot to avoid worst case */
	private static void modQuickSort(int[] array, int first, int last) {
		if(first < last) {
			compCounter++;
			int piv = modPivot(array, first, last);	// pick a random pivot
			modQuickSort(array, first, piv-1);	// sort first "half"
			modQuickSort(array, piv+1, last);	// sort second "half"
		}		
	}

	
	
	/* pivot method for the modQuickSort method - picks a random pivot to avoid worst case */
	private static int modPivot(int[] array, int first, int last) {
		int pivot = rand.nextInt(last-first)+first;	// pick a random pivot
		swap(array, pivot, last);	// swap it
		swapCounter++;
		pivot = array[last];	//perform quickSort
		
		int left = first + 1;
		int right = last;
		
		while(left <= right) {
			compCounter++;
			
			while(left <= right && array[left] <= pivot) {
				compCounter += 2;
				left++;
			}
			
			while(left <= right && array[right] > pivot) {
				compCounter += 2;
				right--;
			}
			
			if(left < right) {
				compCounter++;
				swap(array, left, right);
			}

		}
		
		swap(array, first, right);
		swapCounter++;
		return right;
	}

	
	
	/* Method for insertion sort */
	private static void insertionSort(int[] array) {
		
		// j will go through all spots before i and compare
		for(int i=1; i<array.length; i++) {
			int currValue = array[i];
			int j = i-1;
			
			// Move elements greater than the current value to the left
			while(j >= 0 && array[j] > currValue) {
				compCounter++;
				array[j+1] = array[j];
				j--;
			}
			array[j+1] = currValue; //swap the value into it correct position
		}
		
		
	}
	
	
	
	/* Method for swapping elements in an array */
	private static void swap(int[] array, int i, int j) {
		swapCounter++;
	    int temp = array[i];
	    array[i] = array[j];
	    array[j] = temp;
	}


	/* Method to get the average of an array */
	private static int getArrayAverage(int[] array) {
		Long avg = 0L;
		for(int x : array) {
			avg += x;
		}
		return (int)(avg/array.length);
	}
	
	
	
	/* Method to reset certain variables for ease */
	private static void resetVariables(){
		compCounter = swapCounter = 0;
	}
	
	
	
	/* Method to populate an array with integers ranging from 1 to maxInt */
	private static void populate(int[] array, int maxInt) {
		
		for(int i=0; i<array.length; i++) {
			array[i] = rand.nextInt(maxInt) + 1;
		}
		
	}
	
	

	private static void printArray(int[] array) {
		for(int i: array) {
			System.out.print(i + ", ");
		}
		System.out.println("\n");

	}

}
