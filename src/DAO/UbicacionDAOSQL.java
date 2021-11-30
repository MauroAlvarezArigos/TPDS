package DAO;


import DAO.utils.ConnectionWrapper;
import DAO.utils.DB;
import Dominio.Localidad;
import Dominio.Pais;
import Dominio.Provincia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UbicacionDAOSQL implements UbicacionDAO {

    //Get DB Connection
    //---
    private ConnectionWrapper wrapper;
    private Connection connection;

    //Constructor
    //---
    public UbicacionDAOSQL(){
        wrapper = new ConnectionWrapper();
        connection = wrapper.getConnection();
    }

    //Query Sentences
    //---
    private static final String INSERT_PAIS =
            "INSERT INTO PAIS(NOMBRE, CODIGOPAIS, NACIONALIDAD)"
                    + " VALUES(?, ?, ?)";
    private static final String SEARCH_CODE_PAIS =
            "SELECT * FROM PAIS"
                    + " WHERE CODIGOPAIS=";
    private static final String INSERT_PROVINCIA =
            "INSERT INTO PROVINCIA(NOMBRE, CODIGOPROVINCIA, PAIS)"
                    + " VALUES( ?, ?, ?)";
    private static final String INSERT_LOCALIDAD =
            "INSERT INTO LOCALIDAD(NOMBRE, CODPOSTAL, CODIGOLOCALIDAD, PROV)"
                    + " VALUES( ?, ?, ?, ?)";
    private static final String SEARCH_CODE_PROVINCIA =
            "SELECT * FROM PROVINCIA"
                    + " WHERE CODIGOPROVINCIA =";
    private static final String SEARCH_CODE_LOCALIDAD =
            "SELECT * FROM LOCALIDAD"
                    + " WHERE CODIGOLOCALIDAD =";
    private static final String SEARCH_PAIS_PROVINCIA =
            "SELECT * FROM PROVINCIA" +
                    " WHERE PAIS =";
    private static final String SEARCH_PROVINCIA_LOCALIDAD =
            "SELECT * FROM LOCALIDAD" +
                    " WHERE PROV =";
    private static final String SEARCH_NACIONALIDAD =
            "SELECT * FROM PAIS" +
                    " WHERE NACIONALIDAD =";
    private static final String ALL_NACIONALIDAD = 
    		"SELECT NACIONALIDAD FROM PAIS";
    private static final String SEARCH_NOMBRE_LOCALIDAD =
            "SELECT l.NOMBRE, l.CODPOSTAL, l.CODIGOLOCALIDAD, l.PROV FROM LOCALIDAD l " +
                    "JOIN PROVINCIA p ON (p.CODIGOPROVINCIA = l.PROV) " +
                    "JOIN PAIS c ON (c.CODIGOPAIS = p.PAIS)" +
                    " WHERE ((l.NOMBRE = ?) AND (p.NOMBRE = ?) AND (c.NOMBRE = ?))";

    //Insert Pais
    @Override
    public Pais insertPais(Pais unPais) {
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        List<Provincia> LProv = unPais.getProvincias();
        int Lsize = LProv.size();
        try {
            pstmt = conn.prepareStatement(INSERT_PAIS);
            pstmt.setString(1, unPais.getNombre());
            pstmt.setInt(2, unPais.getCodigo());
            pstmt.setString(3, unPais.getNacionalidad());
            for(int c = 0; c < Lsize; c++){
                insertProvincia(LProv.get(c), unPais.getCodigo());
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


//BuscarPaisCodigo

    @Override
    public Pais buscarCodePais(int Codigo) {
        String sentencia = SEARCH_CODE_PAIS + Codigo;
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Pais pais = new Pais();

        try {
                pstmt = conn.prepareStatement(sentencia);
                rs = pstmt.executeQuery();
            if(rs.next()) {
                pais.setNombre(rs.getString("NOMBRE"));
                pais.setCodigo(rs.getInt("CODIGOPAIS"));
                pais.setNacionalidad(rs.getString("NACIONALIDAD"));
                pais.setListProvincias(buscarProvinciasPais(Codigo));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pais;
    }


//Insert Provincia

    @Override
    public Provincia insertProvincia(Provincia unProvincia, int CodPais) {
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        List<Localidad> LLoc = unProvincia.getLocalidades();
        int Lsize = LLoc.size();

        try {
            pstmt = conn.prepareStatement(INSERT_PROVINCIA);
            pstmt.setString(1, unProvincia.getNombre());
            pstmt.setInt(2, unProvincia.getCodigoProvincia());
            pstmt.setInt(3, CodPais);
            for(int c = 0; c < Lsize; c++){
                insertLocalidad(LLoc.get(c), unProvincia.getCodigoProvincia());
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

//BuscarProvCodigo

    @Override
    public Provincia buscarCodeProvincia(int Codigo) {
        String sentencia = SEARCH_CODE_PROVINCIA + Codigo;
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Provincia prov = new Provincia();

        try {
            pstmt = conn.prepareStatement(sentencia);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                prov.setNombre(rs.getString("NOMBRE"));
                prov.setCodigoProvincia(rs.getInt("CODIGOPROVINCIA"));
                prov.setPais(buscarCodePais(rs.getInt("PAIS")));
                //prov.setLocalidades(rs.getString("NACIONALIDAD"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return prov;
    }

//InsertLocalidad

    @Override
    public Localidad insertLocalidad(Localidad unLocalidad, int CodProv) {
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(INSERT_LOCALIDAD);
            pstmt.setString(1, unLocalidad.getNombre());
            pstmt.setString(2, unLocalidad.getCodPostal());
            pstmt.setString(3, unLocalidad.getCodigoLocalidad().toString());
            pstmt.setInt(4, CodProv);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

//BuscarLocalidad

    @Override
    public Localidad buscarLocalidad(String Codigo) {
        String sentencia = SEARCH_CODE_LOCALIDAD + "\'" + Codigo + "\'";
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Localidad loc = new Localidad();

        try {
            pstmt = conn.prepareStatement(sentencia);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                loc.setNombre(rs.getString("NOMBRE"));
                loc.setCodigoLocalidad(rs.getInt("CODIGOLOCALIDAD"));
                loc.setCodPostal(rs.getString("CODPOSTAL"));
                loc.setProvincia(buscarCodeProvincia(rs.getInt("PROV")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return loc;
    }

//BuscarProvPais

    @Override
    public List<Provincia> buscarProvinciasPais(int codigo) {
        String sentencia = SEARCH_PAIS_PROVINCIA + codigo;
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Provincia> Provincias = new ArrayList<>();

        try {
                pstmt = conn.prepareStatement(sentencia);
                rs = pstmt.executeQuery();
                while(rs.next()) {
                    Provincia prov = new Provincia();
                prov.setNombre(rs.getString("NOMBRE"));
                int codigoprov = rs.getInt("CODIGOPROVINCIA");
                prov.setCodigoProvincia(codigoprov);
                prov.setListLocalidades(buscarLocalidaProvincias(codigoprov));
                Provincias.add(prov);
            }
        }
    catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        return Provincias;
    }


//BuscarLocalidadProvincia

    @Override
    public List<Localidad> buscarLocalidaProvincias(int codigo) {
        String sentencia = SEARCH_PROVINCIA_LOCALIDAD + codigo;
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Localidad> Localidades = new ArrayList<>();

        try {

                Localidad loc = new Localidad();
                pstmt = conn.prepareStatement(sentencia);
                rs = pstmt.executeQuery();
            while(rs.next()) {
                    loc.setNombre(rs.getString("NOMBRE"));
                    loc.setCodigoLocalidad(rs.getInt("CODIGOLOCALIDAD"));
                    loc.setCodPostal(rs.getString("CODPOSTAL"));
                    Localidades.add(loc);
                }
            }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Localidades;
    }


    @Override
    public Pais getNacionalidad(String nacionalidad){
        String sentencia = SEARCH_NACIONALIDAD + nacionalidad;
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Pais unPais = new Pais();

        try {
            pstmt = conn.prepareStatement(sentencia);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                int codigo = rs.getInt("CODIGOPAIS");
                unPais = buscarCodePais(codigo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return unPais;
    }

    public Localidad getLocalidadNombre(String nombre, String prov, String pais){
        String sentencia = SEARCH_NOMBRE_LOCALIDAD;
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Localidad loc = new Localidad();

        try {
            pstmt = conn.prepareStatement(sentencia);
            pstmt.setString(1,nombre);
            pstmt.setString(2,prov);
            pstmt.setString(3,pais);

            rs = pstmt.executeQuery();
            //Lo siguiente puede ser ineficiente revisar
            if(rs.next()) {
                String codigo = rs.getString("CODIGOLOCALIDAD");
                loc = buscarLocalidad(codigo);
            }
            //
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return loc;
    }


	@Override
	public List<String> getNacionalidad() {
		String sentencia = ALL_NACIONALIDAD;
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> Nacionalidad = new ArrayList<>();
        
        try {
        	pstmt = conn.prepareStatement(sentencia);
        	rs = pstmt.executeQuery();
        	
        	while(rs.next()) {
                String getNacionalidad = rs.getString("nacionalidad");
                Nacionalidad.add(getNacionalidad);
            }
		}catch(SQLException e){
			e.printStackTrace();
		}
		return Nacionalidad;
	}

}

