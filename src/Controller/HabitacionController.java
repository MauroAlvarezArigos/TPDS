package Controller;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import Exceptions.DesdeMayorException;
import Exceptions.FechaIncorrectaException;
import GUI.MostrarEstadoHabitacionGUI;

public class HabitacionController {
	MostrarEstadoHabitacionGUI meh;
	
	
	public HabitacionController(MostrarEstadoHabitacionGUI mostrarEstado) {
		this.meh = mostrarEstado;
	}
	
	public void mostrarEstado() throws DesdeMayorException, FechaIncorrectaException{
		
		if(meh.getDesde().getModel().getValue() == null) {
			System.out.println("Desde NULL");
			throw new FechaIncorrectaException();
			
		}
		if(meh.getHasta().getModel().getValue() == null) {
			System.out.println("Hasta NULL");
			throw new FechaIncorrectaException();
		}
		else {
			Date selectedDesde = (Date) meh.getDesde().getModel().getValue();
			Date selectedHasta = (Date) meh.getHasta().getModel().getValue();
		
			System.out.println("Desde: "+selectedDesde.getTime());
			System.out.println("Hasta: "+selectedHasta.getTime());
			
			long diff = selectedHasta.getTime() - selectedDesde.getTime();
	        TimeUnit time = TimeUnit.DAYS; 
	        long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
	        
	        if(diffrence>0) {
	        	System.out.println("Fecha Valida");
	        }
	        else {
	        	throw new DesdeMayorException();
	        }
		}
		
	}
	
	

}
