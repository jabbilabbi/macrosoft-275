// CreateAccountUI.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alex Antonio

import java.awt.Color;
import java.awt.Container;
import java.awt.Insets;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class CreateAccountUI implements ActionListener {

	// Declaration of screen objects used later in action listener method
	private JButton createAccount;

	private JTextField usernameTextBox;

	private JPasswordField passwordTextBox, passwordConfirmTextBox;

	private JTextField secretQuestionTextBox, secretAnswerTextBox;

	private JLabel passwordConflict, usernameTaken, somethingNotEntered;

	private JFrame frame;

	// Purpose: Add all components of the pane into the correct locations and
	// with correct functions
	// PRE: Valid pane is given as a parameter
	// POST: All neceassray components for the Create Account screen will be
	// added and displayed
	public void addComponentsToPane(Container pane) {

		// Absolute container positioning used
		pane.setLayout(null);

		// Declaration of pane components
		createAccount = new JButton("Create Account");

		usernameTextBox = new JTextField(10);

		passwordTextBox = new JPasswordField(10);

		passwordConfirmTextBox = new JPasswordField(10);

		secretQuestionTextBox = new JTextField(10);

		secretAnswerTextBox = new JTextField(10);

		JLabel enterUsername = new JLabel("Username:");

		JLabel enterPassword = new JLabel("Password:");

		passwordConflict = new JLabel(
				"Your confirmed password is different, please re-type");
		passwordConflict.setVisible(false);

		usernameTaken = new JLabel(
				"Your username is in use, please use another");
		usernameTaken.setVisible(false);

		somethingNotEntered = new JLabel(
				"Some fields have been left empty, please complete the form");
		somethingNotEntered.setVisible(false);

		JLabel enterConfirmPassword = new JLabel("Confirm Password:");

		JLabel enterSecretQuestion = new JLabel("Secret Question:");

		JLabel enterSecretAnswer = new JLabel("Secret Answer:");

		JLabel enterNewAccount = new JLabel("Enter Account Information");

		// Add components to the pane
		pane.add(createAccount);
		pane.add(usernameTextBox);
		pane.add(passwordTextBox);
		pane.add(passwordConfirmTextBox);
		pane.add(secretQuestionTextBox);
		pane.add(secretAnswerTextBox);
		pane.add(enterUsername);
		pane.add(enterPassword);
		pane.add(enterConfirmPassword);
		pane.add(enterSecretQuestion);
		pane.add(enterSecretAnswer);
		pane.add(enterNewAccount);
		pane.add(usernameTaken);
		pane.add(passwordConflict);
		pane.add(somethingNotEntered);

		createAccount.addActionListener(this); // Adds action listeners to
												// relevant entities

		Insets insets = pane.getInsets(); // Gets the insets for the pane on
											// the screen

		Dimension size = createAccount.getPreferredSize(); // Declares a size
															// variable to
															// properly size
															// each entity on
															// the screen

		// Uses the size variable and positioning bounds to place every entity
		// as desired
		createAccount.setBounds(305 + insets.left, 525 + insets.top,
				size.width, size.height);

		size = enterUsername.getPreferredSize();
		enterUsername.setBounds(175 + insets.left, 220 + insets.top,
				size.width, size.height);

		size = usernameTextBox.getPreferredSize();
		usernameTextBox.setBounds(350 + insets.left, 220 + insets.top,
				size.width, size.height);

		size = passwordTextBox.getPreferredSize();
		passwordTextBox.setBounds(350 + insets.left, 250 + insets.top,
				size.width, size.height);

		size = enterPassword.getPreferredSize();
		enterPassword.setBounds(175 + insets.left, 250 + insets.top,
				size.width, size.height);

		size = enterConfirmPassword.getPreferredSize();
		enterConfirmPassword.setBounds(175 + insets.left, 280 + insets.top,
				size.width, size.height);

		size = passwordConfirmTextBox.getPreferredSize();
		passwordConfirmTextBox.setBounds(350 + insets.left, 280 + insets.top,
				size.width, size.height);

		size = enterSecretQuestion.getPreferredSize();
		enterSecretQuestion.setBounds(175 + insets.left, 310 + insets.top,
				size.width, size.height);

		size = secretQuestionTextBox.getPreferredSize();
		secretQuestionTextBox.setBounds(350 + insets.left, 310 + insets.top,
				size.width, size.height);

		size = enterSecretAnswer.getPreferredSize();
		enterSecretAnswer.setBounds(175 + insets.left, 400 + insets.top,
				size.width, size.height);

		size = secretAnswerTextBox.getPreferredSize();
		secretAnswerTextBox.setBounds(350 + insets.left, 400 + insets.top,
				size.width, size.height);

		size = enterNewAccount.getPreferredSize();
		enterNewAccount.setBounds(300 + insets.left, 25 + insets.top,
				size.width, size.height);

		size = passwordConflict.getPreferredSize();
		passwordConflict.setBounds(210 + insets.left, 440 + insets.top,
				size.width, size.height);

		size = usernameTaken.getPreferredSize();
		usernameTaken.setBounds(210 + insets.left, 450 + insets.top,
				size.width, size.height);

		size = somethingNotEntered.getPreferredSize();
		somethingNotEntered.setBounds(210 + insets.left, 460 + insets.top,
				size.width, size.height);
	}

	// Purpose: To create and display the 'Create Account' UI
	// PRE: None
	// POST: A new frame is created, components added, frame displayed
	public void createAndShowGUI() {
		// Create and set up the window.
		frame = new JFrame("Macrosoft Media Works");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addComponentsToPane(frame.getContentPane()); // Set up the content
														// pane.

		// Size and display the window.
		Insets insets = frame.getInsets();
		frame.setSize(800 + insets.left + insets.right, 600 + insets.top
				+ insets.bottom);
		frame.setVisible(true);
	}
	// Purpose: To syncronize the actions of the user with the functionality of the screen
	// PRE: Valid action event as param
	// POST: Button functionality with proper conditions and actions taken
	public void actionPerformed(ActionEvent e) {

		SecurityControl sdb = new SecurityControl(); // Creates an instance
														// of the Security DB
		sdb.loadLoginDatabase(); // Loads the Login DB

		LoginUI loginUI = new LoginUI(); // Creates an instance of the
											// LoginUI Class

		// Condition where some field(s) were left empty
		if ((usernameTextBox.getText().length() == 0)
				|| (passwordTextBox.getPassword().length == 0)
				|| (passwordConfirmTextBox.getPassword().length == 0)
				|| (secretQuestionTextBox.getText().length() == 0)
				|| (secretAnswerTextBox.getText().length() == 0)) {
			somethingNotEntered.setVisible(true); // Show relevant error
		} else {
			somethingNotEntered.setVisible(false); // Remove relevant error

			String password = String.valueOf(passwordTextBox.getPassword());
			String confirmPassword = String.valueOf(passwordConfirmTextBox
					.getPassword());
			// Condition where password entered is different then the confirmed
			// password
			if (password.compareTo(confirmPassword) != 0) {
				passwordConflict.setVisible(true); // Show relevant error
			} else {
				passwordConflict.setVisible(false); // Remove relevant error
				// Condition where username is already in login DB
				if (sdb.getUserNames().contains(usernameTextBox.getText()) == false) {
					usernameTaken.setVisible(false); // Remove relevant error
					sdb.appendLoginDatabase(usernameTextBox.getText(),
							passwordTextBox.getText(), secretQuestionTextBox
									.getText(), secretAnswerTextBox.getText()); // Adds
																				// the
																				// login
																				// DB
																				// entry

					loginUI.createAndShowGUI(); // Returns to the Login Screen
					frame.setVisible(false); // Shuts off current frame

				} else {
					usernameTaken.setVisible(true); // Show relevant error
				}
			}
		}
	}

	// Test Method
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				CreateAccountUI ui = new CreateAccountUI();
				ui.createAndShowGUI();
			}
		});

	}
}
