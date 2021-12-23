package DAO;

import Dominio.*;
import utils.Converter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAOSQL implements FacturaDAO{

    private Connection conn;
    private Converter converter = new Converter();
    private PasajeroDAOSQL pasajeroDAO;

    //---
    //Constructor
    public FacturaDAOSQL(Connection unConn){
        this.conn = unConn;
        this.pasajeroDAO = new PasajeroDAOSQL(unConn);
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
    private static final String GET_FACTURA_OCUPACION =
            "\n" +
                    " SELECT * FROM FACTURA " +
                    " WHERE (id_ocupacion = ?)";
    private static final String GET_RESPONSABLE =
            "\n" +
                    " SELECT * FROM RESPONSABLEDEPAGO " +
                    " WHERE (id_responsable = ?)";
    private static final String GET_NOTA_CREDITO =
            "\n" +
                    " SELECT * FROM NOTADECREDITO " +
                    " WHERE (id_nota_de_credito = ?)";
    private static final String GET_ESTADIA =
            "\n" +
                    " SELECT * FROM PERIODOESTADIA " +
                    " WHERE (id_estadia = ?)";
    private static final String GET_TIPO_ID =
            "\n" +
                    " SELECT * FROM TIPODEFACTURA " +
                    " WHERE (id_tipo_factura = ?)";
    private static final String GET_DETALLE =
            "\n" +
                    " SELECT * FROM DETALLEFACTURA " +
                    " WHERE (id_detalle = ?)";
    private static final String GET_UNIDADES_DETALLE =
            "\n" +
                    " SELECT * FROM DETALLEUNIDADES du" +
                    " JOIN UNIDADES u ON (du.id_unidades = u.id_unidades)"+
                    " WHERE du.id_detalle = ?";

    private static final String GET_ITEM_UNIDADES =
            "\n" +
                    " SELECT * FROM ITEM_CONSUMO" +
                    " WHERE id_item = ?";
    private static final String GET_SECCION_CONSUMO =
            "\n" +
                    " SELECT * FROM SECCION_CONSUMO" +
                    " WHERE id_categoria = ?";

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

    @SuppressWarnings("resource")
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

    public List<Factura> getFacturaOcupacion(Ocupacion unOcupacion){
        List<Factura> LFacturas = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_FACTURA_OCUPACION);
            pstmt.setInt(1,unOcupacion.getId());
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Factura f = new Factura();
                f.setOcupacion(unOcupacion);
                f.setId_factura(rs.getInt("ID_FACTURA"));
                f.setFecha(converter.convertToLocalDateViaInstant(rs.getDate("FECHA")));
                f.setMontoTotal(rs.getDouble("MONTOTOTAL"));
                f.setResponsable(this.getResponsable(rs.getInt("ID_RESPONSABLE")));
                f.setNotaDeCredito(this.getNotaDeCredito(rs.getInt("ID_NOTA_DE_CREDITO")));
                f.setEstadia(this.getEstadia(rs.getInt("ID_ESTADIA")));
                f.setTipo(this.getTipoFacturaID(rs.getInt("ID_TIPO_FACTURA")));
                f.setDetalle(this.getDetalle(rs.getInt("ID_DETALLE")));
                LFacturas.add(f);

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

        return LFacturas;

    }

    private PeriodoEstadia getEstadia(int id){
        PeriodoEstadia estadia = new PeriodoEstadia();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_ESTADIA);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                estadia.setId_estadia(id);
                estadia.setMediaEstadia(rs.getBoolean("MEDIA_ESTADIA"));
                estadia.setFechaFinal(converter.convertToLocalDateViaInstant(rs.getDate("FECHAHASTA")));
                estadia.setFechaInicio(converter.convertToLocalDateViaInstant(rs.getDate("FECHADESDE")));
                estadia.setMonto(rs.getDouble("MONTO"));
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

        return estadia;
    }

    private ResponsableDePago getResponsable(int id){
        ResponsableDePago responsable = new ResponsableDePago();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_RESPONSABLE);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                responsable.setId_responsableDePago(id);
                responsable.setCuitDni(rs.getString("CUITDNI"));
                responsable.setNumDireccion(rs.getString("NUMDIRECCION"));
                responsable.setCalle(rs.getString("CALLE"));
                responsable.setTelefono(rs.getString("TELEFONO"));
                responsable.setRazonSocial(rs.getString("RAZONSOCIAL"));
                if(rs.getObject("idpersona") == null){
                    responsable.setPersona_asociada(null);
                }else{
                    responsable.setPersona_asociada(pasajeroDAO.getPasajeroDbid(rs.getInt("idpersona")));
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

        return responsable;

    }

    private NotaDeCredito getNotaDeCredito(int id){
        NotaDeCredito nota = new NotaDeCredito();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_NOTA_CREDITO);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                nota.setId_nota(rs.getInt("ID_NOTA_DE_CREDITO"));
                nota.setResponsable(rs.getString("RESPONSABLE_PAGO"));
                nota.setCodigo_factura(rs.getInt("CODIGO_FACTURA"));
                nota.setFechaFactura(converter.convertToLocalDateViaInstant(rs.getDate("FECHAFACTURA")));
                nota.setIva(rs.getDouble("IVA"));
                nota.setDnicuit(rs.getString("CUIT_DNI"));

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

        return nota;
    }

    private TipoDeFactura getTipoFacturaID(int id){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        TipoDeFactura TipoFactura = new TipoDeFactura();

        try {
            pstmt = conn.prepareStatement(GET_TIPO_ID);
            pstmt.setInt(1,id);
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

    private DetalleFactura getDetalle(int id){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DetalleFactura detalle = new DetalleFactura();

        try {
            pstmt = conn.prepareStatement(GET_DETALLE);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                detalle.setId_detalle(id);
                detalle.setCostoTotal(rs.getDouble("COSTOTOTAL"));
                detalle.setListaItems(getListaItems(id));
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

        return detalle;

    }

    private List<Unidades> getListaItems(int id){
        List<Unidades> LUnidades = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(GET_UNIDADES_DETALLE);
            pstmt.setInt(1,id);
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
