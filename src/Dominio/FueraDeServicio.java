package Dominio;

import java.time.LocalDate;

public class FueraDeServicio {
    private int id;
    private Habitacion habitacion;
    private LocalDate desde;
    private LocalDate hasta;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Habitacion getHabitacion() {
        return habitacion;
    }
    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }
    public LocalDate getDesde() {
        return desde;
    }
    public void setDesde(LocalDate desde) {
        this.desde = desde;
    }
    public LocalDate getHasta() {
        return hasta;
    }
    public void setHasta(LocalDate hasta) {
        this.hasta = hasta;
    }
}
