// AddScreenUI.java
// SFU CMPT 275 - Software Engineering
// Macrosoft
// Programmed by: Scott Fuoco
public class Security {
	// Initalizes variable
	private static boolean check;
	
	// Purpose: To do basic encryption on a String
	// PRE: Requires a valid String
	// POSTS: Encrypts a String
	public static String encrypt(String text){
		char[] encrypt = new char[text.length()];
		for(int i = 0; i < text.length(); i++){
			check = true;
			encrypt[i] = text.charAt(i);
			if(((encrypt[i] >= 65) &&(encrypt[i] <= 77)) || ((encrypt[i] >= 97) &&(encrypt[i] <= 109))){
				encrypt[i] += 13;
				check = false;
			}
			if((((encrypt[i] >= 78) &&(encrypt[i] <= 90)) || ((encrypt[i] >= 110) &&(encrypt[i] <= 122)))&&(check)){
				encrypt[i] -= 13;
			}
		}
		String encryptedText = new String(encrypt);
		return encryptedText;
	}
	
	// Purpose: To do basic decryption on a String
	// PRE: Requires a valid String
	// POSTS: Decrypts a String
	public static String decrypt(String text){
		char[] decrypt = new char[text.length()];
		for(int i = 0; i < text.length(); i++){
			check = true;
			decrypt[i] = text.charAt(i);
			if(((decrypt[i] >= 65) &&(decrypt[i] <= 77)) || ((decrypt[i] >= 97) &&(decrypt[i] <= 109))){
				decrypt[i] += 13;
				check = false;
			}
			if((((decrypt[i] >= 78) &&(decrypt[i] <= 90)) || ((decrypt[i] >= 110) &&(decrypt[i] <= 122)))&&(check)){
				decrypt[i] -= 13;
			}
		}

		String decryptedText = new String(decrypt);
		return decryptedText;
	}
	public static void main(String[] args) {
		System.out.println(decrypt(encrypt("test")));
	}
}
