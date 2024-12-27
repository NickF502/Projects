package code;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Nicholas Farkash
 */

public class Main {
	

	
	public static void main(String[] args) {
		
		// This block is used to run the software and should not be commented out unless the GUI is being used below
		
        // Wait for the user to finish input
        String[] userInput = {"55", "M", "ASY", "155", "137", "1", "Normal", "133", "0", "0.7", "Down", "0"};

        // Run the prediction logic
        Object[] results = run(userInput);

        int prediction = (int) results[0];
        UserData averageValues = (UserData) results[1];

        // Display the results on the GUI
        SOP("Prediction: " + prediction + "\nUserData: " + new UserData(userInput) + "\nAverage: " + averageValues);   
        
        
        
        
		// This commented block runs the built-in GUI. It should stay commented unless testing the backend
		
		/*
		// Create and display the GUI
        GUI g = new GUI();


        // Run the logic in a separate thread to ensure GUI remains responsive
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            // Wait for the user to finish input
            String[] userInput = g.getUserResponses();

            // Show "Please wait" message
            g.showMessage("Calculating Results - Please wait...");

            // Run the prediction logic
            Object[] results = run(userInput);

            int prediction = (int) results[0];
            UserData averageValues = (UserData) results[1];

            // Display the results on the GUI
            g.displayResults(prediction, new UserData(userInput), averageValues);
        });
        */
		
    }
    
    private static Object[] run(String[] userInput) {
    	
        // Import the dataset
    	SOP("Importing Dataset...");
        String originalDataFilePath = "src/data/HeartScope_Dataset.csv";
    	Dataset dataset = new Dataset(originalDataFilePath, true);
    	SOP("Dataset Created\n");


    	// Write the newly formatted dataset to "HeartScope_formatted.csv"
    	String path = "src/data/HeartScope_formatted.csv";
    	saveToCSV(dataset, path);
    	    	
    	// Split the Dataset
    	SOP("Splitting Dataset...");
    	Validation validation = new Validation(dataset);
    	SOP("Dataset Split\n");
    	
    	
    	
    	// Create and Train Random Forest
    	SOP("Creating and Training Random Forest...");
    	RandomForest rf = new RandomForest(validation);
    	SOP("Random Forest Created and Trained\n");

    	
    	
    	// Validate the RandomForest
    	SOP("Validating Random Forest...");
    	float error = rf.calculateInSampleError();
    	SOP("Validation Complete - Error: " + error + "\n");
    	
    	for(String s : userInput) {
            System.out.print(s + ", ");
        }
    	
    	// Convert user's input to a UserData
    	SOP("Converting user's input to UserData...");
        UserData user = new UserData(userInput);
    	SOP("UserData: " + user + "\n");

    	
   
    	// Make a prediction for the user
    	SOP("Making Prediction...");
        int prediction = rf.predict(user);
    	SOP("Prediction: " + prediction + "\n");

    	
    	
    	// Get additional information for that user
    	SOP("Calculating Hypothetical 'Average' User...");
    	UserData averageValues = user.getAdditionalInformation(dataset);
    	SOP("Avg Values: " + averageValues + "\n");   	
    	
    	
    	
    	// Return the prediction and additional information to the GUI
    	Object[] results = {prediction, averageValues};
    	return results;
    }
    
    
    
    private static void saveToCSV(Dataset dataset, String path) {
    	try (FileWriter writer = new FileWriter(path)) {
            for (UserData entry : dataset) {
            	String e = entry.toString();
            	String eSub = e.substring(1, e.length()-1);
                writer.write(eSub);
                writer.write("\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }		
	}

    
    
	private static void SOP(Object o) {
    	System.out.println(o);
    }
    
    private static void SOP(String[] O) {
		for(String s : O) {
			System.out.print(s + ", ");
		}
		SOP("");
	}
}

