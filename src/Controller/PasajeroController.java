package Controller;

import Dominio.Pasajero;
import GUI.GestionPasajeroGUI;
import Servicios.PasajeroServicio;

public class PasajeroController {
	private PasajeroServicio pasajeroServicio;
	private Pasajero pasajero;
	private GestionPasajeroGUI gestionGUI;
	
	//Constructor
	public PasajeroController(GestionPasajeroGUI g) {
		this.pasajeroServicio = new PasajeroServicio();
		this.gestionGUI = g;
	}

}
