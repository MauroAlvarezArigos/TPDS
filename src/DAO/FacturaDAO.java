package DAO;

import Dominio.Factura;
import Dominio.TipoDeFactura;

public interface  FacturaDAO {
    public TipoDeFactura getTipoFactura(String tipo);
    public void guardarFactura(Factura factura);
}
