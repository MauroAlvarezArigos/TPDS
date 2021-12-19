package Servicios;

import DAO.PersonaJuridicaDAO;
import DAO.utils.DAOManager;
import Dominio.PersonaJuridica;

public class PersonaJuridicaServicio {
	DAOManager daoManager;
	PersonaJuridicaDAO pjDAO; 
	
	public PersonaJuridicaServicio() {super();}
	
	public PersonaJuridica getPersonaJuridica(String cuit) {
		daoManager = new DAOManager();
		pjDAO = daoManager.getPersonaJuridicaDAO();
		daoManager.begin();
		
		PersonaJuridica pj = pjDAO.getPersonaJuridicaCUIT(cuit);
		
		daoManager.commit();
		daoManager.disconnect();
		
		return pj;
	}

}
