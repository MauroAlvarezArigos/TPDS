package Controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DTO.OcupacionDTO;
import DTO.PasajeroBusquedaDTO;
import DTO.UnidadesDTO;
import Dominio.PersonaJuridica;
import Exceptions.CampoFacturarIncorrecto;
import Exceptions.CampoFaltanteException;
import Exceptions.NoConcordanciaException;
import GUI.FacturarElementosGUI;
import GUI.FacturarGUI;
import Servicios.OcupacionServicio;
import Servicios.PersonaJuridicaServicio;

public class FacturarController {

	OcupacionDTO ocupacionDTO;

	Color Warning = new Color(255,0,0);
	Color Normal = new Color(128,128,128);

	List<PasajeroBusquedaDTO> ocupantes;
	PasajeroBusquedaDTO responsable = null;

	double valorIVA = 0.105;
	char TipoFactura;
	
	private FacturarGUI facturaGUI;
	private FacturarElementosGUI facturarElementosGUI;
	private PersonaJuridicaServicio pjServicio;
	private PersonaJuridica personaJuridica;
	private	String opciones[] = {"Cancelar","Aceptar"}; 

	
	public FacturarController(FacturarGUI facturarGUI) {
		this.facturaGUI = facturarGUI;
		this.pjServicio = new PersonaJuridicaServicio();
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
			try {
				ocupantes = buscarOcupantesHabitacion();
				JTable table = facturaGUI.getTablePasajero();
				DefaultTableModel model = facturaGUI.getModel();
				if (ocupantes != null) {
					for (PasajeroBusquedaDTO p : ocupantes) {
						model.insertRow(table.getRowCount(), new Object[]{false, p.getApellido(), p.getNombre(), p.getNdoc(), p.getTipodoc()});
					}
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
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
		
		//Recordar Si facturas por tercero el tipo de factura es A, si no es B
		
		if(facturaGUI.getCbxFacturaTercero().isSelected()) {
			facturarTercero();
		}else{
			facturarConsumidorFinal();
		}
	}

	private void facturarConsumidorFinal(){
		TipoFactura = 'A';
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
			responsable = ocupantes.get(selected);
			facturarElementos();
		}else{
			facturaGUI.mostrarError("No se ha seleccionado un responsable", "Por favor seleccione a uno de los ocupantes como responsable");
		}
	}

	private void facturarElementos(){
		facturarElementosGUI = new FacturarElementosGUI(this, responsable.getApellido()+" ,"+responsable.getNombre(), new String(""+TipoFactura));
		facturarElementosGUI.setVisible(true);
		JTable table = facturarElementosGUI.getTable();
		DefaultTableModel model = facturarElementosGUI.getModel();
		model.addColumn("Consumos de la Habitacion");
		model.addColumn("Precio");
		model.addColumn("");
		table.getColumnModel().getColumn(0).setMaxWidth(300);
		table.getColumnModel().getColumn(1).setMaxWidth(75);
		table.getColumnModel().getColumn(2).setMaxWidth(50);
		List<UnidadesDTO> LItems = ocupacionDTO.getConsumo().getListaItems();
		for(UnidadesDTO item : LItems){
			model.addRow(new Object[]{item.getNombre()+" X"+item.getCantidad(), item.getCostoUnitario()*item.getCantidad(), false});
		}
		facturarElementosGUI.getCbxEstadia().setText("Valor de la estadia: "+ "$" + getValorEstadia(ocupacionDTO));
	}

	public void updateTotalValue(){
		double subtotal = calcularSubtotal();
		String StrSubTotal = "" + Math.round(  subtotal * 100) / 100.0;
		facturarElementosGUI.getLblValorSubtotal().setText(" $ "+StrSubTotal);

		double iva = calcularIVA(subtotal);
		String StrIva = "" + iva;
		facturarElementosGUI.getLblValorIVA().setText(" $ "+StrIva);

		double total = subtotal+iva;
		String StrTotal = "" + Math.round(  total * 100) / 100.0;
		facturarElementosGUI.getLblValorTotal().setText(" $ "+StrTotal);
	}

	private double calcularSubtotal(){
		double subtotal = 0.0;
		if(facturarElementosGUI.getCbxEstadia().isSelected()){
			subtotal = subtotal + Double.parseDouble(getValorEstadia(ocupacionDTO));
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
		if(TipoFactura == 'A'){iva = Math.round(  valorIVA*subtotal * 100) / 100.0;}
		return iva;
	}

	private String getValorEstadia(OcupacionDTO unOcupacionDTO){
		String valor = "0";
		if(unOcupacionDTO != null) {
			int dias = unOcupacionDTO.getCheckOut().compareTo(unOcupacionDTO.getCheckIn());
			double valorxDia = unOcupacionDTO.getHabitacion().getValordiario();
			double valorfinal = Math.round(valorxDia * dias * 100) / 100.0;
			valor = "" + valorfinal;
		}
		return valor;
	}

	private void facturarTercero() throws NoConcordanciaException {
		TipoFactura = 'B';
		//Factura por 3ro
		if(facturaGUI.getTbxCuit().getText().isBlank() || facturaGUI.getTbxCuit().getText().isEmpty()) {
			JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(facturaGUI);
			JOptionPane.showMessageDialog(padre,"Pasamos al CU03","No Ingreso CUIT",JOptionPane.ERROR_MESSAGE);
		}else {
			personaJuridica = pjServicio.getPersonaJuridica(facturaGUI.getTbxCuit().getText());
			if(personaJuridica == null) {
				throw new NoConcordanciaException();
			}else {
				if(optionMessageGUI("Desea Facturar al Tercero?", personaJuridica.getRazonSocial(), opciones) == 1) {
					FacturarElementosGUI fe = new FacturarElementosGUI(this, personaJuridica.getRazonSocial(), "A");
					facturaGUI.dispose();
					fe.setVisible(true);
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
}
