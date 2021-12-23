package DAO;

import Dominio.Habitacion;
import Dominio.Ocupacion;
import java.time.LocalDate;
import java.util.List;

public interface OcupacionDAO {
    public List<Ocupacion> getOcupacionesHab(Habitacion unHab);
    public List<Ocupacion> getOcupacionesHabDesdeHasta(Habitacion unHab, LocalDate desde, LocalDate hasta);
    public void guardarOcupacion(Ocupacion unOcupacion);
    public Ocupacion getOcupacion(int id);
}
