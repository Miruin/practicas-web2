package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import helpers.Autentificacion;
import helpers.Condb;
import helpers.Hashing;
import helpers.Prop;

public class Proceso_registro {
	
	public static Connection con = null;
	public static PreparedStatement ps = null;

	public static String Procesar(String usuario, String nombre, String apellido, 
			String pw, String telf, String correo) {
		
		
		try {
			
			con = Condb.crearCon();
			
			String hpw = Hashing.hashPW(pw);
			
			System.out.println("\nverificando si ya existe el usuario");
			if(Autentificacion.AutRegistro(usuario, con)) {
				
				con.close();
				System.out.println("el usuario ya existe");
				return "{\"message\":\"el usuario ya existe\", \"status\": 400}";
				
			} else {
				
				System.out.println("el usuario ingresado no exite preparando conexion "
						+ "a la base de datos para agregar el nuevo usuario");
				ps = con.prepareStatement(Prop.getDatosProp("q2"));
				ps.setString(1, usuario);
				ps.setString(2, nombre);
				ps.setString(3, apellido);
				ps.setString(4, hpw);
				ps.setString(5, correo);
				ps.setString(6, telf);
				System.out.println("preparando sentencia sql "+ps);
				ps.execute();
				System.out.println("sentencia ejecutada");
				ps.close();
				con.close();
				return "{\"message\":\"Usuario creado satisfactoriamente\", "
						+ "\"status\": 200}";
		      }
		} catch(SQLException e) {
			System.out.println("se ha encontrado un error en el metodo Procesar "
					+ "de la clase Proceso_registro");
			e.printStackTrace(); 
			return "{\"message\":\"ERROR\","
					+ "\"status\": 500}";
			
		}
	}

}
