package Servicios.Mappers;

import DAO.PasajeroDAOSQL;
import DAO.utils.DAOManager;
import DTO.PasajeroBusquedaDTO;
import DTO.PasajeroDTO;
import Dominio.Pasajero;

import java.util.ArrayList;
import java.util.List;

public class MapperPasajeroBusqueda {

    private PasajeroDAOSQL pasajeroDAO;
    private DAOManager daoManager;

    public PasajeroBusquedaDTO toDTO(Pasajero unPasajero){
        //todo: Agregar id Pasajero
        PasajeroBusquedaDTO unPasajeroBusquedaDTO = new PasajeroBusquedaDTO();
        unPasajeroBusquedaDTO.setDbid(unPasajero.getIdpersona());
        unPasajeroBusquedaDTO.setNombre(unPasajero.getNombre());
        unPasajeroBusquedaDTO.setApellido(unPasajero.getApellido());
        unPasajeroBusquedaDTO.setNdoc(unPasajero.getNdoc());
        unPasajeroBusquedaDTO.setTipodoc(unPasajero.getTipodoc().getTipoDeID());

        return unPasajeroBusquedaDTO;
    }

    public List<PasajeroBusquedaDTO> toDTO(List<Pasajero> Lpasajeros){
       List<PasajeroBusquedaDTO> LDTO = new ArrayList<>();
        for (Pasajero p : Lpasajeros){
            LDTO.add(this.toDTO(p));
        }
        return LDTO;
    }

    public Pasajero toDomain(PasajeroBusquedaDTO unPasajeroBusquedaDTO){
        daoManager = new DAOManager();
        pasajeroDAO = daoManager.getPasajeroDAO();

        daoManager.begin();
        Pasajero unPasajero = pasajeroDAO.getPasajeroDbid(unPasajeroBusquedaDTO.getDbid());
        daoManager.commit();
        daoManager.disconnect();

        return unPasajero;
    }
}
