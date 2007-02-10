package Project2;

public class DatabaseTest {

	public static void main(String args[]) {
		
		databaseControl db = new databaseControl();
		
		db.loadDatabase("test.txt");
		
		db.saveDatabase("Hello ::: Nothing ::: Yadda ::: Hooray yay");
	}
	
}