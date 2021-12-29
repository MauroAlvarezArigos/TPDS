package Exceptions;

public class CapacidadExcedidaException extends Exception{
    public CapacidadExcedidaException(){
        super("Se ha excedido la capacidad de la habitacion");
    }

}
