import javax.swing.*;

import java.awt.*;
import java.io.Serializable;
public class CDsPanel extends JPanel implements Serializable {

	public JTextField titleField, artistField, genreField;
	public JTextArea descriptionTextArea;

	protected JLabel titleText, artistText, genreText;
	protected JLabel enterTitleText, enterArtistText, enterGenreText, descriptionText;
	protected JScrollPane descriptionPane;
	protected Container title, artist, genre, description;
	public JPanel labels, fields, errors;
	
	private Dimension dim;

	public CDsPanel(Dimension PANEL_SIZE){
		setPreferredSize(PANEL_SIZE);
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

		JPanel combine1 = new JPanel();
		combine1.setLayout(new BoxLayout(combine1, BoxLayout.LINE_AXIS));
		labels();
		fields();
		errors();
		combine1.add(labels);
		combine1.add(fields);

		add(combine1);

	}

	public void labels(){
		labels = new JPanel();
		labels.setLayout(new BoxLayout(labels, BoxLayout.PAGE_AXIS));
		
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

		labels.add(titleText);
		labels.add(artistText);
		labels.add(genreText);
		labels.add(descriptionText);
	}
	public void fields(){
		fields = new JPanel();
		fields.setLayout(new BoxLayout(fields, BoxLayout.PAGE_AXIS));

		titleField = new JTextField(20);
		titleField.setSize(titleField.getMinimumSize());
		
		artistField = new JTextField(20);
		artistField.setSize(artistField.getMinimumSize());
		
		genreField = new JTextField(20);
		genreField.setSize(genreField.getMinimumSize());
		
		descriptionTextArea = new JTextArea(5, 20);
		descriptionPane = new JScrollPane(descriptionTextArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setWrapStyleWord(true);
		descriptionPane.setAlignmentY(Component.TOP_ALIGNMENT);

		fields.add(titleField);
		fields.add(Box.createRigidArea(new Dimension(0,0)));
		fields.add(artistField);
		fields.add(genreField);
		fields.add(descriptionPane);

	}
	public void errors(){
		errors = new JPanel();
		errors.setLayout(new BoxLayout(errors, BoxLayout.PAGE_AXIS));

		enterTitleText = new JLabel("Please enter a title");
		enterTitleText.setFont(new Font("Helvetica", Font.PLAIN, 10));
		enterTitleText.setForeground(Color.red);
		enterTitleText.setVisible(false);
		
		enterArtistText = new JLabel("Please enter an artist");
		enterArtistText.setFont(new Font("Helvetica", Font.PLAIN, 10));
		enterArtistText.setForeground(Color.red);
		enterArtistText.setVisible(false);	

		enterGenreText = new JLabel("Please enter a genre");
		enterGenreText.setFont(new Font("Helvetica", Font.PLAIN, 10));
		enterGenreText.setForeground(Color.red);
		enterGenreText.setVisible(false);	

		errors.add(enterTitleText);
		errors.add(enterArtistText);
		errors.add(enterGenreText);
	}
}