package Servicios.Mappers;

import DAO.IDTypeDAO;
import DAO.IDTypeDAOSQL;
import DAO.utils.DAOManager;
import DTO.IDTypeDTO;
import Dominio.IDType;

public class MapperID {
    private IDTypeDAO IDDAO;

    private DAOManager daoManager;



    public IDTypeDTO toDTO(IDType unIDType){
        IDTypeDTO unIDTypeDTO = new IDTypeDTO();
        unIDTypeDTO.setTipo(unIDType.getTipoDeID());
        return unIDTypeDTO;
    }
    public IDType toDomain(IDTypeDTO unDTO){
        daoManager = new DAOManager();
        IDDAO = daoManager.getIDTypeDAO();

        daoManager.begin();
        IDType idType = IDDAO.getIDType(unDTO.getTipo());
        daoManager.commit();
        daoManager.disconnect();
        return idType;
    }
}

