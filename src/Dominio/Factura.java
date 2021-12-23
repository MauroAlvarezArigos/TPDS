package Dominio;

import java.time.LocalDate;

public class Factura {
    private int id_factura;
    private Ocupacion ocupacion;
    private LocalDate fecha;
    private double montoTotal;
    private ResponsableDePago responsable;
    private TipoDeFactura tipo;
    private Pago pago;
    private DetalleFactura detalle;
    private NotaDeCredito notaDeCredito;
    private PeriodoEstadia estadia;

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
    public ResponsableDePago getResponsable() {
        return responsable;
    }
    public void setResponsable(ResponsableDePago responsable) {
        this.responsable = responsable;
    }
    public TipoDeFactura getTipo() {
        return tipo;
    }
    public void setTipo(TipoDeFactura tipo) {
        this.tipo = tipo;
    }
    public Pago getPago() {
        return pago;
    }
    public void setPago(Pago pago) {
        this.pago = pago;
    }
    public DetalleFactura getDetalle() {
        return detalle;
    }
    public void setDetalle(DetalleFactura detalle) {
        this.detalle = detalle;
    }
    public NotaDeCredito getNotaDeCredito() {
        return notaDeCredito;
    }
    public void setNotaDeCredito(NotaDeCredito notaDeCredito) {
        this.notaDeCredito = notaDeCredito;
    }
    public PeriodoEstadia getEstadia() {
        return estadia;
    }
    public void setEstadia(PeriodoEstadia estadia) {
        this.estadia = estadia;
    }
    public Ocupacion getOcupacion() {
        return ocupacion;
    }
    public void setOcupacion(Ocupacion ocupacion) {
        this.ocupacion = ocupacion;
    }
}
