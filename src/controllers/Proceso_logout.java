package controllers;

import javax.servlet.http.HttpServletRequest;

public class Proceso_logout {

	public static String Procesar(HttpServletRequest req) {
		 System.out.println("\nverificando si hay sesion activa");
		 if (req.getSession(false) != null){
			 System.out.println("hay una sesion activa... desactivando sesion");
			req.getSession(false).invalidate();
			return "{\"message\":\"Se ha desconectado satisfactoriamente\","
					+ "\"redirect\":\"../../index.html\","
					+ "\"status\":200}";
		 } else {
			 System.out.println("no hay sesion activa");
			 return "{\"message\":\"No hay una sesion activa \","
					+ "\"redirect\":\"../../index.html\","
					+ "\"status\":500}";
		 }
	 }
	
}
