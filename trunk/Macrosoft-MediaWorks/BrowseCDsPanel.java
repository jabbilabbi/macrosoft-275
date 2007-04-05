// BrowseCDsPanel.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alex Androne and Scott Fuoco

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import java.awt.*;
public class BrowseCDsPanel extends JPanel {

	// Initialize variables
	
	// Required by Java
	private static final long serialVersionUID = 1;
	
	protected JPanel labels, fields, errors, rating, ratingSpacing;
	
	private JTable table;
	private TableSorter sorter;
	private JScrollPane scrollPane;
	
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
	
	// Purpose: Constructer to set up and view the panel
	// PRE: Valid Dimension
	// POST: Creates a panel
	public BrowseCDsPanel(){
		//Sets the look and Feel of the panel
		panelLookAndFeel();

		// Sets up the panel
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		sorter = new TableSorter(new MyTableModel());
		table = new JTable(sorter);
		sorter.setTableHeader(table.getTableHeader());  
	    //Set up tool tips for column headers.
	    table.getTableHeader().setToolTipText("Click to sort in ascending order.\r\n Click again to sort in descending order. Click again to display contents in original order");
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
	
		
		add(scrollPane);
	}
	
	// Purpose: Sets up the width of the table columns
	// PRE: The table and the width of the table
	// POST: Sets the width of the columns
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
	// PRE: None
	// POST: Table attributes are initialized
	class MyTableModel extends AbstractTableModel {
        
		// Required by Java
		private static final long serialVersionUID = 1;
		
        private String[] columnNames1 = { " ", "Title", "Artist", "Genre", "Type", "Description", " " };
        
        private Object[][] tableData = loadTableData();
      
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
        
        // Purpose: JTable uses this method to determine the default renderer/editor for each cell
    	// If this method was not implemented, then the last column would contain text ("true"/"false"), rather than a check box
        // PRE: Integer
    	// POST: Respective class is returned
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
        
        // Purpose: To set whether the cells in each column are editable directly by double clicking a cell and typing text 
        // PRE: Row and column index
    	// POST: Sets cells as editable/not editable
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col == 6) {
                return true;
            } else {
                return false;
            }
        }

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
        
        // Purpose: To load the 2D object array required by the table with data from the database
        // PRE: None
    	// POST: Returns 2D object array with entries from the database 
    	public Object[][] loadTableData() {
    		
    		DatabaseControl db = new DatabaseControl();
    		db.loadMediaDatabase(db.CDItems, "CD");
    		int rowsNeeded = db.getRowsNeeded(db.CDItems);
    		
    		// Holds table data	
    		Object[][] tableData = new Object[rowsNeeded][7]; 	
    		
    		// Assigns data from the database to tableData
    		for (int i = 0; i < rowsNeeded; i++) {
    			tableData[i][0] = new Integer(i+1);
    			// Holds a row of data from the database	
    			String[] rowData = db.getLibraryRow(db.CDItems, i); 
    			tableData[i][1] = rowData[1];
    			tableData[i][2] = rowData[2];
    			tableData[i][3] = rowData[3];
    			tableData[i][4] = rowData[0]; 
    			tableData[i][5] = "Click";
    			tableData[i][6] = new Boolean(false);
    		}
    		
    		// Updates a similiar 2D string array with entries from the database. The array is needed for deletion purposes
    		updateStrTableData();
    			
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
	
    // Purpose: To update a 2D string array with items from the database after some entries were deleted
    // PRE: None
	// POST: 2D String array is updated
	public void updateStrTableData() {
		
		DatabaseControl db = new DatabaseControl();
		db.loadMediaDatabase(db.CDItems, "CD");
		int rowsNeeded2 = db.getRowsNeeded(db.CDItems);
		
		strTableData = 	new String[rowsNeeded2][5];
		
		for (int i = 0; i < rowsNeeded2; i++) {
			for (int j = 0; j < 5; j++) {
				String[] rowData = db.getLibraryRow(db.CDItems, i);
				strTableData[i][j] = rowData[j];
			}
		}
	}
	
	// Purpose: Detects selection of cells in the details column
	// PRE: The table
	// POST: Detects a click on the a in the details column
	// To detect a different consecutive selection, another cell must be clicked outside of the details column
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
                        	// Gets the int of the first column of the row that has been selected
                        	// Must use sorter not table
                        	Object objectData = sorter.getValueAt(selectedRow, 0);	
                        	// However, it is in the form of an object so it must first be converted to a string and
                        	String stringData = objectData.toString();	
                        	// then into an int
                        	int realRowIndex = Integer.parseInt(stringData);
                        	// Because the numbers in columns are +1 of their real index values
                        	realRowIndex--;	
                        	description = new DescriptionUI(realRowIndex, "CD");
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
	
    // Purpose: To delete one or more media entries selected in the table
    // PRE: None
	// POST: Selected media entries are deleted
	public void delete() {
		System.out.println("Delete was pressed.");
		DatabaseControl db = new DatabaseControl();
		
		// Checks which rows are selected
		for (int i=0 ; i<table.getRowCount() ; i++) {
			// Gets the true/false value of the check box at the selected row
			Object objectData = sorter.getValueAt(i, 6);
			// Must be converted into a string so that
        	String stringData = objectData.toString();
        	stringData = stringData.toLowerCase();
        	// it can be converted to a boolean value
        	boolean delete;
        	if (stringData.equals("false"))
                delete = false;             
        	else
                delete = true;
            
        	if(DEBUG) {
            	System.out.println("objectData: " + objectData);
            	System.out.println("stringData: " + stringData);
            	System.out.println("delete: " + delete);
            	System.out.println();
        	}
        	// If the row i selection box is selected(true) then
			if(delete) {//Proceed to get the real index of the row to be deleted
				// Gets the int of the first column of the row that has been selected
				objectData = sorter.getValueAt(i, 0);
				// Must be converted to a string so that
				stringData = objectData.toString();
				// it can be converted to an int
				int realRowIndex = Integer.parseInt(stringData);
				// Because the numbers in the first column are +1 higher than their real index values
				realRowIndex--;
				String[] rowToDelete = new String[5];
				// Puts the contents of the row that is selected into the row to be deleted
				for(int j=0 ; j<5 ; j++)
					rowToDelete[j] = strTableData[realRowIndex][j];
				db.deleteRow(db.CDItems, rowToDelete, "CD");
				// Updates the 2D string array that was passed to the database deletion method with values excluding thos that were deleted
				updateStrTableData();
			}
		}
	}
	
	
	// Purpose: Set the look and feel of the panel
	// PRE: None
	// POST: Sets the panels look and feel to the system look and feel
	public static void panelLookAndFeel(){
	    try{
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    }catch (Exception e) {
	        System.out.println("Look and Feel error: " + e);
	    }
	}
	
}