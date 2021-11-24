package Servicios.Mappers;

import DAO.IDTypeDAO;
import DAO.IDTypeDAOSQL;
import DTO.IDTypeDTO;
import Dominio.IDType;

public class MapperID {
    private IDTypeDAO IDDAO = new IDTypeDAOSQL();

    public IDTypeDTO toDTO(IDType unIDType){
        IDTypeDTO unIDTypeDTO = new IDTypeDTO();
        unIDTypeDTO.setTipo(unIDType.getTipoDeID());
        return unIDTypeDTO;
    }
    public IDType toDomain(IDTypeDTO unDTO){
        return IDDAO.getIDType(unDTO.getTipo());
    }
}

