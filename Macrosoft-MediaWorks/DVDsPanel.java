import javax.swing.*;

import java.awt.*;
public class DVDsPanel extends JPanel{

	// Initialize variables
	public JTextField titleField, artistField, genreField;
	public JTextArea descriptionTextArea;

	private JLabel titleLabel, artistLabel, genreLabel, ratingLabel;
	protected JLabel enterTitleLabel, enterArtistLabel, enterGenreLabel, descriptionLabel;
	private JScrollPane descriptionPane;
	public JPanel labels, fields, errors, rating, ratingSpacing;
	public JComboBox ratings;
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
		setPreferredSize(PANEL_SIZE);
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		JPanel combine = new JPanel();
		combine.setPreferredSize(PANEL_SIZE);
		combine.setLayout(new BoxLayout(combine, BoxLayout.LINE_AXIS));
		combine.add(Box.createRigidArea(new Dimension(5,0)));
		combine.add(labels());
		combine.add(Box.createRigidArea(new Dimension(5,0)));
		combine.add(fields());
		combine.add(Box.createRigidArea(new Dimension(5,0)));
		combine.add(errors());
		combine.add(Box.createRigidArea(new Dimension(5,0)));
		combine.add(ratings());
		
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
		labels.add(Box.createRigidArea(new Dimension(0,15)));
		labels.add(titleLabel);
		labels.add(Box.createRigidArea(new Dimension(0,17)));
		labels.add(artistLabel);
		labels.add(Box.createRigidArea(new Dimension(0,19)));
		labels.add(genreLabel);
		labels.add(Box.createRigidArea(new Dimension(0,21)));
		labels.add(descriptionLabel);
		labels.add(Box.createRigidArea(new Dimension(0,67)));
		
		return labels;
	}
	
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
		
		artistField = new JTextField(20);
		artistField.setSize(artistField.getMinimumSize());
		artistField.setMinimumSize(fieldSize);
		artistField.setMaximumSize(fieldSize);
		
		genreField = new JTextField(20);
		genreField.setSize(genreField.getMinimumSize());
		genreField.setMinimumSize(fieldSize);
		genreField.setMaximumSize(fieldSize);
		
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
		

		// Adds the Label fields to the panel
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
	
	// Purpose: Put all the error messages in one panel
	// PRE: None
	// POST: Returns a JPanel with error messages in it
	public JPanel errors(){
		// Sets up the panel
		errors = new JPanel();
		errors.setLayout(new BoxLayout(errors, BoxLayout.PAGE_AXIS));
		errors.setAlignmentX(Component.LEFT_ALIGNMENT);
		errors.setAlignmentY(Component.TOP_ALIGNMENT);

		// Sets up the error labels
		enterTitleLabel = new JLabel(" ");
		enterTitleLabel.setMinimumSize(new Dimension(105, 10));
		enterTitleLabel.setPreferredSize(new Dimension(105, 10));
		enterTitleLabel.setMaximumSize(new Dimension(105, 10));
		enterTitleLabel.setFont(new Font("Helvetica", Font.PLAIN, 10));
		enterTitleLabel.setForeground(Color.red);
		
		enterArtistLabel = new JLabel(" ");
		enterArtistLabel.setMinimumSize(new Dimension(105, 10));
		enterArtistLabel.setPreferredSize(new Dimension(105, 10));
		enterArtistLabel.setMaximumSize(new Dimension(105, 10));
		enterArtistLabel.setFont(new Font("Helvetica", Font.PLAIN, 10));
		enterArtistLabel.setForeground(Color.red);
	
		enterGenreLabel = new JLabel(" ");
		enterGenreLabel.setMinimumSize(new Dimension(105, 10));
		enterGenreLabel.setPreferredSize(new Dimension(105, 10));
		enterGenreLabel.setMaximumSize(new Dimension(105, 10));
		enterGenreLabel.setFont(new Font("Helvetica", Font.PLAIN, 10));
		enterGenreLabel.setForeground(Color.red);
		
		// Adds the error labels to the panel
		errors.add(Box.createRigidArea(new Dimension(0,21)));
		errors.add(enterTitleLabel);
		errors.add(Box.createRigidArea(new Dimension(0,25)));
		errors.add(enterArtistLabel);
		errors.add(Box.createRigidArea(new Dimension(0,25)));
		errors.add(enterGenreLabel);
		
		return errors;
	}
	
	// Purpose: Create a combo box with ratings from 1-5
	// PRE: None
	// POST: Returns a JPanel with with the ratings combo box
	public JPanel ratings(){
		// Sets up the panel
		rating = new JPanel();
		rating.setLayout(new BoxLayout(rating, BoxLayout.LINE_AXIS));
		rating.setAlignmentX(Component.LEFT_ALIGNMENT);
		rating.setAlignmentY(Component.TOP_ALIGNMENT);

		ratingLabel = new JLabel("Select a rating:");
		ratingLabel.setFont(new Font("Helvetica", Font.PLAIN, 12));
		dim = ratingLabel.getPreferredSize();
		ratingLabel.setSize(dim);	
		
		// Variable declaration
		String[] ratingValues = { "Select a rating", "1", "2", "3", "4", "5" };
		// declaration of pane components

		// Combo boxes
		ratings = new JComboBox(ratingValues);
		ratings.setMinimumSize(ratings.getPreferredSize());
		ratings.setPreferredSize(ratings.getPreferredSize());
		ratings.setMaximumSize(ratings.getPreferredSize());
	
		rating.add(ratingLabel);
		rating.add(Box.createRigidArea(new Dimension(5,0)));
		rating.add(ratings);
		
		ratingSpacing = new JPanel();
		ratingSpacing.setLayout(new BoxLayout(ratingSpacing, BoxLayout.PAGE_AXIS));
		ratingSpacing.setAlignmentX(Component.LEFT_ALIGNMENT);
		ratingSpacing.setAlignmentY(Component.TOP_ALIGNMENT);
		
		ratingSpacing.add(Box.createRigidArea(new Dimension(0, 15)));
		ratingSpacing.add(rating);
		
		
		return ratingSpacing;
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
		enterTitleLabel.setText(" ");
		enterArtistLabel.setText(" ");
		enterGenreLabel.setText(" ");
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