package src;

import java.util.Random;

public class Job {

	
	protected int arrivalTime;
	protected int cpuBurst;
	protected int priority;
	protected int exitTime;
	protected int turnaroundTime;
	protected int remainingTime;
	
	Random rand = new Random();
	
	
	public Job() {
		this.arrivalTime = rand.nextInt(250) + 1;
		this.cpuBurst= rand.nextInt(14) + 2;
		this.priority = rand.nextInt(4) + 1;
		this.exitTime = -1;
		this.turnaroundTime = this.getTurnaroundTime();
		this.remainingTime = this.cpuBurst;
	}
	
	
	public Job(int a, int b, int p) {
		this.arrivalTime = a;
		this.cpuBurst= b;
		this.priority = p;
		this.exitTime = -1;
		this.turnaroundTime = this.getTurnaroundTime();
		this.remainingTime = this.cpuBurst;
	}
	
	public String toString() {
		return "[" + this.arrivalTime + ",\t " + this.cpuBurst + ",\t " + this.priority + ",\t " + 
					this.exitTime + ",\t " + this.turnaroundTime + ",\t" + this.remainingTime + "]\n";
	}
	
	/* Method to compare two jobs
	 * parameterBeingCompared is int value used to determine weight
	 * 0 = arrival time
	 * 1 = cpu burst
	 * 2 = external priority
	 * 3 = remaining time
	 */
	public boolean compareTo(Job j, int measure) {
		switch (measure) {
	        case 0:
	            return (this.arrivalTime > j.arrivalTime) ? true:false;
	        case 1:
	            return (this.cpuBurst > j.cpuBurst) ? true:false;
	        case 2:
	        	return (this.priority > j.priority) ? true:false;
	        case 3:
	            return (this.remainingTime > j.remainingTime) ? true:false;
	        default:
	            return true;
		}
	}
	
	public int getTurnaroundTime() {
		return this.exitTime - this.arrivalTime;
	}
	
	public void setTurnaroundTime() {
		this.turnaroundTime = this.getTurnaroundTime();
	}
	
	
	
}
