package src;


import java.util.Vector;

public class SRT {
	Vector<Job> SRTHeap = new Vector<Job>();
	HeapSort hs = new HeapSort();
	Vector<Job> jobStack;
	Vector<Job> finishedStack = new Vector<Job>();
	int quantum = 1;
	int time=0;
	
	
	public SRT(Vector<Job> js) {
		jobStack = js;
	}


	public void runSRT() {
		
		// Recursive base case
		if(SRTHeap.isEmpty() && time > 250) return;		// O(1) Time Complexity
				

		// Add all available Jobs into the heap and sort the heap
		if(!jobStack.isEmpty()) {
			addAvailableJobs();			// O(n) Time Complexity
			hs.sort(SRTHeap, 3);		// O(n lg(n)) Time Complexity
		}
		
		
		// Run the job in front
		runFrontJob();					// O(1) Time Complexity
			
		
		// Recursively run SRT
		runSRT();
		
		
	}
	
	
	
	public void runFrontJob() {
		// Get the first job
		if(SRTHeap.isEmpty()) {
			time++;
			return;
		}
		Job frontJob = SRTHeap.remove(0);

		
		// Subtract a time quantum from its remaining time
			frontJob.remainingTime -= quantum;
			time++;

		
		// If it isn't finished running, add it back to the heap and heapify
		if(frontJob.remainingTime != 0) {
			SRTHeap.add(frontJob);
			hs.sort(SRTHeap, 3);
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
				SRTHeap.add(j);
				jobsToRemove.add(j);
			}
		}
		
		if(!jobsToRemove.isEmpty())
			jobStack.removeAll(jobsToRemove);
	
	}
	
}
