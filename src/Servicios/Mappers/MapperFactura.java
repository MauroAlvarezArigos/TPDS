package Servicios.Mappers;

import DAO.FacturaDAOSQL;
import DAO.OcupacionDAOSQL;
import DAO.utils.DAOManager;
import DTO.FacturaDTO;
import DTO.PasajeroBusquedaDTO;
import DTO.PeriodoEstadiaDTO;
import DTO.ResponsableDePagoDTO;
import Dominio.Factura;
import Dominio.Pasajero;
import Dominio.PeriodoEstadia;
import Dominio.ResponsableDePago;

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

    public Factura toDomain(FacturaDTO dto){
        daoManager = new DAOManager();
        facturaDAO = daoManager.getFacturaDAO();
        ocupacionDAO = daoManager.getOcupacionDAO();
        mapperDetalle = new MapperDetalle();
        mapperPasajeroBusqueda = new MapperPasajeroBusqueda();

        daoManager.begin();

        Factura dominio = new Factura();

        dominio.setOcupacion(ocupacionDAO.getOcupacion(dto.getId_ocupacion()));
        dominio.setFecha(dto.getFecha());
        dominio.setMontoTotal(dto.getMontoTotal());
        dominio.setNotaDeCredito(null);
        dominio.setTipo(facturaDAO.getTipoFactura(dto.getTipo()));
        dominio.setEstadia(new PeriodoEstadia(
                dto.getEstadia().getFechaInicio(),
                dto.getEstadia().getFechaFinal(),
                dto.getEstadia().getMonto(),
                dto.getEstadia().getMediaEstadia()));
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
        }else if (dto.getResponsable() instanceof ResponsableDePagoDTO){
            responsable.setRazonSocial(((ResponsableDePagoDTO) dto.getResponsable()).getRazonSocial());
            responsable.setTelefono(((ResponsableDePagoDTO) dto.getResponsable()).getTelefono());
            responsable.setCalle(((ResponsableDePagoDTO) dto.getResponsable()).getCalle());
            responsable.setNumDireccion(((ResponsableDePagoDTO) dto.getResponsable()).getNumDireccion());
            responsable.setCuitDni(((ResponsableDePagoDTO) dto.getResponsable()).getCuitDni());
            responsable.setPersona_asociada(null);
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

        if(dominio.getPago() != null){
            dto.setPago(true);
        }else{
            dto.setPago(false);
        }

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
