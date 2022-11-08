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
		
		view.ProyectoIDBox_1.removeAllItems();
		view.dniCientificoBox.removeAllItems();
		
		for (int i = 0; i < dni.length; i++) {
			System.out.println(dni[i]);
			view.ProyectoIDBox_1.addItem(dni[i]);
			view.dniCientificoBox.addItem(dni[i]);
			
		}
		
		//view.ProyectoIDBox_1.setSelectedIndex(0);
		//view.dniCientificoBox.setSelectedIndex(0);
		
		//Item ID
		
		String[] id = model.getAllIds();
		
		view.ProyectoIDBox.removeAllItems();
		view.idProyectBoxModify.removeAllItems();
		
		for (int i = 0; i < id.length; i++) {
			
			view.ProyectoIDBox.addItem(id[i]);
			view.idProyectBoxModify.addItem(id[i]);
			
		}
		
		//view.ProyectoIDBox.setSelectedIndex(0);
		//view.idProyectBoxModify.setSelectedIndex(0);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(view.PutCientifico == e.getSource()) {
			
			//if (!view.FieldDNI.toString().equals("") && !view.FieldNomApels.toString().equals("") && dniCorrecto(view.FieldDNI.toString())) {
			if (!view.FieldDNI.toString().equals("") && !view.FieldNomApels.toString().equals("")) {	
				String query = "INSERT INTO cientificos(dni, nomapels) VALUES ('"+ view.FieldDNI.getText() +"', '" + view.FieldNomApels.getText() +"');";
				System.out.println(query);
				model.passQuery(query);
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Alguno de los valores introducidos para insertar Cientifico es erroneo");
				
			}
			
		} else if (view.PutProyecto == e.getSource()) {
			
			if (!view.FieldNombreProyect.getText().equals("") && !view.idField.getText().equals("") && view.idField.getText().length() <= 4) {
				
				String query = "INSERT INTO proyecto(id, nombre, horas) VALUES ('"+ view.idField.getText() +"', '"+ view.FieldNombreProyect.getText() +"', '" + view.selectedHours.getValue().toString() +"');";
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
			
			if (!view.FieldModifyNomApels.getText().equals("")) {
				
				String query = "UPDATE cientificos SET nomapels = '"+ view.FieldModifyNomApels.getText() +"' WHERE dni = '" + view.dniCientificoBox.getSelectedItem().toString() +"'";
				model.passQuery(query);
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Alguno de los valores introducidos para modificar Cientifico es erroneo");
				
			}
			
		} else if (view.modifyProyecto == e.getSource()) {
			
			if (!view.nameModifyProyect.getText().equals("") && !view.selectedHoursModify.getValue().toString().equals("0")) {
				
				String query = "UPDATE proyecto SET nombre = '"+ view.nameModifyProyect.getText() +"', horas = '"+ view.selectedHoursModify.getValue().toString() +"' WHERE id = '" + view.idProyectBoxModify.getSelectedItem().toString() +"'";
				model.passQuery(query);
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Alguno de los valores introducidos para modificar Proyecto es erroneo");
				
			}
			
		}
		
		if (view.selectTableBox == e.getSource()) {
			
			String query = "SELECT * FROM " + view.selectTableBox.getSelectedItem().toString();
			System.out.println(query);
			String result = model.lateUploadSelect(query);
			System.out.println(result);
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
