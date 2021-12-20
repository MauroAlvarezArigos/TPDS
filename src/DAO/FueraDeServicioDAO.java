package DAO;

import Dominio.FueraDeServicio;
import Dominio.Habitacion;

import java.time.LocalDate;
import java.util.List;

public interface FueraDeServicioDAO {
    public List<FueraDeServicio> getAllFueraDeServiciohabitacion(Habitacion unHab);
    public List<FueraDeServicio> getAllFueraDeServiciohabitacionDesdeHasta(Habitacion unHab, LocalDate desde, LocalDate hasta);
}
