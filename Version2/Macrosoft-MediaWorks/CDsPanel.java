import javax.swing.*;

import java.awt.*;
import java.io.Serializable;
public class CDsPanel extends JPanel implements Serializable {

	// Initialize variables
	public JTextField titleField, artistField, genreField;
	public JTextArea descriptionTextArea;

	protected JLabel titleText, artistText, genreText;
	protected JLabel enterTitleText, enterArtistText, enterGenreText, descriptionText;
	protected JScrollPane descriptionPane;
	protected Container title, artist, genre, description;
	public JPanel labels, fields, errors;
	
	private Dimension dim;
	
	public CDsPanel(){}
	
	// Purpose: Constructer to set up and view the panel
	// PRE: Valid Dimension
	// POST: Creates a panel
	public CDsPanel(Dimension PANEL_SIZE){
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

		// Adds labels to panel
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
	
	// Purpose: Put all the text fields in one panel
	// PRE: None
	// POST: Returns a JPanel with text fields in it
	public JPanel fields(){
		// Sets up the panel
		fields = new JPanel();
		fields.setLayout(new BoxLayout(fields, BoxLayout.PAGE_AXIS));
		fields.setAlignmentX(Component.LEFT_ALIGNMENT);
		fields.setAlignmentY(Component.TOP_ALIGNMENT);
		
		// Sets up the text fields
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
		
		// Sets up a text area
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
		

		// Adds the text fields to the panel
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
		enterTitleText = new JLabel();
		enterTitleText.setFont(new Font("Helvetica", Font.PLAIN, 10));
		enterTitleText.setForeground(Color.red);
		
		enterArtistText = new JLabel();
		enterArtistText.setFont(new Font("Helvetica", Font.PLAIN, 10));
		enterArtistText.setForeground(Color.red);
	
		enterGenreText = new JLabel();
		enterGenreText.setFont(new Font("Helvetica", Font.PLAIN, 10));
		enterGenreText.setForeground(Color.red);
		
		// Adds the error labels to the panel
		errors.add(Box.createRigidArea(new Dimension(0,15)));
		errors.add(enterTitleText);
		errors.add(Box.createRigidArea(new Dimension(0,21)));
		errors.add(enterArtistText);
		errors.add(Box.createRigidArea(new Dimension(0,23)));
		errors.add(enterGenreText);
		
		return errors;
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