package Controladores;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import Utilidades.Conexion;

public class Controller {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private ResultSetMetaData rsm;
	private ArrayList<String> columns;
	private ArrayList<ArrayList<String>> rows;
	
	public Controller( String host, String db, String user, String pass) {
		this.conn = Conexion.conectar(host, db, user, pass);
	}
	
	public ArrayList<String> getAllDataBases() throws SQLException {
		String sql = "Select * from SCHEMATA";
		stmt = conn.createStatement();
		rs = execQuery(sql);
		ArrayList<String> dbs = new ArrayList<String>();
		while(rs.next()) {
			dbs.add(rs.getString("SCHEMA_NAME"));
		}
		cleanResultSet();
		return dbs;
	}
	
	public ArrayList<String> getTablesDB(String db) throws SQLException {
		String sql = "Select * from TABLES where TABLE_SCHEMA='"+db+"'";
		stmt = conn.createStatement();
		rs = execQuery(sql);
		ArrayList<String> tables = new ArrayList<String>();
		while(rs.next()) {
			tables.add(rs.getString("TABLE_NAME"));
		}
		cleanResultSet();
		return tables;
	}
	
	public void getDataTable(String table, String db) throws SQLException {
		HashMap<String, ArrayList<String>> tableData = new HashMap<String, ArrayList<String>>();
		String sql = "Select * from "+db+"."+table;
		stmt = conn.createStatement();
		rs = execQuery(sql);
		rsm = rs.getMetaData();
		columns = new ArrayList<String>();
		System.out.println(rsm.getColumnCount());
		for(int i=1; i<=rsm.getColumnCount(); i++) {
			columns.add(rsm.getColumnName(i));
			
		}		
		rows = new ArrayList<ArrayList<String>>();
		while(rs.next()) {
			ArrayList<String> fila = new ArrayList<String>();
			for(int i=0; i<columns.size(); i++) {
				fila.add(rs.getString(columns.get(i)));
			}
			rows.add(fila);
		}
	}
	
	private void cleanResultSet() {
		this.rs = null;
	}
	
	private ResultSet execQuery(String sql) throws SQLException {
		return stmt.executeQuery(sql);
	}
	
	public ArrayList<String> getColumns() {
		return this.columns;
	}
	
	public ArrayList<ArrayList<String>> getRows(){
		return this.rows;
	}
	
}
