package Servicios;

import DAO.PersonaJuridicaDAO;
import DAO.utils.DAOManager;
import DTO.PersonaJuridicaDTO;
import Dominio.PersonaJuridica;
import Servicios.Mappers.MapperPersonaJuridica;

public class PersonaJuridicaServicio {
	DAOManager daoManager;
	PersonaJuridicaDAO pjDAO;
	MapperPersonaJuridica mapperPersonaJuridica;
	
	public PersonaJuridicaServicio() {super();}
	
	public PersonaJuridicaDTO getPersonaJuridica(String cuit) {
		daoManager = new DAOManager();
		pjDAO = daoManager.getPersonaJuridicaDAO();
		daoManager.begin();
		
		PersonaJuridica pj = pjDAO.getPersonaJuridicaCUIT(cuit);
		
		daoManager.commit();
		daoManager.disconnect();

		mapperPersonaJuridica = new MapperPersonaJuridica();

		PersonaJuridicaDTO personaJuridicaDTO = mapperPersonaJuridica.toDTO(pj);

		return personaJuridicaDTO;
	}

}
