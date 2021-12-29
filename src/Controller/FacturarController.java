package Controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DTO.*;
import Exceptions.CampoFacturarIncorrecto;
import Exceptions.CampoFaltanteException;
import Exceptions.NoConcordanciaException;
import GUI.FacturarElementosGUI;
import GUI.FacturarGUI;
import Servicios.FacturaServicio;
import Servicios.OcupacionServicio;
import Servicios.PersonaJuridicaServicio;

public class FacturarController {

	OcupacionDTO ocupacionDTO;

	Color Warning = new Color(255,0,0);
	Color Normal = new Color(128,128,128);

	List<UnidadesDTO> LItemsPendientes = new ArrayList<>();
	List<PasajeroBusquedaDTO> ocupantes;


	private Object responsable = null;

	double valorIVA = 0.105;
	char TipoFactura;
	
	private FacturarGUI facturaGUI;
	private FacturarElementosGUI facturarElementosGUI;
	private PersonaJuridicaServicio pjServicio;
	private FacturaServicio facturaServicio;
	private PersonaJuridicaDTO personaJuridica;
	private List<UnidadesDTO> ItemsPendientes;
	private boolean EstadiaFacturada = false;
	private	String opciones[] = {"Cancelar","Aceptar"};

	private double subtotal;
	private double iva;
	private double total;

	private LocalTime horaSalida;

	
	public FacturarController(FacturarGUI facturarGUI) {
		this.facturaGUI = facturarGUI;
		this.pjServicio = new PersonaJuridicaServicio();
		facturaServicio = new FacturaServicio();
		ocupantes = new ArrayList<PasajeroBusquedaDTO>();
		
	}
	
