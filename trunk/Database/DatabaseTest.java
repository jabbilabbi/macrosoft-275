package Project2;

public class databaseTest {

	public static void main(String args[]) {
		
		databaseControl db = new databaseControl();
		
		db.loadMediaDatabase("test.txt");
		
		String mediaType = "Nothing";
		
		System.out.println("Rows needed: " + db.getRowsNeeded());
		System.out.println("Rows needed by type " + mediaType + ": " + db.getRowsNeededByType(mediaType));
		
		String[] testRow = db.getLibraryRow(0);
		
		for (int i = 0; i < testRow.length; i++) {
			System.out.println(testRow[i] + "  ");
		}
		
//		db.saveLoginDatabase("Hello ::: Nothing ::: Yadda ::: Hooray yay");
	}
	
}