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

public class MainScreenUI implements ActionListener{
	
	 // intializes all components needed for the frame
	 private JLabel mainText, chooseText;
	 private JButton addMedia, browse, createHTML;
	 
	 // initializes the frame
	 private JFrame frame;
	 
	 // sets up an instance of SecurityControl
	 private SecurityControl sc = new SecurityControl();
	 
	// Purpose: Add all components of the pane into the correct locations and with correct functions
	// PRE: Valid pane is given as a parameter
	// POST: All neceassray components for the Create Account screen will be
	// added and displayed
	public void addComponentsToPane(Container pane) {
		
		sc.deleteCurrentUser();
		
		// absolute container positioning used
		pane.setLayout(null);
		
		// declaration of pane components
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

		// add components to the pane
		pane.add(mainText);
		pane.add(addMedia);
		pane.add(browse);
		pane.add(createHTML);
		pane.add(chooseText);

		// screen positioning
		Insets insets = pane.getInsets();
        addMedia.setBounds(125 + insets.left, 300 + insets.top,
                150, 75);
        browse.setBounds(325 + insets.left, 300 + insets.top,
                150, 75);
        createHTML.setBounds(525 + insets.left, 300 + insets.top,
                150, 75);
        createHTML.setVisible(false);
        mainText.setBounds(320 + insets.left, 100 + insets.top,
                200, 150);
        chooseText.setBounds(300 + insets.left, 150 + insets.top,
                250, 150);

	}
	
    // Purpose: To create and display the 'Create Account' UI
	// PRE: None
	// POST: A new frame is created, components added, frame displayed
	public void createAndShowGUI() {

	        //Create and set up the window.
	        frame= new JFrame("Macrosoft Media Works");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setResizable(false);
	        
	        //Set up the content pane.
	        addComponentsToPane(frame.getContentPane());

	        //Size and display the window.
	        Insets insets = frame.getInsets();
	        frame.setSize(800 + insets.left + insets.right,
	                      600 + insets.top + insets.bottom);
	        frame.setVisible(true);

	    }
	
	// PURPOSE:
	// PRE:
	// POST:
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		
		BrowseUI browseUI = new BrowseUI();
		AddScreenUI addScreenUI = new AddScreenUI();
					
		if (b == this.addMedia){
			addScreenUI.createAndShowGUI();
			frame.setVisible(false);
			
		}else if(b == this.browse){
			browseUI.createAndShowGUI();
			frame.setVisible(false);
			
		}else if(b == this.createHTML){

		}
		
	}

	// PURPOSE: Test that the Main Screen frame will be created
	// PRE: None.
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

