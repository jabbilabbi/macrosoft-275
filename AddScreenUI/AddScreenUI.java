//Macrosoft - CMPT 275

import java.awt.Color;
import java.awt.Container;
import java.awt.Insets;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class AddScreenUI implements ActionListener {
	
	JComboBox mediaTypeSelected;
	JButton add;
	JButton backToMain;
	JTextField titleTextField;
	JTextField artistTextField;
	JTextField genreTextField;
	JTextField descriptionTextField;
	JLabel mediaText;
	JLabel addText;
	JLabel mediaTitle;
	JLabel mediaArtist;
	JLabel mediaRating;
	JLabel mediaGenre;
	JLabel mediaDescription;
	
	
	
	public void addComponentsToPane(Container pane) {
		// Absolute container positioning used
		pane.setLayout(null);

		// Declaration of pane components
		mediaTypeSelected = new JComboBox(new Object[] { "CD" });

		add = new JButton("Add");
		add.addActionListener(this);

		backToMain = new JButton("Back to Main Screen");

		titleTextField = new JTextField(20);

		artistTextField = new JTextField(20);

		genreTextField = new JTextField(20);

		descriptionTextField = new JTextField(20);

		mediaText = new JLabel("Choose a media type:");
		mediaText.setFont(new Font("Helvetica", Font.PLAIN, 12));

		addText = new JLabel("Adding media:");
		addText.setFont(new Font("Helvetica", Font.BOLD, 14));

		mediaTitle = new JLabel("Enter a title:");
		mediaTitle.setFont(new Font("Helvetica", Font.PLAIN, 12));

		mediaArtist = new JLabel("Enter the artist:");
		mediaArtist.setFont(new Font("Helvetica", Font.PLAIN, 12));

		mediaRating = new JLabel("Choose a rating:");
		mediaRating.setFont(new Font("Helvetica", Font.PLAIN, 12));

		mediaGenre = new JLabel("Enter a genre:");
		mediaGenre.setFont(new Font("Helvetica", Font.PLAIN, 12));

		mediaDescription = new JLabel("Enter a Description:");
		mediaDescription.setFont(new Font("Helvetica", Font.PLAIN, 12));

		// Add components to the pane
		pane.add(mediaTypeSelected);
		pane.add(backToMain);
		pane.add(add);
		pane.add(titleTextField);
		pane.add(artistTextField);
		pane.add(genreTextField);
		pane.add(descriptionTextField);
		pane.add(mediaArtist);
		pane.add(mediaText);
		pane.add(mediaTitle);
		pane.add(mediaRating);
		pane.add(mediaGenre);
		pane.add(mediaDescription);
		pane.add(addText);

		// Screen positioning
		Insets insets = pane.getInsets();

		backToMain.setBounds(540 + insets.left, 425 + insets.top, 160, 75);

		add.setBounds(370 + insets.left, 425 + insets.top, 160, 75);

		mediaTypeSelected.setBounds(175 + insets.left, 128 + insets.top, 100, 20);

		titleTextField.setBounds(175 + insets.left, 178 + insets.top, 175, 20);

		artistTextField.setBounds(175 + insets.left, 228 + insets.top, 175, 20);

		genreTextField.setBounds(175 + insets.left, 278 + insets.top, 175, 20);

		descriptionTextField.setBounds(175 + insets.left, 328 + insets.top,
				175, 75);

		addText.setBounds(50 + insets.left, 50 + insets.top, 300, 75);

		mediaText.setBounds(50 + insets.left, 100 + insets.top, 150, 75);

		mediaTitle.setBounds(50 + insets.left, 150 + insets.top, 150, 75);

		mediaArtist.setBounds(50 + insets.left, 200 + insets.top, 150, 75);

		mediaGenre.setBounds(50 + insets.left, 250 + insets.top, 150, 75);

		mediaDescription.setBounds(50 + insets.left, 300 + insets.top, 150, 75);
	}

	private void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Macrosoft Media Works");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		addComponentsToPane(frame.getContentPane());

		// Size and display the window.
		Insets insets = frame.getInsets();
		frame.setSize(800 + insets.left + insets.right, 600 + insets.top
				+ insets.bottom);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AddScreenUI ui = new AddScreenUI();
				ui.createAndShowGUI();
			}
		});

	}

	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b == this.add) {
			String fname = "CD.txt";
			String title;
			String artist;
			String genre;
			String description;
			String rating;
			
			if( fname == "CD.txt"){
				title = titleTextField.getText();
				artist = artistTextField.getText();
				genre = genreTextField.getText();
				description = descriptionTextField.getText();
				
				DatabaseControl dc = new DatabaseControl();
				dc.appendMediaDatabase(fname, title, artist, genre, description);			
				
			}
			
		}

	}

}
