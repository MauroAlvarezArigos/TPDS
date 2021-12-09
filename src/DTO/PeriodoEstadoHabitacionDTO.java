package DTO;

import java.time.LocalDate;

public class PeriodoEstadoHabitacionDTO {
    private String estado;
    private LocalDate desde;
    private LocalDate hasta;

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
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
