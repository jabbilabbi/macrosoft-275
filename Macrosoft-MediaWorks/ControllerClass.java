import java.util.ArrayList;

public class ControllerClass{

	private AddScreenUI addScreenUI;
	private BrowseUI browseUI;
	private CreateAccountUI createAccountUI;
	private LoginUI loginUI;
	private MainScreenUI mainScreenUI;
	private SecurityControl sdb;
	private DatabaseControl cdb;
	private HTMLOutput html;
	public static String currentUser = "currentUser";

	
	public ControllerClass(){
		addScreenUI = new AddScreenUI();
		browseUI = new BrowseUI();
		createAccountUI = new CreateAccountUI();
		loginUI = new LoginUI();
		mainScreenUI = new MainScreenUI();
		html = new HTMLOutput();
		sdb = new SecurityControl();
		cdb = new DatabaseControl();
	}

	public void loadMediaDatabase(){
		cdb.loadMediaDatabase();
	}
	public int getRowsNeeded(){
		return cdb.getRowsNeeded();
	}
	public String[] getLibraryRow(int i){
		return cdb.getLibraryRow(i);
	}
	
	public void createHTMLOutput(){
		html.createHTMLOutput();
	}
	public boolean checkIfUserExists(String tempUser){
		return sdb.checkIfUserExists(tempUser);
	}
	
	public void loadLoginDatabase(){
		sdb.loadLoginDatabase();
	}
	
	public int checkLogin(String username, String password){
		return sdb.checkLogin(username, password);
	}
	
	public void appendMediaDatabase(String title, String artist, String genre, String description){
		cdb.appendMediaDatabase(title, artist, genre, description);
	}
	
	public void createUserDatabaseFile(){
		cdb.createUserDatabaseFile();
	}
	public void appendLoginDatabase(String username, String password, String secretQ, String secretA){
		sdb.appendLoginDatabase(username, password, secretQ, secretA);
	}
	public ArrayList<String> getUserNames(){
		return sdb.getUserNames();		
	}
		
	public String getPassword(String tempUser){
		return sdb.getPassword(tempUser);		
	}
	
	public String[] getSecretInfo(String tempUser){
		return sdb.getSecretInfo(tempUser);		
	}
//	public ArrayList<String> searchDB(String wordToFind){
//		return cdb.searchDB(wordToFind);
//	}
	public void addScreenFrame(){
		addScreenUI.createAndShowGUI();
	}
	
	public void browseFrame(){
		browseUI.createAndShowGUI();
	}
	
	public void createAccountFrame(){
		createAccountUI.createAndShowGUI();
	}
	
	public void loginFrame(){
		loginUI.createAndShowGUI();
	}
	
	public void mainScreenFrame(){
		mainScreenUI.createAndShowGUI();
	}
	
	public void setCurrentUser(String currentUser){
		this.currentUser = currentUser;
	}
	
	public static String getCurrentUser(){
		return currentUser;
	}
	
	public static void main(String[] args) {
		ControllerClass control = new ControllerClass();
		control.loginUI.createAndShowGUI();	
	}

}
