package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private String host;
	private String bd;
	private String usr;
	private String clave;
	private static Connection conexion;

	public static Connection conectar(String host, String bd, String usr, String clave) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + host +"/"+ bd+"?useSSL=false";
			conexion = DriverManager.getConnection(url, usr, clave);
		} catch (SQLException e) {
			System.out.println("Error sql -> " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Error carga del driver -> " + e.getMessage());
		}

		return conexion;
	}
}
