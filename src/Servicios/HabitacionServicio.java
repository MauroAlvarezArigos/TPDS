package Servicios;

import DAO.HabitacionDAO;
import DAO.HabitacionDAOSQL;
import DAO.IDTypeDAOSQL;
import DAO.utils.DAOManager;
import DTO.HabitacionDTO;
import Dominio.Habitacion;
import Dominio.Ocupacion;
import Servicios.Mappers.MapperHabitacion;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class HabitacionServicio {

    DAOManager daoManager;
    HabitacionDAOSQL HabDAO;
    MapperHabitacion mapperHabitacion;


    public List<HabitacionDTO> getHabDA(Date desde, Date hasta, int n_piso, int n_numero){
        daoManager = new DAOManager();
        HabDAO = daoManager.getHabitacionDAO();


         daoManager.begin();
        List<Habitacion> Lhab = HabDAO.getAllHabitaciones();

        for (Habitacion habitacion : Lhab) {
            habitacion.setReservas(HabDAO.getReservasHabitacionDesdeHasta(habitacion, desde, hasta));
            habitacion.setOcupaciones(HabDAO.getOcupacionesHabDesdeHasta(habitacion, desde, hasta));
            habitacion.setPeriodosFueraDeServicio(HabDAO.getAllFueraDeServiciohabitacionDesdeHasta(habitacion, desde, hasta));
        }
        daoManager.commit();
        daoManager.disconnect();
        mapperHabitacion = new MapperHabitacion();
        return mapperHabitacion.listToDTO(Lhab, n_piso, n_numero);
    }

}
