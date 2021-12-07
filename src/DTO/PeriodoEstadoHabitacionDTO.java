package DTO;

import java.util.Date;

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
}
