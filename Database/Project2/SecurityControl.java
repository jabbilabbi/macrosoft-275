// Login database control functions for a personal library program
// Includes login database creation and addition methods and a password checker

// Alan Lerner
// CMPT 275, SFU Surrey

package Project2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SecurityControl {

	// ArrayList to hold login database
	public ArrayList<String> loginItems = new ArrayList<String>();
	
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
	// POST: Login database file is appended, and login database is reloaded
	
	public void appendLoginDatabase(String fname, String username, String password, String 
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
	    
	    // Clear old ArrayList and reload in order to include the appended account
	    loginItems.clear();
	    this.loadLoginDatabase(fname);
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
}
