package Project2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class databaseControl {

	public HashSet<String> dataBaseItems = new HashSet<String>();
	public HashSet<String> loginItems = new HashSet<String>();
	
	public void loadDatabase(String fname) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(fname));
			String databaseRow = in.readLine();
			do {
				databaseRow = databaseRow.trim();
				String[] databaseRowElements = null;
				databaseRowElements = databaseRow.split(" ::: ");
				for (int i = 0; i < databaseRowElements.length; i++) {
					dataBaseItems.add(databaseRowElements[i]);
//					System.out.println(databaseRowElements[i]);
				}
				databaseRow = in.readLine();
			} while (databaseRow != null);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void saveDatabase(String lineToSave) {
		
	    BufferedWriter bw = null;
		
		try {
			bw = new BufferedWriter(new FileWriter("test.txt", true));
			bw.write(lineToSave);
			bw.newLine();
			bw.flush();
	    } catch (IOException ioe) {
	    	ioe.printStackTrace();
	    }
	}
	
	public void saveLoginDatabase(String username, String password, String 
			secretQuestion, String secretAnswer) {
		
	    BufferedWriter bw = null;
		
	    String loginToSave = username + " ::: " + password + " ::: " + secretQuestion + " ::: " +
	    secretAnswer;
	    
		try {
			bw = new BufferedWriter(new FileWriter("test.txt", true));
			bw.write(loginToSave);
			bw.newLine();
			bw.flush();
	    } catch (IOException ioe) {
	    	ioe.printStackTrace();
	    }
	}
	
	public void loadLoginDatabase(String fname) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(fname));
			String loginRow = in.readLine();
			do {
				loginRow = loginRow.trim();
				String[] loginRowElements = null;
				loginRowElements = loginRow.split(" ::: ");
				for (int i = 0; i < 1; i++) {
					loginItems.add(loginRowElements[i]);
				}
				loginRow = in.readLine();
			} while (loginRow != null);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void checkLogin(String usernameEntered, String passwordEntered) {
	}
	
	
	
}