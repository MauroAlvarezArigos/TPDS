package Exceptions;

@SuppressWarnings("serial")
public class CampoFacturarIncorrecto extends Exception {
	
	public CampoFacturarIncorrecto() {
		super("N�mero de habitaci�n faltante, incorrecto o habitaci�n no ocupada; hora faltante o incorrecta");
	}
}
