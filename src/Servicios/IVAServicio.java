package Servicios;

import DAO.PosIVADAO;
import DAO.PosIVADAOSQL;
import Dominio.PosIVA;

public class IVAServicio {
    PosIVADAO IVADAO;

    public IVAServicio(){
        IVADAO = new PosIVADAOSQL();
    }

    public PosIVA getIVA (String PosIVA){
        return IVADAO.getIVA(PosIVA);
    }
}
