package Servicios;

import DAO.*;
import DAO.utils.DAOManager;
import DTO.HabitacionDTO;
import DTO.OcupacionDTO;
import Dominio.Habitacion;
import Servicios.Mappers.MapperHabitacion;
import Servicios.Mappers.MapperOcupacion;
import utils.Converter;

import java.time.LocalDate;
import java.util.List;

public class HabitacionServicio {

    private Converter converter = new Converter();

    DAOManager daoManager;
    HabitacionDAOSQL HabDAO;
    OcupacionDAOSQL ocupacionDAO;
    ReservaDAOSQL reservaDAO;
    FueraDeServicioDAOSQL fueraDeServicioDAO;
    MapperHabitacion mapperHabitacion;
    MapperOcupacion mapperOcupacion;

    public Habitacion getHabNumeroPiso(int numero, int piso){
        daoManager = new DAOManager();
        HabDAO = daoManager.getHabitacionDAO();

        daoManager.begin();
        Habitacion returnobj = HabDAO.getHabitacion(numero, piso);
        daoManager.commit();
        daoManager.disconnect();

        return  returnobj;
    }


    public List<HabitacionDTO> getHabDA(LocalDate desde, LocalDate hasta, int n_piso, int n_numero){
        daoManager = new DAOManager();
        HabDAO = daoManager.getHabitacionDAO();
        ocupacionDAO = daoManager.getOcupacionDAO();
        reservaDAO = daoManager.getReservaDAO();
        fueraDeServicioDAO = daoManager.getFueraDeServicioDAO();


         daoManager.begin();
        List<Habitacion> Lhab = HabDAO.getAllHabitaciones();

        for (Habitacion habitacion : Lhab) {
            habitacion.setReservas(reservaDAO.getReservasHabitacionDesdeHasta(habitacion, desde, hasta));
            habitacion.setOcupaciones(ocupacionDAO.getOcupacionesHabDesdeHasta(habitacion, desde, hasta));
            habitacion.setPeriodosFueraDeServicio(fueraDeServicioDAO.getAllFueraDeServiciohabitacionDesdeHasta(habitacion, desde,hasta));
        }
        daoManager.commit();
        daoManager.disconnect();
        mapperHabitacion = new MapperHabitacion();
        return mapperHabitacion.listToDTO(Lhab, n_piso, n_numero);
    }

}
