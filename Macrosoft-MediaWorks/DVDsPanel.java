import javax.swing.*;

import java.awt.*;
public class DVDsPanel extends JPanel{

	// Initialize variables
	public JTextField titleField, artistField, genreField;
	public JTextArea descriptionTextArea;

	private JLabel titleLabel, artistLabel, genreLabel, starsLabel, ratingsLabel;
	protected JLabel enterTitleLabel, enterArtistLabel, enterGenreLabel, descriptionLabel;
	private JScrollPane descriptionPane;
	public JPanel labels, fields, labels2, fields2;
	public JComboBox ratings, stars;
	protected Boolean checkAdd;
	private Dimension dim;
	
	public DVDsPanel(){}
	
	// Purpose: Constructer to set up and view the panel
	// PRE: Valid Dimension
	// POST: Creates a panel
	public DVDsPanel(Dimension PANEL_SIZE){
		//Sets the look and Feel of the panel
		panelLookAndFeel();
		
		// Sets up the panel
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		JPanel leftPanelCombine = new JPanel();
		leftPanelCombine.setAlignmentX(Component.LEFT_ALIGNMENT);
		leftPanelCombine.setAlignmentY(Component.TOP_ALIGNMENT);
		leftPanelCombine.setLayout(new BoxLayout(leftPanelCombine, BoxLayout.LINE_AXIS));
		leftPanelCombine.add(Box.createRigidArea(new Dimension(5,0)));
		leftPanelCombine.add(labels());
		leftPanelCombine.add(Box.createRigidArea(new Dimension(5,0)));
		leftPanelCombine.add(fields());
		leftPanelCombine.add(Box.createRigidArea(new Dimension(5,0)));
		
		JPanel leftPanel = new JPanel();
		leftPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		leftPanel.add(Box.createRigidArea(new Dimension(0,15)));
		leftPanel.add(leftPanelCombine);
	
		JPanel rightPanelCombine = new JPanel();
		rightPanelCombine.setAlignmentX(Component.LEFT_ALIGNMENT);
		rightPanelCombine.setAlignmentY(Component.TOP_ALIGNMENT);
		rightPanelCombine.setLayout(new BoxLayout(rightPanelCombine, BoxLayout.LINE_AXIS));
		rightPanelCombine.add(Box.createRigidArea(new Dimension(5,0)));
		rightPanelCombine.add(labels2());
		rightPanelCombine.add(Box.createRigidArea(new Dimension(5,0)));
		rightPanelCombine.add(fields2());
		rightPanelCombine.add(Box.createRigidArea(new Dimension(5,0)));
		
		JPanel rightPanel = new JPanel();
		rightPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
		rightPanel.add(Box.createRigidArea(new Dimension(0,15)));
		rightPanel.add(rightPanelCombine);
	
		
		JPanel combine = new JPanel();
		combine.setPreferredSize(PANEL_SIZE);
		combine.setLayout(new BoxLayout(combine, BoxLayout.LINE_AXIS));
		combine.add(leftPanel);
		combine.add(Box.createRigidArea(new Dimension(5,0)));
		combine.add(rightPanel);

		
		add(combine);
		
	}
	
