package src;


import java.util.Vector;

public class HighestPriority {
	Vector<Job> HighestPriorityHeap = new Vector<Job>();
	HeapSort hs = new HeapSort();
	Vector<Job> jobStack;
	Vector<Job> finishedStack = new Vector<Job>();
	int quantum = 1;
	int time=0;
	
	
	public HighestPriority(Vector<Job> js) {
		jobStack = js;
	}


	public void runHighestPriority() {
		
		// Recursive base case
		if(HighestPriorityHeap.isEmpty() && time > 250) return;		// O(1) Time Complexity
				
		// Add all available Jobs into the heap and sort the heap
		if(!jobStack.isEmpty()) {
			addAvailableJobs();					// O(n) Time Complexity
			hs.sort(HighestPriorityHeap, 2);	// O(n lg(n)) Time Complexity
		}
		
		
		// Run the job in front
		runFrontJob();							// O(1) Time Complexity

		
		// Recursively run HighestPriority
		runHighestPriority();
		
		
	}
	
	
	
	public void runFrontJob() {
		// Get the first job
		if(HighestPriorityHeap.isEmpty()) {
			time++;
			return;
		}
		Job frontJob = HighestPriorityHeap.remove(0);

		
		// Subtract a time quantum from its remaining time
		frontJob.remainingTime -= quantum;
		time++;

		
		// If it isn't finished running, add it back to the heap and heapify
		if(frontJob.remainingTime != 0) {
			HighestPriorityHeap.add(frontJob);
			hs.sort(HighestPriorityHeap, 2);
		}
		// If it is finished running, update its data
		else {
			frontJob.exitTime = time;
			frontJob.setTurnaroundTime();
			finishedStack.add(frontJob);
		}
			
			
	}
	
	
	public void addAvailableJobs() {
		Vector<Job> jobsToRemove = new Vector<Job>();
		
		for (Job j : jobStack) {
			if(j.arrivalTime <= time) {
				HighestPriorityHeap.add(j);
				jobsToRemove.add(j);
			}
		}
		
		if(!jobsToRemove.isEmpty())
			jobStack.removeAll(jobsToRemove);
	
	}
	
}
