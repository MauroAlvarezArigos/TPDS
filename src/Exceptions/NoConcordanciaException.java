package Exceptions;

public class NoConcordanciaException extends Exception {
	
	public NoConcordanciaException() {
		super("No existe ninguna concordancia según los criterios de búsqueda");
	}

}
