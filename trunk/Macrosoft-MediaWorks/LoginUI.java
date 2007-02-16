// LoginUI.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alex Antonio


import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class LoginUI implements ActionListener {
	// Declaration of screen objects used later in action listener method
	private JButton createAccount, login, forgotPassword;

	private JTextField usernameTextBox;

	private JPasswordField passwordTextBox;
	
	private JLabel enterUsername, enterPassword, pleaseLogin,
	enterUserNameText, enterPasswordText, invalidLogin;

	SecurityControl sdb = new SecurityControl(); // Creates instance of the login DB	
	
	

	private JFrame frame;
	// Purpose: Add all components of the pane into the correct locations and
	// with correct functions
	// PRE: Valid pane is given as a parameter
	// POST: All neceassray components for the Create Account screen will be
	// added and displayed
	public void addComponentsToPane(Container pane) {
		pane.setLayout(null); // Absolute container positioning used

		// Declaration of pane components
		createAccount = new JButton("Create New Account");
		createAccount.addActionListener(this);

		login = new JButton("Login");
		login.addActionListener(this); // Adds Action Listener

		forgotPassword = new JButton("Forgot Password?");
		forgotPassword.addActionListener(this); // Adds Action Listener

		usernameTextBox = new JTextField(10);

		passwordTextBox = new JPasswordField(10);

		enterUsername = new JLabel("Username:");

		enterPassword = new JLabel("Password:");

		pleaseLogin = new JLabel("Login to your account:");

		enterUserNameText = new JLabel("Please enter your user name");
		enterUserNameText.setFont(new Font("Helvetica", Font.PLAIN, 10));

		enterPasswordText = new JLabel("Please enter your password");
		enterPasswordText.setFont(new Font("Helvetica", Font.PLAIN, 10));

		invalidLogin = new JLabel("Invalid user name/password");
		invalidLogin.setFont(new Font("Helvetica", Font.PLAIN, 12));

		// Add components to the pane
		pane.add(createAccount);
		pane.add(login);
		pane.add(forgotPassword);
		pane.add(usernameTextBox);
		pane.add(passwordTextBox);
		pane.add(enterUsername);
		pane.add(enterPassword);
		pane.add(pleaseLogin);
		pane.add(enterPasswordText);
		pane.add(enterUserNameText);
		pane.add(invalidLogin);

		// Screen positioning
		Insets insets = pane.getInsets();
		Dimension size = createAccount.getPreferredSize();
		createAccount.setBounds(305 + insets.left, 180 + insets.top,
				size.width, size.height);
		size = login.getPreferredSize();
		login.setBounds(340 + insets.left, 400 + insets.top, size.width,
				size.height);
		size = forgotPassword.getPreferredSize();
		forgotPassword.setBounds(310 + insets.left, 520 + insets.top,
				size.width, size.height);
		size = usernameTextBox.getPreferredSize();
		usernameTextBox.setBounds(350 + insets.left, 250 + insets.top,
				size.width, size.height);
		size = passwordTextBox.getPreferredSize();
		passwordTextBox.setBounds(350 + insets.left, 310 + insets.top,
				size.width, size.height);
		size = enterUsername.getPreferredSize();
		enterUsername.setBounds(250 + insets.left, 250 + insets.top,
				size.width, size.height);
		size = enterPassword.getPreferredSize();
		enterPassword.setBounds(250 + insets.left, 310 + insets.top,
				size.width, size.height);
		size = pleaseLogin.getPreferredSize();
		pleaseLogin.setBounds(315 + insets.left, 150 + insets.top, size.width,
				size.height);
		size = enterPasswordText.getPreferredSize();
		enterPasswordText.setBounds(465 + insets.left, 310 + insets.top,
				size.width, size.height);
		enterPasswordText.setVisible(false);
		size = enterUserNameText.getPreferredSize();
		enterUserNameText.setBounds(465 + insets.left, 250 + insets.top,
				size.width, size.height);
		enterUserNameText.setVisible(false);
		invalidLogin.setBounds(295 + insets.left, 350 + insets.top,
				size.width + 50, size.height);
		invalidLogin.setVisible(false);
	}
	// Purpose: To create and display the 'Create Account' UI
	// PRE: None
	// POST: A new frame is created, components added, frame displayed
	public void createAndShowGUI() {
		// Create and set up the window
		frame = new JFrame("Macrosoft Media Works");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		addComponentsToPane(frame.getContentPane()); // Set up the content pane

		// Size and display the window
		Insets insets = frame.getInsets();
		frame.setSize(800 + insets.left + insets.right, 600 + insets.top
				+ insets.bottom);
		frame.setVisible(true);
	}
	// Purpose: To syncronize the actions of the user with the functionality of the screen
	// PRE: Valid action event as param
	// POST: Button functionality with proper conditions and actions taken
	public void actionPerformed(ActionEvent e) {

		sdb.loadLoginDatabase(); // Loads the Login DB

		CreateAccountUI createAccountUI = new CreateAccountUI(); // Creates an instance of createAccountUI class
		MainScreenUI mainScreenUI = new MainScreenUI(); // Creates an instance of the mainscreenUI class

		Boolean fieldsComplete = true;

		String userEntered = String.valueOf(usernameTextBox.getText());
		String passwordEntered = String.valueOf(passwordTextBox.getPassword());
		int user_result = sdb.checkLogin(userEntered, passwordEntered);

		JButton b = (JButton) e.getSource();
		// Condition where the button pressed what the Create Account button
		if (b == this.createAccount) {
			createAccountUI.createAndShowGUI();
			frame.setVisible(false);
		}
		// Condition where the button pressed what the Login button
		if (b == this.login) {
			// Checks if username wasn't completed
			if (usernameTextBox.getText().length() == 0) {
				fieldsComplete = false;
				enterUserNameText.setVisible(true); // Shows relevant error
			} else {
				enterUserNameText.setVisible(false); // Removes relevant error
			}
			// Checks if the password wasn't completed
			if (passwordTextBox.getText().length() == 0) {
				fieldsComplete = false;
				enterPasswordText.setVisible(true); // Shows relevant error
			} else {
				enterPasswordText.setVisible(false); // Removes relevant error
			}
			// Condition where the login entry was invalid
			if ((user_result == 1) && (fieldsComplete == true)) {
				invalidLogin.setVisible(false); // Removes relevant error
				String currentUser = String.valueOf(usernameTextBox.getText()); // Stores current user

				sdb.recordCurrentUser(currentUser); // Records current user in DB

				mainScreenUI.createAndShowGUI(); // Displays main screen
				frame.setVisible(false); // Shuts off current frame

			} else if ((user_result != 1) && (fieldsComplete == true)) {
				invalidLogin.setVisible(true); // Shows relevant error

			}

		}

	}
	// Test Method
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				LoginUI ui = new LoginUI();
				ui.createAndShowGUI();
			}
		});

	}
}
