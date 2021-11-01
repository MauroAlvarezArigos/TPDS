package Servicios;

import java.util.List;

import DAO.PasajeroDAO;
import DAO.PasajeroDAOSQL;
import Dominio.Pasajero;
import Exceptions.DuplicateDocNumberException;
import Exceptions.NoConcordanciaException;

import static java.lang.Boolean.TRUE;

public class PasajeroServicio {
	
	PasajeroDAO pasajerodao;
	
	public PasajeroServicio() {
		pasajerodao = new PasajeroDAOSQL();
	}
	
	public List<Pasajero> buscarPasajero(String nombre, String apellido, String tipoDoc, String ndoc) throws NoConcordanciaException {
		return pasajerodao.buscar(nombre, apellido, tipoDoc, ndoc);
	}

	public void DarAltaPasajero(boolean skip, Pasajero unPasajero) throws DuplicateDocNumberException {
		if(skip == TRUE){
			pasajerodao.DocRepetido(unPasajero.getTipodoc(), unPasajero.getNdoc());
		}
		pasajerodao.insert(unPasajero);
	}

}
