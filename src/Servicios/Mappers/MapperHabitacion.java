package Servicios.Mappers;

import DTO.HabitacionDTO;
import Dominio.Habitacion;
import Servicios.HabitacionServicio;

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
        String numero = String.format("%0"+Num_Digits+"d",unHab.getNumero());
        numero = numero.substring(numero.length()-Num_Digits);
        String piso = String.format("%0"+Piso_Digits+"d",unHab.getPiso());
        piso = piso.substring(piso.length()-Piso_Digits);

        dto.setCapacidad(unHab.getCapacidad());
        dto.setTipo(unHab.getTipo().getTipo());
        dto.setNumero(piso+numero);
        dto.setN_hab(unHab.getNumero());
        dto.setPiso(unHab.getPiso());
        dto.setReservas(mapperReserva.listToDTO(unHab.getReservas()));
        dto.setOcupaciones(mapperOcupacion.listToSimplifiedDTO(unHab.getOcupaciones()));
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

    public Habitacion toDomain(HabitacionDTO dto){
        Habitacion resultobj;
        HabitacionServicio servicio = new HabitacionServicio();
        resultobj = servicio.getHabNumeroPiso(dto.getN_hab(), dto.getPiso());

        return resultobj;
    }

}
