package Servicios.Mappers;

import DTO.OcupacionDTO;
import DTO.PeriodoEstadoHabitacionDTO;
import Dominio.Ocupacion;

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
    public OcupacionDTO toDto(Ocupacion unOcupacion) {
		OcupacionDTO dto = new OcupacionDTO();
		dto.setCheckIn(unOcupacion.getCheckIn());
		dto.setCheckOut(unOcupacion.getCheckOut());
		return null;
    }

    public List<PeriodoEstadoHabitacionDTO> listToDTO(List<Ocupacion> LOcupacion){
        List<PeriodoEstadoHabitacionDTO> LDTO = new ArrayList<>();

        for(Ocupacion o : LOcupacion){
            LDTO.add(this.toDTO(o));
        }

        return LDTO;
    }
}
