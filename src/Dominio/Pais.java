package Dominio;

import java.util.ArrayList;
import java.util.List;

public class Pais {
	private String nombre;
	private Integer codigoPais;
	private String nacionalidad;
	private List<Provincia> provincias;
	
	//Constructor
	public Pais() {
		super();
	}


	//Getters and Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCodigo() {
		return codigoPais;
	}

	public void setCodigo(Integer codigo) {
		this.codigoPais = codigo;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	public List<Provincia> getProvincias(){
		return provincias;
	}
	
	public void setProvincia(Provincia unaProvincia) {
		this.provincias.add(unaProvincia);
	}

	public void setListProvincias (List<Provincia> Provincias){
		this.provincias = Provincias;
	}

}
