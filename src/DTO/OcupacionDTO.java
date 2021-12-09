package DTO;

import java.time.LocalDate;

public class OcupacionDTO {
    private LocalDate checkIn;
    private LocalDate checkOut;

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
}
