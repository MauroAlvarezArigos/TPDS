package DAO;

import Dominio.*;
import utils.Converter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAOSQL implements HabitacionDAO {

    private Connection conn;
    private TipoHabitacionDAOSQL TipoHabDAO;
    private PasajeroDAOSQL PasajeroDAO;
    private OcupacionDAOSQL OcupacionDAO;
    private Converter converter = new Converter();

    //---
    //Constructor
    public HabitacionDAOSQL(Connection unConn){
        this.conn = unConn;
        this.TipoHabDAO = new TipoHabitacionDAOSQL(unConn);
        this.PasajeroDAO = new PasajeroDAOSQL(unConn);
        this.OcupacionDAO = new OcupacionDAOSQL(unConn);
    }

    //Query Sentences
    private static final String GET_ALL =
            "\n" +
            " SELECT * FROM HABITACION ";
    private static final String GET_HAB_NUM =
            "\n" +
                    "SELECT * FROM HABITACION WHERE numero = ? AND piso = ? ";

        //todo separar en OcupacionDAO, ReservaDAO, HabitacionDAO, FueraDeServicioDAO


    public List<Habitacion> getAllHabitaciones(){
        List<Habitacion> lista = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_ALL);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Habitacion hab = new Habitacion();
                hab.setCapacidad(rs.getInt("CAPACIDAD"));
                hab.setNumero(rs.getInt("NUMERO"));
                hab.setPiso(rs.getInt("PISO"));
                hab.setDescuento(rs.getInt("DESCUENTO"));
                hab.setTipo(TipoHabDAO.getTipoHabitacion(rs.getInt("TIPO")));
                lista.add(hab);
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

        return lista;
    }


   public Habitacion getHabitacion(Integer nroHab, Integer piso) {
        Habitacion hab = new Habitacion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_HAB_NUM);
            pstmt.setInt(1,nroHab);
            pstmt.setInt(2,piso);

            rs = pstmt.executeQuery();
            if(rs.next()) {
                hab.setCapacidad(rs.getInt("CAPACIDAD"));
                hab.setNumero(rs.getInt("NUMERO"));
                hab.setPiso(rs.getInt("PISO"));
                hab.setDescuento(rs.getInt("DESCUENTO"));
                hab.setTipo(TipoHabDAO.getTipoHabitacion(rs.getInt("TIPO")));
            }
            else {
                return null;
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
        return hab;
    }


}





