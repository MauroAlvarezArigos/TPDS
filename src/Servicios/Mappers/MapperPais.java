package Servicios.Mappers;

import DTO.PaisDTO;
import Dominio.Pais;

public class MapperPais {
    public PaisDTO toDTO(Pais unPais){
        PaisDTO unPaisDTO = new PaisDTO();

        unPaisDTO.setPais(unPais.getNombre());

        return unPaisDTO;
    }
}
