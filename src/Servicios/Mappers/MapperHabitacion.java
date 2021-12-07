package Servicios.Mappers;

import DTO.HabitacionDTO;
import DTO.ReservaDTO;
import Dominio.Habitacion;
import Dominio.Reserva;

import java.util.ArrayList;
import java.util.List;

public class MapperHabitacion {

    MapperReserva mapperReserva;
    MapperOcupacion mapperOcupacion;
    MapperFueraDeServicio mapperFueraDeServicio;

    public HabitacionDTO toDTO(Habitacion unHab, int Piso_Digits, int Num_Digits){
        mapperReserva = new MapperReserva();
        mapperOcupacion = new MapperOcupacion();
        mapperFueraDeServicio = new MapperFueraDeServicio();

        HabitacionDTO dto = new HabitacionDTO();
        String numero = Integer.toString(unHab.getNumero());
        numero = numero.substring(numero.length()-Num_Digits);
        String piso = Integer.toString(unHab.getPiso());
        piso = piso.substring(piso.length()-Piso_Digits);

        dto.setCapacidad(unHab.getCapacidad());
        dto.setTipo(unHab.getTipo().getTipo());
        dto.setNumero(piso+numero);
        dto.setReservas(mapperReserva.listToDTO(unHab.getReservas()));
        dto.setOcupaciones(mapperOcupacion.listToDTO(unHab.getOcupaciones()));
        dto.setFueraDeServicio(mapperFueraDeServicio.listToDTO(unHab.getPeriodosFueraDeServicio()));

        return dto;
    }

    public List<HabitacionDTO> listToDTO(List<Habitacion> Lhabitacion, int Piso_Digits, int Num_Digits){
        List<HabitacionDTO> LDTO = new ArrayList<>();

        for(Habitacion h : Lhabitacion){
            LDTO.add(this.toDTO(h, Piso_Digits, Num_Digits));

        }
        return LDTO;
    }

}
