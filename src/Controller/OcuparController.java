package Controller;

import java.util.List;

import javax.swing.JComboBox;

import DTO.IDTypeDTO;
import DTO.PasajeroBusquedaDTO;
import Exceptions.NoConcordanciaException;
import GUI.OcuparHabAsigPasajeroGUI;
import Servicios.IDTypeServicio;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.*;

import DTO.*;
import GUI.OcuparHabitacionGUI;
import GUI.TablaPasajerosGUI;
import Servicios.HabitacionServicio;
import Servicios.OcupacionServicio;
import Servicios.PasajeroServicio;

public class OcuparController {
	private PasajeroServicio pasajeroServicio;
	@SuppressWarnings("unused")
	private HabitacionServicio habitacionServicio;
	private IDTypeServicio IDServicio;
	private List<PasajeroBusquedaDTO> lista;
	private OcuparHabitacionGUI ocuparGUI;
	private OcuparHabAsigPasajeroGUI BuscarOcuparGUI;
	private List<OcupacionDTO> LOcupacion;
	private TablaPasajerosGUI TablaGUI;
	private OcupacionServicio ocupacionServicio;
	private int OcupacionCounter;

	//Getters and Setters


	public void setBuscarOcuparGUI(OcuparHabAsigPasajeroGUI buscarOcuparGUI) {
		BuscarOcuparGUI = buscarOcuparGUI;
	}

	
	public OcuparController(OcuparHabitacionGUI oHab) {
		this.pasajeroServicio = new PasajeroServicio();
		this.habitacionServicio = new HabitacionServicio();
		this.ocupacionServicio = new OcupacionServicio();
		this.IDServicio = new IDTypeServicio();
		this.ocuparGUI = oHab;
		this.OcupacionCounter = 0;
	}
	
