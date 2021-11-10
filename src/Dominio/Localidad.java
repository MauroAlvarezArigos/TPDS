package Dominio;

public class Localidad{
	private String nombre;
	private String codPostal;
	private Integer codigoLocalidad;
	private Provincia provincia;
	
	//Constructor
	public Localidad() {super();}

	//Getters and Setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}
	public Integer getCodigoLocalidad() {
		return codigoLocalidad;
	}
	public void setCodigoLocalidad(Integer codigoLocalidad) {
		this.codigoLocalidad = codigoLocalidad;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
}
