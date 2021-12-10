package Servicios.Mappers;

import DTO.ProvDTO;
import Dominio.Provincia;

public class MapperProvincia {

    public ProvDTO toDTO(Provincia unProv){
        ProvDTO unProvDTO = new ProvDTO();

        unProvDTO.setProv(unProv.getNombre());

        return unProvDTO;
    }

    //todo public toDomain

}
