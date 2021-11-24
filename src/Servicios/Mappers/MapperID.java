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
<<<<<<< HEAD

=======
>>>>>>> f2c99adc8e89050c56b647b343cc6cb94b38ed6a
