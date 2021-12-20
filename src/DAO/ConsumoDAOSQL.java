package DAO;

import Dominio.*;
import utils.Converter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsumoDAOSQL implements ConsumoDAO{

    private Connection conn;
    private Converter converter = new Converter();

    //---
    //Constructor
    public ConsumoDAOSQL(Connection unConn){
        this.conn = unConn;
    }

    //Query Sentences
    private static final String GET_CONSUMO_OCUPACION =
            "\n" +
            " SELECT * FROM CONSUMOS_OCUPACION c" +
            " WHERE c.id_ocupacion = ?";

    private static final String GET_UNIDADES_CONSUMO =
            "\n" +
            " SELECT * FROM CONSUMO_UNIDADES cu" +
                    " JOIN UNIDADES u ON (cu.id_unidades = u.id_unidades)"+
            " WHERE cu.id_consumo = ?";
    private static final String GET_ITEM_UNIDADES =
            "\n" +
            " SELECT * FROM ITEM_CONSUMO" +
            " WHERE id_item = ?";
    private static final String GET_SECCION_CONSUMO =
            "\n" +
            " SELECT * FROM SECCION_CONSUMO" +
            " WHERE id_categoria = ?";

    @Override
    public Consumo getConsumoOcupacion(Ocupacion unOcupacion){
        Consumo consumo = new Consumo();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_CONSUMO_OCUPACION);
            pstmt.setInt(1,unOcupacion.getId());
            rs = pstmt.executeQuery();
            if(rs.next()){
                consumo.setId_consumo(rs.getInt("ID_COMSUMO"));
                consumo.setOcupacion(unOcupacion);
                consumo.setListaItems(getUnidades(consumo));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(pstmt != null) pstmt.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return consumo;
    }

    private List<Unidades> getUnidades(Consumo unConsumo){
        List<Unidades> LUnidades = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_UNIDADES_CONSUMO);
            pstmt.setInt(1,unConsumo.getId_consumo());
            rs = pstmt.executeQuery();
            while(rs.next()){
                Unidades u = new Unidades();
                u.setFechaConsumo(converter.convertToLocalDateViaInstant(rs.getDate("FECHA_CONSUMO")));
                u.setCantidad(rs.getInt("UNIDADES"));
                u.setItemConsumo(getItem(rs.getInt("ID_ITEM")));
                LUnidades.add(u);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(pstmt != null) pstmt.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return LUnidades;
    }

    private ItemConsumo getItem(int id_item ){
        ItemConsumo item = new ItemConsumo();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_ITEM_UNIDADES);
            pstmt.setInt(1,id_item);
            rs = pstmt.executeQuery();
            if(rs.next()){
                item.setId_item(rs.getInt("ID_ITEM"));
                item.setNombre(rs.getString("NOMBRE"));
                item.setCosto(rs.getDouble("COSTO"));
                item.setSeccionConsumo(getSeccionConsumo(rs.getInt("ID_CATEGORIA")));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(pstmt != null) pstmt.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return item;
    }

    private SeccionConsumo getSeccionConsumo(int id_seccion){
        SeccionConsumo seccionConsumo = new SeccionConsumo();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_SECCION_CONSUMO);
            pstmt.setInt(1,id_seccion);
            rs = pstmt.executeQuery();
            if(rs.next()){
                seccionConsumo.setId_categoria(rs.getInt("ID_CATEGORIA"));
                seccionConsumo.setSeccion(rs.getString("TIPO"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(pstmt != null) pstmt.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }

        return seccionConsumo;

    }


}
