package Dominio;

import java.util.List;

public class DetalleFactura {
    private double CostoTotal;
    private int id_detalle;
    private Factura factura;
    private List<Unidades> ListaItems;

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
    public Factura getFactura() {
        return factura;
    }
    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    public List<Unidades> getListaItems() {
        return ListaItems;
    }
    public void setListaItems(List<Unidades> listaItems) {
        ListaItems = listaItems;
    }
}
