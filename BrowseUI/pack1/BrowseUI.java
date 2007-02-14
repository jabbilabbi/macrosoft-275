//SFU CMPT 275
//Browse UI
//Alex Androne

package pack1;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.event.ActionEvent;	//Used for events
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;

public class BrowseUI {
 
    final static boolean RIGHT_TO_LEFT = false;	//GridBag layout manager will lay out components right to left if true and gridx/gridy components are not given
    private static boolean DEBUG = false;		//Used for JTable debug

    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        
        //Lays out frame with GridBagLayout
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
       
        //JBUTTON: Back to Main
        JButton button = new JButton("Back to Main");
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
        String[] columnNames = {"Title", "Artist", "Genre", "Location", "Condition", "Rating", "Date Added"};
        
        Object[][] data = {
        		{"Mezzanine", "Masive Attack", "Electronica", "CD Rack", "New", new Integer(5), "09/2005"},
        		{"Nevermind", "Nirvana", "Rock",  "Car", "Scratched", new Integer(4), "02/2003"},
        		{"Magnetic Fields", "Jean Michel Jarre", "Electronica", "CD Rack", "New", new Integer(5), "08/1999"}
        };
        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(700, 300));	//Sets size of table width,height in pixels
        c.gridx = 0; 	
        c.gridy = 3;       	
        c.gridwidth = 2;   	
        c.weightx = 0.0;	
        c.weighty = 0.0;   	
        c.insets = new Insets(5,10,10,10);

        if (DEBUG) {
        	table.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent e) {
        			printDebugData(table);
        		}
        	});
        }
        JScrollPane scrollPane = new JScrollPane(table);	//Makes a scroll bar available if windows sized smaller than table size 
        pane.add(scrollPane, c);	//Add the scroll pane to this panel.
      
        
    }
    
    //Purpose: To print values from JTable to debug
    //Pre: A JTable
    //Post: Data from table
    private static void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
    
    //Purpose: To display GUI
    //Pre: None
    //Post: Sets up GUI
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Media Works - Browse Library");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}