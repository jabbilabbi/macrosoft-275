// AddScreenUI.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Scott Fuoco

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class DescriptionUI extends JFrame implements ActionListener{

//	 Initalizes pane components
	
	private JLabel detailsLabel;
	private Dimension dim;
	private JButton closeBtn;
	
	//	 Initalizes variables
	final static boolean RIGHT_TO_LEFT = false; // GridBag layout manager will lay out components right to left if true and gridx/gridy components are not given
    private String[] rowData;
	private int selectedRow;
	
	private String typeSelected;

	private final String CDs = "CDs";
	private final String DVDs = "DVDs";
	private final String Books = "Books";
	private final String Games = "Games";
	
	private Dimension PANEL_SIZE;
	private CDsPanel CDsSelected;
	private DVDsPanel DVDsSelected;
	private BooksPanel BooksSelected;
	private GamesPanel GamesSelected;
	
    DescriptionUI() {
    	//Nothing
    }
    
    DescriptionUI(int row) {
    	selectedRow = row;
    	createAndShowGUI();
    }
	
	// Purpose: Add all components of the pane into the correct locations and with correct functions
	// PRE: Valid pane is given as a parameter
	// POST: All neceassray components for the Create Account screen will be
	// added and displayed
	public Component componentSetup() {
		
		JPanel borderedPanel = new JPanel();
		borderedPanel = new JPanel();
		borderedPanel.setBorder(BorderFactory.createEtchedBorder());
		typeSelected = "DVD";
		if(typeSelected == "CD"){
			PANEL_SIZE = new Dimension(300,250);
			CDsSelected = new CDsPanel(PANEL_SIZE);
			borderedPanel.add(CDsSelected, CDs);
		}else if (typeSelected == "DVD"){
			PANEL_SIZE = new Dimension(525,250);
			DVDsSelected = new DVDsPanel(PANEL_SIZE);
			borderedPanel.add(DVDsSelected, DVDs);
		}else if (typeSelected == "Book"){
			PANEL_SIZE = new Dimension(525,250);
			BooksSelected = new BooksPanel(PANEL_SIZE);
			borderedPanel.add(BooksSelected, Books);
		}else if (typeSelected == "Game"){
			PANEL_SIZE = new Dimension(525,250);
			GamesSelected = new GamesPanel(PANEL_SIZE);
			borderedPanel.add(GamesSelected, Games);
		}
		
		detailsLabel = new JLabel("Details:");
		detailsLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
		detailsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		detailsLabel.setAlignmentY(Component.TOP_ALIGNMENT);

		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		pane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		pane.setAlignmentY(Component.TOP_ALIGNMENT);
		pane.add(detailsLabel);
		pane.add(Box.createRigidArea(new Dimension(0,25)));
		pane.add(borderedPanel);
		
		closeBtn = new JButton("Close");
		dim = closeBtn.getPreferredSize();
		closeBtn.setMinimumSize(dim);
		closeBtn.setMaximumSize(dim);
		closeBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		closeBtn.setAlignmentY(Component.TOP_ALIGNMENT);
		closeBtn.setActionCommand("Close");
		closeBtn.addActionListener(this);
		
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.PAGE_AXIS));
		btnPanel.add(pane);
		btnPanel.add(Box.createRigidArea(new Dimension(0,5)));
		btnPanel.add(closeBtn);
		
		JPanel primaryPanel = new JPanel();
		primaryPanel.setLayout(new BoxLayout(primaryPanel, BoxLayout.LINE_AXIS));
		primaryPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		primaryPanel.add(btnPanel);
		return primaryPanel;

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
	
	public void createAndShowGUI() {
		
		DatabaseControl db = new DatabaseControl();
		db.loadAllDatabases();
		rowData = db.getLibraryRow(selectedRow,); // Holds a row of data from the database		
		
		// Create and set up the window
		windowLookAndFeel();
		setTitle("Media Works - Description");
		setResizable(false);
		add(componentSetup());
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	// Purpose: To syncronize the actions of the user with the functionality of the screen
	// PRE: Valid action event as param
	// POST: Button functionality with proper conditions and actions taken
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Close")){
			dispose();
		}

	}

	// Testing method
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DescriptionUI descriptionUI = new DescriptionUI();
				descriptionUI.createAndShowGUI();
			}
		});

	}
}