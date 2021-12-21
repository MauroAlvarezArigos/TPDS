//todo al guardar la localidad y provincia verficar que pertenezcan al pais y provincia respectivamente
//todo autocompletar el CP al seleccionar una ciudad y automcompletar ciudad al ingresar un CP con pais seleccionado

package Controller;

import DTO.*;
import Dominio.Pasajero;
import Exceptions.DuplicateDocNumberException;
import GUI.AltaPasajeroGUI;
import GUI.GestionPasajeroBusquedaGUI;
import GUI.GestionPasajeroGUI;
import Servicios.IDTypeServicio;
import Servicios.IVAServicio;
import Servicios.PasajeroServicio;
import Servicios.UbicacionServicio;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DarAltaController {

    //Colors
    Color Warning = new Color(255,0,0);
    Color CancelButton = new Color(255,0,0);
    Color NextButton = new Color(0,128,0);

    private PasajeroServicio pasajeroServicio;
    private UbicacionServicio ubicacionServicio;
    private IDTypeServicio IDServicio;
    private Pasajero pasajero;
    private List<PasajeroDTO> lista;
    private GestionPasajeroGUI gestionGUI;
    private GestionPasajeroBusquedaGUI gbusquedaGUI;
    private AltaPasajeroGUI AltaPsjeroGUI;
    private PasajeroDTO pasajeroDTO;
    private IVAServicio IVAServicio;

    private List<LocalidadDTO> ListaLocalidadDTO;


    public DarAltaController(AltaPasajeroGUI a) {
        this.pasajeroServicio = new PasajeroServicio();
        this.ubicacionServicio = new UbicacionServicio();
        this.IDServicio = new IDTypeServicio();
        this.IVAServicio = new IVAServicio();
        this.AltaPsjeroGUI = a;
        this.AltaPsjeroGUI.setWarningColor(Warning);
        this.AltaPsjeroGUI.setCancelButtonColor(CancelButton);
        this.AltaPsjeroGUI.setNextButtonColor(NextButton);
    }

    public void DarAltaPasajero(){
        //AltaPsjeroGUI.setController(this);
        AltaPsjeroGUI.setVisible(true);
    }

    public void InformarOmisionnesDatosGUI(){
        boolean bool = true;

        if(AltaPsjeroGUI.getTbxApellidoStr().equals("") || AltaPsjeroGUI.getTbxApellidoStr() == null){
            bool = false;
            highlightInput(AltaPsjeroGUI.getLblApellido(), AltaPsjeroGUI.getTbxApellido(),Warning);
        }
        if(AltaPsjeroGUI.getTbxNombreStr().equals("") || AltaPsjeroGUI.getTbxNombreStr() == null){
            bool = false;
            highlightInput(AltaPsjeroGUI.getLblNombre(), AltaPsjeroGUI.getTbxNombre(),Warning);
        }
        if(AltaPsjeroGUI.getSelectedCbxNacionalidad().equals("") || AltaPsjeroGUI.getSelectedCbxNacionalidad() == null){
            bool = false;
            highlightInput(AltaPsjeroGUI.getLblNacionalidad(), AltaPsjeroGUI.getCbxNacionalidad(),Warning);
        }
        if(AltaPsjeroGUI.getDatePanelFechNac() == null){
            bool = false;
            highlightInput(AltaPsjeroGUI.getLblFecNac(), AltaPsjeroGUI.getDatePicker(),Warning);
        }
        //AltaPsjeroGUI.getTbxEmail();
        if(AltaPsjeroGUI.getTbxNroDocStr().equals("") || AltaPsjeroGUI.getTbxNroDocStr() == null){
            bool = false;
            highlightInput(AltaPsjeroGUI.getLblNroDni(),AltaPsjeroGUI.getTbxNroDoc(),Warning);
        }
        if(AltaPsjeroGUI.getTbxTelefonoStr().equals("") || AltaPsjeroGUI.getTbxTelefonoStr() == null){
            bool = false;
            highlightInput(AltaPsjeroGUI.getLblTelefono(), AltaPsjeroGUI.getTbxTelefono(),Warning);
        }
        if(AltaPsjeroGUI.getTbxOcupacionStr().equals("") || AltaPsjeroGUI.getTbxOcupacionStr() == null){
            bool = false;
            highlightInput(AltaPsjeroGUI.getLblOcupacion(), AltaPsjeroGUI.getTbxOcupacion(),Warning);
        }
        //AltaPsjeroGUI.getTbxCuitStr();

        if(AltaPsjeroGUI.getTbxCalleStr().equals("") || AltaPsjeroGUI.getTbxCalleStr() == null){
            bool = false;
            highlightInput(AltaPsjeroGUI.getLblCalle(),AltaPsjeroGUI.getTbxCalle(),Warning);
        }
        if(AltaPsjeroGUI.getTbxCodPostalStr().equals("") || AltaPsjeroGUI.getTbxCodPostalStr() == null){
            bool = false;
            highlightInput(AltaPsjeroGUI.getLblCP(), AltaPsjeroGUI.getTbxCodPostal(),Warning);
        }
        if(AltaPsjeroGUI.getTbxDireccionNroStr().equals("") || AltaPsjeroGUI.getTbxDireccionNroStr() == null){
            bool = false;
            highlightInput(AltaPsjeroGUI.getLblDirNumero(), AltaPsjeroGUI.getTbxDireccionNro(),Warning);
        }
        if(AltaPsjeroGUI.getCheckDpto().isSelected()){
        if(AltaPsjeroGUI.getTbxDptoStr().equals("") || AltaPsjeroGUI.getTbxDptoStr() == null){
            bool = false;
            highlightInput(AltaPsjeroGUI.getLblDpto(), AltaPsjeroGUI.getTbxDpto(), Warning);
        }
        if(AltaPsjeroGUI.getTbxPisoStr().equals("") || AltaPsjeroGUI.getTbxPisoStr() == null){
            bool = false;
            highlightInput(AltaPsjeroGUI.getLblPiso(), AltaPsjeroGUI.getTbxPiso(),Warning);
        }
        }


        if(AltaPsjeroGUI.getSelectedCbxPais() == null || AltaPsjeroGUI.getSelectedCbxPais().equals("")){
            bool = false;
            highlightInput(AltaPsjeroGUI.getLblPais(), AltaPsjeroGUI.getCbxPais(),Warning);
        }
        if(AltaPsjeroGUI.getSelectedCbxProvincia() == null || AltaPsjeroGUI.getSelectedCbxProvincia().equals("")){
            bool = false;
            highlightInput(AltaPsjeroGUI.getLblProvincia(), AltaPsjeroGUI.getCbxProvincia(),Warning);
        }
        if(AltaPsjeroGUI.getSelectedCbxLocalidad() == null || AltaPsjeroGUI.getSelectedCbxLocalidad().equals("")){
            bool = false;
            highlightInput(AltaPsjeroGUI.getLblLocalidad(), AltaPsjeroGUI.getCbxLocalidad(),Warning);
        }
        if(AltaPsjeroGUI.getSelectedCbxTipoDNI().equals("") || AltaPsjeroGUI.getSelectedCbxTipoDNI() == null){
            bool = false;
            highlightInput(AltaPsjeroGUI.getLblTipo(), AltaPsjeroGUI.getCbxTDoc(),Warning);
        }

        boolean success = false;
        if(bool){
            try
            {
                revisarDocExistente(AltaPsjeroGUI.getTbxNroDocStr(),AltaPsjeroGUI.getSelectedCbxTipoDNI());
                success = true;
            }catch (DuplicateDocNumberException e){
                informarDocExistenteGUI();
            }
            if (success){
                cargarPasajeroGUI();
            }
        }
        //return bool;
    }

    public void cargarIVA(){
        JComboBox<String> tiva = AltaPsjeroGUI.getCbxIVA();
        tiva.removeAllItems();
        List<PosIVADTO> ListaPosIVA = IVAServicio.getAllIVA();
        tiva.addItem("");
        for (PosIVADTO p : ListaPosIVA){
            tiva.addItem(p.getTipo());
        }
        AltaPsjeroGUI.setCbxIVA(tiva);
    }

    public void cargarTDNI() {
        JComboBox<String> tdni = AltaPsjeroGUI.getCbxTipoDNI();
        tdni.removeAllItems();
        List<IDTypeDTO> ListaIDT = IDServicio.getAllIDType();
        tdni.addItem("");
        for (IDTypeDTO idTypeDTO : ListaIDT) {
            tdni.addItem(idTypeDTO.getTipo());
        }
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
        ListaLocalidadDTO = ubicacionServicio.getAllLocalidadesProv(n_prov);

        lloc.addItem("");
        for (LocalidadDTO LocDTO : ListaLocalidadDTO){
            lloc.addItem(LocDTO.getLoc());
        }
    }

    public void cargarNacionalidad(){
        List<String> ListNacionalidades = ubicacionServicio.getAllNacionalidad();
        JComboBox<String> nac = AltaPsjeroGUI.getCbxNacionalidad();

       nac.addItem("");
        for (String Nacionalidad : ListNacionalidades) {
            nac.addItem(Nacionalidad);
        }
    }

    public void revisarDocExistente(String NDoc, String TipoDoc) throws DuplicateDocNumberException {
        pasajeroServicio.revisarDocExistente(NDoc, TipoDoc);
    }

    public void informarDocExistenteGUI(){
          if(AltaPsjeroGUI.optionMessageGUI(
                  "Numero de Documento Existente",
                  "¡CUIDADO! el tipo y numero de documento ya existen en el sistema",
                  new String[]{"ACEPTAR IGUALMENTE",
                          "CORREGIR"})
                  == JOptionPane.YES_OPTION){

              this.cargarPasajeroGUI();

          }else{
              AltaPsjeroGUI.getCbxTDoc().requestFocus();
              AltaPsjeroGUI.getCbxTDoc().setBorder(BorderFactory.createLineBorder(Warning));
              AltaPsjeroGUI.getTbxNroDoc().setBorder(BorderFactory.createLineBorder(Warning));
              AltaPsjeroGUI.getLblNroDni().setForeground(Warning);
              AltaPsjeroGUI.getLblTipo().setForeground(Warning);
          }
    }

    public void cargarPasajeroGUI() {
        pasajeroDTO = new PasajeroDTO();
        pasajeroDTO.setApellido(AltaPsjeroGUI.getTbxApellidoStr());
        pasajeroDTO.setNombre(AltaPsjeroGUI.getTbxNombreStr());
        pasajeroDTO.setNdoc(AltaPsjeroGUI.getTbxNroDocStr());
        pasajeroDTO.setTipodoc(AltaPsjeroGUI.getSelectedCbxTipoDNI());
        pasajeroDTO.setOcupacion(AltaPsjeroGUI.getTbxOcupacionStr());
        pasajeroDTO.setFechanacimiento(AltaPsjeroGUI.getDatePanelFechNac());
        pasajeroDTO.setNacionalidad(AltaPsjeroGUI.getSelectedCbxNacionalidad());
        pasajeroDTO.setPais(AltaPsjeroGUI.getSelectedCbxPais());
        pasajeroDTO.setProvincia(AltaPsjeroGUI.getSelectedCbxProvincia());
        pasajeroDTO.setLocalidad(AltaPsjeroGUI.getSelectedCbxLocalidad());

        pasajeroDTO.setTelefono(AltaPsjeroGUI.getTbxTelefonoStr());
        pasajeroDTO.setEmail(AltaPsjeroGUI.getTbxTelefonoStr());
        pasajeroDTO.setCuit_cif(AltaPsjeroGUI.getTbxCuitStr());
        pasajeroDTO.setCalle(AltaPsjeroGUI.getTbxCalleStr());
        pasajeroDTO.setAltura(AltaPsjeroGUI.getTbxDireccionNroStr());
        pasajeroDTO.setIVA(AltaPsjeroGUI.getSelectedCbxIVA());

        pasajeroServicio.guardarPasajero(pasajeroDTO);
        cargarOtroMensajeGUI();
    }

    public void cargarOtroMensajeGUI(){
        if(AltaPsjeroGUI.optionMessageGUI(
                "Pasajero Guardado",
                "El pasajero "+/*pasajeroDTO.getNombre()+*/" "
                        +/*pasajeroDTO.getApellido()+*/" ha sido satisfactoriamente cargado al sistema." +
                        " ¿desea cargar otro?",
                new String[]{"SI","NO"})
        == JOptionPane.NO_OPTION){
            AltaPsjeroGUI.dispose();
        }else{
            AltaPsjeroGUI.dispose();
            AltaPsjeroGUI = new AltaPasajeroGUI();
        }
    }


    public void mensajeAceptarCancelarGUI(){
        if(AltaPsjeroGUI.optionMessageGUI(
                "Cancelar",
                "Esta Seguro que desea cerrar esta ventana",
                new String[]{"SI","NO"})
                == JOptionPane.YES_OPTION){
            AltaPsjeroGUI.dispose();
        }
    }

    public void highlightInput(JComponent label, JComponent container, Color highlightColor){
        Color Red = new Color(255,0,0);
        label.setForeground(Red);
        container.setBorder(BorderFactory.createLineBorder(Red));
    }

    public String getCodigoPostal(){
        for(LocalidadDTO l : ListaLocalidadDTO){
            if(l.getLoc().equals(AltaPsjeroGUI.getSelectedCbxLocalidad())){
                return l.getCodigoPostal();
            }
        }
        return "";
    }
}
