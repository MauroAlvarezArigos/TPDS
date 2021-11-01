package Exceptions;

public class DuplicateDocNumberException extends Exception {

    public DuplicateDocNumberException() {
        super("Numero y tipo de Documento ya existen en el sistema");
    }

}