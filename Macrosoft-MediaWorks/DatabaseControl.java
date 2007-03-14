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
	private ArrayList<String> mediaItems = new ArrayList<String>();

	// Allow access of retrieveCurrentUser function
	private SecurityControl sdb = new SecurityControl();

	// Storage for media entry database
	private String fname;

	// Used for testing
	public String getFname() {
		return fname;
	}

	// PURPOSE: Create a database file for a given user; used to store media
	// library entries
	// PRE: None
	// POST: Database file with the name of the user is created

	public void createUserDatabaseFile() {
		// Database file name will consist of user name and .txt
		String userDatabaseToCreate = sdb.retrieveCurrentUser() + ".txt";

		try {
			BufferedWriter bw = null;
			// Set up BufferedWriter to be used for writing
			bw = new BufferedWriter(new FileWriter(userDatabaseToCreate, true));
			// Create an empty file
			bw.write("");
			// Clear BufferedWriter after appending is complete
			bw.flush();
			bw.close();
		} catch (IOException ioe) {
		}
	}

	// Update file name based on user name
	// PRE: None
	// POST: fname is updated to reflect current user of the program
	public void updateFileName() {
		// A text file is used with the same name as the person logged in
		fname = sdb.retrieveCurrentUser() + ".txt";
	}

	// Constructor
	public DatabaseControl() {

	}

	// Load database of media entries into an HashSet, allowing them to be read
	// when displaying
	// user's media library
	// PRE: The filename of the media database
	// POST: The media database is loaded into an ArrayList
	public void loadMediaDatabase() {
		updateFileName();
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
					mediaItems.add(mediaRowElements[i]);
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
	// POST: Current ArrayList of media items is cleared and reloaded with entries in database file
	public void reloadMediaDatabase() {
		// Clear current database
		mediaItems.clear();
		// Reload database from file
		loadMediaDatabase();
	}

	// Append media database with a new entry
	// PRE: The filename of the media database, and the information to be added
	// POST: Media database file is appended, and media database is reloaded
	public void appendMediaDatabase(String title, String artist, String genre,
			String description) {

		BufferedWriter bw = null;
		// Ensure file name is current
		updateFileName();

		// Join media data elements
		String lineToSave = title + " ::: " + artist + " ::: " + genre
				+ " ::: " + description;

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
		reloadMediaDatabase();
	}

	// Return an array containing the four elements of the current database row
	// PRE: The index (ArrayList position) of the current row
	// POST: An array containing the elements of the current row is returned

	public String[] getLibraryRow(int indexOfRow) {
		// Determine index of current row; row 0 starts at index 0, row 1 starts
		// at 4, row 2
		// starts at 8, etc.
		int currentIndex = indexOfRow + indexOfRow * 3;
		// Create array to hold row elements
		String mediaRowElements[] = new String[4];

		// Fill array with name, artist, genre, and description of current row
		for (int i = 0; i < 4; i++) {
			mediaRowElements[i] = mediaItems.get(currentIndex + i);
		}

		// Return array containing row elements
		return mediaRowElements;
	}

	// Return the total number of rows to display
	// PRE: None
	// POST: Number of rows required is returned
	
	public int getRowsNeeded() {
		// Number of rows needed is total items / items per row
		int totalRows = mediaItems.size() / 4;
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
	// POST: Media database file has last row deleted; media database is reloaded
	
	public void deleteLastRow() {

		try {
			deleteDatabase();
			BufferedWriter bw = null;
			
			// Set up BufferedWriter to be used for appending
			bw = new BufferedWriter(new FileWriter(fname, true));
			// Append new account data to login database
			for (int i = 0; i < getRowsNeeded() - 1; i++) {
				String[] currentRow = getLibraryRow(i);
				String rowToWrite = currentRow[0] + " ::: " + currentRow[1] + " ::: " + currentRow[2] +
				" ::: " + currentRow[3];
				bw.write(rowToWrite);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (IOException ioe) {
		}

	}

	// *** Unfinished code for future version ***

	// // Return the number of rows required to display all entries of a certain
	// type
	// // PRE: The media type to be displayed (e.g. CD)
	// // POST: Number of rows required is returned
	// public int getRowsNeededByType(String mediaType) {
	//		
	// // Temporary counter
	// int counter = 0;
	//		
	// // Cycle through database elements, checking the type of media
	// for (int i = 1; i < mediaItems.size(); i += 4) {
	// // Compare media type of current entry to desired type
	// int typeCheck = mediaItems.get(i).compareTo(mediaType);
	// // Entry with given media found; increment counter
	// if (typeCheck == 0) {
	// counter++;
	// }
	// }
	//		
	// // Return result
	// return counter;
	// }

	// public void editLibraryEntry(String oldName, String newName, String
	// oldArtist,
	// String newArtist, String oldGenre, String newGenre, String oldDesc,
	// String newDesc) {
	//		
	// if (mediaItems.contains(oldName)) {
	// String artistRequired = mediaItems.get(mediaItems.indexOf(oldName) + 1);
	// int checkArtist = oldArtist.compareTo(artistRequired);
	// String genreRequired = mediaItems.get(mediaItems.indexOf(oldName) + 2);
	// int checkGenre = oldGenre.compareTo(genreRequired);
	// String descRequired = mediaItems.get(mediaItems.indexOf(oldName) + 3);
	// int checkDesc = oldDesc.compareTo(descRequired);
	//			
	// if (checkArtist == 0 && checkGenre == 0 && checkDesc == 0) {
	//				
	// }
	// }
	// }
}