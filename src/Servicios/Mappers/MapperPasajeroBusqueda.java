package Servicios.Mappers;

import DAO.PasajeroDAOSQL;
import DTO.PasajeroBusquedaDTO;
import DTO.PasajeroDTO;
import Dominio.Pasajero;

public class MapperPasajeroBusqueda {

    PasajeroDAOSQL PasajeroDAO = new PasajeroDAOSQL();

    public PasajeroBusquedaDTO toDTO(Pasajero unPasajero){
        //todo: Agregar id Pasajero
        PasajeroBusquedaDTO unPasajeroBusquedaDTO = new PasajeroBusquedaDTO();
        unPasajeroBusquedaDTO.setDbid(unPasajero.getID());
        unPasajeroBusquedaDTO.setNombre(unPasajero.getNombre());
        unPasajeroBusquedaDTO.setApellido(unPasajero.getApellido());
        unPasajeroBusquedaDTO.setNdoc(unPasajero.getNdoc());
        unPasajeroBusquedaDTO.setTipodoc(unPasajero.getTipodoc().getTipoDeID());

        return unPasajeroBusquedaDTO;
    }

    public Pasajero toDomain(PasajeroBusquedaDTO unPasajeroBusquedaDTO){

        return PasajeroDAO.getPasajeroDbid(unPasajeroBusquedaDTO.getDbid());
    }
}
