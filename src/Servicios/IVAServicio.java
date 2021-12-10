package Servicios;

import DAO.PosIVADAO;
import DAO.utils.DAOManager;
import DTO.PosIVADTO;
import Dominio.PosIVA;
import Servicios.Mappers.MapperPosIVA;

import java.util.ArrayList;
import java.util.List;

public class IVAServicio {
    DAOManager daoManager;
    PosIVADAO IVADAO;
    MapperPosIVA mapperPosIVA;

    public IVAServicio(){
      super();
    }

    public PosIVA getIVA (String PosIVA){
        daoManager = new DAOManager();
        IVADAO = daoManager.getPosIVADAO();

        daoManager.begin();
        PosIVA posIVA = IVADAO.getIVA(PosIVA);
        daoManager.commit();
        daoManager.disconnect();
        return posIVA;
    }

    public List<PosIVADTO> getAllIVA(){
        List<PosIVADTO> LIVADTO = new ArrayList<>();
        List<PosIVA> LIVA = new ArrayList<>();
        daoManager = new DAOManager();
        IVADAO = daoManager.getPosIVADAO();

        daoManager.begin();
        LIVA = IVADAO.getAllIVA();
        daoManager.commit();
        daoManager.disconnect();

        LIVADTO = PosIVAToDTO(LIVA);
        return LIVADTO;
    }

    public List<PosIVADTO> PosIVAToDTO(List<PosIVA> ListaIVADominio){
        mapperPosIVA = new MapperPosIVA();

        List<PosIVADTO> LPosIVADTO = new ArrayList<>();
        for(PosIVA p : ListaIVADominio) {
            LPosIVADTO.add(mapperPosIVA.toDTO(p));
        }

        return LPosIVADTO;
    }
}
