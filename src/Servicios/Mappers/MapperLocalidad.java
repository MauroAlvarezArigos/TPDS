package Servicios.Mappers;

import DTO.LocalidadDTO;
import Dominio.Localidad;

public class MapperLocalidad {
    public LocalidadDTO toDTO(Localidad unLocalidad){
        LocalidadDTO unLocDTO = new LocalidadDTO();

        unLocDTO.setCodigoPostal(unLocalidad.getCodPostal());
        unLocDTO.setLoc(unLocalidad.getNombre());
        unLocDTO.setCodigo(unLocalidad.getCodigoLocalidad());

        return unLocDTO;
    }

    //todo public toDomain

}
