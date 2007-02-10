//Macrosoft - CMPT 275
//Programmer: Alex Antonio (the leet progger)

import java.awt.Color;
import java.awt.Container;
import java.awt.Insets;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class LoginUI{
	
	public static void addComponentsToPane(Container pane) {
		//Absolute container positioning used
		pane.setLayout(null);
		
		//Declaration of pane components
		JButton createAccount = new JButton("Create New Account");

		JButton login = new JButton("Login");
		
		JButton forgotPassword = new JButton("Forgot Password?");
		
		JTextField usernameTextBox = new JTextField(10);
		
		JPasswordField passwordTextBox = new JPasswordField(10);
		
		JLabel enterUsername = new JLabel("Username:");
		
		JLabel enterPassword = new JLabel("Password:");
		
		JLabel pleaseLogin = new JLabel("Login to your account:");
		
		//Add components to the pane
		pane.add(createAccount);
		pane.add(login);
		pane.add(forgotPassword);
		pane.add(usernameTextBox);
		pane.add(passwordTextBox);
		pane.add(enterUsername);
		pane.add(enterPassword);
		pane.add(pleaseLogin);
		
		//Screen positioning yo
		Insets insets = pane.getInsets();
        Dimension size = createAccount.getPreferredSize();
        createAccount.setBounds(305 + insets.left, 180 + insets.top,
                size.width, size.height);
        size = login.getPreferredSize();
        login.setBounds(320 + insets.left, 400 + insets.top,
                size.width, size.height);
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
        pleaseLogin.setBounds(315 + insets.left, 150 + insets.top,
                size.width, size.height);
	}
	 private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("Macrosoft Media Works");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //Set up the content pane.
	        addComponentsToPane(frame.getContentPane());

	        //Size and display the window.
	        Insets insets = frame.getInsets();
	        frame.setSize(800 + insets.left + insets.right,
	                      600 + insets.top + insets.bottom);
	        frame.setVisible(true);
	    }
	 public static void main(String[] args) {
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });

		}
}
