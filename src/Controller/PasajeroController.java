package Controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Dominio.Pasajero;
import Exceptions.NoConcordanciaException;
import GUI.GestionPasajeroBusquedaGUI;
import GUI.GestionPasajeroGUI;
import Servicios.PasajeroServicio;

public class PasajeroController {
	private PasajeroServicio pasajeroServicio;
	private Pasajero pasajero;
	private List<Pasajero> lista;
	private GestionPasajeroGUI gestionGUI;
	private GestionPasajeroBusquedaGUI gbusquedaGUI;
	private final JTable table;
	
	//Constructor
	public PasajeroController(GestionPasajeroGUI g) {
		this.table = new JTable();
		this.pasajeroServicio = new PasajeroServicio();
		this.gestionGUI = g;
	}
	
	public void buscarPasajero() throws NoConcordanciaException{
		String a = gestionGUI.getTbxApellido().getText();
		String n = gestionGUI.getTbxNombre().getText();
		String d = gestionGUI.getTbxNDoc().getText();
		String td = gestionGUI.getCbxTipoDNI().getSelectedItem().toString();
		
		try {
			lista = pasajeroServicio.buscarPasajero(n, a, td, d);
			
			gbusquedaGUI = new GestionPasajeroBusquedaGUI(lista);
			gbusquedaGUI.setController(this);
	
		    gestionGUI.setVisible(false);
		    gbusquedaGUI.setVisible(true);
		    
		} catch (NoConcordanciaException e) {
			throw e;
		}
	}
	
	public JTable getTabla() {
		return this.table;
	}
	
	public void cargarTDNI() {
		JComboBox<String> tdni = new JComboBox<String>();
		tdni.addItem("");
		tdni.addItem("DNI");
		tdni.addItem("LE");
		tdni.addItem("LC");
		tdni.addItem("Pasaporte");
		tdni.addItem("Otro");
		gestionGUI.setCbxTipoDNI(tdni);
	}
		

}
