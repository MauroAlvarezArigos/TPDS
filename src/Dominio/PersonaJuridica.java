package Dominio;

public class PersonaJuridica extends Persona {
	private String razonSocial;
	private String domicilioFiscal;
	
	//Constructor
	public PersonaJuridica() {super();}
	
	//Getters and Setters
	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDomicilioFiscal() {
		return domicilioFiscal;
	}

	public void setDomicilioFiscal(String domicilioFiscal) {
		this.domicilioFiscal = domicilioFiscal;
	}
		
	
}
