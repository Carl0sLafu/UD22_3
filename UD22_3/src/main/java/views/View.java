package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.JTextPane;

public class View extends JFrame {

	private JPanel contentPane;
	
	public JTextField FieldDNI;
	public JTextField FieldNomApels;
	public JButton PutCientifico;
	
	public JTextField FieldNombreProyect;
	public JSpinner selectedHours;
	public JTextField idField;
	public JButton PutProyecto;
	
	public JComboBox<String> ProyectoIDBox;
	public JComboBox<String> ProyectoIDBox_1;
	public JButton putAsignadoA;
	
	public JComboBox<String> dniCientificoBox;
	public JTextField FieldModifyNomApels;
	public JButton modifyCientifico;
	
	public JComboBox<String> idProyectBoxModify;
	public JTextField nameModifyProyect;
	public JSpinner selectedHoursModify;
	public JButton modifyProyecto;
	
	public JComboBox<String> selectTableBox;
	
	public JTextPane textPane;
	
	
	/**
	 * Create the frame.
	 */
	public View() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 670);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title1 = new JLabel("Cientificos");
		title1.setFont(new Font("Tahoma", Font.BOLD, 12));
		title1.setBounds(10, 25, 70, 18);
		contentPane.add(title1);
		
		JLabel CientificosDNI = new JLabel("DNI*");
		CientificosDNI.setBounds(10, 55, 45, 15);
		contentPane.add(CientificosDNI);
		
		FieldDNI = new JTextField();
		FieldDNI.setBounds(10, 75, 85, 20);
		contentPane.add(FieldDNI);
		FieldDNI.setColumns(10);
		
		JLabel labelNomApels = new JLabel("Nombre y apellidos*");
		labelNomApels.setBounds(10, 105, 120, 15);
		contentPane.add(labelNomApels);
		
		FieldNomApels = new JTextField();
		FieldNomApels.setBounds(10, 130, 160, 20);
		contentPane.add(FieldNomApels);
		FieldNomApels.setColumns(10);
		
		PutCientifico = new JButton("Intro");
		PutCientifico.setBounds(10, 160, 90, 25);
		contentPane.add(PutCientifico);
		
		JLabel title2 = new JLabel("Proyecto");
		title2.setFont(new Font("Tahoma", Font.BOLD, 12));
		title2.setBounds(200, 25, 70, 18);
		contentPane.add(title2);
		
		JLabel lblnameproyecto = new JLabel("Nombre*");
		lblnameproyecto.setBounds(200, 55, 50, 14);
		contentPane.add(lblnameproyecto);
		
		FieldNombreProyect = new JTextField();
		FieldNombreProyect.setBounds(200, 75, 115, 20);
		contentPane.add(FieldNombreProyect);
		FieldNombreProyect.setColumns(10);
		
		JLabel lblHoras = new JLabel("Horas*");
		lblHoras.setBounds(200, 105, 60, 14);
		contentPane.add(lblHoras);
		
		selectedHours = new JSpinner();
		selectedHours.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		selectedHours.setBounds(200, 130, 70, 20);
		contentPane.add(selectedHours);
		
		PutProyecto = new JButton("Intro");
		PutProyecto.setBounds(200, 160, 90, 25);
		contentPane.add(PutProyecto);
		
		JLabel title3 = new JLabel("Asignación");
		title3.setFont(new Font("Tahoma", Font.BOLD, 12));
		title3.setBounds(345, 25, 80, 18);
		contentPane.add(title3);
		
		JLabel lblIdProyecto = new JLabel("Proyecto ID*");
		lblIdProyecto.setBounds(345, 55, 80, 15);
		contentPane.add(lblIdProyecto);
		
		ProyectoIDBox = new JComboBox();
		ProyectoIDBox.setBounds(345, 74, 80, 22);
		contentPane.add(ProyectoIDBox);
		
		JLabel lblcientificodni = new JLabel("DNI Cientifico*");
		lblcientificodni.setBounds(345, 105, 90, 15);
		contentPane.add(lblcientificodni);
		
		ProyectoIDBox_1 = new JComboBox();
		ProyectoIDBox_1.setBounds(345, 129, 80, 22);
		contentPane.add(ProyectoIDBox_1);
		
		putAsignadoA = new JButton("Intro");
		putAsignadoA.setBounds(345, 160, 90, 25);
		contentPane.add(putAsignadoA);
		
		JLabel lblModifyCientifico = new JLabel("DNI*");
		lblModifyCientifico.setBounds(10, 195, 60, 15);
		contentPane.add(lblModifyCientifico);
		
		dniCientificoBox = new JComboBox();
		dniCientificoBox.setBounds(10, 220, 100, 22);
		contentPane.add(dniCientificoBox);
		
		JLabel lblModifynameCientific = new JLabel("Modify name*");
		lblModifynameCientific.setBounds(10, 255, 80, 15);
		contentPane.add(lblModifynameCientific);
		
		FieldModifyNomApels = new JTextField();
		FieldModifyNomApels.setColumns(10);
		FieldModifyNomApels.setBounds(10, 281, 160, 20);
		contentPane.add(FieldModifyNomApels);
		
		modifyCientifico = new JButton("Modify");
		modifyCientifico.setBounds(10, 315, 90, 25);
		contentPane.add(modifyCientifico);
		
		modifyProyecto = new JButton("Modify");
		modifyProyecto.setBounds(200, 385, 90, 25);
		contentPane.add(modifyProyecto);
		
		JLabel lblID = new JLabel("ID*");
		lblID.setBounds(200, 195, 50, 14);
		contentPane.add(lblID);
		
		idProyectBoxModify = new JComboBox();
		idProyectBoxModify.setBounds(200, 220, 100, 22);
		contentPane.add(idProyectBoxModify);
		
		JLabel lblnameProyectModify = new JLabel("Modify name");
		lblnameProyectModify.setBounds(200, 255, 80, 15);
		contentPane.add(lblnameProyectModify);
		
		nameModifyProyect = new JTextField();
		nameModifyProyect.setBounds(200, 280, 115, 20);
		contentPane.add(nameModifyProyect);
		nameModifyProyect.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Modify Horas");
		lblNewLabel.setBounds(200, 320, 80, 15);
		contentPane.add(lblNewLabel);
		
		selectedHoursModify = new JSpinner();
		selectedHoursModify.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		selectedHoursModify.setBounds(200, 346, 70, 20);
		contentPane.add(selectedHoursModify);
		
		JLabel lblDeletebyDniCientific = new JLabel("Cientific to delete");
		lblDeletebyDniCientific.setBounds(10, 430, 100, 15);
		contentPane.add(lblDeletebyDniCientific);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 190, 500, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 420, 500, 2);
		contentPane.add(separator_1);
		
		JComboBox dniCientificoBoxdelete = new JComboBox();
		dniCientificoBoxdelete.setBounds(10, 450, 100, 22);
		contentPane.add(dniCientificoBoxdelete);
		
		JButton deletecientificButton = new JButton("Delete");
		deletecientificButton.setBounds(10, 500, 89, 23);
		contentPane.add(deletecientificButton);
		
		JButton deleteproyectButton = new JButton("Delete");
		deleteproyectButton.setBounds(201, 500, 89, 23);
		contentPane.add(deleteproyectButton);
		
		JComboBox dniproyectoBoxdelete = new JComboBox();
		dniproyectoBoxdelete.setBounds(200, 450, 100, 22);
		contentPane.add(dniproyectoBoxdelete);
		
		JLabel lblproyectodelete = new JLabel("Proyect to delete");
		lblproyectodelete.setBounds(200, 430, 100, 14);
		contentPane.add(lblproyectodelete);
		
		JLabel lblasignaciondelete = new JLabel("Asignacion to delete");
		lblasignaciondelete.setBounds(345, 430, 100, 14);
		contentPane.add(lblasignaciondelete);
		
		JComboBox dniCientificoBoxdeleteasignacion = new JComboBox();
		dniCientificoBoxdeleteasignacion.setBounds(345, 485, 100, 22);
		contentPane.add(dniCientificoBoxdeleteasignacion);
		
		JComboBox dniproyectoBoxdeleteasignacion = new JComboBox();
		dniproyectoBoxdeleteasignacion.setBounds(345, 450, 100, 22);
		contentPane.add(dniproyectoBoxdeleteasignacion);
		
		JButton deleteasignaxionButton = new JButton("Delete");
		deleteasignaxionButton.setBounds(345, 530, 90, 23);
		contentPane.add(deleteasignaxionButton);
		
		textPane = new JTextPane();
		textPane.setBounds(530, 44, 190, 576);
		contentPane.add(textPane);
		
		selectTableBox = new JComboBox();
		selectTableBox.setBounds(530, 11, 190, 22);
		contentPane.add(selectTableBox);
		
		JLabel idlabel = new JLabel("ID*");
		idlabel.setBounds(270, 105, 46, 14);
		contentPane.add(idlabel);
		
		idField = new JTextField();
		idField.setBounds(270, 130, 60, 20);
		contentPane.add(idField);
		idField.setColumns(10);
		
	}
}
