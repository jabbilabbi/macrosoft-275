import java.util.ArrayList;

// DatabaseControl.java
// Test class for DatabaseControl.java

// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alan Lerner

public class DatabaseTest {

	public static void main(String args[]) {
		
		// Used to test other methods
		//ControllerClass cc = new ControllerClass();
		DatabaseControl db = new DatabaseControl();
		db.setCurrentUser("Joe1");
		
		db.loadAllDatabases();
		
		// Print out Joe1.txt
		db.updateFileName();
		
		System.out.println(db.getFileName());
		
		// Create a user database file; simply reloads the file here as it is called in
		// the DatabaseControl constructor
		 db.createUserDatabaseFile();
		 
		
		// Add a couple of media entries
		String[] cdTestData = {"CD", "Cool Music", "Rockman", "Rock", "This is a description"};
		String[] cdTestData2 = {"CD", "Okay Music", "BluesWarrior", "Blues", "This is a description"};
		ArrayList<String> CDItems = new ArrayList<String>();
		String[] dvdTestData = {"DVD", "Cool Movie", "Directing Bot", "Comedy", "5", "R", "A great movie!"};
		ArrayList<String> DVDItems = new ArrayList<String>();
//		db.appendMediaDatabase(CDItems, cdTestData);
//		db.appendMediaDatabase(DVDItems, dvdTestData);
		
		// Should print 2 if running on a new database file; otherwise prints total lines
		// currently in the file
		System.out.println(db.getMediaIndex(DVDItems));
//		System.out.println("Rows needed: " + db.getRowsNeeded(CDItems));
//		System.out.println("Rows needed: " + db.getRowsNeeded(DVDItems));
//		
//		// Print out database contents by getting rows and printing individual elements
//		for (int i = 0; i < db.getRowsNeeded(); i++) {
//			String[] rowTest = db.getLibraryRow(i);
//			System.out.print("- ");
//			for (int j = 0; j < 4; j++) {
//				System.out.print(rowTest[j] + " - ");
//			}
//			System.out.println();
//		}
//		
//		// Delete last row of media database, aka "Undo" function
//		db.deleteLastRow();
//
//		// Reload database to ensure the deletion is incorporated
//		db.reloadMediaDatabase();
//		
//		System.out.println("---------------");
//		
//		// Check rows needed; should be 1 less than before
//		System.out.println(db.getRowsNeeded());
//		// Print out database contents by getting rows and printing individual elements
//		// Should print one less line than before
//		for (int i = 0; i < db.getRowsNeeded(); i++) {
//			String[] rowTest = db.getLibraryRow(i);
//			System.out.print("- ");
//			for (int j = 0; j < 4; j++) {
//				System.out.print(rowTest[j] + " - ");
//			}
//			System.out.println();
//		} 
	}
}