package Controller;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import DTO.HabitacionDTO;
import Exceptions.DesdeMayorException;
import Exceptions.FechaIncorrectaException;
import GUI.EstadoHabitacionesGUI;
import GUI.MostrarEstadoHabitacionGUI;
import Servicios.HabitacionServicio;

public class HabitacionController {
	private HabitacionServicio habServicio;
	private static final String ZoneId = null;
	MostrarEstadoHabitacionGUI MostarEstadoGUI;
	List<HabitacionDTO> LHab = new ArrayList<>();
	
	
	public HabitacionController(MostrarEstadoHabitacionGUI mostrarEstado) {
		this.MostarEstadoGUI = mostrarEstado;
		this.habServicio = new HabitacionServicio();
	}
	
	public void mostrarEstado() throws DesdeMayorException, FechaIncorrectaException, ParseException{
		
		if(MostarEstadoGUI.getDesde().getModel().getValue() == null) {
			System.out.println("Desde NULL");
			throw new FechaIncorrectaException();
			
		}
		if(MostarEstadoGUI.getHasta().getModel().getValue() == null) {
			System.out.println("Hasta NULL");
			throw new FechaIncorrectaException();
		}
		else {
			Calendar dateDesde = Calendar.getInstance();
			dateDesde.set(MostarEstadoGUI.getDesde().getModel().getYear()
						 , MostarEstadoGUI.getDesde().getModel().getMonth()
						 , MostarEstadoGUI.getDesde().getModel().getDay());
        	java.util.Date desde = dateDesde.getTime();
        	
        	Calendar dateHasta = Calendar.getInstance();
			dateHasta.set(MostarEstadoGUI.getHasta().getModel().getYear()
						 , MostarEstadoGUI.getHasta().getModel().getMonth()
						 , MostarEstadoGUI.getHasta().getModel().getDay());
			java.util.Date hasta = dateHasta.getTime();
        	
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

	public void verficarFechas(){
		try {
			mostrarEstado();
		} catch (DesdeMayorException e1) {
			MostarEstadoGUI.mostrarError("","La fecha \"Desde\" es mayor a la fecha \"Hasta\"");
			MostarEstadoGUI.getDatePickerDesde().getModel().setValue(null);
			MostarEstadoGUI.getDatePickerHasta().getModel().setValue(null);

		} catch (FechaIncorrectaException e1) {
			if(MostarEstadoGUI.getDatePickerHasta().getModel().getValue() == null) {
				MostarEstadoGUI.getDatePickerDesde().setBackground(new Color(255, 0, 0));
			}
			if(MostarEstadoGUI.getDatePickerHasta().getModel().getValue() == null) {
				MostarEstadoGUI.getDatePickerHasta().setBackground(new Color(255, 0, 0));
			}
			MostarEstadoGUI.mostrarError("","Debe indicar una fecha correcta");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void getHabDA(Date Desde, Date Hasta){

		LHab = habServicio.getHabDA(Desde, Hasta, 1, 2);
	}
}
