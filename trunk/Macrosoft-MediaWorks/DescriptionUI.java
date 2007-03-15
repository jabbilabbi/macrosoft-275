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
	
	private JLabel detailsLabel, titleText, artistText, genreText, descriptionText;
	private Dimension dim;
	private JButton closeBtn;
	private JTextField titleField, artistField, genreField;
	private JTextArea descriptionTextArea;
	private JScrollPane descriptionPane;
	//	 Initalizes variables
	final static boolean RIGHT_TO_LEFT = false; // GridBag layout manager will lay out components right to left if true and gridx/gridy components are not given
    private String[] rowData;
	private int selectedRow;

    DescriptionUI() {
    	//Nothing
    }
    
    DescriptionUI(int row) {
    	selectedRow = row;
    	createAndShowGUI();
    }
	
	private ControllerClass controller;
	
	// Purpose: Add all components of the pane into the correct locations and with correct functions
	// PRE: Valid pane is given as a parameter
	// POST: All neceassray components for the Create Account screen will be
	// added and displayed
	public Component componentSetup() {
		detailsLabel = new JLabel("Details:");
		detailsLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
		detailsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		detailsLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		JPanel borderedPanel = new JPanel();
		borderedPanel.setBorder(BorderFactory.createEtchedBorder());
		borderedPanel.setLayout(new BoxLayout(borderedPanel, BoxLayout.LINE_AXIS));
		borderedPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		borderedPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		borderedPanel.add(Box.createRigidArea(new Dimension(10,0)));
		borderedPanel.add(labels());
		borderedPanel.add(Box.createRigidArea(new Dimension(5,0)));
		borderedPanel.add(fields());
		borderedPanel.add(Box.createRigidArea(new Dimension(10,0)));
		
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		pane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		pane.setAlignmentY(Component.TOP_ALIGNMENT);
		pane.add(detailsLabel);
		pane.add(Box.createRigidArea(new Dimension(0,25)));
		pane.add(borderedPanel);
		
		closeBtn = new JButton("close");
		dim = closeBtn.getPreferredSize();
		closeBtn.setMinimumSize(dim);
		closeBtn.setMaximumSize(dim);
		closeBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		closeBtn.setAlignmentY(Component.TOP_ALIGNMENT);
		closeBtn.setActionCommand("close");
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
		controller = new ControllerClass(); // Creates an
											// instance of the
											// UI controller
		
		DatabaseControl db = new DatabaseControl();
		db.loadMediaDatabase();
		rowData = db.getLibraryRow(selectedRow); // Holds a row of data from the database		
		
		// Create and set up the window
		windowLookAndFeel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		if(e.getActionCommand().equals("close")){
			dispose();
		}

		}
	public JPanel labels(){
		JPanel labels = new JPanel();
		labels.setLayout(new BoxLayout(labels, BoxLayout.PAGE_AXIS));
		labels.setAlignmentX(Component.LEFT_ALIGNMENT);
		labels.setAlignmentY(Component.TOP_ALIGNMENT);
		
		
		titleText = new JLabel("Enter a title:");
		titleText.setFont(new Font("Helvetica", Font.PLAIN, 12));
		dim = titleText.getPreferredSize();
		titleText.setSize(dim);

		artistText = new JLabel("Enter an artist:");
		artistText.setFont(new Font("Helvetica", Font.PLAIN, 12));
		dim = artistText.getPreferredSize();
		artistText.setSize(dim);
		
		genreText = new JLabel("Enter a genre:");
		genreText.setFont(new Font("Helvetica", Font.PLAIN, 12));
		dim = genreText.getPreferredSize();
		genreText.setSize(dim);
		
		descriptionText = new JLabel("Enter a description:");
		descriptionText.setFont(new Font("Helvetica", Font.PLAIN, 12));
		dim = descriptionText.getPreferredSize();
		descriptionText.setSize(dim);	

		labels.add(Box.createRigidArea(new Dimension(0,15)));
		labels.add(titleText);
		labels.add(Box.createRigidArea(new Dimension(0,17)));
		labels.add(artistText);
		labels.add(Box.createRigidArea(new Dimension(0,19)));
		labels.add(genreText);
		labels.add(Box.createRigidArea(new Dimension(0,21)));
		labels.add(descriptionText);
		labels.add(Box.createRigidArea(new Dimension(0,50))); 
		
		return labels;
	}
	public JPanel fields(){
		JPanel fields = new JPanel();
		fields.setLayout(new BoxLayout(fields, BoxLayout.PAGE_AXIS));
		fields.setAlignmentX(Component.LEFT_ALIGNMENT);
		fields.setAlignmentY(Component.TOP_ALIGNMENT);
		
		Dimension fieldSize = new Dimension(180,20);		
		titleField = new JTextField(20);
		titleField.setMinimumSize(fieldSize);
		titleField.setMaximumSize(fieldSize);
		titleField.setEditable(false);
		titleField.setText(rowData[0]);
		titleField.setBackground(Color.white);
		
		
		artistField = new JTextField(20);
		artistField.setSize(artistField.getMinimumSize());
		artistField.setMinimumSize(fieldSize);
		artistField.setMaximumSize(fieldSize);
		artistField.setEditable(false);
		artistField.setText(rowData[1]);
		artistField.setBackground(Color.white);
		
		
		genreField = new JTextField(20);
		genreField.setSize(genreField.getMinimumSize());
		genreField.setMinimumSize(fieldSize);
		genreField.setMaximumSize(fieldSize);
		genreField.setEditable(false);
		genreField.setText(rowData[2]);
		genreField.setBackground(Color.white);
		
		descriptionTextArea = new JTextArea(5, 20);
		descriptionPane = new JScrollPane(descriptionTextArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setWrapStyleWord(true);
		descriptionPane.setAlignmentY(Component.TOP_ALIGNMENT);
		
		Dimension areaSize = new Dimension(180,80);
		descriptionPane.setMinimumSize(areaSize);
		descriptionPane.setMaximumSize(areaSize);
		descriptionTextArea.setText(rowData[3]);
		descriptionTextArea.setEditable(false);

		fields.add(Box.createRigidArea(new Dimension(0,15)));
		fields.add(titleField);
		fields.add(Box.createRigidArea(new Dimension(0,15)));
		fields.add(artistField);
		fields.add(Box.createRigidArea(new Dimension(0,15)));
		fields.add(genreField);
		fields.add(Box.createRigidArea(new Dimension(0,15)));
		fields.add(descriptionPane);
		fields.add(Box.createRigidArea(new Dimension(0,50)));
		
		return fields;
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