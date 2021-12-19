package Controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DTO.OcupacionDTO;
import DTO.PasajeroBusquedaDTO;
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
	List<PasajeroBusquedaDTO> ocupantes;
	
	private FacturarGUI facturaGUI;
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
		
		if((facturaGUI.getTbxNumHabitacion()).getText().isEmpty() || (facturaGUI.getTbxNumHabitacion()).getText().isBlank()) {
            bool = false;
            highlightInput(facturaGUI.getLblNumHabitacion(), facturaGUI.getTbxNumHabitacion(),Warning);
		}
		else if(facturaGUI.getTbxHoraSalida().getText().isEmpty() || facturaGUI.getTbxHoraSalida().getText().isBlank()) {
			bool = false;
            highlightInput(facturaGUI.getLblHoraDeSalida(), facturaGUI.getTbxHoraSalida(),Warning);			
		}

		bool = true;
		
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
	        Color Red = new Color(255,0,0);
	        label.setForeground(Red);
	        container.setBorder(BorderFactory.createLineBorder(Red));
	    }

	public void facturar() throws NoConcordanciaException {
		
		//Recordar Si facturas por tercero el tipo de factura es A, si no es B
		
		if(facturaGUI.getCbxFacturaTercero().isSelected()) {
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
