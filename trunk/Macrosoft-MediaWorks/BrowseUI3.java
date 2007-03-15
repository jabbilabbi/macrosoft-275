// BrowseUI.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alex Androne

import java.awt.*;

import javax.swing.JButton;
//import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BrowseUI3 extends JFrame implements ActionListener  {

//	 Initalizes pane components
	
	private JLabel browseLibrary;
	//private JComboBox combo;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton backToMain, searchDB;
	private JTextField searchTF;
	
	//	 Initalizes variables
	final static boolean RIGHT_TO_LEFT = false; // GridBag layout manager will lay out components right to left if true and gridx/gridy components are not given
	private boolean ALLOW_COLUMN_SELECTION = true;
    private boolean ALLOW_ROW_SELECTION = true;	
    private ControllerClass controller;
    private int selectedRow;

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
		String[] mediaTypes = { "All", "CDs" }; // Items in the combo box
		String[] columnNames = { "Name", "Artist", "Genre", "Description" };
		Object[][] tableData = new Object[controller.getRowsNeeded()][4]; // Holds table data				
		
		// Assigns data from the database to tableData
		for (int i = 0; i < controller.getRowsNeeded(); i++) {
			String[] rowData = controller.getLibraryRow(i); // Holds a row of data from the database										
			for (int j = 0; j < 4; j++)
				// Assigns column data from a row to tableData
				tableData[i][j] = rowData[j];
		}
		
		// Declaration of pane components
		
		// JLABEL: Browse Library
		browseLibrary = new JLabel("Browse Library");
		browseLibrary.setFont(new Font("Helvetica", Font.BOLD, 16));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(20, 0, 20, 0); 
		c.anchor = GridBagConstraints.CENTER; 
		pane.add(browseLibrary, c);
		
		//JTEXTFIELD: Search Library
		searchTF = new JTextField("", 17);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(10, 0, 10, 0); 
		c.anchor = GridBagConstraints.CENTER; 
		pane.add(searchTF, c);
		
		
		//JBUTTON: Search Library
		searchDB = new JButton("Search Library");
		searchDB.setToolTipText("Finds all entries with given words in given order"); // Displays text when cursor is hovered over component		
		searchDB.setActionCommand("Search Library");
		searchDB.addActionListener(this);
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(10, 0, 10, 0); 		
		c.anchor = GridBagConstraints.LINE_END;
		insets = new Insets(0, 25, 0, 25);
		searchDB.setMargin(insets);
		pane.add(searchDB, c);
		
		// JTABLE
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(0, 0, 0, 0);
		table = new JTable(tableData, columnNames) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;	// Turns off the ability to edit cells directly
	        }
		};
		
		//DETECTS SELECTIONS FOR EACH CELL
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (ALLOW_ROW_SELECTION) { // true by default
            ListSelectionModel rowSM = table.getSelectionModel();
            rowSM.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    //Ignore extra messages.
                    if (e.getValueIsAdjusting()) return;

                    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                    if (lsm.isSelectionEmpty()) {
                        System.out.println("No rows are selected.");
                    } else {
                        selectedRow = lsm.getMinSelectionIndex();
                        System.out.println("Row " + selectedRow
                                           + " is now selected.");
                    }
                }
            });
        } else {
            table.setRowSelectionAllowed(false);
        }
		
        if (ALLOW_COLUMN_SELECTION) { // false by default
            if (ALLOW_ROW_SELECTION) {//Allows individual cell selection
                table.setCellSelectionEnabled(true);
            }
            table.setColumnSelectionAllowed(true);
            ListSelectionModel colSM =
                table.getColumnModel().getSelectionModel();
            colSM.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    //Ignore extra messages.
                    if (e.getValueIsAdjusting()) return;

                    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                    if (lsm.isSelectionEmpty()) {//No columns are selected
                        System.out.println("No columns are selected.");
                    } else {//selectedColumn is now selected
                        int selectedCol = lsm.getMinSelectionIndex();
                        if (selectedCol == 3) {
                        	DescriptionUI2 description = new DescriptionUI2(selectedRow);
                        	description.createAndShowGUI();
                        }
                        System.out.println("Column " + selectedCol + " is now selected.");
                    }
                }
            });
        }
		
		scrollPane = new JScrollPane(table); // Makes a scroll bar available if windows sized smaller than table size													
		scrollPane.setPreferredSize(new Dimension(500, 300));
		pane.add(scrollPane, c); // Add the scroll pane to this panel
		
		// JBUTTON: Back to Main
		backToMain = new JButton("Back to Main");
		backToMain.setActionCommand("Back to Main");
		backToMain.addActionListener(this);
		c.gridx = 0; // Lays out component at grid x coordinate 0
		c.gridy = 3; // Lays out component at grid y coordinate 0
		c.gridwidth = 2;
		c.weightx = 0.0; // 0.0-1.0 Determines how much additional space is
							// placed within adjacent columns
		c.weighty = 0.0; // 0.0-1.0 Determines how much additional space is
							// placed within adjacent rows
		c.insets = new Insets(20, 0, 20, 0); // Top,Left,Bottom,Right Determines padding around component in pixels
		c.anchor = GridBagConstraints.CENTER; // Aligns text to the right (LINE_END)
		insets = new Insets(20, 20, 20, 20);
		backToMain.setMargin(insets);
		backToMain.setToolTipText("Close browse window and open Main window"); // Displays text when cursor is hovered over component																		
		pane.add(backToMain, c);
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

	// Purpose: To display GUI
	// PRE: None
	// POST: Sets up GUI
	public void createAndShowGUI() {
		// Create and set up the window.
		setTitle("Media Works - Browse Library");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		addComponentsToPane(getContentPane());
		// Size and display the window
		//Insets insets = getInsets();
		setSize(720, 540);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		windowLookAndFeel();
	}

	// Purpose: To set action evemt for back to main button
	// PRE: Valid action event
	// POST: Sets up action event for back to main button
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals("Back to Main")) {
			controller.mainScreenFrame();
			dispose();
		}
		if (e.getSource().equals("Search Library")){
			ArrayList<String> newtable = controller.searchDB(searchTF.getText());
			//update shown table 
//			for (int i = 0; i < newtable.size(); i++) {
//				String[] rowData = controller.getLibraryRow(i); // Holds a row of data from the database										
//				for (int j = 0; j < 4; j++)
//					// Assigns column data from a row to tableData
//					tableData[i][j] = rowData[j];
//			}
		}
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// Creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BrowseUI3 ui = new BrowseUI3();
				ui.createAndShowGUI();
			}
		});
	}
}

/*
// COMBO BOX
String[] mediaTypes = { "All", "CDs" }; // Items in the combo box
JComboBox combo = new JComboBox(mediaTypes); // Passes mediaTypes to combo box													
combo.setSelectedIndex(0); // Sets the default item from mediaTypes to appear in the combo box
c.gridx = ;
c.gridy = ;
c.weightx = 0.0;
c.weighty = 0.0;
c.insets = new Insets(5, 10, 5, 10);
c.anchor = GridBagConstraints.LINE_START;
//pane.add(combo, c);
*/