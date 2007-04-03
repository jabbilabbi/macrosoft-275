// MainScreenUI.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Scott Fuoco


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.Container;

import java.awt.Insets;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

public class MainScreenUI extends JFrame implements ActionListener{
	
	 // Intializes all components needed for the frame
	 private JLabel mainLabel, chooseLabel, createdHTMLLabel;
	 private JButton addMediaBtn, browseBtn, createHTMLBtn;
	 
	 // Sets up an instance of ControllerClass
	private ControllerClass controller;
	 
	// Purpose: Add all components of the pane into the correct locations and with correct functions
	// PRE: Valid pane is given as a parameter
	// POST: All neceassray components for the Create Account screen will be
	// added and displayed
	public Container componentSetup() {
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		JPanel mainScreen = new JPanel();
		mainScreen.setLayout(new BoxLayout(mainScreen, BoxLayout.PAGE_AXIS));
		mainScreen.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainScreen.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		// Declaration of pane components
		addMediaBtn = new JButton("Add New Media");
		addMediaBtn.setFont(new Font("Helvetica", Font.BOLD, 11));
		addMediaBtn.setMinimumSize(new Dimension(140, 75));
		addMediaBtn.setPreferredSize(new Dimension(140, 75));
		addMediaBtn.setMaximumSize(new Dimension(140, 75));
		addMediaBtn.setActionCommand("Add Media");
		addMediaBtn.addActionListener(this);
		addMediaBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		addMediaBtn.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		browseBtn = new JButton("Browse");
		browseBtn.setFont(new Font("Helvetica", Font.BOLD, 11));
		browseBtn.setMinimumSize(new Dimension(140, 75));
		browseBtn.setPreferredSize(new Dimension(140, 75));
		browseBtn.setMaximumSize(new Dimension(140, 75));
		browseBtn.setActionCommand("Browse Media");
		browseBtn.addActionListener(this);
		browseBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		browseBtn.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		createHTMLBtn = new JButton("Create a Web Page");
		createHTMLBtn.setFont(new Font("Helvetica", Font.BOLD, 11));
		createHTMLBtn.setMinimumSize(new Dimension(140, 75));
		createHTMLBtn.setPreferredSize(new Dimension(140, 75));
		createHTMLBtn.setMaximumSize(new Dimension(140, 75));
		createHTMLBtn.setActionCommand("Create HTML");
		createHTMLBtn.addActionListener(this);
		createHTMLBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		createHTMLBtn.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		mainLabel = new JLabel("Main Screen");
		mainLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

		chooseLabel = new JLabel("Choose one of the following:");
		chooseLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
		chooseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		chooseLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		createdHTMLLabel = new JLabel(" ");
		createdHTMLLabel.setFont(new Font("Helvetica", Font.PLAIN, 10));
		createdHTMLLabel.setForeground(Color.red);
		createdHTMLLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		createdHTMLLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		JPanel HTMLPanel = new JPanel();
		HTMLPanel.setLayout(new BoxLayout(HTMLPanel, BoxLayout.PAGE_AXIS));
		HTMLPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		HTMLPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		HTMLPanel.add(createdHTMLLabel);
		HTMLPanel.add(createHTMLBtn);

		buttonPanel.setBorder(BorderFactory.createEtchedBorder());
		buttonPanel.add(addMediaBtn);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(browseBtn);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(HTMLPanel);
		
		mainScreen.add(Box.createRigidArea(new Dimension(0, 75)));
		mainScreen.add(mainLabel);
		mainScreen.add(Box.createRigidArea(new Dimension(0, 25)));
		mainScreen.add(chooseLabel);
		mainScreen.add(Box.createRigidArea(new Dimension(0, 50)));
		mainScreen.add(buttonPanel);
		
		JPanel primaryPanel = new JPanel();
		primaryPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		primaryPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		primaryPanel.add(mainScreen);
		
        return primaryPanel;

	}
	
	// Purpose: Set the look and feel of the window
	// PRE: None
	// POST: Sets the window look and feel to the system look and feel
	public static void windowLookAndFeel(){
	    try{
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    }catch (Exception e) {
	        System.out.println("Look and Feel error: " + e);
	    }
	}	
	
	
	
	public void createAndShowGUI() {
		// Creates an instance of the UI controller
		controller = new ControllerClass();
		// Create and set up the window
		windowLookAndFeel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Media Works - Add Screen");
		setResizable(false);
		add(componentSetup());
		pack();
		setSize(720,540);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	// Purpose: To syncronize the actions of the user with the functionality of the screen
	// PRE: Valid action event as param
	// POST: Button functionality with proper conditions and actions taken
	public void actionPerformed(ActionEvent e) {
		
		// Actions if Add Media button is pressed
		if (e.getActionCommand().equals("Add Media")){
			controller.addScreenFrame();
			dispose();
			
		}
		// Actions if Browse Media button is pressed
		if (e.getActionCommand().equals("Browse Media")){
			controller.browseFrame();
			dispose();
			
		}
		// Actions if Create HTML button is pressed
		if (e.getActionCommand().equals("Create HTML")){
			controller.createHTMLOutput();
			createdHTMLLabel.setText("HTML successfully created");
		}
		
	}

	// PURPOSE: Test that the Main Screen frame will be created
	// PRE: None
	// POST: Creates a the Main Screen frame	
	 public static void main(String[] args) {

		 javax.swing.SwingUtilities.invokeLater(new Runnable() {

	            public void run() {
	            	MainScreenUI ui = new MainScreenUI();
	                ui.createAndShowGUI();

	            }

	        });



		}

}

