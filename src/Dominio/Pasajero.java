package Dominio;

public class Pasajero extends Persona{
	private String nombre;
	private String apellido;
	private String ndoc;
	private String tipodoc;
	private String ocupacion;
	private String fechanacimiento;
	private String nacionalidad;
	
	//Constructor
	public Pasajero(String nombre, String apellido, String ndoc, String tipodoc, String ocupacion,
			String fechanacimiento, String nacionalidad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.ndoc = ndoc;
		this.tipodoc = tipodoc;
		this.ocupacion = ocupacion;
		this.fechanacimiento = fechanacimiento;
		this.nacionalidad = nacionalidad;
	}


	//Getters and Setters
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

	public String getTipodoc() {
		return tipodoc;
	}

	public void setTipodoc(String tipodoc) {
		this.tipodoc = tipodoc;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(String fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	

}
