package controllers;

import java.awt.HeadlessException;
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
		
		//Delete
		view.deletecientificButton.addActionListener(this);
		view.deleteproyectButton.addActionListener(this);
		view.deleteasignaxionButton.addActionListener(this);
		
		String[] tables = model.getTablesDB();
		
		for (int i = 0; i < tables.length; i++) {
			
			view.selectTableBox.addItem(tables[i]);
			
		}
				
	}
	
	private void reloadView() throws SQLException {
		
		//Todos los text field reset
		view.FieldDNI.setText("");
		view.FieldNomApels.setText("");
		view.FieldNombreProyect.setText("");
		view.FieldModifyNomApels.setText("");
		view.nameModifyProyect.setText("");
		view.idField.setText("");
		
		//Selector de horas al que tienen por defecto
		view.selectedHours.setValue(1);
		view.selectedHoursModify.setValue(0);
		
		//Item DNI
		
		String[] dni = model.getAllDNI();
		
		view.ProyectoIDBox_1.removeAllItems();
		view.dniCientificoBox.removeAllItems();
		view.dniCientificoBoxdelete.removeAllItems();
		view.dniCientificoBoxdeleteasignacion.removeAllItems();
		
		for (int i = 0; i < dni.length; i++) {
			
			view.ProyectoIDBox_1.addItem(dni[i]);
			view.dniCientificoBox.addItem(dni[i]);
			view.dniCientificoBoxdelete.addItem(dni[i]);
			view.dniCientificoBoxdeleteasignacion.addItem(dni[i]);
			
		}
		
		//Item ID
		
		String[] id = model.getAllIds();
		
		view.ProyectoIDBox.removeAllItems();
		view.idProyectBoxModify.removeAllItems();
		view.dniproyectoBoxdelete.removeAllItems();
		view.dniproyectoBoxdeleteasignacion.removeAllItems();
		
		for (int i = 0; i < id.length; i++) {
			
			view.ProyectoIDBox.addItem(id[i]);
			view.idProyectBoxModify.addItem(id[i]);
			view.dniproyectoBoxdelete.addItem(id[i]);
			view.dniproyectoBoxdeleteasignacion.addItem(id[i]);
			
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Create
		if(view.PutCientifico == e.getSource()) {
			
			if (!view.FieldDNI.getText().equals("") && !view.FieldNomApels.getText().equals("") && dniCorrecto(view.FieldDNI.getText())) {
			
				String query = "INSERT INTO cientificos(dni, nomapels) VALUES ('"+ view.FieldDNI.getText() +"', '" + view.FieldNomApels.getText() +"');";
				model.passQuery(query);
				System.out.println("Correct Insert");
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Alguno de los valores introducidos para insertar Cientifico es erroneo");
				
			}
			
		} else if (view.PutProyecto == e.getSource()) {
			
			if (!view.FieldNombreProyect.getText().equals("") && !view.idField.getText().equals("") && view.idField.getText().length() <= 4) {
				
				String query = "INSERT INTO proyecto(id, nombre, horas) VALUES ('"+ view.idField.getText() +"', '"+ view.FieldNombreProyect.getText() +"', '" + view.selectedHours.getValue().toString() +"');";
				model.passQuery(query);
				System.out.println("Correct Insert");
			
			} else {
				
				JOptionPane.showMessageDialog(null, "Alguno de los valores introducidos para insertar Proyecto es erroneo");
				
			}
			
		} else if (view.putAsignadoA == e.getSource()) {
			
			if (!view.ProyectoIDBox_1.getSelectedItem().equals("") && !view.ProyectoIDBox.getSelectedItem().equals("")) {
				
				try {
					
					if (!AreLinked(0)) {
						
					String query = "INSERT INTO asignadoa(dni, id) VALUES ('"+ view.ProyectoIDBox_1.getSelectedItem().toString() +"', '" + view.ProyectoIDBox.getSelectedItem().toString() +"');";
					model.passQuery(query);
					System.out.println("Correct Insert");
					
					} else {
						
						System.out.println("El cientifico ya esta asignado en el equipo");
						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Alguno de los valores introducidos para insertar Asignar_A es erroneo");
				
			}
			
		//Update
		} else if (view.modifyCientifico == e.getSource()) {
			
			if (!view.FieldModifyNomApels.getText().equals("") && view.dniCientificoBox.getSelectedItem() != null) {
				
				String query = "UPDATE cientificos SET nomapels = '"+ view.FieldModifyNomApels.getText() +"' WHERE dni = '" + view.dniCientificoBox.getSelectedItem().toString() +"'";
				model.passQuery(query);
				System.out.println("Correct Update");
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Alguno de los valores introducidos para modificar Cientifico es erroneo");
				
			}
			
		} else if (view.modifyProyecto == e.getSource()) {
			
			if (!view.nameModifyProyect.getText().equals("") && !view.selectedHoursModify.getValue().toString().equals("0") && view.idProyectBoxModify.getSelectedItem() != null) {
				
				String query = "UPDATE proyecto SET nombre = '"+ view.nameModifyProyect.getText() +"', horas = '"+ view.selectedHoursModify.getValue().toString() +"' WHERE id = '" + view.idProyectBoxModify.getSelectedItem().toString() +"'";
				model.passQuery(query);
				System.out.println("Correct Update");
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Alguno de los valores introducidos para modificar Proyecto es erroneo");
				
			}
		
		//Delete

		} else if (view.deletecientificButton == e.getSource()) {
			
			if (view.dniCientificoBoxdelete.getSelectedItem() != null) {
			
				String query = "DELETE FROM cientificos WHERE dni = '"+ view.dniCientificoBoxdelete.getSelectedItem().toString() +"'";
				model.passQuery(query);
				System.out.println("Correct Delete");
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Alguno de los valores introducidos para borrar Cientifico es erroneo");
				
			}
			
		} else if (view.deleteproyectButton == e.getSource()) {
			
			if (view.dniproyectoBoxdelete.getSelectedItem() != null) {
				
				String query = "DELETE FROM proyecto WHERE dni = '"+ view.dniproyectoBoxdelete.getSelectedItem().toString() +"'";
				model.passQuery(query);
				System.out.println("Correct Delete");
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Alguno de los valores introducidos para borrar Proyecto es erroneo");
				
			}
			
		} else if (view.deleteasignaxionButton == e.getSource()) {
			
			if (view.dniCientificoBoxdeleteasignacion.getSelectedItem() != null && view.dniproyectoBoxdeleteasignacion.getSelectedItem() != null) {
				
				try {
					
					if (AreLinked(1)) {
						
						String query = "DELETE FROM proyecto WHERE dni = '"+ view.dniproyectoBoxdelete.getSelectedItem().toString() +"' AND id = '" +view.dniproyectoBoxdeleteasignacion.getSelectedItem().toString() + "'" ;
						model.passQuery(query);
						System.out.println("Correct Delete");
						
					} else {
						
						JOptionPane.showMessageDialog(null, "Alguno de los valores introducidos para borrar AsignadoA es erroneo");
						
					}
					
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Alguno de los valores introducidos para borrar AsignadoA es erroneo");
				
			}
			
		} else if (view.selectTableBox == e.getSource()) {
			
			String query = "SELECT * FROM " + view.selectTableBox.getSelectedItem().toString().toLowerCase();
			String result = model.lateUploadSelect(query);
			view.textPane.setText(result);
			
		}
		
		try {
			
			reloadView();
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
			
		}
		
	}
	
	private boolean AreLinked(int request) throws SQLException {
		
		String dni = "";
		String query = "";
		
		if (request == 0) {
			
			dni = view.ProyectoIDBox_1.getSelectedItem().toString();
			query = view.ProyectoIDBox.getSelectedItem().toString();
			
		} else if (request == 1) {
			
			dni = view.dniCientificoBoxdeleteasignacion.getSelectedItem().toString();
			query = view.dniproyectoBoxdeleteasignacion.getSelectedItem().toString();
			
		}
		
		String[] resultados = model.getEnlaces(query);
		
		for (int i = 0; i < resultados.length; i++) {
			
			if (resultados[i].equals(dni)) {
				
				return true;
				
			}
			
		}
		
		return false;
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
