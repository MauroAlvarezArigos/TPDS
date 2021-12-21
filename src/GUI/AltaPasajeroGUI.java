package GUI;

import Controller.DarAltaController;
import modelosTabla.DateLabelFormatter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class AltaPasajeroGUI extends JFrame{
	//Colors
	Color Warning;
	Color CancelButton;
	Color NextButton;
	//Getters and Setters For Colors
	public void setWarningColor(Color warning) {
		Warning = warning;
	}
	public void setCancelButtonColor(Color cancelButton) {
		CancelButton = cancelButton;
	}
	public void setNextButtonColor(Color nextButton) {
		NextButton = nextButton;
	}

	//Personal Data
	private JTextField tbxApellido;
	private JTextField tbxNombre;
	private JTextField tbxEmail;
	private JTextField tbxNroDoc;
	private JTextField tbxTelefono;
	private JTextField tbxOcupacion;
	private JTextField tbxCuit;
	JDatePanelImpl datePanel;
	JDatePickerImpl datePicker;
	//String Getters And Setters For Personal Data
	public String getTbxApellidoStr() {
		if(this.tbxApellido != null){
			return this.tbxApellido.getText();
		}else{
			return null;
		}
	}
	public void setTbxApellidoStr(String tbxApellido) {
		this.tbxApellido.setText(tbxApellido);
	}
	public String getTbxNombreStr() {
		if(this.tbxNombre != null)
		{
			return this.tbxNombre.getText();
		}else{
			return null;
		}
	}
	public void setTbxNombre(String tbxNombre) {
		this.tbxNombre.setText(tbxNombre);
	}
	public String getTbxEmailStr() {
		if(this.tbxEmail != null)
		{
			return this.tbxEmail.getText();
		}else{
			return null;
		}
	}
	public void setTbxEmail(String tbxEmail) {
		this.tbxEmail.setText(tbxEmail);
	}
	public String getTbxNroDocStr() {
		if(this.tbxNroDoc != null)
		{
			return this.tbxNroDoc.getText();
		}else return null;
	}
	public void setTbxNroDoc(String tbxNroDoc) {
		this.tbxNroDoc.setText(tbxNroDoc);
	}
	public String getTbxTelefonoStr() {
		if(this.tbxTelefono != null)
		{
			return this.tbxTelefono.getText();
		}else return null;
	}
	public void setTbxTelefono(String tbxTelefono) {
		this.tbxTelefono.setText(tbxTelefono);
	}
	public String getTbxOcupacionStr() {
		if(this.tbxOcupacion != null)
		{
			return this.tbxOcupacion.getText();
		}else return null;
	}
	public void setTbxOcupacion(String tbxOcupacion) {
		this.tbxOcupacion.setText(tbxOcupacion);
	}
	public String getTbxCuitStr() {
		if(this.tbxCuit != null)
		{
			return this.tbxCuit.getText();
		}else return null;
	}
	public void setTbxCuit(String tbxCuit) {
		this.tbxCuit.setText(tbxCuit);
	}
	public LocalDate getDatePanelFechNac(){
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US);
		//format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
		LocalDate localDate = null;
		try {
			localDate = LocalDate.parse(this.datePicker.getJFormattedTextField().getText(),format);
		}catch (DateTimeParseException e){
			System.out.println("Date data could not be parsed");
		}finally {
			//todo
		}
		return localDate;
}
	public JDatePickerImpl getDatePicker() {
		return datePicker;
	}
	public void setDatePicker(JDatePickerImpl datePicker) {
		this.datePicker = datePicker;
	}
	//Getters For Personal Data
	public JTextField getTbxApellido() {
		return tbxApellido;
	}
	public JTextField getTbxNombre() {
		return tbxNombre;
	}
	public JTextField getTbxEmail() {
		return tbxEmail;
	}
	public JTextField getTbxNroDoc() {
		return tbxNroDoc;
	}
	public JTextField getTbxTelefono() {
		return tbxTelefono;
	}
	public JTextField getTbxOcupacion() {
		return tbxOcupacion;
	}
	public JTextField getTbxCuit() {
		return tbxCuit;
	}

	//Direction
	private JTextField tbxCalle;
	private JTextField tbxCodPostal;
	private JTextField tbxDireccionNro;
	private JTextField tbxDpto;
	private JTextField tbxPiso;
	//String Getters and Setters For Direction
	public String getTbxCalleStr() {
		if(this.tbxCalle != null)
		{
			return tbxCalle.getText();
		}else return null;
	}
	public void setTbxCalle(JTextField tbxCalle) {
		this.tbxCalle = tbxCalle;
	}
	public String getTbxCodPostalStr() {
		if(this.tbxCodPostal != null)
		{
			return tbxCodPostal.getText();
		}else return null;
	}
	public void setTbxCodPostal(JTextField tbxCodPostal) {
		this.tbxCodPostal = tbxCodPostal;
	}
	public String getTbxDireccionNroStr() {
		if(this.tbxDireccionNro != null)
		{
			return tbxDireccionNro.getText();
		}else return null;
	}
	public void setTbxDireccionNro(JTextField tbxDireccionNro) {
		this.tbxDireccionNro = tbxDireccionNro;
	}
	public String getTbxDptoStr() {
		if(this.tbxDpto != null)
		{
			return tbxDpto.getText();
		}else return null;
	}
	public void setTbxDpto(JTextField tbxDpto) {
		this.tbxDpto = tbxDpto;
	}
	public String getTbxPisoStr() {
		if(this.tbxPiso != null)
		{
			return tbxPiso.getText();
		}else return null;
	}
	public void setTbxPiso(JTextField tbxPiso) {
		this.tbxPiso = tbxPiso;
	}
	//Getters For Direction
	public JTextField getTbxCalle() {
		return tbxCalle;
	}
	public JTextField getTbxCodPostal() {
		return tbxCodPostal;
	}
	public JTextField getTbxDireccionNro() {
		return tbxDireccionNro;
	}
	public JTextField getTbxDpto() {
		return tbxDpto;
	}
	public JTextField getTbxPiso() {
		return tbxPiso;
	}

	//CheckBox
	JCheckBox checkDpto;
	//Getters and Setters For CheckBox
	public JCheckBox getCheckDpto() {
		return checkDpto;
	}
	public void setCheckDpto(JCheckBox checkDpto) {
		this.checkDpto = checkDpto;
	}

	//Combobox
	private JComboBox<String> cbxTDoc = new JComboBox<>();
	private JComboBox<String> cbxNacionalidad = new JComboBox<>();
	private JComboBox<String> cbxPais = new JComboBox<>();
	private JComboBox<String> cbxProvincia = new JComboBox<>();
	private JComboBox<String> cbxLocalidad = new JComboBox<>();
	private JComboBox<String> cbxIVA = new JComboBox<>();

	//getters and setters for Combobox
	public void setCbxTipoDNI(JComboBox<String> cbxTipoDNI) {this.cbxTDoc = cbxTipoDNI;}
	public void setCbxPais(JComboBox<String> cbxPais) {this.cbxPais = cbxPais;}
	public void setCbxProvincia(JComboBox<String> cbxProvincia) {this.cbxProvincia = cbxProvincia;}
	public void setCbxLocalidad(JComboBox<String> cbxLocalidad) {this.cbxLocalidad = cbxLocalidad;}
	public void setCbxIVA(JComboBox<String> cbxIVA) {this.cbxIVA = cbxIVA;}
	public JComboBox<String> getCbxTipoDNI(){return this.cbxTDoc;}
	public JComboBox<String> getCbxPais(){return this.cbxPais;}
	public JComboBox<String> getCbxProvincia(){return this.cbxProvincia;}
	public JComboBox<String> getCbxLocalidad(){return this.cbxLocalidad;}
	public JComboBox<String> getCbxTDoc() {
		return cbxTDoc;
	}
	public JComboBox<String> getCbxNacionalidad() {
		return cbxNacionalidad;
	}
	public JComboBox<String> getCbxIVA(){ return cbxIVA;}
	//Get Selected for Combobox
	public String getSelectedCbxIVA(){
		if(this.cbxIVA.getSelectedItem() != null)
		{
			return this.cbxIVA.getSelectedItem().toString();
		}else return null;
	}
	public String getSelectedCbxPais(){
		if(this.cbxPais.getSelectedItem() != null)
	{
		return this.cbxPais.getSelectedItem().toString();
	}else return null;
	}
	public String getSelectedCbxProvincia(){
		if(this.cbxProvincia.getSelectedItem() != null)
		{
			return this.cbxProvincia.getSelectedItem().toString();
		}else return null;
	}
	public String getSelectedCbxLocalidad(){
		if(this.cbxLocalidad.getSelectedItem() != null)
		{
			return this.cbxLocalidad.getSelectedItem().toString();
		}else return null;
	}
	public String getSelectedCbxTipoDNI(){
		if(this.cbxTDoc.getSelectedItem() != null)
		{
			return this.cbxTDoc.getSelectedItem().toString();
		}else return null;
	}
	public String getSelectedCbxNacionalidad(){
		if(this.cbxNacionalidad.getSelectedItem() != null)
		{
			return this.cbxNacionalidad.getSelectedItem().toString();
		}else return null;
	}

	//Labels
	private JLabel lblDpto;
	private JLabel lblPiso;
	private JLabel lblApellido;
	private JLabel lblNacionalidad;
	private JLabel lblNombre;
	private JLabel lblFecNac;
	private JLabel lblTipo;
	private JLabel lblEmail;
	private JLabel lblNroDni;
	private JLabel lblTelefono;
	private JLabel lblOcupacion;
	private JLabel lblCuit;
	private JLabel lblTIVA;
	private JLabel lblPais;
	private JLabel lblProvincia;
	private JLabel lblLocalidad;
	private JLabel lblCalle;
	private JLabel lblCP;
	private JLabel lblDirNumero;
	//Getters and setters for labels
	public JLabel getLblDpto() {
		return lblDpto;
	}
	public JLabel getLblPiso() {
		return lblPiso;
	}
	public JLabel getLblApellido() {
		return lblApellido;
	}
	public JLabel getLblNacionalidad() {
		return lblNacionalidad;
	}
	public JLabel getLblNombre() {
		return lblNombre;
	}
	public JLabel getLblFecNac() {
		return lblFecNac;
	}
	public JLabel getLblTipo() {
		return lblTipo;
	}
	public JLabel getLblEmail() {
		return lblEmail;
	}
	public JLabel getLblNroDni() {
		return lblNroDni;
	}
	public JLabel getLblTelefono() {
		return lblTelefono;
	}
	public JLabel getLblOcupacion() {
		return lblOcupacion;
	}
	public JLabel getLblCuit() {
		return lblCuit;
	}
	public JLabel getLblTIVA() {
		return lblTIVA;
	}
	public JLabel getLblPais() {
		return lblPais;
	}
	public JLabel getLblProvincia() {
		return lblProvincia;
	}
	public JLabel getLblLocalidad() {
		return lblLocalidad;
	}
	public JLabel getLblCalle() {
		return lblCalle;
	}
	public JLabel getLblCP() {
		return lblCP;
	}
	public JLabel getLblDirNumero() {
		return lblDirNumero;
	}


	//Controller
	private DarAltaController controller;
	//Setter For Controller
	public void setController(DarAltaController unController) { controller = unController;
	}

	public AltaPasajeroGUI() {
		this.setVisible(false);

		controller = new DarAltaController(this);
		getContentPane().setLayout(null);
		this.setSize(1000, 500);
		this.controller.cargarTDNI();
		
		JPanel datosPersonales = datosPersonales();
		getContentPane().add(datosPersonales);

		JPanel direccion = direccion();
		direccion.setBounds(539, 28, 426, 277);
		getContentPane().add(direccion);
		
		JLabel lblcampoObl = new JLabel("(*) Campos Obligatorios");
		lblcampoObl.setForeground(Warning);
		lblcampoObl.setBounds(29, 363, 136, 14);
		getContentPane().add(lblcampoObl);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(CancelButton);
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(721, 375, 117, 36);
		btnCancelar.addActionListener(e1 ->{
				controller.mensajeAceptarCancelarGUI();
		});
		getContentPane().add(btnCancelar);
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setForeground(Color.WHITE);
		btnSiguiente.setBackground(NextButton);
		btnSiguiente.setBounds(848, 375, 117, 36);
		getContentPane().add(btnSiguiente);

		btnSiguiente.addActionListener(e -> {
				controller.InformarOmisionnesDatosGUI();
		});




		this.setVisible(true);
			
	}
	
	private JPanel datosPersonales() {
		JPanel datosPersonales = new JPanel();
		datosPersonales.setLocation(29, 28);
		datosPersonales.setLayout(null);
		datosPersonales.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		lblApellido = new JLabel("Apellido(s)");
		lblApellido.setBounds(10, 33, 60, 14);
		datosPersonales.add(lblApellido);
		
		JLabel lblAsteriscoA = new JLabel("(*)");
		lblAsteriscoA.setForeground(Warning);
		lblAsteriscoA.setBounds(77, 33, 15, 14);
		datosPersonales.add(lblAsteriscoA);
		
		lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setBounds(10, 76, 82, 14);
		datosPersonales.add(lblNacionalidad);
		
		tbxApellido = new JTextField();
		tbxApellido.setBounds(102, 30, 140, 20);
		datosPersonales.add(tbxApellido);
		tbxApellido.setColumns(10);
		
		cbxNacionalidad = new JComboBox<String>();
		cbxNacionalidad.setBounds(135, 71, 107, 24);
		controller.cargarNacionalidad();
		datosPersonales.add(cbxNacionalidad);
		
		JLabel lblAsteriscoNac = new JLabel("(*)");
		lblAsteriscoNac.setForeground(Warning);
		lblAsteriscoNac.setBounds(94, 76, 15, 14);
		datosPersonales.add(lblAsteriscoNac);
		
		lblNombre = new JLabel("Nombre(s)");
		lblNombre.setBounds(261, 33, 59, 14);
		datosPersonales.add(lblNombre);
		
		JLabel lblAsteriscoN = new JLabel("(*)");
		lblAsteriscoN.setForeground(Warning);
		lblAsteriscoN.setBounds(323, 33, 15, 14);
		datosPersonales.add(lblAsteriscoN);
		
		tbxNombre = new JTextField();
		tbxNombre.setColumns(10);
		tbxNombre.setBounds(348, 30, 122, 20);
		datosPersonales.add(tbxNombre);
		
		lblFecNac = new JLabel("Fecha de Nacimiento");
		lblFecNac.setBounds(10, 120, 122, 14);
		datosPersonales.add(lblFecNac);
		
		//Agregar DatePicker
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Hoy");
		p.put("text.month", "Mes");
		p.put("text.year", "Ano");
		datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(132, 115, 110, 30);
		datosPersonales.add(datePicker);
		
		JPanel panelDocimento = new JPanel();
		panelDocimento.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Documento       ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDocimento.setBounds(261, 71, 209, 104);
		datosPersonales.add(panelDocimento);
		panelDocimento.setLayout(null);


		controller.cargarTDNI();
		cbxTDoc.setBounds(83, 21, 86, 24);
		panelDocimento.add(cbxTDoc);

		
		lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 26, 30, 14);
		panelDocimento.add(lblTipo);
		
		lblNroDni = new JLabel("Numero");
		lblNroDni.setBounds(10, 62, 46, 14);
		panelDocimento.add(lblNroDni);
		
		JLabel lblAsteriscoDNI = new JLabel("(*)");
		lblAsteriscoDNI.setForeground(Warning);
		lblAsteriscoDNI.setBounds(57, 62, 15, 14);
		panelDocimento.add(lblAsteriscoDNI);
		
		tbxNroDoc = new JTextField();
		tbxNroDoc.setBounds(83, 59, 86, 20);
		panelDocimento.add(tbxNroDoc);
		tbxNroDoc.setColumns(10);
		
		JLabel lblAsteriscoDoc = new JLabel("(*)");
		lblAsteriscoDoc.setBounds(80, 0, 15, 14);
		panelDocimento.add(lblAsteriscoDoc);
		lblAsteriscoDoc.setForeground(Warning);
		
		lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(10, 157, 46, 14);
		datosPersonales.add(lblEmail);
		
		tbxEmail = new JTextField();
		tbxEmail.setBounds(62, 155, 180, 20);
		datosPersonales.add(tbxEmail);
		tbxEmail.setColumns(10);
		
		lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 198, 60, 14);
		datosPersonales.add(lblTelefono);
		
		JLabel lblAsteriscoT = new JLabel("(*)");
		lblAsteriscoT.setForeground(Warning);
		lblAsteriscoT.setBounds(77, 198, 15, 14);
		datosPersonales.add(lblAsteriscoT);
		
		tbxTelefono = new JTextField();
		tbxTelefono.setBounds(92, 196, 150, 20);
		datosPersonales.add(tbxTelefono);
		tbxTelefono.setColumns(10);
		
		lblOcupacion = new JLabel("Ocupacion");
		lblOcupacion.setBounds(260, 198, 78, 14);
		datosPersonales.add(lblOcupacion);
		
		JLabel lblAsteriscoO = new JLabel("(*)");
		lblAsteriscoO.setForeground(Warning);
		lblAsteriscoO.setBounds(330, 198, 15, 14);
		datosPersonales.add(lblAsteriscoO);
		
		tbxOcupacion = new JTextField();
		tbxOcupacion.setBounds(348, 196, 122, 20);
		datosPersonales.add(tbxOcupacion);
		tbxOcupacion.setColumns(10);
		
		lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(10, 248, 60, 14);
		datosPersonales.add(lblCuit);
		
		tbxCuit = new JTextField();
		tbxCuit.setBounds(92, 246, 150, 20);
		datosPersonales.add(tbxCuit);
		tbxCuit.setColumns(10);
		
		lblTIVA = new JLabel("Tipo IVA");
		lblTIVA.setBounds(261, 248, 46, 14);
		datosPersonales.add(lblTIVA);
		

		cbxIVA.setBounds(320, 243, 150, 24);
		controller.cargarIVA();
		datosPersonales.add(cbxIVA);


		datosPersonales.setSize(500, 300);
		return datosPersonales;
	}
	
	private JPanel direccion() {
		JPanel direccion = new JPanel();
		direccion.setLayout(null);
		direccion.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Direccion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		lblPais = new JLabel("Pais");
		lblPais.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPais.setBounds(25, 40, 46, 14);
		direccion.add(lblPais);
		


		cbxPais.setBounds(81, 35, 130, 24);
		direccion.add(cbxPais);
		controller.cargarPais();
		ActionListener cbxPaisActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbxPais.getSelectedItem() != null && cbxPais.getSelectedItem().toString() != "") {
					cbxProvincia.setEnabled(true);
					controller.cargarProvincia();
					cbxLocalidad.removeAllItems();
			}else
			{
				cbxProvincia.setEnabled(false);
				cbxLocalidad.setEnabled(false);
				cbxProvincia.removeAllItems();
				cbxLocalidad.removeAllItems();}
			}
		};
		cbxPais.addActionListener(cbxPaisActionListener);


		lblProvincia = new JLabel("Provincia");
		lblProvincia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProvincia.setBounds(14, 76, 57, 14);
		direccion.add(lblProvincia);

		//controller.cargarProvincia();
		//JComboBox<String> cbxProvincia = new JComboBox<String>();
		cbxProvincia.setBounds(81, 71, 130, 24);
		cbxProvincia.setEnabled(false);
		direccion.add(cbxProvincia);
		ActionListener CbxProvinciaActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbxProvincia.getSelectedItem() != null && cbxProvincia.getSelectedItem().toString() != "") {
					cbxLocalidad.setEnabled(true);
					controller.cargarLocalidad();
				}else {
					cbxLocalidad.setEnabled(false);
					cbxLocalidad.removeAllItems();
					//todo evitar que se quite la seleccion si la seleccion superior es re-seleccionada
				}
			}
		};
		cbxProvincia.addActionListener(CbxProvinciaActionListener);

		//controller.cargarLocalidad();
		//JComboBox<String> cbxLocalidad = new JComboBox<String>();
		cbxLocalidad.setBounds(81, 106, 130, 24);
		cbxLocalidad.setEnabled(false);
		ActionListener cbxLocalidadActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbxLocalidad.getSelectedItem() != null && cbxLocalidad.getSelectedItem().toString() != "") {
						cbxLocalidad.getSelectedItem();
						tbxCodPostal.setText(controller.getCodigoPostal());
						tbxCodPostal.setEnabled(false);
				}else{
					tbxCodPostal.setEnabled(true);
						tbxCodPostal.setText("");
				}
			}
		};
		cbxLocalidad.addActionListener(cbxLocalidadActionListener);
		direccion.add(cbxLocalidad);
		
		lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLocalidad.setBounds(14, 111, 57, 14);
		direccion.add(lblLocalidad);
		
		tbxCalle = new JTextField();
		tbxCalle.setBounds(81, 141, 130, 20);
		direccion.add(tbxCalle);
		tbxCalle.setColumns(10);
		
		lblCalle = new JLabel("Calle");
		lblCalle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCalle.setBounds(14, 144, 46, 14);
		direccion.add(lblCalle);
		
		JLabel lblAsterisco = new JLabel("(*)");
		lblAsterisco.setForeground(Warning);
		lblAsterisco.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsterisco.setBounds(62, 144, 14, 14);
		direccion.add(lblAsterisco);
		
		lblCP = new JLabel("CP");
		lblCP.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCP.setBounds(230, 111, 46, 14);
		direccion.add(lblCP);
		
		JLabel lblAsterisco1 = new JLabel("(*)");
		lblAsterisco1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsterisco1.setForeground(Warning);
		lblAsterisco1.setBounds(279, 111, 14, 14);
		direccion.add(lblAsterisco1);
		
		tbxCodPostal = new JTextField();
		tbxCodPostal.setBounds(303, 108, 87, 20);
		direccion.add(tbxCodPostal);
		tbxCodPostal.setColumns(10);
		
		lblDirNumero = new JLabel("Numero");
		lblDirNumero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDirNumero.setBounds(231, 144, 46, 14);
		direccion.add(lblDirNumero);
		
		JLabel lblAsterisco2 = new JLabel("(*)");
		lblAsterisco2.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsterisco2.setForeground(Warning);
		lblAsterisco2.setBounds(279, 144, 14, 14);
		direccion.add(lblAsterisco2);
		
		tbxDireccionNro = new JTextField();
		tbxDireccionNro.setBounds(304, 141, 86, 20);
		direccion.add(tbxDireccionNro);
		tbxDireccionNro.setColumns(10);
		
		checkDpto = new JCheckBox("Vive en departamento");
		checkDpto.setBounds(127, 181, 153, 23);
		checkDpto.addActionListener(e -> {
			if(checkDpto.isSelected()) {
				lblDpto.setEnabled(true);
				lblDpto.setForeground(null);
				lblDpto.updateUI();
				tbxDpto.setEnabled(true);
				tbxDpto.setBorder(null);
				tbxDpto.updateUI();
				lblPiso.setEnabled(true);
				lblPiso.setForeground(null);
				lblPiso.updateUI();
				tbxPiso.setEnabled(true);
				tbxPiso.setBorder(null);
				tbxPiso.updateUI();
			}
			else {
				lblDpto.setEnabled(false);
				lblDpto.setForeground(null);
				lblDpto.updateUI();
				tbxDpto.setEnabled(false);
				tbxDpto.setBorder(null);
				tbxDpto.updateUI();
				lblPiso.setEnabled(false);
				lblPiso.setForeground(null);
				lblPiso.updateUI();
				tbxPiso.setEnabled(false);
				tbxPiso.setBorder(null);
				tbxPiso.updateUI();			}
		});
		direccion.add(checkDpto);
		
		lblDpto = new JLabel("Departamento");
		lblDpto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDpto.setEnabled(false);
		lblDpto.setBounds(127, 211, 84, 14);
		direccion.add(lblDpto);
		
		tbxDpto = new JTextField();
		tbxDpto.setEnabled(false);
		tbxDpto.setBounds(221, 208, 46, 20);
		direccion.add(tbxDpto);
		tbxDpto.setColumns(10);
		
		lblPiso = new JLabel("Piso");
		lblPiso.setEnabled(false);
		lblPiso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPiso.setBounds(165, 237, 46, 14);
		direccion.add(lblPiso);
		
		tbxPiso = new JTextField();
		tbxPiso.setEnabled(false);
		tbxPiso.setBounds(221, 234, 46, 20);
		direccion.add(tbxPiso);
		tbxPiso.setColumns(10);
		
		return direccion;
	}

	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
				detalle,titulo,
				JOptionPane.ERROR_MESSAGE);
	}

	public int optionMessageGUI(String titulo, String detalle, Object[] options){
		JFrame padre = (JFrame) SwingUtilities.getWindowAncestor(this);
		return JOptionPane.showOptionDialog(padre,
				detalle,titulo,JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,     //do not use a custom Icon
				options,  //the titles of buttons
				options[0]);
	}
}

