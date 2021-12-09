package Controller;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;

import DTO.IDTypeDTO;
import DTO.PasajeroBusquedaDTO;
import Dominio.Pasajero;
import Exceptions.NoConcordanciaException;
//import GUI.DarAltaPsjeroGUI;
import GUI.AltaPasajeroGUI;
import GUI.GestionPasajeroBusquedaGUI;
import GUI.GestionPasajeroGUI;
import Servicios.IDTypeServicio;
import Servicios.PasajeroServicio;

public class PasajeroController {
	private PasajeroServicio pasajeroServicio;
	private IDTypeServicio IDServicio;
	private Pasajero pasajero;
	private List<PasajeroBusquedaDTO> lista;
	private GestionPasajeroGUI gestionGUI;
	private GestionPasajeroBusquedaGUI gbusquedaGUI;
	private AltaPasajeroGUI AltaPsjeroGUI;
	private final JTable table;
	
	//Constructor
	public PasajeroController(GestionPasajeroGUI g) {
		this.table = new JTable();
		this.pasajeroServicio = new PasajeroServicio();
		this.IDServicio = new IDTypeServicio();
		this.gestionGUI = g;
	}
	
	public void buscarPasajero() throws NoConcordanciaException{
		String a = gestionGUI.getTbxApellido().getText();
		String n = gestionGUI.getTbxNombre().getText();
		String d = gestionGUI.getTbxNDoc().getText();
		String td = gestionGUI.getCbxTipoDNI().getSelectedItem().toString();
		String s;
		
		try {
			lista = pasajeroServicio.buscarPasajero(n, a, td, d);
			s = prepararTitulo(n,a,td,d);
			
			gbusquedaGUI = new GestionPasajeroBusquedaGUI(lista, s);
			gbusquedaGUI.setController(this);

	
		    gestionGUI.setVisible(false);
		    gbusquedaGUI.setVisible(true);
		    
		} catch (NoConcordanciaException e) {
			throw e;
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public JTable getTabla() {
		return this.table;
	}

	public void cargarTDNI() {
		JComboBox<String> tdni = new JComboBox<String>();
		List<IDTypeDTO> ListaIDT = IDServicio.getAllIDType();
		int size = ListaIDT.size();
		tdni.addItem("");
		for(int c = 0; c < size; c++) {
			tdni.addItem((ListaIDT.get(c)).getTipo());
		}
		//tdni.addItem("");
		//tdni.addItem("DNI");
		//tdni.addItem("LE");
		//tdni.addItem("LC");
		//tdni.addItem("Pasaporte");
		//tdni.addItem("Otro");
		gestionGUI.setCbxTipoDNI(tdni);
	}
	
	private String prepararTitulo(String nombre, String apellido, String tipoDoc, String ndoc) {
		String tmp = "";
		
		if(nombre.equals("") && apellido.equals("") && tipoDoc.equals("") && ndoc.equals("")) {
			return "Busqueda Sin Parámetros";
		} else {
			
			if(!apellido.equals("")) {
				tmp = tmp + "Apellido: '" + apellido+"'";
			}
		
			if(!nombre.equals("")) {
					
				tmp = tmp + " Nombre: '"+nombre+"'";
			}
		
			if(!tipoDoc.equals("")) {
		
				tmp = tmp + " Tipo Documento: '"+ tipoDoc+"'";				
			}
			if(!ndoc.equals("")) {
				tmp = tmp + " Número: '"+ ndoc+"'";				
			}
		
		}
		return tmp;
	}

}
