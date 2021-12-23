package DAO;

import Dominio.Habitacion;
import Dominio.Reserva;
import java.time.LocalDate;
import java.util.List;

public interface ReservaDAO {
    public List<Reserva> getReservasHab(Habitacion unHab);
    public List<Reserva> getReservasHabitacionDesdeHasta(Habitacion unHab, LocalDate desde, LocalDate hasta);
}
