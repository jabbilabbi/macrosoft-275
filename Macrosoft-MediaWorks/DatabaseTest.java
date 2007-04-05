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
		
		// Print out Joe1.txt
		db.updateFileName();
		
		// Load all databases
		db.loadAllDatabases();
		
		// Check file name
		System.out.println(db.getFileName());
		
		// Create a user database file; simply reloads the file here as it is called in
		// the DatabaseControl constructor
		// db.createUserDatabaseFile();
		 
		
		// Add a couple of media entries
		String[] cdTestData = {"CD", "Cool Music", "Rockman", "Rock", "This is a description"};
		String[] cdTestData2 = {"CD", "Okay Music", "BluesWarrior", "Blues", "This is a description"};
		//ArrayList<String> CDItems = new ArrayList<String>();
		String[] dvdTestData = {"DVD", "Cool Movie", "Directing Bot", "Comedy", "5", "R", "A great movie!"};
		//ArrayList<String> DVDItems = new ArrayList<String>();
//		db.appendMediaDatabase(db.CDItems, cdTestData);
//		db.appendMediaDatabase(db.DVDItems, dvdTestData);
		
		// Should print 2 if running on a new database file; otherwise prints total lines
		// currently in the file
		System.out.println(db.getMediaIndex(db.DVDItems));
		System.out.println("DVD Rows needed: " + db.getRowsNeeded(db.DVDItems));
		System.out.println("CD Rows needed: " + db.getRowsNeeded(db.CDItems));
		
		// Print out database contents by getting rows and printing individual elements
		for (int i = 0; i < db.getRowsNeeded(db.DVDItems); i++) {
			String[] rowTest = db.getLibraryRow(db.DVDItems, i);
			System.out.print("- ");
			for (int j = 0; j < db.getMediaIndex(db.DVDItems); j++) {
				System.out.print(rowTest[j] + " - ");
			}
			System.out.println();
		}
		
		// Print out database contents by getting rows and printing individual elements
		for (int i = 0; i < db.getRowsNeeded(db.CDItems); i++) {
			String[] rowTest = db.getLibraryRow(db.CDItems, i);
			System.out.print("- ");
			for (int j = 0; j < db.getMediaIndex(db.CDItems); j++) {
				System.out.print(rowTest[j] + " - ");
			}
			System.out.println();
		}
		
		String[] dvdDeleteTestData = {"DVD", "Cool Movie2", "Directing Bot", "Comedy", "5", "R", "A great movie!"};
		String[] dvdEditTestData = {"DVD", "Cool Movie9", "Directing Bot", "Comedy", "5", "R", "A great movie!"};
		// Delete last row of media database, aka "Undo" function
		db.deleteRow(db.DVDItems, dvdDeleteTestData, "DVD");
//		db.editRow(db.DVDItems, dvdDeleteTestData, dvdEditTestData, "DVD");
//
//		// Reload database to ensure the deletion is incorporated

//		
		System.out.println("---------------");
//		
//		// Check rows needed; should be 1 less than before
		System.out.println(db.getRowsNeeded(db.DVDItems));
		// Print out database contents by getting rows and printing individual elements
		// Should print one less line than before
		for (int i = 0; i < db.getRowsNeeded(db.DVDItems); i++) {
			String[] rowTest = db.getLibraryRow(db.DVDItems, i);
			System.out.print("- ");
			for (int j = 0; j < db.getMediaIndex(db.DVDItems); j++) {
				System.out.print(rowTest[j] + " - ");
			}
			System.out.println();
		}
		for (int i = 0; i < db.getRowsNeeded(db.CDItems); i++) {
			String[] rowTest = db.getLibraryRow(db.CDItems, i);
			System.out.print("- ");
			for (int j = 0; j < 4; j++) {
				System.out.print(rowTest[j] + " - ");
			}
			System.out.println();
		} 
	}
}