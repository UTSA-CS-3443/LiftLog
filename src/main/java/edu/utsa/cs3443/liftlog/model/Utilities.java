package edu.utsa.cs3443.liftlog.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Utilities {

    public String getDate(){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);

        return today.format(formatter);
    }

    //POTENTIAL AREA FOR CONVERTING POUNDS TO KILOS// KILOS TO POUNDS???

}
