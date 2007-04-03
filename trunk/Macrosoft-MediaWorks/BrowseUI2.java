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

//import BrowseUI.MyTableModel;

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
    DescriptionUI description;
    int selectedRow;
    int selectedCol;
    Dimension windowSize = new Dimension(800, 500);
    int tableWidth = 600;
    Dimension tableSize = new Dimension(tableWidth, 300);
    
//  Initalizes variables
	private String artist, title, genre, details;
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
	
	private String[] mediaTypes = {"CDs", "DVDs", "Games", "Books"};
	
	//	 Initializes a ControllerClass instance
	private ControllerClass controller;
    
	public Component componentSetup() {
//		 Lays out frame with GridBagLayout
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Variable declaration
		
		// Used for padding around components
		Insets insets;
		// Items in the combo box
			
		
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
		add(browseLibrary, c);	// Adds the component to the screen using grid bag layout constraints c
		
		// JTEXTFIELD: Search Library
		searchTF = new JTextField("", 15);
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(0, 0, 10, 7); 
		c.anchor = GridBagConstraints.CENTER; 
		add(searchTF, c);
		
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
		add(searchDB, c);
		
		// JLABEL: Display
		displayLabel = new JLabel("Display");
		displayLabel.setFont(new Font("Helvetica", Font.PLAIN, 14));
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(10, 10, 10, 5); 
		c.anchor = GridBagConstraints.LINE_START; 
		add(displayLabel, c);
		
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
        add(displayCB, c);
		
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
		add(delete, c);
		
		// JPANEL
		addSetup = new JPanel();
		addSetup.setLayout(new CardLayout()); 
		addSetup.setBorder(BorderFactory.createEtchedBorder());
		addSetup.setPreferredSize(new Dimension(600, 300));
		addSetup.add(new UnselectedPanel(PANEL_SIZE), UNSELECTED);
		addSetup.add(CDsSelected, CDs);
		addSetup.add(DVDsSelected, DVDs);
		addSetup.add(BooksSelected, Books);
		addSetup.add(GamesSelected, Games);
		add(addSetup);
		
		// JTABLE
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 3;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(0, 10, 0, 10);
		c.anchor = GridBagConstraints.CENTER;
		
		sorter = new TableSorter(new MyTableModel());
		table = new JTable(sorter);
		sorter.setTableHeader(table.getTableHeader());  
	    //Set up tool tips for column headers.
	    table.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
	    //Create the scroll pane and add the table to it.
	    scrollPane = new JScrollPane(table);
	    //Set up column sizes.
	    initColumnSizes(table);    
		// Detects selection of cells in the details column
	    detectDetailsClick(table);
        // Makes a scroll bar available if windows sized smaller than table size	
		scrollPane = new JScrollPane(table); 	
		// Sets the size of the table
		scrollPane.setPreferredSize(tableSize);
		// Adds the scroll pane to the window
		add(scrollPane, c); 
		
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
		add(backToMain, c);
		
		
	}
    
//	 PURPOSE:Changes the main panel of the add screen
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
	
//	 Purpose: To syncronize the actions of the user with the functionality of the screen
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
			if (e.getActionCommand().equals("")) {
				
				
			}

		}
	
    /*
     * This method picks good column sizes.
     * If all column heads are wider than the column's cells'
     * contents, then you can just use column.sizeWidthToFit().
    */
    private void initColumnSizes(JTable table) {
    	
        TableColumn column = null;
      
        for (int i = 0; i < 7; i++) {
            column = table.getColumnModel().getColumn(i);
            if (( i == 0) || ( i == 6) ) {
                column.setPreferredWidth(5); //sport column is bigger
            } else {
                column.setPreferredWidth( (tableWidth-10)/5 );
            }
        }
    }
    
    // Purpose: Initializes table attributes
	// PRE:
	// POST: 
	class MyTableModel extends AbstractTableModel {
        
		private static final long serialVersionUID = 1;
		
        private String[] columnNames1 = { " ", "Title", "Artist", "Genre", "Type", "Description", " " };
        
        private Object[][] tableData = loadTableData();
        
        /*
		private Object[][] tableData = {
			{new Integer(1), "Mezzanine", "Massive Attack", "Electronica", "CD", "Click", new Boolean(false)},
			{new Integer(2), "Gelb", "Neuroticfish", "Electronica", "CD", "Click", new Boolean(false)},
			{new Integer(3), "Nirvana", "Nevermind", "Rock", "CD", "Click", new Boolean(false)}
		};
		*/
        
        public MyTableModel() {
        	// Nothing
        }
      
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
          JTable uses this method to determine the default renderer/
          editor for each cell.  If we didn't implement this method,
          then the last column would contain text ("true"/"false"),
          rather than a check box.
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
            if (col == 6) {
                return true;
            } else {
                return false;
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
        
    	public Object[][] loadTableData() {
    		
    		DatabaseControl db = new DatabaseControl();
    		db.loadMediaDatabase(db.CDItems, "CD");
    		
    		// Holds table data	
    		Object[][] tableData = new Object[db.getRowsNeeded(db.CDItems)][7]; 			
    		
    		// Assigns data from the database to tableData
    		for (int i = 0; i < db.getRowsNeeded(db.CDItems); i++) {
    			tableData[i][0] = new Integer(i+1);
    			// Holds a row of data from the database	
    			String[] rowData = db.getLibraryRow(db.CDItems, i); 
    			tableData[i][1] = rowData[1];
    			tableData[i][2] = rowData[2];
    			tableData[i][3] = rowData[3];
    			tableData[i][4] = rowData[0]; 
    			/*
    			for (int j = 0; j < 4; j++)
    				// Assigns column data from a row to tableData
    				tableData[i][j+1] = rowData[j];
    				*/
    			tableData[i][5] = "Click";
    			tableData[i][6] = new Boolean(false);
    		}
    		
    		return tableData;
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
	
	// Purpose: Detects selection of cells in the details column
	// PRE: The table
	// POST: 
	public void detectDetailsClick(JTable table) {

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
                        // Row selectedRow is now selected
                        
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
                    if (lsm.isSelectionEmpty()) {
                        // No columns are selected
                    } else {
                        selectedCol = lsm.getMinSelectionIndex();
                        // Column selectedColumn is now selected
                        if (selectedCol == 5) {
                        	Object objectData = sorter.getValueAt(selectedRow, 0);
                        	String stringData = objectData.toString();
                        	int realRowIndex = Integer.parseInt(stringData);
                        	realRowIndex--;
                        	//description = new DescriptionUI(realRowIndex);
                        	if(DEBUG) {
	                        	System.out.println("selectedRow: " + selectedRow);
	                        	System.out.println("objectData: " + objectData);
	                        	System.out.println("stringData: " + stringData);
	                        	System.out.println("realRowIndex: " + realRowIndex);
	                        	System.out.println();
                        	}
                        }
                    }
                }
                
            });
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

		// Create and set up the content pane.
        //BrowseUI2 newContentPane = new BrowseUI2();
        //newContentPane.setOpaque(true); //content panes must be opaque
        //setContentPane(newContentPane);
		//addComponentsToPane(getContentPane());//OLD
		
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
	public void actionPerformed1(ActionEvent e) {
		if (e.getActionCommand().equals("Back to Main")) {
			controller.mainScreenFrame();
			dispose();
		}
	}
	
	public void actionPerformed2(ActionEvent e) {
		if (e.getActionCommand().equals("Search")) {
			//controller. ;
			//dispose();
		}
	}
	
	public void actionPerformed3(ActionEvent e) {
		if (e.getActionCommand().equals("Delete")) {
			
			//controller. ;
			//dispose();
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