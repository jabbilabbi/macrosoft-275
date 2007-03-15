// DescriptionUI.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alex Androne

import java.awt.*;

import javax.swing.JButton;
//import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DescriptionUI extends JFrame implements ActionListener  {

//	 Initalizes pane components
	
	private JLabel details, nameLabel;
	private JTextField nameTextField;
	private JScrollPane scrollPane;
	private JButton backToBrowse;
	
	//	 Initalizes variables
	final static boolean RIGHT_TO_LEFT = false; // GridBag layout manager will lay out components right to left if true and gridx/gridy components are not given
    private String[] rowData;
	private int selectedRow;

    DescriptionUI () {
    	//Nothing
    }
    
    DescriptionUI (int row) {
    	selectedRow = row;
    }
    
	// Purpose: To add and display components
	// PRE: Valid pane is given as a parameter
	// POST: All necessary components for the Create Account screen will be added and displayed
	public void addComponentsToPane(Container pane) {
		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		// Lays out frame with GridBagLayout
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
//		 Variable declaration
		Insets insets;
		//DatabaseControl db = new DatabaseControl();
		//rowData = db.getLibraryRow(selectedRow); // Holds a row of data from the database										
			
			
		// Declaration of pane components
		
		// JLABEL: Details
		details = new JLabel("Details");
		details.setFont(new Font("Helvetica", Font.BOLD, 16));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(20, 0, 20, 0); 
		c.anchor = GridBagConstraints.CENTER; 
		pane.add(details, c);
		
//		JLABEL: Name
		nameLabel = new JLabel("Name:");	
		nameLabel.setFont(new Font("Helvetica", Font.PLAIN, 12));
		//nameLabel.setHorizontalAlignment(JLabel.LEFT);//doesnt work
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(20, 0, 20, 0); 
		c.anchor = GridBagConstraints.LINE_START; 
		pane.add(nameLabel, c);
		
		// JTEXTFIELD: Name
		nameTextField = new JTextField("Mr. Smith");	//rowData[0]
		nameTextField.setFont(new Font("Helvetica", Font.PLAIN, 12));
		//nameTextField.setHorizontalAlignment(JTextField.RIGHT);//doesnt work
		nameTextField.setEditable(false);
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(20, 0, 20, 0); 
		c.anchor = GridBagConstraints.LINE_END; 
		pane.add(nameTextField, c);
		
		// JBUTTON: Browse Library
		backToBrowse = new JButton("Browse Library");
		backToBrowse.addActionListener(this);
		c.gridx = 0; // Lays out component at grid x coordinate 0
		c.gridy = 2; // Lays out component at grid y coordinate 0
		c.gridwidth = 2;
		c.weightx = 0.0; // 0.0-1.0 Determines how much additional space is
							// placed within adjacent columns
		c.weighty = 0.0; // 0.0-1.0 Determines how much additional space is
							// placed within adjacent rows
		c.insets = new Insets(20, 0, 20, 0); // Top,Left,Bottom,Right Determines padding around component in pixels
		c.anchor = GridBagConstraints.CENTER; // Aligns text to the right (LINE_END)
		insets = new Insets(20, 20, 20, 20);
		backToBrowse.setMargin(insets);
		backToBrowse.setToolTipText("Close details window and open Browse Library window"); // Displays text when cursor is hovered over component																		
		pane.add(backToBrowse, c);
	}

	// Purpose: To display GUI
	// PRE: None
	// POST: Sets up GUI
	public void createAndShowGUI() {
		// Create and set up the window.
		setTitle("Media Works - Details");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set up the content pane.
		addComponentsToPane(getContentPane());

		// Size and display the window
		//Insets insets = getInsets();
		setSize(300, 500);
		/*
		setSize(300 + insets.left + insets.right, 500 + insets.top
				+ insets.bottom);
				*/
		setVisible(true);
		setResizable(false);
	}

	// Purpose: To set action evemt for back to main button
	// PRE: Valid action event
	// POST: Sets up action event for back to main button
	public void actionPerformed(ActionEvent e) {
		JButton b = new JButton();
		b = (JButton) e.getSource();

		BrowseUI3 browseUI3 = new BrowseUI3();

		if (b == backToBrowse) {
			browseUI3.createAndShowGUI();
			dispose();
		}
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// Creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DescriptionUI ui = new DescriptionUI();
				ui.createAndShowGUI();
			}
		});
	}
}