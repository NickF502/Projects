package src;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class FIFO {

	Queue<Job> FIFOQueue = new LinkedList<Job>();	// The queue FIFO is run on
	Vector<Job> jobStack;
	Vector<Job> finishedStack = new Vector<Job>();
	int quantum = 1;
	int time=0;
	
	
	public FIFO(Vector<Job> js) {
		jobStack = js;
	}
	
	
	public void runFIFO() {

		
		// Recursive base case
		if(FIFOQueue.isEmpty() && time > 250) return;		// O(1) Time Complexity

				
		
		// Add all available Jobs into the queue
		if(!jobStack.isEmpty()) {
			addAvailableJobs();			// O(n) Time Complexity
		}
		
		
		// Run the job in front to completion
		runFrontJob();					// O(1) Time Complexity

		
		// Recursively run FIFO
		runFIFO();
		
		
	}
	
	
	
	public void runFrontJob() {
		// Get the first job
		if(FIFOQueue.isEmpty()) {
			time++;
			return;
		}
		Job frontJob = FIFOQueue.poll();

		
		// Run the job by subtracting time quanta until its remaining time is 0, adding jobs in between each run
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
				FIFOQueue.add(j);
				jobsToRemove.add(j);
			}
		}
		
		if(!jobsToRemove.isEmpty())
			jobStack.removeAll(jobsToRemove);
		
		
		
	}
	
	
	
}
