package Exceptions;

@SuppressWarnings("serial")
public class NoConcordanciaException extends Exception {
	
	public NoConcordanciaException() {
		super("No existe ninguna concordancia seg�n los criterios de b�squeda");
	}

}
