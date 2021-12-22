package GUI;

import javax.swing.*;
import java.awt.Color;

import javax.swing.border.TitledBorder;

import Controller.HabitacionController;
import Controller.OcuparController;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import modelosTabla.DateLabelFormatter;
import utils.Converter;

import java.util.List;
import java.util.Properties;

@SuppressWarnings("serial")
public class OcuparHabitacionGUI extends JFrame{
	private Converter converter = new Converter();

	private OcuparController controller;

	private JDatePickerImpl datePickerDesde;
	private JDatePickerImpl datePickerHasta;

	private JSpinner indEstandar;
	private JSpinner dobEstandar;
	private JSpinner dobSuperior;
	private JSpinner supFamily;
	private JSpinner suiteDoble;

	private JCheckBox chbxIndEstandar;
	private JCheckBox chbxDobEstandar;
	private JCheckBox chbxDobSuperior;
	private JCheckBox chbxSupFamily;
	private JCheckBox chbxSuiteDoble;

	//Getters And Setters
	public JCheckBox getChbxIndEstandar() {
		return chbxIndEstandar;
	}
	public void setChbxIndEstandar(JCheckBox chbxIndEstandar) {
		this.chbxIndEstandar = chbxIndEstandar;
	}
	public JCheckBox getChbxDobEstandar() {
		return chbxDobEstandar;
	}
	public void setChbxDobEstandar(JCheckBox chbxDobEstandar) {
		this.chbxDobEstandar = chbxDobEstandar;
	}
	public JCheckBox getChbxDobSuperior() {
		return chbxDobSuperior;
	}
	public void setChbxDobSuperior(JCheckBox chbxDobSuperior) {
		this.chbxDobSuperior = chbxDobSuperior;
	}
	public JCheckBox getChbxSupFamily() {
		return chbxSupFamily;
	}
	public void setChbxSupFamily(JCheckBox chbxSupFamily) {
		this.chbxSupFamily = chbxSupFamily;
	}
	public JCheckBox getChbxSuiteDoble() {
		return chbxSuiteDoble;
	}
	public void setChbxSuiteDoble(JCheckBox chbxSuiteDoble) {
		this.chbxSuiteDoble = chbxSuiteDoble;
	}



