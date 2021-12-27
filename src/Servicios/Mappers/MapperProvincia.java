package Servicios.Mappers;

import DTO.PaisDTO;
import DTO.ProvDTO;
import Dominio.Pais;
import Dominio.Provincia;

public class MapperProvincia {

    public ProvDTO toDTO(Provincia unProv){
        ProvDTO unProvDTO = new ProvDTO();

        unProvDTO.setProv(unProv.getNombre());

        return unProvDTO;
    }

    //todo public toDomain

}
