package Project2;

public class DatabaseTest {

	public static void main(String args[]) {
		
		DatabaseControl db = new DatabaseControl();
		
		db.loadMediaDatabase();
		
		String mediaType = "Nothing";
		
		System.out.println("Rows needed: " + db.getRowsNeeded());
		System.out.println("Rows needed by type " + mediaType + ": " + db.getRowsNeededByType(mediaType));
		
		String[] testRow = db.getLibraryRow(0);
		
		for (int i = 0; i < testRow.length; i++) {
			System.out.println(testRow[i] + "  ");
		}
		
//		db.appendLoginDatabase("Hello ::: Nothing ::: Yadda ::: Hooray yay");
	}
	
}