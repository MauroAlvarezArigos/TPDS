package Dominio;

public class ItemConsumo {
    private int id_item;
    private String nombre;
    private double costo;
    private SeccionConsumo seccionConsumo;

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
    public double getCosto() {
        return costo;
    }
    public void setCosto(double costo) {
        this.costo = costo;
    }
    public SeccionConsumo getSeccionConsumo() {
        return seccionConsumo;
    }
    public void setSeccionConsumo(SeccionConsumo seccionConsumo) {this.seccionConsumo = seccionConsumo;}
}
