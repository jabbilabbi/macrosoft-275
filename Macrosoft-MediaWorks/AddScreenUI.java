// AddScreenUI.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Scott Fuoco

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class AddScreenUI extends JFrame implements ActionListener{

	// Initalizes pane components
	private JComboBox mediaTypeSelected;
	private JButton add, backToMain;
	private JLabel mediaText, addText, chooseMediaText, addedText;
	private JPanel addSetup;
	
	// Initalizes variables
	private String artist, title, genre, description;
	private Boolean check_add;
	private final String CDs = "CDs";
	private final String UNSELECTED = "unselected";
	final private Dimension PANEL_SIZE = new Dimension(600,250);
	private CDsPanel CDsSelected = new CDsPanel(PANEL_SIZE);
	
	// Initializes a ControllerClass instance
	private ControllerClass controller;
	
	// Purpose: Add all components of the pane into the correct locations and with correct functions
	// PRE: Valid pane is given as a parameter
	// POST: All neceassray components for the Create Account screen will be
	// added and displayed
	public Component componentSetup() {
		JPanel pane = new JPanel();
		// Absolute container positioning used
		pane.setLayout(null);

		// Variable declaration
		String[] media_combo_box = { "Choose here", "CD" };
		// declaration of pane components

		// Combo boxes
		mediaTypeSelected = new JComboBox(media_combo_box);
		mediaTypeSelected.setActionCommand("Media Select");
		mediaTypeSelected.addActionListener(this);

		// Buttons
		add = new JButton("Add");
		add.setActionCommand("Add");
		add.addActionListener(this);
		backToMain = new JButton("Back to Main Screen");
		backToMain.setActionCommand("Back to Main");
		backToMain.addActionListener(this);

		// Labels
		mediaText = new JLabel("Select a media type:");
		mediaText.setFont(new Font("Helvetica", Font.PLAIN, 12));

		addText = new JLabel("Adding media:");
		addText.setFont(new Font("Helvetica", Font.BOLD, 14));


		chooseMediaText = new JLabel("Please choose a media type");
		chooseMediaText.setFont(new Font("Helvetica", Font.PLAIN, 10));


		addedText = new JLabel("Information successfully added");
		addedText.setFont(new Font("Helvetica", Font.PLAIN, 12));
		addedText.setForeground(Color.red);
		
		chooseMediaText.setForeground(Color.red);
		
		addSetup = new JPanel();
		addSetup.setLayout(new CardLayout()); 
		addSetup.setBorder(BorderFactory.createEtchedBorder());
		addSetup.setPreferredSize(new Dimension(600, 250));
		addSetup.add(new UnselectedPanel(PANEL_SIZE), UNSELECTED);
		addSetup.add(CDsSelected, CDs);
		
		
		// Add components to the pane
		pane.add(mediaTypeSelected);
		pane.add(backToMain);
		pane.add(add);
		pane.add(mediaText);
		pane.add(addText);
		pane.add(chooseMediaText);
		pane.add(addedText);
		pane.add(addSetup);
		
		// Screen positioning
		Insets insets = pane.getInsets();

		backToMain.setBounds(490 + insets.left, 425 + insets.top, 160, 75);

		add.setBounds(320 + insets.left, 425 + insets.top, 160, 75);

		mediaTypeSelected.setBounds(175 + insets.left, 128 + insets.top, 100,
				20);

		addText.setBounds(50 + insets.left, 50 + insets.top, 300, 75);

		mediaText.setBounds(50 + insets.left, 100 + insets.top, 150, 75);

		addedText.setBounds(450 + insets.left, 350 + insets.top, 300, 75);
		addedText.setVisible(false);
		
		addSetup.setBounds(50 + insets.left,160 + insets.top, 600, 250);

		return pane;

	}

	// Purpose: To create and display the 'Create Account' UI
	// PRE: None
	// POST: A new frame is created, components added, frame displayed
	public static void windowLookAndFeel(){
	    try{
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    }catch (Exception e) {
	        System.out.println("Look and Feel error: " + e);
	    }
	}	
	
	public void createAndShowGUI() {
		// Creates an instance of the controller class
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
	

	// PURPOSE: To check if all the CD fields are complete
	// PRE: None
	// POST: Sets the necessary JLabels to visible or not visible to notify the user
	//		 which fields need to be completed
	public void checkCD() {
		if (CDsSelected.titleField.getText().length() != 0) {
			title = CDsSelected.titleField.getText();
			CDsSelected.enterTitleText.setText(" ");
		} else {
			CDsSelected.enterTitleText.setText("Please enter a title");
			check_add = false;
		}
		if (CDsSelected.artistField.getText().length() != 0) {
			artist = CDsSelected.artistField.getText();
			CDsSelected.enterArtistText.setText(" ");
		} else {
			CDsSelected.enterArtistText.setText("Please enter an artist");
			check_add = false;
		}
		if (CDsSelected.genreField.getText().length() != 0) {
			genre = CDsSelected.genreField.getText();
			CDsSelected.enterGenreText.setText(" ");
		} else {
			CDsSelected.enterGenreText.setText("Please enter a genre");
			check_add = false;
		}

		description = CDsSelected.descriptionTextArea.getText();

	}
	
	// PURPOSE:Changes the main panel of the add screen
	// PRE: An int value from the combo box selected item
	// POST: The card layout will show the desired panel selected from the combo boc
	public void changePanel(int PanelID){
		CardLayout cl = (CardLayout)(addSetup.getLayout());
		switch(PanelID){
		case 0:
			cl.show(addSetup, UNSELECTED);
			break;
		case 1:
			cl.show(addSetup, CDs);
			break;
		default:
			break;
		}

	}
	// Purpose: To syncronize the actions of the user with the functionality of the screen
	// PRE: Valid action event as param
	// POST: Button functionality with proper conditions and actions taken
	public void actionPerformed(ActionEvent e) {
		controller.createUserDatabaseFile();
		
		// If backToMain button is pressed
		if (e.getActionCommand().equals("Back to Main")) {
			// Goes back to main screen
			controller.mainScreenFrame();
			dispose();
		}
		
		int selected_index = (int) mediaTypeSelected.getSelectedIndex();

			// If mediaTypeSelected is changed these actions occur
			if (e.getActionCommand().equals("Media Select")) {
				// Checks which item is selected in the combo box and sets up the screen
				// appropriately
				switch (selected_index) {
				case 0:
					changePanel(selected_index);
					break;
				case 1:
					changePanel(selected_index);
					addedText.setVisible(false);
					chooseMediaText.setVisible(false);
					CDsSelected.titleField.setText("");
					CDsSelected.artistField.setText("");
					CDsSelected.genreField.setText("");
					CDsSelected.descriptionTextArea.setText("");
					CDsSelected.enterTitleText.setText(" ");
					CDsSelected.enterArtistText.setText(" ");
					CDsSelected.enterGenreText.setText(" ");
					break;
				default:
					break;
				}

			}

			// If add button is pressed
			if (e.getActionCommand().equals("Add")) {
				check_add = true;

				// Checks which item is selected in the combo box and checks to make sure
				// all the fields are filled in
				switch (selected_index) {
				case 0:
					chooseMediaText.setBounds(276, 128, 150, 20);
					chooseMediaText.setVisible(true);
					break;
				case 1:
					checkCD();
					break;
				default:
					break;
				}


				if ((check_add) && (selected_index != 0)) {
					// Adds item to database and clears the text fields
					controller.appendMediaDatabase(title, artist, genre, description);
					addedText.setVisible(true);
					mediaTypeSelected.setSelectedIndex(0);
					CDsSelected.titleField.setText("");
					CDsSelected.artistField.setText("");
					CDsSelected.genreField.setText("");
					CDsSelected.descriptionTextArea.setText("");
				}
			}

		}
	
	// Testing method
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AddScreenUI addScreenUI = new AddScreenUI();
				addScreenUI.createAndShowGUI();
			}
		});

	}
}