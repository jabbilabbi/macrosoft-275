// BrowseUI2.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alex Androne

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class BrowseUI2 extends JFrame implements ActionListener  {

	// Initalizes pane components
	
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
	
	
	// Initalizes variables
	
	// GridBag layout manager will lay out components right to left if true and gridx/gridy components are not given
	final static boolean RIGHT_TO_LEFT = false; 
	private boolean ALLOW_COLUMN_SELECTION = true;
    private boolean ALLOW_ROW_SELECTION = true;	
    private boolean DEBUG = false;
    private ControllerClass controller;
    private DescriptionUI description;
    private int selectedRow;
    
    
    
	// Purpose: To add and display components
	// PRE: Valid pane is given as a parameter
	// POST: All necessary components for the Create Account screen will be added and displayed
	//IS CONSTRUCTOR...
    public BrowseUI2() {
	
		// Lays out frame with GridBagLayout
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Variable declaration
		
		// Used for padding around components
		Insets insets;
		String[] mediaTypes = {"All", "CDs"};	//Items in the combo box
		/*
		String[] columnNames = { "Name", "Artist", "Genre", "Description" };
		DatabaseControl db = new DatabaseControl();
		db.loadMediaDatabase();
		// Holds table data	
		Object[][] tableData = new Object[db.getRowsNeeded()][4]; 			
		
		// Assigns data from the database to tableData
		for (int i = 0; i < db.getRowsNeeded(); i++) {
			// Holds a row of data from the database	
			String[] rowData = db.getLibraryRow(i); 									
			for (int j = 0; j < 3; j++)
				// Assigns column data from a row to tableData
				tableData[i][j] = rowData[j];
			tableData[i][3] = "Click";
		}
		*/
		// Declaration of pane components
		
// Declaration of pane components
		
		// JLABEL: Browse Library
		browseLibrary = new JLabel("Browse Library");
		browseLibrary.setFont(new Font("Helvetica", Font.BOLD, 16));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(20, 0, 20, 0); 
		c.anchor = GridBagConstraints.CENTER; 
		add(browseLibrary, c);
		
		// JTEXTFIELD: Search Library
		searchTF = new JTextField("", 17);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(0, 0, 10, 0); 
		c.anchor = GridBagConstraints.CENTER; 
		add(searchTF, c);
		
		// JBUTTON: Search Library
		searchDB = new JButton("Search Library");
		searchDB.setToolTipText("Finds all entries with given words in given order"); // Displays text when cursor is hovered over component		
		searchDB.setActionCommand("Search Library");
		searchDB.addActionListener(this);
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(0, 0, 10, 92); 		
		c.anchor = GridBagConstraints.LINE_END;
		insets = new Insets(0, 25, 0, 25);
		searchDB.setMargin(insets);
		add(searchDB, c);
		
		// JLABEL: Display
		displayLabel = new JLabel("Display");
		displayLabel.setFont(new Font("Helvetica", Font.PLAIN, 14));
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(10, 0, 10, 10); 
		//c.anchor = GridBagConstraints.CENTER; 
		add(displayLabel, c);
		
		// COMBO BOX: Display
        displayCB = new JComboBox(mediaTypes);	//Passes mediaTypes to combo box
        displayCB.setSelectedIndex(0);	//Sets the default item from mediaTypes to appear in the combo box
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(10,0,10,0);  //padding
        //c.anchor = GridBagConstraints.LINE_START;
        add(displayCB, c);
		
//		 JBUTTON: Back to Main
		backToMain = new JButton("Back to Main");
		backToMain.setActionCommand("Back to Main");
		backToMain.addActionListener(this);
		
		c.gridx = 0; // Lays out component at grid x coordinate 0
		c.gridy = 2; // Lays out component at grid y coordinate 0
		c.gridwidth = 1;	// Number of coumns the component is spanning
		c.weightx = 0.0; // 0.0-1.0 Determines how much additional space is
							// placed within adjacent columns
		c.weighty = 0.0; // 0.0-1.0 Determines how much additional space is
							// placed within adjacent rows
		c.insets = new Insets(20, 0, 20, 0); // Top,Left,Bottom,Right Determines padding around component in pixels
		c.anchor = GridBagConstraints.CENTER; // Aligns text to the right (LINE_END)
		backToMain.setToolTipText("Close browse window and open Main window"); // Displays text when cursor is hovered over component																		
		add(backToMain, c);
		
		// JTABLE
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(0, 0, 0, 0);
		
		
		sorter = new TableSorter(new MyTableModel());
		table = new JTable(sorter);
		sorter.setTableHeader(table.getTableHeader());  
	    //Set up tool tips for column headers.
	    table.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
	    //Create the scroll pane and add the table to it.
	    scrollPane = new JScrollPane(table);
	    //Set up column sizes.
	    initColumnSizes(table);    
	    
		// DETECTS SELECTIONS FOR EACH CELL
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// True by default
		if (ALLOW_ROW_SELECTION) { 
            ListSelectionModel rowSM = table.getSelectionModel();
            rowSM.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    // Ignore extra messages.
                    if (e.getValueIsAdjusting()) return;

                    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                    if (lsm.isSelectionEmpty()) {
                    	// No rows are selected
                    } else {
                        selectedRow = lsm.getMinSelectionIndex();
                        // Row  selectedRow is now selected
                    }
                }
            });
        } else {
            table.setRowSelectionAllowed(false);
        }
		
        // False by default
        if (ALLOW_COLUMN_SELECTION) { 
        	// Allows individual cell selection
            if (ALLOW_ROW_SELECTION) {
                table.setCellSelectionEnabled(true);
            }
            table.setColumnSelectionAllowed(true);
            ListSelectionModel colSM =
                table.getColumnModel().getSelectionModel();
            colSM.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    // Ignore extra messages.
                    if (e.getValueIsAdjusting()) return;
                    
                    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                    int selectedCol = lsm.getMinSelectionIndex();
                    
                        if (selectedCol == 3) {
                        	description = new DescriptionUI(selectedRow);
                        }
                    }
                
            });
        }
		
        // Makes a scroll bar available if windows sized smaller than table size	
        
		scrollPane = new JScrollPane(table); 				
		scrollPane.setPreferredSize(new Dimension(600, 300));
		// Add the scroll pane to this panel
		add(scrollPane, c); 
		
		// JBUTTON: Delete
		backToMain = new JButton("Delete");
		backToMain.setActionCommand("Delete");
		backToMain.addActionListener(this);
		backToMain.setPreferredSize(new Dimension(160, 75));
		backToMain.setMaximumSize(new Dimension(160, 75));
		c.gridx = 0; // Lays out component at grid x coordinate 0
		c.gridy = 2; // Lays out component at grid y coordinate 0
		c.gridwidth = 1;	// Number of coumns the component is spanning
		c.weightx = 0.0; // 0.0-1.0 Determines how much additional space is
							// placed within adjacent columns
		c.weighty = 0.0; // 0.0-1.0 Determines how much additional space is
							// placed within adjacent rows
		c.insets = new Insets(20, 0, 20, 0); // Top,Left,Bottom,Right Determines padding around component in pixels
		c.anchor = GridBagConstraints.CENTER; // Aligns text to the right (LINE_END)
		backToMain.setToolTipText("Close browse window and open Main window"); // Displays text when cursor is hovered over component																		
		add(backToMain, c);
	//}
	}
    
    /*
     * This method picks good column sizes.
     * If all column heads are wider than the column's cells'
     * contents, then you can just use column.sizeWidthToFit().
     */
    private void initColumnSizes(JTable table) {

        TableColumn column = null;
       
        for (int i = 0; i < 5; i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(0); //sport column is bigger
            } else {
                column.setPreferredWidth(150);
            }
        }
    }
	
	//NEW CLASS
	class MyTableModel extends AbstractTableModel {
        
        private String[] columnNames1 = { " ", "Title", "Artist", "Genre", "Description" };
        
        
		private Object[][] tableData = {
			{"1", "Campione",
			"Snowboarding", new Integer(5), new Boolean(false)},
			{"2", "Huml",
			"Rowing", new Integer(3), new Boolean(true)},
			{"3", "Walrath",
			"Knitting", new Integer(2), new Boolean(false)},
			{"4", "Zakhour",
			"Speed reading", new Integer(20), new Boolean(true)},
			{"5", "Milne",
			"Pool", new Integer(10), new Boolean(false)}
		};

        public int getColumnCount() {
            return columnNames1.length;
        }

        public int getRowCount() {
            return tableData.length;
        }

        public String getColumnName(int col) {
            return columnNames1[col];
        }

        public Object getValueAt(int row, int col) {
            return tableData[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            tableData[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + tableData[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
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

//		Create and set up the content pane.
        BrowseUI2 newContentPane = new BrowseUI2();
        //newContentPane.setOpaque(true); //content panes must be opaque
        //setContentPane(newContentPane);
		
		// Set up the content pane.
		//addComponentsToPane(getContentPane());//OLD
		
        // Size and display the window
		setSize(800, 600);
		setLocationRelativeTo(null);
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

/*
import java.util.ArrayList;
if (e.getSource().equals("Search Library")){
	ArrayList<String> newtable = controller.searchDB(searchTF.getText());
	// Update shown table 
	for (int i = 0; i < newtable.size(); i++) {
		String[] rowData = controller.getLibraryRow(i); // Holds a row of data from the database										
		for (int j = 0; j < 4; j++)
			// Assigns column data from a row to tableData
			tableData[i][j] = rowData[j];
	}
}
		import javax.swing.JTextField;
		private JButton searchDB;
		private JTextField searchTF;

		// JTEXTFIELD: Search Library
		searchTF = new JTextField("", 17);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(10, 0, 10, 0); 
		c.anchor = GridBagConstraints.CENTER; 
		pane.add(searchTF, c);
		
		
		// JBUTTON: Search Library
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
*/