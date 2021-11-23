package Servicios;

import java.util.ArrayList;
import java.util.List;

import DAO.PasajeroDAO;
import DAO.PasajeroDAOSQL;
import DTO.PasajeroDTO;
import Dominio.Pasajero;
import Exceptions.DuplicateDocNumberException;
import Exceptions.NoConcordanciaException;
import Servicios.Mappers.MapperPasajero;

public class PasajeroServicio {
	
	PasajeroDAO pasajerodao;
	MapperPasajero mapper = new MapperPasajero();
	
	public PasajeroServicio() {
		pasajerodao = new PasajeroDAOSQL();
	}
	
	public List<PasajeroDTO> buscarPasajero(String nombre, String apellido, String tipoDoc, String ndoc) throws NoConcordanciaException {

		List<Pasajero> LPsjero = pasajerodao.buscar(nombre, apellido, tipoDoc, ndoc);
		List<PasajeroDTO> LPsjeroDTO = new ArrayList<PasajeroDTO>();

		if(LPsjero.size() == 0) {
			throw new NoConcordanciaException();
		}
		else {
			LPsjeroDTO = pasajerosToDTO(LPsjero);
		}

		return LPsjeroDTO;
	}
	
	private List<PasajeroDTO> pasajerosToDTO(List<Pasajero> LPsjero) {
		List<PasajeroDTO> LPsjeroDTO = new ArrayList<PasajeroDTO>();
		for(Pasajero p : LPsjero) {
			LPsjeroDTO.add(mapper.toDTO(p));
		}
		return LPsjeroDTO;
	}
	
	public void DarAltaPasajero(boolean skip, PasajeroDTO unPasajeroDTO) throws DuplicateDocNumberException {
		Pasajero unPasajero;
		unPasajero = mapper.toDomain(unPasajeroDTO);
		if(skip){
			pasajerodao.docRepetido((unPasajero.getTipodoc()), unPasajero.getNdoc());
		}
		pasajerodao.insert(unPasajero);
	}

}
