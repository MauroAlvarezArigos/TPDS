package Controller;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import DTO.IDTypeDTO;
import DTO.PasajeroBusquedaDTO;
import Exceptions.NoConcordanciaException;
import GUI.OcuparHabAsigPasajeroGUI;
import GUI.OcuparHabitacionGUI;
import Servicios.IDTypeServicio;
import Servicios.PasajeroServicio;

public class OcuparController {
	private PasajeroServicio pasajeroServicio;
	private IDTypeServicio IDServicio;
	private List<PasajeroBusquedaDTO> lista;
	private OcuparHabitacionGUI ohabGUI;
	private OcuparHabAsigPasajeroGUI ocuparGUI;
	
	public OcuparController(OcuparHabitacionGUI oHab) {
		this.pasajeroServicio = new PasajeroServicio();
		this.IDServicio = new IDTypeServicio();
		this.ohabGUI = oHab;
	}
	
	public void buscarPasajero() throws NoConcordanciaException{
		String a = ocuparGUI.getTbxApellido().getText();
		String n = ocuparGUI.getTbxNombre().getText();
		String d = ocuparGUI.getTbxNDoc().getText();
		String td = ocuparGUI.getCbxTipoDNI().getSelectedItem().toString();
		
		try {
			JPanel tablaGUI;
			lista = pasajeroServicio.buscarPasajero(n, a, td, d);
			System.out.println("tengo la lista "+lista.toString());
			tablaGUI = ocuparGUI.armarTabla(lista);
			System.out.println("arme tabla");
			
			ocuparGUI.setContentPane(tablaGUI);
			System.out.println("Set content pane");
			
			SwingUtilities.updateComponentTreeUI(ocuparGUI);

		    
		} catch (NoConcordanciaException e) {
			throw e;
		} catch (Exception e2) {
			System.out.println("Capture e2");
			e2.printStackTrace();
		}
	}
	
	public void cargarTDNI() {
		JComboBox<String> tdni = new JComboBox<String>();
		List<IDTypeDTO> ListaIDT = IDServicio.getAllIDType();
		int size = ListaIDT.size();
		tdni.addItem("");
		for(int c = 0; c < size; c++) {
			tdni.addItem((ListaIDT.get(c)).getTipo());
		}
		ocuparGUI.setCbxTipoDNI(tdni);
	}

	public void verificarHabitaciones() {
		if(ohabGUI.getChbxIndEstandar().isSelected()) {
			ohabGUI.getIndEstandar().getValue();
		}
		if(ohabGUI.getChbxDobEstandar().isSelected()) {
			
		}
		if(ohabGUI.getChbxDobSuperior().isSelected()) {
			
		}
		if(ohabGUI.getChbxSupFamily().isSelected()) {
			
		}
		if(ohabGUI.getChbxSuiteDoble().isSelected()) {
			
		}
	}
}
