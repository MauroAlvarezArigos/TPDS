package GUI;

import Controller.DarAltaController;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class App extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public App() {
		setTitle("Sistema de Gestion de Viajes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelMenu = new JPanel();
		setContentPane(panelMenu);
		panelMenu.setLayout(new FlowLayout());
		
		JButton botonGestionarPasajero = new JButton("Gestionar Pasajero");
		panelMenu.add(botonGestionarPasajero);
		
		JButton btnAltaPasajero = new JButton("Alta Pasajero");
		panelMenu.add(btnAltaPasajero);
		
		JButton btnMostrarEstadoHabitacion = new JButton("Mostrar Estado Habitacion");
		panelMenu.add(btnMostrarEstadoHabitacion);
		
		btnAltaPasajero.addActionListener(e -> {
			AltaPasajeroGUI ap = new AltaPasajeroGUI();
			ap.setController(new DarAltaController(ap));
			ap.setVisible(true);
			ap.setLocationRelativeTo(null);
			SwingUtilities.updateComponentTreeUI(panelMenu);
		});

		botonGestionarPasajero.addActionListener(e -> {
			GestionPasajeroGUI gp = new GestionPasajeroGUI();
			gp.setLocationRelativeTo(null);
			gp.setVisible(true);
			SwingUtilities.updateComponentTreeUI(panelMenu);
		});
		
		btnMostrarEstadoHabitacion.addActionListener(e -> {
			MostrarEstadoHabitacionGUI me = new MostrarEstadoHabitacionGUI();
			me.setLocationRelativeTo(null);
			me.setVisible(true);
			SwingUtilities.updateComponentTreeUI(panelMenu);
		});
	}
}