
import java.io.Serializable;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class ControllerClass implements Serializable{

	private AddScreenUI addScreenUI;
	private BrowseUI browseUI;
	private CreateAccountUI createAccountUI;
	private LoginUI2 loginUI;
	private MainScreenUI mainScreenUI;
	private SecurityControl sdb;
	private DatabaseControl cdb;
	private Object[] UIs;
	
	public ControllerClass(){
		this.addScreenUI = new AddScreenUI();
		this.browseUI = new BrowseUI();
		this.createAccountUI = new CreateAccountUI();
		this.loginUI = new LoginUI2();
		this.mainScreenUI = new MainScreenUI();
		this.sdb = new SecurityControl();
		this.cdb = new DatabaseControl();	
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
