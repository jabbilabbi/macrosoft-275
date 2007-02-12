package Project2;

import java.util.ArrayList;
import java.util.HashSet;

public class ProcessWord {

	public static ArrayList<String> suggestionList = new ArrayList<String>();

	public String suggestionString;

	public void deleteLetter(String wrongWord, HashSet<String> dict_words,
			HashSet<String> priv_words) {
		String temp = "";
		for (int i = 0; i < wrongWord.length(); i++) {
			temp = wrongWord.substring(0, i) + wrongWord.substring(i + 1);
			if (suggestionList.contains(temp)) {
				suggestionList.remove(temp);
			}
			if ((dict_words.contains(temp)) || priv_words.contains(temp)) {
				suggestionList.add(temp + "\n");
			}
		}
	}

	public void addLetter(String wrongWord, HashSet<String> dict_words,
			HashSet<String> priv_words) {
		String temp = "";
		char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z' };
		for (int k = 0; k < 25; k++) {
			for (int i = 0; i < wrongWord.length(); i++) {
				temp = wrongWord.substring(0, i) + alphabet[k]
						+ wrongWord.substring(i);
				if (suggestionList.contains(temp)) {
					suggestionList.remove(temp);
				}
				if ((dict_words.contains(temp) || priv_words.contains(temp))) {
					suggestionList.add(temp + "\n");
				}
			}
		}
	}

	public String toString() {
		suggestionString = "";
		for (int i = 0; i < suggestionList.size(); i++) {
			suggestionString += suggestionList.get(i);
		}
		return suggestionString;
	}

	public static void main(String[] args) {
		
	}
}
