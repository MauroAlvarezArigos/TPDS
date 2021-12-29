package Exceptions;

@SuppressWarnings("serial")
public class CUITInvalidoException extends Exception{

	public CUITInvalidoException() {
        super("El cuit ingresado es invalido");
    }

}
