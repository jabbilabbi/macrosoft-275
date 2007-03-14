// DescriptionUI.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alex Androne

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseUI2 extends JFrame implements ActionListener  {

	//	 Initalizes pane components
	private JButton backToMain;
	private JLabel browseLibrary;
	//private JLabel displayBy;
	private JComboBox combo;
	private JTable table;
	
	//	 Initalizes variables
	final static boolean RIGHT_TO_LEFT = false; // GridBag layout manager will lay out components right to left if true and gridx/gridy components are not given
	private boolean ALLOW_COLUMN_SELECTION = true;
    private boolean ALLOW_ROW_SELECTION = true;		
	
    //  Initalizes the frame
	private JFrame frame;
	
	final private Dimension PANEL_SIZE = new Dimension(600,250);
	final private Dimension SPACING = new Dimension(25, 0);
	
	private ControllerClass controller;
	
	// Purpose: Add all components of the pane into the correct locations and with correct functions
	// PRE: Valid pane is given as a parameter
	// POST: All neceassray components for the Create Account screen will be
	// added and displayed
	public Component addComponentsToPane(Container pane) {
		
		// Absolute container positioning used
		pane.setLayout(null);

		// Variable declaration
		String[] mediaTypes = { "All", "CDs" }; // Items in the combo box
		// declaration of pane components
		
		// Combo box
		combo = new JComboBox(mediaTypes); // Passes mediaTypes to combo box				
		combo.setActionCommand("Media Select");
		combo.addActionListener(this);

		// Buttons
		backToMain = new JButton("Back to Main");
		backToMain.setActionCommand("Back to Main");
		backToMain.addActionListener(this);
	}
	
}