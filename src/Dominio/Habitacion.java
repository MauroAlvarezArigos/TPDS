package Dominio;

import java.util.List;

public class Habitacion {
    private int capacidad;
    //private int estado;
    private int numero;
    private int piso;
    private int descuento;

    private TipoHabitacion tipo;

    private List<Reserva> Reservas;

    private List<Ocupacion> Ocupaciones;

    private List<FueraDeServicio> PeriodosFueraDeServicio;


    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public int getPiso() {
        return piso;
    }
    public void setPiso(int piso) {
        this.piso = piso;
    }
    public int getDescuento() {
        return descuento;
    }
    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
    public TipoHabitacion getTipo() {
        return tipo;
    }
    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }
    public List<Reserva> getReservas() {
        return Reservas;
    }
    public void setReservas(List<Reserva> reservas) {
        Reservas = reservas;
    }
    public List<Ocupacion> getOcupaciones() {
        return Ocupaciones;
    }
    public void setOcupaciones(List<Ocupacion> ocupaciones) {
        Ocupaciones = ocupaciones;
    }
    public List<FueraDeServicio> getPeriodosFueraDeServicio() {
        return PeriodosFueraDeServicio;
    }
    public void setPeriodosFueraDeServicio(List<FueraDeServicio> periodosFueraDeServicio) {
        PeriodosFueraDeServicio = periodosFueraDeServicio;
    }
}
