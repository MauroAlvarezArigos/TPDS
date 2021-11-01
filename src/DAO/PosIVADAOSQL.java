package DAO;

import DAO.utils.DB;
import Dominio.PosIVA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PosIVADAOSQL implements PosIVADAO {

    private static final String INSERT_POSIVA =
            "\n" +
                    "INSERT INTO POSIVA(IDENT, TIPO)"
                    + "VALUES( ?, ?)";
    private static final String GET_ALL =
            "\n" +
                    "SELECT FROM POSIVA";

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

    @Override
    public List<PosIVA> GetListIVA() {
        List<PosIVA> lista = new ArrayList<>();
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_ALL);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                PosIVA iva = new PosIVA();
                iva.setID(rs.getInt("IDENT"));
                iva.setTipo(rs.getString("TIPO"));
                lista.add(iva);
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
