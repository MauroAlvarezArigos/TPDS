package Dominio;

public class PersonaJuridica extends Persona {
	private String razonSocial;
	private String domicilioFiscal;
	
	//Constructor
	public PersonaJuridica(String razonSocial, String domicilioFiscal) {
		super();
		this.razonSocial = razonSocial;
		this.domicilioFiscal = domicilioFiscal;
	}
	
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
