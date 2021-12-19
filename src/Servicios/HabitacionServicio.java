package Servicios;

import DAO.HabitacionDAO;
import DAO.HabitacionDAOSQL;
import DAO.IDTypeDAOSQL;
import DAO.utils.DAOManager;
import DTO.HabitacionDTO;
import DTO.OcupacionDTO;
import Dominio.Habitacion;
import Dominio.Ocupacion;
import Servicios.Mappers.MapperHabitacion;
import Servicios.Mappers.MapperOcupacion;
import utils.Converter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HabitacionServicio {

    private Converter converter = new Converter();

    DAOManager daoManager;
    HabitacionDAOSQL HabDAO;
    MapperHabitacion mapperHabitacion;
    MapperOcupacion mapperOcupacion;

    public void guardarOcupacion(List<OcupacionDTO> Ldto){

        daoManager = new DAOManager();
        HabDAO = daoManager.getHabitacionDAO();
        mapperOcupacion = new MapperOcupacion();

        daoManager.begin();
        for(OcupacionDTO dto : Ldto) {
            HabDAO.guardarOcupacion(mapperOcupacion.toDomain(dto));
        }


        daoManager.commit();
        daoManager.disconnect();
    }

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


         daoManager.begin();
        List<Habitacion> Lhab = HabDAO.getAllHabitaciones();

        for (Habitacion habitacion : Lhab) {
            habitacion.setReservas(HabDAO.getReservasHabitacionDesdeHasta(habitacion,
                    converter.convertToDateViaSqlDate(desde), converter.convertToDateViaSqlDate(hasta)));
            habitacion.setOcupaciones(HabDAO.getOcupacionesHabDesdeHasta(habitacion,
                    converter.convertToDateViaSqlDate(desde), converter.convertToDateViaSqlDate(hasta)));
            habitacion.setPeriodosFueraDeServicio(HabDAO.getAllFueraDeServiciohabitacionDesdeHasta(habitacion,
                    converter.convertToDateViaSqlDate(desde), converter.convertToDateViaSqlDate(hasta)));
        }
        daoManager.commit();
        daoManager.disconnect();
        mapperHabitacion = new MapperHabitacion();
        return mapperHabitacion.listToDTO(Lhab, n_piso, n_numero);
    }

}
