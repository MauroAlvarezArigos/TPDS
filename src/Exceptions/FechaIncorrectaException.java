package Exceptions;

@SuppressWarnings("serial")
public class FechaIncorrectaException extends Exception{

	public FechaIncorrectaException() {
		super("La fecha es incorrecta");
	}
}
