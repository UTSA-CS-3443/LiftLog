package edu.utsa.cs3443.liftlog.model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Utilities {

    public String getDate() {
        return formatDate(LocalDate.now());
    }

    public String formatDate(LocalDate date) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MMM. d, yyyy", Locale.ENGLISH);

        return date.format(formatter);
    }

    //POTENTIAL AREA FOR CONVERTING POUNDS TO KILOS// KILOS TO POUNDS???

}
