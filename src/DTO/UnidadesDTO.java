package DTO;

import Dominio.ItemConsumo;
import Dominio.SeccionConsumo;

import java.time.LocalDate;

public class UnidadesDTO {
    private int id_item;
    private String nombre;
    private double costoUnitario;
    private String seccionConsumo;
    private int id_seccionConsumo;
    private int cantidad;
    private LocalDate fechaConsumo;
    private double valorTotal;

    public int getId_item() {
        return id_item;
    }
    public void setId_item(int id_item) {
        this.id_item = id_item;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getCostoUnitario() {
        return costoUnitario;
    }
    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }
    public String getSeccionConsumo() {
        return seccionConsumo;
    }
    public void setSeccionConsumo(String seccionConsumo) {
        this.seccionConsumo = seccionConsumo;
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
    public int getId_seccionConsumo() {
        return id_seccionConsumo;
    }
    public void setId_seccionConsumo(int id_seccionConsumo) {
        this.id_seccionConsumo = id_seccionConsumo;
    }
}
