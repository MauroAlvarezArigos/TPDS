package DTO;


public class ResponsableDePagoDTO {
    private int id_responsableDePago;
    private String razonSocial;
    private String cuitDni;
    private String numDireccion;
    private String calle;
    private String telefono;
    private Object persona_asociada;

    public int getId_responsableDePago() {
        return id_responsableDePago;
    }
    public void setId_responsableDePago(int id_responsableDePago) {
        this.id_responsableDePago = id_responsableDePago;
    }
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public String getCuitDni() {
        return cuitDni;
    }
    public void setCuitDni(String cuitDni) {
        this.cuitDni = cuitDni;
    }
    public String getNumDireccion() {
        return numDireccion;
    }
    public void setNumDireccion(String numDireccion) {
        this.numDireccion = numDireccion;
    }
    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public Object getPersona_asociada() {
        return persona_asociada;
    }
    public void setPersona_asociada(PasajeroBusquedaDTO persona_asociada) {
        this.persona_asociada = persona_asociada;
    }
    public void setPersona_asociada(PersonaJuridicaDTO persona_asociada){this.persona_asociada = persona_asociada;}
}