	// Purpose: Put all the labels in one panel
	// PRE: None
	// POST: Returns a JPanel with labels in it
	public JPanel labels(){
		//Sets up panel
		labels = new JPanel();
		labels.setLayout(new BoxLayout(labels, BoxLayout.PAGE_AXIS));
		labels.setAlignmentX(Component.LEFT_ALIGNMENT);
		labels.setAlignmentY(Component.TOP_ALIGNMENT);
		
		// Sets up the labels
		titleLabel = new JLabel("Enter a title:");
		titleLabel.setFont(new Font("Helvetica", Font.PLAIN, 12));
		dim = titleLabel.getPreferredSize();
		titleLabel.setSize(dim);

		artistLabel = new JLabel("Enter an artist:");
		artistLabel.setFont(new Font("Helvetica", Font.PLAIN, 12));
		dim = artistLabel.getPreferredSize();
		artistLabel.setSize(dim);
		
		genreLabel = new JLabel("Enter a genre:");
		genreLabel.setFont(new Font("Helvetica", Font.PLAIN, 12));
		dim = genreLabel.getPreferredSize();
		genreLabel.setSize(dim);
		
		descriptionLabel = new JLabel("Enter a description:");
		descriptionLabel.setFont(new Font("Helvetica", Font.PLAIN, 12));
		dim = descriptionLabel.getPreferredSize();
		descriptionLabel.setSize(dim);	

		// Adds labels to panel
		labels.add(titleLabel);
		labels.add(Box.createRigidArea(new Dimension(0,27)));
		labels.add(artistLabel);
		labels.add(Box.createRigidArea(new Dimension(0,27)));
		labels.add(genreLabel);
		labels.add(Box.createRigidArea(new Dimension(0,27)));
		labels.add(descriptionLabel);
		labels.add(Box.createRigidArea(new Dimension(0,67)));
		
		return labels;
	}
	
	// Purpose: Put all the Label fields in one panel
	// PRE: None
	// POST: Returns a JPanel with Label fields in it
	// Purpose: Put all the Label fields in one panel
	// PRE: None
	// POST: Returns a JPanel with Label fields in it
	public JPanel fields(){
		// Sets up the panel
		fields = new JPanel();
		fields.setLayout(new BoxLayout(fields, BoxLayout.PAGE_AXIS));
		fields.setAlignmentX(Component.LEFT_ALIGNMENT);
		fields.setAlignmentY(Component.TOP_ALIGNMENT);
		
		// Sets up the Label fields
		Dimension fieldSize = new Dimension(180,20);		
		titleField = new JTextField(20);
		titleField.setMinimumSize(fieldSize);
		titleField.setMaximumSize(fieldSize);
		titleField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		titleField.setAlignmentY(Component.TOP_ALIGNMENT);
		
		artistField = new JTextField(20);
		artistField.setSize(artistField.getMinimumSize());
		artistField.setMinimumSize(fieldSize);
		artistField.setMaximumSize(fieldSize);
		artistField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		artistField.setAlignmentY(Component.TOP_ALIGNMENT);
		
		genreField = new JTextField(20);
		genreField.setSize(genreField.getMinimumSize());
		genreField.setMinimumSize(fieldSize);
		genreField.setMaximumSize(fieldSize);
		genreField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		genreField.setAlignmentY(Component.TOP_ALIGNMENT);
		
		// Sets up a Label area
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
		descriptionPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		descriptionPane.setAlignmentY(Component.TOP_ALIGNMENT);

		// Sets up the error labels
		enterTitleLabel = new JLabel(" ");
		enterTitleLabel.setFont(new Font("Helvetica", Font.PLAIN, 10));
		enterTitleLabel.setForeground(Color.red);
		enterTitleLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		enterTitleLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		enterArtistLabel = new JLabel(" ");
		enterArtistLabel.setFont(new Font("Helvetica", Font.PLAIN, 10));
		enterArtistLabel.setForeground(Color.red);
		enterArtistLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		enterArtistLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		enterGenreLabel = new JLabel(" ");
		enterGenreLabel.setFont(new Font("Helvetica", Font.PLAIN, 10));
		enterGenreLabel.setForeground(Color.red);
		enterGenreLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		enterGenreLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		
		// Adds the Label fields to the panel
		fields.add(titleField);
		fields.add(enterTitleLabel);
		fields.add(Box.createRigidArea(new Dimension(0,10)));
		fields.add(artistField);
		fields.add(enterArtistLabel);
		fields.add(Box.createRigidArea(new Dimension(0,10)));
		fields.add(genreField);
		fields.add(enterGenreLabel);
		fields.add(Box.createRigidArea(new Dimension(0,10)));
		fields.add(descriptionPane);
		fields.add(Box.createRigidArea(new Dimension(0,50)));
		
		return fields;
	}
	

