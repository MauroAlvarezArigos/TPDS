package Servicios.Mappers;

import DTO.PasajeroBusquedaDTO;
import DTO.PersonaJuridicaDTO;
import DTO.ResponsableDePagoDTO;
import Dominio.Pasajero;
import Dominio.PersonaJuridica;
import Dominio.ResponsableDePago;

public class MapperResponsable {

    MapperPasajeroBusqueda mapperPasajeroBusqueda;
    MapperPersonaJuridica mapperPersonaJuridica;

    public ResponsableDePagoDTO toDTO (ResponsableDePago responsable){
        ResponsableDePagoDTO dto = new ResponsableDePagoDTO();
        dto.setCalle(responsable.getCalle());
        dto.setId_responsableDePago(responsable.getId_responsableDePago());
        dto.setCuitDni(responsable.getCuitDni());
        dto.setNumDireccion(responsable.getNumDireccion());
        dto.setTelefono(responsable.getTelefono());
        dto.setRazonSocial(responsable.getRazonSocial());

        mapperPasajeroBusqueda = new MapperPasajeroBusqueda();
        mapperPersonaJuridica = new MapperPersonaJuridica();

        if(responsable.getPersona_asociada() instanceof Pasajero && responsable.getPersona_asociada() != null) {
            PasajeroBusquedaDTO p = mapperPasajeroBusqueda.toDTO((Pasajero) responsable.getPersona_asociada());
            dto.setPersona_asociada(p);
        }else if(responsable.getPersona_asociada() instanceof PersonaJuridica && responsable.getPersona_asociada() != null){
            PersonaJuridicaDTO pj = mapperPersonaJuridica.toDTO((PersonaJuridica) responsable.getPersona_asociada());
            dto.setPersona_asociada(pj);
        }

        return dto;
    }



}
