package src;

import java.util.Vector;

public class JobSchedulingMain {

	static int NUM_JOBS = 25;
	
	static int[] FIFOTT = new int[NUM_JOBS];
	static int[] SJFTT = new int[NUM_JOBS];
	static int[] SRTTT = new int[NUM_JOBS];
	static int[] HPTT = new int[NUM_JOBS];
	static int[] RRTT = new int[NUM_JOBS];
	static int[] RRNCTT = new int[NUM_JOBS];
	
	static int FIFOAvgTurnaroundTime = 0;
	static int SJFAvgTurnaroundTime = 0;
	static int SRTAvgTurnaroundTime = 0;
	static int HPAvgTurnaroundTime = 0;
	static int RRAvgTurnaroundTime = 0;
	static int RRNCAvgTurnaroundTime = 0;
	
	static int FIFOThroughput = 0;
	static int SJFThroughput = 0;
	static int SRTThroughput = 0;
	static int HPThroughput = 0;
	static int RRThroughput = 0;
	static int RRNCThroughput = 0;

	
	public static void main(String[] args) {

		// Print the Original Jobs
		SOPL("Original Job Information:\n");
		SOPL("Arr.\tBurst\tPrior.\tExit\tTAT\tRem.");
	    
		// Create a vector of 25 jobs
		Vector<Job> jobStack = new Vector<Job>();
		
		for(int i=0; i<NUM_JOBS; i++) {
			Job j = new Job();
			jobStack.add(j);
		}
		
		printVector(jobStack);
		SOPL("\n\n");
		
		
		
		// Perform the various algorithms
		doFIFO(jobStack);
		doSJF(jobStack);
		doSRT(jobStack);
        doHP(jobStack);
		doRR(jobStack);        
		doRRNC(jobStack);
		
		
		// Create the table
		createDataTable();
        
	}
		



	private static void doFIFO(Vector<Job> jobStack) {
		// Run FIFO on a copy of the vector and print the results
 		Vector<Job> FIFOCopy = getVectorCopy(jobStack);
 		FIFO f = new FIFO(FIFOCopy);
        f.runFIFO();
         
        // Calculate average turnaround time for RR
		for(Job j : f.finishedStack) {
			FIFOAvgTurnaroundTime += j.getTurnaroundTime();
		}
		FIFOAvgTurnaroundTime /= f.finishedStack.size();
		
		 
		 
		// Calculate throughput value for FIFO
		for(Job j : f.finishedStack) {
			if(j.arrivalTime >= 100 && j.exitTime <= 200)
				FIFOThroughput++;
		}
		
		
		// Store the turnaround times in FIFOTT[]
		for(int i=0; i<f.finishedStack.size(); i++) {
			FIFOTT[i] = f.finishedStack.get(i).getTurnaroundTime();
		}
   		
	}


	private static void doSJF(Vector<Job> jobStack) {
		// Run SJF on a copy of the vector and print the results
		Vector<Job> SJFCopy = getVectorCopy(jobStack);
		SJF sjf = new SJF(SJFCopy);
		sjf.runSJF();
		 
		// Calculate average turnaround time for RR
		for(Job j : sjf.finishedStack) {
			SJFAvgTurnaroundTime += j.getTurnaroundTime();
		}
		SJFAvgTurnaroundTime /= sjf.finishedStack.size();
		
		 
		 
		// Calculate throughput value for SJF
		for(Job j : sjf.finishedStack) {
			if(j.arrivalTime >= 100 && j.exitTime <= 200)
				SJFThroughput++;
		}
		 
		
		// Store the turnaround times in SJFTT[]
		for(int i=0; i<sjf.finishedStack.size(); i++) {
			SJFTT[i] = sjf.finishedStack.get(i).getTurnaroundTime();
		} 
		
	}


