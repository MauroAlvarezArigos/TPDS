package DAO;

import Dominio.Habitacion;
import Dominio.Reserva;
import utils.Converter;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAOSQL implements ReservaDAO{

    private Connection conn;
    @SuppressWarnings("unused")
	private TipoHabitacionDAOSQL TipoHabDAO;
    @SuppressWarnings("unused")
	private PasajeroDAOSQL PasajeroDAO;
    @SuppressWarnings("unused")
	private OcupacionDAOSQL OcupacionDAO;
    private Converter converter = new Converter();

    //---
    //Constructor
    public ReservaDAOSQL(Connection unConn){
        this.conn = unConn;
        this.TipoHabDAO = new TipoHabitacionDAOSQL(unConn);
        this.PasajeroDAO = new PasajeroDAOSQL(unConn);
        this.OcupacionDAO = new OcupacionDAOSQL(unConn);
    }
    //Query Sentences
    private static final String GET_ALL_RESERVAS_HABITACION =
            "\n" +
                    " SELECT * FROM RESERVA" +
                    " WHERE numero = ? AND piso = ? ";
    private static final String GET_ALL_RESERVAS_HABITACION_DESDE_HASTA =
            GET_ALL_RESERVAS_HABITACION+
                    " AND ((fechahasta <= ? AND fechahasta >= ?)" +
                    " OR (fechadesde >= ? AND fechahasta <= ?)" +
                    " OR (fechadesde <= ? AND fechadesde >= ?)" +
                    " OR (fechadesde <= ? AND fechahasta >= ?)) ";

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
                r.setFechaDesde(converter.convertToLocalDateViaInstant(rs.getDate("FECHADESDE")));
                r.setFechaHasta(converter.convertToLocalDateViaInstant(rs.getDate("FECHAHASTA")));
                LReservas.add(r);
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

        return LReservas;
    }

    public List<Reserva> getReservasHabitacionDesdeHasta(Habitacion unHab, LocalDate desde, LocalDate hasta){
        List<Reserva> LReservas = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_ALL_RESERVAS_HABITACION_DESDE_HASTA);
            pstmt.setInt(1,unHab.getNumero());
            pstmt.setInt(2,unHab.getPiso());
            pstmt.setDate(3,converter.convertToDateViaSqlDate(hasta));
            pstmt.setDate(4,converter.convertToDateViaSqlDate(desde));
            pstmt.setDate(5,converter.convertToDateViaSqlDate(desde));
            pstmt.setDate(6,converter.convertToDateViaSqlDate(hasta));
            pstmt.setDate(7,converter.convertToDateViaSqlDate(hasta));
            pstmt.setDate(8,converter.convertToDateViaSqlDate(desde));
            pstmt.setDate(9,converter.convertToDateViaSqlDate(desde));
            pstmt.setDate(10,converter.convertToDateViaSqlDate(hasta));
            rs = pstmt.executeQuery();
            while(rs.next()){
                Reserva r = new Reserva();
                r.setNombre(rs.getString("NOMBRE"));
                r.setApellido(rs.getString("APELLIDO"));
                r.setIdReserva(rs.getInt("ID_RESERVA"));
                r.setTelefono(rs.getString("TELEFONO"));
                r.setHabitacion(unHab);
                r.setFechaDesde(converter.convertToLocalDateViaInstant(rs.getDate("FECHADESDE")));
                r.setFechaHasta(converter.convertToLocalDateViaInstant(rs.getDate("FECHAHASTA")));
                LReservas.add(r);
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

        return LReservas;
    }

}
