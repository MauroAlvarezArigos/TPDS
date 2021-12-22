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
					frame.pack();
					frame.setSize(600,400);
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
		
		JButton btnOcuparHabitacion = new JButton("Ocupar Habitacion");
		panelMenu.add(btnOcuparHabitacion);
		
		JButton btnFacturar = new JButton("Facturar");
		panelMenu.add(btnFacturar);
		
		/*JButton btnFacturar2 = new JButton("Facturar2");
		panelMenu.add(btnFacturar2);*/
		
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
			MostrarEstadoHabitacionGUI mostrarEstadoHabitacion = new MostrarEstadoHabitacionGUI();
			mostrarEstadoHabitacion.setLocationRelativeTo(null);
			mostrarEstadoHabitacion.setVisible(true);
			SwingUtilities.updateComponentTreeUI(panelMenu);
		});
		
		btnOcuparHabitacion.addActionListener(e -> {
			OcuparHabitacionGUI ocuparHabitacion = new OcuparHabitacionGUI();
			ocuparHabitacion.setLocationRelativeTo(null);
			ocuparHabitacion.setVisible(true);
			SwingUtilities.updateComponentTreeUI(panelMenu);
		});
		
		btnFacturar.addActionListener(e -> {
			FacturarGUI facturar = new FacturarGUI();
			facturar.setLocationRelativeTo(null);
			facturar.setVisible(true);
			SwingUtilities.updateComponentTreeUI(panelMenu);
		});
		/*
		btnFacturar2.addActionListener(e -> {
			FacturarElementosGUI facturar2 = new FacturarElementosGUI(null,"Microsoft","A");
			facturar2.setLocationRelativeTo(null);
			facturar2.setVisible(true);
			SwingUtilities.updateComponentTreeUI(panelMenu);
		});*/
		
		
	}
}