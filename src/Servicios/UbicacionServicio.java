package Servicios;

import java.util.List;

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
    public Localidad getLocalidadNombre (String localidad, String prov, String pais){
        return ubicacionDAO.getLocalidadNombre(localidad, prov, pais);
    }
    
    public List<String> getAllNacionalidad() {
		return ubicacionDAO.getNacionalidad();
    }
}
