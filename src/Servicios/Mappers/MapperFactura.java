package Servicios.Mappers;

import DAO.FacturaDAOSQL;
import DAO.OcupacionDAOSQL;
import DAO.utils.DAOManager;
import DTO.*;
import Dominio.*;

import java.util.ArrayList;
import java.util.List;

public class MapperFactura {

    DAOManager daoManager;
    FacturaDAOSQL facturaDAO;
    OcupacionDAOSQL ocupacionDAO;
    MapperDetalle mapperDetalle;
    MapperPago mapperPago;
    MapperResponsable mapperResponsable;
    MapperPasajeroBusqueda mapperPasajeroBusqueda;
    MapperPersonaJuridica mapperPersonaJuridica;
    MapperEstadia mapperEstadia;

    public Factura toDomain(FacturaDTO dto){
        daoManager = new DAOManager();
        facturaDAO = daoManager.getFacturaDAO();
        ocupacionDAO = daoManager.getOcupacionDAO();
        mapperDetalle = new MapperDetalle();
        mapperPasajeroBusqueda = new MapperPasajeroBusqueda();
        mapperPersonaJuridica = new MapperPersonaJuridica();
        mapperEstadia = new MapperEstadia();

        daoManager.begin();

        Factura dominio = new Factura();

        dominio.setOcupacion(ocupacionDAO.getOcupacion(dto.getId_ocupacion()));
        dominio.setFecha(dto.getFecha());
        dominio.setMontoTotal(dto.getMontoTotal());
        dominio.setMontoIVA(dto.getMontoIVA());
        dominio.setNotaDeCredito(null);
        dominio.setTipo(facturaDAO.getTipoFactura(dto.getTipo()));
        //corregir doble periodo estadia
        dominio.setEstadia(mapperEstadia.toDomain(dto.getEstadia()));
        dominio.setPago(null);
        dominio.setDetalle(mapperDetalle.toDomain(dto.getDetalle()));

        ResponsableDePago responsable = new ResponsableDePago();

        if(dto.getResponsable() instanceof PasajeroBusquedaDTO){
            Pasajero pasajero = mapperPasajeroBusqueda.toDomain((PasajeroBusquedaDTO) dto.getResponsable());
            responsable.setCalle(pasajero.getCalle());
            responsable.setCuitDni(pasajero.getNdoc());
            responsable.setNumDireccion(pasajero.getAltura());
            responsable.setTelefono(pasajero.getTelefono());
            responsable.setPersona_asociada(pasajero);
            responsable.setRazonSocial(null);
        }else if (dto.getResponsable() instanceof PersonaJuridicaDTO){
            PersonaJuridica personaJuridica = mapperPersonaJuridica.toDomain((PersonaJuridicaDTO) dto.getResponsable());
            responsable.setRazonSocial(((PersonaJuridicaDTO) dto.getResponsable()).getRazonSocial());
            responsable.setTelefono(((PersonaJuridicaDTO) dto.getResponsable()).getTelefono());
            responsable.setCalle(((PersonaJuridicaDTO) dto.getResponsable()).getCalle());
            responsable.setNumDireccion(((PersonaJuridicaDTO) dto.getResponsable()).getAltura());
            responsable.setCuitDni(((PersonaJuridicaDTO) dto.getResponsable()).getCuit_cif());
            responsable.setPersona_asociada(personaJuridica);
        }
        dominio.setResponsable(responsable);

        daoManager.commit();
        daoManager.disconnect();

        return dominio;

    }

    public FacturaDTO toDTO(Factura dominio){
        FacturaDTO dto = new FacturaDTO();
        dto.setId_ocupacion(dominio.getOcupacion().getId());
        dto.setMontoTotal(dominio.getMontoTotal());
        dto.setMontoIVA(dominio.getMontoIVA());

        if(dominio.getPago() != null){
            dto.setPago(true);
        }else{
            dto.setPago(false);
        }

        mapperDetalle = new MapperDetalle();
        mapperResponsable = new MapperResponsable();

        dto.setDetalle(mapperDetalle.toDTO(dominio.getDetalle()));
        dto.setResponsable(mapperResponsable.toDTO(dominio.getResponsable()));

        if(dominio.getNotaDeCredito() != null){
            dto.setNotaDeCredito(true);
        }else{
            dto.setNotaDeCredito(false);
        }

        PeriodoEstadiaDTO estadia = new PeriodoEstadiaDTO();
        estadia.setMediaEstadia(dominio.getEstadia().getMediaEstadia());
        estadia.setMonto(dominio.getEstadia().getMonto());
        estadia.setFechaInicio(dominio.getEstadia().getFechaInicio());
        estadia.setFechaFinal(dominio.getEstadia().getFechaFinal());
        estadia.setId_estadia(dominio.getEstadia().getId_estadia());
        dto.setEstadia(estadia);

        dto.setTipo(dominio.getTipo().getTipo());
        dto.setFecha(dominio.getFecha());
        dto.setId_factura(dominio.getId_factura());

        return dto;
    }

    public List<FacturaDTO> toDTO(List<Factura> LFacturas){
        List<FacturaDTO> LDTO = new ArrayList<>();

        for(Factura f : LFacturas){
            LDTO.add(toDTO(f));
        }
        return LDTO;
    }
}
