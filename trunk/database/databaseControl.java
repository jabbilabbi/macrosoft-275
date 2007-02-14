// Database control functions for a personal library program
// Includes database and login control methods, password checker, and a method to return database
// entries

// Alan Lerner, Alex Antonio, Scott Fuoco, Alex Androne
// CMPT 275, SFU Surrey

package Project2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class databaseControl {

	// ArrayLists to hold media and login databases
	public ArrayList<String> mediaItems = new ArrayList<String>();
	public ArrayList<String> loginItems = new ArrayList<String>();
	
	// ------------------------------------------------------------------
	
	// Load database of media entries into an HashSet, allowing them to be read when displaying
	// user's media library
	// PRE: The filename of the media database
	// POST: The media database is loaded into an ArrayList
	public void loadMediaDatabase(String fname) {
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

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// ------------------------------------------------------------------
	
	// Append media database with a new entry
	// PRE: The filename of the media database, and the information to be added
	// POST: The media database is appended with the new entry
	public void saveMediaDatabase(String fname, String title, String artist, String genre, String description) {
		
	    BufferedWriter bw = null;
		
	    // Join media data elements
	    String lineToSave = title + " ::: " + artist + " ::: " + genre + " ::: " +
	    description;
	    
		try {
			// Set up BufferedWriter to be used for appending
			bw = new BufferedWriter(new FileWriter(fname, true));
			// Append new account data to login database
			bw.write(lineToSave);
			// Go to next line in database file
			bw.newLine();
			// Clear BufferedWriter after appending is complete
			bw.flush();
	    } catch (IOException ioe) {
	    	ioe.printStackTrace();
	    }
	}
	
	// ------------------------------------------------------------------
	
	// Load database of logins into an ArrayList, allowing them to be checked when user
	// attempts to login
	// PRE: The filename of the login database
	// POST: The login database is loaded into an ArrayList
	public void loadLoginDatabase(String fname) {
		try {
			// Read in login database file
			BufferedReader in = new BufferedReader(new FileReader(fname));
			// Read first line
			String loginRow = in.readLine();
			do {
				loginRow = loginRow.trim();
				// Create array for usernames/passwords/etc.
				String[] loginRowElements = null;
				// Split based on separator used in database file
				loginRowElements = loginRow.split(" ::: ");
				// Add items to ArrayList
				for (int i = 0; i < loginRowElements.length; i++) {
					loginItems.add(loginRowElements[i]);
				}
				loginRow = in.readLine();
			// Continue reading database lines
			} while (loginRow != null);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	 
	// ------------------------------------------------------------------
	
	// Append login database with a new user account
	// PRE: The user account elements to be appended to the login database
	// POST: Login database is appended
	
	public void saveLoginDatabase(String fname, String username, String password, String 
			secretQuestion, String secretAnswer) {
		
	    BufferedWriter bw = null;
		
	    // Join account data elements
	    String loginToSave = username + " ::: " + password + " ::: " + secretQuestion + " ::: " +
	    secretAnswer;
	    
		try {
			// Set up BufferedWriter to be used for appending
			bw = new BufferedWriter(new FileWriter(fname, true));
			// Append new account data to login database
			bw.write(loginToSave);
			// Go to next line in database file
			bw.newLine();
			// Clear BufferedWriter after appending is complete
			bw.flush();
	    } catch (IOException ioe) {
	    	ioe.printStackTrace();
	    }
	}
	
	// ------------------------------------------------------------------
	
	// Check username and password against login database, and return result
	// PRE: The username and password entered
	// POST: Appropriate result is returned based on accuracy of login:
	// 1 : correct username and password
	// 0 : username exists but password is not correct
	// -1: username does not exist in database
	
	public int checkLogin(String usernameEntered, String passwordEntered) {
		// Determine if username exists in database
		if (loginItems.contains(usernameEntered)) {
			// Determine password associated with given username
			String passwordRequired = loginItems.get(loginItems.indexOf(usernameEntered) + 1);
			// Determine equality of entered and required passwords
			int checkIfEqual = passwordEntered.compareTo(passwordRequired);
			
			if (checkIfEqual == 0) {
				// Correct password
				return 1;
			} else {
				// Incorrect password
				return 0;
			}
		} else {
			// Username does not exist in database
			return -1;
		}	
	}
	
	// ------------------------------------------------------------------
	
	// Return an array containing the four elements of the current database row
	// PRE: The index (ArrayList position) of the current row
	// POST: An array containing the elements of the current row is returned
	
	public String[] getLibraryRow(int indexOfRow) {
		
		// Determine index of current row; row 0 starts at index 0, row 1 starts at 4, row 2
		// starts at 8, etc.
		int currentIndex = indexOfRow + indexOfRow*3;
		// Create array to hold row elements
		String mediaRowElements[] = new String[4];
		
		// Fill array with name, artist, genre, and description of current row
		for (int i = 0; i < 4; i++) {
			mediaRowElements[i] = mediaItems.get(currentIndex + i);
		}
		
		// Return array containing row elements
		return mediaRowElements;
	}
	
	// ------------------------------------------------------------------
	
	
	// Return the total number of rows to display
	// PRE: None
	// POST: Number of rows required is returned
	public int getRowsNeeded() {
		
		// Number of rows needed is total items / items per row
		int totalRows = mediaItems.size() / 4;
		return totalRows;
		
	}
	
	// ------------------------------------------------------------------
	
	// Return the number of rows required to display all entries of a certain type
	// PRE: The media type to be displayed (e.g. CD)
	// POST: Number of rows required is returned
	public int getRowsNeededByType(String mediaType) {
		
		// Temporary counter
		int counter = 0;
		
		// Cycle through database elements, checking the type of media
		for (int i = 1; i < mediaItems.size(); i += 4) {
			// Compare media type of current entry to desired type
			int typeCheck = mediaItems.get(i).compareTo(mediaType);
			// Entry with given media found; increment counter
			if (typeCheck == 0) {
				counter++;
			}
		}
		
		// Return result
		return counter;
	}
	
	// ------------------------------------------------------------------
	
	
//	*** Unfinished code for future version ***
	
	
//	public void editLibraryEntry(String oldName, String newName, String oldArtist, 
//			String newArtist, String oldGenre, String newGenre, String oldDesc, String newDesc) {
//		
//		if (mediaItems.contains(oldName)) {
//			String artistRequired = mediaItems.get(mediaItems.indexOf(oldName) + 1);
//			int checkArtist = oldArtist.compareTo(artistRequired);
//			String genreRequired = mediaItems.get(mediaItems.indexOf(oldName) + 2);
//			int checkGenre = oldGenre.compareTo(genreRequired);
//			String descRequired = mediaItems.get(mediaItems.indexOf(oldName) + 3);
//			int checkDesc = oldDesc.compareTo(descRequired);
//			
//			if (checkArtist == 0 && checkGenre == 0 && checkDesc == 0) {
//				
//			}
//		}
//	}
}