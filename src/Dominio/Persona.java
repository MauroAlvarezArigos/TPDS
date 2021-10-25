package Dominio;

public class Persona {
	private String telefono;
	private String email;
	private String cuit_cif;
	private String calle;
	private Integer altura;
	private PosIVA IVA;


	//Constructor
	public Persona() {
		super();
	}

	//Getters and Setters
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

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}

	public PosIVA getIVA() {return IVA;	}

	public void setIVA(PosIVA IVA) {this.IVA = IVA;	}

}
