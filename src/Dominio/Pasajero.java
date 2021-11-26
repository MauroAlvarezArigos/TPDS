package Dominio;

import java.sql.Date;

public class Pasajero extends Persona{

	private int ID;
	private String nombre;
	private String apellido;
	private String ndoc;
	private IDType tipodoc;
	private String ocupacion;
	private Date fechanacimiento;
	private Pais nacionalidad;

	
	//Constructor
	public Pasajero() {
		super();
	}
	
	//Getters and Setters

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNdoc() {
		return ndoc;
	}

	public void setNdoc(String ndoc) {
		this.ndoc = ndoc;
	}

	public IDType getTipodoc() {
		return tipodoc;
	}

	public void setTipodoc(IDType tipodoc) {
		this.tipodoc = tipodoc;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public Date getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public Pais getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(Pais nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.apellido + " " + this.nombre + " " + this.ndoc;
	}
	

}
