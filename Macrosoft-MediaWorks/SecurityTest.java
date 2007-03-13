// SecurityTest.java
// Test class for SecurityControl.java

// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alan Lerner


public class SecurityTest {

	public static void main(String[] args) {
		
		SecurityControl sc = new SecurityControl();
		
		// Writes Joe1 to current.txt
		sc.recordCurrentUser("Joe1");
		// Outputs Joe1
		System.out.println(sc.retrieveCurrentUser());
		// Deletes current.txt
		sc.deleteCurrentUser();
		
		// Load database (will be empty if running program for first time)
		sc.loadLoginDatabase();
		
		// Add a couple of entries to the database file
		sc.appendLoginDatabase("Joe4", "mypass123", "Where were you born?", "Arizona, USA");
		sc.appendLoginDatabase("ABC", "123", "what's up", "nothing");
		
		// Prints -1 (nonexistant login)
		System.out.println(sc.checkLogin("MikeX", "banana"));
		
		// Prints 1 (correct login)
		System.out.println(sc.checkLogin("ABC", "123"));
		
		// Print 0 (incorrect login)
		System.out.println(sc.checkLogin("ABC", "1234"));
		
		// Print out all user names in login database (would be [Joe4, ABC] unless more names have been added)
		System.out.println(sc.getUserNames());
		
		// Print true
		System.out.println(sc.checkIfUserExists("ABC"));
		
		String[] secretInfo = sc.getSecretInfo("ABC");
		// Print what's up
		System.out.println(secretInfo[0]);
		// Print nothing
		System.out.println(secretInfo[1]);
	}
}
