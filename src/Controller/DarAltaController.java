package Controller;

import DTO.IDTypeDTO;
import DTO.PaisDTO;
import DTO.PasajeroDTO;
import Dominio.Pasajero;
import Exceptions.DuplicateDocNumberException;
import GUI.AltaPasajeroGUI;
import GUI.GestionPasajeroBusquedaGUI;
import GUI.GestionPasajeroGUI;
import Servicios.IDTypeServicio;
import Servicios.PasajeroServicio;
import Servicios.UbicacionServicio;

import javax.swing.*;
import java.util.List;

public class DarAltaController {
    private PasajeroServicio pasajeroServicio;
    private UbicacionServicio ubicacionServicio;
    private IDTypeServicio IDServicio;
    private Pasajero pasajero;
    private List<PasajeroDTO> lista;
    private GestionPasajeroGUI gestionGUI;
    private GestionPasajeroBusquedaGUI gbusquedaGUI;
    private AltaPasajeroGUI AltaPsjeroGUI;


    public DarAltaController(AltaPasajeroGUI a) {
        this.pasajeroServicio = new PasajeroServicio();
        this.ubicacionServicio = new UbicacionServicio();
        this.IDServicio = new IDTypeServicio();
        this.AltaPsjeroGUI = a;
    }

    public void DarAltaPasajero(){
        //AltaPsjeroGUI.setController(this);
        AltaPsjeroGUI.setVisible(true);
    }

    public void InformarOmisionnesDatosGUI(List<String> Datos){


    }

    public void cargarTDNI() {
        JComboBox<String> tdni = new JComboBox<String>();
        List<IDTypeDTO> ListaIDT = IDServicio.getAllIDType();
        int size = ListaIDT.size();
        tdni.addItem("");
        for(int c = 0; c < size; c++) {
            tdni.addItem((ListaIDT.get(c)).getTipo());
        }
        //tdni.addItem("");
        //tdni.addItem("DNI");
        //tdni.addItem("LE");
        //tdni.addItem("LC");
        //tdni.addItem("Pasaporte");
        //tdni.addItem("Otro");
        AltaPsjeroGUI.setCbxTipoDNI(tdni);
    }

    public void cargarPais() {
        JComboBox<String> lpais = new JComboBox<String>();
        List<PaisDTO> ListaPaisesDTO = ubicacionServicio.getAllPais();

        int size = ListaPaisesDTO.size();
        lpais.addItem("");
        for(int c = 0; c < size; c++) {
            lpais.addItem((ListaPaisesDTO.get(c)).getPais());
        }
        AltaPsjeroGUI.setCbxPais(lpais);
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
    
    public List<String> cargarNacionalidadGUI(){
    	return ubicacionServicio.getAllNacionalidad();
    }



}
