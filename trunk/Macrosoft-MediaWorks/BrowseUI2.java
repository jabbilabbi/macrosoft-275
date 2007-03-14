// DescriptionUI.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Alex Androne

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseUI2 extends JFrame implements ActionListener  {

	//	 Initalizes pane components
	
	private JLabel browseLibrary;
	//private JComboBox combo;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton backToMain;
	
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
		//DatabaseControl db = new DatabaseControl();
		Insets insets;
		//String[] mediaTypes = { "All", "CDs" }; // Items in the combo box
		String[] columnNames = { "Name", "Artist", "Genre", "Description" };
		//Object[][] tableData = new Object[db.getRowsNeeded()][4]; // Holds table data				
		///*	
		Object[][] tableData = { 
			{"Mezzanine", "MasiveAttack", "Electronica", "Click"}, 
			{"Nevermind", "Nirvana", "Rock", "Click"}, 
			{"Mezzanine", "MasiveAttack", "Electronica", "Click"}, 
			{"Nevermind", "Nirvana", "Rock", "Click"}, 
			{"Mezzanine", "MasiveAttack", "Electronica", "Click"}, 
			{"Nevermind", "Nirvana", "Rock", "Click"}, 
			{"Mezzanine", "MasiveAttack", "Electronica", "Click"}, 
			{"Nevermind", "Nirvana", "Rock", "Click"}, 
			{"Mezzanine", "MasiveAttack", "Electronica", "Click"}, 
			{"Nevermind", "Nirvana", "Rock", "Click"}, 
			{"Mezzanine", "MasiveAttack", "Electronica", "Click"}, 
			{"Nevermind", "Nirvana", "Rock", "Click"}, 
			{"Mezzanine", "MasiveAttack", "Electronica", "Click"}, 
			{"Nevermind", "Nirvana", "Rock", "Click"}, 
			{"Mezzanine", "MasiveAttack", "Electronica", "Click"}, 
			{"Nevermind", "Nirvana", "Rock", "Click"},
			{"Nevermind", "Nirvana", "Rock", "Click"},
			{"Nevermind", "Nirvana", "Rock", "Click"},
			
			{"Magnetic Fields", "Jean Michel Jarre", "Electronica", "Click"} 
		 };
		//*/
		/*// Assigns data from the database to tableData
		for (int i = 0; i < db.getRowsNeeded(); i++) {
			String[] rowData = db.getLibraryRow(i); // Holds a row of data from the database										
			for (int j = 0; j < 4; j++)
				// Assigns column data from a row to tableData
				tableData[i][j] = rowData[j];
		}
		*/	
		
		// Declaration of pane components
		
		// Label
		browseLibrary = new JLabel("Select a media type:");
		browseLibrary.setFont(new Font("Helvetica", Font.BOLD, 14));
		
		/*// Combo box
		combo = new JComboBox(mediaTypes); // Passes mediaTypes to combo box				
		combo.setActionCommand("Media Select");
		combo.addActionListener(this);
		*/

		// Tabel
		table = new JTable(tableData, columnNames) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;	// Turns off the ability to edit cells directly
	        }
		};
		scrollPane = new JScrollPane(table); // Makes a scroll bar available if windows sized smaller than table size
		table.setEnabled(true);// Allows coloumns/cells to be selected
		table.setPreferredScrollableViewportSize(new Dimension(700, 304)); // Sets size of table width, height in pixels
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		// Detects selections for each cell
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Only allows one thin to be selected at a time
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
                        MainScreenUI mainScreenUI = new MainScreenUI();
                        if (selectedCol == 3) {
                        	mainScreenUI.createAndShowGUI();
                			dispose();
                        }
                        System.out.println("Column " + selectedCol + " is now selected.");
                    }
                }
            });
        }
        
		// Button
		backToMain = new JButton("Back to Main");
		insets = new Insets(20, 20, 20, 20);
		backToMain.setMargin(insets);
		backToMain.setToolTipText("Close browse window and open Main window"); // Displays text when cursor is hovered over component
		backToMain.setActionCommand("Back to Main");
		backToMain.addActionListener(this);
		
		// Add components to the pane
		pane.add(browseLibrary);
		//pane.add(combo);
		pane.add(scrollPane);
		pane.add(backToMain);
		
		// Screen positioning
		backToMain.setBounds(350, 50, 100, 75);
		scrollPane.setBounds(350, 250, 200, 200);
		backToMain.setBounds(350, 500, 160, 75);
		
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
	
	//Purpose: To set action evemt for back to main button
	// PRE: Valid action event
	// POST: Sets up action event for back to main button
	public void actionPerformed(ActionEvent e) {
		JButton b = new JButton();
		b = (JButton) e.getSource();

		MainScreenUI mainScreenUI = new MainScreenUI();

		if (b == backToMain) {
			mainScreenUI.createAndShowGUI();
			dispose();
		}
	}
	
	public void createAndShowGUI() {
		controller = new ControllerClass(); // Creates an
											// instance of the
											// UI controller
		// Create and set up the window
		windowLookAndFeel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Media Works - Browse Library");
		setResizable(false);
		JPanel pane = new JPanel();
		add(addComponentsToPane(pane));
		pack();
		setSize(700,500);
		setVisible(true);
	}
	
	// Testing method
	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// Creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BrowseUI2 browseUI2 = new BrowseUI2();
				browseUI2.createAndShowGUI();
			}
		});

	}
	
}