package DTO;

import java.time.LocalDate;
import java.util.List;

public class OcupacionDTO {
    private int id;
    private PasajeroBusquedaDTO responsable;
    private List<PasajeroBusquedaDTO> listaOcupantes;
    private HabitacionDTO habitacion;
    private ConsumoDTO consumo;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getCheckIn() {
        return checkIn;
    }
    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }
    public LocalDate getCheckOut() {
        return checkOut;
    }
    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }
    public PasajeroBusquedaDTO getResponsable() {
        return responsable;
    }
    public void setResponsable(PasajeroBusquedaDTO responsable) {
        this.responsable = responsable;
    }
    public HabitacionDTO getHabitacion() {
        return habitacion;
    }
    public void setHabitacion(HabitacionDTO habitacion) {
        this.habitacion = habitacion;
    }
    public List<PasajeroBusquedaDTO> getListaOcupantes() {
        return listaOcupantes;
    }
    public void setListaOcupantes(List<PasajeroBusquedaDTO> Acompanantes) {
        this.listaOcupantes = Acompanantes;
    }
    public ConsumoDTO getConsumo() {
        return consumo;
    }
    public void setConsumo(ConsumoDTO consumo) {
        this.consumo = consumo;
    }
}
