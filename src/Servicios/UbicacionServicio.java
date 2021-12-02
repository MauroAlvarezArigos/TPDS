package Servicios;

import java.util.ArrayList;
import java.util.List;

import DAO.UbicacionDAO;
import DAO.UbicacionDAOSQL;
import DAO.utils.DAOManager;
import DTO.PaisDTO;
import DTO.PasajeroBusquedaDTO;
import Dominio.Localidad;
import Dominio.Pais;
import Dominio.Pasajero;
import Servicios.Mappers.MapperPais;
import Servicios.Mappers.MapperPasajero;

public class UbicacionServicio {
    DAOManager daoManager;
    UbicacionDAO ubicacionDAO;
    MapperPais mapperPais;

    public UbicacionServicio(){
        super();
    }

    public Pais getNacionalidad (String nacionalidad){
        daoManager = new DAOManager();
        ubicacionDAO = daoManager.getUbicacionDAO();

        daoManager.begin();
        Pais pais = ubicacionDAO.getNacionalidad(nacionalidad);
        daoManager.commit();
        daoManager.disconnect();

        return pais;
    }
    public Localidad getLocalidadNombre (String localidad, String prov, String pais){
        daoManager = new DAOManager();
        ubicacionDAO = daoManager.getUbicacionDAO();

        daoManager.begin();
        Localidad unlocalidad = ubicacionDAO.getLocalidadNombre(localidad, prov, pais);
        daoManager.commit();
        daoManager.disconnect();

        return unlocalidad;
    }
    
    public List<String> getAllNacionalidad() {
        daoManager = new DAOManager();
        ubicacionDAO = daoManager.getUbicacionDAO();

        daoManager.begin();
        List<String> returnvalue = ubicacionDAO.getNacionalidad();
        daoManager.commit();
        daoManager.disconnect();

		return returnvalue;
    }

    public List<PaisDTO> getAllPais(){
        daoManager = new DAOManager();
        ubicacionDAO = daoManager.getUbicacionDAO();

        daoManager.begin();
        List<Pais> LPais = ubicacionDAO.getAllPais();
        daoManager.commit();
        daoManager.disconnect();

        List<PaisDTO> returnvalue = paisesToDTO(LPais);

        return returnvalue;
    }

    public List<PaisDTO> paisesToDTO(List<Pais> ListaPais){
        mapperPais = new MapperPais();

        List<PaisDTO> LPaisesDTO = new ArrayList<PaisDTO>();
        for(Pais p : ListaPais) {
           LPaisesDTO.add(mapperPais.toDTO(p));
        }

        return LPaisesDTO;
    }
}
