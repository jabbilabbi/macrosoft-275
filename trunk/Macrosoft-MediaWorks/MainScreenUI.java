// MainScreenUI.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Scott Fuoco


import java.awt.Color;

import java.awt.Container;

import java.awt.Insets;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

public class MainScreenUI extends JFrame implements ActionListener{
	
	 // Intializes all components needed for the frame
	 private JLabel mainText, chooseText;
	 private JButton addMedia, browse, createHTML;
	 
	 // Sets up an instance of ControllerClass
	private ControllerClass controller;
	 
	// Purpose: Add all components of the pane into the correct locations and with correct functions
	// PRE: Valid pane is given as a parameter
	// POST: All neceassray components for the Create Account screen will be
	// added and displayed
	public void addComponentsToPane(Container pane) {
		
		// Absolute container positioning used
		pane.setLayout(null);
		
		// Declaration of pane components
		addMedia = new JButton("Add New Media");
		addMedia.addActionListener(this);
		
		browse = new JButton("Browse");
		browse.addActionListener(this);
		
		createHTML = new JButton("Create a Web Page");
		createHTML.addActionListener(this);
		
		mainText = new JLabel("Main Screen");

		mainText.setFont(new Font("Helvetica", Font.PLAIN, 28));

		chooseText = new JLabel("Choose one of the following:");
		chooseText.setFont(new Font("Helvetica", Font.PLAIN, 16));

		// Add components to the pane
		pane.add(mainText);
		pane.add(addMedia);
		pane.add(browse);
		pane.add(createHTML);
		pane.add(chooseText);

		// Screen positioning
		Insets insets = pane.getInsets();
        addMedia.setBounds(125 + insets.left, 300 + insets.top,
                150, 75);
        browse.setBounds(325 + insets.left, 300 + insets.top,
                150, 75);
        createHTML.setBounds(525 + insets.left, 300 + insets.top,
                150, 75);
        mainText.setBounds(320 + insets.left, 100 + insets.top,
                200, 150);
        chooseText.setBounds(300 + insets.left, 150 + insets.top,
                250, 150);

	}
	
    // Purpose: To create and display the 'Create Account' UI
	// PRE: None
	// POST: A new frame is created, components added, frame displayed
	public void createAndShowGUI() {
			controller = new ControllerClass();
			System.out.println(controller.user);
	        //Create and set up the window.
			setTitle("Media Works - Main Screen");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setResizable(false);
	        
	        //Set up the content pane.
	        addComponentsToPane(getContentPane());

	        //Size and display the window.
	        setSize(720, 540);
	        setVisible(true);

	    }
	
	// Purpose: To syncronize the actions of the user with the functionality of the screen
	// PRE: Valid action event as param
	// POST: Button functionality with proper conditions and actions taken
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
					
		if (b == this.addMedia){
			controller.addScreenFrame();
			dispose();
			
		}else if(b == this.browse){
			controller.browseFrame();
			dispose();
			
		}else if(b == this.createHTML){
			controller.createHTMLOutput();
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

