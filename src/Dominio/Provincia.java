package Dominio;

import java.util.ArrayList;
import java.util.List;

public class Provincia{
	private String nombre;
	private Integer codigoProvincia;
	private List<Localidad> localidades;
	
	
	public Provincia(String nombre, Integer codigoProvincia) {
		super();
		this.nombre = nombre;
		this.codigoProvincia = codigoProvincia;
		this.localidades = new ArrayList<Localidad>();
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCodigoProvincia() {
		return codigoProvincia;
	}
	public void setCodigoProvincia(Integer codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}
	public List<Localidad> getLocalidades() {
		return localidades;
	}
	public void setLocalidades(Localidad unaLocalidad) {
		this.localidades.add(unaLocalidad);
	}
	
	

}
