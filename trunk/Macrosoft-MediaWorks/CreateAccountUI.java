// CreateAccountUI.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alex Antonio

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class CreateAccountUI extends JFrame implements ActionListener {

	// Declaration of screen objects used later in action listener method
	private JButton createAccountBtn, backToLoginBtn;
	private JTextField usernameTB, secretATB;
	private JPasswordField passwordTB, passwordComfirmTB;
	private JLabel usernameLabel, passwordLabel, passwordComfirmLabel, secretQLabel,
					secretALabel, dynamicLabel, createAccountLabel;
	private JTextArea secretQTB;
	private JScrollPane secretQPane;
	private Dimension dim;

	// Purpose: Add all components of the pane into the correct locations and
	// with correct functions
	// PRE: Valid pane is given as a parameter
	// POST: All neceassray components for the Create Account screen will be
	// added and displayed
	public Component componentSetup() {
		dim = new Dimension(175, 20);
		usernameLabel = new JLabel("Username:");
		usernameTB = new JTextField(20);
		usernameTB.setMinimumSize(dim);
		usernameTB.setMaximumSize(dim);
		usernameTB.setAlignmentX(Component.LEFT_ALIGNMENT);
		usernameTB.setAlignmentY(Component.TOP_ALIGNMENT);
		
		passwordLabel = new JLabel("Password:");
		passwordTB = new JPasswordField(20);
		passwordTB.setMinimumSize(dim);
		passwordTB.setMaximumSize(dim);
		passwordTB.setAlignmentX(Component.LEFT_ALIGNMENT);
		passwordTB.setAlignmentY(Component.TOP_ALIGNMENT);
		
		passwordComfirmLabel = new JLabel("Confirm password:");
		passwordComfirmTB = new JPasswordField(20);
		passwordComfirmTB.setMinimumSize(dim);
		passwordComfirmTB.setMaximumSize(dim);
		passwordComfirmTB.setAlignmentX(Component.LEFT_ALIGNMENT);
		passwordComfirmTB.setAlignmentY(Component.TOP_ALIGNMENT);
		
		secretQLabel = new JLabel("Secret question:");
		secretQTB = new JTextArea(5, 20);
		secretQTB.setFont(new Font("New Roman Times", Font.PLAIN, 11));
		secretQPane = new JScrollPane(secretQTB,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		secretQTB.setLineWrap(true);
		secretQTB.setWrapStyleWord(true);
		
		Dimension areaSize = new Dimension(175,40);
		secretQPane.setMinimumSize(areaSize);
		secretQPane.setMaximumSize(areaSize);
		secretQPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		secretQPane.setAlignmentY(Component.TOP_ALIGNMENT);
		
		secretALabel = new JLabel("Secret answer:");
		secretATB = new JTextField(20);
		secretATB.setMinimumSize(dim);
		secretATB.setMaximumSize(dim);
		secretATB.setAlignmentX(Component.LEFT_ALIGNMENT);
		secretATB.setAlignmentY(Component.TOP_ALIGNMENT);
		
		dynamicLabel = new JLabel(" ");
		dynamicLabel.setFont(new Font("Helvetica", Font.PLAIN, 12));
		dim = dynamicLabel.getPreferredSize();
		dynamicLabel.setSize(dim);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.LINE_AXIS));
		infoPanel.setBorder(BorderFactory.createEtchedBorder());
		infoPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		infoPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.PAGE_AXIS));	
		labelPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		labelPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		labelPanel.add(Box.createRigidArea(new Dimension(0,12)));
		labelPanel.add(usernameLabel);
		labelPanel.add(Box.createRigidArea(new Dimension(0,17)));
		labelPanel.add(passwordLabel);
		labelPanel.add(Box.createRigidArea(new Dimension(0,17)));
		labelPanel.add(passwordComfirmLabel);
		labelPanel.add(Box.createRigidArea(new Dimension(0,17)));
		labelPanel.add(secretQLabel);
		labelPanel.add(Box.createRigidArea(new Dimension(0,33)));
		labelPanel.add(secretALabel);
		labelPanel.add(Box.createRigidArea(new Dimension(0,15)));
		
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.PAGE_AXIS));	
		fieldPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		fieldPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		fieldPanel.add(Box.createRigidArea(new Dimension(0,10)));
		fieldPanel.add(usernameTB);
		fieldPanel.add(Box.createRigidArea(new Dimension(0,10)));
		fieldPanel.add(passwordTB);
		fieldPanel.add(Box.createRigidArea(new Dimension(0,10)));
		fieldPanel.add(passwordComfirmTB);
		fieldPanel.add(Box.createRigidArea(new Dimension(0,10)));
		fieldPanel.add(secretQPane);
		fieldPanel.add(Box.createRigidArea(new Dimension(0,10)));
		fieldPanel.add(secretATB);
		fieldPanel.add(Box.createRigidArea(new Dimension(0,10)));

		infoPanel.add(Box.createRigidArea(new Dimension(25,0)));
		infoPanel.add(labelPanel);
		infoPanel.add(Box.createRigidArea(new Dimension(10,0)));
		infoPanel.add(fieldPanel);
		infoPanel.add(Box.createRigidArea(new Dimension(25,0)));
		
		createAccountLabel = new JLabel("Create an Account");
		createAccountLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		createAccountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		createAccountLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		createAccountBtn = new JButton("Create Account");
		dim = createAccountBtn.getPreferredSize();
		createAccountBtn.setMinimumSize(dim);
		createAccountBtn.setMaximumSize(dim);
		createAccountBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		createAccountBtn.setAlignmentY(Component.TOP_ALIGNMENT);
		createAccountBtn.setActionCommand("Create Account");
		createAccountBtn.addActionListener(this);
		
		backToLoginBtn = new JButton("Back to Login");
		dim = backToLoginBtn.getPreferredSize();
		backToLoginBtn.setMinimumSize(dim);
		backToLoginBtn.setMaximumSize(dim);
		backToLoginBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		backToLoginBtn.setAlignmentY(Component.TOP_ALIGNMENT);
		backToLoginBtn.setActionCommand("Back to Main");
		backToLoginBtn.addActionListener(this);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.LINE_AXIS));
		btnPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnPanel.setAlignmentY(Component.TOP_ALIGNMENT);

		btnPanel.add(createAccountBtn);
		btnPanel.add(backToLoginBtn);
		
		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.PAGE_AXIS));
		bodyPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		bodyPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		bodyPanel.add(createAccountLabel);
		bodyPanel.add(Box.createRigidArea(new Dimension(0,25)));
		bodyPanel.add(infoPanel);
		bodyPanel.add(Box.createRigidArea(new Dimension(0,10)));
		bodyPanel.add(btnPanel);
		
		JPanel primaryPanel = new JPanel();
		primaryPanel.setLayout(new BoxLayout(primaryPanel, BoxLayout.PAGE_AXIS));
		primaryPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		primaryPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		primaryPanel.add(Box.createRigidArea(new Dimension(0,75)));
		primaryPanel.add(createAccountLabel);
		primaryPanel.add(bodyPanel);
		return primaryPanel;
	}
	// Purpose: To create and display the 'Create Account' UI
	// PRE: None
	// POST: A new frame is created, components added, frame displayed
	public void createAndShowGUI() {
		// Create and set up the window
		windowLookAndFeel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Macrosoft Media Works");
		setResizable(false);
		add(componentSetup());
		pack();
		setSize(720,540);
		setVisible(true);
	}
	
	public static void windowLookAndFeel(){
	    try{
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    }catch (Exception e) {
	        System.out.println("Look and Feel error: " + e);
	    }
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
		if ((usernameTB.getText().length() == 0)
				|| (passwordTB.getPassword().length == 0)
				|| (passwordComfirmTB.getPassword().length == 0)
				|| (secretQTB.getText().length() == 0)
				|| (secretQTB.getText().length() == 0)) {
			dynamicLabel.setVisible(true); // Show relevant error
		} else {
			dynamicLabel.setVisible(false); // Remove relevant error

			String password = String.valueOf(passwordTB.getPassword());
			String confirmPassword = String.valueOf(passwordComfirmTB
					.getPassword());
			// Condition where password entered is different then the confirmed
			// password
			if (password.compareTo(confirmPassword) != 0) {
				dynamicLabel.setVisible(true); // Show relevant error
			} else {
				dynamicLabel.setVisible(false); // Remove relevant error
				// Condition where username is already in login DB
				if (sdb.getUserNames().contains(usernameTB.getText()) == false) {
					dynamicLabel.setVisible(false); // Remove relevant error
					sdb.appendLoginDatabase(usernameTB.getText(),
							secretQTB.getText(), secretQTB
									.getText(), secretQTB.getText()); // Adds
																				// the
																				// login
																				// DB
																				// entry

					loginUI.createAndShowGUI(); // Returns to the Login Screen
					dispose(); // disposes of current frame

				} else {
					dynamicLabel.setVisible(true); // Show relevant error
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
