package DAO;

import Dominio.*;
import utils.Converter;

import java.sql.*;
import java.util.List;

public class FacturaDAOSQL implements FacturaDAO{

    private Connection conn;
    private Converter converter = new Converter();

    //---
    //Constructor
    public FacturaDAOSQL(Connection unConn){
        this.conn = unConn;
    }

    //Query Sentences
    private static final String GET_TIPO =
            "\n" +
                    " SELECT * FROM TIPODEFACTURA " +
                    " WHERE (tipo = ?)";
    private static final String INSERT_FACTURA =
            "\n" +
                    " INSERT INTO FACTURA (ID_ESTADIA, ID_DETALLE, ID_RESPONSABLE, ID_TIPO_FACTURA, ID_NOTA_DE_CREDITO, ID_PAGO, FECHA, MONTOTOTAL, ID_OCUPACION)" +
                    " VALUES (?, ?, ?, ?, NULL, NULL, ?, ?, ?) RETURNING ID_FACTURA";
    private static final String INSERT_ESTADIA =
            "\n" +
                    " INSERT INTO PERIODOESTADIA ( ID_OCUPACION, FECHADESDE, FECHAHASTA, MONTO, MEDIA_ESTADIA)" +
                    " VALUES ( ?, ?, ?, ?, ?) RETURNING ID_ESTADIA";
    private static final String INSERT_DETALLE =
            "\n" +
                    " INSERT INTO DETALLEFACTURA (COSTOTOTAL)" +
                    " VALUES (?) RETURNING ID_DETALLE";
    private static final String INSERT_UNIDADES =
            "\n" +
                    " INSERT INTO UNIDADES (FECHA_CONSUMO, UNIDADES)" +
                    " VALUES (?,?) RETURNING ID_UNIDADES";
    private static final String INSERT_DETALLE_UNIDADES =
            "\n" +
                    " INSERT INTO DETALLEUNIDADES ( ID_UNIDADES, ID_DETALLE, ID_ITEM)" +
                    " VALUES ( ?, ?, ?)";
    private static final String INSERT_RESPONSABLE =
            "\n" +
                    " INSERT INTO RESPONSABLEDEPAGO ( IDPERSONA, RAZONSOCIAL, CUITDNI, NUMDIRECCION, CALLE, TELEFONO)" +
                    " VALUES ( ?, ?, ?, ?, ?, ?) RETURNING ID_RESPONSABLE";

    public TipoDeFactura getTipoFactura(String tipo){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        TipoDeFactura TipoFactura = new TipoDeFactura();

        try {
            pstmt = conn.prepareStatement(GET_TIPO);
            pstmt.setString(1,tipo);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                TipoFactura.setTipo(rs.getString("TIPO"));
                TipoFactura.setId_tipo(rs.getInt("ID_TIPO_FACTURA"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return TipoFactura;
    }

    private int guardarDetalle(DetalleFactura detalle){
        int id_detalle = -1;
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(INSERT_DETALLE);
            pstmt.setDouble(1,detalle.calcularCostoTotal());
            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();
            if (resultSet.next()) {
                id_detalle = resultSet.getInt("id_detalle");
            }
            for(Unidades u : detalle.getListaItems()) {
                pstmt = conn.prepareStatement(INSERT_UNIDADES);
                pstmt.setDate(1, converter.convertToDateViaSqlDate(u.getFechaConsumo()));
                pstmt.setInt(2, u.getCantidad());
                //pstmt.setInt(3, u.get);
                pstmt.execute();
                ResultSet rs = pstmt.getResultSet();
                int id_unidades = -1;
                if(rs.next()){
                   id_unidades = rs.getInt("ID_UNIDADES");
                    pstmt = conn.prepareStatement(INSERT_DETALLE_UNIDADES);
                    pstmt.setInt(1,id_unidades);
                    pstmt.setInt(2,id_detalle);
                    pstmt.setInt(3,u.getItemConsumo().getId_item());
                    pstmt.executeUpdate();
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id_detalle;
    }

    private int guardarEstadia(PeriodoEstadia estadia, Ocupacion ocupacion){
        int id_estadia = -1;
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(INSERT_ESTADIA);
            pstmt.setInt(1,ocupacion.getId());
            pstmt.setDate(2, converter.convertToDateViaSqlDate(estadia.getFechaInicio()));
            pstmt.setDate(3, converter.convertToDateViaSqlDate(estadia.getFechaFinal()));
            pstmt.setDouble(4, estadia.getMonto());
            pstmt.setBoolean(5,estadia.getMediaEstadia());

            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();

            if (resultSet.next()) {
                id_estadia = resultSet.getInt("ID_ESTADIA");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return id_estadia;
    }

    public int guardarResponsable(ResponsableDePago responsable){
        int id_responsable = -1;
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(INSERT_RESPONSABLE);
            if(responsable.getPersona_asociada() != null) {
                pstmt.setInt(1, responsable.getPersona_asociada().getIdpersona());
            }else{
                pstmt.setNull(1, Types.NULL);
            }
            pstmt.setString(2,responsable.getRazonSocial());
            pstmt.setString(3,responsable.getCuitDni());
            pstmt.setString(4, responsable.getNumDireccion());
            pstmt.setString(5, responsable.getCalle());
            pstmt.setString(6, responsable.getTelefono());

            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();

            if (resultSet.next()) {
                id_responsable = resultSet.getInt("ID_RESPONSABLE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id_responsable;
    }

    public void guardarFactura(Factura factura) {
        PreparedStatement pstmt = null;
        int id_estadia;
        int id_detalle;
        int id_responsable;

        id_detalle = guardarDetalle(factura.getDetalle());
        id_estadia = guardarEstadia(factura.getEstadia(), factura.getOcupacion());
        id_responsable = guardarResponsable(factura.getResponsable());

        try {
            pstmt = conn.prepareStatement(INSERT_FACTURA);
            pstmt.setInt(1,id_estadia);
            pstmt.setInt(2,id_detalle);
            pstmt.setInt(3,id_responsable);
            pstmt.setInt(4,factura.getTipo().getId_tipo());
            pstmt.setDate(5, converter.convertToDateViaSqlDate(factura.getFecha()));
            pstmt.setDouble(6, factura.getMontoTotal());
            pstmt.setInt(7, factura.getOcupacion().getId());
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
