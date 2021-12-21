package Servicios.Mappers;

import DAO.FacturaDAOSQL;
import DAO.OcupacionDAOSQL;
import DAO.utils.DAOManager;
import DTO.FacturaDTO;
import DTO.PasajeroBusquedaDTO;
import DTO.ResponsableDePagoDTO;
import Dominio.Factura;
import Dominio.Pasajero;
import Dominio.PeriodoEstadia;
import Dominio.ResponsableDePago;

public class MapperFactura {

    DAOManager daoManager;
    FacturaDAOSQL facturaDAO;
    OcupacionDAOSQL ocupacionDAO;
    MapperDetalle mapperDetalle;
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
}