	private static void doSRT(Vector<Job> jobStack) {
		// Run SRT on a copy of the vector and print the results
		Vector<Job> SRTCopy = getVectorCopy(jobStack);
		SRT srt = new SRT(SRTCopy);
		srt.runSRT();
		 
		// Calculate average turnaround time for RR
		for(Job j : srt.finishedStack) {
			SRTAvgTurnaroundTime += j.getTurnaroundTime();
		}
		SRTAvgTurnaroundTime /= srt.finishedStack.size();
		
		 
		 
		// Calculate throughput value for SRT
		for(Job j : srt.finishedStack) {
			if(j.arrivalTime >= 100 && j.exitTime <= 200)
				SRTThroughput++;
		}
		 
		
		// Store the turnaround times in SRTTT[]
		for(int i=0; i<srt.finishedStack.size(); i++) {
			SRTTT[i] = srt.finishedStack.get(i).getTurnaroundTime();
		}
	
				
	}
	
	
	private static void doHP(Vector<Job> jobStack) {
		// Run HP on a copy of the vector and print the results
		Vector<Job> HPCopy = getVectorCopy(jobStack);
		HighestPriority hp = new HighestPriority(HPCopy);
		hp.runHighestPriority();
		 
		// Calculate average turnaround time for RR
		for(Job j : hp.finishedStack) {
			HPAvgTurnaroundTime += j.getTurnaroundTime();
		}
		HPAvgTurnaroundTime /= hp.finishedStack.size();
		
		 
		 
		// Calculate throughput value for HP
		for(Job j : hp.finishedStack) {
			if(j.arrivalTime >= 100 && j.exitTime <= 200)
				HPThroughput++;
		}
		 
		
		// Store the turnaround times in HPTT[]
		for(int i=0; i<hp.finishedStack.size(); i++) {
			HPTT[i] = hp.finishedStack.get(i).getTurnaroundTime();
		}
				
	}
	
	
	private static void doRR(Vector<Job> jobStack) {
		// Run RR on a copy of the vector and print the results
 		Vector<Job> RRCopy = getVectorCopy(jobStack);
 		RR rr = new RR(RRCopy);
        rr.runRR();
         
        // Calculate average turnaround time for RR
		for(Job j : rr.finishedStack) {
			RRAvgTurnaroundTime += j.getTurnaroundTime();
		}
		RRAvgTurnaroundTime /= rr.finishedStack.size();
		
		 
		 
		// Calculate throughput value for RR
		for(Job j : rr.finishedStack) {
			if(j.arrivalTime >= 100 && j.exitTime <= 200)
				RRThroughput++;
		}
		 
		
		// Store the turnaround times in RRTT[]
		for(int i=0; i<rr.finishedStack.size(); i++) {
			RRTT[i] = rr.finishedStack.get(i).getTurnaroundTime();
		}
						
	}


	private static void doRRNC(Vector<Job> jobStack) {
		// Run RRNC on a copy of the vector and print the results
		Vector<Job> RRNCCopy = getVectorCopy(jobStack);
		RRNC rrnc = new RRNC(RRNCCopy);
        rrnc.runRRNC();
        
        // Calculate average turnaround time for RRNC
        for(Job j : rrnc.finishedStack) {
        	RRNCAvgTurnaroundTime += j.getTurnaroundTime();
        }
        RRNCAvgTurnaroundTime /= rrnc.finishedStack.size();

        
        
        // Calculate throughput value for RRNC
        for(Job j : rrnc.finishedStack) {
        	if(j.arrivalTime >= 100 && j.exitTime <= 200)
        		RRNCThroughput++;
        }
        
        
        // Store the turnaround times in RRNCTT[]
		for(int i=0; i<rrnc.finishedStack.size(); i++) {
			RRNCTT[i] = rrnc.finishedStack.get(i).getTurnaroundTime();
		}

	}



	private static void createDataTable() {
        SOPL("\n\n\n");  
        SOPL("\t\tTurnaround Time Table (in Q)\n");
		SOPL("Job#\tFIFO\tSJF\tSRT\tHP\tRR\tRRNC");
        SOPL("------------------------------------------------------");  
		
        // Print the turnaround time for each job on each algorithm
		for(int i=0; i < NUM_JOBS; i++) {
			SOPL(i + "\t" + FIFOTT[i]+ "\t" + SJFTT[i]+ "\t" + SRTTT[i]+ "\t" + HPTT[i]+ "\t" + RRTT[i]+ "\t" + RRNCTT[i]);
	        SOPL("------------------------------------------------------");  

		}
		
		// Print the average turnaround time for each algorithm
		SOPL("Avg:\t" + FIFOAvgTurnaroundTime + "\t" + SJFAvgTurnaroundTime + "\t" + SRTAvgTurnaroundTime + "\t" + HPAvgTurnaroundTime + 
				"\t" + RRAvgTurnaroundTime + "\t" + RRNCAvgTurnaroundTime + "\t");
        SOPL("------------------------------------------------------");
        
        // Print the throughput time, how many jobs ran between t=100 and t=200
        SOPL("Thru:\t" + FIFOThroughput + "\t" + SJFThroughput + "\t" + SRTThroughput + "\t" + HPThroughput + 
				"\t" + RRThroughput + "\t" + RRNCThroughput + "\t");
        SOPL("------------------------------------------------------");  
		SOPL("Job#\tFIFO\tSJF\tSRT\tHP\tRR\tRRNC");

		
	}




	public static Vector<Job> getVectorCopy(Vector<Job> v){
		Vector<Job> newVector = new Vector<Job>();
		
		for(Job j : v) {
			Job newJob = new Job(j.arrivalTime, j.cpuBurst, j.priority);
	        newVector.add(newJob);
		}
			
		return newVector;
	}
	
	

	public static void SOP(Object o) {
		System.out.print(o);
	}
	
	public static void SOPL(Object o) {
		System.out.println(o);
	}
	
	
	public static void printVector(Vector<Job> jobStack) {
    	for(Job j : jobStack)
    		SOP(j);
	}
	
	
}