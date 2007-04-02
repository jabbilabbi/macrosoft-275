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

	// ArrayList to hold media database
	protected static ArrayList<String> CDItems = new ArrayList<String>();
	protected static ArrayList<String> DVDItems = new ArrayList<String>();
	protected static  ArrayList<String> BookItems = new ArrayList<String>();
	protected static  ArrayList<String> GameItems = new ArrayList<String>();
	protected static String currentUser = "currentUser";

	// Allow access of retrieveCurrentUser function
	//private ControllerClass controller;

	// Storage for media entry database
	private static String fname;

	// Constructor
	public DatabaseControl() {
		updateFileName();
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
	
	public String getCurrentUser() {
		return this.currentUser;
	}
	
	public void updateFileName() {
		fname = currentUser + ".txt";
	}
	
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

	public void loadAllDatabases() {
		loadMediaDatabase(CDItems, "CD");
		loadMediaDatabase(DVDItems, "DVD");
		loadMediaDatabase(BookItems, "Book");
		loadMediaDatabase(GameItems, "Game");
	}
	
	// Load database of media entries into an HashSet, allowing them to be read
	// when displaying
	// user's media library
	// PRE: The filename of the media database
	// POST: The media database is loaded into an ArrayList
	public void loadMediaDatabase(ArrayList<String> dbType, String typeToGet) {
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
	// PRE: The filename of the media database, and the information to be added
	// POST: Media database file is appended, and media database is reloaded
	public void appendMediaDatabase(ArrayList<String> dbType, String[] rowElements) {

		BufferedWriter bw = null;

		// Join media data elements
		String lineToSave = "";
		int i;
		
		for (i=0; i<rowElements.length-1; i++) {
			lineToSave += rowElements[i] + " ::: ";
		}
		lineToSave += rowElements[i];
		
		try {
			// Set up BufferedWriter to be used for appending
			bw = new BufferedWriter(new FileWriter(fname, true));
			// Append new account data to login database
			bw.write(lineToSave);
			// Go to next line in database file
			bw.newLine();
			// Clear BufferedWriter after appending is complete
			bw.flush();
			bw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		// Reload database in order to include appended media entry
		reloadMediaDatabase(dbType, rowElements[0]);
	}

	public int getMediaIndex(ArrayList<String> dbType) {
		int mediaIndex = 0;
		String firstDBEntry = String.valueOf(dbType.get(0));
		
		if (firstDBEntry.contains("CD")) {
			mediaIndex = 5;
		} else if (firstDBEntry.contains("DVD")){
			mediaIndex = 7;
		} else if (firstDBEntry.contains("Book")) {
			mediaIndex = 6;
		} else if (firstDBEntry.contains("Game")) {
			mediaIndex = 8;
		}
		return mediaIndex;
	}
	
	public String[] getLibraryRow(int indexOfRow, String currentDB) {
		
		String[] defaultReturn = {""};
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
	
	// Return an array containing the four elements of the current database row
	// PRE: The index (ArrayList position) of the current row
	// POST: An array containing the elements of the current row is returned

	public String[] getLibraryRow(ArrayList<String> dbType, int indexOfRow) {
		// Determine index of current row; row 0 starts at index 0, row 1 starts
		// at 4, row 2
		// starts at 8, etc.
		int mediaIndex = getMediaIndex(dbType);
		int currentIndex = 0;
		
		currentIndex = indexOfRow + indexOfRow * (mediaIndex - 1);
		
		// Create array to hold row elements
		String mediaRowElements[] = new String[mediaIndex];

		// Fill array with name, artist, genre, and description of current row
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

	// Delete last row of media database
	// PRE: None
	// POST: Media database file has last row deleted; media database is
	// reloaded

	public void deleteRow(ArrayList<String> dbType, String[] rowToDelete, String typeToReload) {
		String[] editTo = {""};
		editRow(dbType, rowToDelete, editTo, typeToReload);
	}
	
	public String stringJoiner(String[] stringToJoin) {
		if (stringToJoin.length == 1) {
			System.out.println("bleh");
			return "";
		}
		String joinedString = "";
		int j;
		for (j=0; j < stringToJoin.length-1; j++) {
			joinedString += stringToJoin[j] + " ::: ";
		}
		joinedString += stringToJoin[j] + "\r\n";
		return joinedString;
		
	}
	
	public void editRow(ArrayList<String> dbType, String[] rowToDelete, String[] editTo, String typeToReload) {
		try {
			// Remove last row of current ArrayList
//			int deletionOffset = getMediaIndex(dbType);
//			int startPoint = dbType.size() - deletionOffset;
//			for (int i = 0; i < deletionOffset; i++) {
//				dbType.remove(startPoint + i);
//			}
			// Rewrite database with -1 lines
			BufferedReader in = new BufferedReader(new FileReader(fname));
			String dbText = "";
			String mediaRow = in.readLine();
			do {
				dbText += mediaRow;
				dbText += "\r\n";
				mediaRow = in.readLine();
			} while (mediaRow != null);
			
			in.close();
			
			deleteDatabase();
			
			String completeRowToDelete = stringJoiner(rowToDelete);
			String completeRowToEditTo = stringJoiner(editTo);
			
			dbText = dbText.replaceFirst(completeRowToDelete, completeRowToEditTo);

			// Write
			BufferedWriter bw = null;
			// Set up BufferedWriter
			bw = new BufferedWriter(new FileWriter(fname, true));
			bw.write(dbText);
			bw.flush();
			bw.close();
			
			reloadMediaDatabase(dbType, typeToReload);
		} catch (IOException e) {
		}
	}
}