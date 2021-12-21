package Servicios.Mappers;

import DTO.DetalleFacturaDTO;
import Dominio.DetalleFactura;

public class MapperDetalle {

    MapperUnidades mapperUnidades;

    public DetalleFactura toDomain(DetalleFacturaDTO dto){
        mapperUnidades = new MapperUnidades();

        DetalleFactura dominio = new DetalleFactura();
        dominio.setCostoTotal(dto.getCostoTotal());
        dominio.setListaItems(mapperUnidades.toDomain(dto.getListaItems()));

        return dominio;
    }
}
