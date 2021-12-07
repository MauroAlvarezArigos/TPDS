package DTO;

import java.sql.Date;

public class PeriodoEstadoHabitacionDTO {
    private String estado;
    private Date desde;
    private Date hasta;

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public java.sql.Date getDesde() {
        return desde;
    }
    public void setDesde(Date desde) {
        this.desde = desde;
    }
    public java.sql.Date getHasta() {
        return hasta;
    }
    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }
}
