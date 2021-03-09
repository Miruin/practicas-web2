package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import helpers.Autentificacion;
import helpers.Condb;

public class Proceso_login {
	public static Connection con = null;
	public static PreparedStatement ps = null;
	
	public static String Procesar(HttpServletRequest req) {
		
		try {
		con = Condb.crearCon();
		System.out.println("\nAutentificando los datos");
		Boolean aut = Autentificacion.AutLogin(req.getParameter("usuario"),req.getParameter("pw"), con);
		con.close();

		if(aut) {
			
			System.out.println("\nel usuario existe... verificando si hay una sesion activa");
			if(req.getSession(false) != null) {
				
				System.out.println("la sesion ya esta activa");
				
				return "{\"message\":\"Su sesion ya esta activa \","
				       + "\"redirect\":\"../views/cliente.html\","
					   + "\"status\":200}";
				
			} else {
				
				System.out.println("iniciando sesion");
				HttpSession sesion = req.getSession();
				sesion.setAttribute("u", req.getParameter("usuario"));
				
				return "{\"message\":\"Inicio de sesion exitoso\","
				   + "\"redirect\":\"../views/cliente.html\","
				   + "\"status\":200}";
	
			}
			
		} else if(aut == false){
			
			System.out.println("el usuario no existe");
			
			return "{\"message\":\"No se ha encontrado el usuario. Porfavor Registrarse primero\","
					+ "\"redirect\":\"#\","
					+ "\"status\":404}";
			
		} else {
			
			
			System.out.println("Se ha encontrado un error en el servidor");
			return "{\"message\":\"Error de Inicio en Sesion\","
					+ "\"status\":500,"
					+ "\"redirect\":\"#\"}";
		}
		
        } catch(SQLException e) {
        	System.out.println("se ha encontrado un error en el "
					+ "metodo Procesar de la clase Proceso_login");
			e.printStackTrace();
			return "{\"message\":\"Error en el servidor\","
			       + "\"status\":500}";
		}
		
	}
}
