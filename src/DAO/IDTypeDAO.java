package DAO;

import Dominio.IDType;

import java.util.List;

public interface IDTypeDAO {
    public List<IDType> getAllIDType();
    public IDType getIDType(String ID);
}
