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

import static java.lang.Boolean.TRUE;

public class PasajeroServicio {
	
	PasajeroDAO pasajerodao;
	MapperPasajero mapper = new MapperPasajero();
	
	public PasajeroServicio() {
		pasajerodao = new PasajeroDAOSQL();
	}
	
	public List<PasajeroDTO> buscarPasajero(String nombre, String apellido, String tipoDoc, String ndoc) throws NoConcordanciaException {

				List<Pasajero> LPsjero = pasajerodao.buscar(nombre, apellido, tipoDoc, ndoc);
				List<PasajeroDTO> LPsjeroDTO = new ArrayList<PasajeroDTO>();

				int size = LPsjero.size();
				for(int c = 0; c < size; c++){
					LPsjeroDTO.add(mapper.toDTO(LPsjero.get(c)));
				}

		return LPsjeroDTO;
	}

	public void DarAltaPasajero(boolean skip, PasajeroDTO unPasajeroDTO) throws DuplicateDocNumberException {
		Pasajero unPasajero;
		unPasajero = mapper.toDomain(unPasajeroDTO);
		if(skip == TRUE){
			pasajerodao.DocRepetido((unPasajero.getTipodoc()), unPasajero.getNdoc());
		}
		pasajerodao.insert(unPasajero);
	}

}
