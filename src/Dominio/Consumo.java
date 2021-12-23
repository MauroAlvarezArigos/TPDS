package Dominio;

import java.util.List;

public class Consumo {
    private double CostoTotal;
    private int id_consumo;
    private List<Unidades> ListaItems;

    public double getCostoTotal() {
        return CostoTotal;
    }
    public void setCostoTotal(double costoTotal) {
        CostoTotal = costoTotal;
    }
    public int getId_consumo() {
        return id_consumo;
    }
    public void setId_consumo(int id_consumo) {
        this.id_consumo = id_consumo;
    }
    public List<Unidades> getListaItems() {
        return ListaItems;
    }
    public void setListaItems(List<Unidades> listaItems) {
        ListaItems = listaItems;
    }
}
