package Servicios;

import java.util.List;

import DAO.UbicacionDAO;
import DAO.UbicacionDAOSQL;
import DAO.utils.DAOManager;
import Dominio.Localidad;
import Dominio.Pais;

public class UbicacionServicio {
    DAOManager daoManager;
    UbicacionDAO ubicacionDAO;

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
}
