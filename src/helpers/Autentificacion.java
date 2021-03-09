package helpers;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Autentificacion {
	
	public static PreparedStatement ps = null;
	
	
	public static Boolean AutRegistro(String usuario, Connection con) {
		
		try {
			
			System.out.println("\nestableciendo conexion con la base de datos");
			ps = con.prepareStatement(Prop.getDatosProp("q1"));
			ps.setString(1, usuario);
			System.out.println("preparando sentencia sql "+ps);
			ResultSet rs = ps.executeQuery();
			System.out.println("sentencia ejecutada");
			
			if(rs.next()) {
				
				System.out.println("usuario encontrado en la base de datos\n");
				ps.close();
				return true;
				
			} else {
				
				System.out.println("no se ha encontrado el usuario en la base de datos\n");
				ps.close();
				return false;
		    }
			
		} catch(SQLException e) {
			System.out.println("Error encontrado en el metodo AutRegistro de la clase Autentificacion\n");
			e.printStackTrace();
			return null;
		}
	}
	public static Boolean AutLogin(String usuario, String pw, Connection con) {
		
		try {
			
			String hpw = Hashing.hashPW(pw);
			
		    
			ps = con.prepareStatement(Prop.getDatosProp("q3"));
			ps.setString(1, usuario);
			ps.setString(2, hpw);
			System.out.println("preparando sentencia sql "+ps);
			ResultSet rs = ps.executeQuery();
			System.out.println("sentencia ejecutada");
			
			if(rs.next()) {
				
				System.out.println("usuario encontrado en la base de datos\n");
				ps.close();
				return true;
				
			} else {
				
				System.out.println("no se ha encontro el usuario en la base de datos\n");
				ps.close();
				return false;
		    }
			
		} catch (SQLException e) {
			
			System.out.println("error encontrado en el metodo de AutLogin de la clase autentificacion\n");
			e.printStackTrace();
			return null;
		}
	}

}
