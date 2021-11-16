package Controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import DTO.PasajeroDTO;
import Dominio.Pasajero;
import Exceptions.NoConcordanciaException;
//import GUI.DarAltaPsjeroGUI;
import GUI.AltaPasajeroGUI;
import GUI.GestionPasajeroBusquedaGUI;
import GUI.GestionPasajeroGUI;
import Servicios.PasajeroServicio;

public class PasajeroController {
	private PasajeroServicio pasajeroServicio;
	private Pasajero pasajero;
	private List<PasajeroDTO> lista;
	private GestionPasajeroGUI gestionGUI;
	private GestionPasajeroBusquedaGUI gbusquedaGUI;
	private AltaPasajeroGUI AltaPsjeroGUI;
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
		String s;
		
		try {
			System.out.println("Entre al Try block");
			lista = pasajeroServicio.buscarPasajero(n, a, td, d);
					//System.out.println("busque los pasajeros");
			s = prepararTitulo(n,a,td,d);
					//System.out.println("Prepare el titulo");
			
			gbusquedaGUI = new GestionPasajeroBusquedaGUI(lista, s);
					//System.out.println("Cree gbusquedaGUI");
			gbusquedaGUI.setController(this);
					//System.out.println("Setee el controller");

	
		    gestionGUI.setVisible(false);
		    		//System.out.println("Escondi gestionGUI");
		    gbusquedaGUI.setVisible(true);
		    		//System.out.println("Mostre gBusquedaGUI");
		    
		} catch (NoConcordanciaException e) {
			throw e;
		} catch (Exception e2) {
			System.out.println("Capture e2");
			e2.printStackTrace();
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
	
	private String prepararTitulo(String nombre, String apellido, String tipoDoc, String ndoc) {
		String tmp = "";
		
		if(nombre.equals("") && apellido.equals("") && tipoDoc.equals("") && ndoc.equals("")) {
			return "Busqueda Sin Parámetros";
		} else {
			
			if(!apellido.equals("")) {
				tmp = tmp + "Apellido: '" + apellido+"'";
				System.out.println("apellido concatenado");
			}
		
			if(!nombre.equals("")) {
				System.out.println("Nombre concatenado");
					
				tmp = tmp + " Nombre: '"+nombre+"'";
			}
		
			if(!tipoDoc.equals("")) {
				System.out.println("tdoc concatenado");
		
				tmp = tmp + " Tipo Documento: '"+ tipoDoc+"'";				
			}
			if(!ndoc.equals("")) {
				System.out.println("doc concatenado");
				tmp = tmp + " Número: '"+ ndoc+"'";				
			}
		
		}
		return tmp;
	}

	public void DarAltaPasajero(){
		AltaPsjeroGUI = new AltaPasajeroGUI();
		AltaPsjeroGUI.setController(this);
		gbusquedaGUI.setVisible(false);
		AltaPsjeroGUI.setVisible(true);
	}
}
