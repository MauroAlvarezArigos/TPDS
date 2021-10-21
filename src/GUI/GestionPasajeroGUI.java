package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.PasajeroController;
import java.awt.Color;

public class GestionPasajeroGUI extends JFrame{
	private PasajeroController controller;
	
	private JTextField tbxNombre;
	private JTextField tbxApellido;
	private JComboBox<String> cbxTipoDNI;
	private JTextField tbxNDoc;
	private JTextField textField;
	
	public GestionPasajeroGUI() {
		this.setUndecorated(true);
		this.setResizable(false);
		this.controller = new PasajeroController(this);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new BorderLayout());
		this.setContentPane(contentPane);
		
		JPanel panelDatos = new JPanel();
		contentPane.add(panelDatos, BorderLayout.CENTER);
		panelDatos.setLayout(new GridBagLayout());
		
		JLabel lblGestionar = new JLabel("Gestionar Pasajero");
		lblGestionar.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionar.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(lblGestionar, BorderLayout.NORTH);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.anchor = GridBagConstraints.EAST;
		gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido.gridx = 0;
		gbc_lblApellido.gridy = 1;
		panelDatos.add(lblApellido, gbc_lblApellido);
		
		tbxApellido = new JTextField();
		tbxApellido.setColumns(20);
		GridBagConstraints gbc_tbxApellido = new GridBagConstraints();
		gbc_tbxApellido.anchor = GridBagConstraints.WEST;
		gbc_tbxApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbxApellido.insets = new Insets(0, 0, 5, 5);
		gbc_tbxApellido.gridx = 1;
		gbc_tbxApellido.gridy = 1;
		panelDatos.add(tbxApellido, gbc_tbxApellido);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 2;
		panelDatos.add(lblNombre, gbc_lblNombre);
		
		tbxNombre = new JTextField();
		tbxNombre.setColumns(20);
		GridBagConstraints gbc_tbxNombre = new GridBagConstraints();
		gbc_tbxNombre.anchor = GridBagConstraints.WEST;
		gbc_tbxNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbxNombre.insets = new Insets(0, 0, 5, 5);
		gbc_tbxNombre.gridx = 1;
		gbc_tbxNombre.gridy = 2;
		panelDatos.add(tbxNombre, gbc_tbxNombre);
		
		JLabel lblDocTipo = new JLabel("Documento Tipo");
		GridBagConstraints gbc_lblDocTipo = new GridBagConstraints();
		gbc_lblDocTipo.anchor = GridBagConstraints.EAST;
		gbc_lblDocTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblDocTipo.gridx = 0;
		gbc_lblDocTipo.gridy = 2;
		panelDatos.add(lblDocTipo, gbc_lblDocTipo);
		
		cbxTipoDNI 
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(Color.RED);
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.EAST;
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 3;
		panelDatos.add(btnCancel, gbc_btnCancel);
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBackground(Color.GREEN);
		GridBagConstraints gbc_btnSiguiente = new GridBagConstraints();
		gbc_btnSiguiente.anchor = GridBagConstraints.WEST;
		gbc_btnSiguiente.insets = new Insets(0, 0, 5, 5);
		gbc_btnSiguiente.gridx = 2;
		gbc_btnSiguiente.gridy = 3;
		panelDatos.add(btnSiguiente, gbc_btnSiguiente);
		
				
		
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setSize(300,200);
		
	}
	
	

}
