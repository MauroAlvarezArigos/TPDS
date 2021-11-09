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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Controller.PasajeroController;
import Dominio.Pasajero;

public class GestionPasajeroBusquedaGUI extends JFrame implements ActionListener{
	private ButtonGroup bg;
	private List<Pasajero> lista;
	
	public GestionPasajeroBusquedaGUI(List<Pasajero> lista, String t) {
		
		this.lista = lista;
		this.setLocationRelativeTo(null);
		this.setSize(700,300);
		
		//Filling search results
		JPanel results = new JPanel();
		results.setLayout(null);
		
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
		sp.setBounds(0, 0, 0, 0);
		sp.setPreferredSize(new Dimension(600,600));
		System.out.println("Cree el JScrollPane");
		
		getContentPane().add(sp);
		System.out.println("Añadi JScrollPane al JFrame");
		
		//Buttons
		JPanel buttons = new JPanel();
		buttons.setBounds(0, 0, 0, 0);
		buttons.setLayout(new BorderLayout());
		JButton siguiente = new JButton("Siguiente");
		siguiente.addActionListener(this);
		buttons.add(siguiente, BorderLayout.LINE_END);
		getContentPane().add(buttons);
		
		System.out.println("Cree y añadi botones");
		
		
		
		SwingUtilities.updateComponentTreeUI(this);
		
		System.out.println("update");
			
	}
	
	public void update() {
	    SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void setTable(JTable tabla) {
	}
	
	public void setController(PasajeroController unController) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getActionCommand().equals("Siguiente")) {
	            System.out.println("Selected Radio Button: " + lista.get((Integer.parseInt(bg.getSelection().getActionCommand()))).toString());
	     }
	}

}
