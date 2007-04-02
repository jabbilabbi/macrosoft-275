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
	
	// Used for database/current user information
	//ControllerClass controller;
	
	DatabaseControl db = new DatabaseControl();
	
	public String getCurrentUser() {
		return db.getCurrentUser();
	}
	
	public void createHTMLOutput(boolean createCDTable, boolean createDVDTable, boolean createBookTable, boolean createGameTable) {
		
		db.setCurrentUser("Joe1");
		
		// Print out Joe1.txt
		db.updateFileName();
		
		db.loadAllDatabases();
		
		String currentUser = getCurrentUser();
		String pageToCreate = currentUser + ".htm";
		

	
		// Begin HTML code for web page, including beginning of media library table
		String textToWrite = "<html><head><title>" + currentUser + "'s Media " +
				"Library</title></head><body><font face=Arial><h1>" + currentUser + "'s Media " +
				"Library</h1><font font=Arial>";
		
		if (createCDTable) {
			textToWrite += createHTMLTable(db.CDItems, "CD");
		}
		if (createDVDTable) {
			textToWrite += createHTMLTable(db.DVDItems, "DVD");
		}
		if (createBookTable) {
			textToWrite += createHTMLTable(db.BookItems, "Book");
		}
		if (createGameTable) {
			textToWrite += createHTMLTable(db.GameItems, "Game");
		}
		
		// Delete previous HTML page
		File oldHTML = new File(pageToCreate);
		oldHTML.delete();
		
		try {
			BufferedWriter bw = null;
			// Set up BufferedWriter to be used for writing
			bw = new BufferedWriter(new FileWriter(pageToCreate, true));
			// Write username to file
			bw.write(textToWrite);
			// Clear BufferedWriter after writing is complete
			bw.flush();
			bw.close();
		} catch (IOException ioe) {
		}
	}
	
	public String createHTMLTable(ArrayList<String> dbType, String tableTypeToCreate) {
		//ControllerClass controller = new ControllerClass();
		//controller.loadMediaDatabase(dbType);

		String tableOutput = "<br>";
		String typeColour = "";
		
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