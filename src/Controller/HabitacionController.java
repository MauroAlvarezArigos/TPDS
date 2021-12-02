package Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;
import javax.swing.text.DateFormatter;

import Exceptions.DesdeMayorException;
import Exceptions.FechaIncorrectaException;
import GUI.EstadoHabitacionesGUI;
import GUI.MostrarEstadoHabitacionGUI;

public class HabitacionController {
	private static final String ZoneId = null;
	MostrarEstadoHabitacionGUI meh;
	
	
	public HabitacionController(MostrarEstadoHabitacionGUI mostrarEstado) {
		this.meh = mostrarEstado;
	}
	
	public void mostrarEstado() throws DesdeMayorException, FechaIncorrectaException, ParseException{
		
		if(meh.getDesde().getModel().getValue() == null) {
			System.out.println("Desde NULL");
			throw new FechaIncorrectaException();
			
		}
		if(meh.getHasta().getModel().getValue() == null) {
			System.out.println("Hasta NULL");
			throw new FechaIncorrectaException();
		}
		else {
			Calendar dateDesde = Calendar.getInstance();
			dateDesde.set(meh.getDesde().getModel().getYear()
						 ,meh.getDesde().getModel().getMonth()
						 ,meh.getDesde().getModel().getDay());
        	Date desde = dateDesde.getTime();             
        	
        	Calendar dateHasta = Calendar.getInstance();
			dateHasta.set(meh.getHasta().getModel().getYear()
						 ,meh.getHasta().getModel().getMonth()
						 ,meh.getHasta().getModel().getDay());
        	Date hasta = dateHasta.getTime();             
        	
			if(desde.getTime() < hasta.getTime()) {
				System.out.println("Fecha Valida");
	        	SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
	        	String date1 = format1.format(desde); 
	        	String date2 = format1.format(hasta); 

	        	System.out.println(date1);   
	        	System.out.println(date2);   
	        	EstadoHabitacionesGUI eh = new EstadoHabitacionesGUI(desde, hasta);
	        	eh.setVisible(true);
			}
	        else {
	        	throw new DesdeMayorException();
	        }
		}
		
	}
	
	

}
