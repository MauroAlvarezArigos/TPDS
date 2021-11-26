package Servicios;

import java.util.ArrayList;
import java.util.List;

import DAO.PasajeroDAO;
import DAO.PasajeroDAOSQL;
import DTO.PasajeroBusquedaDTO;
import DTO.PasajeroDTO;
import Dominio.IDType;
import Dominio.Pasajero;
import Exceptions.DuplicateDocNumberException;
import Exceptions.NoConcordanciaException;
import Servicios.Mappers.MapperPasajero;
import Servicios.Mappers.MapperPasajeroBusqueda;

public class PasajeroServicio {
	
	PasajeroDAO pasajerodao;
	IDTypeServicio IDServicio = new IDTypeServicio();
	MapperPasajeroBusqueda mapperB = new MapperPasajeroBusqueda();
	MapperPasajero mapperP = new MapperPasajero();
	
	public PasajeroServicio() {
		pasajerodao = new PasajeroDAOSQL();
	}
	
	public List<PasajeroBusquedaDTO> buscarPasajero(String nombre, String apellido, String tipoDoc, String ndoc) throws NoConcordanciaException {

		List<Pasajero> LPsjero = pasajerodao.buscarGestion(nombre, apellido, tipoDoc, ndoc);
		List<PasajeroBusquedaDTO> LPsjeroDTO;

		if(LPsjero.size() == 0) {
			throw new NoConcordanciaException();
		}
		else {
			LPsjeroDTO = pasajerosToDTO(LPsjero);
		}

		return LPsjeroDTO;
	}
	
	private List<PasajeroBusquedaDTO> pasajerosToDTO(List<Pasajero> LPsjero) {
		List<PasajeroBusquedaDTO> LPsjeroDTO = new ArrayList<PasajeroBusquedaDTO>();
		for(Pasajero p : LPsjero) {
			LPsjeroDTO.add(mapperB.toDTO(p));
		}
		return LPsjeroDTO;
	}

	public void revisarDocExistente(String Ndoc, String TipoDoc) throws DuplicateDocNumberException{
		IDType ID = IDServicio.getIDType(TipoDoc);
		pasajerodao.docRepetido(ID,Ndoc);
	}
	
	public void DarAltaPasajero(boolean skip, PasajeroDTO unPasajeroDTO) throws DuplicateDocNumberException {
		Pasajero unPasajero;
		unPasajero = mapperP.toDomain(unPasajeroDTO);
		if(skip){
			pasajerodao.docRepetido((unPasajero.getTipodoc()), unPasajero.getNdoc());
		}
		pasajerodao.insert(unPasajero);
	}

}
