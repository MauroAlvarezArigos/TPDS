package DAO;

import Dominio.Localidad;
import Dominio.Pais;
import Dominio.Provincia;

import java.util.List;

public interface UbicacionDAO {
    public Pais insertPais(Pais unPais);
    public Pais buscarCodePais(int Codigo);
    public Provincia insertProvincia(Provincia unProvincia, int CodPais);
    public Provincia buscarCodeProvincia(int Codigo);
    public Localidad insertLocalidad(Localidad unLocalidad, int CodProv);
    public Localidad buscarLocalidad(int Codigo);
    public Pais getNacionalidad(String nacionalidad);
    public List<Provincia> buscarProvinciasPais(Pais unPais);
    public List<Localidad> buscarLocalidaProvincias(Provincia unProvincia);
    public Localidad getLocalidadNombre(String nombre, String prov, String pais);
}
