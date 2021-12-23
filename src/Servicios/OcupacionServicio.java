package Servicios;

import DAO.HabitacionDAOSQL;
import DAO.OcupacionDAOSQL;
import DAO.utils.DAOManager;
import DTO.OcupacionDTO;
import Dominio.Habitacion;
import Dominio.Ocupacion;
import Servicios.Mappers.MapperOcupacion;
import utils.Converter;

import java.time.LocalDate;
import java.util.List;

public class OcupacionServicio {
    @SuppressWarnings("unused")
	private Converter converter = new Converter();

    DAOManager daoManager;
    OcupacionDAOSQL ocupacionDAO;
    HabitacionDAOSQL habitacionDAO;
    ConsumosServicio consumosServicio;

    MapperOcupacion mapperOcupacion;

    public OcupacionDTO buscarOcupacionActual(int numero, int piso){
        daoManager = new DAOManager();

        ocupacionDAO = daoManager.getOcupacionDAO();
        habitacionDAO = daoManager.getHabitacionDAO();
        mapperOcupacion = new MapperOcupacion();
        consumosServicio = new ConsumosServicio();

        Habitacion hab = habitacionDAO.getHabitacion(numero, piso);
        List<Ocupacion> locupacion = ocupacionDAO.getOcupacionesHabDesdeHasta(hab ,LocalDate.now(), LocalDate.now());

        Ocupacion ocupacion = null;
        OcupacionDTO dto = null;

        if(!locupacion.isEmpty()) {
            ocupacion = locupacion.get(0);
            ocupacion.setConsumos(consumosServicio.getConsumosOcupacion(ocupacion));
            dto = mapperOcupacion.toDTO(ocupacion);
        }
        return dto;

    }

    public void guardarOcupacion(List<OcupacionDTO> Ldto){

        daoManager = new DAOManager();
        ocupacionDAO = daoManager.getOcupacionDAO();
        mapperOcupacion = new MapperOcupacion();


        for(OcupacionDTO dto : Ldto) {
            daoManager.begin();
            ocupacionDAO.guardarOcupacion(mapperOcupacion.toDomain(dto));
            daoManager.commit();

        }
        daoManager.disconnect();


    }

}
