package Servicios.Mappers;

import DTO.PeriodoEstadoHabitacionDTO;
import Dominio.FueraDeServicio;

import java.util.ArrayList;
import java.util.List;

public class MapperFueraDeServicio {
    public PeriodoEstadoHabitacionDTO toDTO(FueraDeServicio unFueraDeServicio){
        PeriodoEstadoHabitacionDTO dto = new PeriodoEstadoHabitacionDTO();

        dto.setDesde(unFueraDeServicio.getDesde());
        dto.setHasta(unFueraDeServicio.getHasta());
        dto.setEstado("Fuera de Servicio");

        return dto;

    }

    public List<PeriodoEstadoHabitacionDTO> listToDTO(List<FueraDeServicio> LFueraDeServicio){
        List<PeriodoEstadoHabitacionDTO> LDTO = new ArrayList<>();

        for(FueraDeServicio f : LFueraDeServicio){
            LDTO.add(this.toDTO(f));
        }

        return LDTO;
    }

}
