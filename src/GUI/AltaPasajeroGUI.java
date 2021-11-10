package GUI;

import Controller.PasajeroController;
import modelosTabla.DateLabelFormatter;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Properties;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JButton;

public class AltaPasajeroGUI extends JFrame{
	//Personal data
	private JTextField tbxApellido;
	private JTextField tbxNombre;
	private JTextField tbxEmail;
	private JTextField tbxNroDoc;
	private JTextField tbxTelefono;
	private JTextField tbxOcupacion;
	private JTextField tbxCuit;
	
	//Direction
	private JTextField tbxCalle;
	private JTextField tbxCodPostal;
	private JTextField tbxDireccionNro;
	private JTextField tbxDpto;
	private JTextField tbxPiso;
	private JLabel lblDpto;
	private JLabel lblPiso;

	private PasajeroController controller;
	
	public AltaPasajeroGUI() {
		this.setLayout(null);
		this.setSize(1000, 500);
		
		JPanel datosPersonales = datosPersonales();
		this.add(datosPersonales);

		JPanel direccion = direccion();
		direccion.setBounds(539, 28, 426, 277);
		this.add(direccion);
		
		JLabel lblcampoObl = new JLabel("(*) Campos Obligatorios");
		lblcampoObl.setForeground(Color.RED);
		lblcampoObl.setBounds(29, 363, 136, 14);
		this.add(lblcampoObl);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(255, 0, 0));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(721, 375, 117, 36);
		this.add(btnCancelar);
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setForeground(new Color(255, 255, 255));
		btnSiguiente.setBackground(new Color(0, 128, 0));
		btnSiguiente.setBounds(848, 375, 117, 36);
		this.add(btnSiguiente);
		
		
		this.setVisible(true);
			
	}
	
	private JPanel datosPersonales() {
		JPanel datosPersonales = new JPanel();
		datosPersonales.setLocation(29, 28);
		datosPersonales.setLayout(null);
		datosPersonales.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblApellido = new JLabel("Apellido(s)");
		lblApellido.setBounds(25, 33, 60, 14);
		datosPersonales.add(lblApellido);
		
		JLabel lblAsteriscoA = new JLabel("(*)");
		lblAsteriscoA.setForeground(new Color(255, 0, 0));
		lblAsteriscoA.setBounds(87, 33, 15, 14);
		datosPersonales.add(lblAsteriscoA);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setBounds(25, 76, 77, 14);
		datosPersonales.add(lblNacionalidad);
		
		tbxApellido = new JTextField();
		tbxApellido.setBounds(102, 30, 140, 20);
		datosPersonales.add(tbxApellido);
		tbxApellido.setColumns(10);
		
		JComboBox<String> cbxNacionalidad = new JComboBox<String>();
		cbxNacionalidad.setBounds(135, 71, 107, 24);
		cbxNacionalidad.addItem("Argentino");
		datosPersonales.add(cbxNacionalidad);
		
		JLabel lblAsteriscoNac = new JLabel("(*)");
		lblAsteriscoNac.setForeground(new Color(255, 0, 0));
		lblAsteriscoNac.setBounds(102, 76, 15, 14);
		datosPersonales.add(lblAsteriscoNac);
		
		JLabel lblNombre = new JLabel("Nombre(s)");
		lblNombre.setBounds(252, 33, 60, 14);
		datosPersonales.add(lblNombre);
		
		JLabel lblAsteriscoN = new JLabel("(*)");
		lblAsteriscoN.setForeground(new Color(255, 0, 0));
		lblAsteriscoN.setBounds(315, 33, 15, 14);
		datosPersonales.add(lblAsteriscoN);
		
		tbxNombre = new JTextField();
		tbxNombre.setColumns(10);
		tbxNombre.setBounds(330, 30, 140, 20);
		datosPersonales.add(tbxNombre);
		
		JLabel lblFecNac = new JLabel("Fecha de Nacimiento");
		lblFecNac.setBounds(25, 125, 122, 14);
		datosPersonales.add(lblFecNac);
		
		//Agregar DatePicker
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Hoy");
		p.put("text.month", "Mes");
		p.put("text.year", "Año");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(150, 120, 110, 30);
		datosPersonales.add(datePicker);
		
		
		JPanel panelDocimento = new JPanel();
		panelDocimento.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Documento       ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDocimento.setBounds(261, 71, 209, 104);
		datosPersonales.add(panelDocimento);
		panelDocimento.setLayout(null);
		
		JComboBox<String> cbxTDoc = new JComboBox<String>();
		cbxTDoc.setBounds(83, 21, 86, 24);
		cbxTDoc.addItem("Pasaporte");
		panelDocimento.add(cbxTDoc);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 26, 30, 14);
		panelDocimento.add(lblTipo);
		
		JLabel lblNroDni = new JLabel("Número");
		lblNroDni.setBounds(10, 62, 46, 14);
		panelDocimento.add(lblNroDni);
		
		JLabel lblAsteriscoDNI = new JLabel("(*)");
		lblAsteriscoDNI.setForeground(Color.RED);
		lblAsteriscoDNI.setBounds(57, 62, 15, 14);
		panelDocimento.add(lblAsteriscoDNI);
		
		tbxNroDoc = new JTextField();
		tbxNroDoc.setBounds(83, 59, 86, 20);
		panelDocimento.add(tbxNroDoc);
		tbxNroDoc.setColumns(10);
		
		JLabel lblAsteriscoDoc = new JLabel("(*)");
		lblAsteriscoDoc.setBounds(80, 0, 15, 14);
		panelDocimento.add(lblAsteriscoDoc);
		lblAsteriscoDoc.setForeground(Color.RED);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(25, 161, 46, 14);
		datosPersonales.add(lblEmail);
		
		tbxEmail = new JTextField();
		tbxEmail.setBounds(72, 155, 140, 20);
		datosPersonales.add(tbxEmail);
		tbxEmail.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setBounds(25, 198, 60, 14);
		datosPersonales.add(lblTelefono);
		
		JLabel lblAsteriscoT = new JLabel("(*)");
		lblAsteriscoT.setForeground(Color.RED);
		lblAsteriscoT.setBounds(80, 198, 15, 14);
		datosPersonales.add(lblAsteriscoT);
		
		tbxTelefono = new JTextField();
		tbxTelefono.setBounds(102, 195, 122, 20);
		datosPersonales.add(tbxTelefono);
		tbxTelefono.setColumns(10);
		
		JLabel lblOcupacion = new JLabel("Ocupación");
		lblOcupacion.setBounds(242, 198, 70, 14);
		datosPersonales.add(lblOcupacion);
		
		JLabel lblAsteriscoO = new JLabel("(*)");
		lblAsteriscoO.setForeground(Color.RED);
		lblAsteriscoO.setBounds(310, 198, 15, 14);
		datosPersonales.add(lblAsteriscoO);
		
		tbxOcupacion = new JTextField();
		tbxOcupacion.setBounds(330, 195, 130, 20);
		datosPersonales.add(tbxOcupacion);
		tbxOcupacion.setColumns(10);
		
		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(25, 249, 28, 14);
		datosPersonales.add(lblCuit);
		
		tbxCuit = new JTextField();
		tbxCuit.setBounds(63, 246, 157, 20);
		datosPersonales.add(tbxCuit);
		tbxCuit.setColumns(10);
		
		JLabel lblTIVA = new JLabel("Tipo IVA");
		lblTIVA.setBounds(242, 249, 60, 14);
		datosPersonales.add(lblTIVA);
		
		JComboBox<String> cbxIVA = new JComboBox<String>();
		cbxIVA.setBounds(294, 244, 176, 24);
		cbxIVA.addItem("Responsable No Inscripto");
		datosPersonales.add(cbxIVA);
		
		datosPersonales.setSize(500, 300);
		return datosPersonales;
	}
	
	private JPanel direccion() {
		JPanel direccion = new JPanel();
		direccion.setLayout(null);
		direccion.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Direccion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblPais = new JLabel("País");
		lblPais.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPais.setBounds(25, 40, 46, 14);
		direccion.add(lblPais);
		
		JComboBox<String> cbxPais = new JComboBox<String>();
		cbxPais.setBounds(81, 35, 130, 24);
		cbxPais.addItem("Argentina");
		direccion.add(cbxPais);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProvincia.setBounds(14, 76, 57, 14);
		direccion.add(lblProvincia);
		
		JComboBox<String> cbxProvincia = new JComboBox<String>();
		cbxProvincia.setBounds(81, 71, 130, 24);
		cbxProvincia.addItem("Entre Rios");
		direccion.add(cbxProvincia);
		
		JComboBox<String> cbxLocalidad = new JComboBox<String>();
		cbxLocalidad.setBounds(81, 106, 130, 24);
		cbxLocalidad.addItem("Parana");
		direccion.add(cbxLocalidad);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLocalidad.setBounds(14, 111, 57, 14);
		direccion.add(lblLocalidad);
		
		tbxCalle = new JTextField();
		tbxCalle.setBounds(81, 141, 130, 20);
		direccion.add(tbxCalle);
		tbxCalle.setColumns(10);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCalle.setBounds(14, 144, 46, 14);
		direccion.add(lblCalle);
		
		JLabel lblAsterisco = new JLabel("(*)");
		lblAsterisco.setForeground(Color.RED);
		lblAsterisco.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsterisco.setBounds(62, 144, 14, 14);
		direccion.add(lblAsterisco);
		
		JLabel lblCP = new JLabel("CP");
		lblCP.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCP.setBounds(230, 111, 46, 14);
		direccion.add(lblCP);
		
		JLabel lblAsterisco1 = new JLabel("(*)");
		lblAsterisco1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsterisco1.setForeground(Color.RED);
		lblAsterisco1.setBounds(279, 111, 14, 14);
		direccion.add(lblAsterisco1);
		
		tbxCodPostal = new JTextField();
		tbxCodPostal.setBounds(303, 108, 87, 20);
		direccion.add(tbxCodPostal);
		tbxCodPostal.setColumns(10);
		
		JLabel lblNumero = new JLabel("Número");
		lblNumero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumero.setBounds(231, 144, 46, 14);
		direccion.add(lblNumero);
		
		JLabel lblAsterisco2 = new JLabel("(*)");
		lblAsterisco2.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsterisco2.setForeground(Color.RED);
		lblAsterisco2.setBounds(279, 144, 14, 14);
		direccion.add(lblAsterisco2);
		
		tbxDireccionNro = new JTextField();
		tbxDireccionNro.setBounds(304, 141, 86, 20);
		direccion.add(tbxDireccionNro);
		tbxDireccionNro.setColumns(10);
		
		JCheckBox checkDpto = new JCheckBox("Vive en departamento");
		checkDpto.setBounds(127, 181, 153, 23);
		checkDpto.addActionListener(e -> {
			if(checkDpto.isSelected()) {
				lblDpto.setEnabled(true);
				tbxDpto.setEnabled(true);
				lblPiso.setEnabled(true);
				tbxPiso.setEnabled(true);
			}
			else {
				lblDpto.setEnabled(false);
				tbxDpto.setEnabled(false);
				lblPiso.setEnabled(false);
				tbxPiso.setEnabled(false);
			}
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

	public void setController(PasajeroController unController) {
		controller = unController;
	}
}
