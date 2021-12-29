package Exceptions;

@SuppressWarnings("serial")
public class EmailInvalidoException extends Exception {

    public EmailInvalidoException() {
        super("El email ingresado es invalido");
    }

}