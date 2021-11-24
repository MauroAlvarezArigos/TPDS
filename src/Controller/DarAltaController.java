package Controller;

import DTO.PasajeroDTO;
import Dominio.Pasajero;
import Exceptions.DuplicateDocNumberException;
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


    public DarAltaController() {
        this.pasajeroServicio = new PasajeroServicio();
        this.IDServicio = new IDTypeServicio();
    }

    public void DarAltaPasajero(){
        AltaPsjeroGUI = new AltaPasajeroGUI();
        AltaPsjeroGUI.setController(this);
        AltaPsjeroGUI.setVisible(true);
    }

    public void InformarOmisionnesDatosGUI(List<String> Datos){



    }

    public void revisarDocExistente(String NDoc, String TipoDoc) throws DuplicateDocNumberException {
        pasajeroServicio.revisarDocExistente(NDoc, TipoDoc);
    }

    public void informarDocExistenteGUI(){

    }

    public void cargarOtroMensajeGUI(){

    }

    public void cargarPasajeroGUI(){

    }

    public void mensajeAceptarCancelarGUI(){

    }



}
