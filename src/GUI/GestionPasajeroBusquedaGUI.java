package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Controller.PasajeroController;
import Dominio.Pasajero;

public class GestionPasajeroBusquedaGUI extends JFrame{
	private JTable table;
	private PasajeroController controller;

	public GestionPasajeroBusquedaGUI(List<Pasajero> lista) {
		
		this.setResizable(false);
		this.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVisible(true);

		
		//ADD LUPA
		JLabel lblGestionar = new JLabel("Apellido: Nombres: ");
		lblGestionar.setVerticalAlignment(SwingConstants.BOTTOM);
		lblGestionar.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionar.setFont(new Font("Tahoma", Font.PLAIN, 22));
		this.add(lblGestionar, BorderLayout.NORTH);
		
		
		//Creo la tabla
		int tam = lista.size();
		Object[][] tabla = new Object[tam][4];
		for(int i=0; i<tam; i++) {
			tabla[i][0] = lista.get(i).getApellido();
			tabla[i][1] = lista.get(i).getNombre();
			tabla[i][2] = lista.get(i).getTipodoc();
			tabla[i][3] = lista.get(i).getNdoc();
		}

		
		String[] columnNames = {"Apellido", "Nombre", "Tipo Documento", "Número"};
		DefaultTableModel dtm = new DefaultTableModel((Object[][]) tabla, columnNames);
		final JTable table = new JTable(dtm);
		scrollPane.setPreferredSize(new Dimension(100,500));
		table.setPreferredScrollableViewportSize(new Dimension(250, 100));
		
		scrollPane.setViewportView(table);
		this.add(scrollPane, BorderLayout.CENTER);
		
		SwingUtilities.updateComponentTreeUI(this);	 
		
		this.setLocationRelativeTo(null);
		this.setSize(500,300);	
	}
	
	public void update() {
	    SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void setTable(JTable tabla) {
		this.table = tabla;
	}
	
	public void setController(PasajeroController unController) {
		this.controller = unController;
		
	}

}
