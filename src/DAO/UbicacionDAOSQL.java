package DAO;


import DAO.utils.DB;
import Dominio.Pais;
import Dominio.Provincia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UbicacionDAOSQL implements UbicacionDAO {

    private static final String INSERT_PAIS =
            "INSERT INTO PAIS(NOMBRE, CODIGOPAIS, NACIONALIDAD)"
                    + " VALUES(?, ?, ?)";
    private static final String SEARCH_CODE_PAIS =
            "SELECT FROM PAIS"
                    + "WHERE CODIGOPAIS = ";
    private static final String INSERT_PROVINCIA=
            "INSERT INTO PROVINCIA(NOMBRE, CODIGOPROVINCIA, PAIS)"
                    +" VALUES( ?, ?, ?)";

//Insert Pais
    @Override
    public Pais insertPais(Pais unPais){
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        try{
            pstmt = conn.prepareStatement(INSERT_PAIS);
            pstmt.setString(1, unPais.getNombre());
            pstmt.setInt(2, unPais.getCodigo());
            pstmt.setString(3, unPais.getNacionalidad());

            pstmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(pstmt!=null) pstmt.close();
                if(conn!=null) conn.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



//BuscarPaisCodigo

    @Override
    public Pais buscarCodePais(int Codigo){
        String sentencia = SEARCH_CODE_PAIS+Codigo;
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Pais pais = new Pais();

        try {
            pstmt = conn.prepareStatement(sentencia);
            rs = pstmt.executeQuery();
            pais.setNombre(rs.getString("NOMBRE"));
            pais.setCodigo(rs.getInt("CODIGOPAIS"));
            pais.setNacionalidad(rs.getString("NACIONALIDAD"));

        }catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return pais;
    }

}

//Insert Provincia

@Override
public Provincia insertProvincia(Provincia unProvincia){
    Connection conn = DB.getConexion();
    PreparedStatement pstmt = null;
    try{
        pstmt = conn.prepareStatement(INSERT_PROVINCIA);
        pstmt.setString(1, unProvincia.getNombre());
        pstmt.setInt(2, unProvincia.getCodigoProvincia());
        //pstmt.setString(3, unProvincia.get());

        pstmt.executeUpdate();
    } catch(SQLException e) {
        e.printStackTrace();
    }
    finally {
        try {
            if(pstmt!=null) pstmt.close();
            if(conn!=null) conn.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    return null;
}
}