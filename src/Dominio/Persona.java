package Dominio;

public abstract class Persona {
	private int idpersona;
	private String telefono;
	private String email;
	private String cuit_cif;
	private String calle;
	private String altura;
	private PosIVA IVA;
	private Localidad localidad;


	//Constructor
	public Persona() {
		super();
	}

	//Getters and Setters

	public int getIdpersona() {
		return idpersona;
	}

	public void setIdpersona(int idpersona) {
		this.idpersona = idpersona;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCuit_cif() {
		return cuit_cif;
	}

	public void setCuit_cif(String cuit_cif) {
		this.cuit_cif = cuit_cif;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public PosIVA getIVA() {return IVA;	}

	public void setIVA(PosIVA IVA) {this.IVA = IVA;	}

	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

}
