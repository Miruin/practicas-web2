package helpers;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Prop {
	
	public static String getDatosProp(String llave) {
		
		Properties p = new Properties();
		
		try {
			
			InputStream is = new FileInputStream("src/helpers/SentenciasSQL.properties");
			p.load(is);
			return p.getProperty(llave);
			
		} catch(Exception e) {
			System.out.println("error encontrado en el metodo getDtosProp en la clase Prop");
			e.printStackTrace();
			return null;
		}
		
	}

}
 