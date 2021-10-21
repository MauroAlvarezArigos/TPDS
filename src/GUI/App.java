package GUI;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class App extends JFrame {
	
	public static void main(String[] args) {

		JFrame frame = new JFrame("Naurede");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Sistema de Gestion de Viajes");
		
		JPanel menu = new JPanel();
		
		JButton boton = new JButton("Gestionar Pasajero");
		menu.add(boton);
		
		boton.addActionListener(e -> {
			GestionPasajeroGUI gp = new GestionPasajeroGUI();
			gp.setVisible(true);
		});
		
		
		frame.setContentPane(menu);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		
	}
	
}