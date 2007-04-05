import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// HTMLOutput.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alan Lerner

public class HTMLOutput {
	
	//Used for database/current user information
	//ControllerClass controller;
	
	DatabaseControl db = new DatabaseControl();
	
	// Get current program user
	public String getCurrentUser() {
		return db.getCurrentUser();
	}
	
	// Create HTML output file of database information
	// PRE: None
	// POST: Web page file is created in program directory
	
	public void createHTMLOutput(boolean createCDTable, boolean createDVDTable, boolean createBookTable, boolean createGameTable) {
		
		// Ensure latest filename in use
		db.updateFileName();
		
		// Load database information
		db.loadAllDatabases();
		
		// Get current user
		String currentUser = getCurrentUser();
		// Web page filename; same as user
		String pageToCreate = currentUser + ".htm";
		
		// Begin HTML code for web page, including beginning of media library table
		String textToWrite = "<html><head><title>" + currentUser + "'s Media " +
				"Library</title></head><body><font face=Arial><h1>" + currentUser + "'s Media " +
				"Library</h1><font font=Arial>";
		
		// Check databases; data is only output for a given database if the database is
		// not empty
		if (createCDTable == true && db.CDItems.size() > 0) {
			// Add HTML data for current database to output string
			textToWrite += createHTMLTable(db.CDItems, "CD");
		}
		if (createDVDTable == true && db.DVDItems.size() > 0) {
			textToWrite += createHTMLTable(db.DVDItems, "DVD");
		}
		if (createBookTable == true && db.BookItems.size() > 0) {
			textToWrite += createHTMLTable(db.BookItems, "Book");
		}
		if (createGameTable == true && db.GameItems.size() > 0) {
			textToWrite += createHTMLTable(db.GameItems, "Game");
		}
		
		// Delete previous HTML page
		File oldHTML = new File(pageToCreate);
		oldHTML.delete();
		
		try {
			BufferedWriter bw = null;
			// Set up BufferedWriter to be used for writing
			bw = new BufferedWriter(new FileWriter(pageToCreate, true));
			// Write HTML file
			bw.write(textToWrite);
			// Clear BufferedWriter after writing is complete
			bw.flush();
			bw.close();
		} catch (IOException ioe) {
		}
	}
	
	// Get HTML code for a given database to be output in a web page file
	// PRE: Current database is non-empty
	// POST: String is returned containg HTML text for current database
	
	public String createHTMLTable(ArrayList<String> dbType, String tableTypeToCreate) {

		// HTML separator; create space between tables
		String tableOutput = "<br>";
		// Variable for table background colour
		String typeColour = "";
		
		// Check current table type, and set up table headings accordingly
		if (tableTypeToCreate == "CD") {
			tableOutput += "<table width=700 bgcolor=black><tr>" +
			"<td width=200 bgcolor=white><b><font size=4>Type</font></b></td>" +
			"<td width=200 bgcolor=white><b><font size=4>Title</font></b></td>" +
			"<td width=200 bgcolor=white><b><font size=4>Artist</font></b></td>" +
			"<td width=150 bgcolor=white><b><font size=4>Genre</font></b></td>" +
			"<td width=200 bgcolor=white><b><font size=4>Description</font></b></td></tr>";
			typeColour = "magenta";
		} else if (tableTypeToCreate == "DVD") {
			tableOutput += "<table width=800 bgcolor=black><tr>" +
			"<td width=200 bgcolor=white><b><font size=4>Type</font></b></td>" +
			"<td width=300 bgcolor=white><b><font size=4>Title</font></b></td>" +
			"<td width=200 bgcolor=white><b><font size=4>Director</font></b></td>" +
			"<td width=150 bgcolor=white><b><font size=4>Genre</font></b></td>" +
			"<td width=200 bgcolor=white><b><font size=4>Stars</font></b></td>" +
			"<td width=200 bgcolor=white><b><font size=4>Rating</font></b></td>" +
			"<td width=200 bgcolor=white><b><font size=4>Description</font></b></td></tr>";
			typeColour = "9999cc";
		} else if (tableTypeToCreate == "Book") {
			tableOutput += "<table width=700 bgcolor=black><tr>" +
			"<td width=200 bgcolor=white><b><font size=4>Type</font></b></td>" +
			"<td width=300 bgcolor=white><b><font size=4>Title</font></b></td>" +
			"<td width=200 bgcolor=white><b><font size=4>Author</font></b></td>" +
			"<td width=150 bgcolor=white><b><font size=4>Genre</font></b></td>" +
			"<td width=200 bgcolor=white><b><font size=4>Publisher</font></b></td>" +
			"<td width=200 bgcolor=white><b><font size=4>Description</font></b></td></tr>";
			typeColour = "ff9900";
		} else if (tableTypeToCreate == "Game") {	
			tableOutput += "<table width=700 bgcolor=black><tr>" +
			"<td width=200 bgcolor=white><b><font size=4>Type</font></b></td>" +
			"<td width=300 bgcolor=white><b><font size=4>Title</font></b></td>" +
			"<td width=200 bgcolor=white><b><font size=4>Developer</font></b></td>" +
			"<td width=150 bgcolor=white><b><font size=4>Genre</font></b></td>" +
			"<td width=200 bgcolor=white><b><font size=4>Publisher</font></b></td>" +
			"<td width=200 bgcolor=white><b><font size=4>Platform</font></b></td>" +
			"<td width=200 bgcolor=white><b><font size=4>ESRB</font></b></td>" +
			"<td width=200 bgcolor=white><b><font size=4>Description</font></b></td></tr>";
			typeColour = "66cc33";
		}

		// Get current media index
		int mediaIndex = db.getMediaIndex(dbType);
		
		// Cycle through media library and create HTML rows as needed
		for (int i = 0; i < db.getRowsNeeded(dbType); i++) {
			// Get row information
			String[] rowElements = db.getLibraryRow(dbType, i);
			// Start a table row
			tableOutput += "<tr>";
			// Create HTML to add table elements to the current row
			for (int j = 0; j < mediaIndex; j++) {
				tableOutput += "<td valign=top bgcolor=" + typeColour + ">" + rowElements[j] + "</td>";
			}
			// End the current row
			tableOutput += "</tr>";
		}
		
		// Finish table html
		tableOutput += "</table>";
		
		// Return result
		return tableOutput;

	}
}