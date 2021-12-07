package DTO;

import java.sql.Date;

public class ReservaDTO {
    private Date fechaDesde;
    private Date fechaHasta;

    public Date getFechaDesde() {
        return fechaDesde;
    }
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }
    public Date getFechaHasta() {
        return fechaHasta;
    }
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }
}
