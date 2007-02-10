package Project2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import csimage.util.EasyInput;

public class GenerateUI implements ActionListener {

	public JButton IgnoreOnce;

	public JButton IgnoreAll;

	public JButton AddtoDic;

	public JButton Change;

	public JButton GetFile;
	
	public JButton YesBtn;
	
//	public JButton No;

	public boolean ChangeClick;

	public String corrected;

	public int increment = 0;

	public JPanel display_box = new JPanel();

	public JPanel display_box_left = new JPanel();

	public JPanel display_box_suggestions = new JPanel();

	public JPanel main_panel = new JPanel();
	
//	public JPanel sub_display_box = new JPanel();

	public JFrame frame = new JFrame("Alex and Alan's Spellchecker");
//	public JFrame prompt = new JFrame("Done SPELLCHECKING!!!");

	public JTextField text_box = new JTextField(10);

	public JTextPane suggestions_box = new JTextPane();

	public ArrayList<String> wordFile = new ArrayList<String>();

	public Dictionary dict = new Dictionary();

	public JLabel incorrectWord = new JLabel();

	public JLabel enterHere = new JLabel("Enter correction here:");
	public JLabel spellCheckDone = new JLabel("Spellchecking is done!");

	public ProcessWord pWord = new ProcessWord();

	public ReadWriteFile userFile = new ReadWriteFile();

	public String fname;

