package Dominio;

import java.sql.Date;

public class Ocupacion {
    private int id;
    private Pasajero Responsable;
    private Habitacion habitacion;
    private Date checkIn;
    private Date checkOut;

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
    public Date getCheckIn() {
        return checkIn;
    }
    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }
    public Date getCheckOut() {
        return checkOut;
    }
    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }
}
