package Dominio;

import java.time.LocalDate;
import java.util.List;

public class Ocupacion {
    private int id;
    private Pasajero Responsable;
    private List<Pasajero> Acompanantes;
    private Habitacion habitacion;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Pasajero getResponsable() {
        return Responsable;
    }
    public void setResponsable(Pasajero responsable) {
        Responsable = responsable;
    }
    public Habitacion getHabitacion() {
        return habitacion;
    }
    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }
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
    public List<Pasajero> getAcompanantes() {
        return Acompanantes;
    }
    public void setAcompanantes(List<Pasajero> acompanantes) {
        Acompanantes = acompanantes;
    }
}
