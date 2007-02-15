//Macrosoft - CMPT 275



import java.awt.Color;

import java.awt.Container;

import java.awt.Insets;



import javax.swing.*;



import java.awt.*;

import java.awt.event.*;



public class MainScreenUI{

	

	public static void addComponentsToPane(Container pane) {

		//Absolute container positioning used

		pane.setLayout(null);

		

		//Declaration of pane components

		JButton addMedia = new JButton("Add New Media");



		JButton browse = new JButton("Browse");

		

		JButton createHTML = new JButton("Create a Web Page");



		JLabel mainText = new JLabel("Main Screen");

		mainText.setFont(new Font("Helvetica", Font.PLAIN, 28));

		

		JLabel chooseText = new JLabel("Choose one of the following:");

		chooseText.setFont(new Font("Helvetica", Font.PLAIN, 16));

		

		//Add components to the pane

		pane.add(mainText);

		pane.add(addMedia);

		pane.add(browse);

		pane.add(createHTML);

		pane.add(chooseText);

		

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

	}

	 private static void createAndShowGUI() {

	        //Create and set up the window.

	        JFrame frame = new JFrame("Macrosoft Media Works");

	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



	        //Set up the content pane.

	        addComponentsToPane(frame.getContentPane());



	        //Size and display the window.

	        Insets insets = frame.getInsets();

	        frame.setSize(800 + insets.left + insets.right,

	                      600 + insets.top + insets.bottom);

	        frame.setVisible(true);

	    }

	 public static void main(String[] args) {

		 javax.swing.SwingUtilities.invokeLater(new Runnable() {

	            public void run() {

	                createAndShowGUI();

	            }

	        });



		}

}

