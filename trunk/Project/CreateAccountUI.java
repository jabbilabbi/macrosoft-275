//Macrosoft - CMPT 275
//Programmer: Alex Antonio

import java.awt.Color;
import java.awt.Container;
import java.awt.Insets;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class CreateAccountUI implements ActionListener {
	JButton createAccount;

	JTextField usernameTextBox;

	JPasswordField passwordTextBox;

	JPasswordField passwordConfirmTextBox;

	JTextField secretQuestionTextBox;

	JTextField secretAnswerTextBox;

	JLabel passwordConflict;

	JLabel usernameTaken;

	JLabel somethingNotEntered;

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
		
		// Adds action listeners to relevant entities
		createAccount.addActionListener(this);

		// Gets the insets for the pane on the screen
		Insets insets = pane.getInsets();

		// Declares a size variable to properly size each entity on the screen
		Dimension size = createAccount.getPreferredSize();

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

	// PRE: None
	// POST: A new frame is created, components added, frame displayed
	private void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Macrosoft Media Works");
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
		SecurityControl sdb = new SecurityControl();
		sdb.loadLoginDatabase("logins.txt");
		if ((usernameTextBox.getText().length() == 0)
				|| (passwordTextBox.getPassword().length == 0)
				|| (passwordConfirmTextBox.getPassword().length == 0)
				|| (secretQuestionTextBox.getText().length() == 0)
				|| (secretAnswerTextBox.getText().length() == 0)) {
			somethingNotEntered.setVisible(true);
			if (passwordTextBox.getPassword() != passwordConfirmTextBox
					.getPassword()) {
				passwordConflict.setVisible(true);
				if (sdb.getUserNames().contains(usernameTextBox.getText()) == false) {
					usernameTaken.setVisible(false);
					sdb.appendLoginDatabase("logins.txt", usernameTextBox
							.getText(), passwordTextBox.getText(),
							secretQuestionTextBox.getText(),
							secretAnswerTextBox.getText());
				} else {
					usernameTaken.setVisible(true);
				}
			} else {
				passwordConflict.setVisible(false);
			}
		} else {
			somethingNotEntered.setVisible(false);
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
