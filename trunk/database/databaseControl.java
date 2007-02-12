package Project2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class databaseControl {

	public ArrayList<String> mediaItems = new ArrayList<String>();
	public ArrayList<String> loginItems = new ArrayList<String>();
	
	// ------------------------------------------------------------------
	
	// Load database of media entries into an HashSet, allowing them to be read when displaying
	// user's media library
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
	
	public void editLibraryEntry(String oldName, String newName, String oldArtist, 
			String newArtist, String oldGenre, String newGenre, String oldDesc, String newDesc) {
		
		if (mediaItems.contains(oldName)) {
			String artistRequired = mediaItems.get(mediaItems.indexOf(oldName) + 1);
			int checkArtist = oldArtist.compareTo(artistRequired);
			String genreRequired = mediaItems.get(mediaItems.indexOf(oldName) + 2);
			int checkGenre = oldGenre.compareTo(genreRequired);
			String descRequired = mediaItems.get(mediaItems.indexOf(oldName) + 3);
			int checkDesc = oldDesc.compareTo(descRequired);
			
			if (checkArtist == 0 && checkGenre == 0 && checkDesc == 0) {
				
			}
		}
	}
}