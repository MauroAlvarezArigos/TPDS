package Servicios.Mappers;

import DTO.UnidadesDTO;
import Dominio.Unidades;

import java.util.ArrayList;
import java.util.List;

public class MapperUnidades {
    public UnidadesDTO toDTO(Unidades dominio){
        UnidadesDTO dto = new UnidadesDTO();
        dto.setNombre(dominio.getItemConsumo().getNombre());
        dto.setCantidad(dominio.getCantidad());
        dto.setCostoUnitario(dominio.getItemConsumo().getCosto());
        dto.setFechaConsumo(dominio.getFechaConsumo());
        dto.setId_item(dominio.getItemConsumo().getId_item());
        dto.setSeccionConsumo(dominio.getItemConsumo().getSeccionConsumo().getSeccion());
        dto.setId_seccionConsumo(dominio.getItemConsumo().getSeccionConsumo().getId_categoria());

        return dto;
    }

    public List<UnidadesDTO> toDTO(List<Unidades> LDominio){
        if(LDominio == null){
            List<UnidadesDTO> Unidades = new ArrayList<>();
            return Unidades;
        }else {
            List<UnidadesDTO> Ldto = new ArrayList<>();
            for (Unidades u : LDominio) {
                Ldto.add(toDTO(u));
            }
            return Ldto;
        }
    }

}
