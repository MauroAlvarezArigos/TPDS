package Controller;

import DTO.*;
import Dominio.Pasajero;
import Exceptions.DuplicateDocNumberException;
import GUI.AltaPasajeroGUI;
import GUI.GestionPasajeroBusquedaGUI;
import GUI.GestionPasajeroGUI;
import Servicios.IDTypeServicio;
import Servicios.PasajeroServicio;
import Servicios.UbicacionServicio;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
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

    public boolean InformarOmisionnesDatosGUI(){
        boolean bool = false;

        if(AltaPsjeroGUI.getTbxApellidoStr().equals("") || AltaPsjeroGUI.getTbxApellidoStr() == null){
            bool = true;
            highlightInput(AltaPsjeroGUI.getLblApellido(), AltaPsjeroGUI.getTbxApellido());
        }
        if(AltaPsjeroGUI.getTbxNombreStr().equals("") || AltaPsjeroGUI.getTbxNombreStr() == null){
            bool = true;
            highlightInput(AltaPsjeroGUI.getLblNombre(), AltaPsjeroGUI.getTbxNombre());
        }
        if(AltaPsjeroGUI.getSelectedCbxNacionalidad().equals("") || AltaPsjeroGUI.getSelectedCbxNacionalidad() == null){
            bool = true;
            highlightInput(AltaPsjeroGUI.getLblNacionalidad(), AltaPsjeroGUI.getCbxNacionalidad());
        }
        if(AltaPsjeroGUI.getDatePanelFechNac() == null){
            bool = true;
            //todo highlightInput(AltaPsjeroGUI);
        }
        //AltaPsjeroGUI.getTbxEmail();
        if(AltaPsjeroGUI.getTbxNroDocStr().equals("") || AltaPsjeroGUI.getTbxNroDocStr() == null){
            bool = true;
            highlightInput(AltaPsjeroGUI.getLblNroDni(),AltaPsjeroGUI.getTbxNroDoc());
        }
        if(AltaPsjeroGUI.getTbxTelefonoStr().equals("") || AltaPsjeroGUI.getTbxTelefonoStr() == null){
            bool = true;
            highlightInput(AltaPsjeroGUI.getLblTelefono(), AltaPsjeroGUI.getTbxTelefono());
        }
        if(AltaPsjeroGUI.getTbxOcupacionStr().equals("") || AltaPsjeroGUI.getTbxOcupacionStr() == null){
            bool = true;
            highlightInput(AltaPsjeroGUI.getLblOcupacion(), AltaPsjeroGUI.getTbxOcupacion());
        }
        //AltaPsjeroGUI.getTbxCuitStr();

        if(AltaPsjeroGUI.getTbxCalleStr().equals("") || AltaPsjeroGUI.getTbxCalleStr() == null){
            bool = true;
            highlightInput(AltaPsjeroGUI.getLblCalle(),AltaPsjeroGUI.getTbxCalle());
        }
        if(AltaPsjeroGUI.getTbxCodPostalStr().equals("") || AltaPsjeroGUI.getTbxCodPostalStr() == null){
            bool = true;
            highlightInput(AltaPsjeroGUI.getLblCP(), AltaPsjeroGUI.getTbxCodPostal());
        }
        if(AltaPsjeroGUI.getTbxDireccionNroStr().equals("") || AltaPsjeroGUI.getTbxDireccionNroStr() == null){
            bool = true;
            highlightInput(AltaPsjeroGUI.getLblDirNumero(), AltaPsjeroGUI.getTbxDireccionNro());
        }
        if(AltaPsjeroGUI.getCheckDpto().isSelected()){
        if(AltaPsjeroGUI.getTbxDptoStr().equals("") || AltaPsjeroGUI.getTbxDptoStr() == null){
            bool = true;
            highlightInput(AltaPsjeroGUI.getLblDpto(), AltaPsjeroGUI.getTbxDpto());
        }
        if(AltaPsjeroGUI.getTbxPisoStr().equals("") || AltaPsjeroGUI.getTbxPisoStr() == null){
            bool = true;
            highlightInput(AltaPsjeroGUI.getLblPiso(), AltaPsjeroGUI.getTbxPiso());
        }
        }


        if(AltaPsjeroGUI.getSelectedCbxPais() == null || AltaPsjeroGUI.getSelectedCbxPais().equals("")){
            bool = true;
            highlightInput(AltaPsjeroGUI.getLblPais(), AltaPsjeroGUI.getCbxPais());
        }
        if(AltaPsjeroGUI.getSelectedCbxProvincia() == null || AltaPsjeroGUI.getSelectedCbxProvincia().equals("")){
            bool = true;
            highlightInput(AltaPsjeroGUI.getLblProvincia(), AltaPsjeroGUI.getCbxProvincia());
        }
        if(AltaPsjeroGUI.getSelectedCbxLocalidad() == null || AltaPsjeroGUI.getSelectedCbxLocalidad().equals("")){
            bool = true;
            highlightInput(AltaPsjeroGUI.getLblLocalidad(), AltaPsjeroGUI.getCbxLocalidad());
        }
        if(AltaPsjeroGUI.getSelectedCbxTipoDNI().equals("") || AltaPsjeroGUI.getSelectedCbxTipoDNI() == null){
            bool = true;
            System.out.println("Tipo Dni revisado");
            highlightInput(AltaPsjeroGUI.getLblTipo(), AltaPsjeroGUI.getCbxTDoc());
        }
        return bool;
    }

    public void cargarTDNI() {
        JComboBox<String> tdni = AltaPsjeroGUI.getCbxTipoDNI();
        tdni.removeAllItems();
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
        JComboBox<String> lpais = AltaPsjeroGUI.getCbxPais();
        lpais.removeAllItems();
        List<PaisDTO> ListaPaisesDTO = ubicacionServicio.getAllPais();

        lpais.addItem("");
        for (PaisDTO paisDTO : ListaPaisesDTO) {
            lpais.addItem(paisDTO.getPais());
        }
        //AltaPsjeroGUI.setCbxPais(lpais);
    }

    public void cargarProvincia(){
        JComboBox<String> lprov = AltaPsjeroGUI.getCbxProvincia();
        lprov.removeAllItems();
        String n_pais = AltaPsjeroGUI.getSelectedCbxPais();
        List<ProvDTO> ListaProvDTO = ubicacionServicio.getAllProvinciasPais(n_pais);

        lprov.addItem("");
        for (ProvDTO provDTO : ListaProvDTO){
            lprov.addItem(provDTO.getProv());
        }
        //AltaPsjeroGUI.setCbxProv(lprov);
    }

    public void cargarLocalidad(){
        JComboBox<String> lloc = AltaPsjeroGUI.getCbxLocalidad();
        lloc.removeAllItems();
        String n_prov = AltaPsjeroGUI.getSelectedCbxProvincia();
        List<LocalidadDTO> ListaLocalidadDTO = ubicacionServicio.getAllLocalidadesProv(n_prov);

        lloc.addItem("");
        for (LocalidadDTO LocDTO : ListaLocalidadDTO){
            lloc.addItem(LocDTO.getLoc());
        }
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

    public void highlightInput(JComponent label, JComponent container){
        Color Red = new Color(255,0,0);
        label.setForeground(Red);
        container.setBorder(BorderFactory.createLineBorder(Red));
    }



}
