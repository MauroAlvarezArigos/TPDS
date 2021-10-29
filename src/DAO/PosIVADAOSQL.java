package DAO;

import DAO.utils.DB;
import Dominio.Pasajero;
import Dominio.PosIVA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PosIVADAOSQL implements PosIVADAO {

    private static final String INSERT_POSIVA =
            "\n" +
                    "INSERT INTO POSIVA(IDENT, TIPO)"
                    + "VALUES( ?, ?)";

    @Override
    public PosIVA Insert(PosIVA unPosIVA) {
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(INSERT_POSIVA);
            pstmt.setInt(1, unPosIVA.getID());
            pstmt.setString(1, unPosIVA.getTipo());
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
