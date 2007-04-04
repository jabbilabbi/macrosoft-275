// AddScreenUI.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Scott Fuoco

import java.awt.CardLayout;
import java.awt.Color;
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
	
	private JLabel detailsLabel, successLabel;
	private Dimension dim;
	private JButton closeBtn, editBtn;
	private DatabaseControl cdb = new DatabaseControl();
	
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
    
    DescriptionUI(int row, String type) {
    	selectedRow = row;
    	typeSelected = type;
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
		if(typeSelected == "CD"){
			PANEL_SIZE = new Dimension(300,250);
			CDsSelected = new CDsPanel(PANEL_SIZE);
			borderedPanel.add(CDsSelected, CDs);
			CDsSelected.setCD(rowData);
		}else if (typeSelected == "DVD"){
			PANEL_SIZE = new Dimension(525,250);
			DVDsSelected = new DVDsPanel(PANEL_SIZE);
			borderedPanel.add(DVDsSelected, DVDs);
			DVDsSelected.setDVD(rowData);
		}else if (typeSelected == "Book"){
			PANEL_SIZE = new Dimension(525,250);
			BooksSelected = new BooksPanel(PANEL_SIZE);
			borderedPanel.add(BooksSelected, Books);
			BooksSelected.setBook(rowData);
		}else if (typeSelected == "Game"){
			PANEL_SIZE = new Dimension(525,250);
			GamesSelected = new GamesPanel(PANEL_SIZE);
			borderedPanel.add(GamesSelected, Games);
			GamesSelected.setGame(rowData);
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
		
		editBtn = new JButton("Save");
		dim = editBtn.getPreferredSize();
		editBtn.setMinimumSize(dim);
		editBtn.setMaximumSize(dim);
		editBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		editBtn.setAlignmentY(Component.TOP_ALIGNMENT);
		editBtn.setActionCommand("Edit");
		editBtn.addActionListener(this);
		
		
		successLabel = new JLabel(" ");
		successLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		successLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		successLabel.setFont(new Font("Helvetica", Font.PLAIN, 12));
		successLabel.setForeground(Color.red);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.LINE_AXIS));
		btnPanel.add(successLabel);
		btnPanel.add(Box.createRigidArea(new Dimension(5,0)));
		btnPanel.add(editBtn);
		btnPanel.add(Box.createRigidArea(new Dimension(5,0)));
		btnPanel.add(closeBtn);
		
		JPanel btnPanel2 = new JPanel();
		btnPanel2.setLayout(new BoxLayout(btnPanel2, BoxLayout.PAGE_AXIS));
		btnPanel2.add(pane);
		btnPanel2.add(Box.createRigidArea(new Dimension(0,5)));
		btnPanel2.add(btnPanel);
		
		JPanel primaryPanel = new JPanel();
		primaryPanel.setLayout(new BoxLayout(primaryPanel, BoxLayout.LINE_AXIS));
		primaryPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		primaryPanel.add(btnPanel2);
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
		rowData = cdb.getLibraryRow(selectedRow, typeSelected); // Holds a row of data from the database		
		
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

		if(e.getActionCommand().equals("Edit")){
			if(typeSelected == "CD"){
			cdb.editRow(cdb.CDItems, rowData, CDsSelected.returnCD(), CDsSelected.returnCD()[0]);
			}else if (typeSelected == "DVD"){
				cdb.editRow(cdb.DVDItems, rowData, DVDsSelected.returnDVD(), DVDsSelected.returnDVD()[0]);
			}else if (typeSelected == "Book"){
				cdb.editRow(cdb.BookItems, rowData, BooksSelected.returnBook(), BooksSelected.returnBook()[0]);
			}else if (typeSelected == "Game"){
				cdb.editRow(cdb.GameItems, rowData, GamesSelected.returnGame(), GamesSelected.returnGame()[0]);
			}
			successLabel.setText("Edit successful");
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