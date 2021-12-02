package Servicios;

import DAO.IDTypeDAO;
import DAO.IDTypeDAOSQL;
import DAO.utils.DAOManager;
import DTO.IDTypeDTO;
import DTO.PasajeroDTO;
import Dominio.IDType;
import Servicios.Mappers.MapperID;

import java.util.ArrayList;
import java.util.List;

public class IDTypeServicio {

    DAOManager daoManager;
    IDTypeDAOSQL IDDAO;

    MapperID Mapper = new MapperID();

    public IDTypeServicio(){super();}

    public IDType getIDType(String ID){
        daoManager = new DAOManager();
        IDDAO = daoManager.getIDTypeDAO();

        daoManager.begin();
        IDType idType = IDDAO.getIDType(ID);
        daoManager.commit();
        daoManager.disconnect();


        return IDDAO.getIDType(ID);
    }
    public List<IDTypeDTO> getAllIDType(){
        daoManager = new DAOManager();
        IDDAO = daoManager.getIDTypeDAO();

        daoManager.begin();
        List<IDType> ListaDominio = IDDAO.getAllIDType();
        List<IDTypeDTO> ListaDTO = new ArrayList<IDTypeDTO>();

        daoManager.commit();
        daoManager.disconnect();

        int size = ListaDominio.size();
        for(int c = 0; c < size; c++){
            ListaDTO.add(Mapper.toDTO(ListaDominio.get(c)));
        }

        return ListaDTO;

    }
}
