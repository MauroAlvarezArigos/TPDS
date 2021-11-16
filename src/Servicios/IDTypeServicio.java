package Servicios;

import DAO.IDTypeDAO;
import DAO.IDTypeDAOSQL;
import Dominio.IDType;

public class IDTypeServicio {
    IDTypeDAO IDDAO;

    public IDTypeServicio(){IDDAO = new IDTypeDAOSQL();
    }
    public IDType getIDType(String ID){
        return IDDAO.getIDType(ID);
    }
}
