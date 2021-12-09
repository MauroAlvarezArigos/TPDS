package Dominio;

import java.time.LocalDate;

public class Ocupacion {
    private int id;
    private Pasajero Responsable;
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
}
