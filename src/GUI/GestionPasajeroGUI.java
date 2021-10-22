package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.PasajeroController;
import Exceptions.NoConcordanciaException;

import java.awt.Color;
import java.awt.FlowLayout;

public class GestionPasajeroGUI extends JFrame{
	private PasajeroController controller;
	private JComboBox<String> cbxTipoDNI;	
	private JTextField tbxApellido;
	private JTextField tbxNombre;
	private JTextField tbxNDoc;
	
	public GestionPasajeroGUI() {
		this.setResizable(false);
		
		this.controller = new PasajeroController(this);
		this.controller.cargarTDNI();
		
		this.setLayout(new BorderLayout(0, 0));
		
		JLabel lblGestionar = new JLabel("Gestionar Pasajero");
		lblGestionar.setVerticalAlignment(SwingConstants.BOTTOM);
		lblGestionar.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionar.setFont(new Font("Tahoma", Font.PLAIN, 22));
		this.add(lblGestionar, BorderLayout.NORTH);
		
		JPanel panelDatos = new JPanel();
		this.add(panelDatos, BorderLayout.CENTER);
		panelDatos.setLayout(null);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(88, 64, 50, 15);
		panelDatos.add(lblApellido);
		
		tbxApellido = new JTextField();
		tbxApellido.setBounds(145, 61, 270, 20);
		panelDatos.add(tbxApellido);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(88, 91, 50, 15);
		panelDatos.add(lblNombre);
				
		tbxNombre = new JTextField();
		tbxNombre.setBounds(145, 89, 270, 20);
		panelDatos.add(tbxNombre);
		
		JLabel lblDocumentoTipo = new JLabel("Documento Tipo:");
		lblDocumentoTipo.setBounds(88, 120, 100, 15);
		panelDatos.add(lblDocumentoTipo);
		
		cbxTipoDNI.setBounds(190, 115, 87, 25);
		panelDatos.add(cbxTipoDNI);
			
		
		JLabel lblNumero = new JLabel("Número");
		lblNumero.setBounds(280, 120, 50, 15);
		panelDatos.add(lblNumero);
		
		tbxNDoc = new JTextField();
		tbxNDoc.setBounds(330, 117, 85, 20);
		panelDatos.add(tbxNDoc);
		tbxNDoc.setColumns(10);
		
		
		//Buttons
		JButton Cancelar = new JButton("Cancelar");
		Cancelar.setBounds(210,150,90,25);
		Cancelar.setBackground(Color.RED);
		Cancelar.setForeground(Color.WHITE);
		panelDatos.add(Cancelar);
		
		JButton Siguiente = new JButton("Siguiente");
		Siguiente.setBounds(315,150,100,25);
		Siguiente.setBackground(new Color(0, 128, 0));
		Siguiente.setForeground(Color.WHITE);
		panelDatos.add(Siguiente);

		//Actions
		Cancelar.addActionListener(e -> dispose());
		
		Siguiente.addActionListener(e -> {
			try {
				controller.buscarPasajero();
			} catch (NoConcordanciaException e1) {
				e1.printStackTrace();
				mostrarError("No Concordancia", "No existe ninguna concordancia según los criterios de búsqueda");
			}
		});
		
		this.setLocationRelativeTo(null);
		this.setSize(500,300);		
		
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
