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
	private JButton backToMain;
	private JPanel addSetup;
	
	private final String CDs = "CDs";
	private final String DVDs = "DVDs";
	private final String Books = "Books";
	private final String Games = "Games";
	
	// Initalizes variables
	
    boolean DEBUG = true;
    ControllerClass controller;
   
    Dimension windowSize = new Dimension(800, 500);
    int tableWidth = 600;
    Dimension tableSize = new Dimension(tableWidth, 300);
    
    
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
		/*
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
		*/
		// JLABEL: Display
		displayLabel = new JLabel("Display");
		displayLabel.setFont(new Font("Helvetica", Font.PLAIN, 14));
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(10, 10, 10, 5); 
		c.anchor = GridBagConstraints.LINE_START; 
		pane.add(displayLabel, c);
		
		// COMBO BOX: Display
        displayCB = new JComboBox(mediaTypes);
        displayCB.setActionCommand("Media Select");
        displayCB.addActionListener(this);
        c.gridx = 1;
        c.gridy = 1;
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
		c.gridy = 1; 
		c.weightx = 0.0; 
		c.weighty = 0.0; 
		c.insets = new Insets(10, 0, 10, 10); 
		c.anchor = GridBagConstraints.LINE_END; 
		delete.setToolTipText("Delete selected rows from your library"); // Displays text when cursor is hovered over component																		
		pane.add(delete, c);
		
		// JTABLE
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 3;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(0, 10, 0, 10);
		c.anchor = GridBagConstraints.CENTER;
		
		
		// Adds the scroll pane to the window
		addSetup = new JPanel();
		addSetup.setLayout(new CardLayout()); 
		try{
		addSetup.add(new BrowseCDsPanel(), CDs);
		}catch(Exception ea){}
		try{
		addSetup.add(new BrowseDVDsPanel(), DVDs);
		}catch(Exception eb){}
		try{
		addSetup.add(new BrowseBooksPanel(), Books);
		}catch(Exception ec){}
		try{
		addSetup.add(new BrowseGamesPanel(), Games);
		}catch(Exception ed){}
		pane.add(addSetup, c); 
		
		// JBUTTON: Back to Main
		backToMain = new JButton("Back to Main");
		backToMain.setActionCommand("Back to Main");
		backToMain.addActionListener(this);
		c.gridx = 2; 
		c.gridy = 3; 
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
			
			BrowseCDsPanel browseCD = new BrowseCDsPanel();
			browseCD.delete();
			dispose();
			controller.browseFrame();
		}
		
		if (e.getActionCommand().equals("Media Select")) {
			int PanelID = displayCB.getSelectedIndex();
			CardLayout cl = (CardLayout)(addSetup.getLayout());
			switch(PanelID){
			case 0:
				try{
				cl.show(addSetup, CDs);
				}
				catch(Exception e1){
				}
				break;
			case 1:
				try{
				cl.show(addSetup, DVDs);
				}
				catch(Exception e2){
				}
				break;
			case 2:
				try{
					cl.show(addSetup, Games);
					}
					catch(Exception e3){
					}
				break;
			case 3:
				try{
					cl.show(addSetup, Books);
					}
					catch(Exception e4){
					}
				break;
			default:
				break;
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