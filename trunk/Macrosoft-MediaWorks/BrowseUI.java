// BrowseUI.java
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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseUI extends JFrame implements ActionListener  {

	//JFrame frame;

	final static boolean RIGHT_TO_LEFT = false; // GridBag layout manager will lay out components right to left if true and gridx/gridy components are not given
	private boolean ALLOW_COLUMN_SELECTION = true;
    private boolean ALLOW_ROW_SELECTION = true;		
	
	JButton button;

	// Purpose: To add and display components
	// PRE: Valid pane is given as a parameter
	// POST: All necessary components for the Create Account screen will be
	// added and displayed
	public void addComponentsToPane(Container pane) {
		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		// Lays out frame with GridBagLayout
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// JBUTTON: Back to Main
		button = new JButton("Back to Main");
		button.addActionListener(this);
		c.gridx = 0; // Lays out component at grid x coordinate 0
		c.gridy = 0; // Lays out component at grid y coordinate 0
		c.weightx = 0.0; // 0.0-1.0 Determines how much additional space is
							// placed within adjacent columns
		c.weighty = 0.0; // 0.0-1.0 Determines how much additional space is
							// placed within adjacent rows
		c.insets = new Insets(10, 10, 5, 10); // Top,Left,Bottom,Right Determines padding around component in pixels
		button.setToolTipText("Close browse window and open Main window"); // Displays text when cursor is hovered over component																		
		pane.add(button, c);

		// JLABEL: Display By:
		JLabel label2 = new JLabel("Display by:");
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(5, 10, 5, 10); 
		c.anchor = GridBagConstraints.LINE_END; // Aligns text to the right (LINE_END)
		label2.setToolTipText("Select category to display media contents by");
		//pane.add(label2, c);

		// COMBO BOX
		String[] mediaTypes = { "All", "CDs" }; // Items in the combo box
		JComboBox combo = new JComboBox(mediaTypes); // Passes mediaTypes to combo box													
		combo.setSelectedIndex(0); // Sets the default item from mediaTypes to appear in the combo box
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(5, 10, 5, 10);
		c.anchor = GridBagConstraints.LINE_START;
		//pane.add(combo, c);

		// JTABLE
		//DatabaseControl db = new DatabaseControl();
		String[] columnNames = { "Name", "Artist", "Genre", "Description" };
		//Object[][] tableData = new Object[db.getRowsNeeded()][4]; // Holds table data													
		
		///*
		Object[][] tableData = { 
		 {"Mezzanine", "MasiveAttack", "Electronica", "Click"}, 
		 {"Nevermind", "Nirvana", "Rock", "Click"}, 
		 {"Magnetic Fields", "Jean Michel Jarre", "Electronica", "Click"} };
		//*/
		// Assigns data from the database to tableData
		/*
		for (int i = 0; i < db.getRowsNeeded(); i++) {
			String[] rowData = db.getLibraryRow(i); // Holds a row of data from the database										
			for (int j = 0; j < 4; j++)
				// Assigns column data from a row to tableData
				tableData[i][j] = rowData[j];
		}
		*/
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(5, 10, 10, 10);
		JTable table = new JTable(tableData, columnNames);
		
		table.setEnabled(true);// Allows coloumns/cells to be selected
		table.setPreferredScrollableViewportSize(new Dimension(700, 300)); // Sets size of table width, height in pixels																
		
		//DETECTS SELECTIONS FOR EACH CELL
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
		
		JScrollPane scrollPane = new JScrollPane(table); // Makes a scroll bar available if windows sized smaller than table size													
		pane.add(scrollPane, c); // Add the scroll pane to this panel
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
		Insets insets = getInsets();
		setSize(800 + insets.left + insets.right, 600 + insets.top
				+ insets.bottom);
		setVisible(true);
		setResizable(false);
	}

	// Purpose: To set action evemt for back to main button
	// PRE: Valid action event
	// POST: Sets up action event for back to main button
	public void actionPerformed(ActionEvent e) {
		JButton b = new JButton();
		b = (JButton) e.getSource();

		MainScreenUI mainScreenUI = new MainScreenUI();

		if (b == button) {
			mainScreenUI.createAndShowGUI();
			dispose();
		}
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// Creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BrowseUI ui = new BrowseUI();
				ui.createAndShowGUI();
			}
		});
	}
}