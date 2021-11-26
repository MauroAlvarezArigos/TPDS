package DAO;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import Excepciones.ExcepcionNoExisteElemento;
import DAO.utils.ConnectionWrapper;
import DAO.utils.DB;
import Dominio.IDType;
import Dominio.Pasajero;
import Exceptions.DuplicateDocNumberException;
import Exceptions.NoConcordanciaException;

public class PasajeroDAOSQL implements PasajeroDAO{

	//External DAO Dependencies
	private IDTypeDAOSQL IDDAO = new IDTypeDAOSQL();
	private UbicacionDAOSQL UBICACIONDAO = new UbicacionDAOSQL();
	private PosIVADAOSQL IVADAO = new PosIVADAOSQL();


	//Get DB Connection
	private ConnectionWrapper wrapper;
	private Connection connection;

	//Constructor
	public PasajeroDAOSQL(){
		wrapper = new ConnectionWrapper();
		connection = wrapper.getConnection();
	}

	//Query Sentences
	//---
	private static final String INSERT_PASAJERO =
			"\n"+
			"INSERT INTO PASAJERO(CUIT, NOMBRE, APELLIDO,"
			+"NDOC, TIPODOC, OCUPACION, FECHANAC, NACIONALIDAD)"
			+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_PERSONA =
			"\n"+
			"INSERT INTO PERSONA(TELEFONO, EMAIL, CUIT, CALLE, ALTURA, POSIVA)"
			+"VALUES( ?, ?, ?, ?, ?, ?)";
	private static final String BUSCAR_DOC_REPETIDO =
			"\n"+
			"SELECT FROM PASAJERO p" +
			" JOIN PERSONA per ON (p.idpersona = per.idpersona)" +
			" JOIN IDTYPE id ON (p.tipodoc = id.tipodeid)" +
			" WHERE (? = id.tipodeid AND ? = p.ndoc)";
	private static final String BUSCAR_DBID =
			"\n"+
			"SELECT * FROM PASAJERO p " +
			"JOIN PERSONA per ON (p.idpersona = per.idpersona) "+
			"WHERE p.ID = ";

	//Insert Pasajero
	//---
	@Override
	public Pasajero insert(Pasajero unPasajero) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement(INSERT_PASAJERO+INSERT_PERSONA);
			pstmt.setString(1, unPasajero.getCuit_cif());
			pstmt.setString(2, unPasajero.getNombre());
			pstmt.setString(3, unPasajero.getApellido());
			pstmt.setString(4, unPasajero.getNdoc());
			pstmt.setString(5, unPasajero.getTipodoc().getTipoDeID());
			pstmt.setString(6, unPasajero.getOcupacion());
			pstmt.setDate(7, unPasajero.getFechanacimiento());
			pstmt.setInt(8, unPasajero.getNacionalidad().getCodigo());
			//parte de Persona
			pstmt.setString(9, unPasajero.getTelefono());
			pstmt.setString(10, unPasajero.getEmail());
			pstmt.setString(11, unPasajero.getCuit_cif());
			pstmt.setString(12, unPasajero.getCalle());
			pstmt.setInt(13, unPasajero.getAltura());
			pstmt.setInt(14, unPasajero.getIVA().getID());
				
