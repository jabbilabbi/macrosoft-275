// HTMLOutputTest.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alan Lerner

public class HTMLOutputTest {

	public static void main(String[] args) {
		
		// Create instance of HTMLOutput class
		HTMLOutput ho = new HTMLOutput();
		//DatabaseControl db = new DatabaseControl();
		
		// Test web page creation
		ho.createHTMLOutput(true, true, true, true);
		
		// System.out.println("Web page creation completed!");
	}
}