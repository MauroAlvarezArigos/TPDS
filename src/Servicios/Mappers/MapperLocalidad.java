package Servicios.Mappers;

import DTO.LocalidadDTO;
import Dominio.Localidad;

public class MapperLocalidad {
    public LocalidadDTO toDTO(Localidad unLocalidad){
        LocalidadDTO unLocDTO = new LocalidadDTO();

        unLocDTO.setLoc(unLocalidad.getNombre());

        return unLocDTO;
    }

    //todo public toDomain

}
