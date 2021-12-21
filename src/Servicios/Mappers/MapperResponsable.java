package Servicios.Mappers;

import DTO.ResponsableDePagoDTO;
import Dominio.Pasajero;
import Dominio.ResponsableDePago;

public class MapperResponsable {

    MapperPasajeroBusqueda mapperPasajeroBusqueda;

    public ResponsableDePagoDTO toDTO (ResponsableDePago responsable){
        ResponsableDePagoDTO dto = new ResponsableDePagoDTO();
        dto.setCalle(responsable.getCalle());
        dto.setId_responsableDePago(responsable.getId_responsableDePago());
        dto.setCuitDni(responsable.getCuitDni());
        dto.setNumDireccion(responsable.getNumDireccion());
        dto.setTelefono(responsable.getTelefono());
        dto.setRazonSocial(responsable.getRazonSocial());

        mapperPasajeroBusqueda = new MapperPasajeroBusqueda();

        if(responsable.getPersona_asociada() != null) {
            dto.setPersona_asociada(mapperPasajeroBusqueda.toDTO((Pasajero) responsable.getPersona_asociada()));
        }

        return dto;
    }



}
