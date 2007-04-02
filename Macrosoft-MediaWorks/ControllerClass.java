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

	
	public ControllerClass(){
		sdb = new SecurityControl();
		loginUI = new LoginUI();
	}

	public void loadMediaDatabase(ArrayList<String> dbType){
//		cdb.loadMediaDatabase(dbType);
	}
	public int getRowsNeeded(ArrayList<String> dbType){
		return cdb.getRowsNeeded(dbType);
	}
	public String[] getLibraryRow(ArrayList<String> dbType, int i){
		return cdb.getLibraryRow(dbType, i);
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
	
	public void appendMediaDatabase(ArrayList<String> dbType, String[] rowElements){
		cdb.appendMediaDatabase(dbType, rowElements);
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
		cdb = new DatabaseControl();
		addScreenUI = new AddScreenUI();
		browseUI = new BrowseUI();
		createAccountUI = new CreateAccountUI();
		mainScreenUI = new MainScreenUI();
		html = new HTMLOutput();
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
