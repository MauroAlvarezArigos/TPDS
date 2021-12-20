package Dominio;

import java.time.LocalDate;

public class Unidades {
    private ItemConsumo itemConsumo;
    private int cantidad;
    private LocalDate fechaConsumo;
    private double valorTotal;

    public ItemConsumo getItemConsumo() {
        return itemConsumo;
    }
    public void setItemConsumo(ItemConsumo itemConsumo) {
        this.itemConsumo = itemConsumo;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public LocalDate getFechaConsumo() {
        return fechaConsumo;
    }
    public void setFechaConsumo(LocalDate fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
