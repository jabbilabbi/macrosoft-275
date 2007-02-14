package Project2;

public class securityTest {

	public static void main(String[] args) {
		
		securityControl sc = new securityControl();
		
		sc.loadLoginDatabase("logins.txt");
//		sc.appendLoginDatabase("logins.txt", "Joe4", "mypass123", "Where were you born?"
//				, "Arizona, USA");
		
		System.out.println(sc.loginItems.toString());
		
		sc.appendLoginDatabase("logins.txt", "ABC", "123", "what's up", "nothing");
		
		System.out.println(sc.loginItems.toString());
	}
}
