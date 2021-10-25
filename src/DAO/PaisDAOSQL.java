package DAO;


import DAO.utils.DB;
import Dominio.Pais;
import Dominio.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaisDAOSQL implements PaisDAO{

    private static final String INSERT_PAIS =
            "INSERT INTO PAIS(NOMBRE, CODIGOPAIS, NACIONALIDAD)"
                    + " VALUES(?, ?, ?)";
    private static final String SEARCH_CODE_PAIS =
            "SELECT FROM PAIS"
                    + "WHERE CODIGOPAIS = ";

//Insert Pais
    @Override
    public Pais insert(Pais unPais){
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
    public Pais buscarCode(int Codigo){
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
