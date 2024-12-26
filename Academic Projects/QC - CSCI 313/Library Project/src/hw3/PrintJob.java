package hw3;

public class PrintJob {

	public String documentName; // The name of the document to be printed.
	public int pages; // The number of pages in the document
	
	
	public PrintJob(String n, int p) {
		documentName = n;
		pages = p;
	}
	
	public String getDocumentName() {
		return documentName;
	}
	
	public int getPages() {
		return pages;
	}
}
