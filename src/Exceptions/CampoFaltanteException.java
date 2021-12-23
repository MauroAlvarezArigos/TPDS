package Exceptions;

@SuppressWarnings("serial")
public class CampoFaltanteException extends Exception{

	public CampoFaltanteException() {
		super("Falta rellenar un campo");
	}
}
