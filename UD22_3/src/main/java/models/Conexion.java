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

	// Método para establecer conexión

	public void establecerCon() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(
					"jdbc:mysql://192.168.1.172:3306?useTimezone=true&serverTimezone=UTC", "remote", "Kappa323232!");
			System.out.println("Server connected");
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("No se ha podido conectar con mi base de datos");
			ex.printStackTrace();
			System.out.println(ex);
		}

	}

	// Método para cerrar la conexión

	public void cerrarCon() {

		try {
			conexion.close();
			JOptionPane.showMessageDialog(null, "Se ha finalizado la conexión con el server");
		} catch (SQLException ex) {
			Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	// Método que elimina las bases de datos que les introducimos en caso de existir
	// y crea una con dicho nombre

	public void crearDB() {

		try {

			String Query = "DROP DATABASE IF EXISTS cientificos;";
			String Query2 = "CREATE DATABASE cientificos;";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			st.executeUpdate(Query2);

		} catch (SQLException ex) {

			System.out.println("No se ha podido conectar con la base de datos");

		}

	}

	// Método para crear las tablas

	public void crearTablas() {

		try {

			String Querydb1 = "USE cientificos;";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb1);

			String Query1 = "CREATE TABLE cientificos (\r\n" + "dni varchar(9) NOT NULL,\r\n"
					+ "nomapels nvarchar(255) NOT NULL,\r\n" + "PRIMARY KEY (dni)\r\n" + ");";

			stdb.executeUpdate(Query1);

			// Si falla por aqui se debe al formato que tiene el id

			String Query2 = "CREATE TABLE proyecto (\r\n" + "id char(4) NOT NULL,\r\n"
					+ "nombre nvarchar(255) NOT NULL,\r\n" + "horas INT NOT NULL,\r\n" + "PRIMARY KEY (id)\r\n" + ");";

			stdb.executeUpdate(Query2);

			String Query3 = "CREATE TABLE asignadoa (\r\n" + "dni varchar(9) NOT NULL,\r\n" + "id char(4) NOT NULL,\r\n"
					+ "PRIMARY KEY (dni, id),\r\n" + "FOREIGN KEY (dni) REFERENCES cientificos(dni),\r\n"
					+ "FOREIGN KEY (id) REFERENCES proyecto(id)\r\n" + ");";

			stdb.executeUpdate(Query3);

			System.out.println("Tabla cliente creada correctamente");

		} catch (Exception ex) {

			System.out.println(ex);

		}

	}

	public void datainstruction(String query) {

		try {

			Statement stdb = conexion.createStatement();
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

		String query = table;

		ResultSet rs = null;

		try {

			rs = stdb.executeQuery(query);

		} catch (SQLException e) {

			e.printStackTrace();

		}

		String select = "";

		// rs.beforeFirst();

		switch (table.charAt(table.length()-1)) {

		case 's':

			while (rs.next()) {

				select += rs.getString("dni") +" - "+ rs.getString("nomapels") + "\n";

			}

			break;

		case 'o':

			while (rs.next()) {
				select += rs.getString("id") +" - "+ rs.getString("nombre") +" - "+ rs.getInt("horas") + "\n";
			}

			break;

		case 'a':

			while (rs.next()) {

				select += rs.getString("dni") +" - "+ rs.getString("id") + "\n";

			}

			break;

		}

		return select;

	}

	public String[] getAllTables() throws SQLException {

		Statement stdb = null;

		try {

			stdb = conexion.createStatement();

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		ResultSet rs = null;

		try {

			rs = stdb.executeQuery("SHOW TABLES");

		} catch (SQLException e) {

			e.printStackTrace();

		}

		String[] tablas = new String[3];

		// rs.beforeFirst();
		for (int i = 0; i < tablas.length; i++) {
			rs.next();
			tablas[i] = rs.getString("Tables_in_cientificos");

		}

		return tablas;

	}

	public String[] getAllDNI() throws SQLException {

		Statement stdb = null;

		try {

			stdb = conexion.createStatement();

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		int count = contQueryResults("cientificos");
		ResultSet rs = null;

		try {

			rs = stdb.executeQuery("SELECT dni FROM cientificos");

		} catch (SQLException e) {

			e.printStackTrace();

		}

		String[] dni;

		if (count != 0) {

			dni = new String[count];

		} else {

			dni = new String[0];

		}

		// rs.beforeFirst();
		int i = 0;
		while (rs.next()) {
			dni[i] = rs.getString("dni");
			i++;
		}

		return dni;

	}

	private int contQueryResults(String string) {
		// TODO Auto-generated method stub
		int count = 0;
		Statement stdb;
		try {
			stdb = conexion.createStatement();
			ResultSet rscount = stdb.executeQuery("SELECT count(*) FROM " + string);
			if (rscount.next()) {
				count = rscount.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}

	public String[] getAllIds() throws SQLException {

		Statement stdb = null;

		try {

			stdb = conexion.createStatement();

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		int count = contQueryResults("proyecto");
		ResultSet rs = null;

		try {

			rs = stdb.executeQuery("SELECT id FROM proyecto");

		} catch (SQLException e) {

			e.printStackTrace();

		}

		String[] id;

		if (count != 0) {

			id = new String[count];

		} else {

			id = new String[0];

		}

		// rs.beforeFirst();
		int i = 0;
		while (rs.next()) {
			id[i] = rs.getString("id");
			i++;
		}

		return id;

	}

}
