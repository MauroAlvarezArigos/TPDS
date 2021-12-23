package DTO;

import java.util.List;

public class DetalleFacturaDTO {
    private double CostoTotal;
    private int id_detalle;
    private List<UnidadesDTO> ListaItems;

    public double getCostoTotal() {
        return CostoTotal;
    }
    public void setCostoTotal(double costoTotal) {
        CostoTotal = costoTotal;
    }
    public int getId_detalle() {
        return id_detalle;
    }
    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }
    public List<UnidadesDTO> getListaItems() {
        return ListaItems;
    }
    public void setListaItems(List<UnidadesDTO> listaItems) {
        ListaItems = listaItems;
    }
}
