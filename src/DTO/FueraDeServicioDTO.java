package DTO;

import java.time.LocalDate;

public class FueraDeServicioDTO {
    private LocalDate desde;
    private LocalDate hasta;

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
