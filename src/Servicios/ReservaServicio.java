package Servicios;

import DAO.HabitacionDAOSQL;
import DAO.OcupacionDAOSQL;
import DAO.ReservaDAO;
import DAO.utils.DAOManager;
import DTO.OcupacionDTO;
import DTO.ReservaDTO;
import Dominio.Habitacion;
import Dominio.Ocupacion;
import Dominio.Reserva;
import Servicios.Mappers.MapperOcupacion;
import Servicios.Mappers.MapperReserva;

import java.time.LocalDate;
import java.util.List;

public class ReservaServicio {

    DAOManager daoManager;
    ReservaDAO reservaDAO;
    HabitacionDAOSQL habitacionDAO;
    MapperReserva mapperReserva;

    public ReservaDTO buscarReservaFecha(int numero, int piso, LocalDate date){
        daoManager = new DAOManager();

        reservaDAO = daoManager.getReservaDAO();
        habitacionDAO = daoManager.getHabitacionDAO();
        mapperReserva = new MapperReserva();

        Habitacion hab = habitacionDAO.getHabitacion(numero, piso);
        List<Reserva> lreservas = reservaDAO.getReservasHabitacionDesdeHasta(hab , date, date);

        Reserva reserva = null;
        ReservaDTO dto = null;

        if(!lreservas.isEmpty()) {
            reserva = lreservas.get(0);
            dto = mapperReserva.toComplexDTO(reserva);
        }
        return dto;

    }

}
