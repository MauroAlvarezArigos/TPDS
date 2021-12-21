package Servicios;

import DAO.FacturaDAOSQL;
import DAO.utils.DAOManager;
import DTO.FacturaDTO;
import Servicios.Mappers.MapperFactura;

public class FacturaServicio {

    DAOManager daoManager;
    MapperFactura mapperFactura;
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
}
