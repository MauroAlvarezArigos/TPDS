package DAO;

import Dominio.Factura;
import Dominio.Ocupacion;
import Dominio.TipoDeFactura;

import java.util.List;

public interface  FacturaDAO {
    public TipoDeFactura getTipoFactura(String tipo);
    public void guardarFactura(Factura factura);
    public List<Factura> getFacturaOcupacion(Ocupacion unOcupacion);
}
