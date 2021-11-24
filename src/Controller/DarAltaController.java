package Controller;

import DTO.PasajeroDTO;
import Dominio.Pasajero;
import GUI.AltaPasajeroGUI;
import GUI.GestionPasajeroBusquedaGUI;
import GUI.GestionPasajeroGUI;
import Servicios.IDTypeServicio;
import Servicios.PasajeroServicio;

import javax.swing.*;
import java.util.List;

public class DarAltaController {
    private PasajeroServicio pasajeroServicio;
    private IDTypeServicio IDServicio;
    private Pasajero pasajero;
    private List<PasajeroDTO> lista;
    private GestionPasajeroGUI gestionGUI;
    private GestionPasajeroBusquedaGUI gbusquedaGUI;
    private AltaPasajeroGUI AltaPsjeroGUI;
    private final JTable table;


    public DarAltaController() {
        this.table = new JTable();
        this.pasajeroServicio = new PasajeroServicio();
        this.IDServicio = new IDTypeServicio();
    }

    public void DarAltaPasajero(){
        AltaPsjeroGUI = new AltaPasajeroGUI();
        AltaPsjeroGUI.setController(this);
        gbusquedaGUI.setVisible(false);
        AltaPsjeroGUI.setVisible(true);
    }
}
