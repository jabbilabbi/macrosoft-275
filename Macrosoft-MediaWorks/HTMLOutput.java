import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// HTMLOutput.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alan Lerner

public class HTMLOutput {
	
	// Used for database/current user information
	DatabaseControl db = new DatabaseControl();
	SecurityControl sc = new SecurityControl();
	
	public void createHTMLOutput() {
		
		// Get current user
		String currentUser = sc.retrieveCurrentUser();
		// Name of web page to be created; will have same name as current user
		String pageToCreate = currentUser + ".htm";
		
		// Begin HTML code for web page, including beginning of media library table
		String textToWrite = "<html><head><title>" + currentUser + "'s Media " +
				"Library</title></head><body><h1>" + currentUser + "'s Media " +
				"Library</h1><table>";
		
		// Cycle through media library
		for (int i = 0; i < db.getRowsNeeded(); i++) {
			// Get row information
			String[] rowElements = db.getLibraryRow(i);
			// Start a table row
			textToWrite += "<tr>";
			// Add table elements to the current row
			for (int j = 0; j < 3; j++) {
				textToWrite += "<td>" + rowElements[j] + "</td>";
			}
			// End the current row
			textToWrite += "</tr>";
		}
		
		// Finish table html
		textToWrite += "</table></body></html>";
		
		try {
			BufferedWriter bw = null;
			// Set up BufferedWriter to be used for writing
			bw = new BufferedWriter(new FileWriter(pageToCreate, true));
			// Write username to file
			bw.write(textToWrite);
			// Clear BufferedWriter after writing is complete
			bw.flush();
		} catch (IOException ioe) {
		}
	}
}