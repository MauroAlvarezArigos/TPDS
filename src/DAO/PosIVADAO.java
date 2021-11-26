package DAO;

import Dominio.PosIVA;

import java.util.List;

public interface PosIVADAO {
    public void Insert(PosIVA unPosIVA);
    public List<PosIVA> GetListIVA();
    public PosIVA getIVA(String PosIVA);
    public PosIVA BuscarIVA(int ident);
}
