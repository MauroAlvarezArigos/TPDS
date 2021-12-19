package DAO;

import Dominio.Habitacion;
import Dominio.Ocupacion;
import utils.Converter;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OcupacionDAOSQL implements OcupacionDAO{

    private Connection conn;
    private PasajeroDAOSQL PasajeroDAO;
    private Converter converter = new Converter();

    public OcupacionDAOSQL(Connection unConn){
        this.conn = unConn;
        this.PasajeroDAO = new PasajeroDAOSQL(unConn);
    }

    private static final String GET_ALL_OCUPACIONES_HABITACION =
            "\n" +
                    " SELECT * FROM OCUPACION" +
                    " WHERE numero = ? AND piso = ? ";
    private static final String GET_ALL_OCUPACIONES_HABITACION_DESDE_HASTA =
            GET_ALL_OCUPACIONES_HABITACION + " AND ((checkout <= ? AND checkout >= ?) OR (checkin >= ? AND checkout <= ?) OR (checkin <= ? AND checkin >= ?)" +
                    " OR (checkin <= ? AND checkout >= ?)) ";
    private static final String GET_ALL_FUERADESERVICIO_HABITACION =
            "\n" +
                    " SELECT * FROM FUERADESERVICIO" +
                    " WHERE numero = ? AND piso = ? ";
    private static final String INSERT_OCUPACION =
            "\n" +
                    " INSERT INTO OCUPACION( numero, piso, responsable, checkin, checkout)" +
                    " VALUES( ?, ?, ?, ?, ?) RETURNING id_ocupacion";
    private static final String INSERT_ACOMPANANTES =
            "\n" +
                    " INSERT INTO ACOMPANANTES(pasajero,ocupacion)" +
                    " VALUES( ?, ?)";


    public List<Ocupacion> getOcupacionesHabDesdeHasta(Habitacion unHab, LocalDate desde, LocalDate hasta){
        List<Ocupacion> LOcupaciones = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_ALL_OCUPACIONES_HABITACION_DESDE_HASTA);
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
                Ocupacion o = new Ocupacion();
                o.setHabitacion(unHab);
                o.setId(rs.getInt("ID_OCUPACION"));
                o.setCheckIn(converter.convertToLocalDateViaInstant(rs.getDate("CHECKIN")));
                o.setCheckOut(converter.convertToLocalDateViaInstant(rs.getDate("CHECKOUT")));
                o.setResponsable(this.PasajeroDAO.getPasajeroDbid(rs.getInt("RESPONSABLE")));
                o.setAcompanantes(this.PasajeroDAO.getAcompanantesOcupacion(o.getId()));
                LOcupaciones.add(o);
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

        return LOcupaciones;
    }
}
