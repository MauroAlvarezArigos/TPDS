package DAO;

import java.sql.Array;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import Excepciones.ExcepcionNoExisteElemento;
import DAO.utils.DB;
import Dominio.Pasajero;
import Exceptions.NoConcordanciaException;

public class PasajeroDAOSQL implements PasajeroDAO{
	
	private static final String INSERT_PASAJERO =
			"INSERT INTO PASAJERO(CUIT, NOMBRE, APELLIDO,"
			+"NDOC, TIPODOC, OCUPACION, NACIONALIDAD)"
			+ " VALUES(?, ?, ?, ?, ?, ?, ?)";
	

	@Override
	public Pasajero insert(Pasajero unPasajero) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement(INSERT_PASAJERO);
			pstmt.setString(1, unPasajero.getCuit_cif());
			pstmt.setString(2, unPasajero.getNombre());
			pstmt.setString(3, unPasajero.getApellido());
			pstmt.setString(4, unPasajero.getNdoc());
			pstmt.setString(5, unPasajero.getTipodoc().getTipoDeID());
			pstmt.setString(6, unPasajero.getOcupacion());
			//pstmt.setString(7, unPasajero.getFechanacimiento());
			pstmt.setInt(7, unPasajero.getNacionalidad().getCodigo());
				
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
			if(lista.size() == 0) {
				throw new NoConcordanciaException();
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
	
	private String prepararSentencia(String nombre, String apellido, String tipoDoc, String ndoc) {
		String p1 = "SELECT * FROM PASAJERO p " +
				"JOIN PERSONA per ON (p.cuit = per.cuit) ";
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
