package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import code.Dataset;
import code.DecisionTree;
import code.Node;
import code.UserData;

/**
 * @author Nicholas Farkash
 */

class DecisionTreeTest {

	@Test
	void BootstrapDataNormalSizeTest() {

		Dataset mother = new Dataset();

		String[] originalTestEntries = new String[]{
				"40,M,ATA,140,289,0,Normal,172,N,0,Up,0",
				"50,F,NAP,160,180,0,Normal,156,N,1,Flat,1",
				"38,M,NAP,130,211,0,Normal,142,N,0,Up,0",
				"63,F,ATA,120,204,0,Normal,145,N,0,Up,0",
				"41,M,ATA,130,283,0,ST,98,N,0,Up,0",
				"44,F,ASY,138,214,0,Normal,108,Y,1.5,Flat,1",
				"41,M,ATA,136,164,0,ST,99,Y,2,Flat,1",
				"51,F,ASY,140,234,0,Normal,140,Y,1,Flat,1",
				"56,M,NAP,150,195,0,Normal,122,N,0,Up,0",
				"52,F,NAP,120,339,0,Normal,170,N,0,Up,0",
		};
		 
		for(String e : originalTestEntries) {
			mother.add(new UserData(e));
		}
		
		// Create the expectation
		int expected = 50;
		
		// Create the actual
		DecisionTree tree = new DecisionTree(mother);		
		int actual = tree.getBootstrappedData().size();
		
		// Assert that expected equals actual 	
		assertEquals(expected, actual);
	}
	
	
	
	@Test
	void BootstrapDataNormalSubsetTest() {

		Dataset mother = new Dataset();

		String[] originalTestEntries = new String[]{
				"40,M,ATA,140,289,0,Normal,172,N,0,Up,0",
				"50,F,NAP,160,180,0,Normal,156,N,1,Flat,1",
				"38,M,NAP,130,211,0,Normal,142,N,0,Up,0",
				"63,F,ATA,120,204,0,Normal,145,N,0,Up,0",
				"41,M,ATA,130,283,0,ST,98,N,0,Up,0",
				"44,F,ASY,138,214,0,Normal,108,Y,1.5,Flat,1",
				"41,M,ATA,136,164,0,ST,99,Y,2,Flat,1",
				"51,F,ASY,140,234,0,Normal,140,Y,1,Flat,1",
				"56,M,NAP,150,195,0,Normal,122,N,0,Up,0",
				"52,F,NAP,120,339,0,Normal,170,N,0,Up,0",
		};
		 
		for(String e : originalTestEntries) {
			mother.add(new UserData(e));
		}
		
		// Create the expectation
		boolean expected = true;

		
		// Create the actual
		DecisionTree tree = new DecisionTree(mother);
		boolean actual = true;
		for(UserData entry : tree.getBootstrappedData()) {
			if(!mother.contains(entry)) {
				actual = false;
				break;
			}
		}
		
		// Assert that expected equals actual 	
		assertEquals(expected, actual);
	}
		
	
	
	@Test
	void GrowTreeTree() {

		Dataset mother = new Dataset();

		String[] originalTestEntries = new String[]{
				"40,M,ATA,140,289,0,Normal,172,N,0,Up,0",
				"50,F,NAP,160,180,0,Normal,156,N,1,Flat,1",
				"38,M,NAP,130,211,0,Normal,142,N,0,Up,0",
				"63,F,ATA,120,204,0,Normal,145,N,0,Up,0",
				"41,M,ATA,130,283,0,ST,98,N,0,Up,0",
				"44,F,ASY,138,214,0,Normal,108,Y,1.5,Flat,1",
				"41,M,ATA,136,164,0,ST,99,Y,2,Flat,1",
				"51,F,ASY,140,234,0,Normal,140,Y,1,Flat,1",
				"56,M,NAP,150,195,0,Normal,122,N,0,Up,0",
				"52,F,NAP,120,339,0,Normal,170,N,0,Up,0",
		};
		 
		for(String e : originalTestEntries) {
			mother.add(new UserData(e));
		}
		
		
		// Create two trees and ensure their nodeLists are equal
		DecisionTree tree1 = new DecisionTree(mother);		
		DecisionTree tree2 = new DecisionTree(mother);		
		
		boolean expected = true;
		boolean actual = true;
		
		for(Node n : tree1.getNodeList()) {
			if(tree2.getNodeList().contains(n)) {
				actual = false;
				break;
			}
		}
		
		// Assert that expected equals actual 	
		assertEquals(expected, actual);
	}
		
	
	
	@Test
	void getClassificationTest() {

		Dataset mother = new Dataset();

		String[] originalTestEntries = new String[]{
				"40,M,ATA,140,289,0,Normal,172,N,0,Up,0",
				"50,F,NAP,160,180,0,Normal,156,N,1,Flat,1",
				"38,M,NAP,130,211,0,Normal,142,N,0,Up,0",
				"63,F,ATA,120,204,0,Normal,145,N,0,Up,0",
				"41,M,ATA,130,283,0,ST,98,N,0,Up,0",
				"44,F,ASY,138,214,0,Normal,108,Y,1.5,Flat,1",
				"41,M,ATA,136,164,0,ST,99,Y,2,Flat,1",
				"51,F,ASY,140,234,0,Normal,140,Y,1,Flat,1",
				"56,M,NAP,150,195,0,Normal,122,N,0,Up,0",
				"52,F,NAP,120,339,0,Normal,170,N,0,Up,0",
		};
		 
		
		for(String e : originalTestEntries) {
			mother.add(new UserData(e));
		}
		
		DecisionTree tree = new DecisionTree(mother);		

		UserData testUser = new UserData("40,M,ATA,140,289,0,Normal,172,N,0,Up,0");

		
		// Create the expectation
		int expected = 0;
		int actual = tree.getClassification(testUser);
		
		// Assert that expected equals actual 	
		assertEquals(expected, actual);
	}
		
		
	
		
}