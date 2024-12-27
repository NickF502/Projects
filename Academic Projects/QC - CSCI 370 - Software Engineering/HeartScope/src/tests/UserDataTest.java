package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import code.Dataset;
import code.UserData;

/**
 * @author Nicholas Farkash
 */

class UserDataTest {

	
	Dataset d;
	
	@Test
	void fullUserData() {
    	
		// Create the expectation
		ArrayList<Number> expected = new ArrayList<Number>() {{
			add(55);
			add(1);
			add(1);
			add(0);
			add(0);
			add(0);
			add(130);
			add(215);
			add(0);
			add(0);
			add(0);
			add(1);
			add(132);
			add(1);
			add(0.0f);
			add(0);
			add(0);
			add(1);
			add(0);
		}};
		
		// Create the actual
		String[] test = {"55", "F", "ATA", "130", "215", "0", "LVH", "132", "Y", "0.0", "Up", "0"};
    	UserData actual = new UserData(test);

    	// Assert that expected equals actual
    	assertEquals(expected, actual);
	}
	
	@Test
	void partialUserData() {
    	
		// Create the expectation
		ArrayList<Number> expected = new ArrayList<Number>() {{
			add(55);
			add(1);
			add(1);
			add(0);
			add(0);
			add(0);
			add(130);
			add(245);
			add(0);
			add(1);
			add(0);
			add(0);
			add(132);
			add(0);
			add(0.0f);
			add(0);
			add(1);
			add(0);
			add(0);
		}};
		
		// Create the actual
		String[] test = {"55", "F", "ATA", "130", "", "0", "", "132", "", "", "", ""};
    	UserData actual = new UserData(test);

    	// Assert that expected equals actual
    	assertEquals(expected, actual);
	}
	
	
	
	@Test
	void returnUserDataComplete() {
		
		// Create the expectation
		String[] expected = {"40", "0", "1", "0", "0", "0", "140", "289", "0", "1", "0", "0", "172", "0", "0.0", "0", "0", "1", "0"};

		
		// Create the actual
		String[] test = {"40", "M", "ATA", "140", "289", "0", "Normal", "172", "N", "0", "Up", "0"};
    	UserData testUser = new UserData(test);
    	String[] actual = testUser.returnUserData();
    	
    	// Assert that expected equals actual
    	assertArrayEquals(expected, actual);
	}


}
