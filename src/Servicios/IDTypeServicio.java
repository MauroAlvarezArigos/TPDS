package Servicios;

import DAO.IDTypeDAO;
import DAO.IDTypeDAOSQL;
import DTO.IDTypeDTO;
import DTO.PasajeroDTO;
import Dominio.IDType;
import Servicios.Mappers.MapperID;

import java.util.ArrayList;
import java.util.List;

public class IDTypeServicio {
    MapperID Mapper = new MapperID();
    IDTypeDAO IDDAO;

    public IDTypeServicio(){IDDAO = new IDTypeDAOSQL();
    }
    public IDType getIDType(String ID){
        return IDDAO.getIDType(ID);
    }
    public List<IDTypeDTO> getAllIDType(){
        List<IDType> ListaDominio = IDDAO.getAllIDType();
        List<IDTypeDTO> ListaDTO = new ArrayList<IDTypeDTO>();

        int size = ListaDominio.size();
        for(int c = 0; c < size; c++){
            ListaDTO.add(Mapper.toDTO(ListaDominio.get(c)));
        }
        return ListaDTO;
    }
}
