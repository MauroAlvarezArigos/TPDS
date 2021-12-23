package Dominio;

import java.time.LocalDate;

public class NotaDeCredito {
    private int id_nota;
    private String responsable;
    private int codigo_factura;
    private LocalDate FechaFactura;
    private double importeNeto;
    private double iva;
    private String dnicuit;

    public int getId_nota() {
        return id_nota;
    }
    public void setId_nota(int id_nota) {
        this.id_nota = id_nota;
    }
    public String getResponsable() {
        return responsable;
    }
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
    public int getCodigo_factura() {
        return codigo_factura;
    }
    public void setCodigo_factura(int codigo_factura) {
        this.codigo_factura = codigo_factura;
    }
    public LocalDate getFechaFactura() {
        return FechaFactura;
    }
    public void setFechaFactura(LocalDate fechaFactura) {
        FechaFactura = fechaFactura;
    }
    public double getImporteNeto() {
        return importeNeto;
    }
    public void setImporteNeto(double importeNeto) {
        this.importeNeto = importeNeto;
    }
    public double getIva() {
        return iva;
    }
    public void setIva(double iva) {
        this.iva = iva;
    }
    public String getDnicuit() {
        return dnicuit;
    }
    public void setDnicuit(String dnicuit) {
        this.dnicuit = dnicuit;
    }
}
