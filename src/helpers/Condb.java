package helpers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Condb {
	
	public static Connection crearCon() {
		
		try {
			
			System.out.println("\npreparando las variables, los datos y el driver necesarios "
					+ "para la conexion a la base de datos");
			Connection con = null;
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					Prop.getDatosProp("urlDB"),
					Prop.getDatosProp("userDB"),
					Prop.getDatosProp("pwDB"));
			System.out.println("conexion realizada\n");
			return con;
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println("error encontrado en Condb");
			e.printStackTrace();
			return null;
		}	
	}

}
