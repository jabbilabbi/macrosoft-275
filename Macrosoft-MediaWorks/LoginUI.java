//Macrosoft - CMPT 275
//Programmer: Alex Antonio

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class LoginUI implements ActionListener {
	private JButton createAccount, login, forgotPassword;

	private JTextField usernameTextBox;

	private JPasswordField passwordTextBox;

	SecurityControl sdb = new SecurityControl();
	
	
	private JLabel enterUsername, enterPassword, pleaseLogin,
			enterUserNameText, enterPasswordText, invalidLogin;

	private JFrame frame;

	public void addComponentsToPane(Container pane) {
		// Absolute container positioning used
		pane.setLayout(null);

		// Declaration of pane components
		createAccount = new JButton("Create New Account");
		createAccount.addActionListener(this);

		login = new JButton("Login");
		login.addActionListener(this);

		forgotPassword = new JButton("Forgot Password?");
		forgotPassword.addActionListener(this);

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

	public void createAndShowGUI() {
		// Create and set up the window.
		frame = new JFrame("Macrosoft Media Works");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		addComponentsToPane(frame.getContentPane());

		// Size and display the window.
		Insets insets = frame.getInsets();
		frame.setSize(800 + insets.left + insets.right, 600 + insets.top
				+ insets.bottom);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		sdb.loadLoginDatabase();

		CreateAccountUI createAccountUI = new CreateAccountUI();
		MainScreenUI mainScreenUI = new MainScreenUI();

		Boolean fields_complete = true;

		String userEntered = String.valueOf(usernameTextBox.getText());
		String passwordEntered = String.valueOf(passwordTextBox.getPassword());
		int user_result = sdb.checkLogin(userEntered, passwordEntered);
		System.out.println(user_result);

		JButton b = (JButton) e.getSource();

		if (b == this.createAccount) {
			createAccountUI.createAndShowGUI();
			frame.setVisible(false);
		}
		if (b == this.login) {

			if (usernameTextBox.getText().length() == 0) {
				fields_complete = false;
				enterUserNameText.setVisible(true);
			} else {
				enterUserNameText.setVisible(false);
			}
			if (passwordTextBox.getText().length() == 0) {
				fields_complete = false;
				enterPasswordText.setVisible(true);
			} else {
				enterPasswordText.setVisible(false);
			}

			if ((user_result == 1) && (fields_complete == true)) {
				invalidLogin.setVisible(false);
				String currentUser = String.valueOf(usernameTextBox.getText());

				sdb.recordCurrentUser(currentUser);

				mainScreenUI.createAndShowGUI();
				frame.setVisible(false);

			} else if ((user_result != 1) && (fields_complete == true)) {
				invalidLogin.setVisible(true);

			}

		}

	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				LoginUI ui = new LoginUI();
				ui.createAndShowGUI();
			}
		});

	}
}
