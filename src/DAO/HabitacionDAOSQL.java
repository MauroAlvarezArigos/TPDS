package DAO;

import Dominio.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAOSQL implements HabitacionDAO {

    private Connection conn;
    private TipoHabitacionDAOSQL TipoHabDAO;

    //---
    //Constructor
    public HabitacionDAOSQL(Connection unConn){
        this.conn = unConn;
        this.TipoHabDAO = new TipoHabitacionDAOSQL(unConn);
    }

    //Query Sentences
    private static final String GET_ALL =
            "\n" +
            "SELECT * FROM HABITACION ";
    private static final String GET_ALL_RESERVAS_HABITACION =
            "\n" +
            "SELECT * FROM RESERVA" +
                    " WHERE numerohabitacion = ? AND pisohabitacion = ? ";
    private static final String GET_ALL_RESERVAS_HABITACION_DESDE_HASTA =
            GET_ALL_RESERVAS_HABITACION+" AND ((fechadesde BETWEEN ? AND ?) or (fechahasta BETWEEN ? AND ?)) ";
    private static final String GET_ALL_OCUPACIONES_HABITACION =
            "\n" +
            "SELECT * FROM OCUPACION" +
            " WHERE numerohabitacion = ? AND pisohabitacion = ? ";
    private static final String GET_ALL_OCUPACIONES_HABITACION_DESDE_HASTA =
            GET_ALL_OCUPACIONES_HABITACION + " AND ((checkin BETWEEN ? AND ?) or (checkout BETWEEN ? AND ?)) ";
    private static final String GET_ALL_FUERADESERVICIO_HABITACION =
            "\n" +
            "SELECT * FROM FUERADESERVICIO" +
            " WHERE numerohabitacion = ? AND pisohabitacion = ? ";
    private static final String GET_ALL_FUERADESERVICIO_HABITACION_DESDE_HASTA =
            GET_ALL_FUERADESERVICIO_HABITACION + " AND ((desde BETWEEN ? AND ?) or (hasta BETWEEN ? AND ?)) ";

    /*public List<Ocupacion> getOcupacionDesdeHasta(){
        //todo mover a OcupacionDAO
    }*/

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
                    System.out.println("Cerre pstmt");
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lista;
    }

    public List<Reserva> getReservasHab(Habitacion unHab){
        List<Reserva> LReservas = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_ALL_RESERVAS_HABITACION);
            pstmt.setInt(1,unHab.getNumero());
            pstmt.setInt(2,unHab.getPiso());
            rs = pstmt.executeQuery();
            while(rs.next()){
                Reserva r = new Reserva();
                r.setNombre(rs.getString("NOMBRE"));
                r.setApellido(rs.getString("APELLIDO"));
                r.setIdReserva(rs.getInt("ID_RESERVA"));
                r.setTelefono(rs.getString("TELEFONO"));
                r.setHabitacion(unHab);
                r.setFechaDesde(rs.getDate("FECHADESDE"));
                r.setFechaHasta(rs.getDate("FECHAHASTA"));
                LReservas.add(r);
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

        return LReservas;
    }

    public List<Reserva> getReservasHabitacionDesdeHasta(Habitacion unHab, Date desde, Date hasta){
        List<Reserva> LReservas = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_ALL_RESERVAS_HABITACION_DESDE_HASTA);
            pstmt.setInt(1,unHab.getNumero());
            pstmt.setInt(2,unHab.getPiso());
            pstmt.setDate(3,desde);
            pstmt.setDate(4,hasta);
            pstmt.setDate(5,desde);
            pstmt.setDate(6,hasta);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Reserva r = new Reserva();
                r.setNombre(rs.getString("NOMBRE"));
                r.setApellido(rs.getString("APELLIDO"));
                r.setIdReserva(rs.getInt("ID_RESERVA"));
                r.setTelefono(rs.getString("TELEFONO"));
                r.setHabitacion(unHab);
                r.setFechaDesde(rs.getDate("FECHADESDE"));
                r.setFechaHasta(rs.getDate("FECHAHASTA"));
                LReservas.add(r);
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

        return LReservas;
    }

    public List<Ocupacion> getOcupacionesHab(Habitacion unHab){
        List<Ocupacion> LOcupaciones = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_ALL_OCUPACIONES_HABITACION);
            pstmt.setInt(1,unHab.getNumero());
            pstmt.setInt(2,unHab.getPiso());
            rs = pstmt.executeQuery();
            while(rs.next()){
                Ocupacion o = new Ocupacion();
                o.setHabitacion(unHab);
                o.setCheckIn(rs.getDate("CHECKIN"));
                o.setCheckOut(rs.getDate("CHECKOUT"));
                o.setId(rs.getInt("ID_OCUPACION"));
                LOcupaciones.add(o);
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

        return LOcupaciones;
    }

    public List<Ocupacion> getOcupacionesHabDesdeHasta(Habitacion unHab, Date desde, Date hasta){
        List<Ocupacion> LOcupaciones = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_ALL_OCUPACIONES_HABITACION_DESDE_HASTA);
            pstmt.setInt(1,unHab.getNumero());
            pstmt.setInt(2,unHab.getPiso());
            pstmt.setDate(3,desde);
            pstmt.setDate(4,hasta);
            pstmt.setDate(5,desde);
            pstmt.setDate(6,hasta);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Ocupacion o = new Ocupacion();
                o.setHabitacion(unHab);
                o.setCheckIn(rs.getDate("CHECKIN"));
                o.setCheckOut(rs.getDate("CHECKOUT"));
                o.setId(rs.getInt("ID_OCUPACION"));
                LOcupaciones.add(o);
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

        return LOcupaciones;
    }

    public List<FueraDeServicio> getAllFueraDeServiciohabitacion(Habitacion unHab){
        List<FueraDeServicio> LFueraDeServicio = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_ALL_FUERADESERVICIO_HABITACION);
            pstmt.setInt(1,unHab.getNumero());
            pstmt.setInt(2,unHab.getPiso());
            rs = pstmt.executeQuery();
            while(rs.next()){
                FueraDeServicio f = new FueraDeServicio();
                f.setHabitacion(unHab);
                f.setDesde(rs.getDate("CHECKIN"));
                f.setHasta(rs.getDate("CHECKOUT"));
                f.setId(rs.getInt("ID_OCUPACION"));
                LFueraDeServicio.add(f);
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

        return LFueraDeServicio;
    }

    public List<FueraDeServicio> getAllFueraDeServiciohabitacionDesdeHasta(Habitacion unHab, Date desde, Date hasta){
        List<FueraDeServicio> LFueraDeServicio = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_ALL_OCUPACIONES_HABITACION_DESDE_HASTA);
            pstmt.setInt(1,unHab.getNumero());
            pstmt.setInt(2,unHab.getPiso());
            pstmt.setDate(3,desde);
            pstmt.setDate(4,hasta);
            pstmt.setDate(5,desde);
            pstmt.setDate(6,hasta);
            rs = pstmt.executeQuery();
            while(rs.next()){
                FueraDeServicio f = new FueraDeServicio();
                f.setHabitacion(unHab);
                f.setDesde(rs.getDate("CHECKIN"));
                f.setHasta(rs.getDate("CHECKOUT"));
                f.setId(rs.getInt("ID_OCUPACION"));
                LFueraDeServicio.add(f);
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

        return LFueraDeServicio;
    }


}