	public JPanel labels2(){
		//Sets up panel
		labels2 = new JPanel();
		labels2.setLayout(new BoxLayout(labels2, BoxLayout.PAGE_AXIS));
		labels2.setAlignmentX(Component.LEFT_ALIGNMENT);
		labels2.setAlignmentY(Component.TOP_ALIGNMENT);
		
		// Sets up the labels
		starsLabel = new JLabel("Number of stars:");
		starsLabel.setFont(new Font("Helvetica", Font.PLAIN, 12));
		dim = starsLabel.getPreferredSize();
		starsLabel.setSize(dim);

		ratingsLabel = new JLabel("Select a rating:");
		ratingsLabel.setFont(new Font("Helvetica", Font.PLAIN, 12));
		dim = ratingsLabel.getPreferredSize();
		ratingsLabel.setSize(dim);

		// Adds labels to panel
		labels2.add(starsLabel);
		labels2.add(Box.createRigidArea(new Dimension(0,27)));
		labels2.add(ratingsLabel);
		
		return labels2;
	}
	
	public JPanel fields2(){
		// Sets up the panel
		fields2 = new JPanel();
		fields2.setLayout(new BoxLayout(fields2, BoxLayout.PAGE_AXIS));
		fields2.setAlignmentX(Component.LEFT_ALIGNMENT);
		fields2.setAlignmentY(Component.TOP_ALIGNMENT);
		
		// Variable declaration
		String[] starsValues = { "Select a # of stars", "1", "2", "3", "4", "5" };
		// declaration of pane components

		// Combo boxes
		stars = new JComboBox(starsValues);
		stars.setAlignmentX(Component.LEFT_ALIGNMENT);
		stars.setAlignmentY(Component.TOP_ALIGNMENT);
		stars.setMinimumSize(stars.getPreferredSize());
		stars.setPreferredSize(stars.getPreferredSize());
		stars.setMaximumSize(stars.getPreferredSize());
		
		// Variable declaration
		String[] ratingValues = { "Select a rating", "G", "PG", "PG-13", "R", "NC-17" };
		// declaration of pane components

		// Combo boxes
		ratings = new JComboBox(ratingValues);
		ratings.setAlignmentX(Component.LEFT_ALIGNMENT);
		ratings.setAlignmentY(Component.TOP_ALIGNMENT);
		ratings.setMinimumSize(stars.getPreferredSize());
		ratings.setPreferredSize(stars.getPreferredSize());
		ratings.setMaximumSize(stars.getPreferredSize());
		

		// Adds the Label fields to the panel
		fields2.add(stars);
		fields2.add(Box.createRigidArea(new Dimension(0,20)));
		fields2.add(ratings);
		
		return fields2;
	}
	
	// PURPOSE: To check if all the CD fields are complete
	// PRE: None
	// POST: Sets the necessary JLabels to visible or not visible to notify the user
	//		 which fields need to be completed
	public Boolean checkDVD() {
		checkAdd = true;
		if (titleField.getText().length() != 0) {
			enterTitleLabel.setText(" ");
		} else {
			enterTitleLabel.setText("Please enter a title");
			checkAdd = false;
		}
		if (artistField.getText().length() != 0) {
			enterArtistLabel.setText(" ");
		} else {
			enterArtistLabel.setText("Please enter an artist");
			checkAdd = false;
		}
		if (genreField.getText().length() != 0) {
			enterGenreLabel.setText(" ");
		} else {
			enterGenreLabel.setText("Please enter a genre");
			checkAdd = false;
		}
		
		return checkAdd;

	}
	
	public void clearDVDs(){
		titleField.setText("");
		artistField.setText("");
		genreField.setText("");
		descriptionTextArea.setText("");
		stars.setSelectedIndex(0);
		ratings.setSelectedIndex(0);
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