package GUI;


import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class App extends JFrame {
	
	public static void main(String[] args) {

		JFrame frame = new JFrame("Naurede");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Sistema de Gestion de Viajes");
		frame.setLayout(new BorderLayout());
		
		JPanel menu = new JPanel();
		
		JButton boton = new JButton("Gestionar Pasajero");
		menu.add(boton);
		
		boton.addActionListener(e -> {
			GestionPasajeroGUI gp = new GestionPasajeroGUI();
			gp.setVisible(true);
			SwingUtilities.updateComponentTreeUI(frame);
		});
		
		
		frame.add(menu, BorderLayout.NORTH);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		
	}
	
}