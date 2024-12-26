package src;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class RRNC {

	Queue<Job> RRNCQueue = new LinkedList<Job>();	// The queue RRNC is run on
	Vector<Job> jobStack;
	Vector<Job> finishedStack = new Vector<Job>();
	int quantum = 1;
	int time=0;
	
	
	public RRNC(Vector<Job> js) {
		jobStack = js;
	}
	
	public void runRRNC() {
		
		// Recursive base case
		if(RRNCQueue.isEmpty() && time > 250) return;	// O(1) Time Complexity
				
		
		// Add all available Jobs into the queue
		if(!jobStack.isEmpty()) {
			addAvailableJobs();	// O(n) Time Complexity
		}
		
		
		// Run the job in front
		runFrontJob();			// O(1) time Complexity

		
		// Recursively run RRNC
		runRRNC();
		
		
	}
	
	
	
	public void runFrontJob() {
		// Get the first job
		if(RRNCQueue.isEmpty()) {
			time++;
			return;
		}
		
		Job frontJob = RRNCQueue.poll();

		
		// Subtract a time quantum from its remaining time
		frontJob.remainingTime -= quantum;
		time++;
		
		// If it isn't finished running, add it back into the queue
		if(frontJob.remainingTime != 0) {
			RRNCQueue.add(frontJob);
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
				RRNCQueue.add(j);
				jobsToRemove.add(j);
			}
		}
		
		if(!jobsToRemove.isEmpty())
			jobStack.removeAll(jobsToRemove);
		
		
		
	}
	
	
}