	public void checkOut() throws CampoFacturarIncorrecto, CampoFaltanteException{
		boolean bool = true;


		if(((facturaGUI.getTbxNumHabitacion().getText())).replaceAll("[\uFEFF-\uFFFF]", "").length() != 3){
			bool = false;
			facturaGUI.mostrarError("Error", "El numero de habitacion debe estar conformado por 3 (tres) digitos");
			highlightInput(facturaGUI.getLblNumHabitacion(), facturaGUI.getTbxNumHabitacion(),Warning);
		}else if(facturaGUI.getTbxNumHabitacion().getText().replaceAll("[0123456789]","").length() != 0){
			bool = false;
			facturaGUI.mostrarError("Error", "El numero de habitacion debe contener solo digitos numericos");
			highlightInput(facturaGUI.getLblNumHabitacion(), facturaGUI.getTbxNumHabitacion(),Warning);
		}else
		if((facturaGUI.getTbxNumHabitacion()).getText().isEmpty() || (facturaGUI.getTbxNumHabitacion()).getText().isBlank()) {
            bool = false;
            highlightInput(facturaGUI.getLblNumHabitacion(), facturaGUI.getTbxNumHabitacion(),Warning);
		}else{
			highlightInput(facturaGUI.getLblNumHabitacion(), facturaGUI.getTbxNumHabitacion(),Normal);}

		if(facturaGUI.getTbxHoraSalida().getText().isEmpty() || facturaGUI.getTbxHoraSalida().getText().isBlank()) {
			bool = false;
            highlightInput(facturaGUI.getLblHoraDeSalida(), facturaGUI.getTbxHoraSalida(),Warning);			
		}else
		if(!(facturaGUI.getTbxHoraSalida().getText().replaceAll("[\uFEFF-\uFFFF]", "").length() == 5 &&
     		facturaGUI.getTbxHoraSalida().getText().replaceAll("[0123456789]", "").equals(":"))){
			bool = false;
			highlightInput(facturaGUI.getLblHoraDeSalida(), facturaGUI.getTbxHoraSalida(),Warning);
			facturaGUI.mostrarError("Error", "La hora de salida debe tener el formato HH:MM");
		}else{
			highlightInput(facturaGUI.getLblHoraDeSalida(), facturaGUI.getTbxHoraSalida(),Normal);
		}

		
		if(bool) {
			horaSalida = LocalTime.parse(facturaGUI.getTbxHoraSalida().getText(), DateTimeFormatter.ofPattern("HH:mm"));
			try {
				ocupantes = buscarOcupantesHabitacion();
				JTable table = facturaGUI.getTablePasajero();
				DefaultTableModel model = facturaGUI.getModel();
				model.setRowCount(0);
				if (ocupantes != null) {
					for (PasajeroBusquedaDTO p : ocupantes) {
						model.insertRow(table.getRowCount(), new Object[]{false, p.getApellido(), p.getNombre(), p.getNdoc(), p.getTipodoc()});
					}
				}
				obtenerItemsPendientes();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<UnidadesDTO> obtenerItemsPendientes(){
		List<FacturaDTO> Lfacturas = new ArrayList<>();
		List<UnidadesDTO> LItemsFacturados = new ArrayList<>();

		Lfacturas = facturaServicio.getFacturasOcuapcion(ocupacionDTO);


		for (FacturaDTO f : Lfacturas) {
			int size = f.getDetalle().getListaItems().size();
			if(f.getEstadia() != null){EstadiaFacturada = true;}
			for (int c = 0; c < size; c++) {
				LItemsFacturados.add(f.getDetalle().getListaItems().get(c));
			}
		}
		List<UnidadesDTO> LFinal = new ArrayList<>();
		for(UnidadesDTO uocup : ocupacionDTO.getConsumo().getListaItems()){
			boolean op = true;
			for(UnidadesDTO u : LItemsFacturados){
				if(uocup.getId_item() == u.getId_item() && uocup.getCantidad() == u.getCantidad()){
					op = false;
					break;
				}
			}
			if(op){
				LFinal.add(uocup);
			}
		}
		ItemsPendientes = LFinal;
		ocupacionDTO.getConsumo().getListaItems();



		return ItemsPendientes;
	}
	
	public List<PasajeroBusquedaDTO> buscarOcupantesHabitacion() {
		OcupacionServicio ocupacionServicio = new OcupacionServicio();
		ocupacionDTO = ocupacionServicio.buscarOcupacionActual(
				Integer.parseInt(facturaGUI.getTbxNumHabitacion().getText().substring(facturaGUI.getTbxNumHabitacion().getText().length()-2)),
				Integer.parseInt(facturaGUI.getTbxNumHabitacion().getText().substring(0,1)));
		if(ocupacionDTO == null){
			facturaGUI.mostrarError("Ocupacion no existente", "No existe una ocupacion activa para la habitacion seleccionada");
			return null;
		}else {
			List<PasajeroBusquedaDTO> LPasajeros = new ArrayList<>();
			LPasajeros = ocupacionDTO.getListaOcupantes();

			boolean bool = true;
			for(PasajeroBusquedaDTO p : LPasajeros){
				if (p.getDbid() == ocupacionDTO.getResponsable().getDbid()) {
					bool = false;
					break;
				}
			}
			if(bool) {
				LPasajeros.add(ocupacionDTO.getResponsable());
			}
			return LPasajeros;
		}
	}
	
	public void informarError(){
        if(facturaGUI.optionMessageGUI(
                "Error",
                "Numero de habitacion faltante, incorrecto o habitacion no ocupada; hora faltante o incorrecta",
                new String[]{"OK"}) == JOptionPane.YES_OPTION){
        }else{
        	facturaGUI.getTbxNumHabitacion().requestFocus();
        	facturaGUI.getTbxNumHabitacion().setBorder(BorderFactory.createLineBorder(Warning));
        	facturaGUI.getTbxHoraSalida().setBorder(BorderFactory.createLineBorder(Warning));
        	facturaGUI.getTbxHoraSalida().setForeground(Warning);
        }
	}
	 public void highlightInput(JComponent label, JComponent container, Color highlightColor){
	        label.setForeground(highlightColor);
	        container.setBorder(BorderFactory.createLineBorder(highlightColor));
	    }

	public void facturar() throws NoConcordanciaException {

		if(facturaGUI.getCbxFacturaTercero().isSelected()) {
			facturarTercero();
		}else{
			facturarConsumidorFinal();
		}
	}

	private void facturarConsumidorFinal(){
		TipoFactura = 'B';
		JTable table = facturaGUI.getTablePasajero();
		int size = table.getRowCount();
		int selected = -1;
		for(int c = 0; c < size; c++){
			if((Boolean) table.getValueAt(c,0)){
				selected = c;
				break;
			}
		}
		if(selected >= 0) {
			Period p = Period.between(ocupantes.get(selected).getFechaNacimiento(), LocalDate.now());
			if(p.getYears() >= 17) {
				responsable = ocupantes.get(selected);
				facturarElementos();
			}else{
				facturaGUI.mostrarError("Error", "El responsable seleccionado es menor a 18 a�os, por favor seleccione a otra persona como responsable");
			}
		}else{
			facturaGUI.mostrarError("No se ha seleccionado un responsable", "Por favor seleccione a uno de los ocupantes como responsable");
		}
	}

	private void facturarElementos(){

		if(TipoFactura == 'B') {
			facturarElementosGUI = new FacturarElementosGUI(this,
					((PasajeroBusquedaDTO) responsable).getApellido() + " ," + ((PasajeroBusquedaDTO) responsable).getNombre(),
					"" + TipoFactura);
		}else{
			facturarElementosGUI = new FacturarElementosGUI(this,
					((PersonaJuridicaDTO) responsable).getRazonSocial(),
					"" + TipoFactura);
		}
		facturarElementosGUI.setVisible(true);
		facturarElementosGUI.getCbxEstadia().setEnabled(!EstadiaFacturada);
		JTable table = facturarElementosGUI.getTable();
		DefaultTableModel model = facturarElementosGUI.getModel();
		model.addColumn("Consumos de la Habitacion");
		model.addColumn("Precio");
		model.addColumn("");
		table.getColumnModel().getColumn(0).setMaxWidth(300);
		table.getColumnModel().getColumn(1).setMaxWidth(75);
		table.getColumnModel().getColumn(2).setMaxWidth(50);
		List<UnidadesDTO> LItems = ItemsPendientes;
		model.setRowCount(0);
		for(UnidadesDTO item : LItems){
			model.addRow(new Object[]{item.getNombre()+" X"+item.getCantidad(), item.getCostoUnitario()*item.getCantidad(), false});
		}
		facturarElementosGUI.getCbxEstadia().setText("Valor de la estadia: "+ "$" + getValorEstadia(ocupacionDTO));
		updateTotalValue();
	}

	public void updateTotalValue(){
		subtotal = calcularSubtotal();
		String StrSubTotal = "" + Math.round(  subtotal * 100) / 100.0;
		facturarElementosGUI.getLblValorSubtotal().setText(" $ "+StrSubTotal);

		iva = calcularIVA(subtotal);
		String StrIva = "" + iva;
		facturarElementosGUI.getLblValorIVA().setText(" $ "+StrIva);

		total = subtotal+iva;
		String StrTotal = "" + Math.round(  total * 100) / 100.0;
		facturarElementosGUI.getLblValorTotal().setText(" $ "+StrTotal);
	}

	private double calcularSubtotal(){
		double subtotal = 0.0;
		if(facturarElementosGUI.getCbxEstadia().isSelected()){
			subtotal = subtotal + Double.parseDouble(new String(""+getValorEstadia(ocupacionDTO)));
		}
		JTable table = facturarElementosGUI.getTable();
		int size = table.getRowCount();
		for(int c = 0; c < size; c++){
			if((Boolean) table.getValueAt(c,2)){
				subtotal = subtotal + (double) table.getValueAt(c,1);
			}
		}

		return subtotal;
	}

	private double calcularIVA(double subtotal){
		double iva = 0.0;
		iva = Math.round(  valorIVA*subtotal * 100) / 100.0;
		return iva;
	}

	private double getValorEstadia(OcupacionDTO unOcupacionDTO) {
		double valor = 0.0;
		if (unOcupacionDTO != null) {
			int dias = unOcupacionDTO.getCheckOut().compareTo(unOcupacionDTO.getCheckIn());
			double valorxDia = unOcupacionDTO.getHabitacion().getValordiario();
			valor = Math.round(valorxDia * dias * 100) / 100.0;

		if (horaSalida.isAfter(LocalTime.of(11, 0))) {
			if (horaSalida.isAfter(LocalTime.of(18, 0))) {
				return valor;
			} else {
				double ddias = unOcupacionDTO.getCheckOut().compareTo(unOcupacionDTO.getCheckIn()) + 0.5;
				valorxDia = unOcupacionDTO.getHabitacion().getValordiario();
				valor = Math.round(valorxDia * ddias * 100) / 100.0;
				return valor;
			}
		}

	}
	return valor;
	}

	private void facturarTercero() throws NoConcordanciaException {
		TipoFactura = 'A';
		//Factura por 3ro
		if(facturaGUI.getTbxCuit().getText().isBlank() || facturaGUI.getTbxCuit().getText().isEmpty()) {
			JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(facturaGUI);
			JOptionPane.showMessageDialog(padre,"Pasamos al CU03","No Ingreso CUIT",JOptionPane.ERROR_MESSAGE);
		}else {
			personaJuridica = pjServicio.getPersonaJuridica(facturaGUI.getTbxCuit().getText());
			if(personaJuridica == null) {
				//facturaGUI.mostrarError("Erorr", "No se ha encontrado una persona juridica que coincida con el CUIT de busqueda");
				throw new NoConcordanciaException();
			}else {
				if(optionMessageGUI("Desea Facturar al Tercero?", personaJuridica.getRazonSocial(), opciones) == 1) {
					responsable = personaJuridica;
					facturarElementos();
					//facturarElementosGUI = new FacturarElementosGUI(this, ""+personaJuridica.getRazonSocial(), "A");
					//facturaGUI.dispose();
					//facturarElementosGUI.setVisible(true);
				}
				else {
					facturaGUI.getTbxCuit().setText("");
				}
			}
		}

	}
	
	public int optionMessageGUI(String titulo, String detalle, Object[] options){
		JFrame padre = (JFrame) SwingUtilities.getWindowAncestor(facturaGUI);
		return JOptionPane.showOptionDialog(padre,
				detalle,titulo,JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,     //do not use a custom Icon
				options,  //the titles of buttons
				options[0]);
	}

	public void guardarFactura(){
		if(total == 0){
			facturaGUI.mostrarError("Error", "Seleccione al menos un elemento a facturar");
		}else{
			FacturaDTO facturaDTO = new FacturaDTO();
			facturaDTO.setFecha(LocalDate.now());
			facturaDTO.setMontoTotal(subtotal);
			facturaDTO.setMontoIVA(iva);
			facturaDTO.setNotaDeCredito(false);
			facturaDTO.setTipo(new String(""+TipoFactura));
			facturaDTO.setPago(false);
			facturaDTO.setResponsable(responsable);
			facturaDTO.setDetalle(generarDetalle());
			facturaDTO.setEstadia(generarEstadia());
			facturaDTO.setId_ocupacion(ocupacionDTO.getId());

			FacturaServicio facturaServicio = new FacturaServicio();
			facturaDTO.getId_factura();
			facturaServicio.guardarFactura(facturaDTO);
			facturaGUI.mostrarError("Exito", "La factura ha sido cargada al sistema");
			facturaGUI.dispose();
			facturarElementosGUI.dispose();
		}
	}

	public DetalleFacturaDTO generarDetalle(){
		JTable table = facturarElementosGUI.getTable();
		int size = table.getRowCount();
		DetalleFacturaDTO detalle = new DetalleFacturaDTO();
		List<UnidadesDTO> Lunidades = new ArrayList<>();
		for(int c = 0; c < size; c++){
			if((Boolean) table.getValueAt(c,2)) {
				Lunidades.add(ocupacionDTO.getConsumo().getListaItems().get(c));
			}
		}
		detalle.setListaItems(Lunidades);

		return detalle;
	}

	public PeriodoEstadiaDTO generarEstadia(){
		PeriodoEstadiaDTO estadiaDTO = null;
		if(facturarElementosGUI.getCbxEstadia().isSelected()) {
			estadiaDTO = new PeriodoEstadiaDTO();
			if (horaSalida.isAfter(LocalTime.of(11, 0))) {
				if (horaSalida.isAfter(LocalTime.of(18, 0))) {
					estadiaDTO.setMediaEstadia(false);
					//todo OcuparHabitacion y facturar
				} else {
					estadiaDTO.setMediaEstadia(true);
				}
			} else {
				estadiaDTO.setMediaEstadia(false);
			}
			estadiaDTO.setFechaInicio(ocupacionDTO.getCheckIn());
			estadiaDTO.setFechaFinal(ocupacionDTO.getCheckOut());
			estadiaDTO.setMonto(getValorEstadia(ocupacionDTO));
		}
		return estadiaDTO;
	}

}
