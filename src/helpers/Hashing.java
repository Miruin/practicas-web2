package helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
	
	public static String hashPW(String pw) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(pw.getBytes());
			byte[] hash = md.digest();
			StringBuffer sb = new StringBuffer();
			    
			for(byte b : hash) {        
				sb.append(String.format("%02x", b));
			}
			
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
