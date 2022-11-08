package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import models.Model;
import views.View;

public class Controller implements ActionListener {

	private Model model;
	private View view;
	
	public Controller(Model model, View view) throws SQLException {
		
		this.model = model;
		this.view = view;
		PutListeners();
		
	}
	
	public void launchView() {
		
		view.setTitle("CRUD");
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setLocationRelativeTo(null);
		view.setVisible(true);
		
	}
	
	private void PutListeners() throws SQLException {
		
		//Create
		view.PutCientifico.addActionListener(this);
		view.PutProyecto.addActionListener(this);
		view.putAsignadoA.addActionListener(this);
		
		//Update
		view.modifyCientifico.addActionListener(this);
		view.modifyProyecto.addActionListener(this);
		
		//Read
		view.selectTableBox.addActionListener(this);
		
		String[] tables = model.getTablesDB();
		
		for (int i = 0; i < tables.length; i++) {
			
			view.selectTableBox.addItem(tables[i]);
			
		}
		
		//Delete
		
		
	}
	
	private void reloadView() throws SQLException {
		
		//Todos los text field reset
		view.FieldDNI.setText("");
		view.FieldNomApels.setText("");
		view.FieldNombreProyect.setText("");
		view.FieldModifyNomApels.setText("");
		view.nameModifyProyect.setText("");
		
		//Selector de horas al que tienen por defecto
		view.selectedHours.setValue(1);
		view.selectedHoursModify.setValue(0);
		
		//Item DNI
		
		String[] dni = model.getAllDNI();
		
		view.ProyectoIDBox_1.removeAll();
		view.dniCientificoBox.removeAll();
		
		for (int i = 0; i < dni.length; i++) {
			
			view.ProyectoIDBox_1.addItem(dni[i]);
			view.dniCientificoBox.addItem(dni[i]);
			
		}
		
		view.ProyectoIDBox_1.setSelectedIndex(0);
		view.dniCientificoBox.setSelectedIndex(0);
		
		//Item ID
		
		String[] id = model.getAllIds();
		
		view.ProyectoIDBox_1.removeAll();
		view.dniCientificoBox.removeAll();
		
		for (int i = 0; i < dni.length; i++) {
			
			view.ProyectoIDBox_1.addItem(dni[i]);
			view.dniCientificoBox.addItem(dni[i]);
			
		}
		
		view.ProyectoIDBox.setSelectedIndex(0);
		view.idProyectBoxModify.setSelectedIndex(0);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(view.PutCientifico == e.getSource()) {
			
			if (!view.FieldDNI.toString().equals("") && !view.FieldNomApels.toString().equals("") && dniCorrecto(view.FieldDNI.toString())) {
				
				String query = "INSERT INTO cientificos(dni, nomapels) VALUES ('"+ view.FieldDNI.toString() +"', '" + view.FieldNomApels.toString() +"');";
				model.passQuery(query);
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Alguno de los valores introducidos para insertar Cientifico es erroneo");
				
			}
			
		} else if (view.PutProyecto == e.getSource()) {
			
			if (!view.FieldNombreProyect.toString().equals("") && !view.idField.toString().equals("") && view.idField.toString().length() <= 4) {
				
				String query = "INSERT INTO proyecto(id, nombre, horas) VALUES ('"+ view.idField.toString() +"', '"+ view.FieldNombreProyect.toString() +"', '" + view.selectedHours.getValue().toString() +"');";
				model.passQuery(query);
			
			} else {
				
				JOptionPane.showMessageDialog(null, "Alguno de los valores introducidos para insertar Proyecto es erroneo");
				
			}
			
		//Hacer que no coinicdan
		} else if (view.putAsignadoA == e.getSource()) {
			
			if (!view.ProyectoIDBox_1.getSelectedItem().equals("") && !view.ProyectoIDBox.getSelectedItem().equals("")) {
				
				String query = "INSERT INTO asignadoa(dni, id) VALUES ('"+ view.ProyectoIDBox_1.getSelectedItem().toString() +"', '" + view.ProyectoIDBox.getSelectedItem().toString() +"');";
				model.passQuery(query);
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Alguno de los valores introducidos para insertar Asignar_A es erroneo");
				
			}
			
			//Cambiar query
		} else if (view.modifyCientifico == e.getSource()) {
			
			if (!view.FieldModifyNomApels.toString().equals("")) {
				
				String query = "UPDATE cientificos SET nomapels = '"+ view.FieldModifyNomApels.toString() +"' WHERE dni = '" + view.dniCientificoBox.getSelectedItem().toString() +"'";
				model.passQuery(query);
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Alguno de los valores introducidos para modificar Cientifico es erroneo");
				
			}
			
		} else if (view.modifyProyecto == e.getSource()) {
			
			if (!view.nameModifyProyect.toString().equals("") && !view.selectedHoursModify.getValue().toString().equals("0")) {
				
				String query = "UPDATE proyecto SET nombre = '"+ view.nameModifyProyect.toString() +"', horas = '"+ view.selectedHoursModify.getValue().toString() +"' WHERE id = '" + view.idProyectBoxModify.getSelectedItem().toString() +"'";
				model.passQuery(query);
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Alguno de los valores introducidos para modificar Proyecto es erroneo");
				
			}
			
		}
		
		if (view.selectTableBox == e.getSource()) {
			
			String query = "SELECT * FROM " + view.selectTableBox.toString();
			String result = model.lateUploadSelect(query);
			view.textPane.setText(result);
			
		}
		
		try {
			
			reloadView();
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
			
		}
		
	}
	
	private boolean dniCorrecto(String dni) {
		
		if (dni.length() == 9) {
			
			for (int i = 0; i < dni.length(); i++) {
				
				if (!Character.isDigit(dni.charAt(i)) && i < 8) {
					
					return false;
					
				}
				
				if (!Character.isAlphabetic(dni.charAt(i)) && i == 8) {
					
					return false;
					
				} 
				
			}
			
			return true;
			
		}
		
		return false;
		
	}
	
	
	
}
