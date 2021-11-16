package DAO;

import DAO.utils.DB;
import Dominio.IDType;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IDTypeDAOSQL implements IDTypeDAO {

    private static final String GET_ALL =
            "\n" +
                    "SELECT * FROM IDTYPE";
    private static final String GET_ID =
            "\n" +
                    "SELECT * FROM IDTYPE " +
                    "WHERE (TIPODEID = ";


    @Override
    public List<IDType> GetAllIDType() {
        List<IDType> lista = new ArrayList<>();
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_ALL);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                IDType ID = new IDType();
                ID.setTipoDeID(rs.getString("TIPODEID"));
                lista.add(ID);
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

        return lista;
    }


    @Override
    public IDType getIDType(String ID) {
        ID = ID.toUpperCase();
        String Sentencia = GET_ID + "\'"+ID+"\'" + ")";
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        IDType unID = new IDType();

        try {
            pstmt = conn.prepareStatement(Sentencia);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                unID.setTipoDeID(rs.getString("TIPODEID"));
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

        return unID;
    }
}
