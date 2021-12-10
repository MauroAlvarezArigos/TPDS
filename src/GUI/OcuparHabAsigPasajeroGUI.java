package GUI;

import java.awt.Font;

import javax.swing.*;

import Controller.OcuparController;
import Controller.PasajeroController;
import DTO.PasajeroBusquedaDTO;
import Exceptions.NoConcordanciaException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class OcuparHabAsigPasajeroGUI extends JFrame{
	
	private OcuparController controller;
	private JComboBox<String> cbxTipoDNI;	
	private JTextField tbxApellido;
	private JTextField tbxNombre;
	private JTextField tbxNDoc;
	
	 private DefaultTableModel model;
	 private JTable tabla;
	
	public OcuparHabAsigPasajeroGUI() {
	
		this.controller = new OcuparController(this);
		this.controller.cargarTDNI();
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setSize(500,300);		
		
		pantallaDatos();
	}
	
	public JPanel armarTabla(List<PasajeroBusquedaDTO> lista) {
		JPanel tablaGUI = new JPanel();
		tablaGUI.setLayout(new BorderLayout());
		int tam = lista.size();
		
		Object[][] data = new Object[tam][5];
		Object[] columnNames = {" ", "Apellido", "Nombre", "Tipo Documento", "Numero Documento"};
		
		System.out.println("Pre for");
		for(int i=0; i<lista.size(); ++i) {
			data[i][0] = false;
			data[i][1] = lista.get(i).getApellido();
			data[i][2] = lista.get(i).getNombre();
			data[i][3] = lista.get(i).getTipodoc();
			data[i][4] = lista.get(i).getNdoc();
		}
		
		JTable tabla = new JTable(data, columnNames) {
	         @Override
	         public Class getColumnClass(int column) {
	                switch (column) {
	                    case 0:
	                        return Boolean.class;
	                    default:
	                        return String.class;
	                }
	         }
	         
	         @Override
	         public boolean isCellEditable(int row, int column) {
	             return column == 0 ? true : false;
	         }
	    };
	    tabla.getColumnModel().getColumn(0).setPreferredWidth(5);
	    tabla.setPreferredScrollableViewportSize(new Dimension(400, 200));
		JScrollPane sp = new JScrollPane(tabla);
		tablaGUI.add(sp, BorderLayout.CENTER);
		
		JPanel boton = new JPanel();
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(306, 323, 156, 21);
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setBackground(new Color(50, 205, 50));
		boton.add(btnAceptar, BorderLayout.SOUTH);
		tablaGUI.add(boton, BorderLayout.SOUTH);
		
		
		return tablaGUI;
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
		
		JButton btnAceptar = new JButton("Siguiente");
		btnAceptar.setBounds(345, 200, 100, 25);
		getContentPane().add(btnAceptar);
		btnAceptar.setBackground(new Color(0, 128, 0));
		btnAceptar.setForeground(Color.WHITE);

		//Actions
		btnAceptar.addActionListener(e -> {
			try {
				controller.buscarPasajero();
			} catch (NoConcordanciaException e1) {
				e1.printStackTrace();
				mostrarError("No Concordancia", "No existe ninguna concordancia segun los criterios de busqueda");
			} catch (Exception e1) {
				System.out.println("Es en el try de gestion pasajero");
				e1.printStackTrace();
				
			}
		});

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