	public void createUI() {
		dict.createStandardDictionary("dict.txt");
		dict.createPrivateDictionary("priv_dict.txt");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton IgnoreOnce = new JButton("Ignore Once");
		JButton IgnoreAll = new JButton("Ignore All");
		JButton AddtoDic = new JButton("Add to Dictionary");
		JButton Change = new JButton("Change");
		JButton YesBtn = new JButton("Save?");
		YesBtn.setVisible(false);
		spellCheckDone.setVisible(false);
//		JButton No = new JButton("No");
		JButton GetFile = new JButton("**~Get File~**");
//		prompt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		text_box.setText(null);
		text_box.setBounds(0, 0, 50, 100);

		GenerateUI ui = new GenerateUI();

		ui.IgnoreOnce = IgnoreOnce;
		ui.IgnoreAll = IgnoreAll;
		ui.AddtoDic = AddtoDic;
		ui.Change = Change;
		ui.GetFile = GetFile;
		ui.YesBtn = YesBtn;
		ui.spellCheckDone = spellCheckDone;
		ui.text_box = text_box;
		ui.dict = dict;
		ui.display_box = display_box;
		ui.display_box_left = display_box_left;
		ui.display_box_suggestions = display_box_suggestions;
		ui.incorrectWord = incorrectWord;
		ui.suggestions_box = suggestions_box;
		ui.increment = increment;
		ui.main_panel = main_panel;

//		ui.No = No;
//		ui.sub_display_box = sub_display_box;

		ui.IgnoreOnce.addActionListener(ui);
		ui.IgnoreAll.addActionListener(ui);
		ui.AddtoDic.addActionListener(ui);
		ui.Change.addActionListener(ui);
		ui.GetFile.addActionListener(ui);
		ui.YesBtn.addActionListener(ui);
//		ui.spellCheck

		display_box.setBackground(Color.red);
		display_box.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		display_box_left.setBackground(Color.white);
		display_box_left.setBorder(BorderFactory.createLineBorder(Color.black,
				3));
		display_box_suggestions.setBackground(Color.white);
		display_box_suggestions.setBorder(BorderFactory.createLineBorder(
				Color.black, 3));
		text_box.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		frame.setBackground(Color.white);
//		prompt.setBackground(Color.white);
//		sub_display_box.setBackground(Color.white);
		display_box.setPreferredSize(new Dimension(150, 500));
		display_box_left.setPreferredSize(new Dimension(340, 250));
		display_box_suggestions.setPreferredSize(new Dimension(340, 250));
		main_panel.setPreferredSize(new Dimension(340, 500));
		IgnoreOnce.setPreferredSize(new Dimension(130, 26));
		IgnoreAll.setPreferredSize(new Dimension(130, 26));
//		No.setPreferredSize(new Dimension(50, 26));
		AddtoDic.setPreferredSize(new Dimension(130, 26));
		Change.setPreferredSize(new Dimension(130, 26));
		GetFile.setPreferredSize(new Dimension(130, 26));
		YesBtn.setPreferredSize(new Dimension(130, 26));
		frame.setPreferredSize(new Dimension(500, 500));
//		prompt.setPreferredSize(new Dimension(500,500));
		suggestions_box.setBounds(100, 100, 40, 40);

		display_box.add(Change, SwingConstants.CENTER);
		display_box.add(AddtoDic, SwingConstants.CENTER);
		display_box.add(IgnoreAll, SwingConstants.CENTER);
		display_box.add(IgnoreOnce, SwingConstants.CENTER);
		display_box.add(GetFile, BorderLayout.SOUTH);
		display_box_left.add(YesBtn, SwingConstants.CENTER);
		display_box_left.add(spellCheckDone, SwingConstants.CENTER);
//		sub_display_box.setPreferredSize(new Dimension(500,500));
//		sub_display_box.add(YesBtn);
//		sub_display_box.add(No);
		display_box_left.add(incorrectWord, BorderLayout.NORTH);
		display_box_left.add(suggestions_box, BorderLayout.SOUTH);
		display_box_suggestions.add(enterHere, BorderLayout.CENTER);
		display_box_suggestions.add(text_box, BorderLayout.CENTER);
		main_panel.add(display_box_left, BorderLayout.NORTH);
		main_panel.add(display_box_suggestions, BorderLayout.SOUTH);
//		prompt.add(sub_display_box, BorderLayout.CENTER);
//		prompt.pack();
//		prompt.setVisible(false);
		frame.add(main_panel, BorderLayout.WEST);
		frame.add(display_box, BorderLayout.EAST);
		frame.pack();
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		JButton b = (JButton) e.getSource();
		try {
		if (b == this.IgnoreOnce) {
			text_box.setText(null);
			increment++;
			goToNext();
		}
		if (b == this.IgnoreAll) {
			dict.ignoredWords.add(wordFile.get(increment));
			text_box.setText(null);
			increment++;
			goToNext();
		}
		if (b == this.AddtoDic) {
			dict.addToPrivateDictionary(wordFile.get(increment));
			corrected = text_box.getText();
			text_box.setText(null);
			wordFile.remove(increment);
			wordFile.add(increment, corrected);
			increment++;
			goToNext();
		}
		if (b == this.Change) {
			corrected = text_box.getText();
			text_box.setText(null);
			wordFile.remove(increment);
			wordFile.add(increment, corrected);
			increment++;
			goToNext();
		}
		} catch (IndexOutOfBoundsException f) {
		}
		if (b == this.GetFile) {
			fname = EasyInput.chooseFile();
			wordFile = userFile.Tokenize(fname);
			goToNext();
		}
		if (b == this.YesBtn) {
			userFile.saveFile(fname, wordFile);
			System.exit(0);
		}
//		if (b == this.No) {
//			System.exit(0);
//		}
	}

	public void goToNext() {
		if (!(increment == wordFile.size())) {
			String currentWord = wordFile.get(increment);
			if (!dict.dict_words.contains(currentWord)
					&& !dict.priv_words.contains(currentWord)
					&& !dict.ignoredWords.contains(currentWord)) {
				pWord.addLetter(currentWord, dict.dict_words, dict.priv_words);
				pWord.deleteLetter(currentWord, dict.dict_words,
						dict.priv_words);
				incorrectWord.setText(currentWord
						+ " is not in main dictionary or user dictionary");
				suggestions_box.setText("Suggestions:\n" + pWord.toString());
				pWord.suggestionList.clear();
			} else {
				increment++;
				goToNext();
			}
		} else {
//			prompt.setVisible(true);
			incorrectWord.setText("");
			suggestions_box.setText("");
			dict.savePrivateDictionary();
			YesBtn.setVisible(true);
			spellCheckDone.setVisible(true);
			
		}

	}
	
	public static void main(String[] args) {
		GenerateUI ui = new GenerateUI();
		ui.createUI();
	}
}