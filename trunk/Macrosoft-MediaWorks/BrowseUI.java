//SFU CMPT 275
//Browse UI
//Alex Androne

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;//Used for events
//import java.awt.event.MouseEvent;
//import java.awt.event.ActionEvent;	
//import java.awt.event.KeyEvent;

public class BrowseUI implements ActionListener{
	
	JFrame frame;
	
    final static boolean RIGHT_TO_LEFT = false;	//GridBag layout manager will lay out components right to left if true and gridx/gridy components are not given

    JButton button;
    
    //Purpose:
    //Pre: Valid pane is given as a parameter
	//Post: All necessary components for the Create Account screen will be added and displayed
    public void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        
        //Lays out frame with GridBagLayout
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
       
        //JBUTTON: Back to Main
        button = new JButton("Back to Main");
        button.addActionListener(this);
        
        c.gridx = 0;		//Lays out component at grid x coordinate 0
        c.gridy = 0;		//Lays out component at grid y coordinate 0
        c.weightx = 0.0;	//0.0-1.0 Determines how much additional space is placed within adjacent columns 
        c.weighty = 0.0;	//0.0-1.0 Determines how much additional space is placed within adjacent rows 
        c.insets = new Insets(10,10,5,10);	//Top,Left,Bottom,Right Determines padding around component in pixels
        //button.setActionCommand("buttonClick");	//For events...
        button.setToolTipText("Close browse window and open Main window");	//Displays text when cursor is hovered over component
        pane.add(button, c); 
        
        //JLABEL: Display By:
        JLabel label2 = new JLabel("Display by:");
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(5,10,5,10); 
        c.anchor = GridBagConstraints.LINE_END;	//Aligns text to the right (LINE_END)
        label2.setToolTipText("Select category to display media contents by");
        pane.add(label2, c);
        
        //COMBO BOX
        String[] mediaTypes = {"All", "CDs"};	//Items in the combo box
        JComboBox combo = new JComboBox(mediaTypes);	//Passes mediaTypes to combo box
        combo.setSelectedIndex(0);	//Sets the default item from mediaTypes to appear in the combo box
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(5,10,5,10);  //padding
        c.anchor = GridBagConstraints.LINE_START;
        pane.add(combo, c);
        
        //JTABLE
        DatabaseControl db = new DatabaseControl();
     
        String[] columnNames = {"Name", "Artist", "Genre", "Description"};
        Object[][] tableData = new Object[db.getRowsNeeded()][4];	//Holds table data
        /*tableData Example
        Object[][] tableData = {
        		{"Mezzanine", "Masive Attack", "Electronica", "09/2005"},
        		{"Nevermind", "Nirvana", "Rock", "02/2003"},
        		{"Magnetic Fields", "Jean Michel Jarre", "Electronica", "08/1999"}
        };
        */
        //Assigns data from the database to tableData	
        for(int i=0 ; i < db.getRowsNeeded() ; i++) {	
        	String[] rowData = db.getLibraryRow(i);	//Holds a row of data from the database
        	for(int j=0 ; j<4 ; j++)	//Assigns column data from a row to tableData
        		tableData[i][j] = rowData[j];
        }
        
        c.gridx = 0; 	
        c.gridy = 3;       	
        c.gridwidth = 2;   	
        c.weightx = 0.0;	
        c.weighty = 0.0;   	
        c.insets = new Insets(5,10,10,10);
        JTable table = new JTable(tableData, columnNames);
        table.setEnabled(false);
        table.setPreferredScrollableViewportSize(new Dimension(700, 300));	//Sets size of table width,height in pixels
        JScrollPane scrollPane = new JScrollPane(table);	//Makes a scroll bar available if windows sized smaller than table size 
        pane.add(scrollPane, c);	//Add the scroll pane to this panel 
    }
    
    //Purpose: To display GUI
    //Pre: None
    //Post: Sets up GUI
    public void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Media Works - Browse Library");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

		// Size and display the window
		Insets insets = frame.getInsets();
		frame.setSize(800 + insets.left + insets.right, 600 + insets.top
				+ insets.bottom);
		frame.setVisible(true);
		frame.setResizable(false);
    }
    
    //Purpose: To set action evemt for back to main button
    //Pre: Valid action event
    //Post: Sets up action event for back to main button
    public void actionPerformed(ActionEvent e) {
		JButton b = new JButton();
		b = (JButton)e.getSource();
		
		MainScreenUI mainScreenUI = new MainScreenUI();
		
		if (b == button) {
			mainScreenUI.createAndShowGUI();
			frame.setVisible(false);
			
		}
	}

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	BrowseUI ui=new BrowseUI();
                ui.createAndShowGUI();
            }
        });
    }
}