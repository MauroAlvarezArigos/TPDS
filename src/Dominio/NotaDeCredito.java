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
}
