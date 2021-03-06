package GUI;

import java.awt.Font;

import javax.swing.*;
import Controller.PasajeroController;
import Exceptions.NoConcordanciaException;
import modelosTabla.JTextFieldLimit;

import java.awt.Color;

@SuppressWarnings("serial")
public class GestionPasajeroGUI extends JFrame{

	private PasajeroController controller;
	private JComboBox<String> cbxTipoDNI;	
	private JTextField tbxApellido;
	private JTextField tbxNombre;
	private JTextField tbxNDoc;
	
	public GestionPasajeroGUI() {
	
		this.controller = new PasajeroController(this);
		this.controller.cargarTDNI();
		getContentPane().setLayout(null);
		this.setSize(500,300);		
		
		JPanel panelDatos = new JPanel();
		panelDatos.setBounds(0, 33, 476, 230);
		getContentPane().add(panelDatos);
		panelDatos.setLayout(null);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(68, 64, 70, 15);
		panelDatos.add(lblApellido);
		
		tbxApellido = new JTextField();
		tbxApellido.setDocument(new JTextFieldLimit(30));
		tbxApellido.setBounds(145, 61, 270, 20);
		panelDatos.add(tbxApellido);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(68, 91, 70, 15);
		panelDatos.add(lblNombre);
				
		tbxNombre = new JTextField();
		tbxNombre.setDocument(new JTextFieldLimit(30));
		tbxNombre.setBounds(145, 89, 270, 20);
		panelDatos.add(tbxNombre);
		
		JLabel lblDocumentoTipo = new JLabel("Documento Tipo:");
		lblDocumentoTipo.setBounds(68, 120, 100, 15);
		panelDatos.add(lblDocumentoTipo);
		
		cbxTipoDNI.setBounds(190, 115, 87, 25);
		panelDatos.add(cbxTipoDNI);
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setBounds(280, 120, 50, 15);
		panelDatos.add(lblNumero);
		
		tbxNDoc = new JTextField();
		tbxNDoc.setDocument(new JTextFieldLimit(15));
		tbxNDoc.setBounds(330, 117, 85, 20);
		panelDatos.add(tbxNDoc);
		tbxNDoc.setColumns(10);
		
		//Buttons
		JButton Cancelar = new JButton("Cancelar");
		Cancelar.setBounds(256,195,100,25);
		Cancelar.setBackground(Color.RED);
		Cancelar.setForeground(Color.WHITE);
		panelDatos.add(Cancelar);
		
		JButton next = new JButton("Siguiente");
		next.setBounds(366,195,100,25);
		next.setBackground(new Color(0, 128, 0));
		next.setForeground(Color.WHITE);
		panelDatos.add(next);
		
		JLabel informeDeErrores = new JLabel();
		informeDeErrores.setForeground(Color.RED);
		informeDeErrores.setVisible(false);
		informeDeErrores.setBounds(88, 172, 378, 13);
		panelDatos.add(informeDeErrores);
		
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
				if((tbxApellido.getText()).length()>30) {
					informeDeErrores.setVisible(true);
					tbxApellido.setBorder(BorderFactory.createLineBorder(Color.red));
					informeDeErrores.setText("El apellido debe contener menos de 30 caracteres");
				}else if((tbxNombre.getText()).length()>30) {
					informeDeErrores.setVisible(true);
					tbxNombre.setBorder(BorderFactory.createLineBorder(Color.red));
					informeDeErrores.setText("El nombre debe contener menos de 30 caracteres");
				}else if((tbxNDoc.getText()).length()>30) {
					informeDeErrores.setVisible(true);
					tbxNDoc.setBorder(BorderFactory.createLineBorder(Color.red));
					informeDeErrores.setText("El documento debe contener menos de 10 caracteres");
				} else {
					controller.buscarPasajero();
				}
			} catch (NoConcordanciaException e1) {
				e1.printStackTrace();
				mostrarError("No Concordancia", "No existe ninguna concordancia segun los criterios de busqueda");
			} catch (Exception e1) {
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