			pstmt.executeUpdate();
		} catch(SQLException e) {
				e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	//Buscar Pasajero por Nombre, Apellido, Tipo y Numero de Documento
	//---
	@Override
	public List<Pasajero> buscar(String nombre, String apellido, String tipoDoc, String ndoc) throws NoConcordanciaException{
		String sentencia = prepararSentencia(nombre, apellido, tipoDoc, ndoc);
		
		List<Pasajero> lista = new ArrayList<Pasajero>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sentencia);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Pasajero p = new Pasajero();
				p.setTelefono(rs.getString("TELEFONO"));
				p.setEmail(rs.getString("EMAIL"));
				p.setCuit_cif(rs.getString("CUIT"));
				p.setCalle(rs.getString("CALLE"));
				p.setAltura(rs.getInt("ALTURA"));
				p.setIVA(IVADAO.BuscarIVA(rs.getInt("POSIVA")));
				p.setNombre(rs.getString("NOMBRE"));
				p.setApellido(rs.getString("APELLIDO"));
				p.setNdoc(rs.getString("NDOC"));
				p.setFechanacimiento(rs.getDate("FECHANAC"));
				//Puede estar mal este set
				p.setTipodoc(IDDAO.getIDType(rs.getString("TIPODOC")));
				p.setOcupacion(rs.getString("OCUPACION"));
				//Puede estar mal este set
				p.setNacionalidad(UBICACIONDAO.buscarCodePais(rs.getInt("NACIONALIDAD")));
				p.setLocalidad(UBICACIONDAO.buscarLocalidad(rs.getString("LOCALIDAD")));

				lista.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return lista;
		
		
	}

	public List<Pasajero> buscarGestion(String nombre, String apellido, String tipoDoc, String ndoc) throws NoConcordanciaException{
		String sentencia = prepararSentencia(nombre, apellido, tipoDoc, ndoc);

		List<Pasajero> lista = new ArrayList<Pasajero>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sentencia);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Pasajero p = new Pasajero();
				p.setID(rs.getInt("idpersona"));
				p.setNombre(rs.getString("NOMBRE"));
				p.setApellido(rs.getString("APELLIDO"));
				p.setNdoc(rs.getString("NDOC"));
				p.setTipodoc(IDDAO.getIDType(rs.getString("TIPODOC")));

				lista.add(p);
			}
		}catch(SQLException e) {
		e.printStackTrace();
		}
		finally {
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	return lista;
}

	//Revisar si existe un documento repetido
	//---
	@Override
	public void docRepetido(IDType IDtipo, String Ndoc) throws DuplicateDocNumberException {
		List<Pasajero> lista = new ArrayList<Pasajero>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(BUSCAR_DOC_REPETIDO);
			pstmt.setString(1, IDtipo.getTipoDeID());
			pstmt.setString(2, Ndoc);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Pasajero p = new Pasajero();
				p.setTelefono(rs.getString("TELEFONO"));
				p.setEmail(rs.getString("EMAIL"));
				p.setCalle(rs.getString("CALLE"));
				p.setAltura(rs.getInt("ALTURA"));
				p.setCuit_cif(rs.getString("CUIT"));
				p.setNombre(rs.getString("NOMBRE"));
				p.setApellido(rs.getString("APELLIDO"));
				p.setNdoc(rs.getString("NDOC"));
				//p.setTipodoc(rs.getString("TIPODOC"));
				p.setOcupacion(rs.getString("OCUPACION"));
				//p.setNacionalidad(rs.getInt("NACIONALIDAD")); Crear dao de pais
				lista.add(p);
			}
			if(lista.size() > 0) {
				throw new DuplicateDocNumberException();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}


	}

	//Busca el pasajero que posee la ID = BDID
	//---
	public Pasajero getPasajeroDbid(int DBID) {
		String sentencia = BUSCAR_DBID + DBID;
		Pasajero unPasajero = new Pasajero();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sentencia);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				unPasajero.setID(rs.getInt("idpersona"));
				unPasajero.setTelefono(rs.getString("TELEFONO"));
				unPasajero.setEmail(rs.getString("EMAIL"));
				unPasajero.setCuit_cif(rs.getString("CUIT"));
				unPasajero.setCalle(rs.getString("CALLE"));
				unPasajero.setAltura(rs.getInt("ALTURA"));
				unPasajero.setIVA(IVADAO.BuscarIVA(rs.getInt("POSIVA")));
				unPasajero.setNombre(rs.getString("NOMBRE"));
				unPasajero.setApellido(rs.getString("APELLIDO"));
				unPasajero.setNdoc(rs.getString("NDOC"));
				unPasajero.setFechanacimiento(rs.getDate("FECHANAC"));
				//Puede estar mal este set
				unPasajero.setTipodoc(IDDAO.getIDType(rs.getString("TIPODOC")));
				unPasajero.setOcupacion(rs.getString("OCUPACION"));
				//Puede estar mal este set
				unPasajero.setNacionalidad(UBICACIONDAO.buscarCodePais(rs.getInt("NACIONALIDAD")));
				unPasajero.setLocalidad(UBICACIONDAO.buscarLocalidad(rs.getString("LOCALIDAD")));
			}}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return unPasajero;
	}


	//Utility: Prepara sentencia para busqueda de pasajero
	//---
	private String prepararSentencia(String nombre, String apellido, String tipoDoc, String ndoc) {
		String p1 = "SELECT * FROM PASAJERO p " +
				"JOIN PERSONA per ON (p.idpersona = per.idpersona) ";
		String tmp = "";
		int cont = 0;

		if(nombre.equals("") && apellido.equals("") && tipoDoc.equals("") && ndoc.equals("")) {
			return p1;
		} else {
			tmp = tmp + "WHERE ";
			System.out.println("Where concatenado");

			if(!nombre.equals("")) {
				System.out.println("Nombre concatenado");

				tmp = tmp + " NOMBRE LIKE '%"+ nombre +"%'";
				cont++;
			}

			if(!apellido.equals("")) {
				if(cont == 0) {
					tmp = tmp + " APELLIDO LIKE '%"+ apellido +"%'";
				}
				else {
					tmp = tmp + " AND APELLIDO LIKE '%"+ apellido +"%'";

				}
				System.out.println("apellido concatenado");

				cont++;
			}
			if(!tipoDoc.equals("")) {
				System.out.println("tdoc concatenado");

				if(cont == 0) {
					tmp = tmp + " TIPODOC = '"+ tipoDoc +"'";
				}
				else {
					tmp = tmp + " AND TIPODOC = '"+ tipoDoc +"'";

				}
				cont++;
			}
			if(!ndoc.equals("")) {
				System.out.println("doc concatenado");

				if(cont == 0) {
					tmp = tmp + "NDOC = '"+ ndoc + "'";
				}
				else {
					tmp = tmp + " AND NDOC = '"+ ndoc +"'";

				}
				cont++;
			}

		}
		System.out.println(tmp);
		return p1.concat(tmp);
	}
}
