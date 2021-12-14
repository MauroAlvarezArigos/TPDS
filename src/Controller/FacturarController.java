package Controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import DTO.OcupacionDTO;
import DTO.PasajeroDTO;
import Dominio.PersonaJuridica;
import Exceptions.CampoFacturarIncorrecto;
import Exceptions.CampoFaltanteException;
import Exceptions.NoConcordanciaException;
import GUI.FacturarElementosGUI;
import GUI.FacturarGUI;
import Servicios.HabitacionServicio;
import Servicios.PersonaJuridicaServicio;

public class FacturarController {

	Color Warning = new Color(255,0,0);
	
	OcupacionDTO ocupantes;
	
	private FacturarGUI facturaGUI;
	private PersonaJuridicaServicio pjServicio;
	private PersonaJuridica personaJuridica;
	private	String opciones[] = {"Cancelar","Aceptar"}; 

	
	public FacturarController(FacturarGUI facturarGUI) {
		this.facturaGUI = facturarGUI;
		this.pjServicio = new PersonaJuridicaServicio();
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
		
		if(bool) {
			try {
				System.out.println("ANA: "+Integer.parseInt(facturaGUI.getTbxNumHabitacion().getText().substring(1, 3)));
				System.out.println("SHE: "+Integer.parseInt(facturaGUI.getTbxNumHabitacion().getText().substring(0, 1)));
				buscarOcupantesHabitacion();
				//Armar la tabla con los ocupantes
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public OcupacionDTO buscarOcupantesHabitacion() {
		HabitacionServicio hs = new HabitacionServicio();
		return hs.getOcupantes(facturaGUI.getTbxNumHabitacion().getText());
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
		else {
			//No facturar por tercero
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
