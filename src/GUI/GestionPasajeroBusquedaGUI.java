package GUI;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Controller.PasajeroController;
import Dominio.Pasajero;

public class GestionPasajeroBusquedaGUI extends JFrame implements ActionListener{
	private JTable table;
	private PasajeroController controller;
	private ButtonGroup bg;
	private List<Pasajero> lista;
	
	public GestionPasajeroBusquedaGUI(List<Pasajero> lista, String t) {
		this.lista = lista;
		System.out.println("Guarde la lista");
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		System.out.println("Setee Layout");
		
		JPanel titulo = new JPanel();
		ImageIcon image = new ImageIcon(".\\res\\search1x.png");
		JLabel img = new JLabel(image, JLabel.CENTER);
		JLabel lblTitulo = new JLabel(t);
		lblTitulo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));

		titulo.add(img);
		titulo.add(lblTitulo);

		this.add(titulo, BorderLayout.NORTH);
		
		System.out.println("Setee TITULO");
		
		//Filling search results
		JPanel results = new JPanel();
		results.setLayout(new GridLayout(0,1));
		System.out.println("Cree results");
		
		bg = new ButtonGroup();
		System.out.println("Cree bg");
		
		int tam = lista.size();
		String s = "";
		for(int i=0; i<tam; i++) {
			s += lista.get(i).getApellido() + " ";
			s += lista.get(i).getNombre() + " ";
			s += lista.get(i).getTipodoc()+ " ";
			s += lista.get(i).getNdoc() + " ";
			
			final JRadioButton rb = new JRadioButton(s);
			rb.setActionCommand(Integer.toString(i));
			bg.add(rb);
			results.add(rb);
			s = "";
		}
		
		JScrollPane sp = new JScrollPane(results);
		sp.setPreferredSize(new Dimension(600,600));
		System.out.println("Cree el JScrollPane");
		
		this.add(sp, BorderLayout.CENTER);
		System.out.println("Añadi JScrollPane al JFrame");
		
		//Buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new BorderLayout());
		JButton siguiente = new JButton("Siguiente");
		siguiente.addActionListener(this);
		buttons.add(siguiente, BorderLayout.LINE_END);
		this.add(buttons, BorderLayout.SOUTH);
		
		System.out.println("Cree y añadi botones");
		
		
		
		SwingUtilities.updateComponentTreeUI(this);
		
		System.out.println("update");
		
		this.setLocationRelativeTo(null);
		this.setSize(700,300);	
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

	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getActionCommand().equals("Siguiente")) {
	            System.out.println("Selected Radio Button: " + lista.get((Integer.parseInt(bg.getSelection().getActionCommand()))).toString());
	     }
	}

}
