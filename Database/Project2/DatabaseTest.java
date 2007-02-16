package Project2;

public class DatabaseTest {

	public static void main(String args[]) {
		
		DatabaseControl db = new DatabaseControl();
		
		String mediaType = "Nothing";
		
		System.out.println("Rows needed: " + db.getRowsNeeded());
		System.out.println("Rows needed by type " + mediaType + ": " + db.getRowsNeededByType(mediaType));
		
		System.out.println(db.mediaItems.toString());
		
//		for (int i = 0; i < db.getRowsNeeded(); i++) {
//			String[] rowTest = db.getLibraryRow(i);
//			for (int j = 0; j < 4; j++) {
//				System.out.println(rowTest[j]);
//			}
//		}
		
//		db.appendLoginDatabase("Hello ::: Nothing ::: Yadda ::: Hooray yay");
	}
	
}