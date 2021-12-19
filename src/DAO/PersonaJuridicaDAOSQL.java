package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Dominio.PersonaJuridica;
import Dominio.PosIVA;

public class PersonaJuridicaDAOSQL implements PersonaJuridicaDAO{
	
	private Connection conn;
	
	
	public PersonaJuridicaDAOSQL(Connection conn) {
		this.conn = conn;
	}
	
	private static final String GET_PERSONAJURIDICA = "SELECT * FROM PERSONA_JURIDICA pj, PERSONA p "
			+ "WHERE p.idpersona = pj.idpersona AND p.cuit = ";

	@Override
	public PersonaJuridica getPersonaJuridicaCUIT(String CUIT) {
	      String Sentencia = GET_PERSONAJURIDICA + "'" + CUIT + "'";
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        PersonaJuridica personaJuridica = new PersonaJuridica();
	        try {
	            pstmt = conn.prepareStatement(Sentencia);
	            rs = pstmt.executeQuery();
	            if(rs.next()) {
	            	personaJuridica.setRazonSocial(rs.getString("RAZONSOCIAL"));
	            	System.out.println("RazonSocial: "+rs.getString("RAZONSOCIAL"));
	            	personaJuridica.setDomicilioFiscal(rs.getString("DOMICILIOFISCAL"));
	            	System.out.println("DomicilioFiscal: "+rs.getString("DOMICILIOFISCAL"));
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
