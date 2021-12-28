package Servicios.Mappers;

import DTO.PeriodoEstadiaDTO;
import Dominio.PeriodoEstadia;

public class MapperEstadia {
    public PeriodoEstadia toDomain(PeriodoEstadiaDTO unDTO){
        PeriodoEstadia estadia = null;
        if(unDTO != null){
        estadia = new PeriodoEstadia();
        estadia.setFechaInicio(unDTO.getFechaInicio());
        estadia.setFechaFinal(unDTO.getFechaFinal());
        estadia.setMonto(unDTO.getMonto());
        estadia.setMediaEstadia(unDTO.getMediaEstadia());
        }
        return estadia;
    }

}
