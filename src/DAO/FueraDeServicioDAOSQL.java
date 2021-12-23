package DAO;

import Dominio.FueraDeServicio;
import Dominio.Habitacion;
import utils.Converter;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FueraDeServicioDAOSQL implements FueraDeServicioDAO{


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
    public FueraDeServicioDAOSQL(Connection unConn){
        this.conn = unConn;
        this.TipoHabDAO = new TipoHabitacionDAOSQL(unConn);
        this.PasajeroDAO = new PasajeroDAOSQL(unConn);
        this.OcupacionDAO = new OcupacionDAOSQL(unConn);
    }

    //Query Sentences
    private static final String GET_ALL_FUERADESERVICIO_HABITACION =
            "\n" +
                    " SELECT * FROM FUERADESERVICIO" +
                    " WHERE numero = ? AND piso = ? ";
    private static final String GET_ALL_FUERADESERVICIO_HABITACION_DESDE_HASTA =
            GET_ALL_FUERADESERVICIO_HABITACION +
                    " AND ((hasta <= ? AND hasta >= ?)" +
                    " OR (desde >= ? AND hasta <= ?)" +
                    " OR (desde <= ? AND desde >= ?)" +
                    " OR (desde <= ? AND hasta >= ?)) ";

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
                f.setDesde(converter.convertToLocalDateViaInstant(rs.getDate("DESDE")));
                f.setHasta(converter.convertToLocalDateViaInstant(rs.getDate("HASTA")));
                f.setId(rs.getInt("ID_FUERADESERVICIO"));
                LFueraDeServicio.add(f);
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

        return LFueraDeServicio;
    }

    public List<FueraDeServicio> getAllFueraDeServiciohabitacionDesdeHasta(Habitacion unHab, LocalDate desde, LocalDate hasta){
        List<FueraDeServicio> LFueraDeServicio = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_ALL_FUERADESERVICIO_HABITACION_DESDE_HASTA);
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
                FueraDeServicio f = new FueraDeServicio();
                f.setHabitacion(unHab);
                f.setDesde(converter.convertToLocalDateViaInstant(rs.getDate("DESDE")));
                f.setHasta(converter.convertToLocalDateViaInstant(rs.getDate("HASTA")));
                f.setId(rs.getInt("ID_FUERADESERVICIO"));
                LFueraDeServicio.add(f);
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

        return LFueraDeServicio;
    }
}