	public OcuparHabitacionGUI() {
		controller = new OcuparController(this);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		this.setSize(500, 400);
		
		JButton btnMostrarEstado = new JButton("Ver estados de Habitacion");
		btnMostrarEstado.setBounds(22, 10, 208, 21);
		getContentPane().add(btnMostrarEstado);
		btnMostrarEstado.addActionListener(e->{
			MostrarEstadoHabitacionGUI mostrarEstado = new MostrarEstadoHabitacionGUI();
			mostrarEstado.setVisible(true);
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Asignar Habitacion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(22, 41, 440, 272);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblIngreso = new JLabel("Ingreso");
		lblIngreso.setBounds(31, 35, 45, 13);
		panel.add(lblIngreso);
		
		//Adding DatePicker DESDE
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Hoy");
		p.put("text.month", "Mes");
		p.put("text.year", "Ano");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePickerDesde = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePickerDesde.setBounds(80, 30, 110, 30);
		panel.add(datePickerDesde);
		
		JLabel lblEgreso = new JLabel("Egreso");
		lblEgreso.setBounds(219, 35, 45, 13);
		panel.add(lblEgreso);
		
		//Adding DatePicker HASTA
		UtilDateModel model2 = new UtilDateModel();
		Properties p2 = new Properties();
		p2.put("text.today", "Hoy");
		p2.put("text.month", "Mes");
		p2.put("text.year", "Ano");
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
		datePickerHasta = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		datePickerHasta.setBounds(270, 30, 110, 30);
		panel.add(datePickerHasta);
		
		JLabel lblTHabitacion = new JLabel("Tipos de habitacion");
		lblTHabitacion.setBounds(31, 80, 125, 13);
		panel.add(lblTHabitacion);
		
		JLabel lblCantHab = new JLabel("Cantidad de Habitaciones");
		lblCantHab.setBounds(204, 80, 176, 13);
		panel.add(lblCantHab);
		
		//SPINNERS
		chbxIndEstandar = new JCheckBox("Individual Estandar");
		chbxIndEstandar.setBounds(31, 100, 146, 25);
		panel.add(chbxIndEstandar);
		
		indEstandar = new JSpinner();
		indEstandar.setEnabled(false);
		indEstandar.setModel(new SpinnerNumberModel(0, 0, null, 1));
		indEstandar.setBounds(249, 100, 36, 25);
		panel.add(indEstandar);
		
		chbxDobEstandar = new JCheckBox("Doble Estandar");
		chbxDobEstandar.setBounds(31, 130, 146, 25);
		panel.add(chbxDobEstandar);
		
		dobEstandar = new JSpinner();
		dobEstandar.setEnabled(false);
		dobEstandar.setModel(new SpinnerNumberModel(0, 0, null, 1));
		dobEstandar.setBounds(249, 130, 36, 25);
		panel.add(dobEstandar);
		
		chbxDobSuperior = new JCheckBox("Doble Superior");
		chbxDobSuperior.setBounds(31, 160, 146, 25);
		panel.add(chbxDobSuperior);
		
		dobSuperior = new JSpinner();
		dobSuperior.setEnabled(false);
		dobSuperior.setModel(new SpinnerNumberModel(0, 0, null, 1));
		dobSuperior.setBounds(249, 160, 36, 25);
		panel.add(dobSuperior);
		
		chbxSupFamily = new JCheckBox("Superior Family Plan");
		chbxSupFamily.setBounds(31, 190, 146, 25);
		panel.add(chbxSupFamily);
		
		supFamily = new JSpinner();
		supFamily.setEnabled(false);
		supFamily.setModel(new SpinnerNumberModel(0, 0, null, 1));
		supFamily.setBounds(249, 190, 36, 25);
		panel.add(supFamily);
		
		chbxSuiteDoble = new JCheckBox("Suite Doble");
		chbxSuiteDoble.setBounds(31, 220, 146, 25);
		panel.add(chbxSuiteDoble);
		
		suiteDoble = new JSpinner();
		suiteDoble.setEnabled(false);
		suiteDoble.setModel(new SpinnerNumberModel(0, 0, null, 1));
		suiteDoble.setBounds(249, 220, 36, 25);
		panel.add(suiteDoble);
		
		JButton btnAsignarHab = new JButton("Asignar Habitacion");
		btnAsignarHab.setBounds(306, 323, 156, 21);
		btnAsignarHab.setForeground(new Color(255, 255, 255));
		btnAsignarHab.setBackground(new Color(50, 205, 50));
		getContentPane().add(btnAsignarHab);
		btnAsignarHab.addActionListener(e->{
			MostrarEstadoHabitacionGUI mostrarEstadoHabitacionGUI = new MostrarEstadoHabitacionGUI();
			mostrarEstadoHabitacionGUI.setVisible(false);
			HabitacionController habController = new HabitacionController(mostrarEstadoHabitacionGUI);
			EstadoHabitacionesGUI estadoHabitacionesGUI = new EstadoHabitacionesGUI(
					(converter.convertStringtoDate(datePickerDesde.getJFormattedTextField().getText())) ,
					(converter.convertStringtoDate(datePickerHasta.getJFormattedTextField().getText())) ,
					(habController.getHabDA(converter.convertStrtoLocalDate(datePickerDesde.getJFormattedTextField().getText()),
					(converter.convertStrtoLocalDate(datePickerHasta.getJFormattedTextField().getText())))),
					controller.getSelectedTipos(),
					habController);
			estadoHabitacionesGUI.getBtnAceptar().addActionListener(e1 ->{
				List<JTable> LTables = estadoHabitacionesGUI.getLTables();
				controller.getListOcupacion(   habController.getSelectedHab(LTables),
										habController.getSelections(LTables));

				estadoHabitacionesGUI.setVisible(false);
				controller.setOcupacionCounter();
				controller.asignarResponsableAcompanantesporOcupacion();
				/*
				OcuparHabAsigPasajeroGUI oh = new OcuparHabAsigPasajeroGUI(controller);
				oh.setVisible(true);*/
				this.setVisible(false);
				//this.dispose();
			});
			estadoHabitacionesGUI.setVisible(true);

		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(211, 323, 85, 21);
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBackground(new Color(255, 0, 0));
		getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(e->dispose());
		
		//Actions
		chbxIndEstandar.addActionListener(e->{
			if(chbxIndEstandar.isSelected()) {this.indEstandar.setEnabled(true);}
			else {this.indEstandar.setEnabled(false);}
		});
		chbxDobEstandar.addActionListener(e->{
			if(chbxDobEstandar.isSelected()) {this.dobEstandar.setEnabled(true);}
			else {this.dobEstandar.setEnabled(false);}
		});
		chbxDobSuperior.addActionListener(e->{
			if(chbxDobSuperior.isSelected()) {this.dobSuperior.setEnabled(true);}
			else {this.dobSuperior.setEnabled(false);}
		});
		chbxSupFamily.addActionListener(e->{
			if(chbxSupFamily.isSelected()) {this.supFamily.setEnabled(true);}
			else {this.supFamily.setEnabled(false);}
		});
		chbxSuiteDoble.addActionListener(e->{
			if(chbxSuiteDoble.isSelected()) {this.suiteDoble.setEnabled(true);}
			else {this.suiteDoble.setEnabled(false);}
		});
		
		
		
		
	}
}