package DTO;


import java.time.LocalDate;

public class FacturaDTO {
    private int id_factura;
    private LocalDate fecha;
    private double montoTotal;
    private double montoIVA;
    private Object responsable;
    private String tipo;
    private boolean pago;
    private DetalleFacturaDTO detalle;
    private Boolean notaDeCredito;
    private PeriodoEstadiaDTO estadia;
    private int id_ocupacion;

    public int getId_factura() {
        return id_factura;
    }
    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public double getMontoTotal() {
        return montoTotal;
    }
    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }
    public Object getResponsable() {
        return responsable;
    }
    public void setResponsable(Object responsable) {
        this.responsable = responsable;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public boolean isPago() {
        return pago;
    }
    public void setPago(boolean pago) {
        this.pago = pago;
    }
    public DetalleFacturaDTO getDetalle() {
        return detalle;
    }
    public void setDetalle(DetalleFacturaDTO detalle) {
        this.detalle = detalle;
    }
    public Boolean getNotaDeCredito() {
        return notaDeCredito;
    }
    public void setNotaDeCredito(Boolean notaDeCredito) {
        this.notaDeCredito = notaDeCredito;
    }
    public PeriodoEstadiaDTO getEstadia() {
        return estadia;
    }
    public void setEstadia(PeriodoEstadiaDTO estadia) {
        this.estadia = estadia;
    }
    public int getId_ocupacion() {
        return id_ocupacion;
    }
    public void setId_ocupacion(int id_ocupacion) {
        this.id_ocupacion = id_ocupacion;
    }
    public double getMontoIVA() {
        return montoIVA;
    }
    public void setMontoIVA(double montoIVA) {
        this.montoIVA = montoIVA;
    }
}
