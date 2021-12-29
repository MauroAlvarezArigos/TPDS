package DTO;

import java.util.List;

public class HabitacionDTO {
    private String numero;
    private int n_hab;
    private int piso;
    private String tipo;
    private double valordiario;
    private int capacidad;
    private int descuento;

    private List<PeriodoEstadoHabitacionDTO> Reservas;
    private List<PeriodoEstadoHabitacionDTO> Ocupaciones;
    private List<PeriodoEstadoHabitacionDTO> FueraDeServicio;

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    public List<PeriodoEstadoHabitacionDTO> getReservas() {
        return Reservas;
    }
    public void setReservas(List<PeriodoEstadoHabitacionDTO> reservas) {
        Reservas = reservas;
    }
    public List<PeriodoEstadoHabitacionDTO> getOcupaciones() {
        return Ocupaciones;
    }
    public void setOcupaciones(List<PeriodoEstadoHabitacionDTO> ocupaciones) {
        Ocupaciones = ocupaciones;
    }
    public List<PeriodoEstadoHabitacionDTO> getFueraDeServicio() {
        return FueraDeServicio;
    }
    public void setFueraDeServicio(List<PeriodoEstadoHabitacionDTO> fueraDeServicio) {
        FueraDeServicio = fueraDeServicio;
    }
    public int getN_hab() {
        return n_hab;
    }
    public void setN_hab(int n_hab) {
        this.n_hab = n_hab;
    }
    public int getPiso() {
        return piso;
    }
    public void setPiso(int piso) {
        this.piso = piso;
    }
    public double getValordiario() {
        return valordiario;
    }
    public void setValordiario(double valordiario) {
        this.valordiario = valordiario;
    }
    public int getDescuento() {
        return descuento;
    }
    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

}
