package DTO;

import java.util.Date;

public class FueraDeServicioDTO {
    private Date desde;
    private Date hasta;

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
