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
	private String[] addInfo;
	private final String CDs = "CDs";
	private final String DVDs = "DVDs";
	private final String Books = "Books";
	private final String Games = "Games";
	private final String UNSELECTED = "unselected";
	private Boolean checkAdd;
	final private Dimension PANEL_SIZE = new Dimension(600,250);
	private CDsPanel CDsSelected = new CDsPanel(PANEL_SIZE);
	private DVDsPanel DVDsSelected = new DVDsPanel(PANEL_SIZE);
	private BooksPanel BooksSelected = new BooksPanel(PANEL_SIZE);
	private GamesPanel GamesSelected = new GamesPanel(PANEL_SIZE);
	
	final private String[] MEDIA_COMBO_BOX = { "Choose here", "CD", "DVD", "Book", "Video Game" };
	
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
		// declaration of pane components

		// Combo boxes
		mediaTypeSelected = new JComboBox(MEDIA_COMBO_BOX);
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
		addSetup.add(DVDsSelected, DVDs);
		addSetup.add(BooksSelected, Books);
		addSetup.add(GamesSelected, Games);
		
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
	// Purpose: To create and display the 'Add Screen' UI
	// PRE: None
	// POST: A new frame is created, components added, frame displayed
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
		case 2:
			cl.show(addSetup, DVDs);
			break;
		case 3:
			cl.show(addSetup, Books);
			break;
		case 4:
			cl.show(addSetup, Games);
			break;
		default:
			break;
		}

	}
	// Purpose: To syncronize the actions of the user with the functionality of the screen
	// PRE: Valid action event as param
	// POST: Button functionality with proper conditions and actions taken
	public void actionPerformed(ActionEvent e) {
		DatabaseControl db = new DatabaseControl();
		controller.createUserDatabaseFile();
		checkAdd = false;
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
					CDsSelected.clearCDs();
					break;
				case 2:
					changePanel(selected_index);
					addedText.setVisible(false);
					chooseMediaText.setVisible(false);
					DVDsSelected.clearDVDs();
					break;
				case 3:
					changePanel(selected_index);
					addedText.setVisible(false);
					chooseMediaText.setVisible(false);
					BooksSelected.clearBooks();
					break;
				case 4:
					changePanel(selected_index);
					addedText.setVisible(false);
					chooseMediaText.setVisible(false);
					GamesSelected.clearGames();
					break;
				default:
					break;
				}

			}

			// If add button is pressed
			if (e.getActionCommand().equals("Add")) {
				// Checks which item is selected in the combo box and checks to make sure
				// all the fields are filled in
				switch (selected_index) {
				case 0:
					chooseMediaText.setBounds(276, 128, 150, 20);
					chooseMediaText.setVisible(true);
					break;
				case 1:
					if(CDsSelected.checkCD()){
						addInfo = new String[5];
						addInfo[0] = "CD";
						addInfo[1] = CDsSelected.titleField.getText();
						addInfo[2] = CDsSelected.artistField.getText();
						addInfo[3] = CDsSelected.genreField.getText();
						addInfo[4] = CDsSelected.descriptionTextArea.getText();
						if(addInfo[4].length() == 0){
								addInfo[4] = " ";								
						}
						controller.appendMediaDatabase(controller.cdb.CDItems, addInfo);
						checkAdd = true;
					}
					break;
				case 2:
					if(DVDsSelected.checkDVD()){
						addInfo = new String[7];
						addInfo[0] = "DVD";
						addInfo[1] = DVDsSelected.titleField.getText();
						addInfo[2] = DVDsSelected.artistField.getText();
						addInfo[3] = DVDsSelected.genreField.getText();
						addInfo[4] = DVDsSelected.descriptionTextArea.getText();
						addInfo[5] = (String) DVDsSelected.stars.getSelectedItem();
						addInfo[6] = (String) DVDsSelected.ratings.getSelectedItem();
						if(addInfo[4].length() == 0){
							addInfo[4] = " ";								
						}
						if(addInfo[5].contains("stars")){
							addInfo[5] = "N/A";								
					}
						if(addInfo[6].contains("rating")){
							addInfo[6] = "N/A";								
					}
						controller.appendMediaDatabase(controller.cdb.DVDItems, addInfo);
						checkAdd = true;
					}
					break;
				case 3:
					if(BooksSelected.checkBook()){
						addInfo = new String[6];
						addInfo[0] = "Book";
						addInfo[1] = BooksSelected.titleField.getText();
						addInfo[2] = BooksSelected.artistField.getText();
						addInfo[3] = BooksSelected.genreField.getText();
						addInfo[4] = BooksSelected.descriptionTextArea.getText();
						addInfo[5] = BooksSelected.publisherField.getText();
						if(addInfo[4].length() == 0){
							addInfo[4] = " ";								
						}
						if(addInfo[5].length() == 0){
							addInfo[5] = " ";								
						}
						controller.appendMediaDatabase(controller.cdb.BookItems, addInfo);
						checkAdd = true;
					}
					break;
				case 4:
					if(GamesSelected.checkGame()){
						addInfo = new String[8];
						addInfo[0] = "Game";
						addInfo[1] = GamesSelected.titleField.getText();
						addInfo[2] = GamesSelected.artistField.getText();
						addInfo[3] = GamesSelected.genreField.getText();
						addInfo[4] = GamesSelected.descriptionTextArea.getText();
						addInfo[5] = GamesSelected.publisherField.getText();
						addInfo[6] = GamesSelected.platformField.getText();
						addInfo[7] = (String) GamesSelected.ESRBs.getSelectedItem();
						if(addInfo[4].length() == 0){
							addInfo[4] = " ";								
						}
						if(addInfo[5].length() == 0){
							addInfo[5] = " ";								
						}
						if(addInfo[6].length() == 0){
							addInfo[6] = " ";								
						}
						if(addInfo[7].contains("ESRB")){
							addInfo[7] = "N/A";								
					}
						controller.appendMediaDatabase(controller.cdb.GameItems, addInfo);
						checkAdd = true;
					}
					break;
				default:
					break;
				}


				if (checkAdd){
					// Adds item to database and clears the text fields
					addedText.setVisible(true);
					mediaTypeSelected.setSelectedIndex(0);
					CDsSelected.clearCDs();
					DVDsSelected.clearDVDs();
					BooksSelected.clearBooks();
					GamesSelected.clearGames();
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