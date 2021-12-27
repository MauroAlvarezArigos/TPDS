package DAO;

import Dominio.PersonaJuridica;

public interface PersonaJuridicaDAO {
	public PersonaJuridica getPersonaJuridicaCUIT(String CUIT);
	public PersonaJuridica getPersonaJuridicaID(int id);
}
