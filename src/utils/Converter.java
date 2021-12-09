package utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Locale;

public class Converter {

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toLocalDate();
    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public LocalDate convertStrtoLocalDate(String str){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US);
        //format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(str,format);
        }catch (DateTimeParseException e){
            System.out.println("Data Could not be parsed");
        }finally {
            //todo
        }
        return localDate;
    }

    public LocalDate convertCalendarToLocalDate(Calendar calendar){
        return LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate();
    }

}
