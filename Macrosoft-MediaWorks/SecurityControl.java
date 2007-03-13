// SecurityControl.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alan Lerner

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

public class SecurityControl {

	// ArrayList to hold login database
	private ArrayList<String> loginItems = new ArrayList<String>();

	// Storage for user account database
	private String fname = "logins.txt";

	// PURPOSE: Delete file containing the name of the current user
	// PRE: None
	// POST: current.txt is deleted

	public void deleteCurrentUser() {
		// Open file
		File userFile = new File("current.txt");
		// Perform deletion
		userFile.delete();
	}

	// PURPOSE: Retrieve name of current user logged in to program
	// PRE: None
	// POST: A String is returned with the name of the currently logged in user

	public String retrieveCurrentUser() {

		// Temporary variable for username
		String userLoggedIn = "";

		try {
			// Use a BufferedReader to check the current.txt file
			BufferedReader in = new BufferedReader(
					new FileReader("current.txt"));
			// Read user name
			userLoggedIn = in.readLine();
		} catch (IOException ioe) {
		}
		// Return result
		return userLoggedIn;
	}

	// PURPOSE: Record name of currently logged in user to a file; allows
	// username to be read by
	// other classes
	// PRE: A String containing the currently logged in user's name
	// POST: A file named "current.txt" is written in the current directory
	// containing the user's name

	public void recordCurrentUser(String currentUser) {
		try {
			// Delete previous user
			deleteCurrentUser();
			BufferedWriter bw = null;
			// Set up BufferedWriter to be used for writing
			bw = new BufferedWriter(new FileWriter("current.txt", true));
			// Write username to file
			bw.write(currentUser);
			// Clear BufferedWriter after writing is complete
			bw.flush();
		} catch (IOException ioe) {
		}
	}

	// Constructor
	public SecurityControl() {
	}

	// Load database of logins into an ArrayList, allowing them to be checked
	// when user
	// attempts to login
	// PRE: The filename of the login database
	// POST: The login database is loaded into an ArrayList

	public void loadLoginDatabase() {
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

	// Append login database with a new user account
	// PRE: The user account elements to be appended to the login database
	// POST: Login database file is appended, and login database is reloaded

	public void appendLoginDatabase(String username, String password,
			String secretQuestion, String secretAnswer) {

		BufferedWriter bw = null;

		// Join account data elements
		String loginToSave = username + " ::: " + password + " ::: "
				+ secretQuestion + " ::: " + secretAnswer;

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

		// Clear old ArrayList and reload in order to include the appended
		// account
		loginItems.clear();
		this.loadLoginDatabase();
	}

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
			String passwordRequired = loginItems.get(loginItems
					.indexOf(usernameEntered) + 1);
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
	
	// Get secret question and password for a given user
	// PRE: usernameEntered exists in login database
	// POST: String array is returned containing secret question in position 0 and answer in position 1
	
	public String[] getSecretInfo(String usernameEntered) {
		
		// String array to hold secret info
		String[] secretInfo = new String[2];
		
		// Get secret question and answer
		secretInfo[0] = loginItems.get(loginItems.indexOf(usernameEntered) + 2);
		secretInfo[1] = loginItems.get(loginItems.indexOf(usernameEntered) + 3);
		
		// Return result
		return secretInfo;
	}
	
	// Determine if a user exists in the login database
	// PRE: None
	// POST: Returns true if username exists in database; otherwise false
	
	public boolean checkIfUserExists(String usernameEntered) {
		// Check login and return appropriate result
		if (loginItems.contains(usernameEntered)) {
			return true;
		} else {
			return false;
		}
	}

	// Get all user names in login database
	// PRE: None
	// POST: ArrayList with all usernames in login database is returned

	public ArrayList<String> getUserNames() {
		// ArrayList to hold user names
		ArrayList<String> nameList = new ArrayList<String>();
		// Loop through user names in login database and add to name list
		for (int i = 0; i < loginItems.size(); i += 4) {
			nameList.add(loginItems.get(i));
		}
		// Return result
		return nameList;
	}

}
