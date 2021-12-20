package Servicios.Mappers;

import DAO.HabitacionDAOSQL;
import DAO.PasajeroDAOSQL;
import DAO.utils.DAOManager;
import DTO.OcupacionDTO;
import DTO.PasajeroBusquedaDTO;
import DTO.PeriodoEstadoHabitacionDTO;
import Dominio.Ocupacion;
import Dominio.Pasajero;

import java.util.ArrayList;
import java.util.List;

public class MapperOcupacion {

    DAOManager daoManager;
    MapperHabitacion mapperHabitacion;
    MapperPasajeroBusqueda mapperPasajeroBusqueda;
    MapperConsumo mapperConsumo;

    public PeriodoEstadoHabitacionDTO toSimplifiedDTO(Ocupacion unOcupacion){
        PeriodoEstadoHabitacionDTO dto = new PeriodoEstadoHabitacionDTO();

        dto.setDesde(unOcupacion.getCheckIn());
        dto.setHasta(unOcupacion.getCheckOut());
        dto.setEstado("Ocupado");

        return dto;
    }

    public Ocupacion toDomain(OcupacionDTO dto){
        Ocupacion dominio = new Ocupacion();
        daoManager = new DAOManager();
        PasajeroDAOSQL pasajeroDAO = daoManager.getPasajeroDAO();
        HabitacionDAOSQL habitacionDAO = daoManager.getHabitacionDAO();

        daoManager.begin();

        List<Pasajero> Lpasajeros = new ArrayList<>();
        for(PasajeroBusquedaDTO p : dto.getListaOcupantes()){
            Lpasajeros.add(pasajeroDAO.getPasajeroDbid(p.getDbid()));
        }
        dominio.setResponsable(pasajeroDAO.getPasajeroDbid(dto.getResponsable().getDbid()));
        dominio.setAcompanantes(Lpasajeros);
        dominio.setCheckIn(dto.getCheckIn());
        dominio.setCheckOut(dto.getCheckOut());
        dominio.setHabitacion(habitacionDAO.getHabitacion(dto.getHabitacion().getN_hab(), dto.getHabitacion().getPiso()));
        daoManager.commit();
        daoManager.disconnect();

        return dominio;
    }

    public List<PeriodoEstadoHabitacionDTO> listToSimplifiedDTO(List<Ocupacion> LOcupacion){
        List<PeriodoEstadoHabitacionDTO> LDTO = new ArrayList<>();
        if(LOcupacion == null || LOcupacion.isEmpty()){
            List<PeriodoEstadoHabitacionDTO> L = new ArrayList<>();
            return L;
        }else {
            for (Ocupacion o : LOcupacion) {
                LDTO.add(this.toSimplifiedDTO(o));
            }
            return LDTO;
        }
    }

    public OcupacionDTO toDTO(Ocupacion dominio){
        OcupacionDTO dto = new OcupacionDTO();
        mapperHabitacion = new MapperHabitacion();
        mapperPasajeroBusqueda = new MapperPasajeroBusqueda();
        mapperConsumo = new MapperConsumo();

        if(dominio == null){
            return null;
        }else {
            dto.setCheckIn(dominio.getCheckIn());
            dto.setCheckOut(dominio.getCheckOut());
            dto.setHabitacion(mapperHabitacion.toDTO(dominio.getHabitacion(), 1, 2));
            dto.setId(dominio.getId());
            dto.setListaOcupantes(mapperPasajeroBusqueda.toDTO(dominio.getAcompanantes()));
            dto.setResponsable(mapperPasajeroBusqueda.toDTO(dominio.getResponsable()));
            dto.setConsumo(mapperConsumo.toDTO(dominio.getConsumos()));
        }
        return dto;

    }
}
