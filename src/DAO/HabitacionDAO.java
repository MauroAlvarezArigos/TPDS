package DAO;

import Dominio.Habitacion;

import java.util.List;

public interface HabitacionDAO {
    public Habitacion getHabitacion(Integer nroHab, Integer piso);
    public List<Habitacion> getAllHabitaciones();
}
