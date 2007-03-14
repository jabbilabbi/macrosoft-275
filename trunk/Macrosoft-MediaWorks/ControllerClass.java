
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class ControllerClass implements Serializable{

	private AddScreenUI addScreenUI;
	private BrowseUI browseUI;
	private CreateAccountUI createAccountUI;
	private LoginUI loginUI;
	private MainScreenUI mainScreenUI;
	private SecurityControl sdb;
	private DatabaseControl cdb;
	private HTMLOutput html;
	protected String user;
	private Object[] UIs;
	
	public ControllerClass(){
		this.addScreenUI = new AddScreenUI();
		this.browseUI = new BrowseUI();
		this.createAccountUI = new CreateAccountUI();
		this.loginUI = new LoginUI();
		this.mainScreenUI = new MainScreenUI();
		this.sdb = new SecurityControl();
		this.cdb = new DatabaseControl();	
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
	
	private static void main(String[] args) {
		ControllerClass control = new ControllerClass();
		control.loginUI.createAndShowGUI();	
	}

}
