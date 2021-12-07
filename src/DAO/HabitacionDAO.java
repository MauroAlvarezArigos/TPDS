package DAO;

import Dominio.Habitacion;
import Dominio.Ocupacion;
import Dominio.Reserva;

import java.util.List;

public interface HabitacionDAO {
    public List<Habitacion> getAllHabitaciones();
    public List<Reserva> getReservasHab(Habitacion unHab);
    public List<Ocupacion> getOcupacionesHab(Habitacion unHab);
}
