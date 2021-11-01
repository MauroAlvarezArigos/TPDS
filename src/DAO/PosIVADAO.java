package DAO;

import Dominio.PosIVA;

import java.util.List;

public interface PosIVADAO {
    public PosIVA Insert(PosIVA unPosIVA);
    public List<PosIVA> GetListIVA();
}
