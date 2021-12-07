package DAO;

import Dominio.Habitacion;
import Dominio.TipoHabitacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoHabitacionDAOSQL {
    private Connection conn;

    public TipoHabitacionDAOSQL(Connection unConn){
        this.conn = unConn;
    }

    //---
    //Query
    private static final String GET_TIPO_HABITACION =
            "\n" +
            "SELECT * FROM TIPOHABITACION t " +
                    "WHERE id = ?";

    public TipoHabitacion getTipoHabitacion(int id){
        TipoHabitacion tipo = new TipoHabitacion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_TIPO_HABITACION);
            pstmt.setInt(1,id);

            rs = pstmt.executeQuery();

            if(rs.next()) {
                tipo.setTipo(rs.getString("TIPO"));
                tipo.setCosto(rs.getFloat("COSTO"));
                tipo.setId(rs.getInt("ID"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (pstmt != null) {
                    System.out.println("Cerre pstmt");
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tipo;
    }
}
