package Servicios;

import java.util.ArrayList;
import java.util.List;

import DAO.PasajeroDAO;
import DAO.PasajeroDAOSQL;
import DAO.utils.DAOManager;
import DTO.PasajeroBusquedaDTO;
import DTO.PasajeroDTO;
import Dominio.IDType;
import Dominio.Pasajero;
import Exceptions.DuplicateDocNumberException;
import Exceptions.NoConcordanciaException;
import Servicios.Mappers.MapperPasajero;
import Servicios.Mappers.MapperPasajeroBusqueda;

public class PasajeroServicio {
	
	DAOManager daoManager;

	PasajeroDAO pasajerodao;

	MapperPasajeroBusqueda mapperB = new MapperPasajeroBusqueda();
	MapperPasajero mapperP = new MapperPasajero();
	IDTypeServicio IDServicio = new IDTypeServicio();

	public PasajeroServicio() {super(); }

	public List<PasajeroBusquedaDTO> buscarPasajero(String nombre, String apellido, String tipoDoc, String ndoc) throws NoConcordanciaException {

		daoManager = new DAOManager();

		pasajerodao = daoManager.getPasajeroDAO();

		daoManager.begin();

		List<Pasajero> LPsjero = pasajerodao.buscarGestion(nombre, apellido, tipoDoc, ndoc);
		List<PasajeroBusquedaDTO> LPsjeroDTO;

		if(LPsjero.size() == 0) {
			throw new NoConcordanciaException();
		}
		else {
			LPsjeroDTO = pasajerosToDTO(LPsjero);
		}
		daoManager.commit();
		daoManager.disconnect();
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
		daoManager = new DAOManager();
		pasajerodao = daoManager.getPasajeroDAO();

		IDType ID = IDServicio.getIDType(TipoDoc);
		daoManager.begin();
		pasajerodao.docRepetido(ID,Ndoc);
		daoManager.commit();
		daoManager.disconnect();
	}
	
	public void DarAltaPasajero(boolean skip, PasajeroDTO unPasajeroDTO) throws DuplicateDocNumberException {
		Pasajero unPasajero;
		unPasajero = mapperP.toDomain(unPasajeroDTO);
		if(skip){
			pasajerodao.docRepetido((unPasajero.getTipodoc()), unPasajero.getNdoc());
		}
		pasajerodao.insert(unPasajero);
	}

	public void guardarPasajero(PasajeroDTO unPasajeroDTO){
		daoManager = new DAOManager();
		pasajerodao = daoManager.getPasajeroDAO();

		daoManager.begin();
		pasajerodao.insert(mapperP.toDomain(unPasajeroDTO));
		daoManager.commit();
		daoManager.disconnect();
	}

}
