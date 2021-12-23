package Servicios;

import DAO.FacturaDAOSQL;
import DAO.utils.DAOManager;
import DTO.FacturaDTO;
import DTO.OcupacionDTO;
import Dominio.Factura;
import Servicios.Mappers.MapperFactura;
import Servicios.Mappers.MapperOcupacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FacturaServicio {

    DAOManager daoManager;
    MapperFactura mapperFactura;
    MapperOcupacion mapperOcupacion;
    FacturaDAOSQL facturaDAO;

    public void guardarFactura(FacturaDTO facturaDTO){
        daoManager = new DAOManager();
        mapperFactura = new MapperFactura();
        facturaDAO = daoManager.getFacturaDAO();

        daoManager.begin();
        facturaDAO.guardarFactura(mapperFactura.toDomain(facturaDTO));
        daoManager.commit();
        daoManager.disconnect();

    }

    public List<FacturaDTO> getFacturasOcuapcion(OcupacionDTO unOcupacionDTO){
        List<FacturaDTO> LfacturaDTO = new ArrayList<>();
        List<Factura> Lfactura = new ArrayList<>();
        daoManager = new DAOManager();
        facturaDAO = daoManager.getFacturaDAO();
        mapperFactura = new MapperFactura();
        mapperOcupacion = new MapperOcupacion();

        daoManager.begin();

        Lfactura = facturaDAO.getFacturaOcupacion(mapperOcupacion.toDomain(unOcupacionDTO));

        LfacturaDTO = mapperFactura.toDTO(Lfactura);

        return LfacturaDTO;

    }
}
