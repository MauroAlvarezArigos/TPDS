package DAO;

import DAO.utils.ConnectionWrapper;
import DAO.utils.DB;
import Dominio.PosIVA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PosIVADAOSQL implements PosIVADAO {


    //Get DB Connection
    //---
    private ConnectionWrapper wrapper;
    private Connection connection;

    //Constructor
    //---
    public PosIVADAOSQL(){
        wrapper = new ConnectionWrapper();
        connection = wrapper.getConnection();
    }

    //Query Sentences
    //---
    private static final String INSERT_POSIVA =
            "\n" +
                    "INSERT INTO POSIVA(IDENT, TIPO)"
                    + "VALUES( ?, ?)";
    private static final String GET_ALL =
            "\n" +
                    "SELECT FROM POSIVA";
    private static final String GET_IVA =
            "\n" +
                    "SELECT * FROM POSIVA" +
                    " WHERE TIPO =";
    private static final String SEARCH_IVA =
            "\n" +
                    "SELECT * FROM POSIVA" +
                    " WHERE IDENT=";


    //Guardar una nueva posicion IVA --(Revisar: puede no ser necesario)--
    //---
    @Override
    public void Insert(PosIVA unPosIVA) {
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
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }


    //Obtiene la lista de todos las posibles posiciones de IVA
    //---
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
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

		return lista;
}


    //Obtiene la Posicion de IVA que representa una String
    //---
    public PosIVA getIVA(String PosIVA){
        String Sentencia = GET_IVA + PosIVA;
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PosIVA iva = new PosIVA();

        try {
            pstmt = conn.prepareStatement(Sentencia);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                iva.setID(rs.getInt("IDENT"));
                iva.setTipo(rs.getString("TIPO"));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(pstmt != null) pstmt.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }

        return iva;


    }


    public PosIVA BuscarIVA(int ident){
        String Sentencia = SEARCH_IVA + ident;
        Connection conn = DB.getConexion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PosIVA iva = new PosIVA();
        try {
            pstmt = conn.prepareStatement(Sentencia);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                iva.setID(rs.getInt("IDENT"));
                iva.setTipo(rs.getString("TIPO"));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(pstmt != null) pstmt.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }

        return iva;


    }
}
