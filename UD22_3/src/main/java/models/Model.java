package models;

import java.sql.SQLException;

public class Model {

	private Conexion conexion;
	
	public Model() {
		
		conexion = new Conexion();
		conexion.establecerCon();
		//Quitar estas dos si encuentra la base de datos
		conexion.crearDB();
		conexion.crearTablas();
		
	}
	
	public void passQuery(String query) {
		
		conexion.datainstruction(query);
		
	}
	
	public String lateUploadSelect(String query) {
		
		try {
			
			return conexion.selectQuery(query);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return "";
		
	}

	public String[] getTablesDB() throws SQLException {
		
		return conexion.getAllTables();
		
	}

	public String[] getAllDNI() throws SQLException {
		
		return conexion.getAllDNI();
	}

	public String[] getAllIds() throws SQLException {
		
		return conexion.getAllIds();
		
	}
	
}
