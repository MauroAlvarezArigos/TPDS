package Controller;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import Exceptions.CampoFacturarIncorrecto;
import GUI.FacturarGUI;

public class FacturarController {

	Color Warning = new Color(255,0,0);
	
	private FacturarGUI facturaGUI;
	
	public void InformarOmisionnesDatosGUI(){
        boolean bool = true;
        if(facturaGUI.gettextNumHabitacion().equals("") || facturaGUI.gettextHoraSalida() == null){
            bool = false;
            highlightInput(facturaGUI.gettextNumHabitacion(), facturaGUI.gettextHoraSalida(),Warning);
        }
        boolean success = false;
        if(bool){
            try {
            	prueba();
            }catch (CampoFacturarIncorrecto e){
                informarError();
            }
            if (success){
                
            }
        }
	}
	
	public void prueba() throws CampoFacturarIncorrecto{
		
	}
	
	public void informarError(){
        if(facturaGUI.optionMessageGUI(
                "Error",
                "N�mero de habitaci�n faltante, incorrecto o habitaci�n no ocupada; hora faltante o incorrecta",
                new String[]{"OK"}) == JOptionPane.YES_OPTION){
        }else{
        	facturaGUI.gettextNumHabitacion().requestFocus();
        	facturaGUI.gettextNumHabitacion().setBorder(BorderFactory.createLineBorder(Warning));
        	facturaGUI.gettextHoraSalida().setBorder(BorderFactory.createLineBorder(Warning));
        	facturaGUI.gettextHoraSalida().setForeground(Warning);
        }
	}
	 public void highlightInput(JComponent label, JComponent container, Color highlightColor){
	        Color Red = new Color(255,0,0);
	        label.setForeground(Red);
	        container.setBorder(BorderFactory.createLineBorder(Red));
	    }
}
