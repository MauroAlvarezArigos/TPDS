package Servicios.Mappers;

import DTO.OcupacionDTO;
import DTO.PeriodoEstadoHabitacionDTO;
import Dominio.Ocupacion;
import Dominio.Reserva;

import java.util.ArrayList;
import java.util.List;

public class MapperOcupacion {

    public PeriodoEstadoHabitacionDTO toDTO(Ocupacion unOcupacion){
        PeriodoEstadoHabitacionDTO dto = new PeriodoEstadoHabitacionDTO();

        dto.setDesde(unOcupacion.getCheckIn());
        dto.setHasta(unOcupacion.getCheckOut());
        dto.setEstado("Ocupado");

        return dto;
    }

    public List<PeriodoEstadoHabitacionDTO> listToDTO(List<Ocupacion> LOcupacion){
        List<PeriodoEstadoHabitacionDTO> LDTO = new ArrayList<>();

        for(Ocupacion o : LOcupacion){
            LDTO.add(this.toDTO(o));
        }

        return LDTO;
    }
}
