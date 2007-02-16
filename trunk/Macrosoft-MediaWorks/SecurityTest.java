// SecurityTest.java
// Test class for SecurityControl.java

// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alan Lerner


public class SecurityTest {

	public static void main(String[] args) {
		
		SecurityControl sc = new SecurityControl();
		
		sc.loadLoginDatabase();
//		sc.appendLoginDatabase("logins.txt", "Joe4", "mypass123", "Where were you born?"
//				, "Arizona, USA");
		
//		System.out.println(sc.loginItems.toString());
		
		sc.appendLoginDatabase("ABC", "123", "what's up", "nothing");
		
		System.out.println(sc.checkLogin("ABC", "123"));
		
//		System.out.println(sc.loginItems.toString());
		
		System.out.println(sc.getUserNames());
	}
}
