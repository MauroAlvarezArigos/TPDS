package Dominio;

import java.time.LocalDate;

public class PeriodoEstadia {
    private int id_estadia;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private double monto;
    private Boolean mediaEstadia;

    public PeriodoEstadia(LocalDate fechaInicio, LocalDate fechaFinal, double monto, Boolean mediaEstadia) {
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.monto = monto;
        this.mediaEstadia = mediaEstadia;
    }

    public int getId_estadia() {
        return id_estadia;
    }
    public void setId_estadia(int id_estadia) {
        this.id_estadia = id_estadia;
    }
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public LocalDate getFechaFinal() {
        return fechaFinal;
    }
    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public Boolean getMediaEstadia() {
        return mediaEstadia;
    }
    public void setMediaEstadia(Boolean mediaEstadia) {
        this.mediaEstadia = mediaEstadia;
    }
}
