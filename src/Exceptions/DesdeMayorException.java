package Exceptions;

@SuppressWarnings("serial")
public class DesdeMayorException extends Exception {
	
	public DesdeMayorException() {
		super("La fecha \"Desde\" es mayor a la fecha \"Hasta\"");
	}

}
