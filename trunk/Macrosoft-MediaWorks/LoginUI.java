// LoginUI.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alex Antonio

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginUI extends JFrame implements ActionListener {

	// Declarations of UI Elements
	private JButton createAccountBtn, loginBtn, forgotPasswordBtn,
			checkAnswerBtn;
	private JTextField usernameTB, secretAnswerTB;
	private JPasswordField passwordTB;
	private JLabel usernameLabel, passwordLabel, loginLabel, dynamicLabel,
			title, secretQuestionLabel, secretAnswerLabel,
			secretQuestionContentLabel;
	private String dynamicText;
	private Dimension dim;
	private JPanel forgotPasswordPanel;
	private ControllerClass controller;
	private String currentUser;

	// Purpose: Add all components of the pane into the correct locations and
	// with correct functions
	// PRE: Valid pane is given as a parameter
	// POST: All neceassray components for the Create Account screen will be
	// added and displayed
	private Component componentSetup() {
		// Login panel
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));
		loginPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

		// Creates a label instruction the user
		loginLabel = new JLabel("Log into your account");
		loginLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
		loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

		// Forgot Password panel
		forgotPasswordPanel = new JPanel();
		forgotPasswordPanel.setLayout(new BoxLayout(forgotPasswordPanel,
				BoxLayout.LINE_AXIS));
		forgotPasswordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		forgotPasswordPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

		// Forgot Password panel
		JPanel centerForgotPasswordPanel = new JPanel();
		centerForgotPasswordPanel.setLayout(new BoxLayout(
				centerForgotPasswordPanel, BoxLayout.LINE_AXIS));
		centerForgotPasswordPanel.setBorder(BorderFactory.createEtchedBorder());
		centerForgotPasswordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerForgotPasswordPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

		// Sub-Panel to Forgot Password panel
		JPanel labelPanel2 = new JPanel();
		labelPanel2.setLayout(new BoxLayout(labelPanel2, BoxLayout.PAGE_AXIS));
		labelPanel2.setAlignmentX(Component.LEFT_ALIGNMENT);
		labelPanel2.setAlignmentY(Component.TOP_ALIGNMENT);

		// Sub-Panel to Forgot Password panel
		JPanel fieldPanel2 = new JPanel();
		fieldPanel2.setLayout(new BoxLayout(fieldPanel2, BoxLayout.PAGE_AXIS));
		fieldPanel2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		fieldPanel2.setAlignmentY(Component.TOP_ALIGNMENT);

		// Sets the text and dimentions of labels
		secretQuestionLabel = new JLabel("Secret Question:");
		secretAnswerLabel = new JLabel("Secret Answer:");
		secretQuestionContentLabel = new JLabel(" ");
		secretQuestionContentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		secretQuestionContentLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		secretAnswerTB = new JTextField(20);
		secretAnswerTB.setAlignmentX(Component.LEFT_ALIGNMENT);
		secretAnswerTB.setAlignmentY(Component.TOP_ALIGNMENT);
		dim = secretAnswerTB.getPreferredSize();
		secretAnswerTB.setMinimumSize(dim);
		secretAnswerTB.setMaximumSize(dim);

		// Adds components to sub-panel
		labelPanel2.add(Box.createRigidArea(new Dimension(0, 10)));
		labelPanel2.add(secretQuestionLabel);
		labelPanel2.add(Box.createRigidArea(new Dimension(0, 15)));
		labelPanel2.add(secretAnswerLabel);
		labelPanel2.add(Box.createRigidArea(new Dimension(0, 10)));

		// Declares button functionality, dimentions and position
		checkAnswerBtn = new JButton("Answer");
		dim = checkAnswerBtn.getPreferredSize();
		checkAnswerBtn.setMinimumSize(dim);
		checkAnswerBtn.setMaximumSize(dim);
		checkAnswerBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		checkAnswerBtn.setAlignmentY(Component.TOP_ALIGNMENT);
		checkAnswerBtn.setActionCommand("Answer");
		checkAnswerBtn.addActionListener(this);

		// Adds components to sub-panel
		fieldPanel2.add(Box.createRigidArea(new Dimension(0, 10)));
		fieldPanel2.add(secretQuestionContentLabel);
		fieldPanel2.add(Box.createRigidArea(new Dimension(0, 15)));
		fieldPanel2.add(secretAnswerTB);
		fieldPanel2.add(Box.createRigidArea(new Dimension(0, 10)));

		JPanel secretAnswerBtnPanel = new JPanel();
		secretAnswerBtnPanel.setLayout(new BoxLayout(secretAnswerBtnPanel,
				BoxLayout.PAGE_AXIS));
		secretAnswerBtnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		secretAnswerBtnPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		secretAnswerBtnPanel.add(fieldPanel2);
		secretAnswerBtnPanel.add(checkAnswerBtn);

		// Adds sub-panels to main panel
		centerForgotPasswordPanel
				.add(Box.createRigidArea(new Dimension(25, 0)));
		centerForgotPasswordPanel.add(labelPanel2);
		centerForgotPasswordPanel
				.add(Box.createRigidArea(new Dimension(10, 0)));
		centerForgotPasswordPanel.add(secretAnswerBtnPanel);
		centerForgotPasswordPanel
				.add(Box.createRigidArea(new Dimension(25, 0)));

		forgotPasswordPanel.add(centerForgotPasswordPanel);
		forgotPasswordPanel.setVisible(false);

		// Username/Password border panel
		JPanel usernamePasswordPanel = new JPanel();
		usernamePasswordPanel.setLayout(new BoxLayout(usernamePasswordPanel,
				BoxLayout.LINE_AXIS));
		usernamePasswordPanel.setBorder(BorderFactory.createEtchedBorder());
		usernamePasswordPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		usernamePasswordPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);

		// Sub-Panel to username Password Panel
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.PAGE_AXIS));
		labelPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		labelPanel.setAlignmentY(Component.TOP_ALIGNMENT);

		// Sub-Panel to username Password Panel
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.PAGE_AXIS));
		fieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		fieldPanel.setAlignmentY(Component.TOP_ALIGNMENT);

		// Declares components of sub-panel
		usernameLabel = new JLabel("Username:");
		usernameTB = new JTextField(20);
		dim = usernameTB.getPreferredSize();
		usernameTB.setMinimumSize(dim);
		usernameTB.setMaximumSize(dim);
		usernameTB.setAlignmentX(Component.LEFT_ALIGNMENT);
		usernameTB.setAlignmentY(Component.TOP_ALIGNMENT);

		// Declares components of sub-panel
		passwordLabel = new JLabel("Password:");
		passwordTB = new JPasswordField(20);
		dim = usernameTB.getPreferredSize();
		passwordTB.setMinimumSize(dim);
		passwordTB.setMaximumSize(dim);
		passwordTB.setAlignmentX(Component.LEFT_ALIGNMENT);
		passwordTB.setAlignmentY(Component.TOP_ALIGNMENT);

		// Adds components to sub-panel
		labelPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		labelPanel.add(usernameLabel);
		labelPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		labelPanel.add(passwordLabel);
		labelPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Adds components to sub-panel
		fieldPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		fieldPanel.add(usernameTB);
		fieldPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		fieldPanel.add(passwordTB);
		fieldPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Adds sub-panels to main panel
		usernamePasswordPanel.add(Box.createRigidArea(new Dimension(25, 0)));
		usernamePasswordPanel.add(labelPanel);
		usernamePasswordPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		usernamePasswordPanel.add(fieldPanel);
		usernamePasswordPanel.add(Box.createRigidArea(new Dimension(25, 0)));

		// Declares button functionality, dimentions and position
		loginBtn = new JButton("Login");
		dim = loginBtn.getPreferredSize();
		loginBtn.setMinimumSize(dim);
		loginBtn.setMaximumSize(dim);
		loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
		loginBtn.setActionCommand("Login");
		loginBtn.addActionListener(this);

		// Declares button functionality, dimentions and position
		createAccountBtn = new JButton("Create an Account");
		dim = createAccountBtn.getPreferredSize();
		createAccountBtn.setMinimumSize(dim);
		createAccountBtn.setMaximumSize(dim);
		createAccountBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		createAccountBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
		createAccountBtn.setActionCommand("Create an Account");
		createAccountBtn.addActionListener(this);

		// Declares button functionality, dimentions and position
		forgotPasswordBtn = new JButton("Forgot Your Password?");
		dim = new Dimension(180, 23);
		forgotPasswordBtn.setMinimumSize(dim);
		forgotPasswordBtn.setMaximumSize(dim);
		forgotPasswordBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		forgotPasswordBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
		forgotPasswordBtn.setActionCommand("Forgot your Password");
		forgotPasswordBtn.addActionListener(this);

		// Declares a sub-panel the top of the screen
		JPanel topLogin = new JPanel();
		topLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		topLogin.setAlignmentY(Component.CENTER_ALIGNMENT);
		topLogin.setLayout(new BoxLayout(topLogin, BoxLayout.PAGE_AXIS));
		topLogin.add(loginLabel);
		topLogin.add(loginPanel);

		// Declares a sub-panel for the bottom of the screen
		JPanel bottomLogin = new JPanel();
		bottomLogin.setLayout(new BoxLayout(bottomLogin, BoxLayout.LINE_AXIS));
		bottomLogin.setAlignmentX(Component.RIGHT_ALIGNMENT);
		bottomLogin.add(createAccountBtn);
		bottomLogin.add(loginBtn);

		// Adds sub-panels to main panel
		loginPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		loginPanel.add(usernamePasswordPanel);
		loginPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		loginPanel.add(bottomLogin);

		// Defines screen title
		title = new JLabel("     Macrosoft Personal Library     ");
		title.setFont(new Font("Helvetica", Font.BOLD, 24));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setAlignmentY(Component.CENTER_ALIGNMENT);

		// Defines panel for the title screen
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
		titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titlePanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		titlePanel.add(title);
		titlePanel.add(Box.createRigidArea(new Dimension(0, 25)));
		titlePanel.add(topLogin);

		// Defines Dynamic Text used for error messages
		dynamicText = " ";
		dynamicLabel = new JLabel(dynamicText);
		dynamicLabel.setForeground(Color.red);
		dynamicLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		dynamicLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

		// Declares a sub-panel
		JPanel componentsPanel = new JPanel();
		componentsPanel.setLayout(new BoxLayout(componentsPanel,
				BoxLayout.PAGE_AXIS));
		componentsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		componentsPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		// Adds appropriate sub-panels and labels
		componentsPanel.add(Box.createRigidArea(new Dimension(0, 75)));
		componentsPanel.add(titlePanel);
		componentsPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		componentsPanel.add(dynamicLabel);
		componentsPanel.add(forgotPasswordPanel);

		JPanel forgotBtnPanel = new JPanel();
		forgotBtnPanel.setLayout(new BoxLayout(forgotBtnPanel,
				BoxLayout.LINE_AXIS));
		forgotBtnPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		forgotBtnPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		forgotBtnPanel.add(Box.createRigidArea(new Dimension(290, 0)));
		forgotBtnPanel.add(forgotPasswordBtn);
		forgotBtnPanel.add(Box.createRigidArea(new Dimension(0, 50)));

		// Declares the main panel
		JPanel primaryPanel = new JPanel();
		primaryPanel.setLayout(new BorderLayout());
		// Adds appropriate sub-panels
		primaryPanel.add(componentsPanel, BorderLayout.CENTER);
		primaryPanel.add(forgotBtnPanel, BorderLayout.PAGE_END);

		return primaryPanel;
	}

	public static void windowLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Look and Feel error: " + e);
		}
	}

	// Purpose: To create and display the 'Create Account' UI
	// PRE: None
	// POST: A new frame is created, components added, frame displayed
	public void createAndShowGUI() {
		// Creates an instance of the UI controller
		controller = new ControllerClass();

		// Create and set up the window
		windowLookAndFeel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Media Works - Login Screen");
		setResizable(false);
		add(componentSetup());
		pack();
		setSize(720, 540);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// Purpose: To syncronize the actions of the user with the functionality of
	// the screen
	// PRE: Valid action event as param
	// POST: Button functionality with proper conditions and actions taken
	public void actionPerformed(ActionEvent e) {
		controller.loadLoginDatabase(); // Loads the Login DB

		Boolean fieldsComplete = true;
		String usernameEntered = String.valueOf(usernameTB.getText());
		String passwordEntered = String.valueOf(passwordTB.getPassword());
		// Stores the current username
		String tempUser = usernameTB.getText();
		String secretAnswer = String.valueOf(secretAnswerTB.getText());

		int user_result = controller.checkLogin(usernameEntered,
				passwordEntered);

		// Case where button pressed was 'Forgot your Password'
		if (e.getActionCommand().equals("Forgot your Password")) {
			// Case where username is in DB
			if (controller.checkIfUserExists(tempUser)) {
				// Turns off error messages
				dynamicLabel.setVisible(false);
				// Turns on forgot password panel
				forgotPasswordPanel.setVisible(true);
				// Gets secret question from DB
				secretQuestionContentLabel.setText(controller
						.getSecretInfo(tempUser)[0]);
			}
			// Case where username is not in DB
			else {
				dynamicText = "That username is not found, please enter a valid username";
				dynamicLabel.setText(dynamicText);
				// Shows relevant error
				dynamicLabel.setVisible(true);
				forgotPasswordPanel.setVisible(false);
			}

		}
		// Case where Answer button was hit
		if (e.getActionCommand().equals("Answer")) {
			// Case where correct Answer is given
			if (controller.getSecretInfo(tempUser)[1].equals(secretAnswer)) {
				// Turns off the forgot password panel
				forgotPasswordPanel.setVisible(false);
				// Password is printed for user
				dynamicText = "Your password is: "
						+ controller.getPassword(tempUser);
				// Password is set to label
				dynamicLabel.setText(dynamicText);
				// Password is displayed for user
				dynamicLabel.setVisible(true);
			}
			// Case where correct Answer is not given
			else {
				// Error message for user
				dynamicText = "Incorrect secret question answer, try again";
				dynamicLabel.setText(dynamicText);
				dynamicLabel.setVisible(true);
			}

		}
		// Case where button pressed was the Login button
		if (e.getActionCommand().equals("Login")) {
			// Checks if username wasn't completed
			if (usernameTB.getText().length() == 0) {
				// Fields are not complete
				fieldsComplete = false;
				// user prompted for appropriate action
				dynamicText = "Please enter username";
				dynamicLabel.setText(dynamicText);
				// Shows relevant error
				dynamicLabel.setVisible(true);
			}
			// Case where username is entered
			else {
				// Removes relevant error
				dynamicLabel.setVisible(false);
			}
			// Checks if the password wasn't completed
			if ((passwordTB.getText().length() == 0)
					&& (usernameTB.getText().length() != 0)) {
				fieldsComplete = false;
				dynamicText = "Please enter password";
				dynamicLabel.setText(dynamicText);
				// Shows relevant error
				dynamicLabel.setVisible(true);
			} else if ((passwordTB.getText().length() != 0)
					&& (usernameTB.getText().length() == 0)) {
				dynamicText = "Please enter username";
				dynamicLabel.setText(dynamicText);
				// Shows relevatn error
				dynamicLabel.setVisible(true);
			}

			// Condition where the login entry was invalid
			if ((user_result == 1) && (fieldsComplete == true)) {
				// Removes releavnt error
				dynamicLabel.setVisible(false);
				currentUser = usernameEntered;
				controller.setCurrentUser(currentUser);
				controller.mainScreenFrame();
				// Disposes of current frame
				dispose();

			} else if ((user_result != 1) && (fieldsComplete == true)) {
				dynamicText = "Invalid username or password";
				dynamicLabel.setText(dynamicText);
				// Shows relevant error
				dynamicLabel.setVisible(true);
			}

		}
		// Case where button pressed was Create an Account
		if (e.getActionCommand().equals("Create an Account")) {
			// Load nwe frame for Create an Account
			controller.createAccountFrame();
			// Throws out current frame
			dispose();
		}

	}

	// Test Method
	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				LoginUI loginUI = new LoginUI();
				loginUI.createAndShowGUI();
			}
		});
	}
}
