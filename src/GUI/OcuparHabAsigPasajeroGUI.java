package GUI;

import javax.swing.*;

import Controller.OcuparController;
import java.awt.Color;


import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class OcuparHabAsigPasajeroGUI extends JFrame{
	
	private OcuparController controller;
	private JComboBox<String> cbxTipoDNI;	
	private JTextField tbxApellido;
	private JTextField tbxNombre;
	private JTextField tbxNDoc;
	private JButton btnSiguiente;
	private ActionListener SiguienteAction;

	@SuppressWarnings("unused")
	private DefaultTableModel model;


	public JButton getBtnSiguiente() {
		return btnSiguiente;
	}
	public ActionListener getSiguienteAction() {
		return SiguienteAction;
	}
	public void setSiguienteAction(ActionListener siguienteAction) {
		SiguienteAction = siguienteAction;
	}

	public OcuparHabAsigPasajeroGUI(OcuparController unController) {

	
		this.controller = unController;
		controller.setBuscarOcuparGUI(this);

		this.controller.cargarTDNI();
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setSize(500,300);		
		
		pantallaDatos();
	}
	
	public void pantallaDatos() {
		JPanel panelDatos = new JPanel();
		panelDatos.setBorder(new TitledBorder(null, "Datos del Pasajero", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDatos.setBounds(42, 29, 402, 160);
		getContentPane().add(panelDatos);
		panelDatos.setLayout(null);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(38, 39, 50, 15);
		panelDatos.add(lblApellido);
		
		tbxApellido = new JTextField();
		tbxApellido.setBounds(95, 36, 270, 20);
		panelDatos.add(tbxApellido);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(38, 67, 50, 15);
		panelDatos.add(lblNombre);
				
		tbxNombre = new JTextField();
		tbxNombre.setBounds(95, 65, 270, 20);
		panelDatos.add(tbxNombre);
		
		JLabel lblDocumentoTipo = new JLabel("Documento Tipo");
		lblDocumentoTipo.setBounds(38, 97, 100, 15);
		panelDatos.add(lblDocumentoTipo);
		
		cbxTipoDNI.setBounds(140, 97, 87, 25);
		panelDatos.add(cbxTipoDNI);
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setBounds(230, 97, 50, 15);
		panelDatos.add(lblNumero);
		
		tbxNDoc = new JTextField();
		tbxNDoc.setBounds(280, 94, 85, 20);
		panelDatos.add(tbxNDoc);
		tbxNDoc.setColumns(10);
		
		
		//Buttons
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(243, 200, 90, 25);
		getContentPane().add(btnCancelar);
		btnCancelar.setBackground(Color.RED);
		btnCancelar.setForeground(Color.WHITE);
		
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBounds(345, 200, 100, 25);
		getContentPane().add(btnSiguiente);
		btnSiguiente.setBackground(new Color(0, 128, 0));
		btnSiguiente.setForeground(Color.WHITE);

		//Actions

		SiguienteAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		};

		btnSiguiente.addActionListener(SiguienteAction);

		btnCancelar.addActionListener(e -> dispose());
	}
	
	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}

	//Getters and Setters
	public JTextField getTbxNombre() {return tbxNombre;}
	public void setTbxNombre(JTextField tbxNombre) {this.tbxNombre = tbxNombre;}
	public JTextField getTbxApellido() {return tbxApellido;}
	public void setTbxApellido(JTextField tbxApellido) {this.tbxApellido = tbxApellido;}
	public JComboBox<String> getCbxTipoDNI() {return cbxTipoDNI;}
	public void setCbxTipoDNI(JComboBox<String> cbxTipoDNI) {this.cbxTipoDNI = cbxTipoDNI;}
	public JTextField getTbxNDoc() {return tbxNDoc;}
	public void setTbxNDoc(JTextField tbxNDoc) {this.tbxNDoc = tbxNDoc;}
}
