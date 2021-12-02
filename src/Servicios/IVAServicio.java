package Servicios;

import DAO.PosIVADAO;
import DAO.PosIVADAOSQL;
import DAO.utils.DAOManager;
import Dominio.PosIVA;

public class IVAServicio {
    DAOManager daoManager;
    PosIVADAO IVADAO;

    public IVAServicio(){
      super();
    }

    public PosIVA getIVA (String PosIVA){
        IVADAO = daoManager.getPosIVADAO();

        daoManager.begin();
        PosIVA posIVA = IVADAO.getIVA(PosIVA);
        daoManager.commit();
        daoManager.disconnect();
        return posIVA;
    }
}
