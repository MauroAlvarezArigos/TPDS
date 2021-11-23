package Servicios.Mappers;

import DAO.IDTypeDAO;
import DTO.IDTypeDTO;
import Dominio.IDType;

public class MapperID {
    private IDTypeDAO IDDAO;

    public IDTypeDTO toDTO(IDType unIDType){
        IDTypeDTO unIDTypeDTO = new IDTypeDTO();
        unIDTypeDTO.setTipo(unIDType.getTipoDeID());
        return unIDTypeDTO;
    }
    public IDType toDomain(IDTypeDTO unDTO){
        return IDDAO.getIDType(unDTO.getTipo());
    }
}

