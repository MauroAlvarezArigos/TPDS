package DTO;

import java.time.LocalDate;

public class PasajeroBusquedaDTO {

    //Pasajero
        //todo: Agregar ID Pasajero
        private int dbid;
        private String nombre;
        private String apellido;
        private String ndoc;
        private String tipodoc;
        private LocalDate fechanacimiento;

    public int getDbid() {
        return dbid;
    }
    public void setDbid(int dbid) {
        this.dbid = dbid;
    }
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
	public LocalDate getFechanacimiento() {
		return fechanacimiento;
	}
	public void setFechanacimiento(LocalDate fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}
    
}
