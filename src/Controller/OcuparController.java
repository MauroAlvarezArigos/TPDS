package Controller;

import java.awt.*;
import java.time.Period;
import java.util.List;

import javax.swing.JComboBox;

import DTO.IDTypeDTO;
import DTO.PasajeroBusquedaDTO;
import Exceptions.CapacidadExcedidaException;
import Exceptions.NoConcordanciaException;
import GUI.OcupacionSolicitadaGUI;
import GUI.OcuparHabAsigPasajeroGUI;
import Servicios.IDTypeServicio;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

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
	private OcupacionSolicitadaGUI ocupacionSolicitadaGUI;

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
				}else {
					Period period = Period.between(checkOut, ArrayDates.get(x).get(c));
					if (period.getDays() == 1) {
						checkOut = ArrayDates.get(x).get(c);
						if (c == sizeDates - 1) {
							OcupacionDTO ocupacionDTO2 = new OcupacionDTO();
							ocupacionDTO2.setHabitacion(LHab.get(x));
							ocupacionDTO2.setCheckIn(checkIn);
							ocupacionDTO2.setCheckOut(checkOut);
							LOcupacion.add(ocupacionDTO2);
						}
					} else {
						checkOut = ArrayDates.get(x).get(c - 1);
						OcupacionDTO ocupacionDTO = new OcupacionDTO();
						ocupacionDTO.setHabitacion(LHab.get(x));
						ocupacionDTO.setCheckIn(checkIn);
						ocupacionDTO.setCheckOut(checkOut);
						LOcupacion.add(ocupacionDTO);
						checkIn = ArrayDates.get(x).get(c);
						checkOut = ArrayDates.get(x).get(c);
						if (c == sizeDates - 1) {
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

	}

	public void setResponsable(PasajeroBusquedaDTO pBusqueda){
		//for(OcupacionDTO o : LOcupacion){
		//	System.out.println("Responsable");
		LOcupacion.get(OcupacionCounter-1).setResponsable(pBusqueda);
		//}
	}
	public void setAcompanantes(List<PasajeroBusquedaDTO> LpBusqueda) throws CapacidadExcedidaException{
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
		if(Locupantes.size() < LOcupacion.get(OcupacionCounter-1).getHabitacion().getCapacidad()) {
			LOcupacion.get(OcupacionCounter - 1).setListaOcupantes(Locupantes);
		}else{
			throw new CapacidadExcedidaException();
		}
		}else{
			if(LpBusqueda.size() < LOcupacion.get(OcupacionCounter-1).getHabitacion().getCapacidad()) {
				LOcupacion.get(OcupacionCounter - 1).setListaOcupantes(LpBusqueda);
			}else{
				throw new CapacidadExcedidaException();
			}
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
			ocupacionSolicitadaGUI = new OcupacionSolicitadaGUI(this);

			for(OcupacionDTO o : LOcupacion){
				List<String> LOcupantes = new ArrayList<>();
				if(o.getListaOcupantes() == null){
					LOcupantes.add("");
				}else{
				for (PasajeroBusquedaDTO p : o.getListaOcupantes()){
					LOcupantes.add(p.getApellido()+", "+p.getNombre());
				}}
				ocupacionSolicitadaGUI.addPanelOcupacion(o.getHabitacion().getNumero(), o.getHabitacion().getTipo(), ""+o.getCheckIn(), ""+o.getCheckOut(),
						o.getResponsable().getApellido()+", "+o.getResponsable().getNombre(), LOcupantes);
			}

			ocupacionSolicitadaGUI.setVisible(true);

			GuardarOcupacion();

		}
	}

	public void opcionFinal(){
		if(BuscarOcuparGUI.optionMessageGUI("Opciones", "Elija alguna de las siguientes opciones", new Object[]{"Cargar otra habitacion", "Salir"})
				!= 1){
			OcuparHabitacionGUI ocuparHabitacion = new OcuparHabitacionGUI();
			ocuparHabitacion.setLocationRelativeTo(null);
			ocuparHabitacion.setVisible(true);
			ocupacionSolicitadaGUI.dispose();
		}else{
			ocupacionSolicitadaGUI.dispose();
			BuscarOcuparGUI.dispose();
			TablaGUI.dispose();
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
		BuscarOcuparGUI = new OcuparHabAsigPasajeroGUI(this,LOcupacion.get(OcupacionCounter-1).getHabitacion().getNumero() ,true);
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
		TablaGUI.getBtnAceptar().setEnabled(false);
		TablaGUI.getBtnVolver().addActionListener(e -> {
			asignarResponsableAcompanante();
			TablaGUI.setVisible(false);
			TablaGUI.dispose();
		});
		TablaGUI.getTabla().getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				TablaGUI.getBtnAceptar().setEnabled(true);
			}
		});
		TablaGUI.getBtnAceptar().addActionListener(e -> {
			LocalDate fechaNacimiento = getPasajerosSeleccionados(TablaGUI.getTabla()).get(0).getFechaNacimiento();
			Period edad = Period.between(fechaNacimiento, LocalDate.now());
			if (edad.getYears() < 18) {
				BuscarOcuparGUI.mostrarError("Error", "El responable seleccionado no es mayor de edad, por favor seleccione a una persona mayor a " +
						"18 a?os como responsable");
					TablaGUI.setVisible(false);
					TablaGUI.dispose();
					asignarResponsableAcompanante();
			} else {
				setResponsable(getPasajerosSeleccionados(TablaGUI.getTabla()).get(0));
			try {
				if (LOcupacion.get(OcupacionCounter - 1).getHabitacion().getCapacidad() == 1) {
					OcupacionCounter = OcupacionCounter - 1;
					if (OcupacionCounter >= 0) {
						asignarResponsableAcompanantesporOcupacion();
					}
				} else {
					AsignarAcompanantes();
				}
			} catch (NoConcordanciaException e1) {
				BuscarOcuparGUI.mostrarError("Error de Busqueda", "No existen pasasjeros que cumplan con los requisitos de busqueda");
			}
			TablaGUI.dispose();

		}
		});

	}

	public void AsignarAcompanantes() throws NoConcordanciaException{
		BuscarOcuparGUI = new OcuparHabAsigPasajeroGUI(this,LOcupacion.get(OcupacionCounter-1).getHabitacion().getNumero() ,false);
		BuscarOcuparGUI.setVisible(true);
		BuscarOcuparGUI.getBtnSiguiente().removeActionListener(BuscarOcuparGUI.getBtnSiguiente().getActionListeners()[0]);
		BuscarOcuparGUI.getBtnSiguiente().addActionListener(e -> {
			try {
				this.buscarPasajero(1);
				BuscarOcuparGUI.setVisible(false);
				JButton btnOtro = new JButton("Asignar otro");
				btnOtro.setBounds(100, 200, 90, 25);
				TablaGUI.getBtnVolver().addActionListener(e4 -> {
					try {
						AsignarAcompanantes();
					} catch (NoConcordanciaException ex) {
						ex.printStackTrace();
					}
					TablaGUI.setVisible(false);
					TablaGUI.dispose();
				});
				TablaGUI.getBoton().add(btnOtro);
				btnOtro.setBackground(Color.GREEN);
				btnOtro.setForeground(Color.WHITE);
				btnOtro.addActionListener(e1 -> {
					try {
						setAcompanantes(getPasajerosSeleccionados(TablaGUI.getTabla()));
					} catch (CapacidadExcedidaException ex) {
						BuscarOcuparGUI.mostrarError("Capacidad excedida", "La capacidad de la habitacion ha sido excedida");
						try {
							AsignarAcompanantes();
						} catch (NoConcordanciaException exc) {
							exc.printStackTrace();
						}
						TablaGUI.setVisible(false);
						TablaGUI.dispose();
						ex.printStackTrace();
					}
					TablaGUI.setVisible(false);
					TablaGUI.dispose();
					try{
						this.AsignarAcompanantes();
					}catch (NoConcordanciaException x){

					}

				});
				TablaGUI.getBtnAceptar().addActionListener(e2 -> {
					try {
						setAcompanantes(getPasajerosSeleccionados(TablaGUI.getTabla()));
						TablaGUI.setVisible(false);
						TablaGUI.dispose();
						BuscarOcuparGUI.dispose();
						OcupacionCounter = OcupacionCounter - 1;
						if(OcupacionCounter >= 0){
							asignarResponsableAcompanantesporOcupacion();
						}
					} catch (CapacidadExcedidaException ex) {
						BuscarOcuparGUI.mostrarError("Capacidad excedida", "La capacidad de la habitacion ha sido excedida");
						ex.printStackTrace();
						try {
							AsignarAcompanantes();
						} catch (NoConcordanciaException exc) {
							exc.printStackTrace();
						}
						TablaGUI.setVisible(false);
						TablaGUI.dispose();
					}
					});
			}catch(NoConcordanciaException e1){
				BuscarOcuparGUI.mostrarError("Error de Busqueda", "No existen pasasjeros que cumplan con los requisitos de busqueda");				}
		});


	}

	private void GuardarOcupacion(){
		for(OcupacionDTO o : LOcupacion){
			if(o.getListaOcupantes() == null){
				o.setListaOcupantes(new ArrayList<>());
			}
		}
		ocupacionServicio.guardarOcupacion(LOcupacion);
	}


}
