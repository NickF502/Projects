package hw3;

import java.util.LinkedList;
import java.util.Queue;

public class PrintQueue {

	
	Queue<PrintJob> queue = new LinkedList<>();
	
	public void enqueue(PrintJob job) {
		queue.add(job);
		System.out.println(job.getDocumentName() + "(" + job.getPages() + " pg.) has been added to the printing queue.");
	}

	public void dequeue() {
		if(!isEmpty()) {
			PrintJob job = queue.remove();
			System.out.println(job.getDocumentName() + "(" + job.getPages() + " pg.) has been printed.");
		}
		else {
			System.out.println("There are no PrintJobs in the queue!");
		}
	}
	
	public void peek() {
		PrintJob job = queue.peek();
		if(job != null)
			System.out.println("Next up: " + job.getDocumentName() + "(" + job.getPages() + " pg.)");
		else
			System.out.println("There are no PrintJobs in the queue!");

			
	}
	
	public boolean isEmpty() {
		if(queue.peek() == null) return true;
		else return false;
	}
}
