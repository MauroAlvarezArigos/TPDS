package DTO;


import java.util.List;

public class ConsumoDTO {
    private double CostoTotal;
    private int id_consumo;
    //private OcupacionDTO ocupacion;
    private List<UnidadesDTO> ListaItems;

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
   public List<UnidadesDTO> getListaItems() {
        return ListaItems;
    }
    public void setListaItems(List<UnidadesDTO> listaItems) {
        ListaItems = listaItems;
    }
}
