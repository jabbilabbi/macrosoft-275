// ControllerClass.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Scott Fuoco
import java.util.ArrayList;

public class ControllerClass{

	
	private AddScreenUI addScreenUI;
	private BrowseUI browseUI;
	private CreateAccountUI createAccountUI;
	private LoginUI loginUI;
	private MainScreenUI mainScreenUI;
	protected SecurityControl sdb;
	protected DatabaseControl cdb;
	private HTMLOutput html;
	private Security sec;

	
	public ControllerClass(){
		sdb = new SecurityControl();
		loginUI = new LoginUI();
		cdb = new DatabaseControl();
		addScreenUI = new AddScreenUI();
		browseUI = new BrowseUI();
		createAccountUI = new CreateAccountUI();
		mainScreenUI = new MainScreenUI();
		html = new HTMLOutput();
		sec = new Security();
	}
// The functions in the controller class call the appropriate funtions of the other classes
	
	public int getRowsNeeded(ArrayList<String> dbType){
		return cdb.getRowsNeeded(dbType);
	}
	public String[] getLibraryRow(ArrayList<String> dbType, int i){
		return cdb.getLibraryRow(dbType, i);
	}
	
	public void createHTMLOutput(){
		html.createHTMLOutput(true, true, true, true);
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
	
	public void appendMediaDatabase(ArrayList<String> dbType, String[] rowElements){
		cdb.appendMediaDatabase(dbType, rowElements);
	}
	
	public void createUserDatabaseFile(){
		cdb.createUserDatabaseFile();
	}
	public void appendLoginDatabase(String username, String password, String secretQ, String secretA){
		username = sec.encrypt(username);
		password = sec.encrypt(password);
		secretQ = sec.encrypt(secretQ);
		secretA = sec.encrypt(secretA);
		
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
		cdb.currentUser = currentUser;
	}
	
	public String getCurrentUser(){
		return cdb.currentUser;
	}
	
	public static void main(String[] args) {
		ControllerClass control = new ControllerClass();
		control.loginUI.createAndShowGUI();	
	}

}
