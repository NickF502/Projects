package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import code.Dataset;
import code.UserData;

/**
 * @author Nicholas Farkash
 */

class DatasetTest {
	

	@Test
	void LinkCSVNoValidPath() {
		
		// Create the expectation
		int expectation = -1;
		
		// Create the actual
		String filePath = "src/data/thisfiledoesnotexist.csv";
		Dataset d = new Dataset();
		int actual = d.linkCSV(filePath);
	   	
		// Assert that expected equals actual 	
		assertEquals(expectation, actual);
	}
	
	
	
	@Test
	void LinkCSVNotCSVFile() {
		
		// Create the expectation
		int expectation = -2;
		
		// Create the actual
		String filePath = "src/data/not_a_csv.txt";
		Dataset d = new Dataset();
		int actual = d.linkCSV(filePath);
	   	
		// Assert that expected equals actual 	
		assertEquals(expectation, actual);
	}
	
	
	
	@Test
	void LinkCSVNormal() {
		
		// Create the expectation
		int expectation = 0;
		
		// Create the actual
		String filePath = "src/data/empty_csv.csv";
		Dataset d = new Dataset();
		int actual = d.linkCSV(filePath);
	   	
		// Assert that expected equals actual 	
		assertEquals(expectation, actual);
	}
	
	
	
	
	@Test
	void ImportDataNoContent() {
		
		// Create the expectation
		Dataset expectation = new Dataset();
		
		// Create the actual
		String filePath = "src/data/empty_csv.csv";
		Dataset actual = new Dataset();
		actual.linkCSV(filePath);
		actual.importData(false);
	   	
		// Assert that expected equals actual 	
		assertEquals(expectation, actual);
	}
	
	
	
	@Test
	void ImportDataHeaders() {
		
		// Create the expectation
		Dataset expectation = new Dataset();
		UserData u = new UserData("40,M,ATA,140,289,0,Normal,172,N,0,Up,0");
		expectation.add(u);
		
		// Create the actual
		String filePath = "src/data/header_csv.csv";
		Dataset actual = new Dataset();
		actual.linkCSV(filePath);
		actual.importData(true);
		
		// Assert that expected equals actual 	
		assertEquals(expectation, actual);
	}
	
	
	
	@Test
	void ImportDataNoHeaders() {
		
		// Create the expectation
		Dataset expectation = new Dataset();
		UserData u = new UserData("40,M,ATA,140,289,0,Normal,172,N,0,Up,0");
		expectation.add(u);
		
		// Create the actual
		String filePath = "src/data/no_header_csv.csv";
		Dataset actual = new Dataset();
		actual.linkCSV(filePath);
		actual.importData(false);
		
		// Assert that expected equals actual 	
		assertEquals(expectation, actual);
	}
	
	

	
	

}
