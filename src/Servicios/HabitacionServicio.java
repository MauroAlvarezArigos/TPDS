package Servicios;

import DAO.HabitacionDAOSQL;
import DAO.utils.DAOManager;
import DTO.HabitacionDTO;
import DTO.OcupacionDTO;
import Dominio.Habitacion;
import Dominio.Ocupacion;
import Servicios.Mappers.MapperHabitacion;
import utils.Converter;

import java.time.LocalDate;
import java.util.List;

public class HabitacionServicio {

    private Converter converter = new Converter();

    DAOManager daoManager;
    HabitacionDAOSQL HabDAO;
    MapperHabitacion mapperHabitacion;


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
    
    public OcupacionDTO getOcupantes(String NHabitacion){
    	Ocupacion ocupacion;
    	Habitacion habitacion;
    	daoManager = new DAOManager();
    	HabDAO = daoManager.getHabitacionDAO();
    	daoManager.begin();
    	habitacion = HabDAO.getHabitacion(Integer.parseInt(NHabitacion.substring(1, 3)), Integer.parseInt(NHabitacion.substring(0, 1)));
    	ocupacion  = HabDAO.getOcupacionesHabDesdeHasta(habitacion,converter.convertToDateViaSqlDate(LocalDate.now()),converter.convertToDateViaSqlDate(LocalDate.now())).get(0);
    	
    	daoManager.commit();
        daoManager.disconnect();
        
        
    	return null;
    }

}
