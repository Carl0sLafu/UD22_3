package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class Conexion {
	
	private Connection conexion;
	
	//Método para establecer conexión
	
		public void establecerCon() {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexion = DriverManager.getConnection("jdbc:mysql://192.168.1.70:3306?useTimezone=true&serverTimezone=UTC",
						"remote","##927SG7mt");
				System.out.println("Server connected");
			} catch (SQLException | ClassNotFoundException ex) {
				System.out.println("No se ha podido conectar con mi base de datos");
				ex.printStackTrace();
				System.out.println(ex);
			}
			
		}
		
		//Método para cerrar la conexión
		
		public void cerrarCon() {
			
			try {
				conexion.close();
				JOptionPane.showMessageDialog(null, "Se ha finalizado la conexión con el server");
			} catch (SQLException ex) {
				Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
			}
			
		}
			
		//Método que elimina las bases de datos que les introducimos en caso de existir y crea una con dicho nombre
		
		public void crearDB() {
			
			try {
				
				String Query =  "DROP DATABASE IF EXISTS cientificos;";
				String Query2 = "CREATE DATABASE cientificos;";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				st.executeUpdate(Query2);
				
			} catch(SQLException ex) {
				
				System.out.println("No se ha podido conectar con la base de datos");	
				
			}
			
		}
		
		
		//Método para crear las tablas
		
		public void crearTablas() {

	        try {

	            String Querydb1 = "USE cientificos;";
	            Statement stdb =  conexion.createStatement();
	            stdb.executeUpdate(Querydb1);

	            String Query1 = "CREATE TABLE cientificos (\r\n"
	            		+ "dni varchar(9) NOT NULL,\r\n"
	            		+ "nomapels nvarchar(255) NOT NULL,\r\n"
	            		+ "PRIMARY KEY (dni)\r\n"
	            		+ ");";
	           
	            stdb.executeUpdate(Query1);

	            //Si falla por aqui se debe al formato que tiene el id
	            	
	            String Query2 = "CREATE TABLE proyecto (\r\n"
	            		+ "id char(4) NOT NULL AUTO_INCREMENT,\r\n"
	            		+ "nombre nvarchar(255) NOT NULL,\r\n"
	            		+ "horas INT NOT NULL,\r\n"
	            		+ "PRIMARY KEY (id)\r\n"
	            		+ ");";
	           
	            stdb.executeUpdate(Query2);
	            
	            String Query3 = "CREATE TABLE asignadoa (\r\n"
	            		+ "dni varchar(9) NOT NULL,\r\n"
	            		+ "id char(4) NOT NULL,\r\n"
	            		+ "PRIMARY KEY (dni, id),\r\n"
	            		+ "FOREIGN KEY (dni) REFERENCES cientificos(dni),\r\n"
	            		+ "FOREIGN KEY (id) REFERENCES proyecto(id)\r\n"
	            		+ ");";
	           
	            stdb.executeUpdate(Query3);
	            
	            System.out.println("Tabla cliente creada correctamente");
	            
	        } catch (Exception ex) {

	            System.out.println(ex);

	        }

		}
		
		public void datainstruction(String query) {
			
            try {
            	
				Statement stdb =  conexion.createStatement();
				stdb.executeUpdate(query);
				
			} catch (SQLException e) {

				e.printStackTrace();
				
			}
			
		}
		
		public String selectQuery(String table) throws SQLException {
			 
			Statement stdb = null;
			
			try {
				
				stdb = conexion.createStatement();
				
			} catch (SQLException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			String query = "SELECT * FROM " + table;
			
			ResultSet rs = null;
			
			try {
				
				rs = stdb.executeQuery(query);
			
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
			
			String select = null;
			
			rs.beforeFirst();
			
			switch (table) {
			
				case "cientificos":
					
					while (rs.next()) {
					
						select += rs.getString("dni") + rs.getString("nomapels") + "\n";
					
					}
					
					break;
					
				case "proyecto":
					
					while (rs.next()) {
						select += rs.getString("id") + rs.getInt("nombre") + rs.getInt("horas") + "\n";
					}
					
					break;
					
				case "asignadoa":
					
					while (rs.next()) {
						
						select += rs.getString("dni") + rs.getString("id") + "\n";
						
					}
					
					break;
					
			}
			
			return select;
			
		}
		
}
