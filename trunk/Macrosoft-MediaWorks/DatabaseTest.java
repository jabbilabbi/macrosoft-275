// DatabaseControl.java
// Test class for DatabaseControl.java

// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alan Lerner

public class DatabaseTest {

	public static void main(String args[]) {
		
		// Used to test other methods
		SecurityControl sc = new SecurityControl();
		sc.recordCurrentUser("Joe1");
		
		DatabaseControl db = new DatabaseControl();
		
		db.loadMediaDatabase();
		
		// Print out Joe1.txt
		db.updateFileName();
		System.out.println(db.getFname());
		
		// Create a user database file; simply reloads the file here as it is called in
		// the DatabaseControl constructor
		// db.createUserDatabaseFile();
		
		// Add a couple of media entries
//		db.appendMediaDatabase("Cool Music", "Rockman", "Rock", "This is a description");
//		db.appendMediaDatabase("Okay Music", "BluesWarrior", "Blues", "This is a description");
		
		// Should print 2 if running on a new database file; otherwise prints total lines
		// currently in the file
		System.out.println("Rows needed: " + db.getRowsNeeded());
		
		// Print out database contents by getting rows and printing individual elements
		for (int i = 0; i < db.getRowsNeeded(); i++) {
			String[] rowTest = db.getLibraryRow(i);
			System.out.print("- ");
			for (int j = 0; j < 4; j++) {
				System.out.print(rowTest[j] + " - ");
			}
			System.out.println();
		}
		
		// Delete last row of media database, aka "Undo" function
		db.deleteLastRow();

		// Reload database to ensure the deletion is incorporated
		db.reloadMediaDatabase();
		
		System.out.println("---------------");
		
		// Check rows needed; should be 1 less than before
		System.out.println(db.getRowsNeeded());
		// Print out database contents by getting rows and printing individual elements
		// Should print one less line than before
		for (int i = 0; i < db.getRowsNeeded(); i++) {
			String[] rowTest = db.getLibraryRow(i);
			System.out.print("- ");
			for (int j = 0; j < 4; j++) {
				System.out.print(rowTest[j] + " - ");
			}
			System.out.println();
		}
	}
}