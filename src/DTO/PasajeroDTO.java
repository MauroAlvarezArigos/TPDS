package DTO;

public class PasajeroDTO {
//Pasajero
    private String nombre;
    private String apellido;
    private String ndoc;
    private String tipodoc;
    private String ocupacion;
    private String fechanacimiento;
    private String nacionalidad;
    private String pais;
    private String provincia;
    private String localidad;

//Persona
    private String telefono;
    private String email;
    private String cuit_cif;
    private String calle;
    private Integer altura;
    private String IVA;

//Getters and Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getNdoc() {
        return ndoc;
    }
    public void setNdoc(String ndoc) {
        this.ndoc = ndoc;
    }
    public String getTipodoc() {
        return tipodoc;
    }
    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }
    public String getOcupacion() {
        return ocupacion;
    }
    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
    public String getFechanacimiento() {
        return fechanacimiento;
    }
    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
    public String getNacionalidad() {
        return nacionalidad;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
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
    public Integer getAltura() {
        return altura;
    }
    public void setAltura(Integer altura) {
        this.altura = altura;
    }
    public String getIVA() {
        return IVA;
    }
    public void setIVA(String IVA) {
        this.IVA = IVA;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getProvincia() {
        return provincia;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public String getLocalidad() {
        return localidad;
    }
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
