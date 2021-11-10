package Servicios;

import DAO.UbicacionDAO;
import DAO.UbicacionDAOSQL;
import Dominio.Localidad;
import Dominio.Pais;

public class UbicacionServicio {
    UbicacionDAO ubicacionDAO;

    public UbicacionServicio(){
        ubicacionDAO = new UbicacionDAOSQL();
    }

    public Pais getNacionalidad (String nacionalidad){
        return ubicacionDAO.getNacionalidad(nacionalidad);
    }
    public Localidad getLocalidadNombre (String localidad){
        return ubicacionDAO.getLocalidadNombre(localidad);
    }
}
