package DTO;

import java.util.List;

public class HabitacionDTO {
    private String numero;
    private String tipo;
    private int capacidad;

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
}
