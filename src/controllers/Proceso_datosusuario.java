package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import helpers.Autentificacion;
import helpers.Condb;
import helpers.Prop;

public class Proceso_datosusuario {
	
	public static Connection con = Condb.crearCon();
	public static PreparedStatement ps = null;
    public static ResultSet rs = null;

	public static String Procesar_getDatos(HttpServletRequest req) {
		
		String usuario = null, nombre = null, apellido = null, correo = null, telf = null, pais = null, 
				postal = null, sexo = null;
		
		try {
			System.out.println("\nverificando si hay una sesion activa");
			if (req.getSession(false) != null){
				System.out.println("hay una sesion activa obteniendo datos....");
				HttpSession s = req.getSession(false);
					ps = con.prepareStatement(Prop.getDatosProp("q4"));
					ps.setString(1, (String) s.getAttribute("u"));
					System.out.println("preparando setencia sql:\n"+ps);
					rs = ps.executeQuery();
					System.out.println("obteniendo resultados");
					if(rs.next()){
						usuario = rs.getString(1);
						nombre = rs.getString(2);
						apellido = rs.getString(3);
						correo = rs.getString(4);
						telf = rs.getString(5);
						pais = rs.getString(6);
						postal = rs.getString(7);
						sexo = rs.getString(8);

					} 
					return "{\"usuario\":\""+usuario+"\","
				    + "\"nombre\":\""+nombre+"\","
				    + "\"apellido\":\""+apellido+"\","
				    + "\"correo\":\""+correo+"\","
				    + "\"telefono\":\""+telf+"\","
				    + "\"pais\":\""+pais+"\","
				    + "\"postal\":\""+postal+"\","
				    + "\"sexo\":\""+sexo+"\","
				    + "\"status\":200}";
					
				} else {
					 
					 System.out.println("no hay una sesion activa....");
					 return "{\"message\":\"No hay una sesion activa \","
						+ "\"redirect\":\"../../index.html\","
						+ "\"status\":500}";
				} 

		 } catch (SQLException e) {
				
				System.out.println("se ha encontrado un error en el metodo Procesar_getDatos en la clase Proceo_datos cliente");
				e.printStackTrace();
				return "{\"message\":\"ERROR\","
						+ "\"redirect\":\"#\","	
			        + "\"status\":500}";
				
		}
	}

	public static String Eliminar(HttpServletRequest req) {
		
		
		System.out.println("\nverificando si hay una sesion activa");
		if(req.getSession(false) != null) {
			
			System.out.println("\nhay una sesion activa ");
			
			try {
				System.out.println("preparando datos para eliminar informacion en la tabla usuarios");
				ps = con.prepareStatement(Prop.getDatosProp("q6"));
				ps.setString(1, (String) req.getSession(false).getAttribute("u"));
				System.out.println("sentencia sql: "+ps);
				ps.execute();
				System.out.println("sentencia ejecutada");
				ps.close();
				req.getSession(false).invalidate();
				return "{\"message\":\"El usuario se ha eliminado\","
				+ "\"redirect\":\"../../index.html\","
				+ "\"status\":200}";
				
			} catch (SQLException e) {
				
				System.out.println("error encontrado en el metodo eliminar en la clase Proceso_datoscliente");
				e.printStackTrace();
				return "{\"message\":\"ERROR\","
						+ "\"redirect\":\"#\","	
				+ "\"status\":500}";
				
			}
		} else {
			
			System.out.println("\nno hay una sesion activa");
			return "{\"message\":\"No hay una sesion activa \","
			+ "\"redirect\":\"../../index.html\","
			+ "\"status\":500}";
			
		}
	}
    
	public static String Procesar_setDatos(HttpServletRequest req){
		
		
		System.out.println("\nverificando si hay una sesion activa");
		if (req.getSession(false) != null){
			try {
				
			    System.out.println("hay una sesion activa...");
		        HttpSession s = req.getSession(false);
		        
		        if(s.getAttribute("u").equals(req.getParameter("usuarioActualizado"))) {
		        	
		        	ps = con.prepareStatement(Prop.getDatosProp("q5.1"));
					ps.setString(1, req.getParameter("nombreActualizado"));
					ps.setString(2, req.getParameter("apellidoActualizado"));
					ps.setString(3, req.getParameter("correoActualizado"));
					ps.setString(4, req.getParameter("telfActualizado"));
					ps.setString(5, req.getParameter("pais"));
					ps.setString(6, req.getParameter("postal"));
					ps.setString(7, req.getParameter("sexo"));
					ps.setString(8, (String) s.getAttribute("u"));
					System.out.println("preparando sentencia sql "+ps);
					ps.execute();
					System.out.println("sentencia ejecutada");
					ps.close();
					con.close();
					return "{\"message\":\"se ha actualizado satisfactoriamente\","
					     + "\"redirect\":\"../views/cliente.html\","
						 + "\"status\":200}";
					
		        } else {
		        	
		        	if(Autentificacion.AutRegistro(req.getParameter("usuarioActualizado"), con)) {
		        		
		        		con.close();
						System.out.println("el usuario ya existe");
						return "{\"message\":\"el usuario ya existe\","
								+ "\"redirect\":\"../views/cliente.html\","
								+ " \"status\": 400}";
						
		        	} else {
		        		
		        		ps = con.prepareStatement(Prop.getDatosProp("q5"));
						ps.setString(1, req.getParameter("usuarioActualizado"));
						ps.setString(2, req.getParameter("nombreActualizado"));
						ps.setString(3, req.getParameter("apellidoActualizado"));
						ps.setString(4, req.getParameter("correoActualizado"));
						ps.setString(5, req.getParameter("telfActualizado"));
						ps.setString(6, req.getParameter("pais"));
						ps.setString(7, req.getParameter("postal"));
						ps.setString(8, req.getParameter("sexo"));
						ps.setString(9, (String) s.getAttribute("u"));
						System.out.println("preparando sentencia sql "+ps);
						ps.execute();
						System.out.println("sentencia ejecutada");
						s.setAttribute("u", req.getParameter("usuarioActualizado"));
						ps.close();
						con.close();
						return "{\"message\":\"se ha actualizado satisfactoriamente\","
						     + "\"redirect\":\"../views/cliente.html\","
							 + "\"status\":200}";
		  
		        	}
		        }
			} catch (SQLException e) {
					// TODO Auto-generated catch block
				System.out.println("error encontrado en el metodo Procesar_setDatos del primer catch en la clase Proceso_datoscliente");
				e.printStackTrace();
				return "{\"message\":\"ERROR\","
						 + "\"redirect\":\"#\","	
					+ "\"status\":500}";
			}
		} else {
			 
			 return "{\"message\":\"No hay una sesion activa \","
				+ "\"redirect\":\"../../index.html\","
				+ "\"status\":500}";
		}


	}
}
