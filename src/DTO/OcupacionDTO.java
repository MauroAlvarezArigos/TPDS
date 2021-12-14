package DTO;

import Dominio.Habitacion;
import Dominio.Pasajero;

import java.time.LocalDate;
import java.util.List;

public class OcupacionDTO {
    private PasajeroBusquedaDTO responsable;
    private List<PasajeroBusquedaDTO> listaAcompanantes;
    private HabitacionDTO habitacion;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public LocalDate getCheckIn() {
        return checkIn;
    }
    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }
    public LocalDate getCheckOut() {
        return checkOut;
    }
    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }
    public PasajeroBusquedaDTO getResponsable() {
        return responsable;
    }
    public void setResponsable(PasajeroBusquedaDTO responsable) {
        this.responsable = responsable;
    }
    public HabitacionDTO getHabitacion() {
        return habitacion;
    }
    public void setHabitacion(HabitacionDTO habitacion) {
        this.habitacion = habitacion;
    }
    public List<PasajeroBusquedaDTO> getListaAcompanantes() {
        return listaAcompanantes;
    }
    public void setListaAcompanantes(List<PasajeroBusquedaDTO> listaAcompanantes) {
        this.listaAcompanantes = listaAcompanantes;
    }
}
