//Macrosoft - CMPT 275

import java.awt.Color;

import java.awt.Container;

import java.awt.Insets;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

public class MainScreenUI implements ActionListener{
	 JLabel mainText, notImplementedText, chooseText;
	 JButton addMedia, browse, createHTML;
	 JFrame frame;
	 
	public void addComponentsToPane(Container pane) {

		//Absolute container positioning used
		pane.setLayout(null);
		//Declaration of pane components
		addMedia = new JButton("Add New Media");
		addMedia.addActionListener(this);
		
		browse = new JButton("Browse");
		browse.addActionListener(this);
		
		createHTML = new JButton("Create a Web Page");
		createHTML.addActionListener(this);
		
		mainText = new JLabel("Main Screen");

		mainText.setFont(new Font("Helvetica", Font.PLAIN, 28));

		chooseText = new JLabel("Choose one of the following:");
		chooseText.setFont(new Font("Helvetica", Font.PLAIN, 16));
		
		notImplementedText = new JLabel("Not implemented yet.");
		notImplementedText.setFont(new Font("Helvetica", Font.PLAIN, 10));

		//Add components to the pane

		pane.add(mainText);
		pane.add(addMedia);
		pane.add(browse);
		pane.add(createHTML);
		pane.add(chooseText);
		pane.add(notImplementedText);

		//Screen positioning

		Insets insets = pane.getInsets();

        addMedia.setBounds(125 + insets.left, 300 + insets.top,
                150, 75);
        browse.setBounds(325 + insets.left, 300 + insets.top,
                150, 75);
        createHTML.setBounds(525 + insets.left, 300 + insets.top,
                150, 75);
        mainText.setBounds(320 + insets.left, 100 + insets.top,
                200, 150);
        chooseText.setBounds(300 + insets.left, 150 + insets.top,
                250, 150);
        notImplementedText.setBounds(550 + insets.left, 200 + insets.top,
                250, 150);
        notImplementedText.setVisible(false);
	}

	public void createAndShowGUI() {

	        //Create and set up the window.

	        frame= new JFrame("Macrosoft Media Works");

	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //Set up the content pane.

	        addComponentsToPane(frame.getContentPane());

	        //Size and display the window.

	        Insets insets = frame.getInsets();

	        frame.setSize(800 + insets.left + insets.right,

	                      600 + insets.top + insets.bottom);

	        frame.setVisible(true);

	    }
	
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		
		BrowseUI browseUI = new BrowseUI();
		AddScreenUI addScreenUI = new AddScreenUI();
					
		if (b == this.addMedia){
			addScreenUI.createAndShowGUI();
			frame.setVisible(false);
			
		}else if(b == this.browse){
			browseUI.createAndShowGUI();
			frame.setVisible(false);
			
		}else if(b == this.createHTML){
			notImplementedText.setVisible(true);
		}
		
	}

	 public static void main(String[] args) {

		 javax.swing.SwingUtilities.invokeLater(new Runnable() {

	            public void run() {
	            	MainScreenUI ui = new MainScreenUI();
	                ui.createAndShowGUI();

	            }

	        });



		}

}

