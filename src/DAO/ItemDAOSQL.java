package DAO;

import Dominio.Habitacion;
import Dominio.ItemConsumo;
import Dominio.SeccionConsumo;
import utils.Converter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAOSQL implements ItemDAO{


    private Connection conn;
    private Converter converter = new Converter();

    //---
    //Constructor
    public ItemDAOSQL(Connection unConn){
        this.conn = unConn;

    }
    //Query Sentences
    private static final String GET_ITEM =
            "\n" +
                    " SELECT * FROM ITEM_CONSUMO" +
                    " WHERE id_item = ? ";
    private static final String GET_SECCION =
            "\n" +
                    " SELECT * FROM SECCION_CONSUMO" +
                    " WHERE id_categoria = ? ";




    public ItemConsumo getItem(int dbid){
        ItemConsumo item = new ItemConsumo();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_ITEM);
            pstmt.setInt(1,dbid);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                item.setId_item(rs.getInt("ID_ITEM"));
                item.setSeccionConsumo(getSeccionConsumo(rs.getInt("ID_CATEGORIA")));
                item.setCosto(rs.getDouble("COSTO"));
                item.setNombre(rs.getString("NOMBRE"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return item;
    }

    private SeccionConsumo getSeccionConsumo(int dbid){
        SeccionConsumo seccionConsumo = new SeccionConsumo();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_SECCION);
            pstmt.setInt(1,dbid);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                seccionConsumo.setSeccion(rs.getString("TIPO"));
                seccionConsumo.setId_categoria(rs.getInt("ID_CATEGORIA"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return seccionConsumo;
    }
}
