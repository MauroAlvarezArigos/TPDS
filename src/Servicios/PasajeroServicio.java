package Servicios;

import java.util.List;

import DAO.PasajeroDAO;
import DAO.PasajeroDAOSQL;
import Dominio.Pasajero;
import Exceptions.NoConcordanciaException;

public class PasajeroServicio {
	
	PasajeroDAO pasajerodao;
	
	public PasajeroServicio() {
		pasajerodao = new PasajeroDAOSQL();
	}
	
	public List<Pasajero> buscarPasajero(String nombre, String apellido, String tipoDoc, String ndoc) throws NoConcordanciaException {
		return pasajerodao.buscar(nombre, apellido, tipoDoc, ndoc);
	}
	

}
