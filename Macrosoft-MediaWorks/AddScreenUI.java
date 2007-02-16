//Macrosoft - CMPT 275

import java.awt.Container;
import java.awt.Insets;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class AddScreenUI implements ActionListener {

	private JComboBox mediaTypeSelected;

	private JButton add, backToMain;

	private JTextField textField1,textField2, textField3, textField4; 

	private JLabel mediaText, addText, titleText, artistText, ratingText, genreText, desctiptionText,
	chooseMediaText, enterTitleText, enterArtistText, enterGenreText;

	private String artist, title, genre, description;

	private Boolean check_add;
	
	private JFrame frame;

	public void addComponentsToPane(Container pane) {
		// Absolute container positioning used
		pane.setLayout(null);

		// Variable declaration
		String[] media_combo_box = { "Choose here", "CD" };
		// Declaration of pane components

		//Combo boxes
		mediaTypeSelected = new JComboBox(media_combo_box);
		mediaTypeSelected.addActionListener(this);

		//Buttons
		add = new JButton("Add");
		add.addActionListener(this);
		backToMain = new JButton("Back to Main Screen");
		backToMain.addActionListener(this);


		//Text fields
		textField1 = new JTextField(20);
		textField1.addActionListener(this);
		textField2 = new JTextField(20);
		textField2.addActionListener(this);
		textField3 = new JTextField(20);
		textField3.addActionListener(this);
		textField4 = new JTextField(20);
		textField4.addActionListener(this);

		//Labels
		mediaText = new JLabel("Select a media type:");
		mediaText.setFont(new Font("Helvetica", Font.PLAIN, 12));

		addText = new JLabel("Adding media:");
		addText.setFont(new Font("Helvetica", Font.BOLD, 14));

		titleText = new JLabel("Enter a title:");
		titleText.setFont(new Font("Helvetica", Font.PLAIN, 12));

		artistText = new JLabel("Enter the artist:");
		artistText.setFont(new Font("Helvetica", Font.PLAIN, 12));

		ratingText = new JLabel("Choose a rating:");
		ratingText.setFont(new Font("Helvetica", Font.PLAIN, 12));

		genreText = new JLabel("Enter a genre:");
		genreText.setFont(new Font("Helvetica", Font.PLAIN, 12));

		desctiptionText = new JLabel("Enter a Description:");
		desctiptionText.setFont(new Font("Helvetica", Font.PLAIN, 12));

		chooseMediaText = new JLabel("Please choose a media type");
		chooseMediaText.setFont(new Font("Helvetica", Font.PLAIN, 10));

		enterTitleText = new JLabel("Please enter a title");
		enterTitleText.setFont(new Font("Helvetica", Font.PLAIN, 10));

		enterArtistText = new JLabel("Please enter a artist");
		enterArtistText.setFont(new Font("Helvetica", Font.PLAIN, 10));

		enterGenreText = new JLabel("Please enter a genre");
		enterGenreText.setFont(new Font("Helvetica", Font.PLAIN, 10));

		// Add components to the pane
		pane.add(mediaTypeSelected);
		pane.add(backToMain);
		pane.add(add);
		pane.add(textField1);
		pane.add(textField2);
		pane.add(textField3);
		pane.add(textField4);
		pane.add(artistText);
		pane.add(mediaText);
		pane.add(titleText);
		pane.add(genreText);
		pane.add(desctiptionText);
		pane.add(addText);
		pane.add(chooseMediaText);
		pane.add(enterTitleText);
		pane.add(enterArtistText);
		pane.add(enterGenreText);

		// Screen positioning
		Insets insets = pane.getInsets();

		backToMain.setBounds(540 + insets.left, 425 + insets.top, 160, 75);

		add.setBounds(370 + insets.left, 425 + insets.top, 160, 75);

		mediaTypeSelected.setBounds(175 + insets.left, 128 + insets.top, 100, 20);

		addText.setBounds(50 + insets.left, 50 + insets.top, 300, 75);

		mediaText.setBounds(50 + insets.left, 100 + insets.top, 150, 75);
	}

	public void createAndShowGUI() {
		// Create and set up the window.
		frame = new JFrame("Macrosoft Media Works");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane
		addComponentsToPane(frame.getContentPane());

		// Size and display the window
		Insets insets = frame.getInsets();
		frame.setSize(800 + insets.left + insets.right, 600 + insets.top
				+ insets.bottom);
		frame.setVisible(true);
		frame.setResizable(false);

	}

	private void setupNone(){
		
		chooseMediaText.setBounds(276, 128, 150, 20);
		chooseMediaText.setVisible(true);
		
		textField1.setVisible(false);
		textField2.setVisible(false);
		textField3.setVisible(false);
		textField4.setVisible(false);
		titleText.setVisible(false);
		artistText.setVisible(false);
		genreText.setVisible(false);
		desctiptionText.setVisible(false);
		enterTitleText.setVisible(false);
		enterArtistText.setVisible(false);
		enterGenreText.setVisible(false);
	}
	private void setupCD(){
		textField1.setBounds(175, 178, 175, 20);
		textField1.setVisible(true);

		textField2.setBounds(175, 228, 175, 20);
		textField2.setVisible(true);

		textField3.setBounds(175, 278, 175, 20);
		textField3.setVisible(true);

		textField4.setBounds(175, 328, 175, 75);
		textField4.setVisible(true);

		titleText.setBounds(50, 150, 150, 75);
		titleText.setVisible(true);

		artistText.setBounds(50, 200, 150, 75);
		artistText.setVisible(true);

		genreText.setBounds(50, 250, 150, 75);
		genreText.setVisible(true);

		desctiptionText.setBounds(50, 300, 150, 75);
		desctiptionText.setVisible(true);

		chooseMediaText.setBounds(276, 128, 150, 20);
		chooseMediaText.setVisible(false);

		enterTitleText.setBounds(350, 178, 150, 20);
		enterTitleText.setVisible(false);

		enterArtistText.setBounds(350, 228, 150, 20);
		enterArtistText.setVisible(false);

		enterGenreText.setBounds(350, 278, 150, 20);
		enterGenreText.setVisible(false);

	}
	public void checkCD(){
		if(textField1.getText().length() == 0){
			enterTitleText.setVisible(true);
			check_add = false;
		}else{
			title = textField1.getText();
			enterTitleText.setVisible(false);
		}
		if(textField2.getText().length() == 0){
			enterArtistText.setVisible(true);
			check_add = false;
		}else{
			artist = textField2.getText();
			enterArtistText.setVisible(false);
		}
		if(textField3.getText().length() == 0){
			enterGenreText.setVisible(true);
			check_add = false;
		}else{
			genre = textField3.getText();
			enterGenreText.setVisible(false);
		}
		
		description = textField4.getText();

	}
	public void actionPerformed(ActionEvent e) {
		DatabaseControl dc = new DatabaseControl();
		MainScreenUI mainScreenUI = new MainScreenUI();
		
		int selected_index = (int)mediaTypeSelected.getSelectedIndex();

		// If the combo box is used these actions occur
		if(e.toString().contains("comboBox")){
			JComboBox cb = (JComboBox) e.getSource();

			if (cb == this.mediaTypeSelected){

				switch(selected_index){
				case 0:
					setupNone();
					break;
				case 1:
					setupCD();
					break;
				default:
					break;	
				}

			}				

		}
		// if a button is pressed these actions occur
		else if(e.toString().contains("Button")){
			JButton b = (JButton) e.getSource();

			if(b == this.add){
				check_add = true;
				switch(selected_index){
				case 0:
					chooseMediaText.setBounds(276, 128, 150, 20);
					chooseMediaText.setVisible(true);
					break;
				case 1:
					checkCD();
					break;
				default:
					break;	
				}

				if((check_add) && (selected_index !=0)){
					dc.appendMediaDatabase(title, artist, genre, description);
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");				
				}
			}
			else if(b == this.backToMain){
				mainScreenUI.createAndShowGUI();
				frame.setVisible(false);				
			}
			
		}
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AddScreenUI ui = new AddScreenUI();
				ui.createAndShowGUI();
			}
		});

	}

}
