package Servicios.Mappers;

import DTO.ConsumoDTO;
import Dominio.Consumo;

public class MapperConsumo {

    MapperUnidades mapperUnidades;

    public ConsumoDTO toDTO(Consumo dominio){
        mapperUnidades = new MapperUnidades();

        ConsumoDTO dto = new ConsumoDTO();
        dto.setId_consumo(dominio.getId_consumo());
        dto.setListaItems(mapperUnidades.toDTO(dominio.getListaItems()));

        return dto;
    }
}
