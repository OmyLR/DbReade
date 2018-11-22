package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;

import com.google.gson.Gson;

import Controladores.Controller;
import Utilidades.Conexion;

/**
 * Servlet implementation class DBServlet
 */
public class DBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Ejecutando Servlet!!");
		String opcion = (request.getParameter("opcion")!=null)?request.getParameter("opcion"):"";
		System.out.println(opcion);
		String ruta = "index.jsp";
		if(opcion != null) {
			switch(opcion){
				case "":
					try {
						loadAllDataBases(ruta, request, response);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch(ServletException e) {
						
					}
				break;
				case "dbAjax":
					try {
						loadAllTablesAJAX(request.getParameter("search"), response);
					}catch(SQLException e) {
						e.printStackTrace();
					}catch(IOException e) {
						e.printStackTrace();
					}
					break;
				case "dataAjax":
					try {
						loadDataTablesAJAX(request.getParameter("table"), request.getParameter("database"), response);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void loadAllDataBases(String ruta, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		Controller cont = new Controller("localhost:3306", "information_schema", "root", "");
		ArrayList<String> databases = cont.getAllDataBases();
		request.setAttribute("databases", databases);
		redirect(ruta, response, request);
	}
	
	private void loadAllTablesAJAX(String database, HttpServletResponse response) throws SQLException, IOException {
		Controller cont = new Controller("localhost:3306", "information_schema", "root", "");
		System.out.println("Comenzando la busqueda de tabla!!");
		ArrayList<String> databases = cont.getTablesDB(database);
		// Instruciones Devolución JSON AJAX
		// Convertir a JSON
		String json = new Gson().toJson(databases);
		// Se indica el tipo de dato a devolver
	    response.setContentType("application/json");
	    // Se indica la codificación del texto
	    response.setCharacterEncoding("UTF-8");
	    // Se produce el envío
	    response.getWriter().write(json);
	}
	
	private void loadDataTablesAJAX(String table, String database, HttpServletResponse response) throws SQLException, IOException {
		Controller cont = new Controller("localhost:3306", "information_schema", "root", "");
		System.out.println("Comenzando la busqueda de datos!!");
		ArrayList<ArrayList<String>> data = cont.getDataTable(table, database);
		// Instruciones Devolución JSON AJAX
		// Convertir a JSON
		String json = new Gson().toJson(data);
		// Se indica el tipo de dato a devolver
	    response.setContentType("application/json");
	    // Se indica la codificación del texto
	    response.setCharacterEncoding("UTF-8");
	    // Se produce el envío
	    response.getWriter().write(json);
	}
	
	private void redirect(String ruta, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		request.getRequestDispatcher("jsp/"+ruta).forward(request, response);
	}

}
