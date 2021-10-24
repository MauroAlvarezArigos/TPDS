package DAO;

import Dominio.Pais;

import java.util.List;

public interface PaisDAO {
    public Pais insert(Pais unPais);
    public Pais buscarCode(int Codigo);
}
