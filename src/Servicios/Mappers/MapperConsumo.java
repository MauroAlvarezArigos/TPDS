package Servicios.Mappers;

import DTO.ConsumoDTO;
import Dominio.Consumo;

import java.util.List;

public class MapperConsumo {

    MapperUnidades mapperUnidades;

    public ConsumoDTO toDTO(Consumo dominio){
        mapperUnidades = new MapperUnidades();

        ConsumoDTO dto = new ConsumoDTO();
        dto.setId_consumo(dominio.getId_consumo());
        dto.setListaItems(mapperUnidades.toDTO(dominio.getListaItems()));

        return dto;
    }

    public Consumo toDomain(ConsumoDTO dto){
        Consumo dominio = new Consumo();
        mapperUnidades = new MapperUnidades();
        if(dto != null) {
            dominio.setCostoTotal(dto.getCostoTotal());
            dominio.setListaItems(mapperUnidades.toDomain(dto.getListaItems()));

            return dominio;
        }else{
            return null;
        }
    }
}
