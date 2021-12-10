package Servicios;

import java.util.ArrayList;
import java.util.List;

import DAO.UbicacionDAO;
import DAO.utils.DAOManager;
import DTO.LocalidadDTO;
import DTO.PaisDTO;
import DTO.ProvDTO;
import Dominio.Localidad;
import Dominio.Pais;
import Dominio.Provincia;
import Servicios.Mappers.MapperLocalidad;
import Servicios.Mappers.MapperPais;
import Servicios.Mappers.MapperProvincia;

public class UbicacionServicio {
    DAOManager daoManager;
    UbicacionDAO ubicacionDAO;
    MapperPais mapperPais;
    MapperProvincia mapperProvincia;
    MapperLocalidad mapperLocalidad;

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

    public List<ProvDTO> provinciasToDTO(List<Provincia> ListaProv){
        mapperProvincia = new MapperProvincia();

        List<ProvDTO> LprovDTO = new ArrayList<>();
        for(Provincia p : ListaProv){
            LprovDTO.add(mapperProvincia.toDTO(p));
        }

        return LprovDTO;
    }

    public List<LocalidadDTO> localidadesToDTO(List<Localidad> ListaLoc){
        mapperLocalidad = new MapperLocalidad();

        List<LocalidadDTO> LocDTO = new ArrayList<>();

        for(Localidad l : ListaLoc){
            LocDTO.add(mapperLocalidad.toDTO(l));
        }

        return LocDTO;
    }

    public List<ProvDTO> getAllProvinciasPais(String n_pais){
        daoManager = new DAOManager();
        ubicacionDAO = daoManager.getUbicacionDAO();

        daoManager.begin();

        List<Provincia> Lprov = ubicacionDAO.getAllProvinciasPais(n_pais);
        daoManager.commit();
        daoManager.disconnect();

        List<ProvDTO> LProvReturn = provinciasToDTO(Lprov);

        return LProvReturn;
    }

    public List<LocalidadDTO> getAllLocalidadesProv(String n_prov){
        daoManager = new DAOManager();
        ubicacionDAO = daoManager.getUbicacionDAO();

        daoManager.begin();

        List<Localidad> LLoc = ubicacionDAO.getAllLocalidadesProv(n_prov);
        daoManager.commit();
        daoManager.disconnect();

        List<LocalidadDTO> LLocReturn = localidadesToDTO(LLoc);

        return LLocReturn;
    }
}