	public void buscarPasajero(int flag) throws NoConcordanciaException{


		String a = BuscarOcuparGUI.getTbxApellido().getText();
		String n = BuscarOcuparGUI.getTbxNombre().getText();
		String d = BuscarOcuparGUI.getTbxNDoc().getText();
		String td = BuscarOcuparGUI.getCbxTipoDNI().getSelectedItem().toString();
		
		try {
			lista = pasajeroServicio.buscarPasajero(n, a, td, d);
			System.out.println("tengo la lista "+lista.toString());
			TablaGUI = new TablaPasajerosGUI(this, lista, flag);
			TablaGUI.setVisible(true);
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
		BuscarOcuparGUI.setCbxTipoDNI(tdni);
	}

	public List<String> getSelectedTipos(){
		List<String> LTipos = new ArrayList<>();
		if(ocuparGUI.getChbxIndEstandar().isSelected()){
			LTipos.add("Individual Estandar");
		}
		if(ocuparGUI.getChbxDobEstandar().isSelected()){
			LTipos.add("Doble Estandar");
		}
		if(ocuparGUI.getChbxDobSuperior().isSelected()){
			LTipos.add("Doble Superior");
		}
		if(ocuparGUI.getChbxSuiteDoble().isSelected()){
			LTipos.add("Suite Doble");
		}
		if(ocuparGUI.getChbxSupFamily().isSelected()){
			LTipos.add("Superior Family Plan");
		}
		return LTipos;
	}

	public void getListOcupacion(List<HabitacionDTO> LHab, List<List<LocalDate>> ArrayDates){
		LOcupacion = new ArrayList<>();

		int sizeLHab = LHab.size();
		for(int x = 0; x < sizeLHab; x++){
			int sizeDates = ArrayDates.get(x).size();
			LocalDate checkIn = null;
			LocalDate checkOut = null;
			for(int c = 0; c < sizeDates; c++){
				if(sizeDates == 1) {
					checkIn = ArrayDates.get(x).get(c);
					checkOut = ArrayDates.get(x).get(c);
					OcupacionDTO ocupacionDTO = new OcupacionDTO();
					ocupacionDTO.setHabitacion(LHab.get(x));
					ocupacionDTO.setCheckIn(checkIn);
					ocupacionDTO.setCheckOut(checkOut);
					LOcupacion.add(ocupacionDTO);
				}else if(c == 0 || checkIn == null) {
					checkIn = ArrayDates.get(x).get(c);
					checkOut = ArrayDates.get(x).get(c);
				}else if(ArrayDates.get(x).get(c).compareTo(checkOut) == 1){
					checkOut = ArrayDates.get(x).get(c);
					if(c == sizeDates-1){
						OcupacionDTO ocupacionDTO2 = new OcupacionDTO();
						ocupacionDTO2.setHabitacion(LHab.get(x));
						ocupacionDTO2.setCheckIn(checkIn);
						ocupacionDTO2.setCheckOut(checkOut);
						LOcupacion.add(ocupacionDTO2);
					}
				}else {
					checkOut = ArrayDates.get(x).get(c-1);
					OcupacionDTO ocupacionDTO = new OcupacionDTO();
					ocupacionDTO.setHabitacion(LHab.get(x));
					ocupacionDTO.setCheckIn(checkIn);
					ocupacionDTO.setCheckOut(checkOut);
					LOcupacion.add(ocupacionDTO);
					checkIn = ArrayDates.get(x).get(c);
					checkOut = ArrayDates.get(x).get(c);
					if(c == sizeDates-1){
						OcupacionDTO ocupacionDTO2 = new OcupacionDTO();
						ocupacionDTO2.setHabitacion(LHab.get(x));
						ocupacionDTO2.setCheckIn(checkIn);
						ocupacionDTO2.setCheckOut(checkOut);
						LOcupacion.add(ocupacionDTO2);
					}
				}
			}
		}

	}

	public void setResponsable(PasajeroBusquedaDTO pBusqueda){
		//for(OcupacionDTO o : LOcupacion){
		//	System.out.println("Responsable");
		LOcupacion.get(OcupacionCounter-1).setResponsable(pBusqueda);
		//}
	}
	public void setAcompanantes(List<PasajeroBusquedaDTO> LpBusqueda){
		//for(OcupacionDTO o : LOcupacion){
			//System.out.println("Acompanantes");
		List<PasajeroBusquedaDTO> Locupantes = LOcupacion.get(OcupacionCounter-1).getListaOcupantes();
		if(Locupantes != null) {
		for(PasajeroBusquedaDTO p : LpBusqueda){
				boolean op = true;
				for (PasajeroBusquedaDTO po : Locupantes) {
					if (po.getDbid() == p.getDbid()) {
						op = false;
					}
				}
				if (op) {
					Locupantes.add(p);
				}
			}
			LOcupacion.get(OcupacionCounter-1).setListaOcupantes(Locupantes);
		}else{
			LOcupacion.get(OcupacionCounter-1).setListaOcupantes(LpBusqueda);
		}

		//}
	}

	public void setOcupacionCounter(){
		OcupacionCounter = LOcupacion.size();
	}

	public void asignarResponsableAcompanantesporOcupacion(){
		if(OcupacionCounter > 0){
			asignarResponsableAcompanante();
		}else{
			GuardarOcupacion();
		}
	}

	public List<PasajeroBusquedaDTO> getPasajerosSeleccionados(JTable tabla){
		List<PasajeroBusquedaDTO>  LPasajeros = new ArrayList<>();
		int size = tabla.getRowCount();
		for (int c = 0; c < size; c++){
			if ((boolean) tabla.getModel().getValueAt(c, 0)){
				LPasajeros.add(lista.get(c));
			}
		}
		return LPasajeros;
	}


	public void asignarResponsableAcompanante(){
		BuscarOcuparGUI = new OcuparHabAsigPasajeroGUI(this);
		BuscarOcuparGUI.setVisible(true);
		BuscarOcuparGUI.getBtnSiguiente().addActionListener(e -> {
			try {
				AsignarResponsable();
				BuscarOcuparGUI.setVisible(false);
			}catch (NoConcordanciaException e1){
				BuscarOcuparGUI.mostrarError("Error de Busqueda", "No existen pasasjeros que cumplan con los requisitos de busqueda");
			}
		});
	}


	public void AsignarResponsable() throws NoConcordanciaException{
		this.buscarPasajero(0);
		TablaGUI.getBtnAceptar().addActionListener(e -> {
			setResponsable(getPasajerosSeleccionados(TablaGUI.getTabla()).get(0));
			try {
				AsignarAcompanantes();
			}catch (NoConcordanciaException e1){
				BuscarOcuparGUI.mostrarError("Error de Busqueda", "No existen pasasjeros que cumplan con los requisitos de busqueda");
			}
			TablaGUI.dispose();
		});

	}

	public void AsignarAcompanantes() throws NoConcordanciaException {
		BuscarOcuparGUI = new OcuparHabAsigPasajeroGUI(this);
		BuscarOcuparGUI.setVisible(true);
		BuscarOcuparGUI.getBtnSiguiente().removeActionListener(BuscarOcuparGUI.getBtnSiguiente().getActionListeners()[0]);
		BuscarOcuparGUI.getBtnSiguiente().addActionListener(e -> {
			try {
				this.buscarPasajero(1);
				BuscarOcuparGUI.setVisible(false);
				TablaGUI.getBtnAceptar().addActionListener(e2 -> {
					System.out.println("Acteptar ActionListener");
					setAcompanantes(getPasajerosSeleccionados(TablaGUI.getTabla()));
					TablaGUI.setVisible(false);
					TablaGUI.dispose();
					BuscarOcuparGUI.dispose();
					OcupacionCounter = OcupacionCounter - 1;
					if(OcupacionCounter >= 0){
						asignarResponsableAcompanantesporOcupacion();
					}
					});
			}catch(NoConcordanciaException e1){
				BuscarOcuparGUI.mostrarError("Error de Busqueda", "No existen pasasjeros que cumplan con los requisitos de busqueda");				}
		});


	}

	private void GuardarOcupacion(){
		ocupacionServicio.guardarOcupacion(LOcupacion);
	}


}
