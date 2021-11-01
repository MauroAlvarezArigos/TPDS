package DAO;

import java.util.List;

import Dominio.IDType;
import Dominio.Pasajero;
import Exceptions.DuplicateDocNumberException;
import Exceptions.NoConcordanciaException;

public interface PasajeroDAO {
	public Pasajero insert(Pasajero unPasajero);
	public List<Pasajero> buscar(String nombre, String apellido, String tipoDoc, String ndoc) throws NoConcordanciaException;
	public void DocRepetido(IDType IDtipo, String Ndoc) throws DuplicateDocNumberException;
}
