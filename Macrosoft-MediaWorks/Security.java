import java.security.*;

public class Security{
	public static String hashString(String text){
		byte[] defaultBytes = text.getBytes();
		try{
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();
			
			StringBuffer hexString = new StringBuffer();
			for (int i=0;i<messageDigest.length;i++) {
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}
			text = hexString.toString();
		}catch(NoSuchAlgorithmException nsae){
			
		}
		
		return text;
	}
}