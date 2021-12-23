package DTO;

import Dominio.Localidad;
import Dominio.PosIVA;

public class PersonaJuridicaDTO {

    private int idpersona;
    private String telefono;
    private String email;
    private String cuit_cif;
    private String calle;
    private String altura;
    private String posIVA;
    private LocalidadDTO localidad;

    private String razonSocial;
    private String domicilioFiscal;

    public int getIdpersona() {
        return idpersona;
    }
    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCuit_cif() {
        return cuit_cif;
    }
    public void setCuit_cif(String cuit_cif) {
        this.cuit_cif = cuit_cif;
    }
    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public String getAltura() {
        return altura;
    }
    public void setAltura(String altura) {
        this.altura = altura;
    }
    public String getPosIVA() {
        return posIVA;
    }
    public void setPosIVA(String posIVA) {
        this.posIVA = posIVA;
    }
    public LocalidadDTO getLocalidad() {
        return localidad;
    }
    public void setLocalidad(LocalidadDTO localidad) {
        this.localidad = localidad;
    }
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public String getDomicilioFiscal() {
        return domicilioFiscal;
    }
    public void setDomicilioFiscal(String domicilioFiscal) {
        this.domicilioFiscal = domicilioFiscal;
    }
}
