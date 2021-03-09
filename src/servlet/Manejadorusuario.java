package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.Proceso_datosusuario;
import controllers.Proceso_logout;

/**
 * Servlet implementation class Manejadorusuario
 */
@MultipartConfig()
@WebServlet("/Manejadorusuario")
public class Manejadorusuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manejadorusuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		System.out.println("\n------------------------------------------------------------------------"
				         + "\niniciando el proceso para logout");
		out.println(Proceso_logout.Procesar(request));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		System.out.println("\n------------------------------------------------------------------------"
				         + "\niniciando el proceso para obtener datos de usuario");
	
		out.println(Proceso_datosusuario.Procesar_getDatos(request));
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();	
		
		System.out.println("\n------------------------------------------------------------------------"
				         + "\niniciando proceso para actualizar datos del usuario"
				  
				  +"\n "+request.getParameter("usuarioA")+"");
		out.println(Proceso_datosusuario.Procesar_setDatos(request));
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();	
		
		System.out.println("\n------------------------------------------------------------------------"
				         + "\niniciando proceso para eliminar al usuario");
		out.println(Proceso_datosusuario.Eliminar(request));
	}

}
