package DAO;

import Dominio.Localidad;
import Dominio.Pais;
import Dominio.Provincia;

import java.util.List;

public interface UbicacionDAO {
    public Pais insertPais(Pais unPais);
    public Pais buscarCodePais(int Codigo);
    public Provincia insertProvincia(Provincia unProvincia);
    public Provincia buscarCodeProvincia(int Codigo);
    public Localidad insertLocalidad(Localidad unLocalidad);
    public Localidad buscarLocalidad(int Codigo);
    public List<Provincia> buscarProvinciasPais(Pais unPais);
}
