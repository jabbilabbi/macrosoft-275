package Project2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import java.io.*;

public class Dictionary {

	public HashSet<String> dict_words = new HashSet<String>();

	public HashSet<String> priv_words = new HashSet<String>();
	
	public HashSet<String> ignoredWords = new HashSet<String>();

	public void createStandardDictionary(String fname) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(fname));
			String line = in.readLine();
			do {
				dict_words.add(line);
				line = in.readLine();
			} while (line != null);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void createPrivateDictionary(String fname) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(fname));
			String line = in.readLine();
			do {
				priv_words.add(line);
				line = in.readLine();
			} while (line != null);
				System.out.print(priv_words.toString());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean isWordinDictionary(String word) {
		if (this.dict_words.contains(word) || this.priv_words.contains(word) || this.ignoredWords.contains(word)) {
			return true;
		} else {
			return false;
		}
	}

	public void addToPrivateDictionary(String word) {
		this.priv_words.add(word);
	}

	public void savePrivateDictionary() {
		try {
			File file = new File("priv_dict.txt");
			FileWriter out = new FileWriter(file);
			String text = privateTextOutput();
			out.write(text);
			out.close();
		} catch (IOException e) {
		}
	}
	
	public String privateTextOutput() {
		String privateWordString = priv_words.toString();
		privateWordString = privateWordString.replaceAll("[^A-Za-z]", " ");
		privateWordString = privateWordString.replaceAll("  ", " ");
		privateWordString = privateWordString.replaceAll("null", "");
		privateWordString = privateWordString.replaceAll(" ", "\n");
		return privateWordString.trim();
	}
}
