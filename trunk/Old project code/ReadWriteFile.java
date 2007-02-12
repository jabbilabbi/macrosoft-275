package Project2;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import csimage.util.*;

public class ReadWriteFile {


		public static ArrayList<String> Tokenize(String filename) {
			ArrayList<String> tokens = new ArrayList<String>();
			try {
				BufferedReader in = new BufferedReader(new FileReader(filename));
				String line = in.readLine();
				do {
					line = line.trim();
					String[] t = line.split(" ");
					for(int i =0; i<t.length; i++)
					{
						tokens.add(t[i]);
					}
					line = in.readLine();
				} while (line != null);
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			return tokens;
		}
		
		public void saveFile (String fname, ArrayList<String> wordFile) {
			PrintWriter output = null;

			try
			{
			FileOutputStream outputStream = new FileOutputStream(fname);

			output = new PrintWriter(outputStream);
			}
			catch (Exception ee)
			{
			}
			output.println(toString(wordFile));
			
			output.close();
		}
		
		public String toString(ArrayList<String> wordFile) {
			String outputString = "";
			for (int i=0; i<wordFile.size(); i++) {
				outputString += (wordFile.get(i) + " ");
			}
			outputString.trim();
			return outputString;
		}
}
