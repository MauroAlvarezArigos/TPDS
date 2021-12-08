package Controller;

import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Date;
import java.util.List;

import DTO.HabitacionDTO;
import DTO.PeriodoEstadoHabitacionDTO;
import Exceptions.DesdeMayorException;
import Exceptions.FechaIncorrectaException;
import GUI.EstadoHabitacionesGUI;
import GUI.MostrarEstadoHabitacionGUI;
import Servicios.HabitacionServicio;

import javax.swing.*;

public class HabitacionController {
	private HabitacionServicio habServicio;
	private static final String ZoneId = null;
	private MostrarEstadoHabitacionGUI MostarEstadoGUI;
	private EstadoHabitacionesGUI EstadoGUI;

	private Color Disponible = new Color(118, 203, 78);
	private Color Reservado = new Color(255, 226, 32);
	private Color Ocupado = new Color(203, 76, 76);
	private Color FueraDeServicio = new Color(64, 131, 231);

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
				this.getHabDA(convertJavaDateToSqlDate(desde), convertJavaDateToSqlDate(hasta));
				EstadoGUI = new EstadoHabitacionesGUI(desde, hasta, LHab, this);
				EstadoGUI.setVisible(true);
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

	public java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

	public String getEstadoHabitacionFecha(Date fecha, HabitacionDTO hab){
		String Estado = "Libre";
		for (PeriodoEstadoHabitacionDTO p : hab.getReservas()) {
			if(!fecha.after(p.getDesde()) || !fecha.before(p.getHasta())){
				Estado = "Reservada";
			}
		}
		for (PeriodoEstadoHabitacionDTO p : hab.getOcupaciones()) {
			if(!fecha.after(p.getDesde()) || !fecha.before(p.getHasta())){
				Estado = "Ocupada";
			}
		}
		for (PeriodoEstadoHabitacionDTO p : hab.getFueraDeServicio()) {
			if (!fecha.after(p.getDesde()) || !fecha.before(p.getHasta())) {
				Estado = "Fuera de Servicio";
			}
		}
		return Estado;
	}

	public java.sql.Date convertStringtosqlDate(String str){
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
		format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
		Date sqlDate = null;
		try {
			java.util.Date date = format.parse(str);
			sqlDate = new java.sql.Date(date.getTime());
		} catch (ParseException e){
			//todo
		}finally {
			//todo
		}
		return sqlDate;
	}

	public List<String> getAllTiposHabDisponibles(List<HabitacionDTO> LHabDTO){
		List<String> LTipos = new ArrayList<>();

		for(HabitacionDTO h : LHabDTO){
			boolean ifcond = true;
			for(String t : LTipos) {
				if (h.getTipo().equals(t)) {
					ifcond = false;
				}
			}
				if(ifcond){
					LTipos.add(h.getTipo());
				}

			}

		return LTipos;
	}

	public Color GetColor(String Value){
		ArrayList<JCheckBox> LCheckBox = new ArrayList<>();


			Color returnvalue = new Color(0,0,0);
			if      (Value.equals("Ocupada")) {
				returnvalue = Ocupado;
			}else if(Value.equals("Reservada")){
				returnvalue = Reservado;
			}else if(Value.equals("Fuera de Servicio")){
				returnvalue = FueraDeServicio;
			}else{
				returnvalue = Disponible;
			};

		return returnvalue;
	}
}
