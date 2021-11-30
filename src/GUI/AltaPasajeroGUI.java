package GUI;

import Controller.DarAltaController;
import Exceptions.DuplicateDocNumberException;
import modelosTabla.DateLabelFormatter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


@SuppressWarnings("serial")
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
	private JComboBox<String> cbxTDoc;
	private JComboBox<String> cbxNacionalidad; 

	private DarAltaController controller;

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
		lblcampoObl.setForeground(Color.RED);
		lblcampoObl.setBounds(29, 363, 136, 14);
		getContentPane().add(lblcampoObl);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(255, 0, 0));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBounds(721, 375, 117, 36);
		getContentPane().add(btnCancelar);
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setForeground(new Color(255, 255, 255));
		btnSiguiente.setBackground(new Color(0, 128, 0));
		btnSiguiente.setBounds(848, 375, 117, 36);
		getContentPane().add(btnSiguiente);

		btnSiguiente.addActionListener(e -> {
			try {
				controller.revisarDocExistente(tbxNroDoc.getText(), cbxTDoc.getSelectedItem().toString());
			} catch (DuplicateDocNumberException e1) {
				e1.printStackTrace();
				mostrarError("No Concordancia", "No existe ninguna concordancia segun los criterios de busqueda");
			} catch (Exception e1) {
				System.out.println("Es en el try de gestion pasajero");
				e1.printStackTrace();

			}
		});

		this.setVisible(true);
			
	}
	
	private JPanel datosPersonales() {
		JPanel datosPersonales = new JPanel();
		datosPersonales.setLocation(29, 28);
		datosPersonales.setLayout(null);
		datosPersonales.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblApellido = new JLabel("Apellido(s)");
		lblApellido.setBounds(10, 33, 60, 14);
		datosPersonales.add(lblApellido);
		
		JLabel lblAsteriscoA = new JLabel("(*)");
		lblAsteriscoA.setForeground(new Color(255, 0, 0));
		lblAsteriscoA.setBounds(77, 33, 15, 14);
		datosPersonales.add(lblAsteriscoA);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setBounds(10, 76, 82, 14);
		datosPersonales.add(lblNacionalidad);
		
		tbxApellido = new JTextField();
		tbxApellido.setBounds(102, 30, 140, 20);
		datosPersonales.add(tbxApellido);
		tbxApellido.setColumns(10);
		
		cbxNacionalidad = new JComboBox<String>();
		cbxNacionalidad.setBounds(135, 71, 107, 24);
		llenarListaNacionalidad();
		datosPersonales.add(cbxNacionalidad);
		
		JLabel lblAsteriscoNac = new JLabel("(*)");
		lblAsteriscoNac.setForeground(new Color(255, 0, 0));
		lblAsteriscoNac.setBounds(94, 76, 15, 14);
		datosPersonales.add(lblAsteriscoNac);
		
		JLabel lblNombre = new JLabel("Nombre(s)");
		lblNombre.setBounds(261, 33, 59, 14);
		datosPersonales.add(lblNombre);
		
		JLabel lblAsteriscoN = new JLabel("(*)");
		lblAsteriscoN.setForeground(new Color(255, 0, 0));
		lblAsteriscoN.setBounds(323, 33, 15, 14);
		datosPersonales.add(lblAsteriscoN);
		
		tbxNombre = new JTextField();
		tbxNombre.setColumns(10);
		tbxNombre.setBounds(348, 30, 122, 20);
		datosPersonales.add(tbxNombre);
		
		JLabel lblFecNac = new JLabel("Fecha de Nacimiento");
		lblFecNac.setBounds(10, 120, 122, 14);
		datosPersonales.add(lblFecNac);
		
		//Agregar DatePicker
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Hoy");
		p.put("text.month", "Mes");
		p.put("text.year", "Ano");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
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

		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 26, 30, 14);
		panelDocimento.add(lblTipo);
		
		JLabel lblNroDni = new JLabel("Numero");
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
		lblEmail.setBounds(10, 157, 46, 14);
		datosPersonales.add(lblEmail);
		
		tbxEmail = new JTextField();
		tbxEmail.setBounds(62, 155, 180, 20);
		datosPersonales.add(tbxEmail);
		tbxEmail.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 198, 60, 14);
		datosPersonales.add(lblTelefono);
		
		JLabel lblAsteriscoT = new JLabel("(*)");
		lblAsteriscoT.setForeground(Color.RED);
		lblAsteriscoT.setBounds(77, 198, 15, 14);
		datosPersonales.add(lblAsteriscoT);
		
		tbxTelefono = new JTextField();
		tbxTelefono.setBounds(92, 196, 150, 20);
		datosPersonales.add(tbxTelefono);
		tbxTelefono.setColumns(10);
		
		JLabel lblOcupacion = new JLabel("Ocupacion");
		lblOcupacion.setBounds(260, 198, 78, 14);
		datosPersonales.add(lblOcupacion);
		
		JLabel lblAsteriscoO = new JLabel("(*)");
		lblAsteriscoO.setForeground(Color.RED);
		lblAsteriscoO.setBounds(330, 198, 15, 14);
		datosPersonales.add(lblAsteriscoO);
		
		tbxOcupacion = new JTextField();
		tbxOcupacion.setBounds(348, 196, 122, 20);
		datosPersonales.add(tbxOcupacion);
		tbxOcupacion.setColumns(10);
		
		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(10, 248, 60, 14);
		datosPersonales.add(lblCuit);
		
		tbxCuit = new JTextField();
		tbxCuit.setBounds(92, 246, 150, 20);
		datosPersonales.add(tbxCuit);
		tbxCuit.setColumns(10);
		
		JLabel lblTIVA = new JLabel("Tipo IVA");
		lblTIVA.setBounds(261, 248, 46, 14);
		datosPersonales.add(lblTIVA);
		
		JComboBox<String> cbxIVA = new JComboBox<String>();
		cbxIVA.setBounds(320, 243, 150, 24);
		cbxIVA.addItem("Responsable No Inscripto");
		datosPersonales.add(cbxIVA);


		datosPersonales.setSize(500, 300);
		return datosPersonales;
	}
	
	private JPanel direccion() {
		JPanel direccion = new JPanel();
		direccion.setLayout(null);
		direccion.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Direccion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblPais = new JLabel("Pais");
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
		
		JLabel lblNumero = new JLabel("Numero");
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


	public void setController(DarAltaController unController) { controller = unController;
	}

	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
				detalle,titulo,
				JOptionPane.ERROR_MESSAGE);
	}
	public void setCbxTipoDNI(JComboBox<String> cbxTipoDNI) {this.cbxTDoc = cbxTipoDNI;}
	
	public void llenarListaNacionalidad() {
		List<String> ListNacionalidades = new ArrayList<>();
		ListNacionalidades = controller.cargarNacionalidadGUI();
		for(int i=0; i<ListNacionalidades.size(); i++) {
			this.cbxNacionalidad.addItem(ListNacionalidades.get(i));
		}
	}
}
