package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Dominio.PersonaJuridica;
import Dominio.PosIVA;

public class PersonaJuridicaDAOSQL implements PersonaJuridicaDAO{
	
	private Connection conn;
<<<<<<< HEAD
	
	
	public PersonaJuridicaDAOSQL(Connection conn) {
		this.conn = conn;
	}
	
	private static final String GET_PERSONAJURIDICA = "SELECT * FROM PERSONA_JURIDICA pj, PERSONA p "
			+ "WHERE p.idpersona = pj.idpersona AND p.cuit = ";

	@Override
	public PersonaJuridica getPersonaJuridicaCUIT(String CUIT) {
	      String Sentencia = GET_PERSONAJURIDICA + "'" + CUIT + "'";
=======
	private PosIVADAOSQL posIVADAO;
	private UbicacionDAOSQL ubicacionDAO;
	
	
	public PersonaJuridicaDAOSQL(Connection conn) {

		this.conn = conn;
		posIVADAO = new PosIVADAOSQL(conn);
		ubicacionDAO = new UbicacionDAOSQL(conn);



	}
	
	private static final String GET_PERSONAJURIDICA = "SELECT * FROM PERSONAJURIDICA pj, PERSONA p "
			+ "WHERE p.idpersona = pj.idpersona AND p.cuit = ?";

	@Override
	public PersonaJuridica getPersonaJuridicaCUIT(String CUIT) {
>>>>>>> d1d1b9edd8ebb29cdf56269cd6aad38f9b3aaf61
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        PersonaJuridica personaJuridica = new PersonaJuridica();
	        try {
<<<<<<< HEAD
	            pstmt = conn.prepareStatement(Sentencia);
	            rs = pstmt.executeQuery();
	            if(rs.next()) {
	            	personaJuridica.setRazonSocial(rs.getString("RAZONSOCIAL"));
	            	System.out.println("RazonSocial: "+rs.getString("RAZONSOCIAL"));
	            	personaJuridica.setDomicilioFiscal(rs.getString("DOMICILIOFISCAL"));
	            	System.out.println("DomicilioFiscal: "+rs.getString("DOMICILIOFISCAL"));
=======
	            pstmt = conn.prepareStatement(GET_PERSONAJURIDICA);
				pstmt.setString(1,CUIT);
	            rs = pstmt.executeQuery();
	            if(rs.next()) {
	            	personaJuridica.setIdpersona(rs.getInt("IDPERSONA"));
					personaJuridica.setRazonSocial(rs.getString("RAZONSOCIAL"));
	            	personaJuridica.setDomicilioFiscal(rs.getString("DOMICILIOFISCAL"));
					personaJuridica.setCuit_cif(rs.getString("CUIT"));
					personaJuridica.setCalle(rs.getString("CALLE"));
					personaJuridica.setTelefono(rs.getString("TELEFONO"));
					personaJuridica.setAltura(rs.getString("ALTURA"));
					personaJuridica.setEmail(rs.getString("EMAIL"));
					personaJuridica.setIVA(posIVADAO.BuscarIVA(rs.getInt("POSIVA")));
					personaJuridica.setLocalidad(ubicacionDAO.buscarLocalidad(rs.getInt("LOCALIDAD")));

>>>>>>> d1d1b9edd8ebb29cdf56269cd6aad38f9b3aaf61
	            }
	            else {
	            	System.out.println("No se encontro la persona juridica");
	            	return null;
	            }
	            
	        }catch(SQLException e) {
	            e.printStackTrace();
	        }
	        finally {
	            try {
	                if(pstmt != null) pstmt.close();
	            }
	            catch(SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return personaJuridica;
	}

}
