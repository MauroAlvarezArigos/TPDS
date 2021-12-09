package DTO;

import java.time.LocalDate;

public class ReservaDTO {
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;

    public LocalDate getFechaDesde() {
        return fechaDesde;
    }
    public void setFechaDesde(LocalDate fechaDesde) {
        this.fechaDesde = fechaDesde;
    }
    public LocalDate getFechaHasta() {
        return fechaHasta;
    }
    public void setFechaHasta(LocalDate fechaHasta) {
        this.fechaHasta = fechaHasta;
    }
}
