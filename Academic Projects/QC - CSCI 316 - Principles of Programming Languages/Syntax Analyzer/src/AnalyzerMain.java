package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AnalyzerMain {

	// Data Structures
	static HashMap<String, String> dataTypes  = new HashMap<String, String>();
	static HashMap<String, String> reservedWords  = new HashMap<String, String>();
	static HashMap<String, String> operators = new HashMap<String, String>();
	static ArrayList<Character> variableCharacters = new ArrayList<Character>();

	
	public static void main(String[] args) {

		// Initialize Data Structures
		dataTypes.put("double", "<DOUBLE>");
		dataTypes.put("int", "<INT>");
		
		reservedWords.put("if", "<IF>");
		reservedWords.put("else", "<ELSE>");

		operators.put("==", "<EQUAL_TO>");
		operators.put(">=", "<GTEQ>");
		operators.put("+=", "<ADD_ASSIGN>");
		operators.put(";", "<SEMICOLON>");
		operators.put(",", "<COMMA>");
		operators.put("(", "<L_PAREN>");
		operators.put(")", "<R_PAREN>");
		operators.put("{", "<L_BRACKET>");
		operators.put("}", "<R_BRACKET>");
		operators.put("+", "<ADD>");
		operators.put("-", "<SUBTRACT>");
		operators.put("*", "<MULTIPLY>");
		operators.put("/", "<DIVIDE>");
		operators.put("=", "<ASSIGN>");
		operators.put("\n", "<NEW_LINE>");


		// Add the lowercase letters
		for (int i = 65; i <= 90; i++) {
			variableCharacters.add((char) i);
		}
		
		// Add the uppercase letters
		for (int i = 97; i <= 122; i++) {
			variableCharacters.add((char) i);
		}

		// Add the digits
		for (int i = 48; i <= 57; i++) {
			variableCharacters.add((char) i);
		}

		// Parse the textfile
		parseFile("src/input.txt/");

	}
	
	
	
	public static void parseFile(String filePath) {
		
		// Data Structures
		ArrayList<String> linesInFile = new ArrayList<String>();
		ArrayList<String> textTokens = new ArrayList<String>();
		ArrayList<String> parsedTokens = new ArrayList<String>();

		// Read the lines from the file and store them in linesInFile
		try {

			// Open the Scanner
			Scanner fromFile = new Scanner(new File(filePath));
			fromFile.useDelimiter("\n");
			
			// While there are more lines to read
			while(fromFile.hasNext()) {
				
				// Get the next line
				linesInFile.add(fromFile.next());
				
			}
			
			fromFile.close();	// Close the Scanner
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		// linesInFile now stores each line at one index

		
		
		// For each line in the text file
		for(String line : linesInFile) {
			
			// Check the line for substrings of length 2 and 1 that match operators
			for(int i=0; i<line.length(); i++) {
				
				// Make substrings to check
				String subLen1 = line.substring(i, i+1);
				String subLen2 = "";
				
				// Can only make a substring of length 2 if we're not on the last character of the word
				if(i < line.length()-1) {
					subLen2 = line.substring(i, i+2);
				}
				
			  	// Check for a space, which separates words
				if(subLen1.equals(" ")) { 
					String subBeforeMatch = line.substring(0, i);	// Separate what was good before the match
					textTokens.add(subBeforeMatch);	// Add what was good before the match to textTokens
					
					line = line.substring(i+1);	// Update the line to start at the character after the length 1 substring
					i = -1;						// Reset the loop variable
					continue;
				}
				
				// Otherwise, check for a length 2 substring match. If found, separate it and continue
				else if(operators.containsKey(subLen2)){
					String subBeforeMatch = line.substring(0, i);	// Separate what was good before the match
					textTokens.add(subBeforeMatch);	// Add what was good before the match to textTokens

					textTokens.add(subLen2);	// Add the matched length 2 substring to textTokens

					line = line.substring(i+2);	// Update the line to start at the character after the length 2 substring
					i = -1;						// Reset the loop variable
					continue;
				}
		
				// Otherwise, check for a length 1 substring match. If found, separate it and continue
				else if(operators.containsKey(subLen1)){	
					String subBeforeMatch = line.substring(0, i);	// Separate what was good before the match
					textTokens.add(subBeforeMatch);	// Add what was good before the match to textTokens

					textTokens.add(subLen1);	// Add the matched length 2 substring to textTokens

					line = line.substring(i+1);	// Update the line to start at the character after the length 1 substring
					i = -1;						// Reset the loop variable
					continue;
				}
				
				// Otherwise, no special operators were found, continue searching

			}	// Finished separating a single line
			
			textTokens.add(line);	// Add the remainder of the line to textTokens
			
		}	// Finished separating all lines
		  
		// Remove any empty spaces in the textTokens, leaving just the tokens
		textTokens.removeIf(String::isEmpty);

		

		// textTokens is now a 1D ArrayList that stores all of the tokens in the file sequentially		
		
		
		
		// Parse each token and store it in parsedTokens
		for(int i=0; i<textTokens.size(); i++){
			String t = textTokens.get(i);
			String p = getToken(t);
			
			// If a "\n" is encountered, designate it as <EOL> for later splitting purposes
			if(p.equals("<UNKNOWN>") && t.length() == 1) {
				parsedTokens.add("<EOL>");
			}
			else {
				parsedTokens.add(p);
			}
		}
		
		
		
		// Create Data Structures to store parsed tokens, with each entry of the 2D ArrayList storing a single line's tokens
		ArrayList<ArrayList<String>> parsedTokensByLine = new ArrayList<ArrayList<String>>();
		ArrayList<String> parsedTemp = new ArrayList<String>();
		
		// Set up parsedTokensByLine to store individual lines, which are separated by the <EOL> just added
		for(int i=0; i<parsedTokens.size(); i++) {

			if(!parsedTokens.get(i).equals("<EOL>")) {
				parsedTemp.add(parsedTokens.get(i));
			}
			else {
				parsedTokensByLine.add(new ArrayList<String>(parsedTemp));
				parsedTemp.clear();
			}
		}
		
		
		
		/* 
		 * parsedTokens is now an ArrayList that stores ArrayList<String>
		 * Each ArrayList<String> stores the tokens for a single line of code
		 */
		
		
		
		// Print the parsed tokens, showing each line of code's breakdown in its rawest form
		printRawParsedArray(parsedTokensByLine);

		print("\n\n\n");
		
		// Print the parsed tokens, showing the type of statement each line of code represents
		printHigherParsedArray(parsedTokensByLine);

	} // parseFile


	
	private static String variableInitialization(ArrayList<String> line) {
		// Method to determine if a line takes the form of an initialization statement
		// Initialization takes the form <TYPE> <VAR> <ASSIGN> <MATH_EXPR> <SEMICOLON>
		
		if(line.size() == 0) return "<NEW_LINE>";	// If the line has no length, it is a new_line token
		
	    int index = 0; // Initialize the index of line to 0
	    

	    // Check for <TYPE>
	    if (line.get(index).equals("<INT>") || line.get(index).equals("<DOUBLE>")) {
	    	index++;
	    	
	    	// Check for <VAR>
	        if (line.get(index++).equals("<VAR>")) {
	        	
	        	// Check for <ASSIGN>
	        	if (line.get(index++).equals("<ASSIGN>")){

	        		// Check for <MATH_EXPR> 
	            	int newIndex = mathExpr(line, index);
	                if (newIndex == line.size()-1) {                	
	                	
	                	
	                	// Check for <SEMICOLON>
	                    if (line.get(newIndex++).equals("<SEMICOLON>")) {
	                	    return "<INITIALIZATION>"; // Line follows the grammar for assignment
	                    }
	                }
	        	}
	        }	    	
	    }
	    
	    // Otherwise, check for a variable declaration statement
	    return variableDeclaration(line);
	}
	


	private static String variableDeclaration(ArrayList<String> line) {
		// Method to determine if a line takes the form of a declaration statement
		// Declaration takes the form <TYPE> <VAR> (<COMMA> <VAR>)* <SEMICOLON>
		
	    int index = 0; // Initialize the index of line to 0

	    // Check for <TYPE>
	    if (line.get(index).equals("<INT>") || line.get(index).equals("<DOUBLE>")) {
	    	index++;

	    	// Check for <VAR>
	        if (line.get(index++).equals("<VAR>")) {

	        	if(line.get(index).equals("<COMMA>")) {
	        		
	        		// Loop on <COMMA> <VAR>
		            while(index + 2 < line.size()) {

		            	if(line.get(index).equals("<COMMA>") && line.get(index+1).equals("<VAR>")) {
		            		index += 2;
		            	}
		        	
		            }
		            
		            // Check for <SEMICOLON>
		            if (line.get(index++).equals("<SEMICOLON>")) {
		            	return "<DECLARATION>"; // Line follows the grammar for declaration is valid
		            }
	        	}	            
	        }
	    }
	    
        // Otherwise, check for a variable assignment statement
	    return variableAssignment(line);
	}
	
	
	private static String variableAssignment(ArrayList<String> line) {
		// Method to determine if a line takes the form of variable assignment
		// Assignment takes the form <VAR> <ASSIGN> <MATH_EXPR> <SEMICOLON>

	    int index = 0; // Initialize the index of line to 0

	    // Check for <VAR>
        if (line.get(index++).equals("<VAR>")) {
        	
        	// Check for <ASSIGN>
            if (line.get(index).equals("<ASSIGN>") || line.get(index).equals("<ADD_ASSIGN>")) {
            	index++;
            	
            	// Check for <MATH_EXPR> 
            	int newIndex = mathExpr(line, index);
                if (newIndex == line.size()-1) {                	

                	// Check for <SEMICOLON>
                    if (line.get(newIndex++).equals("<SEMICOLON>")) {
                	    return "<ASSIGNMENT>"; // Line follows the grammar for initialization
                    }
                }
            }
        }
        
        // Otherwise, check for an if statement
        return ifStatement(line);        
	}
	
	

	public static String ifStatement(ArrayList<String> line) {
		// Method to determine if a line takes the form of an if statement
		// <IF_STMT> takes the form <IF> <BOOL_EXPR>
	    int index = 0; // Initialize the index of line to 0

	    // Check for <IF>
        if (line.get(index++).equals("<IF>")) {
        	
    	    // Check for <BOOL_EXPR>
        	if(boolExpr(line, index)) return "<IF_STMT>";
        }

        // Otherwise, check for an else statement
        return elseStatement(line);
	
	}
	
	
	public static String elseStatement(ArrayList<String> line) {
		// Method to determine if a line is an else statement
		// An else statement takes the form <ELSE>

	    // Check for <ELSE>
        if (line.get(0).equals("<ELSE>")) return "<ELSE>";
        
        // Otherwise, check for a Bracket
        return isBracket(line);
        
	}
	
	
	
	public static String isBracket(ArrayList<String> line) {
		// Method to determine if a line is a bracket
		// Bracket takes the form <L_BRACKET> | <R_BRACKET>

	    // Check for <L_BRACKET>
        if (line.get(0).equals("<L_BRACKET>")) return "<L_BRACKET>";
        
	    // Check for <R_BRACKET>
        if (line.get(0).equals("<R_BRACKET>")) return "<R_BRACKET>";

        // Otherwise, recursive descent has ended with no valid value. Return UNKNOWN
        return "UNKNOWN";
        
	}
	
	
	private static int mathExpr(ArrayList<String> line, int index) {
		// Method to determine if a line contains a mathematical expression
		// math_expr takes the form ( <L_PAREN> | <R_PAREN> | <OP> | <NUM> | <VAR> )*
		
		/* NOTE: I know this is not a proper grammar to determine a mathematical expression, since this
		 * would accept bad entries, like (((((. It doesn't check parenthesis matching or anything like that.
		 * The actual grammar would require a lot more time to flesh out and I don't feel like I have the time
		 * to find a proper solution without outside help, so I chose to submit this instead 
		 */
				
		// Data Structure that stores valid tokens for a mathematical expression
		ArrayList<String> validTokens = new ArrayList<String>();
		validTokens.add("<L_PAREN>");
		validTokens.add("<R_PAREN>");
		validTokens.add("<ADD>");
		validTokens.add("<SUBTRACT>");
		validTokens.add("<MULTIPLY>");
		validTokens.add("<DIVIDE>");
		validTokens.add("<INT>");
		validTokens.add("<DOUBLE>");
		validTokens.add("<VAR>");
		
		
		// Loop on ( <L_PAREN> | <R_PAREN> | <OP> | <NUM> | <VAR> )*
        while(index < line.size() && validTokens.contains(line.get(index))) {	

        	// If there is a L_PAREN
        	if(line.get(index).equals("<L_PAREN>")) {
        		
        		// Check the symbol before the L_PAREN. It cannot be a variable or number
        		String x = line.get(index-1);
        		if(x.equals("<VAR>") || x.equals("<INT>") || x.equals("<DOUBLE>")) {
        			// If it is, return a value of -10. This ensures proper evaluation when it is returned. See comment below.
        			return -10;
        		}
        	}
        	index++;
        }
        
        // Loop has broken. Return the index of the token left off at. Upon return, this index will be checked.
        // If it is one less than the size of the line, that means there is one token left, which is the semicolon
        // or R_PAREN of an IF_STMT.
        return index;
	}
	
	
	
	private static boolean boolExpr(ArrayList<String> line, int index) {
		// Method to determine if a line contains a boolean expression
		// bool_expr takes the form <L_PAREN> (<NUM> | <VAR>) <BOOL_OP> (<NUM> | <VAR>) <R_PAREN>
		
		// Check for <L_PAREN>
		if(line.get(index++).equals("<L_PAREN>")) {
		
			// Check for <NUM> or <VAR>
			if(line.get(index).equals("<INT>") || line.get(index).equals("<DOUBLE>") || line.get(index).equals("<VAR>")) {
				index++;
				
				// Check for <BOOL_OP>
				if(line.get(index).equals("<EQUAL_TO>") || line.get(index).equals("<GTEQ>")) {
					index++;

					// Check for <NUM> or <VAR>
					if(line.get(index).equals("<INT>") || line.get(index).equals("<DOUBLE>") || line.get(index).equals("<VAR>")) {
						index++;
					
						// Check for <R_PAREN>
						if(line.get(index++).equals("<R_PAREN>")) {
							
							// Line contains a valud boolean expression - return true
							return true;
						}
					}
				}
			}
		}
		
		// Line did not contain a valid boolean expression - return false
		return false;
	}



	public static String getToken(String s) {
		// Recursively descending method to determine what String s represents
		
		// Look at the first character of s
		char first = s.charAt(0);

		// If it starts with an integer, check for an integer value
		if ((int) first >= 48 && (int) first <= 57) {	// Cast the char into its ASCII value
			return getNumberType(s);
		}

		// Otherwise, check if the string is a data type
		if(dataTypes.containsKey(s))
			return dataTypes.get(s);
		
		// Otherwise, check if the string is a reserved word
		if(reservedWords.containsKey(s))
			return reservedWords.get(s);
				
		// Otherwise, if it starts with a non-variable character, check for an operator
		if (!variableCharacters.contains(first)) {
			return getOperator(s);
		}
		
		// Otherwise, String s starts with a variable character and is not a "special" word, It is a variable
		return "<VAR>";

	}

	
	
	public static String getOperator(String s) {
		// Method to see if String s is an operator symbol
		
		if (operators.containsKey(s))
			return operators.get(s);
		
		else return "<UNKNOWN>";	// No more recursive calls to make - return <UNKNOWN>
			
	}

	
	
	public static String getNumberType(String s) {
		// Method to see if String s is a number (Integer or Double)
		
		// Check to see if it's an integer
		try {
			Integer.parseInt(s);
		}
		
		// If it isn't an integer, check if it's a double
		catch (NumberFormatException n) {
			try {
				Double.parseDouble(s);
			}
			
			// If it isn't a double, its an invalid string, return <UNKNOWN>
			catch (NumberFormatException m) {
				return "<UNKNOWN>";
			}
			return "<DOUBLE>";	// Successfully parsed Double
		}

		return "<INT>";	// Successfully parsed Integer
	}

	

	public static void print(Object o) {
		// Method to print, because writing "System.out.print()" takes too long
		
		System.out.print(o);
	}
	

	
	public static void printRawParsedArray(ArrayList<ArrayList<String>> arr) {
		// Method to print the raw tokens each line contains
		
		for(int i=0; i<arr.size(); i++) {
			ArrayList<String> currList = arr.get(i); 
			
			for(int j=0; j<currList.size(); j++) {
				print(currList.get(j));
			}
			
			print("\n");
		}
	}
	
	
	
	public static void printHigherParsedArray(ArrayList<ArrayList<String>> arr) {
		// Method to print the highest form of statement each line of code represents
		
		for(ArrayList<String> line : arr) {			
			String x = variableInitialization(line); 
			print(x + "\n");
		}
	}

	
	
}
