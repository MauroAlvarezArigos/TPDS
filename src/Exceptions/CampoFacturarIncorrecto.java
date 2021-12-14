package Exceptions;

@SuppressWarnings("serial")
public class CampoFacturarIncorrecto extends Exception {
	
	public CampoFacturarIncorrecto() {
		super("Número de habitación faltante, incorrecto o habitación no ocupada; hora faltante o incorrecta");
	}
}
