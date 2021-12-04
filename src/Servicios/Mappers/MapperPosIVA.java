package Servicios.Mappers;

import DTO.PosIVADTO;
import Dominio.PosIVA;

public class MapperPosIVA {
    public PosIVADTO toDTO(PosIVA unPosIVA){
        PosIVADTO dto = new PosIVADTO();
        dto.setID(unPosIVA.getID());
        dto.setTipo(unPosIVA.getTipo());

        return dto;
    }
}
