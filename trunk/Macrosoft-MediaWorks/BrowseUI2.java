// BrowseUI2.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alex Androne

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class BrowseUI2 extends JFrame implements ActionListener  {
	
	// Initalizes pane components
	private static final long serialVersionUID = 1;
	
	private JLabel browseLibrary;
	private JLabel displayLabel;
	private JButton searchDB;
	private JButton delete;
	private JTextField searchTF;
	private JComboBox displayCB;
	private JTable table;
	private TableSorter sorter;
	private JScrollPane scrollPane;
	private JButton backToMain;
	private JPanel addSetup;
	
	// Initalizes variables
	
	boolean ALLOW_COLUMN_SELECTION = true;
    boolean ALLOW_ROW_SELECTION = true;	
    boolean DEBUG = true;
    ControllerClass controller;
    DescriptionUI description;
    int selectedRow;
    int selectedCol;
    Dimension windowSize = new Dimension(800, 500);
    int tableWidth = 600;
    Dimension tableSize = new Dimension(tableWidth, 300);
    String[][] strTableData;
    
    DatabaseControl db = new DatabaseControl();
    
    public Component componentSetup() {
		JPanel pane = new JPanel();
		// Absolute container positioning used
		
//		 Lays out frame with GridBagLayout
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Variable declaration
		
		// Used for padding around components
		Insets insets;
		// Items in the combo box
		String[] mediaTypes = {"CDs", "DVDs", "Games", "Books"};	
		
		// Declaration of pane components
		
		// JLABEL: Browse Library
		browseLibrary = new JLabel("Browse Library");
		browseLibrary.setFont(new Font("Helvetica", Font.BOLD, 16));
		//browseLibrary.setHorizontalAlignment(arg0)
		c.gridx = 0;	// Lays out component at grid x coordinate 0
		c.gridy = 0;	// Lays out component at grid y coordinate 0
		c.gridwidth = 3;	// Number of coumns the component is spanning
		c.weightx = 0.0;	// 0.0-1.0 Determines how much additional space is placed between adjacent columns
		c.weighty = 0.0;	// 0.0-1.0 Determines how much additional space is placed between adjacent rows
		c.insets = new Insets(20, 247, 20, 0);	// Top, Left, Bottom, Right Determines padding around component in pixels
		c.anchor = GridBagConstraints.CENTER; // Aligns text absolute position relative to screen
		pane.add(browseLibrary, c);	// Adds the component to the screen using grid bag layout constraints c
		
		// JTEXTFIELD: Search Library
		searchTF = new JTextField("", 15);
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(0, 0, 10, 7); 
		c.anchor = GridBagConstraints.CENTER; 
		pane.add(searchTF, c);
		
		// JBUTTON: Search Library
		searchDB = new JButton("Search Library");
		searchDB.setToolTipText("Finds all entries with given words in given order"); // Displays text when cursor is hovered over component		
		searchDB.setActionCommand("Search Library");
		searchDB.addActionListener(this);
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(0, 0, 10, 100); 		
		c.anchor = GridBagConstraints.LINE_END;
		//insets = new Insets(0, 25, 0, 25);
		//searchDB.setMargin(insets);
		pane.add(searchDB, c);
		
		// JLABEL: Display
		displayLabel = new JLabel("Display");
		displayLabel.setFont(new Font("Helvetica", Font.PLAIN, 14));
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(10, 10, 10, 5); 
		c.anchor = GridBagConstraints.LINE_START; 
		pane.add(displayLabel, c);
		
		// COMBO BOX: Display
        displayCB = new JComboBox(mediaTypes);
        displayCB.setActionCommand("Media Select");
        displayCB.addActionListener(this);
        displayCB.setSelectedIndex(0);	//Sets the default item from mediaTypes to appear in the combo box
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(10, 0, 10, 405); 
        c.anchor = GridBagConstraints.CENTER;
        pane.add(displayCB, c);
		
        // JBUTTON: Delete
		delete = new JButton("Delete");
		delete.setActionCommand("Delete");
		delete.addActionListener(this);
		c.gridx = 2; 
		c.gridy = 2; 
		c.weightx = 0.0; 
		c.weighty = 0.0; 
		c.insets = new Insets(10, 0, 10, 10); 
		c.anchor = GridBagConstraints.LINE_END; 
		delete.setToolTipText("Delete selected rows from your library"); // Displays text when cursor is hovered over component																		
		pane.add(delete, c);
		
		// JTABLE
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 3;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(0, 10, 0, 10);
		c.anchor = GridBagConstraints.CENTER;
		
		
		// Adds the scroll pane to the window
		pane.add(scrollPane, c); 
		
		// JBUTTON: Back to Main
		backToMain = new JButton("Back to Main");
		backToMain.setActionCommand("Back to Main");
		backToMain.addActionListener(this);
		c.gridx = 2; 
		c.gridy = 4; 
		c.gridwidth = 3;	
		c.weightx = 0.0; 					
		c.weighty = 0.0; 							
		c.insets = new Insets(20, 0, 20, 0); 
		c.anchor = GridBagConstraints.CENTER; 
		backToMain.setToolTipText("Close browse window and open Main window");
		//insets = new Insets(0, 25, 0, 25);
		//backToMain.setMargin(insets);
		backToMain.setPreferredSize(new Dimension(160, 75));
		backToMain.setMaximumSize(new Dimension(160, 75));
		pane.add(backToMain, c);

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

	// Purpose: To display GUI
	// PRE: None
	// POST: Sets up GUI
	public void createAndShowGUI() {
		// Create and set up the window.
		controller = new ControllerClass();
		setTitle("Media Works - Browse Library");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
        //BrowseUI2 newContentPane = new BrowseUI2();
        //newContentPane.setOpaque(true); //content panes must be opaque
        //setContentPane(newContentPane);
		//addComponentsToPane(getContentPane());//OLD
		add(componentSetup());
        // Size and display the window
		setSize(windowSize);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
		setResizable(false);
		windowLookAndFeel();
	}

	// Purpose: To set action evemt for back to main button
	// PRE: Valid action event
	// POST: Sets up action event for back to main button
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Back to Main")) {
			controller.mainScreenFrame();
			dispose();
		}
		
		if (e.getActionCommand().equals("Delete")) {
			
			System.out.println("Delete was pressed.");
			DatabaseControl db = new DatabaseControl();
			
			//Checks which rows are selected
			for (int i=0 ; i<table.getRowCount() ; i++) {
				// Gets the true/false value of the check box at the selected row
				Object objectData = sorter.getValueAt(i, 6);
				// Must be converted into a string so that
            	String stringData = objectData.toString();
            	stringData = stringData.toLowerCase();
            
            	boolean delete;
            	if (stringData.equals("false"))
                    delete = false;             
            	else
                    delete = true;
                
            	if(DEBUG) {
                	
                	System.out.println("objectData: " + objectData);
                	System.out.println("stringData: " + stringData);
                	System.out.println("delete: " + delete);
                	//System.out.println("realRowIndex: " + realRowIndex);
                	System.out.println();
            	}
				if(delete) {//Proceed to get the real index of the row
					// Gets the int of the first column of the row that has been selected
					objectData = sorter.getValueAt(i, 0);
					// Must be converted to a string so that
					stringData = objectData.toString();
					// it can be converted to an int
					int realRowIndex = Integer.parseInt(stringData);
					// Because the numbers in the first column are +1 higher than their real index values
					realRowIndex--;
					String[] rowToDelete = new String[5];
					// Puts the contents of the row that is selected into the
					// row to be deleted
					for(int j=0 ; j<5 ; j++)
						rowToDelete[j] = strTableData[realRowIndex][j];
					db.deleteRow(db.CDItems, rowToDelete, "CD");
					updateStrTableData();
					// Refreshes table?
					sorter.fireTableRowsDeleted(0, db.getRowsNeeded(db.CDItems));
					
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// Creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BrowseUI2 ui = new BrowseUI2();
				ui.createAndShowGUI();
			}
		});
	}
}