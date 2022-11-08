package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import models.Model;
import views.View;

public class Controller implements ActionListener {

	private Model model;
	private View view;
	
	public Controller(Model model, View view) {
		
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
	
	private void PutListeners() {
		
		view.PutCientifico.addActionListener(this);
		view.PutProyecto.addActionListener(this);
		view.putAsignadoA.addActionListener(this);
		view.modifyCientifico.addActionListener(this);
		view.modifyProyecto.addActionListener(this);
		
	}
	
	private void reloadView() {
		
		//Todos los text field reset
		view.FieldDNI.setText("");
		view.FieldNomApels.setText("");
		view.FieldNombreProyect.setText("");
		view.FieldModifyNomApels.setText("");
		view.nameModifyProyect.setText("");
		
		//Selector de horas al que tienen por defecto
		view.selectedHours.setValue(1);
		view.selectedHoursModify.setValue(0);
		
		//???
		view.dniCientificoBox.add("Hola", view);;
		
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
			
		} else if (view.PutCientifico == e.getSource()) {
			
			
			
		}
		
		reloadView();
		
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
