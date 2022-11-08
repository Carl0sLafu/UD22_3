package AppMain;

import java.sql.SQLException;

import controllers.Controller;
import models.Model;
import views.View;

public class MainApp {

	public static void main(String[] args) throws SQLException {
		
		View vista = new View();
		Model model = new Model();
		Controller controlador = new Controller(model, vista);
		controlador.launchView();
		
	}

}
