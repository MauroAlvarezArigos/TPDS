package Servicios;

import DAO.HabitacionDAOSQL;
import DAO.OcupacionDAO;
import DAO.OcupacionDAOSQL;
import DAO.utils.DAOManager;
import DTO.OcupacionDTO;
import DTO.PasajeroBusquedaDTO;
import Dominio.Habitacion;
import Dominio.Ocupacion;
import Servicios.Mappers.MapperHabitacion;
import Servicios.Mappers.MapperOcupacion;
import utils.Converter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OcupacionServicio {
    private Converter converter = new Converter();

    DAOManager daoManager;
    OcupacionDAOSQL ocupacionDAO;
    HabitacionDAOSQL habitacionDAO;
    MapperOcupacion mapperOcupacion;

    public OcupacionDTO buscarOcupacionActual(int numero, int piso){
        daoManager = new DAOManager();

        ocupacionDAO = daoManager.getOcupacionDAO();
        habitacionDAO = daoManager.getHabitacionDAO();
        mapperOcupacion = new MapperOcupacion();

        Habitacion hab = habitacionDAO.getHabitacion(numero, piso);
        List<Ocupacion> locupacion = ocupacionDAO.getOcupacionesHabDesdeHasta(hab ,LocalDate.now(), LocalDate.now());

        Ocupacion ocupacion = null;
        OcupacionDTO dto = null;

        if(!locupacion.isEmpty()) {
            ocupacion = locupacion.get(0);
            dto = mapperOcupacion.toDTO(ocupacion);
        }
        return dto;

    }

}