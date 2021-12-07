package Dominio;

import java.sql.Date;

public class FueraDeServicio {
    private int id;
    private Habitacion habitacion;
    private Date desde;
    private Date hasta;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getDesde() {
        return desde;
    }
    public void setDesde(Date desde) {
        this.desde = desde;
    }
    public Date getHasta() {
        return hasta;
    }
    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }
    public Habitacion getHabitacion() {
        return habitacion;
    }
    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }
}
