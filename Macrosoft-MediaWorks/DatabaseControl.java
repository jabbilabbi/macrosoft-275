// DatabaseControl.java
// Media database control functions for a personal library program
// Includes database creation and addition methods and a method to return database entries

// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alan Lerner

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DatabaseControl {

	// ArrayLists to hold media databases
	protected static ArrayList<String> CDItems = new ArrayList<String>();
	protected static ArrayList<String> DVDItems = new ArrayList<String>();
	protected static ArrayList<String> BookItems = new ArrayList<String>();
	protected static ArrayList<String> GameItems = new ArrayList<String>();
	protected static String currentUser = "currentUser";

	// Storage for media entry database
	private static String fname;

	// Constructor
	public DatabaseControl() {
		updateFileName();
	}

	// Set current user
	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
	
	// Get current user
	public String getCurrentUser() {
		return this.currentUser;
	}
	
	// Update file name with current user
	public void updateFileName() {
		fname = currentUser + ".txt";
	}
	
	// Get current file name
	public String getFileName() {
		return this.fname;
	}
	
	// PURPOSE: Create a database file for a given user; used to store media
	// library entries
	// PRE: None
	// POST: Database file with the name of the user is created

	public void createUserDatabaseFile() {
		// Database file name will consist of user name and .txt
		try {
			BufferedWriter bw = null;
			// Set up BufferedWriter to be used for writing
			bw = new BufferedWriter(new FileWriter(fname, true));
			// Create an empty file
			bw.write("");
			// Clear BufferedWriter after appending is complete
			bw.flush();
			bw.close();
		} catch (IOException ioe) {
		}
	}

	// Load all media databases at once with correct information
	// PRE: Media database is non-empty
	// POST: ArrayLists are filled with corresponding data
	
	public void loadAllDatabases() {
		// Load each database consecutively
		loadMediaDatabase(CDItems, "CD");
		loadMediaDatabase(DVDItems, "DVD");
		loadMediaDatabase(BookItems, "Book");
		loadMediaDatabase(GameItems, "Game");
	}
	
	// Load a given database with corresponding media entries
	// PRE: Current database is non-empty
	// POST: ArrayList for current database is filled with corresponding media information
	
	public void loadMediaDatabase(ArrayList<String> dbType, String typeToGet) {
		// Clear database; important if loading again
		dbType.clear();
		try {
			BufferedReader in = new BufferedReader(new FileReader(fname));
			// Read first line
			String mediaRow = in.readLine();
			do {
				mediaRow = mediaRow.trim();
				// Create array for media elements
				String[] mediaRowElements = null;
				// Split based on separator used in database file
				mediaRowElements = mediaRow.split(" ::: ");
				// Add items to ArrayList
				for (int i = 0; i < mediaRowElements.length; i++) {
					if (mediaRowElements[0].contains(typeToGet)) {
						dbType.add(mediaRowElements[i]);
					}
				}
				mediaRow = in.readLine();
				// Continue reading database lines
			} while (mediaRow != null);

			// Close BufferedReader after operation complete
			in.close();

		} catch (IOException e) {
			// System.out.println(e.getMessage());
		}
	}

	// Reload current database of media entries
	// PRE: None
	// POST: Current ArrayList of media items is cleared and reloaded with
	// entries in database file
	public void reloadMediaDatabase(ArrayList<String> dbType, String typeToReload) {
		// Clear current database
		dbType.clear();
		// Reload database from file
		loadMediaDatabase(dbType, typeToReload);
	}

	// Append media database with a new entry
	// PRE: None
	// POST: Media database file is appended with new row, and database is reloaded
	
	public void appendMediaDatabase(ArrayList<String> dbType, String[] rowElements) {

		// BufferedWriter to write to database
		BufferedWriter bw = null;

		// Join media data elements
		String lineToSave = "";
		int i;
		// Cycle through elements
		for (i=0; i<rowElements.length-1; i++) {
			// Join each element to string for current row
			lineToSave += rowElements[i] + " ::: ";
		}
		// Join last element
		lineToSave += rowElements[i];
		
		try {
			// Set up BufferedWriter to be used for appending
			bw = new BufferedWriter(new FileWriter(fname, true));
			// Append new account data to media database
			bw.write(lineToSave);
			// Go to next line in database file
			bw.newLine();
			// Clear BufferedWriter after appending is complete
			bw.flush();
			bw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		// Reload current database in order to include appended media entry
		reloadMediaDatabase(dbType, rowElements[0]);
	}

	// Determine media index for current database
	// PRE: Current database is non-empty
	// POST: Media index for current database is returned
	
	public int getMediaIndex(ArrayList<String> dbType) {
		int mediaIndex = 0;
		// Check first entry of database to determine which kind of database is being used
		String firstDBEntry = String.valueOf(dbType.get(0));
		
		// Depending on type of first entry, set proper media index
		if (firstDBEntry.contains("CD")) {
			mediaIndex = 5;
		} else if (firstDBEntry.contains("DVD")){
			mediaIndex = 7;
		} else if (firstDBEntry.contains("Book")) {
			mediaIndex = 6;
		} else if (firstDBEntry.contains("Game")) {
			mediaIndex = 8;
		}
		// Return result
		return mediaIndex;
	}
	
	// Helper for getLibraryRow
	// Uses quick String check to determine which database is currently being polled, then
	// calls getLibraryRow on it
	// PRE: Database to be polled is non-empty
	// POST: An array containing the elements of the current row is returned
	
	public String[] getLibraryRow(int indexOfRow, String currentDB) {
		
		// Default return
		String[] defaultReturn = {""};
		// Check which database is to be polled, and call appropriate getLibraryRow
		if (currentDB == "CD") {
			return getLibraryRow(CDItems, indexOfRow);
		} else if (currentDB == "DVD") {
			return getLibraryRow(DVDItems, indexOfRow);
		} else if (currentDB == "Book") {
			return getLibraryRow(BookItems, indexOfRow);
		} else if (currentDB == "Game") {
			return getLibraryRow(GameItems, indexOfRow);
		} else {
			return defaultReturn;
		}
	}
	
	// Return an array containing the elements of the current database row
	// PRE: Database is non-empty
	// POST: An array containing the elements of the current row is returned

	public String[] getLibraryRow(ArrayList<String> dbType, int indexOfRow) {
		// Determine index of current row using mediaIndex
		int mediaIndex = getMediaIndex(dbType);
		int currentIndex = 0;
		
		// Determines which element of database the current row begins at
		currentIndex = indexOfRow + indexOfRow * (mediaIndex - 1);
		
		// Create array to hold row elements
		String mediaRowElements[] = new String[mediaIndex];

		// Fill array with information for current row
		for (int i = 0; i < mediaIndex; i++) {
			mediaRowElements[i] = dbType.get(currentIndex + i);
		}

		// Return array containing row elements
		return mediaRowElements;
	}

	// Return the total number of rows to display
	// PRE: None
	// POST: Number of rows required is returned

	public int getRowsNeeded(ArrayList<String> dbType) {
		// Number of rows needed is total items / items per row
		int totalRows = dbType.size() / getMediaIndex(dbType);
		// Return result
		return totalRows;
	}

	// PURPOSE: Delete current database file
	// PRE: None
	// POST: Current user's database is deleted

	public void deleteDatabase() {
		// Open file
		File userFile = new File(fname);
		// Perform deletion
		userFile.delete();
	}

	// Delete given row of media database
	// PRE: Row exists in database
	// POST: Media database file has row deleted; media database is reloaded

	public void deleteRow(ArrayList<String> dbType, String[] rowToDelete, String typeToReload) {
		// Delete is performed using edit function
		// Simply edit line to be deleted into a ""
		String[] editTo = {""};
		editRow(dbType, rowToDelete, editTo, typeToReload);
	}
	
	// Join String[] array elements into one String
	// PRE: None
	// POST: A String is returned containing all array elements separated by " ::: "
	
	public String stringJoiner(String[] stringToJoin) {
		
		// Empty array, return empty string
		if (stringToJoin.length == 1) {
			return "";
		}
		// String to be output
		String joinedString = "";
		int j;
		// Cycle through array elements
		for (j=0; j < stringToJoin.length-1; j++) {
			// Add each element to output string
			joinedString += stringToJoin[j] + " ::: ";
		}
		// Add last element
		joinedString += stringToJoin[j] + "\r\n";
		// Return result
		return joinedString;
		
	}
	
	// Edit current database row information
	// PRE: rowToDelete exists in database
	// POST: Database row is replaced with the editted information
	
	public void editRow(ArrayList<String> dbType, String[] rowToDelete, String[] editTo, String typeToReload) {
		try {
			// Read in database information
			BufferedReader in = new BufferedReader(new FileReader(fname));
			// Variable for database text
			String dbText = "";
			// Read in all lines of database
			String mediaRow = in.readLine();
			do {
				dbText += mediaRow;
				dbText += "\r\n";
				mediaRow = in.readLine();
			} while (mediaRow != null);
			
			// BufferedReader no longer needed
			in.close();
			
			// Delete old database
			deleteDatabase();
			
			// Join array elements into whole strings
			String completeRowToDelete = stringJoiner(rowToDelete);
			String completeRowToEditTo = stringJoiner(editTo);
			
			// Replace old text for new
			dbText = dbText.replaceFirst(completeRowToDelete, completeRowToEditTo);

			BufferedWriter bw = null;
			// Set up BufferedWriter
			bw = new BufferedWriter(new FileWriter(fname, true));
			// Write to database
			bw.write(dbText);
			// Close BufferedWriter after operation complete
			bw.flush();
			bw.close();
			
			// Reload database to ensure changes are taken into effect
			reloadMediaDatabase(dbType, typeToReload);
		} catch (IOException e) {
		}
	}
}