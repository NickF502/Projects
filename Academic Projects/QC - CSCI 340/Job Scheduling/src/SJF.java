package src;


import java.util.Vector;

public class SJF {
	Vector<Job> SJFHeap = new Vector<Job>();
	HeapSort hs = new HeapSort();
	Vector<Job> jobStack;
	Vector<Job> finishedStack = new Vector<Job>();
	int quantum = 1;
	int time=0;
	
	
	public SJF(Vector<Job> js) {
		jobStack = js;
	}


	public void runSJF() {
		
		// Recursive base case
		if(SJFHeap.isEmpty() && time > 250) return;		// O(1) Time Complexity
				

		// Add all available Jobs into the heap and sort the heap
		if(!jobStack.isEmpty()) {
			addAvailableJobs();			// O(n) Time Complexity
			hs.sort(SJFHeap, 1);		// O(n lg(n)) Time Complexity
		}
		
		
		// Run the job in front to completion
		runFrontJob();					// O(1) Time Complexity

		
		// Recursively run SJF
		runSJF();
		
		
	}
	
	
	
	public void runFrontJob() {
		// Get the first job
		if(SJFHeap.isEmpty()) {
			time++;
			return;
		}
		
		
		Job frontJob = SJFHeap.remove(0);



		
		// Run the job by subtracting time quanta until its remaining time is 0, checking for new jobs in between
		while(frontJob.remainingTime > 0) {
			frontJob.remainingTime -= quantum;
			time++;
			addAvailableJobs();
		}
		
		// When it finishes running, update its data
		frontJob.exitTime = time;
		frontJob.setTurnaroundTime();
		finishedStack.add(frontJob);
		
	}
	
	
	public void addAvailableJobs() {
		Vector<Job> jobsToRemove = new Vector<Job>();
		
		for (Job j : jobStack) {
			if(j.arrivalTime <= time) {
				SJFHeap.add(j);
				jobsToRemove.add(j);
			}
		}
		
		if(!jobsToRemove.isEmpty())
			jobStack.removeAll(jobsToRemove);
	
	}
	
}
