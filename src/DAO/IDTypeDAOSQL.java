package DAO;

import DAO.utils.DB;
import Dominio.IDType;
import Dominio.PosIVA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IDTypeDAOSQL implements IDTypeDAO{

    private static final String GET_ALL =
            "\n" +
                    "SELECT FROM IDTYPE";


    @Override
    public List<IDType> GetAllIDType() {
        List<IDType> lista = new ArrayList<>();
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_ALL);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                IDType ID = new IDType();
                ID.setTipoDeID(rs.getString("TIPODEID"));
                lista.add(ID);
            }
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

        return lista;
    }


}
