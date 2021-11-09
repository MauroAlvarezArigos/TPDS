package GUI;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.PasajeroController;
import Exceptions.NoConcordanciaException;

import java.awt.Color;

public class GestionPasajeroGUI extends JFrame{
	
	private PasajeroController controller;
	private JComboBox<String> cbxTipoDNI;	
	private JTextField tbxApellido;
	private JTextField tbxNombre;
	private JTextField tbxNDoc;
	
	public GestionPasajeroGUI() {
	
		this.controller = new PasajeroController(this);
		this.controller.cargarTDNI();
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setSize(500,300);		
		
		JPanel panelDatos = new JPanel();
		panelDatos.setBounds(0, 33, 476, 230);
		getContentPane().add(panelDatos);
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
		
		JButton next = new JButton("Siguiente");
		next.setBounds(315,150,100,25);
		next.setBackground(new Color(0, 128, 0));
		next.setForeground(Color.WHITE);
		panelDatos.add(next);
		
		JLabel lblGestionar = new JLabel("Gestionar Pasajero");
		lblGestionar.setBounds(0, 0, 496, 27);
		getContentPane().add(lblGestionar);
		lblGestionar.setVerticalAlignment(SwingConstants.BOTTOM);
		lblGestionar.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionar.setFont(new Font("Tahoma", Font.PLAIN, 22));

		//Actions
		Cancelar.addActionListener(e -> dispose());
		
		next.addActionListener(e -> {
			try {
				controller.buscarPasajero();
			} catch (NoConcordanciaException e1) {
				e1.printStackTrace();
				mostrarError("No Concordancia", "No existe ninguna concordancia según los criterios de búsqueda");
			} catch (Exception e1) {
				System.out.println("Es en el try de gestion pasajero");
				e1.printStackTrace();
				
			}
		});
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
