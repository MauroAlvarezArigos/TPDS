package Servicios.Mappers;

import DTO.PeriodoEstadoHabitacionDTO;
import Dominio.Ocupacion;
import DAO.HabitacionDAOSQL;
import DAO.PasajeroDAOSQL;
import DAO.utils.DAOManager;

import DTO.OcupacionDTO;
import DTO.PasajeroBusquedaDTO;

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
        mapperConsumo = new MapperConsumo();

        daoManager.begin();

        List<Pasajero> Lpasajeros = new ArrayList<>();
        for(PasajeroBusquedaDTO p : dto.getListaOcupantes()){
            Lpasajeros.add(pasajeroDAO.getPasajeroDbid(p.getDbid()));
        }
        dominio.setId(dto.getId());
        dominio.setResponsable(pasajeroDAO.getPasajeroDbid(dto.getResponsable().getDbid()));
        dominio.setAcompanantes(Lpasajeros);
        dominio.setCheckIn(dto.getCheckIn());
        dominio.setCheckOut(dto.getCheckOut());
        dominio.setHabitacion(habitacionDAO.getHabitacion(dto.getHabitacion().getN_hab(), dto.getHabitacion().getPiso()));
        dominio.setConsumos(mapperConsumo.toDomain(dto.getConsumo()));
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
        mapperPasajeroBusqueda = new MapperPasajeroBusqueda();
        mapperHabitacion = new MapperHabitacion();
        mapperConsumo = new MapperConsumo();

        if(dominio == null){
            return null;
        }else {
            dto.setCheckIn(dominio.getCheckIn());
            dto.setCheckOut(dominio.getCheckOut());
            /*HabitacionDTO habDTO = new HabitacionDTO();
            habDTO.setN_hab(dominio.getHabitacion().getNumero());
            habDTO.setPiso(dominio.getHabitacion().getPiso());
            habDTO.setDescuento(dominio.getHabitacion().getDescuento());
            habDTO.setTipo(dominio.getHabitacion().getTipo().getTipo());
            habDTO.setCapacidad(dominio.getHabitacion().getCapacidad());
            habDTO.setValordiario(dominio.getHabitacion().getTipo().getCosto());*/
            dto.setHabitacion(mapperHabitacion.toDTO(dominio.getHabitacion(), 1, 2));
            dto.setId(dominio.getId());
            dto.setListaOcupantes(mapperPasajeroBusqueda.toDTO(dominio.getAcompanantes()));
            dto.setResponsable(mapperPasajeroBusqueda.toDTO(dominio.getResponsable()));
            dto.setConsumo(mapperConsumo.toDTO(dominio.getConsumos()));
        }
        return dto;

    }
}
