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

import Controladores.Controller;
import Utilidades.Conexion;

/**
 * Servlet implementation class DBServlet
 */
@WebServlet("/")
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
		
		String opcion = (request.getParameter("opcion")!=null)?request.getParameter("opcion"):"";
		System.out.println(opcion);
		String ruta = "index.jsp";
		if(opcion != null) {
			switch(opcion){
				case "":
					try {
						loadDataBases(request.getParameter("selected"), request);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				break;
			}
		}
		request.getRequestDispatcher("jsp/"+ruta).forward(request, response);
		/*try {
			ArrayList<String> dbs 
			String dbSelected = dbs.get(dbs.size()-1);
			System.out.println("###### Base de Datos Seleccionada "+dbSelected+" ######");
			ArrayList<String> tables = cont.getTablesDB(dbSelected);
			System.out.println("###### Listado de las Tablas ######");
			for(int i=0; i<tables.size(); i++) {
				System.out.println("- "+tables.get(i));
			}
			System.out.println("###### Listado de los Datos y Columnas ######");
			cont.getDataTable(tables.get(3), dbSelected);
			ArrayList<String> columnas = cont.getColumns();
			ArrayList<ArrayList<String>> filas = cont.getRows();
			System.out.println("###### Columnas ######");
			for(int i=0; i<columnas.size(); i++) {
				System.out.println("- "+columnas.get(i));
			}
			System.out.println("###### Filas ######");
			for(int i=0; i<filas.size(); i++) {
				System.out.print("- ");
				for(int j=0; j<filas.get(i).size(); j++) {
					System.out.print(filas.get(i).get(j)+" ## ");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void loadDataBases(String database, HttpServletRequest request) throws SQLException {
		Controller cont = new Controller("localhost:3306", "information_schema", "root", "");
		ArrayList<String> databases = cont.getAllDataBases();
		request.setAttribute("databases", databases);
	}

}
