package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Dominio.PersonaJuridica;

public class PersonaJuridicaDAOSQL implements PersonaJuridicaDAO{
	
	private Connection conn;
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
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        PersonaJuridica personaJuridica = new PersonaJuridica();
	        try {
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
