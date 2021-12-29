package Servicios.Mappers;

import DTO.PeriodoEstadoHabitacionDTO;
import DTO.ReservaDTO;
import Dominio.Reserva;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MapperReserva {

    public PeriodoEstadoHabitacionDTO toDTO(Reserva unReserva){
        PeriodoEstadoHabitacionDTO dto = new PeriodoEstadoHabitacionDTO();

        dto.setDesde(unReserva.getFechaDesde());
        dto.setHasta(unReserva.getFechaHasta());
        dto.setEstado("Reservado");

        return dto;
    }

    public List<PeriodoEstadoHabitacionDTO> listToDTO(List<Reserva> LReservas){
        List<PeriodoEstadoHabitacionDTO> LDTO = new ArrayList<>();

        if(LReservas == null || LReservas.isEmpty()){
            List<PeriodoEstadoHabitacionDTO> L = new ArrayList<>();
            return L;
        }else {
            for (Reserva r : LReservas) {
                LDTO.add(this.toDTO(r));
            }
            return LDTO;
        }
    }

    public ReservaDTO toComplexDTO(Reserva unReserva){
        ReservaDTO dto = new ReservaDTO();
        dto.setFechaDesde(unReserva.getFechaDesde());
        dto.setFechaHasta(unReserva.getFechaHasta());
        dto.setApellido(unReserva.getApellido());
        dto.setNombre(unReserva.getNombre());

        return dto;
    }
}
